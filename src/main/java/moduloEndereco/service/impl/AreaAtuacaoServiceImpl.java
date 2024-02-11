package moduloEndereco.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import moduloEndereco.autenticacao.JwtTokenProvider;
import moduloEndereco.excecoes.MsgGenericaPersonalizadaException;
import moduloEndereco.model.AreaAtuacao;
import moduloEndereco.model.AreaAtuacaoUsuario;
import moduloEndereco.model.IdBairroIdLocalidade;
import moduloEndereco.rabbitmq.produtores.Produtor;
import moduloEndereco.rabbitmq.produtores.mensagens.AreaAtuacaoMsgProdutor;
import moduloEndereco.repository.AreaAtuacaoRepository;
import moduloEndereco.repository.AreaAtuacaoUsuarioRepository;
import moduloEndereco.service.AreaAtuacaoBairroService;
import moduloEndereco.service.AreaAtuacaoService;
import moduloEndereco.service.AuditoriaService;
import moduloEndereco.service.dto.AreaAtuacaoBairroDTO;
import moduloEndereco.service.dto.AreaAtuacaoDTO;
import moduloEndereco.service.dto.AreaAtuacaoWrapperDTO;
import moduloEndereco.service.dto.MensagemTipoGenericoDTO;
import moduloEndereco.service.mapper.AreaAtuacaoMapper;
import moduloEndereco.util.Constants;

@Service
@Transactional
public class AreaAtuacaoServiceImpl implements AreaAtuacaoService {

	@Autowired
	private AreaAtuacaoRepository areaAtuacaoRepository;
	@Autowired
	private AreaAtuacaoUsuarioRepository areaAtuacaoUsuarioRepository;
	@Autowired
	private AreaAtuacaoMapper areaAtuacaoMapper;
	@Autowired
	private AuditoriaService auditoriaService;
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	@Autowired
	private AreaAtuacaoBairroService areaAtuacaoBairroService;

	@Value("${rabbitmq.direct.produtorAreaAtuacao.nomeExchange}")
	private String exchangeAreaAtuacao;
	@Value("${rabbitmq.direct.produtorAreaAtuacao.rota}")
	private String rotaAreaAtuacao;

	@Autowired
	Produtor produtor;

	@Override
	public AreaAtuacaoWrapperDTO pesquisarAreaAtuacao(String nome, Pageable pageable) {
		AreaAtuacaoWrapperDTO areaAtuacaoWrapperDTO = new AreaAtuacaoWrapperDTO();
		if (nome == null) {
			areaAtuacaoWrapperDTO
					.setListAreaAtuacao(areaAtuacaoMapper.toDto(areaAtuacaoRepository.findByStatusReg("A", pageable)));
			areaAtuacaoWrapperDTO.setTotalRegistros(areaAtuacaoRepository.countByStatusReg("A"));
			return areaAtuacaoWrapperDTO;
		} else {
			this.validarTamanho(nome);
			areaAtuacaoWrapperDTO.setListAreaAtuacao(areaAtuacaoMapper
					.toDto(areaAtuacaoRepository.findByNomeIgnoreCaseContainingAndStatusReg(nome, "A", pageable)));
			areaAtuacaoWrapperDTO
					.setTotalRegistros(areaAtuacaoRepository.countByNomeIgnoreCaseContainingAndStatusReg(nome, "A"));
			return areaAtuacaoWrapperDTO;
		}
	}

	@Override
	public AreaAtuacaoDTO buscarPorId(Long id) {
		return areaAtuacaoMapper.toDto(areaAtuacaoRepository.findById(id).get());
	}

	@Override
	public Long salvar(AreaAtuacaoBairroDTO areaAtuacaoBairroDTO, String token) {
		this.validarNome(areaAtuacaoBairroDTO.getNomeAreaAtuacao());
		Long idUsuario = jwtTokenProvider.buscarIdUsuario(token);
		areaAtuacaoBairroDTO.setNomeAreaAtuacao(areaAtuacaoBairroDTO.getNomeAreaAtuacao().toUpperCase());
		AreaAtuacao areaAtuacao = new AreaAtuacao();
		areaAtuacao.setNome(areaAtuacaoBairroDTO.getNomeAreaAtuacao());
		areaAtuacao.setStatusReg("A");
		areaAtuacao = areaAtuacaoRepository.save(areaAtuacao);
		String areaAtuacaoJson = areaAtuacao.toJson();
		areaAtuacaoBairroDTO.setIdAreaAtuacao(areaAtuacao.getId());
		auditoriaService.gerarAuditoria(areaAtuacao.getId(), Constants.EMPTY_STRING, areaAtuacaoJson, 10L,
				"AreaAtuacao", idUsuario);
		areaAtuacaoBairroService.salvar(areaAtuacaoBairroDTO, idUsuario);
		this.enviarRabbit(areaAtuacao);
		return areaAtuacao.getId();
	}

	@Override
	public void excluir(Long id, String token) {
		this.validarExclusao(id);
		Long idUsuario = jwtTokenProvider.buscarIdUsuario(token);
		areaAtuacaoBairroService.excluir(id, idUsuario);
		AreaAtuacao areaAtuacao = areaAtuacaoRepository.findById(id).get();
		areaAtuacao.setStatusReg("E");
		areaAtuacaoRepository.save(areaAtuacao);
		String areaAtuacaoJson = areaAtuacao.toJson();
		auditoriaService.gerarAuditoria(id, areaAtuacaoJson, Constants.EMPTY_STRING, 10L, "AreaAtuacao", idUsuario);
		this.enviarRabbit(areaAtuacao);
	}

