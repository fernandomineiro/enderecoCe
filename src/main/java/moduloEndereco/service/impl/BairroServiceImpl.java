package moduloEndereco.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import moduloEndereco.autenticacao.JwtTokenProvider;
import moduloEndereco.excecoes.ExcecaoRegraNegocio;
import moduloEndereco.excecoes.MsgGenericaPersonalizadaException;
import moduloEndereco.model.AreaAtuacao;
import moduloEndereco.model.Bairro;
import moduloEndereco.model.BairroLogradouro;
import moduloEndereco.model.IdBairroIdLocalidade;
import moduloEndereco.model.MunicipioLocalidade;
import moduloEndereco.repository.AreaAtuacaoBairroRepository;
import moduloEndereco.repository.AreaAtuacaoUsuarioRepository;
import moduloEndereco.repository.BairroLogradouroRepository;
import moduloEndereco.repository.BairroRepository;
import moduloEndereco.repository.MunicipioLocalidadeRepository;
import moduloEndereco.repository.filter.BairroFilter;
import moduloEndereco.service.AuditoriaService;
import moduloEndereco.service.BairroService;
import moduloEndereco.service.dto.BairroDTO;
import moduloEndereco.service.dto.BairroDropDownDTO;
import moduloEndereco.service.dto.BairroRetornoDTO;
import moduloEndereco.service.dto.BairroWrapperDTO;
import moduloEndereco.service.dto.BairroWrapperRetornoDTO;
import moduloEndereco.service.dto.ImovelLogradouroDTO;
import moduloEndereco.service.dto.ItemAtendimentoRetornoDTO;
import moduloEndereco.service.dto.ItemAtendimentoSSMatriculaImovelWrapperDTO;
import moduloEndereco.service.dto.ItemAtendimentoSSRetornoDTO;
import moduloEndereco.service.dto.LocalidadeDTO;
import moduloEndereco.service.dto.MunicipioDTO;
import moduloEndereco.service.dto.SolicitacaoServicoRetornoDTO;
import moduloEndereco.service.mapper.BairroDropDownMapper;
import moduloEndereco.service.mapper.BairroMapper;
import moduloEndereco.util.Constants;
import moduloEndereco.util.UrlMicroservico;

@Service
@Transactional
public class BairroServiceImpl implements BairroService {

	@Autowired
	private BairroRepository bairroRepository;
	@Autowired
	private BairroLogradouroRepository bairroLogradouroRepository;
	@Autowired
	private BairroMapper bairroMapper;
	@Autowired
	private BairroDropDownMapper bairroDropDownMapper;
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	@Autowired
	private AuditoriaService auditoriaService;
	@Autowired
	private MunicipioLocalidadeRepository municipioLocalidadeRepository;
	@Autowired
	private AreaAtuacaoUsuarioRepository areaAtuacaoUsuarioRepository;
	@Autowired
	private AreaAtuacaoBairroRepository areaAtuacaoBairroRepository;

	@Autowired
	private UrlMicroservico urlMicroservico;

	@Override
	public void salvar(BairroDTO bairroDTO, String token) {
		if (bairroDTO.getCdBairro().toString().length() > 3)
			throw new MsgGenericaPersonalizadaException("Código inválido.");
		this.validarInclusao(bairroDTO.getCdBairro(), bairroDTO.getCdLocalidade());

		bairroDTO.setNomeBairro(bairroDTO.getNomeBairro().toUpperCase());
		Integer id = 0;
		Bairro idBairro = bairroRepository.findFirstByOrderByIdDesc();
		if (idBairro == null)
			id = 1;
		else {
			id = idBairro.getId();
			++id;
		}

		bairroDTO.setId(id);

		if (bairroDTO.getCdMunicipioImpressao() == null)
			bairroDTO.setCdMunicipioImpressao(0);
		Bairro bairro = bairroMapper.toEntity(bairroDTO);
		bairro.setNomeBairro(bairroDTO.getNomeBairro().trim());
		bairro.setMaint('A');
		bairroRepository.save(bairro);
		String bairroJson = bairroMapper.toDto(bairro).toJson();
		auditoriaService.gerarAuditoria(bairro.getIdBairroIdLocalidade().getCdBairro().longValue(),
				Constants.EMPTY_STRING, bairroJson, 6L, "Bairro", jwtTokenProvider.buscarIdUsuario(token));
	}

