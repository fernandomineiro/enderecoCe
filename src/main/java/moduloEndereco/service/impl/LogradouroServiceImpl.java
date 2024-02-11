package moduloEndereco.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import moduloEndereco.autenticacao.JwtTokenProvider;
import moduloEndereco.excecoes.ExcecaoRegraNegocio;
import moduloEndereco.excecoes.MsgGenericaPersonalizadaException;
import moduloEndereco.model.Bairro;
import moduloEndereco.model.BairroLogradouro;
import moduloEndereco.model.IdBairroIdLocalidade;
import moduloEndereco.model.IdLogradouroIdLocalidade;
import moduloEndereco.model.Logradouro;
import moduloEndereco.model.MunicipioLocalidade;
import moduloEndereco.repository.BairroLogradouroRepository;
import moduloEndereco.repository.BairroRepository;
import moduloEndereco.repository.LogradouroRepository;
import moduloEndereco.repository.MunicipioLocalidadeRepository;
import moduloEndereco.repository.TipoLogradouroRepository;
import moduloEndereco.repository.filter.LogradouroFilter;
import moduloEndereco.service.AuditoriaService;
import moduloEndereco.service.BairroLogradouroService;
import moduloEndereco.service.LogradouroService;
import moduloEndereco.service.dto.BairroDTO;
import moduloEndereco.service.dto.ImovelLogradouroDTO;
import moduloEndereco.service.dto.ItemAtendimentoRetornoDTO;
import moduloEndereco.service.dto.ItemAtendimentoSSMatriculaImovelWrapperDTO;
import moduloEndereco.service.dto.ItemAtendimentoSSRetornoDTO;
import moduloEndereco.service.dto.LocalidadeDTO;
import moduloEndereco.service.dto.LogradouroDTO;
import moduloEndereco.service.dto.LogradouroRetornoDTO;
import moduloEndereco.service.dto.LogradouroWrapperDTO;
import moduloEndereco.service.dto.LogradouroWrapperRetornoDTO;
import moduloEndereco.service.dto.SolicitacaoServicoRetornoDTO;
import moduloEndereco.service.mapper.LogradouroMapper;
import moduloEndereco.util.Constants;
import moduloEndereco.util.UrlMicroservico;

@Service
@Transactional
public class LogradouroServiceImpl implements LogradouroService {

	@Autowired
	private LogradouroRepository logradouroRepository;

	@Autowired
	private BairroRepository bairroRepository;

	@Autowired
	private LogradouroMapper logradouroMapper;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Autowired
	private AuditoriaService auditoriaService;

	@Autowired
	private BairroLogradouroService bairroLogradouroService;

	@Autowired
	private TipoLogradouroRepository tipoLogradouroRepository;

	@Autowired
	private MunicipioLocalidadeRepository municipioLocalidadeRepository;
	@Autowired
	private BairroLogradouroRepository bairroLogradouroRepository;
	@Autowired
	private UrlMicroservico urlMicroservico;

	@Override
	public List<LogradouroDTO> listAll() {

		return logradouroMapper.toDto(logradouroRepository.findAllByOrderByNomeLogradouro());
	}

	@Override
	public LogradouroRetornoDTO buscarPorcodLogradouroCodLocalidade(Short codLogradouro, Short codLocalidade) {
		IdLogradouroIdLocalidade idLogradouroIdLocalidade = new IdLogradouroIdLocalidade(codLogradouro, codLocalidade);
		LogradouroDTO logradouroDTO = logradouroMapper
				.toDto(logradouroRepository.findById(idLogradouroIdLocalidade).get());
		logradouroDTO.setIdsBairro(bairroLogradouroService.listaBairro(idLogradouroIdLocalidade));
		return this.toLogradouroRetorno(logradouroDTO);
	}

