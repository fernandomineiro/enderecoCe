package moduloEndereco.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import moduloEndereco.batch.EnderecoGisRotina;
import moduloEndereco.excecoes.MsgGenericaPersonalizadaException;
import moduloEndereco.model.Bairro;
import moduloEndereco.model.IdBairroIdLocalidade;
import moduloEndereco.model.IdLogradouroIdLocalidade;
import moduloEndereco.model.Logradouro;
import moduloEndereco.model.MunicipioLocalidade;
import moduloEndereco.repository.BairroRepository;
import moduloEndereco.repository.EnderecoGisRepository;
import moduloEndereco.repository.LogradouroRepository;
import moduloEndereco.repository.MunicipioLocalidadeRepository;
import moduloEndereco.repository.filter.EnderecoGisFilter;
import moduloEndereco.service.EnderecoGisService;
import moduloEndereco.service.dto.CriticaEnderecoDTO;
import moduloEndereco.service.dto.CronogramaFaturaDTO;
import moduloEndereco.service.dto.EnderecoGisDTO;
import moduloEndereco.service.dto.EnderecoGisDTOWrapper;
import moduloEndereco.service.dto.EnderecoGisIdsDTO;
import moduloEndereco.service.dto.ImovelDTO;
import moduloEndereco.service.mapper.EnderecoGisMapper;
import moduloEndereco.util.Formulas;
import moduloEndereco.util.UrlMicroservico;

@Service
public class EnderecoGisServiceImpl implements EnderecoGisService {

	@Autowired
	private EnderecoGisRepository enderecoGisRepository;
	@Autowired
	private BairroRepository bairroRepository;
	@Autowired
	private LogradouroRepository logradouroRepository;
	@Autowired
	private MunicipioLocalidadeRepository municipioLocalidadeRepository;
	@Autowired
	private EnderecoGisMapper enderecoGisMapper;
	@Autowired
	private Formulas formulas;
	@Autowired
	private UrlMicroservico urlMicroservico;
	@Autowired
	private EnderecoGisRotina enderecoGisRotina;

	@Override
	public EnderecoGisDTOWrapper buscarEnderecosAlterado(Pageable pageable) {
		List<EnderecoGisDTO> listEnderecoGisSemLocalidade = new ArrayList<>();
		List<EnderecoGisDTO> listEnderecoGisDTO = enderecoGisMapper
				.toDto(enderecoGisRepository.findBySituacao((short) 0));
		listEnderecoGisDTO = listEnderecoGisDTO.parallelStream()
				.peek(e -> e.setDv(formulas.calculaDV(e.getMatriculaImovel()))).collect(Collectors.toList());
		List<ImovelDTO> listImovelDTO = this.buscarImoveis(
				listEnderecoGisDTO.stream().map(e -> e.getMatriculaImovel()).distinct().collect(Collectors.toList()));

		listEnderecoGisDTO = listEnderecoGisDTO.stream()
				.peek(e -> e.setCdLocalidade(this.buscarCodigoLocalidade(listImovelDTO, e.getMatriculaImovel())))
				.collect(Collectors.toList());

		listEnderecoGisSemLocalidade = listEnderecoGisDTO.stream().filter(e -> e.getCdLocalidade().byteValue() == 0)
				.peek(e -> e.setLocalidade("")).collect(Collectors.toList());

		listEnderecoGisDTO.removeIf(e -> e.getCdLocalidade().byteValue() == 0);
		
		listEnderecoGisDTO = listEnderecoGisDTO.stream().peek( e -> {
			Optional<Bairro> optionalBairroAntigo = bairroRepository.findById(new IdBairroIdLocalidade(e.getCdBairroAntigo(), e.getCdLocalidade()));
			if(optionalBairroAntigo.isPresent()) e.setDescricaoBairroAntigo(optionalBairroAntigo.get().getNomeBairro());
			})
		.peek(e -> { 
			Optional<Bairro> optionalBairroNovo = bairroRepository.findById(new IdBairroIdLocalidade(e.getCdBairroNovo(), e.getCdLocalidade()));
			if(optionalBairroNovo.isPresent()) e.setDescricaoBairroNovo(optionalBairroNovo.get().getNomeBairro());
			})
		.peek(e -> { 
			Optional<Logradouro> optionalLogradouroAntigo = logradouroRepository.findById(new IdLogradouroIdLocalidade(e.getCdLogradouroAntigo(), e.getCdLocalidade()));
			if(optionalLogradouroAntigo.isPresent()) e.setDescricaoLogradouroAntigo(optionalLogradouroAntigo.get().getDescricao());
			})
		.peek(e -> { 
			Optional<Logradouro> optionalLogradouroNovo = logradouroRepository.findById(new IdLogradouroIdLocalidade(e.getCdLogradouroNovo(), e.getCdLocalidade()));
			if(optionalLogradouroNovo.isPresent()) e.setDescricaoLogradouroNovo(optionalLogradouroNovo.get().getDescricao());
			})
		.peek(e -> { 
			Optional<MunicipioLocalidade> optionalMunicipioLocalidade = municipioLocalidadeRepository.findById(e.getCdLocalidade());
			if(optionalMunicipioLocalidade.isPresent()) e.setLocalidade(optionalMunicipioLocalidade.get().getDcCidade());
			})
		.collect(Collectors.toList());
		
		listEnderecoGisDTO.addAll(listEnderecoGisSemLocalidade);
		return this.ordenarCampos(this.converterInteiro(listEnderecoGisDTO), pageable);
	}


