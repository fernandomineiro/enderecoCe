package moduloEndereco.service.impl;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import moduloEndereco.autenticacao.JwtTokenProvider;
import moduloEndereco.excecoes.MsgGenericaPersonalizadaException;
import moduloEndereco.model.MunicipioLocalidade;
import moduloEndereco.model.Regional;
import moduloEndereco.repository.MunicipioLocalidadeRepository;
import moduloEndereco.repository.RegionalRepository;
import moduloEndereco.repository.filter.RegionalFilter;
import moduloEndereco.service.AuditoriaService;
import moduloEndereco.service.RegionalService;
import moduloEndereco.service.dto.RegionalDTO;
import moduloEndereco.service.dto.RegionalWrapperDTO;
import moduloEndereco.service.mapper.RegionalMapper;
import moduloEndereco.util.Constants;

/**
 * @author Ivan Alves
 * <b>Implementa o serviço de regional</b>
 */
@Service
@Transactional
public class RegionalServiceImpl implements RegionalService {
	
	private final String MSG_DADO_NAO_ENCONTRADO_SALVAR = "Dado não encontrado para salvar o registro";
	private final String MSG_DADO_JA_CADASTRADO = "Dado já cadastrado";
	private final String MSG_ID_NAO_ENCONTRADO = "Código regional não encontrado";
	private final String MSG_EXCLUSAO_INVALIDA="Esta regional está associada a uma localidade";
	private final String MSG_CODIGO_INVALIDO="Codigo invalido";
	@Autowired
	private RegionalMapper mapper;
	@Autowired
	private RegionalRepository repository;
	@Autowired
	private MunicipioLocalidadeRepository municipioLocalidadeRepository;
	@Autowired
	private AuditoriaService auditoriaService;
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	private String token;
	private RegionalDTO dto;
	private Regional model;
	private Regional objetoPosSalvar;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
	/**
	 * @author Ivan Alves
	 * Busca a regional pelo id informado
	 * @param id
	 * @return regional
	 */
	public Regional buscarRegionalPorID(Integer id) {
		Regional regional = null;
		try {
			regional = this.repository.findByCodRegional(id);
			if(regional == null || regional.getDataRemocao() != null) {
				throw new MsgGenericaPersonalizadaException(this.MSG_ID_NAO_ENCONTRADO);
			}
		}catch(Exception er) {
			throw new MsgGenericaPersonalizadaException(this.MSG_ID_NAO_ENCONTRADO);
		}
		
		return regional;
	}
	
	/**
	 * @author Ivan Alves
	 * Busca a listagem de regionais conforme o objeto de  parametros
	 * @param regionalFilter
	 * @param pageable
	 * @return RegionalWrapperDTO
	 */
	public RegionalWrapperDTO filtrar(RegionalFilter regionalFilter, Pageable pageable) {
		return this.repository.filtrar(regionalFilter, pageable);
	}
	
	
	/**
	 * @author Ivan Alves
	 * Salvar objeto
	 * @param dto
	 * @param token
	 * @return dto
	 */
	public RegionalDTO executarProcedimentoSalvar(RegionalDTO dto, String token) {
		dto.setNomeRegional(dto.getNomeRegional().toUpperCase());
		this.validarCodigo(dto.getCodRegional());
		this.setToken(token);
		dto = this.setarStatusDefault(dto);
		this.verificarPresencaDeDados(dto);
		this.converterDTOParaEntity(dto);
		this.verificarSeCodRegionalExiste();   
		this.verificarSeNomeRegionalExiste();
		this.salvar();
		this.converterEntityParaDTO();
		this.auditoriaService.gerarAuditoria(this.model.getCodRegional().longValue(), Constants.EMPTY_STRING, this.model.toJson(), 8L, 
				"Regional", jwtTokenProvider.buscarIdUsuario(token));
		
		return this.dto;
	}
	
	/**
	 * @author Ivan Alves
	 * Editar objeto
	 * @param dto
	 * @param token
	 * @return dto
	 */
	public RegionalDTO executarProcedimentoEditar(RegionalDTO dto, String token) {
		dto.setNomeRegional(dto.getNomeRegional().toUpperCase());
		this.setToken(token);
		dto = this.setarStatusDefault(dto);
		this.verificarPresencaDeDados(dto);
		this.converterDTOParaEntity(dto);
		String nomeAntigo= this.repository.findByCodRegional(dto.getCodRegional()).getNomeRegional();
		if(!nomeAntigo.equalsIgnoreCase(dto.getNomeRegional()))
		this.verificarSeNomeRegionalExiste(dto.getNomeRegional());
		String dadosAntes  = this.repository.findByCodRegional(dto.getCodRegional()).toJson();
		this.salvar();
		this.converterEntityParaDTO();
		this.auditoriaService.gerarAuditoria(this.model.getCodRegional().longValue(), dadosAntes, this.model.toJson(), 8L, 
				"Regional", jwtTokenProvider.buscarIdUsuario(token));
		return this.dto;
	}
	
	
	 /** @author Ivan Alves
	 * Editar objeto
	 * @param dto
	 * @param token
	 * @return dto
	 */
	public RegionalDTO executarProcedimentoRemover(Integer id, String token) {
		this.validarExclusao(id);
		this.setToken(token);
		this.consultarRegionalPorID(id);
		this.setarStatusDataRemocao();
		String dadosAntes  = this.model.toJson();
		this.salvar();
		this.converterEntityParaDTO();
		this.auditoriaService.gerarAuditoria(this.model.getCodRegional().longValue(), dadosAntes, this.model.toJson(), 8L, 
				"Regional", jwtTokenProvider.buscarIdUsuario(token));
		return this.dto;
	}
	