	@Override
	public LogradouroWrapperRetornoDTO filtrar(LogradouroFilter logradouroFilter, Pageable pageable) {
		LogradouroWrapperRetornoDTO logradouroWrapperRetornoDTO = new LogradouroWrapperRetornoDTO();
		String ordenarCampo = "";
		if (pageable.getSort().iterator().hasNext())
			ordenarCampo = pageable.getSort().iterator().next().getProperty();
		if (logradouroFilter.getIdsBairro() != null) {
			logradouroWrapperRetornoDTO = this
					.toLogradouroWrapperRetornoDTO(logradouroRepository.filtrar(logradouroFilter));
			logradouroWrapperRetornoDTO.setListLogradouroDTO(this.filtrarBairro(logradouroFilter.getIdsBairro(),
					logradouroWrapperRetornoDTO.getListLogradouroDTO()));
			logradouroWrapperRetornoDTO
					.setTotalRegistros((long) logradouroWrapperRetornoDTO.getListLogradouroDTO().size());
			if (ordenarCampo.equals("nomeLocalidade")) {
				logradouroWrapperRetornoDTO = this.ordenarLocalidade(logradouroWrapperRetornoDTO, pageable);
			} else if (ordenarCampo.equals("siglaLogradouro")) {
				logradouroWrapperRetornoDTO = this.ordenarSigla(logradouroWrapperRetornoDTO, pageable);
			} else if (ordenarCampo.equals("IdLogradouroIdLocalidade.codLogradouro")) {
				logradouroWrapperRetornoDTO = this.ordenarCodigoLogradouro(logradouroWrapperRetornoDTO, pageable);
			} else {
				logradouroWrapperRetornoDTO = this.ordenarLogradouro(logradouroWrapperRetornoDTO, pageable);
			}
			logradouroWrapperRetornoDTO
					.setListLogradouroDTO(this.carregarBairro(logradouroWrapperRetornoDTO.getListLogradouroDTO()));
			return logradouroWrapperRetornoDTO;
		}
		if (ordenarCampo.equals("nomeLocalidade")) {
			logradouroWrapperRetornoDTO = this
					.toLogradouroWrapperRetornoDTO(logradouroRepository.filtrar(logradouroFilter));
			logradouroWrapperRetornoDTO = this.ordenarLocalidade(logradouroWrapperRetornoDTO, pageable);
			logradouroWrapperRetornoDTO
					.setListLogradouroDTO(this.carregarBairro(logradouroWrapperRetornoDTO.getListLogradouroDTO()));
			return logradouroWrapperRetornoDTO;
		} else {
			logradouroWrapperRetornoDTO = this
					.toLogradouroWrapperRetornoDTO(logradouroRepository.filtrar(logradouroFilter, pageable));
			logradouroWrapperRetornoDTO
					.setListLogradouroDTO(this.carregarBairro(logradouroWrapperRetornoDTO.getListLogradouroDTO()));
			return logradouroWrapperRetornoDTO;
		}
	}

	@Override
	public void salvar(LogradouroDTO logradouroDTO, String token) {
		if (logradouroDTO.getCodLogradouro().toString().length() > 4)
			throw new MsgGenericaPersonalizadaException("Código inválido.");
		this.validarInclusao(logradouroDTO.getCodLogradouro(), logradouroDTO.getCodLocalidade());
		logradouroDTO.setNomeLogradouro(logradouroDTO.getNomeLogradouro().toUpperCase());
		Integer id = 0;
		Logradouro idLogradouro = logradouroRepository.findFirstByOrderByIdDesc();
		if (idLogradouro == null)
			id = 1;
		else {
			id = idLogradouro.getId();
			++id;
		}

		logradouroDTO.setId(id);

		Logradouro logradouro = logradouroMapper.toEntity(logradouroDTO);

		logradouro.setMaint('A');
		logradouro.setCodAtendimento(0);
		logradouro.setDiametroRedeAgua((short) 0);
		logradouro.setDiametroRedeEsgoto((short) 0);
		logradouro.setLocalRedeAgua((short) 0);
		logradouro.setLocalRedeEsgoto((short) 0);
		logradouro.setMaterialRedeAgua((short) 0);
		logradouro.setMaterialRedeEsgoto((short) 0);
		logradouro.setRefAtendimenoSS(0);
		logradouro.setSequencialSS((short) 0);
		logradouro.setSiglaLogradouro(
				tipoLogradouroRepository.findById(logradouroDTO.getIdTipoLogradouro()).get().getSigla());
		logradouro.setTipoPavimento((short) 0);

		logradouroRepository.save(logradouro);
		bairroLogradouroService.salvarBairroLogradouro(logradouroDTO.getIdsBairro(),
				logradouro.getIdLogradouroIdLocalidade(), token);
		String logradouroJson = logradouroMapper.toDto(logradouro).toJson();

		auditoriaService.gerarAuditoria(logradouro.getIdLogradouroIdLocalidade().getCodLogradouro().longValue(),
				Constants.EMPTY_STRING, logradouroJson, 9L, "Logradouro", jwtTokenProvider.buscarIdUsuario(token));

	}