	@Override
	public List<CriticaEnderecoDTO> validarAtualizacao(List<Integer> ids) {
		String critica = "";
		List<CriticaEnderecoDTO> listCriticaEnderecoDTO = new ArrayList<CriticaEnderecoDTO>();
		List<EnderecoGisDTO> listEnderecoGisDTO = enderecoGisMapper.toDto(enderecoGisRepository.findByIdIn(ids));
		List<ImovelDTO> listImovelDTO = this.buscarImoveis(
				listEnderecoGisDTO.stream().map(e -> e.getMatriculaImovel()).distinct().collect(Collectors.toList()));
		listEnderecoGisDTO = listEnderecoGisDTO.stream()
				.peek(e -> e.setCdLocalidade(this.buscarCodigoLocalidade(listImovelDTO, e.getMatriculaImovel())))
				.collect(Collectors.toList());
		List<CronogramaFaturaDTO> listCronogramaFaturaDTO = this.buscarCronogramaFatura(listEnderecoGisDTO.stream()
				.map(e -> e.getCdLocalidade()).filter(e -> e.byteValue() != 0).distinct().collect(Collectors.toList()));
		for (EnderecoGisDTO enderecoGisDTO : listEnderecoGisDTO) {
			CriticaEnderecoDTO criticaEnderecoDTO = new CriticaEnderecoDTO();
			criticaEnderecoDTO.setId(enderecoGisDTO.getId());
			criticaEnderecoDTO.setDv(formulas.calculaDV(enderecoGisDTO.getMatriculaImovel()));
			criticaEnderecoDTO.setMatricula(enderecoGisDTO.getMatriculaImovel());
			critica = this.validarLogradouro(enderecoGisDTO);
			critica = critica + this.validarBairro(enderecoGisDTO);
			critica = critica + this.validarMatricula(listEnderecoGisDTO, enderecoGisDTO.getMatriculaImovel());
			critica = critica + this.validarAlteracao(enderecoGisDTO);
			if (enderecoGisDTO.getCdLocalidade() != 0)
				critica = critica + this.validarReferencia(listImovelDTO, listCronogramaFaturaDTO,
						enderecoGisDTO.getMatriculaImovel());
			String criticaLocalidade = this.validarLocalidade(enderecoGisDTO);
			critica = criticaLocalidade.isEmpty() ? critica : criticaLocalidade;
			if (!critica.isEmpty())
				criticaEnderecoDTO.setCritica(critica);
			listCriticaEnderecoDTO.add(criticaEnderecoDTO);

		}

		return listCriticaEnderecoDTO;
	}