	public void consultarRegionalPorID(Integer id) {
		try {
			this.model = this.repository.findByCodRegionalAndStatus(id, "A");
			if(this.model == null) {
				throw new MsgGenericaPersonalizadaException(this.MSG_ID_NAO_ENCONTRADO);
			}
		}catch(Exception err) {
			throw new MsgGenericaPersonalizadaException(this.MSG_ID_NAO_ENCONTRADO);
		}
	}
	

	/** INICIO MANIPULA DTO */
	/**
	 * @author Ivan Alves
	 *         <p>
	 *         Recebe o DTO. Depois seta no atributo do model os dados convertidos
	 *         de dto para entidade
	 *         </p>
	 * @param acessoGrupoTransacaoDTO
	 */
	private void converterDTOParaEntity(RegionalDTO dto) {
		this.model = this.mapper.toEntity(dto);
	}

	/**
	 * @author Ivan Alves
	 *         <p>
	 *         Recebe uma entidade e converte para DTO, atribui no modelo DTO
	 *         </p>
	 */
	private void converterEntityParaDTO() {
		this.dto = this.mapper.toDto(this.objetoPosSalvar);
	}
	
	/**
	 * @author Ivan Alves
	 *         <p>
	 *         Setar status default dto
	 *         </p>
	 * @param dto
	 * @return dto
	 */
	public RegionalDTO setarStatusDefault(RegionalDTO dto) {
		if(dto.getStatus() == null || dto.getStatus().isEmpty()) {
			dto.setStatus("A");
		}
		return dto;
	}
	
	/**
	 * @author Ivan Alves
	 * Seta o status de inativo e data de remoção
	 */
	public void setarStatusDataRemocao() {
		this.model.setStatus("C");
		this.model.setDataRemocao(Instant.now());
	}

	/**
	 * @author Ivan Alves
	 *         <p>
	 *        Verificar se codigo da regional ja esta cadastrado
	 *         </p>
	 */
	public void verificarSeCodRegionalExiste() {
		Regional regionalConsultado = this.repository.findByCodRegional(this.model.getCodRegional());
		if(regionalConsultado != null) {
			throw new MsgGenericaPersonalizadaException(this.MSG_DADO_JA_CADASTRADO);
		}
	}
	
	/**
	 * @author Ivan Alves
	 *         <p>
	 *         Verificar se nome de regional ja esta cadastrada
	 *         </p>
	 */
	public void verificarSeNomeRegionalExiste() {
		
		Regional regionalConsultado = this.repository.findByNomeRegionalAndDataRemocaoIsNull(this.model.getNomeRegional());
		if(regionalConsultado != null) {
			throw new MsgGenericaPersonalizadaException(this.MSG_DADO_JA_CADASTRADO);
		}
	}
	
    public void verificarSeNomeRegionalExiste(String nome) {
		
		Regional regionalConsultado = this.repository.findByNomeRegionalAndDataRemocaoIsNull(nome);
		if(regionalConsultado != null) {
			throw new MsgGenericaPersonalizadaException(this.MSG_DADO_JA_CADASTRADO);
		}
	}
	
	
	/**
	 * @author Ivan Alves
	 *         <p>
	 *        	Verifica se o usuario informou os dados necessarios
	 *         </p>
	 * @param dto
	 * @return dto
	 */
	public void verificarPresencaDeDados(RegionalDTO dto) {
		if(dto.getCodRegional() == 0) {
			throw new MsgGenericaPersonalizadaException(this.MSG_DADO_NAO_ENCONTRADO_SALVAR);
		}
		
		if(dto.getNomeRegional() == null || dto.getNomeRegional().isEmpty()) {
			throw new MsgGenericaPersonalizadaException(this.MSG_DADO_NAO_ENCONTRADO_SALVAR);
		}
	}

	/** FIM MANIPULA DTO */
	
	/**
	 * @author Ivan Alves
	 *         <p>
	 *         Persiste os dados do acesso a URL no banco de dados
	 *         </p>
	 */
	private void salvar() {
		try {
			this.objetoPosSalvar = this.repository.save(this.model);
		} catch (Exception e) {
			throw new MsgGenericaPersonalizadaException(this.MSG_DADO_NAO_ENCONTRADO_SALVAR);
		}
	}

	private void validarExclusao(Integer cdRegional) {
	Regional regional= new Regional();
	regional.setCodRegional(cdRegional);
	List<MunicipioLocalidade> listMunicipioLocalidade=	municipioLocalidadeRepository.findAllByRegionalAndDataHoraExclusaoIsNull(regional);
	if(!listMunicipioLocalidade.isEmpty())
		throw new MsgGenericaPersonalizadaException(this.MSG_EXCLUSAO_INVALIDA);
	}
	
	private void validarCodigo(Integer cdRegional) {
		if(cdRegional>99)
			throw new MsgGenericaPersonalizadaException(this.MSG_CODIGO_INVALIDO);	
	}

	@Override
	public List<RegionalDTO> listarRegionais() {
		return mapper.toDto(repository.findAllByDataRemocaoIsNullOrderByNomeRegional());
	}
}