	@Override
	public LogradouroDTO atualizar(LogradouroDTO logradouroDTO, String token) {
		String dadosAntes;
		String dadosDepois;

		logradouroDTO.setNomeLogradouro(logradouroDTO.getNomeLogradouro().toUpperCase());
		IdLogradouroIdLocalidade idLogradouroIdLocalidade = new IdLogradouroIdLocalidade();
		idLogradouroIdLocalidade.setCodLocalidade(logradouroDTO.getCodLocalidade());
		idLogradouroIdLocalidade.setCodLogradouro(logradouroDTO.getCodLogradouro());

		if (!this.validarDesassociacaoBairro(logradouroDTO)) {

			throw new ExcecaoRegraNegocio(
					"O logradouro possui imóveis vinculados, não é possível desvincular o bairro.");

		}
		Logradouro logradouro = logradouroRepository
				.findByIdLogradouroIdLocalidadeAndDataHoraExclusao(idLogradouroIdLocalidade, null);
		if (logradouro == null) {
			throw new MsgGenericaPersonalizadaException("Para esta localidade este logradouro não existe");
		} else {

			dadosAntes = logradouroMapper.toDto(logradouro).toJson();
			logradouro = logradouroMapper.toEntity(logradouroDTO);

			logradouro.setMaint('A');
			logradouro.setCodAtendimento(0);
			logradouro.setDiametroRedeAgua((short) 0);
			logradouro.setDiametroRedeEsgoto((short) 0);
			logradouro.setLocalRedeAgua((short) 0);
			logradouro.setLocalRedeEsgoto((short) 0);
			logradouro.setMaterialRedeAgua((short) 0);
			logradouro.setMaterialRedeEsgoto((short) 0);
			logradouro.setRefAtendimenoSS(0);
			logradouro.setSequencialSS((short) 0);
			logradouro.setTipoLogradouro(tipoLogradouroRepository.findBySigla(logradouroDTO.getSiglaLogradouro()));
			logradouro.setTipoPavimento((short) 0);
			dadosDepois = logradouroMapper.toDto(logradouroRepository.save(logradouro)).toJson();
			bairroLogradouroService.atualizarBairroLogradouro(logradouroDTO.getIdsBairro(), idLogradouroIdLocalidade,
					token);
			auditoriaService.gerarAuditoria(logradouro.getIdLogradouroIdLocalidade().getCodLogradouro().longValue(),
					dadosAntes, dadosDepois, 9L, "Logradouro", jwtTokenProvider.buscarIdUsuario(token));

		}

		return logradouroMapper.toDto(logradouro);
	}

	@Override
	public void excluir(Short codLogradouro, Short codLocalidade, String token) {
		IdLogradouroIdLocalidade idLogradouroIdLocalidade = new IdLogradouroIdLocalidade();
		idLogradouroIdLocalidade.setCodLocalidade(codLocalidade);
		idLogradouroIdLocalidade.setCodLogradouro(codLogradouro);

		if (!this.validarLogrouroImovel(codLogradouro, codLocalidade)
				|| !this.validarItemAtendimento(codLogradouro, codLocalidade)
				|| !this.validarSolicitacaoServico(codLogradouro, codLocalidade)) {

			throw new ExcecaoRegraNegocio("Esse logradouro não pode ser excluído");

		}

		Logradouro logradouro = logradouroRepository
				.findByIdLogradouroIdLocalidadeAndDataHoraExclusao(idLogradouroIdLocalidade, null);
		if (logradouro == null) {
			throw new MsgGenericaPersonalizadaException("Exclusão inválida");
		} else {

			bairroLogradouroService.excluirBairroLogradouro(idLogradouroIdLocalidade, token);
			logradouro.setIdLogradouroIdLocalidade(idLogradouroIdLocalidade);
			logradouro.setDataHoraExclusao(new Date());

			logradouroRepository.save(logradouro);
			String logradouroJson = logradouroMapper.toDto(logradouro).toJson();
			auditoriaService.gerarAuditoria(logradouro.getIdLogradouroIdLocalidade().getCodLogradouro().longValue(),
					logradouroJson, Constants.EMPTY_STRING, 9L, "Logradouro", jwtTokenProvider.buscarIdUsuario(token));
		}

	}