	@Override
	public List<EnderecoGisDTO> buscarEnderecosAlterado(List<Integer> ids) {
		List<EnderecoGisDTO> listEnderecoGisSemLocalidade = new ArrayList<>();
		List<EnderecoGisDTO> listEnderecoGisDTO = enderecoGisMapper.toDto(enderecoGisRepository.findByIdIn(ids));
		listEnderecoGisDTO = listEnderecoGisDTO.parallelStream()
				.peek(e -> e.setDv(formulas.calculaDV(e.getMatriculaImovel()))).collect(Collectors.toList());
		List<ImovelDTO> listImovelDTO = this.buscarImoveis(
				listEnderecoGisDTO.stream().map(e -> e.getMatriculaImovel()).distinct().collect(Collectors.toList()));

		listEnderecoGisDTO = listEnderecoGisDTO.stream()
				.peek(e -> e.setCdLocalidade(this.buscarCodigoLocalidade(listImovelDTO, e.getMatriculaImovel())))
				.collect(Collectors.toList());

		listEnderecoGisSemLocalidade = listEnderecoGisDTO.stream().filter(e -> e.getCdLocalidade().byteValue() == 0)
				.peek(e -> e.setLocalidade("")).collect(Collectors.toList());

		listEnderecoGisDTO.removeIf(e -> e.getCdLocalidade().byteValue() == 0);

		listEnderecoGisDTO = listEnderecoGisDTO.stream().peek(e -> e.setDescricaoBairroAntigo(bairroRepository
				.findById(new IdBairroIdLocalidade(e.getCdBairroAntigo(), e.getCdLocalidade())).get().getNomeBairro()))
				.peek(e -> e.setDescricaoBairroNovo(
						bairroRepository.findById(new IdBairroIdLocalidade(e.getCdBairroNovo(), e.getCdLocalidade()))
								.get().getNomeBairro()))
				.peek(e -> e.setDescricaoLogradouroAntigo((logradouroRepository
						.findById(new IdLogradouroIdLocalidade(e.getCdLogradouroAntigo(), e.getCdLocalidade())).get()
						.getDescricao())))
				.peek(e -> e.setDescricaoLogradouroNovo((logradouroRepository
						.findById(new IdLogradouroIdLocalidade(e.getCdLogradouroNovo(), e.getCdLocalidade())).get()
						.getDescricao())))
				.peek(e -> e
						.setLocalidade(municipioLocalidadeRepository.findById(e.getCdLocalidade()).get().getDcCidade()))
				.collect(Collectors.toList());

		listEnderecoGisDTO.addAll(listEnderecoGisSemLocalidade);
		return this.converterInteiro(listEnderecoGisDTO);
	}

	@Override
	public void atualizarEndereco(EnderecoGisIdsDTO enderecoGisIdsDTO, String token) {
		enderecoGisRotina.setIds(enderecoGisIdsDTO.getIds());
		enderecoGisRotina.setToken(token);
		new Thread(enderecoGisRotina).start();

	}

	@Override
	public List<EnderecoGisDTO> filtrar(EnderecoGisFilter enderecoGisFilter) {
		List<EnderecoGisDTO> listEnderecoGisSemLocalidade = new ArrayList<>();
		if (enderecoGisFilter.getDataInicio() != null && enderecoGisFilter.getDataFim() != null) {

			enderecoGisFilter.setDataInicioI(this.converterDataReportServer(enderecoGisFilter.getDataInicio()));

			enderecoGisFilter.setDataFimI(this.converterDataReportServer(enderecoGisFilter.getDataFim()));

			List<EnderecoGisDTO> listEnderecoGisResDTO = enderecoGisMapper
					.toDto(enderecoGisRepository.filtrar(enderecoGisFilter));

			listEnderecoGisResDTO = listEnderecoGisResDTO.parallelStream()
					.peek(e -> e.setDv(formulas.calculaDV(e.getMatriculaImovel()))).collect(Collectors.toList());

			List<ImovelDTO> listImovelDTO = this.buscarImoveis(listEnderecoGisResDTO.stream()
					.map(e -> e.getMatriculaImovel()).distinct().collect(Collectors.toList()));

			listEnderecoGisResDTO = listEnderecoGisResDTO.stream()
					.peek(e -> e.setCdLocalidade(this.buscarCodigoLocalidade(listImovelDTO, e.getMatriculaImovel())))
					.collect(Collectors.toList());

			listEnderecoGisSemLocalidade = listEnderecoGisResDTO.stream()
					.filter(e -> e.getCdLocalidade().byteValue() == 0).peek(e -> e.setLocalidade(""))
					.collect(Collectors.toList());

			listEnderecoGisResDTO.removeIf(e -> e.getCdLocalidade().byteValue() == 0);
			listEnderecoGisResDTO = listEnderecoGisResDTO.stream()
					.peek(e -> e.setDescricaoBairroAntigo(bairroRepository
							.findById(new IdBairroIdLocalidade(e.getCdBairroAntigo(), e.getCdLocalidade())).get()
							.getNomeBairro()))
					.peek(e -> {
						e.setDescricaoBairroNovo(this.validarConsultaBairro(bairroRepository
								.findById(new IdBairroIdLocalidade(e.getCdBairroNovo(), e.getCdLocalidade()))));
					})
					.peek(e -> e.setDescricaoLogradouroAntigo((logradouroRepository
							.findById(new IdLogradouroIdLocalidade(e.getCdLogradouroAntigo(), e.getCdLocalidade()))
							.get().getDescricao())))
					.peek(e -> {
						e.setDescricaoLogradouroNovo(this.validarConsultaLogradouro(logradouroRepository
								.findById(new IdLogradouroIdLocalidade(e.getCdLogradouroNovo(), e.getCdLocalidade()))));
					})
					.peek(e -> e.setLocalidade(
							municipioLocalidadeRepository.findById(e.getCdLocalidade()).get().getDcCidade()))
					.collect(Collectors.toList());

			if (enderecoGisFilter.getLocalidade() != null) {

				List<MunicipioLocalidade> listLocalidade = municipioLocalidadeRepository
						.findByDcCidadeIn(enderecoGisFilter.getLocalidade());

				if (!listLocalidade.isEmpty()) {
					listEnderecoGisResDTO = this.filtrarLocalidade(
							listLocalidade.stream().map(e -> e.getDcCidade()).collect(Collectors.toList()),
							listEnderecoGisResDTO);

				}
			} else
				listEnderecoGisResDTO.addAll(listEnderecoGisSemLocalidade);

			return this.converterInteiro(listEnderecoGisResDTO);
		} else {

			throw new MsgGenericaPersonalizadaException("Data Inicio e Data Fim são obrigatórias");

		}

	}
	
	
	