	@Override
	public Long atualizar(AreaAtuacaoBairroDTO areaAtuacaoBairroDTO, String token) {
		areaAtuacaoBairroDTO.setNomeAreaAtuacao(areaAtuacaoBairroDTO.getNomeAreaAtuacao().toUpperCase());
		Long idUsuario = jwtTokenProvider.buscarIdUsuario(token);
		String nomeAntigo = areaAtuacaoRepository.findById(areaAtuacaoBairroDTO.getIdAreaAtuacao()).get().getNome();
		boolean nomeIguais = areaAtuacaoBairroDTO.getNomeAreaAtuacao().equalsIgnoreCase(nomeAntigo);
		if (!nomeIguais)
			this.validarNome(areaAtuacaoBairroDTO.getNomeAreaAtuacao());
		areaAtuacaoBairroService.atualizar(areaAtuacaoBairroDTO, idUsuario);

		if (!nomeIguais) {
			AreaAtuacao areaAtuacaoAntigo = new AreaAtuacao();
			areaAtuacaoAntigo.setId(areaAtuacaoBairroDTO.getIdAreaAtuacao());
			areaAtuacaoAntigo.setNome(nomeAntigo);
			AreaAtuacao areaAtuacaoNovo = new AreaAtuacao();
			areaAtuacaoNovo.setId(areaAtuacaoBairroDTO.getIdAreaAtuacao());
			areaAtuacaoNovo.setNome(areaAtuacaoBairroDTO.getNomeAreaAtuacao());
			areaAtuacaoNovo.setStatusReg("A");
			String areaAtuacaoJsonAntigo = areaAtuacaoAntigo.toJson();
			String areaAtuacaoJsonNovo = areaAtuacaoNovo.toJson();
			areaAtuacaoRepository.save(areaAtuacaoNovo);
			auditoriaService.gerarAuditoria(areaAtuacaoBairroDTO.getIdAreaAtuacao(), areaAtuacaoJsonAntigo,
					areaAtuacaoJsonNovo, 10L, "AreaAtuacao", idUsuario);
			this.enviarRabbit(areaAtuacaoNovo);
		}

		return areaAtuacaoBairroDTO.getIdAreaAtuacao();
	}

	@Override
	public List<AreaAtuacao> listarAreaAtuacaoPorUsuario(String token) {
		return areaAtuacaoUsuarioRepository.findByIdUsuario(jwtTokenProvider.buscarIdUsuario(token)).stream()
				.map(e -> e.getAreaAtuacao()).collect(Collectors.toList());
	}

	@Override
	public MensagemTipoGenericoDTO<Boolean> validarAreaAtuacao(Short cdCidade, Short cdBairro, String token) {
		MensagemTipoGenericoDTO<Boolean> mensagemTipoGenericoDTO = new MensagemTipoGenericoDTO<>();
		mensagemTipoGenericoDTO.setMensagem(false);
		List<AreaAtuacaoUsuario> listAreaAtuacaoUsuario = areaAtuacaoUsuarioRepository
				.findByIdUsuario(jwtTokenProvider.buscarIdUsuario(token));
		
		if(!listAreaAtuacaoUsuario.isEmpty()) {
		List<IdBairroIdLocalidade> listIdBairroIdLocalidade = areaAtuacaoBairroService.listarBairroPorAreaAtuaco(
				listAreaAtuacaoUsuario.stream().map(e -> e.getAreaAtuacao()).collect(Collectors.toList()));

		for (IdBairroIdLocalidade idBairroIdLocalidade : listIdBairroIdLocalidade) {
			if (idBairroIdLocalidade.getCdLocalidade().shortValue() == cdCidade.shortValue()
					&& idBairroIdLocalidade.getCdBairro().shortValue() == cdBairro.shortValue()) {
				mensagemTipoGenericoDTO.setMensagem(true);
				break;
			}

		}
		}else {
			mensagemTipoGenericoDTO.setMensagem(true);
		}

		return mensagemTipoGenericoDTO;
	}

	private void validarTamanho(String nome) {
		if (nome.length() < 3 || nome.length() > 60) {
			throw new MsgGenericaPersonalizadaException("Minimo três caracteres e máximo 60 caracteres");
		}
	}

	private void validarNome(String nome) {
		if (nome.length() < 3 || nome.length() > 60)
			throw new MsgGenericaPersonalizadaException("Minimo três caracteres e máximo 60 caracteres");
		AreaAtuacao areaAtuacao = areaAtuacaoRepository.findByNomeAndStatusReg(nome, "A");
		if (areaAtuacao != null)
			throw new MsgGenericaPersonalizadaException("Este nome de área de atuação já existe.");

	}

	private void enviarRabbit(AreaAtuacao areaAtuacao) {
		AreaAtuacaoMsgProdutor areaAtuacaoMsgProdutor = new AreaAtuacaoMsgProdutor(areaAtuacao.getId(),
				areaAtuacao.getNome(), areaAtuacao.getStatusReg());
		produtor.envioMensagem(exchangeAreaAtuacao, "direct", rotaAreaAtuacao, areaAtuacaoMsgProdutor);
	}

	private void validarExclusao(Long id) {
		AreaAtuacao areaAtuacao = new AreaAtuacao();
		areaAtuacao.setId(id);
		List<AreaAtuacaoUsuario> listAreaAtuacaoUsuario = areaAtuacaoUsuarioRepository.findByAreaAtuacao(areaAtuacao);
		if (!listAreaAtuacaoUsuario.isEmpty())
			throw new MsgGenericaPersonalizadaException("Existe usuário associado à esta área de atuação");
	}

}