	@Override
	public ItemAtendimentoSSMatriculaImovelWrapperDTO listarItemAtendimentoSSMatriculaImovel(Short codLogradouro,
			Short codLocalidade,List<Short> idsBairro,boolean flagExclusao) {
		ItemAtendimentoSSMatriculaImovelWrapperDTO itemAtendimentoSSMatriculaImovel = new ItemAtendimentoSSMatriculaImovelWrapperDTO();

		List<ItemAtendimentoSSRetornoDTO> listItemAtendimentoSS = this.listarItemAtendimentoSS(codLogradouro,
				codLocalidade,idsBairro,flagExclusao);

		itemAtendimentoSSMatriculaImovel.setListItemAtendimentoRetornoDTO(listItemAtendimentoSS);

		List<ImovelLogradouroDTO> listMatriculaImovel = this.buscarMatriculaImovel(codLogradouro, codLocalidade);
		

		if(!flagExclusao) {
			for(Short idBairro : idsBairro) {
				listMatriculaImovel.removeIf(e->e.getCodBairro().shortValue()==idBairro.shortValue());
				listMatriculaImovel.removeIf(e->e.getCodBairro().shortValue()==idBairro.shortValue());
			}
		}

		itemAtendimentoSSMatriculaImovel.setListImovelLogradouroDTO(listMatriculaImovel);

		return itemAtendimentoSSMatriculaImovel;

	}

	@Override
	public List<LogradouroDTO> buscarLogradouroPorLocalidadeBairro(Short cdBairro, Short cdLocalidade) {
		List<Short> listCodLogradouro = bairroLogradouroRepository
				.findByCodBairroAndCodLocalidade(cdBairro, cdLocalidade).stream().map(e -> e.getCodLogradouro())
				.collect(Collectors.toList());
		return logradouroMapper.toDto(logradouroRepository
				.findByIdLogradouroIdLocalidadeInAndDataHoraExclusaoIsNull(listCodLogradouro.stream()
						.map(e -> new IdLogradouroIdLocalidade(e, cdLocalidade)).collect(Collectors.toList())));

	}

	private void validarInclusao(Short codLogradouro, Short codLocalidade) {
		IdLogradouroIdLocalidade idLogradouroIdLocalidade = new IdLogradouroIdLocalidade();
		idLogradouroIdLocalidade.setCodLocalidade(codLocalidade);
		idLogradouroIdLocalidade.setCodLogradouro(codLogradouro);

		Logradouro logradouro = logradouroRepository
				.findByIdLogradouroIdLocalidadeAndDataHoraExclusao(idLogradouroIdLocalidade, null);
		if (logradouro != null) {
			throw new MsgGenericaPersonalizadaException("Este logradouro já está associado a esta localidade");
		}

	}

	private LogradouroWrapperRetornoDTO toLogradouroWrapperRetornoDTO(LogradouroWrapperDTO logradouroWrapperDTO) {
		LogradouroWrapperRetornoDTO logradouroWrapperRetornoDTO = new LogradouroWrapperRetornoDTO();
		List<LogradouroRetornoDTO> listLogradouroRetornoDTO = new ArrayList<>();
		logradouroWrapperRetornoDTO.setTotalRegistros(logradouroWrapperDTO.getTotalRegistros());
		List<LogradouroDTO> listLogradouro = logradouroWrapperDTO.getListLogradouroDTO();
		for (LogradouroDTO logradouro : listLogradouro) {

			LogradouroRetornoDTO logradouroRetornoDTO = new LogradouroRetornoDTO();
			logradouroRetornoDTO.setCodLocalidade(logradouro.getCodLocalidade());
			logradouroRetornoDTO.setCodLogradouro(logradouro.getCodLogradouro());
			logradouroRetornoDTO.setId(logradouro.getId());
			logradouroRetornoDTO.setIdTipoLogradouro(logradouro.getIdTipoLogradouro());
			logradouroRetornoDTO.setNomeLogradouro(logradouro.getNomeLogradouro());
			logradouroRetornoDTO.setSiglaLogradouro(logradouro.getSiglaLogradouro());
			logradouroRetornoDTO.setLocalidade(this.carregarLocalidade(logradouro.getCodLocalidade()));
			listLogradouroRetornoDTO.add(logradouroRetornoDTO);
		}

		logradouroWrapperRetornoDTO.setListLogradouroDTO(listLogradouroRetornoDTO);
		return logradouroWrapperRetornoDTO;
	}

	private LogradouroWrapperRetornoDTO ordenarLocalidade(LogradouroWrapperRetornoDTO logradouroWrapperRetornoDTO,
			Pageable pageable) {
		int indice = pageable.getPageSize() * pageable.getPageNumber();
		List<LogradouroRetornoDTO> listLogradouroRetornoDTO = logradouroWrapperRetornoDTO.getListLogradouroDTO();
		listLogradouroRetornoDTO
				.sort((a, b) -> a.getLocalidade().getNomeLocalidade().compareTo(b.getLocalidade().getNomeLocalidade()));
		if (!pageable.getSort().iterator().next().isDescending())
			listLogradouroRetornoDTO = listLogradouroRetornoDTO.stream().skip(indice).limit(pageable.getPageSize())
					.collect(Collectors.toList());
		else {
			Collections.reverse(listLogradouroRetornoDTO);
			listLogradouroRetornoDTO = listLogradouroRetornoDTO.stream().skip(indice).limit(pageable.getPageSize())
					.collect(Collectors.toList());
		}
		logradouroWrapperRetornoDTO.setListLogradouroDTO(listLogradouroRetornoDTO);

		return logradouroWrapperRetornoDTO;
	}