	private List<EnderecoGisDTO> filtrarLocalidade(List<String> listLocalidade,
			List<EnderecoGisDTO> listEnderecoGisDTO) {

		List<EnderecoGisDTO> listEnderecoGisNova = new ArrayList<>();

		for (EnderecoGisDTO enderecoGis : listEnderecoGisDTO) {

			for (String loc : listLocalidade) {
				if (enderecoGis.getLocalidade().equals(loc)) {
					listEnderecoGisNova.add(enderecoGis);
					break;

				}

			}

		}

		return listEnderecoGisNova;
	}

	private String validarConsultaBairro(Optional<Bairro> verificaBairro) {
		if (verificaBairro.isPresent())
			return verificaBairro.get().getNomeBairro();
		else
			return "";
	}

	private String validarConsultaLogradouro(Optional<Logradouro> verificaLogradouro) {
		if (verificaLogradouro.isPresent())
			return verificaLogradouro.get().getNomeLogradouro();
		else
			return "";
	}

	private List<ImovelDTO> buscarImoveis(List<Integer> matriculas) {
		List<ImovelDTO> listImovel = new ArrayList<>();
		int totalMatricula = matriculas.size();
		int registroPorRequest = 0;

		while (registroPorRequest < totalMatricula) {
			List<Integer> matriculasPorRequest = matriculas.stream().skip(registroPorRequest).limit(300)
					.collect(Collectors.toList());
			HttpHeaders headers = new HttpHeaders();
			headers.set("Accept", "application/json");
			RestTemplate restTemplate = new RestTemplate();
			String url = urlMicroservico.getUrlImovel() + "/backend-imovel/imovel/matricula/";
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url).queryParam("matriculas",
					matriculasPorRequest);
			HttpEntity<?> request = new HttpEntity<>(headers);
			HttpEntity<List<ImovelDTO>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, request,
					new ParameterizedTypeReference<List<ImovelDTO>>() {
					});