	@Override
	public BairroWrapperRetornoDTO filtrar(BairroFilter bairroFilter, Pageable pageable) {
		BairroWrapperRetornoDTO bairroWrapperRetornoDTO = new BairroWrapperRetornoDTO();
		String ordenarCampo = "";
		if (pageable.getSort().iterator().hasNext())
			ordenarCampo = pageable.getSort().iterator().next().getProperty();

		if (ordenarCampo.equals("nomeLocalidade") || ordenarCampo.equals("nomeMunicipio")) {
			bairroWrapperRetornoDTO = this.toBairroWrapperRetornoDTO(bairroRepository.filtrar(bairroFilter));
			if (ordenarCampo.equals("nomeLocalidade")) {
				bairroWrapperRetornoDTO = this.ordenarLocalidade(bairroWrapperRetornoDTO, pageable);
				return bairroWrapperRetornoDTO;
			} else if (ordenarCampo.equals("nomeMunicipio"))
				bairroWrapperRetornoDTO = this.ordenarMunicipio(bairroWrapperRetornoDTO, pageable);
			return bairroWrapperRetornoDTO;
		} else {
			bairroWrapperRetornoDTO = this.toBairroWrapperRetornoDTO(bairroRepository.filtrar(bairroFilter, pageable));
			return bairroWrapperRetornoDTO;
		}
	}

	@Override
	public BairroRetornoDTO buscarPorId(Short cdBairro, Short cdLocalidade) {
		if (bairroRepository.findById(new IdBairroIdLocalidade(cdBairro, cdLocalidade)).isEmpty())
			throw new MsgGenericaPersonalizadaException("Este bairro para esta localidade não existe.");
		BairroRetornoDTO bairroRetornoDTO = this.toBairroRetornoDTO(
				bairroMapper.toDto(bairroRepository.findById(new IdBairroIdLocalidade(cdBairro, cdLocalidade)).get()));
		
		bairroRetornoDTO.setNomeBairro(bairroRetornoDTO.getNomeBairro().replaceAll("\\s+$","").replaceAll("^\\s+",""));
		
		return bairroRetornoDTO;

	}

	@Override
	public BairroRetornoDTO atualizar(BairroDTO bairroDTO, String token) {
		String dadosAntes;
		String dadosDepois;
		Integer idAntes;
		bairroDTO.setNomeBairro(bairroDTO.getNomeBairro().toUpperCase());
		IdBairroIdLocalidade idBairroIdLocalidade = new IdBairroIdLocalidade();
		idBairroIdLocalidade.setCdBairro(bairroDTO.getCdBairro());
		idBairroIdLocalidade.setCdLocalidade(bairroDTO.getCdLocalidade());

		Bairro bairro = bairroRepository.findByIdBairroIdLocalidadeAndDataHoraExclusao(idBairroIdLocalidade, null);
		if (bairro == null) throw new MsgGenericaPersonalizadaException("Este bairro para esta localidade não existe.");
		
		if ( !(bairroDTO.getCdBairro().equals(bairro.getIdBairroIdLocalidade().getCdBairro() )
				&& bairroDTO.getCdLocalidade().equals(bairro.getIdBairroIdLocalidade().getCdLocalidade()) 
				&& (bairroDTO.getCdMunicipioImpressao() == null || bairroDTO.getCdMunicipioImpressao().equals(bairro.getCdMunicipioImpressao().intValue()))
				&& (bairroDTO.getCdBairro() == null || bairroDTO.getCdBairro().equals(bairro.getIdBairroIdLocalidade().getCdBairro()))
				&& !bairroDTO.getNomeBairro().equals(bairro.getNomeBairro()))) {
			
			if (!this.validarBairroImovel(idBairroIdLocalidade.getCdBairro(), idBairroIdLocalidade.getCdLocalidade())
					|| !this.validarItemAtendimento(idBairroIdLocalidade.getCdBairro(),
							idBairroIdLocalidade.getCdLocalidade())
					|| !this.validarSolicitacaoServico(idBairroIdLocalidade.getCdBairro(),
							idBairroIdLocalidade.getCdLocalidade())) {
	
				throw new ExcecaoRegraNegocio("Esse Bairro não pode ser atualizado");
	
			}
		}

		
		idAntes = bairro.getId();
		dadosAntes = bairroMapper.toDto(bairro).toJson();
		if (bairroDTO.getCdMunicipioImpressao() == null)
			bairroDTO.setCdMunicipioImpressao(0);
		bairro = bairroMapper.toEntity(bairroDTO);
		bairro.setNomeBairro(bairroDTO.getNomeBairro().trim());
		bairro.setMaint('A');
		if (!(idAntes == 0 || idAntes == null ))bairro.setId(idAntes);
		
		dadosDepois = bairroMapper.toDto(bairroRepository.save(bairro)).toJson();
		auditoriaService.gerarAuditoria(bairro.getIdBairroIdLocalidade().getCdBairro().longValue(), dadosAntes,
				dadosDepois, 6L, "Bairro", jwtTokenProvider.buscarIdUsuario(token));


		return this.toBairroRetornoDTO(bairroMapper.toDto(bairro));
	}