	private LogradouroWrapperRetornoDTO ordenarCodigoLogradouro(LogradouroWrapperRetornoDTO logradouroWrapperRetornoDTO,
			Pageable pageable) {
		int indice = pageable.getPageSize() * pageable.getPageNumber();
		List<LogradouroRetornoDTO> listLogradouroRetornoDTO = logradouroWrapperRetornoDTO.getListLogradouroDTO();
		listLogradouroRetornoDTO.sort((a, b) -> a.getCodLogradouro().compareTo(b.getCodLogradouro()));
		if (!pageable.getSort().iterator().next().isDescending())
			listLogradouroRetornoDTO = listLogradouroRetornoDTO.stream().skip(indice).limit(pageable.getPageSize())
					.collect(Collectors.toList());
		else {
			Collections.reverse(listLogradouroRetornoDTO);
			listLogradouroRetornoDTO = listLogradouroRetornoDTO.stream().skip(indice).limit(pageable.getPageSize())
					.collect(Collectors.toList());
		}
		logradouroWrapperRetornoDTO.setListLogradouroDTO(listLogradouroRetornoDTO);

		return logradouroWrapperRetornoDTO;
	}

	private LogradouroWrapperRetornoDTO ordenarLogradouro(LogradouroWrapperRetornoDTO logradouroWrapperRetornoDTO,
			Pageable pageable) {
		int indice = pageable.getPageSize() * pageable.getPageNumber();
		List<LogradouroRetornoDTO> listLogradouroRetornoDTO = logradouroWrapperRetornoDTO.getListLogradouroDTO();
		listLogradouroRetornoDTO.sort((a, b) -> a.getNomeLogradouro().compareTo(b.getNomeLogradouro()));
		if (!pageable.getSort().iterator().next().isDescending())
			listLogradouroRetornoDTO = listLogradouroRetornoDTO.stream().skip(indice).limit(pageable.getPageSize())
					.collect(Collectors.toList());
		else {
			Collections.reverse(listLogradouroRetornoDTO);
			listLogradouroRetornoDTO = listLogradouroRetornoDTO.stream().skip(indice).limit(pageable.getPageSize())
					.collect(Collectors.toList());
		}
		logradouroWrapperRetornoDTO.setListLogradouroDTO(listLogradouroRetornoDTO);

		return logradouroWrapperRetornoDTO;
	}

	private LogradouroWrapperRetornoDTO ordenarSigla(LogradouroWrapperRetornoDTO logradouroWrapperRetornoDTO,
			Pageable pageable) {
		int indice = pageable.getPageSize() * pageable.getPageNumber();
		List<LogradouroRetornoDTO> listLogradouroRetornoDTO = logradouroWrapperRetornoDTO.getListLogradouroDTO();
		listLogradouroRetornoDTO.sort((a, b) -> a.getSiglaLogradouro().compareTo(b.getSiglaLogradouro()));
		if (!pageable.getSort().iterator().next().isDescending())
			listLogradouroRetornoDTO = listLogradouroRetornoDTO.stream().skip(indice).limit(pageable.getPageSize())
					.collect(Collectors.toList());
		else {
			Collections.reverse(listLogradouroRetornoDTO);
			listLogradouroRetornoDTO = listLogradouroRetornoDTO.stream().skip(indice).limit(pageable.getPageSize())
					.collect(Collectors.toList());
		}
		logradouroWrapperRetornoDTO.setListLogradouroDTO(listLogradouroRetornoDTO);

		return logradouroWrapperRetornoDTO;
	}

	private LocalidadeDTO carregarLocalidade(short codigoLocalidade) {
		MunicipioLocalidade municipioLocalidade = municipioLocalidadeRepository.findById(codigoLocalidade).get();
		LocalidadeDTO localidadeDTO = new LocalidadeDTO();
		localidadeDTO.setCodigoLocalidade(codigoLocalidade);
		localidadeDTO.setNomeLocalidade(municipioLocalidade.getDcCidade());
		return localidadeDTO;
	}