			registroPorRequest = registroPorRequest + 300;
			listImovel.addAll(response.getBody());
		}

		return listImovel;

	}

	private List<CronogramaFaturaDTO> buscarCronogramaFatura(List<Short> listCodFatura) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", "application/json");
		RestTemplate restTemplate = new RestTemplate();
		String url = urlMicroservico.getUrlCronogramaFatura() + "/backend-cronograma/cronograma/localidade";
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url).queryParam("listCodLocalidade",
				listCodFatura);
		HttpEntity<?> request = new HttpEntity<>(headers);
		HttpEntity<List<CronogramaFaturaDTO>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET,
				request, new ParameterizedTypeReference<List<CronogramaFaturaDTO>>() {
				});
		return response.getBody();

	}

	private Short buscarCodigoLocalidade(List<ImovelDTO> listImovelDTO, Integer matricula) {
		Optional<Short> codCidade = listImovelDTO.stream().filter(e -> e.getMatriculaImovel().equals(matricula))
				.map(e -> e.getCodCidade()).findFirst();

		if (codCidade.isEmpty())
			return (short) 0;
		else
			return codCidade.get();
	}

	private List<EnderecoGisDTO> converterInteiro(List<EnderecoGisDTO> listEnderecoGisDTO) {

		List<EnderecoGisDTO> listEnderecoGisDTONova = new ArrayList<>();
		for (EnderecoGisDTO enderecoGisDTO : listEnderecoGisDTO) {
			if (enderecoGisDTO.getDataExecucao() != null && enderecoGisDTO.getDataExecucao().intValue()!=0) {
				enderecoGisDTO.setDataExecucaoD(
						LocalDate.of(Integer.parseInt(enderecoGisDTO.getDataExecucao().toString().substring(0, 4)),
								Integer.parseInt(enderecoGisDTO.getDataExecucao().toString().substring(4, 6)),
								Integer.parseInt(enderecoGisDTO.getDataExecucao().toString().substring(6, 8))));
			}

			if (enderecoGisDTO.getDtInclusao() != null) {
				enderecoGisDTO.setDataInclusao(
						LocalDate.of(Integer.parseInt(enderecoGisDTO.getDtInclusao().toString().substring(0, 4)),
								Integer.parseInt(enderecoGisDTO.getDtInclusao().toString().substring(4, 6)),
								Integer.parseInt(enderecoGisDTO.getDtInclusao().toString().substring(6, 8))));

				listEnderecoGisDTONova.add(enderecoGisDTO);

			} else {
				listEnderecoGisDTONova.add(enderecoGisDTO);
			}

		}

		return listEnderecoGisDTONova;

	}

	private int converterDataReportServer(LocalDate localDate) {

		return Integer.parseInt(localDate.toString().replaceAll("-", ""));

	}

	private EnderecoGisDTOWrapper ordenarCampos(List<EnderecoGisDTO> listEnderecoGisDTO, Pageable pageable) {
		EnderecoGisDTOWrapper enderecoGisDTOWrapper = new EnderecoGisDTOWrapper();
		String ordenarCampo = "";
		if (pageable.getSort().iterator().hasNext())
			ordenarCampo = pageable.getSort().iterator().next().getProperty();
		if ((ordenarCampo.equals("cdBairroAntigo"))) {
			listEnderecoGisDTO.sort((a, b) -> a.getCdBairroAntigo().compareTo(b.getCdBairroAntigo()));
			enderecoGisDTOWrapper = this.ordenarCampo(listEnderecoGisDTO, pageable);
		} else if (ordenarCampo.equals("descricaoBairroAntigo")) {
			listEnderecoGisDTO.stream().peek(e -> {
				if (e.getDescricaoBairroAntigo() == null)
					e.setDescricaoBairroAntigo("");
			}).collect(Collectors.toList());
			listEnderecoGisDTO.sort((a, b) -> a.getDescricaoBairroAntigo().compareTo(b.getDescricaoBairroAntigo()));
			listEnderecoGisDTO.stream().peek(e -> {
				if (e.getDescricaoBairroAntigo().isEmpty())
					e.setDescricaoBairroAntigo(null);
			}).collect(Collectors.toList());
			enderecoGisDTOWrapper = this.ordenarCampo(listEnderecoGisDTO, pageable);

		} else if (ordenarCampo.equals("cdLogradouroAntigo")) {
			listEnderecoGisDTO.sort((a, b) -> a.getCdLogradouroAntigo().compareTo(b.getCdLogradouroAntigo()));
			enderecoGisDTOWrapper = this.ordenarCampo(listEnderecoGisDTO, pageable);

		} else if (ordenarCampo.equals("descricaoLogradouroAntigo")) {
			listEnderecoGisDTO.stream().peek(e -> {
				if (e.getDescricaoLogradouroAntigo() == null)
					e.setDescricaoLogradouroAntigo("");
			}).collect(Collectors.toList());
			listEnderecoGisDTO
					.sort((a, b) -> a.getDescricaoLogradouroAntigo().compareTo(b.getDescricaoLogradouroAntigo()));
			listEnderecoGisDTO.stream().peek(e -> {
				if (e.getDescricaoLogradouroAntigo().isEmpty())
					e.setDescricaoLogradouroAntigo(null);
			}).collect(Collectors.toList());
			enderecoGisDTOWrapper = this.ordenarCampo(listEnderecoGisDTO, pageable);

		} else if (ordenarCampo.equals("cdBairroNovo")) {
			listEnderecoGisDTO.sort((a, b) -> a.getCdBairroNovo().compareTo(b.getCdBairroNovo()));
			enderecoGisDTOWrapper = this.ordenarCampo(listEnderecoGisDTO, pageable);

		} else if (ordenarCampo.equals("descricaoBairroNovo")) {
			listEnderecoGisDTO.stream().peek(e -> {
				if (e.getDescricaoBairroNovo() == null)
					e.setDescricaoBairroNovo("");
			}).collect(Collectors.toList());
			listEnderecoGisDTO.sort((a, b) -> a.getDescricaoBairroNovo().compareTo(b.getDescricaoBairroNovo()));
			listEnderecoGisDTO.stream().peek(e -> {
				if (e.getDescricaoBairroNovo().isEmpty())
					e.setDescricaoBairroNovo(null);
			}).collect(Collectors.toList());
			enderecoGisDTOWrapper = this.ordenarCampo(listEnderecoGisDTO, pageable);

		} else if (ordenarCampo.equals("cdLogradouroNovo")) {
			listEnderecoGisDTO.sort((a, b) -> a.getCdLogradouroNovo().compareTo(b.getCdLogradouroNovo()));
			enderecoGisDTOWrapper = this.ordenarCampo(listEnderecoGisDTO, pageable);

		} else if (ordenarCampo.equals("descricaoLogradouroNovo")) {
			listEnderecoGisDTO.stream().peek(e -> {
				if (e.getDescricaoLogradouroNovo() == null)
					e.setDescricaoLogradouroNovo("");
			}).collect(Collectors.toList());
			listEnderecoGisDTO.sort((a, b) -> a.getDescricaoLogradouroNovo().compareTo(b.getDescricaoLogradouroNovo()));
			listEnderecoGisDTO.stream().peek(e -> {
				if (e.getDescricaoLogradouroNovo().isEmpty())
					e.setDescricaoLogradouroNovo(null);
			}).collect(Collectors.toList());
			enderecoGisDTOWrapper = this.ordenarCampo(listEnderecoGisDTO, pageable);

		} else if (ordenarCampo.equals("dataInclusao")) {
			listEnderecoGisDTO.sort((a, b) -> a.getDataInclusao().compareTo(b.getDataInclusao()));
			enderecoGisDTOWrapper = this.ordenarCampo(listEnderecoGisDTO, pageable);

		} else if (ordenarCampo.equals("localidade")) {
			listEnderecoGisDTO.sort((a, b) -> a.getLocalidade().compareTo(b.getLocalidade()));
			enderecoGisDTOWrapper = this.ordenarCampo(listEnderecoGisDTO, pageable);

		} else if (ordenarCampo.equals("sequenciaInclusao")) {
			listEnderecoGisDTO.sort((a, b) -> a.getSequenciaInclusao().compareTo(b.getSequenciaInclusao()));
			enderecoGisDTOWrapper = this.ordenarCampo(listEnderecoGisDTO, pageable);

		} else if (ordenarCampo.equals("dv")) {
			listEnderecoGisDTO.sort((a, b) -> a.getDv().compareTo(b.getDv()));
			enderecoGisDTOWrapper = this.ordenarCampo(listEnderecoGisDTO, pageable);

		} else {

			listEnderecoGisDTO.sort((a, b) -> a.getMatriculaImovel().compareTo(b.getMatriculaImovel()));
			enderecoGisDTOWrapper = this.ordenarCampo(listEnderecoGisDTO, pageable);

		}
		return enderecoGisDTOWrapper;
	}

	private EnderecoGisDTOWrapper ordenarCampo(List<EnderecoGisDTO> listEnderecoGisDTO, Pageable pageable) {
		EnderecoGisDTOWrapper enderecoGisDTOWrapper = new EnderecoGisDTOWrapper();
		enderecoGisDTOWrapper.setTotalRegistros(listEnderecoGisDTO.size());
		int indice = pageable.getPageSize() * pageable.getPageNumber();
		if (!pageable.getSort().iterator().hasNext()) {
			listEnderecoGisDTO = listEnderecoGisDTO.stream().skip(indice).limit(pageable.getPageSize())
					.collect(Collectors.toList());
		}

		else if (!pageable.getSort().iterator().next().isDescending()) {
			listEnderecoGisDTO = listEnderecoGisDTO.stream().skip(indice).limit(pageable.getPageSize())
					.collect(Collectors.toList());
		} else {
			Collections.reverse(listEnderecoGisDTO);
			listEnderecoGisDTO = listEnderecoGisDTO.stream().skip(indice).limit(pageable.getPageSize())
					.collect(Collectors.toList());
		}
		enderecoGisDTOWrapper.setListEnderecoGisDTO(listEnderecoGisDTO);

		return enderecoGisDTOWrapper;
	}

	private String validarLogradouro(EnderecoGisDTO enderecoGisDTO) {
		String critica = "";
		Optional<Logradouro> lograoduroAntigo = logradouroRepository.findById(
				new IdLogradouroIdLocalidade(enderecoGisDTO.getCdLogradouroAntigo(), enderecoGisDTO.getCdLocalidade()));
		Optional<Logradouro> lograoduroNovo = logradouroRepository.findById(
				new IdLogradouroIdLocalidade(enderecoGisDTO.getCdLogradouroNovo(), enderecoGisDTO.getCdLocalidade()));
		if (lograoduroAntigo.isEmpty())
			critica = "Lograodouro antigo não existe.";
		if (lograoduroNovo.isEmpty())
			critica = critica + "Lograodouro novo não existe.";

		return critica;

	}

	private String validarBairro(EnderecoGisDTO enderecoGisDTO) {
		String critica = "";
		Optional<Bairro> bairroAntigo = bairroRepository.findById(
				new IdBairroIdLocalidade(enderecoGisDTO.getCdBairroAntigo(), enderecoGisDTO.getCdLocalidade()));
		Optional<Bairro> bairroNovo = bairroRepository
				.findById(new IdBairroIdLocalidade(enderecoGisDTO.getCdBairroNovo(), enderecoGisDTO.getCdLocalidade()));
		if (bairroAntigo.isEmpty())
			critica = "Bairro antigo não existe.";
		if (bairroNovo.isEmpty())
			critica = critica + "Bairro novo não existe.";

		return critica;

	}

	private String validarMatricula(List<EnderecoGisDTO> listEnderecoGisDTO, Integer matricula) {
		String critica = "";
		if (listEnderecoGisDTO.stream().filter(e -> e.getMatriculaImovel().equals(matricula))
				.count() > 1)
			return critica = "Este imóvel está em mais de um registro.";
		else
			return critica;
	}

	private String validarAlteracao(EnderecoGisDTO enderecoGisDTO) {
		String critica = "";
		if (enderecoGisDTO.getCdBairroAntigo().byteValue() == enderecoGisDTO.getCdBairroNovo().byteValue()
				&& enderecoGisDTO.getCdLogradouroAntigo().byteValue() == enderecoGisDTO.getCdLogradouroNovo()
						.byteValue())
			return critica = "Não houve alteração no endereço.";
		else
			return critica;
	}

	private String validarLocalidade(EnderecoGisDTO enderecoGisDTO) {
		String critica = "";
		if (enderecoGisDTO.getCdLocalidade().byteValue() == 0)
			return critica = "Esta matricula não existe.";
		else
			return critica;
	}

	private String validarReferencia(List<ImovelDTO> listImovelDTO, List<CronogramaFaturaDTO> listCronogramaFaturaDTO,
			Integer matricula) {
		String critica = "";
		ImovelDTO imovelDTO = listImovelDTO.stream()
				.filter(e -> matricula.intValue() == e.getMatriculaImovel().intValue()).findFirst().get();

		if (imovelDTO.getCiclo().byteValue() == 0)
			return critica = "Matricula com ciclo 0";
		long count = listCronogramaFaturaDTO.stream()
				.filter(e -> imovelDTO.getCiclo().intValue() == e.getCiclo().intValue()
						&& imovelDTO.getCodCidade().intValue() == e.getCodLocalidade().intValue())
				.map(e -> e.getFase()).filter(e -> e.intValue() != 0 && e.intValue() != 5).count();

		if (count > 0)
			return critica = "Fase de cronograma diferente de 0 e 5 ";
		else
			return critica;
	}

	

}