	@Override
	public void excluir(Short cdBairro, Short cdLocalidade, String token) {

		if (!this.validarBairroImovel(cdBairro, cdLocalidade) || !this.validarItemAtendimento(cdBairro, cdLocalidade)
				|| !this.validarSolicitacaoServico(cdBairro, cdLocalidade)) {

			throw new ExcecaoRegraNegocio("Esse Bairro não pode ser excluído");

		}

		this.validarExclusao(cdBairro, cdLocalidade);

		IdBairroIdLocalidade idBairroIdLocalidade = new IdBairroIdLocalidade();
		idBairroIdLocalidade.setCdBairro(cdBairro);
		idBairroIdLocalidade.setCdLocalidade(cdLocalidade);
		Bairro bairro = bairroRepository.findByIdBairroIdLocalidadeAndDataHoraExclusao(idBairroIdLocalidade, null);
		if (bairro == null)
			throw new MsgGenericaPersonalizadaException("Exclusão inválida");
		else {
			bairro.setDataHoraExclusao(new Date());
			bairroRepository.save(bairro);
			String bairroJson = bairroMapper.toDto(bairro).toJson();
			auditoriaService.gerarAuditoria(bairro.getIdBairroIdLocalidade().getCdBairro().longValue(), bairroJson,
					Constants.EMPTY_STRING, 6L, "Bairro", jwtTokenProvider.buscarIdUsuario(token));
		}

	}

	@Override
	public List<BairroDTO> listPorIdLocalidade(Short cdLocalidade) {
		return bairroMapper.toDto(bairroRepository
				.findByIdBairroIdLocalidade_CdLocalidadeAndDataHoraExclusaoIsNullOrderByNomeBairro(cdLocalidade));
	}

	@Override
	public List<BairroDTO> buscarTodas() {
		return bairroMapper.toDto(bairroRepository.findByDataHoraExclusaoIsNullOrderByNomeBairro());
	}

	@Override
	public List<BairroDropDownDTO> findAllByOrderByNomeBairroAsc() {
		return bairroDropDownMapper.toDto(bairroRepository.findAllByOrderByNomeBairroAsc());
	}

	@Override
	public List<BairroDTO> listarBairroPorAreaAtuacao(Short codLocalidade, String token) {
		List<AreaAtuacao> listAreaAtuacao = areaAtuacaoUsuarioRepository
				.findByIdUsuario(jwtTokenProvider.buscarIdUsuario(token)).stream()
				.map(e -> e.getAreaAtuacao()).collect(Collectors.toList());

		List<Short> listCodBairro = areaAtuacaoBairroRepository.findByAreaAtuacaoIn(listAreaAtuacao).stream()
				.filter(e -> e.getBairro().getIdBairroIdLocalidade().getCdLocalidade().shortValue() == codLocalidade
						.shortValue())
				.map(e -> e.getBairro().getIdBairroIdLocalidade().getCdBairro()).collect(Collectors.toList());
		List<Bairro> listBairro = bairroRepository
				.findByIdBairroIdLocalidade_CdLocalidadeAndDataHoraExclusaoIsNullOrderByNomeBairro(codLocalidade);
		if(!listAreaAtuacao.isEmpty())
		return bairroMapper.toDto(this.filtrarBairroPorArea(listBairro, listCodBairro));
		else
		return bairroMapper.toDto(listBairro);	
	}