	private List<LogradouroRetornoDTO> filtrarBairro(List<Short> idsBairro,
			List<LogradouroRetornoDTO> listLogradouroRetornoAntigaDTO) {
		List<LogradouroRetornoDTO> listLogradouroRetornoNovaDTO = new ArrayList<>();
		List<BairroLogradouro> listBairroLogradouro = bairroLogradouroRepository.findByCodBairroIn(idsBairro);
		for (BairroLogradouro bairroLogradouro : listBairroLogradouro) {
			for (LogradouroRetornoDTO logradouroRetornoDTO : listLogradouroRetornoAntigaDTO) {
				if (logradouroRetornoDTO.getCodLogradouro().shortValue() == bairroLogradouro.getCodLogradouro()
						.shortValue()
						&& (logradouroRetornoDTO.getCodLocalidade()
								.shortValue() == (bairroLogradouro.getCodLocalidade().shortValue()))) {
					listLogradouroRetornoNovaDTO.add(logradouroRetornoDTO);
					break;
				}
			}
		}

		return listLogradouroRetornoNovaDTO;
	}

	private List<LogradouroRetornoDTO> carregarBairro(List<LogradouroRetornoDTO> listLogradouroRetornoDTO) {
		List<LogradouroRetornoDTO> listLogradouroRetornoDTONova = new ArrayList<>();
		for (LogradouroRetornoDTO logradouroRetornoDTO : listLogradouroRetornoDTO) {
			List<Short> listCodigoBairro = bairroLogradouroRepository
					.findByCodLocalidadeAndCodLogradouro(logradouroRetornoDTO.getCodLocalidade(),
							logradouroRetornoDTO.getCodLogradouro())
					.stream().map(e -> e.getCodBairro()).collect(Collectors.toList());
			List<Bairro> listBairro = bairroRepository.findByIdBairroIdLocalidadeIn(
					this.carregarBairroLocalidade(logradouroRetornoDTO.getCodLocalidade(), listCodigoBairro));
			List<BairroDTO> listBairroDTO = listBairro.stream()
					.map(e -> new BairroDTO(e.getIdBairroIdLocalidade().getCdBairro(),
							e.getIdBairroIdLocalidade().getCdLocalidade(), e.getNomeBairro(), e.getId()))
					.collect(Collectors.toList());
			listBairroDTO.sort((a, b) -> a.getNomeBairro().compareTo(b.getNomeBairro()));
			logradouroRetornoDTO.setListBairroDTO(listBairroDTO);
			listLogradouroRetornoDTONova.add(logradouroRetornoDTO);
		}
		return listLogradouroRetornoDTONova;
	}

	private List<IdBairroIdLocalidade> carregarBairroLocalidade(Short cdLocalidade, List<Short> listCdBairro) {
		List<IdBairroIdLocalidade> listBairroLocalidade = new ArrayList<>();
		for (Short cdbairro : listCdBairro) {
			IdBairroIdLocalidade bairroLocalidade = new IdBairroIdLocalidade();
			bairroLocalidade.setCdLocalidade(cdLocalidade);
			bairroLocalidade.setCdBairro(cdbairro);
			listBairroLocalidade.add(bairroLocalidade);
		}

		return listBairroLocalidade;
	}

	private LogradouroRetornoDTO toLogradouroRetorno(LogradouroDTO logradouroDTO) {
		LogradouroRetornoDTO logradouroRetornoDTO = new LogradouroRetornoDTO();
		logradouroRetornoDTO.setCodLocalidade(logradouroDTO.getCodLocalidade());
		logradouroRetornoDTO.setCodLogradouro(logradouroDTO.getCodLogradouro());
		logradouroRetornoDTO.setIdTipoLogradouro(logradouroDTO.getIdTipoLogradouro());
		logradouroRetornoDTO.setSiglaLogradouro(logradouroDTO.getSiglaLogradouro());
		List<Bairro> listBairro = bairroRepository.findByIdBairroIdLocalidadeIn(
				this.carregarBairroLocalidade(logradouroRetornoDTO.getCodLocalidade(), logradouroDTO.getIdsBairro()));
		List<BairroDTO> listBairroDTO = listBairro.stream()
				.map(e -> new BairroDTO(e.getIdBairroIdLocalidade().getCdBairro(),
						e.getIdBairroIdLocalidade().getCdLocalidade(), e.getNomeBairro(), e.getId()))
				.collect(Collectors.toList());
		listBairroDTO.sort((a, b) -> a.getNomeBairro().compareTo(b.getNomeBairro()));
		logradouroRetornoDTO.setListBairroDTO(listBairroDTO);
		logradouroRetornoDTO.setNomeLogradouro(logradouroDTO.getNomeLogradouro());

		return logradouroRetornoDTO;
	}

	private boolean validarLogrouroImovel(Short codLogradouro, Short codLocalidade) {
		if (!this.buscarMatriculaImovel(codLogradouro, codLocalidade).isEmpty()) {

			return false;
		}
		return true;

	}

	private boolean validarItemAtendimento(Short codLogradouro, Short codLocalidade) {
		if (!this.buscarItemAtendimento(codLogradouro, codLocalidade).isEmpty()) {
			return false;
		}
		return true;

	}

	private boolean validarSolicitacaoServico(Short codLogradouro, Short codLocalidade) {
		if (!this.buscarSolicitacaoServico(codLogradouro, codLocalidade).isEmpty()) {
			return false;
		}
		return true;

	}

	private List<ImovelLogradouroDTO> buscarMatriculaImovel(Short codLogradouro, Short codLocalidade) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", "application/json");
		RestTemplate restTemplate = new RestTemplate();

		String url = urlMicroservico.getUrlImovel() + "/backend-imovel/imovel";
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url).queryParam("codLogradouro", codLogradouro)
				.queryParam("codLocalidade", codLocalidade);
		HttpEntity<?> request = new HttpEntity<>(headers);
		HttpEntity<List<ImovelLogradouroDTO>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET,
				request, new ParameterizedTypeReference<List<ImovelLogradouroDTO>>() {
				});
		return response.getBody();
	}

	private List<ItemAtendimentoRetornoDTO> buscarItemAtendimento(Short codLogradouro, Short codLocalidade) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", "application/json");
		RestTemplate restTemplate = new RestTemplate();

		String url = urlMicroservico.getUrlServico() + "/backend-servico/itemAtendimento";
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url).queryParam("codLogradouro", codLogradouro)
				.queryParam("codLocalidade", codLocalidade);
		HttpEntity<?> request = new HttpEntity<>(headers);
		HttpEntity<List<ItemAtendimentoRetornoDTO>> response = restTemplate.exchange(builder.toUriString(),
				HttpMethod.GET, request, new ParameterizedTypeReference<List<ItemAtendimentoRetornoDTO>>() {
				});

		return response.getBody();
	}

	private List<SolicitacaoServicoRetornoDTO> buscarSolicitacaoServico(Short codLogradouro, Short codLocalidade) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", "application/json");
		RestTemplate restTemplate = new RestTemplate();

		String url = urlMicroservico.getUrlServico() + "/backend-servico/solicitacaoServico/logradouroServico";
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url).queryParam("codLogradouro", codLogradouro)
				.queryParam("codLocalidade", codLocalidade);
		HttpEntity<?> request = new HttpEntity<>(headers);
		HttpEntity<List<SolicitacaoServicoRetornoDTO>> response = restTemplate.exchange(builder.toUriString(),
				HttpMethod.GET, request, new ParameterizedTypeReference<List<SolicitacaoServicoRetornoDTO>>() {
				});
		return response.getBody();
	}

	private List<ItemAtendimentoSSRetornoDTO> listarItemAtendimentoSS(Short codLogradouro, Short codLocalidade,List<Short> idsBairro,boolean flagExclusao) {
		List<ItemAtendimentoSSRetornoDTO> listItemAtendimentoSS = new ArrayList<>();

		List<SolicitacaoServicoRetornoDTO> listSolicitacaoServico = this.buscarSolicitacaoServico(codLogradouro,
				codLocalidade);

		List<ItemAtendimentoRetornoDTO> listItemAtendimento = this.buscarItemAtendimento(codLogradouro, codLocalidade);

		
		if(!flagExclusao) {
			for(Short idBairro : idsBairro) {
			listSolicitacaoServico.removeIf(e->e.getCdBairro().shortValue()==idBairro.shortValue());
			listItemAtendimento.removeIf(e->e.getCodBairro().shortValue()==idBairro.shortValue());
			}
		}
		
		for (SolicitacaoServicoRetornoDTO solicitacaoServico : listSolicitacaoServico) {
			ItemAtendimentoSSRetornoDTO itemAtendimentoSSRetornoDTO = new ItemAtendimentoSSRetornoDTO();
			itemAtendimentoSSRetornoDTO.setCodAtendimento(solicitacaoServico.getCodAtendimento());
			itemAtendimentoSSRetornoDTO.setRefAtendimento(solicitacaoServico.getRefAtendimento());
			itemAtendimentoSSRetornoDTO.setSequencial(solicitacaoServico.getSeqSS());

			listItemAtendimentoSS.add(itemAtendimentoSSRetornoDTO);

		}

		for (ItemAtendimentoRetornoDTO itemAtendimento : listItemAtendimento) {
			ItemAtendimentoSSRetornoDTO itemAtendimentoSSRetornoDTO = new ItemAtendimentoSSRetornoDTO();
			itemAtendimentoSSRetornoDTO.setCodAtendimento(itemAtendimento.getCodAtendimento());
			itemAtendimentoSSRetornoDTO.setRefAtendimento(itemAtendimento.getRefAtendimento());
			itemAtendimentoSSRetornoDTO.setSequencial(itemAtendimento.getSeqAtendimento());

			listItemAtendimentoSS.add(itemAtendimentoSSRetornoDTO);

		}

		return listItemAtendimentoSS;

	}

	private boolean validarDesassociacaoBairro(LogradouroDTO logradouroDTO) {
		boolean valido = true;
		List<Short> listBairro = bairroLogradouroRepository
				.findByCodLocalidadeAndCodLogradouro(logradouroDTO.getCodLocalidade(), logradouroDTO.getCodLogradouro())
				.stream().map(e -> e.getCodBairro()).collect(Collectors.toList());

		for (Short bairro : listBairro) {
			if (logradouroDTO.getIdsBairro().stream().noneMatch(e -> e.shortValue() == bairro)) {

				if (this.buscarMatriculaImovel(bairro, logradouroDTO.getCodLocalidade(),logradouroDTO.getCodLogradouro()).stream().anyMatch(
						e -> e.getCodLogradouro().shortValue() == logradouroDTO.getCodLogradouro().shortValue())) {
					valido = false;
					break;
				}else if(this.buscarItemAtendimento(bairro,logradouroDTO.getCodLocalidade(),logradouroDTO.getCodLogradouro()).stream().anyMatch(e -> e.getCodLogradouro().shortValue() == logradouroDTO.getCodLogradouro().shortValue())) {
					valido = false;
					break;
				}else if(this.buscarSolicitacaoServico(bairro,logradouroDTO.getCodLocalidade(),logradouroDTO.getCodLogradouro()).stream().anyMatch(e -> e.getCodLogradouro().shortValue() == logradouroDTO.getCodLogradouro().shortValue())) {
					valido = false;
					break;
				}
			}

		}
		return valido;
	}
	
	
	private List<SolicitacaoServicoRetornoDTO> buscarSolicitacaoServico(Short codBairro, Short codLocalidade,Short codLogradouro) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", "application/json");
		RestTemplate restTemplate = new RestTemplate();

		String url = urlMicroservico.getUrlServico() + "/backend-servico/solicitacaoServico/bairroLogradouroServico";
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url).queryParam("codBairro", codBairro)
				.queryParam("codLocalidade", codLocalidade).queryParam("codLogradouro", codLogradouro);
		HttpEntity<?> request = new HttpEntity<>(headers);
		HttpEntity<List<SolicitacaoServicoRetornoDTO>> response = restTemplate.exchange(builder.toUriString(),
				HttpMethod.GET, request, new ParameterizedTypeReference<List<SolicitacaoServicoRetornoDTO>>() {
				});
		return response.getBody();
	}
	


	private List<ItemAtendimentoRetornoDTO> buscarItemAtendimento(Short codBairro, Short codLocalidade,Short codLogradouro) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", "application/json");
		RestTemplate restTemplate = new RestTemplate();

		String url = urlMicroservico.getUrlServico() + "/backend-servico/itemAtendimento/bairroLogradouro";
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url).queryParam("codBairro", codBairro)
				.queryParam("codLocalidade", codLocalidade).queryParam("codLogradouro", codLogradouro);
		HttpEntity<?> request = new HttpEntity<>(headers);
		HttpEntity<List<ItemAtendimentoRetornoDTO>> response = restTemplate.exchange(builder.toUriString(),
				HttpMethod.GET, request, new ParameterizedTypeReference<List<ItemAtendimentoRetornoDTO>>() {
				});
		return response.getBody();
	}
	
	
	private List<ImovelLogradouroDTO> buscarMatriculaImovel(Short codBairro, Short codLocalidade,Short codLogradouro) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", "application/json");
		RestTemplate restTemplate = new RestTemplate();

		String url = urlMicroservico.getUrlImovel() + "/backend-imovel/imovel/bairroLogradouro";
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url).queryParam("codBairro", codBairro)
				.queryParam("codLocalidade", codLocalidade).queryParam("codLogradouro", codLogradouro);
		HttpEntity<?> request = new HttpEntity<>(headers);
		HttpEntity<List<ImovelLogradouroDTO>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET,
				request, new ParameterizedTypeReference<List<ImovelLogradouroDTO>>() {
				});
		return response.getBody();

	}

}