	public ItemAtendimentoSSMatriculaImovelWrapperDTO listarItemAtendimentoSSMatriculaImovel(Short codBairro,
			Short codLocalidade) {
		ItemAtendimentoSSMatriculaImovelWrapperDTO itemAtendimentoSSMatriculaImovel = new ItemAtendimentoSSMatriculaImovelWrapperDTO();

		List<ItemAtendimentoSSRetornoDTO> listItemAtendimentoSS = this.listarItemAtendimentoSS(codBairro,
				codLocalidade);

		itemAtendimentoSSMatriculaImovel.setListItemAtendimentoRetornoDTO(listItemAtendimentoSS);

		List<ImovelLogradouroDTO> listMatriculaImovel = this.buscarMatriculaImovel(codBairro, codLocalidade);

		itemAtendimentoSSMatriculaImovel.setListImovelLogradouroDTO(listMatriculaImovel);

		return itemAtendimentoSSMatriculaImovel;

	}
	@Override
	public List<ImovelLogradouroDTO> buscarMatriculaImovel(Short codBairro, Short codLocalidade) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", "application/json");
		RestTemplate restTemplate = new RestTemplate();

		String url = urlMicroservico.getUrlImovel() + "/backend-imovel/imovel/bairro";
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url).queryParam("codBairro", codBairro)
				.queryParam("codLocalidade", codLocalidade);
		HttpEntity<?> request = new HttpEntity<>(headers);
		HttpEntity<List<ImovelLogradouroDTO>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET,
				request, new ParameterizedTypeReference<List<ImovelLogradouroDTO>>() {
				});
		return response.getBody();

	}

	@Override
	public List<ItemAtendimentoRetornoDTO> buscarItemAtendimento(Short codBairro, Short codLocalidade) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", "application/json");
		RestTemplate restTemplate = new RestTemplate();

		String url = urlMicroservico.getUrlServico() + "/backend-servico/itemAtendimento/bairro";
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url).queryParam("codBairro", codBairro)
				.queryParam("codLocalidade", codLocalidade);
		HttpEntity<?> request = new HttpEntity<>(headers);
		HttpEntity<List<ItemAtendimentoRetornoDTO>> response = restTemplate.exchange(builder.toUriString(),
				HttpMethod.GET, request, new ParameterizedTypeReference<List<ItemAtendimentoRetornoDTO>>() {
				});
		return response.getBody();
	}

	@Override
	public List<SolicitacaoServicoRetornoDTO> buscarSolicitacaoServico(Short codBairro, Short codLocalidade) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", "application/json");
		RestTemplate restTemplate = new RestTemplate();

		String url = urlMicroservico.getUrlServico() + "/backend-servico/solicitacaoServico/bairroServico";
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url).queryParam("codBairro", codBairro)
				.queryParam("codLocalidade", codLocalidade);
		HttpEntity<?> request = new HttpEntity<>(headers);
		HttpEntity<List<SolicitacaoServicoRetornoDTO>> response = restTemplate.exchange(builder.toUriString(),
				HttpMethod.GET, request, new ParameterizedTypeReference<List<SolicitacaoServicoRetornoDTO>>() {
				});
		return response.getBody();
	}

	private boolean validarBairroImovel(Short codBairro, Short codLocalidade) {
		if (!this.buscarMatriculaImovel(codBairro, codLocalidade).isEmpty()) {

			return false;
		}
		return true;

	}

	private boolean validarItemAtendimento(Short codBairro, Short codLocalidade) {
		if (!this.buscarItemAtendimento(codBairro, codLocalidade).isEmpty()) {
			return false;
		}
		return true;

	}

	private boolean validarSolicitacaoServico(Short codBairro, Short codLocalidade) {
		if (!this.buscarSolicitacaoServico(codBairro, codLocalidade).isEmpty()) {
			return false;
		}
		return true;

	}

	private void validarInclusao(Short cdBairro, Short cdLocalidade) {
		IdBairroIdLocalidade idBairroIdLocalidade = new IdBairroIdLocalidade();
		idBairroIdLocalidade.setCdBairro(cdBairro);
		idBairroIdLocalidade.setCdLocalidade(cdLocalidade);
		Optional<Bairro> bairro = bairroRepository.findById(idBairroIdLocalidade);
		if (!bairro.isEmpty())
			throw new MsgGenericaPersonalizadaException("Este bairro já está associado a esta localidade");
	}

	private BairroWrapperRetornoDTO toBairroWrapperRetornoDTO(BairroWrapperDTO bairroWrapperDTO) {
		BairroWrapperRetornoDTO bairroWrapperRetornoDTO = new BairroWrapperRetornoDTO();
		List<BairroRetornoDTO> listBairroRetornoDTO = new ArrayList<>();
		bairroWrapperRetornoDTO.setTotalRegistros(bairroWrapperDTO.getTotalRegistros());
		List<BairroDTO> listBairro = bairroWrapperDTO.getListBairroDTO();
		for (BairroDTO bairro : listBairro) {

			BairroRetornoDTO bairroRetornoDTO = new BairroRetornoDTO();
			bairroRetornoDTO.setCdBairro(bairro.getCdBairro());
			bairroRetornoDTO.setNomeBairro(bairro.getNomeBairro());
			bairroRetornoDTO.setLocalidadeDTO(this.carregarLocalidade(bairro.getCdLocalidade()));
			bairroRetornoDTO.setMunicipioDTO(this.carregarMunicipio(bairro.getCdMunicipioImpressao().shortValue()));
			listBairroRetornoDTO.add(bairroRetornoDTO);
		}

		bairroWrapperRetornoDTO.setListBairroDTO(listBairroRetornoDTO);
		return bairroWrapperRetornoDTO;
	}

	private LocalidadeDTO carregarLocalidade(short codigoLocalidade) {
		MunicipioLocalidade municipioLocalidade = municipioLocalidadeRepository.findById(codigoLocalidade).get();
		LocalidadeDTO localidadeDTO = new LocalidadeDTO();
		localidadeDTO.setCodigoLocalidade(codigoLocalidade);
		localidadeDTO.setNomeLocalidade(municipioLocalidade.getDcCidade());
		return localidadeDTO;
	}

	private MunicipioDTO carregarMunicipio(short codigoMunicipio) {
		MunicipioDTO municipioDTO = new MunicipioDTO();
		if (codigoMunicipio != 0) {
			MunicipioLocalidade municipioLocalidade = municipioLocalidadeRepository.findById(codigoMunicipio).get();
			municipioDTO.setCdCidade(codigoMunicipio);
			municipioDTO.setDcCidade(municipioLocalidade.getDcCidade());
			return municipioDTO;
		} else {
			municipioDTO.setCdCidade((short) 0);
			municipioDTO.setDcCidade("");
			return municipioDTO;
		}

	}

	private BairroWrapperRetornoDTO ordenarLocalidade(BairroWrapperRetornoDTO bairroWrapperRetornoDTO,
			Pageable pageable) {
		int indice = pageable.getPageSize() * pageable.getPageNumber();
		List<BairroRetornoDTO> listBairroRetornoDTO = bairroWrapperRetornoDTO.getListBairroDTO();
		listBairroRetornoDTO.sort(
				(a, b) -> a.getLocalidadeDTO().getNomeLocalidade().compareTo(b.getLocalidadeDTO().getNomeLocalidade()));
		if (!pageable.getSort().iterator().next().isDescending())
			listBairroRetornoDTO = listBairroRetornoDTO.stream().skip(indice).limit(pageable.getPageSize())
					.collect(Collectors.toList());
		else {
			Collections.reverse(listBairroRetornoDTO);
			listBairroRetornoDTO = listBairroRetornoDTO.stream().skip(indice).limit(pageable.getPageSize())
					.collect(Collectors.toList());
		}
		bairroWrapperRetornoDTO.setListBairroDTO(listBairroRetornoDTO);

		return bairroWrapperRetornoDTO;
	}

	private BairroWrapperRetornoDTO ordenarMunicipio(BairroWrapperRetornoDTO bairroWrapperRetornoDTO,
			Pageable pageable) {
		int indice = pageable.getPageSize() * pageable.getPageNumber();
		List<BairroRetornoDTO> listBairroRetornoDTO = bairroWrapperRetornoDTO.getListBairroDTO();
		listBairroRetornoDTO
				.sort((a, b) -> a.getMunicipioDTO().getDcCidade().compareTo(b.getMunicipioDTO().getDcCidade()));
		if (!pageable.getSort().iterator().next().isDescending())
			listBairroRetornoDTO = listBairroRetornoDTO.stream().skip(indice).limit(pageable.getPageSize())
					.collect(Collectors.toList());
		else {
			Collections.reverse(listBairroRetornoDTO);
			listBairroRetornoDTO = listBairroRetornoDTO.stream().skip(indice).limit(pageable.getPageSize())
					.collect(Collectors.toList());
		}
		bairroWrapperRetornoDTO.setListBairroDTO(listBairroRetornoDTO);

		return bairroWrapperRetornoDTO;
	}

	private BairroRetornoDTO toBairroRetornoDTO(BairroDTO bairroDTO) {
		BairroRetornoDTO bairroRetornoDTO = new BairroRetornoDTO();
		bairroRetornoDTO.setNomeBairro(bairroDTO.getNomeBairro());
		bairroRetornoDTO.setCdBairro(bairroDTO.getCdBairro());
		LocalidadeDTO localidadeDTO = new LocalidadeDTO();
		localidadeDTO.setCodigoLocalidade(bairroDTO.getCdLocalidade());
		localidadeDTO.setNomeLocalidade(
				municipioLocalidadeRepository.findById(bairroDTO.getCdLocalidade()).get().getDcCidade());
		bairroRetornoDTO.setLocalidadeDTO(localidadeDTO);
		if (bairroDTO.getCdMunicipioImpressao() != 0) {
			MunicipioDTO municipioDTO = new MunicipioDTO();
			municipioDTO.setCdCidade(bairroDTO.getCdMunicipioImpressao().shortValue());
			municipioDTO.setDcCidade(municipioLocalidadeRepository
					.findById(bairroDTO.getCdMunicipioImpressao().shortValue()).get().getDcCidade());
			bairroRetornoDTO.setMunicipioDTO(municipioDTO);
		}
		return bairroRetornoDTO;
	}

	private void validarExclusao(Short idBairro, Short idLocalidade) {
		List<BairroLogradouro> listBairroLogradouro = bairroLogradouroRepository
				.findByCodBairroAndCodLocalidade(idBairro, idLocalidade);
		if (!listBairroLogradouro.isEmpty()) {
			throw new RuntimeException("Existe Logradouros associado a este Bairro.");
		}
	}

	

	
	private List<ItemAtendimentoSSRetornoDTO> listarItemAtendimentoSS(Short codBairro, Short codLocalidade) {
		List<ItemAtendimentoSSRetornoDTO> listItemAtendimentoSS = new ArrayList<>();

		List<SolicitacaoServicoRetornoDTO> listSolicitacaoServico = this.buscarSolicitacaoServico(codBairro,
				codLocalidade);

		List<ItemAtendimentoRetornoDTO> listItemAtendimento = this.buscarItemAtendimento(codBairro, codLocalidade);

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

	private List<Bairro> filtrarBairroPorArea(List<Bairro> listBairro, List<Short> listCodBairro){
		List<Bairro> listNovaBairro=new ArrayList<>();
		for(Short codBairro:listCodBairro) {
		for(Bairro bairro:listBairro) {
			if(bairro.getIdBairroIdLocalidade().getCdBairro().shortValue()==codBairro.shortValue()) {
				listNovaBairro.add(bairro);
			    break;
			}  
		}
		}
		listNovaBairro.sort((a, b) -> a.getNomeBairro().compareTo(b.getNomeBairro()));
		
		return listNovaBairro;
	}
}
