package moduloEndereco.batch;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import moduloEndereco.autenticacao.JwtTokenProvider;
import moduloEndereco.model.EnderecoGis;
import moduloEndereco.model.ServicoAtendimento;
import moduloEndereco.repository.EnderecoGisRepository;
import moduloEndereco.repository.ServicoAtendimentoRepository;
import moduloEndereco.service.AuditoriaService;
import moduloEndereco.service.EnderecoGisService;
import moduloEndereco.service.dto.EnderecoGisDTO;
import moduloEndereco.service.dto.SolicitacaoServicoItemAtendimentoDTO;
import moduloEndereco.service.mapper.EnderecoGisMapper;
import moduloEndereco.service.mapper.ImovelEnderecoMapper;
import moduloEndereco.util.Constants;
import moduloEndereco.util.UrlMicroservico;

@Component
public class EnderecoGisRotina implements Runnable {

	private List<Integer> ids;
	private String token;
	@Autowired
	private EnderecoGisService enderecoGisService;
	@Autowired
	private UrlMicroservico urlMicroservico;
	@Autowired
	private ServicoAtendimentoRepository servicoAtendimentoRepository;
	@Autowired
	private EnderecoGisRepository enderecoGisRepository;
	@Autowired
	private EnderecoGisMapper enderecoGisMapper;
	@Autowired
	private ImovelEnderecoMapper imovelEnderecoMapper;
	@Autowired
	private AuditoriaService auditoriaService;
	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Override
	public void run() {

		this.iniciarProcesso();
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public List<Integer> getIds() {
		return ids;
	}

	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}

	private void iniciarProcesso() {
		List<ServicoAtendimento> listServicoAtendimento = new ArrayList<>();
		List<EnderecoGisDTO> listEnderecoGisDTO = enderecoGisService.buscarEnderecosAlterado(ids);
		List<EnderecoGis> listEnderecoGis = enderecoGisMapper.toEntity(listEnderecoGisDTO);
		this.alterarSituacaoGis(listEnderecoGis, (short) 3);
		List<SolicitacaoServicoItemAtendimentoDTO> listAuxiliarSolicitacaoServicoItemAtendimentoDTO = this
				.buscarSolicitacaoServicoItemAtendimento(listEnderecoGisDTO.stream().map(e -> e.getMatriculaImovel())
						.distinct().collect(Collectors.toList()));
		ServicoAtendimento servicoAtendimento = null;
		for (EnderecoGisDTO enderecoGisDTO : listEnderecoGisDTO) {
			List<SolicitacaoServicoItemAtendimentoDTO> listSolicitacaoServicoItemAtendimentoDTO = listAuxiliarSolicitacaoServicoItemAtendimentoDTO
					.stream()
					.filter(e -> e.getMatricula().byteValue() == enderecoGisDTO.getMatriculaImovel().byteValue())
					.collect(Collectors.toList());

			if (listSolicitacaoServicoItemAtendimentoDTO.isEmpty()) {
				servicoAtendimento = new ServicoAtendimento();
				
				servicoAtendimento.setCdBairroAntigo(enderecoGisDTO.getCdBairroAntigo());
				servicoAtendimento.setCdBairroNovo(enderecoGisDTO.getCdBairroNovo());
				servicoAtendimento.setCdLocalidade(enderecoGisDTO.getCdLocalidade());
				servicoAtendimento.setCdLogradouroAntigo(enderecoGisDTO.getCdLogradouroAntigo());
				servicoAtendimento.setCdLogradouroNovo(enderecoGisDTO.getCdLogradouroNovo());
				servicoAtendimento.setUsuario(jwtTokenProvider.buscarLogin(token));
				servicoAtendimento.setDataInclusao(enderecoGisDTO.getDataInclusao()
						.atTime(enderecoGisDTO.getHoraInclusao(), enderecoGisDTO.getMinutoInclusao()));
				servicoAtendimento.setDescricaoBairroAntigo(enderecoGisDTO.getDescricaoBairroAntigo());
				servicoAtendimento.setDescricaoBairroNovo(enderecoGisDTO.getDescricaoBairroNovo());
				servicoAtendimento.setDescricaoLogradouroAntigo(enderecoGisDTO.getDescricaoLogradouroAntigo());
				servicoAtendimento.setDescricaoLogradouroNovo(enderecoGisDTO.getDescricaoLogradouroNovo());
				servicoAtendimento.setDv(enderecoGisDTO.getDv().shortValue());
				servicoAtendimento.setMatriculaImovel(enderecoGisDTO.getMatriculaImovel());
				servicoAtendimento.setSequenciaInclusao(enderecoGisDTO.getSequenciaInclusao());
				servicoAtendimento.setSituacao((short) 3);
				servicoAtendimento.setCdAtendimento(null);
				servicoAtendimento.setRefAtendimento(null);
				servicoAtendimento.setSequencial(null);
				servicoAtendimento.setOrigemMatricula("Imóvel");
				servicoAtendimento.setCdEnderecoGis(enderecoGisDTO.getId().intValue());

				listServicoAtendimento.add(servicoAtendimento);
			} else {
				for (SolicitacaoServicoItemAtendimentoDTO solicitacaoServicoItemAtendimentoDTO : listSolicitacaoServicoItemAtendimentoDTO) {
					servicoAtendimento = new ServicoAtendimento();
					
					servicoAtendimento.setCdBairroAntigo(enderecoGisDTO.getCdBairroAntigo());
					servicoAtendimento.setCdBairroNovo(enderecoGisDTO.getCdBairroNovo());
					servicoAtendimento.setCdLocalidade(enderecoGisDTO.getCdLocalidade());
					servicoAtendimento.setCdLogradouroAntigo(enderecoGisDTO.getCdLogradouroAntigo());
					servicoAtendimento.setCdLogradouroNovo(enderecoGisDTO.getCdLogradouroNovo());
					servicoAtendimento.setUsuario(jwtTokenProvider.buscarLogin(token));
					servicoAtendimento.setDataInclusao(enderecoGisDTO.getDataInclusao()
							.atTime(enderecoGisDTO.getHoraInclusao(), enderecoGisDTO.getMinutoInclusao()));
					servicoAtendimento.setDescricaoBairroAntigo(enderecoGisDTO.getDescricaoBairroAntigo());
					servicoAtendimento.setDescricaoBairroNovo(enderecoGisDTO.getDescricaoBairroNovo());
					servicoAtendimento.setDescricaoLogradouroAntigo(enderecoGisDTO.getDescricaoLogradouroAntigo());
					servicoAtendimento.setDescricaoLogradouroNovo(enderecoGisDTO.getDescricaoLogradouroNovo());
					servicoAtendimento.setDv(enderecoGisDTO.getDv().shortValue());
					servicoAtendimento.setMatriculaImovel(enderecoGisDTO.getMatriculaImovel());
					servicoAtendimento.setSequenciaInclusao(enderecoGisDTO.getSequenciaInclusao());
					servicoAtendimento.setSituacao((short) 3);
					servicoAtendimento.setCdAtendimento(solicitacaoServicoItemAtendimentoDTO.getCdAtendimento());
					servicoAtendimento.setRefAtendimento(solicitacaoServicoItemAtendimentoDTO.getRefAtendimento());
					servicoAtendimento.setSequencial(solicitacaoServicoItemAtendimentoDTO.getSequencial());
					servicoAtendimento.setOrigemMatricula(solicitacaoServicoItemAtendimentoDTO.getOrigemMatricula());
					servicoAtendimento.setCdEnderecoGis(enderecoGisDTO.getId().intValue());

					listServicoAtendimento.add(servicoAtendimento);
				}
			}
		}

		listServicoAtendimento = servicoAtendimentoRepository.saveAll(listServicoAtendimento);
		listServicoAtendimento = listServicoAtendimento.stream().peek(e -> {
			auditoriaService.gerarAuditoria(e.getId(), Constants.EMPTY_STRING, e.toJson(), 20L, "Endereço Gis",
					jwtTokenProvider.buscarIdUsuario(token));
		}).collect(Collectors.toList());

		this.atualizarEndereco(listServicoAtendimento, listServicoAtendimento.stream().map(e -> e.getMatriculaImovel())
				.distinct().collect(Collectors.toList()));
		this.alterarSituacaoGis(listEnderecoGis, (short) 1);

	}

	public void retornarStatusProcessamento() {
		List<ServicoAtendimento> listServicoAtendimento = servicoAtendimentoRepository.findBySituacao((short) 3);
		if (!listServicoAtendimento.isEmpty())
			servicoAtendimentoRepository.deleteAll(listServicoAtendimento);
		List<EnderecoGis> listEnderecoGis = enderecoGisRepository.findBySituacao((short) 3);
		if (!listEnderecoGis.isEmpty()) {
			listEnderecoGis = listEnderecoGis.stream().peek(e -> e.setSituacao((short) 1)).collect(Collectors.toList());
			enderecoGisRepository.saveAll(listEnderecoGis);
		}
	}

	private void atualizarEndereco(List<ServicoAtendimento> listServicoAtendimento, List<Integer> listMatricula) {
		for (Integer matricula : listMatricula) {
			List<ServicoAtendimento> listServicoAtendimentoPorMatricula = listServicoAtendimento.stream()
					.filter(e -> e.getMatriculaImovel().byteValue() == matricula.byteValue())
					.collect(Collectors.toList());
			this.atualizarEnderecoImovel(listServicoAtendimentoPorMatricula.get(0));
			if (listServicoAtendimentoPorMatricula.get(0).getSituacao().byteValue() == 2)
				listServicoAtendimentoPorMatricula.forEach(e -> e.setSituacao((short) 2));
			for (ServicoAtendimento servicoAtendimento : listServicoAtendimentoPorMatricula) {
				if (servicoAtendimento.getSituacao() != 2) {
					if (servicoAtendimento.getCdAtendimento() != null)
						this.atualizarEnderecoItemAtendimentoSolicitacaoServico(servicoAtendimento);
					
					servicoAtendimento.setSituacao((short) 1);
				}
				String servicoAtendimentoAntesJson = servicoAtendimentoRepository.findById(servicoAtendimento.getId())
						.get().toJson();
				String servicoAtendimentoDepoisJson = servicoAtendimentoRepository.save(servicoAtendimento).toJson();
				auditoriaService.gerarAuditoria(servicoAtendimento.getId(), servicoAtendimentoAntesJson,
						servicoAtendimentoDepoisJson, 20L, "Endereço Gis", jwtTokenProvider.buscarIdUsuario(token));
			}
		}
	}

	private List<SolicitacaoServicoItemAtendimentoDTO> buscarSolicitacaoServicoItemAtendimento(
			List<Integer> matriculas) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", "application/json");
		RestTemplate restTemplate = new RestTemplate();
		String url = urlMicroservico.getUrlServico() + "/backend-servico/atendimento/itemAtendimentoSS/";
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url).queryParam("matriculas", matriculas);
		HttpEntity<?> request = new HttpEntity<>(headers);

		HttpEntity<List<SolicitacaoServicoItemAtendimentoDTO>> response = restTemplate.exchange(builder.toUriString(),
				HttpMethod.GET, request, new ParameterizedTypeReference<List<SolicitacaoServicoItemAtendimentoDTO>>() {
				});

		return response.getBody();

	}

	private void alterarSituacaoGis(List<EnderecoGis> listEnderecoGis, short situacao) {
		listEnderecoGis = listEnderecoGis.stream().peek(e -> e.setSituacao(situacao)).collect(Collectors.toList());
		enderecoGisRepository.saveAll(listEnderecoGis);
	}

	private void atualizarEnderecoImovel(ServicoAtendimento servicoAtendimento) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		headers.set("Authorization", this.getToken());
		RestTemplate restTemplate = new RestTemplate();
		String url = urlMicroservico.getUrlImovel() + "/backend-imovel/imovel/endereco";
		try {
			HttpEntity<?> request = new HttpEntity<>(imovelEnderecoMapper.toDto(servicoAtendimento), headers);
			restTemplate.exchange(url, HttpMethod.PUT, request, Void.class);
		} catch (HttpStatusCodeException | ResourceAccessException exception) {
			exception.printStackTrace();
			servicoAtendimento.setSituacao((short) 2);
		}
	}

	private void atualizarEnderecoItemAtendimentoSolicitacaoServico(ServicoAtendimento servicoAtendimento) {
		SolicitacaoServicoItemAtendimentoDTO solicitacaoServicoItemAtendimentoDTO = new SolicitacaoServicoItemAtendimentoDTO();
		solicitacaoServicoItemAtendimentoDTO.setSequencial(servicoAtendimento.getSequencial());
		solicitacaoServicoItemAtendimentoDTO.setCdAtendimento(servicoAtendimento.getCdAtendimento());
		solicitacaoServicoItemAtendimentoDTO.setRefAtendimento(servicoAtendimento.getRefAtendimento());
		solicitacaoServicoItemAtendimentoDTO.setMatricula(servicoAtendimento.getMatriculaImovel());
		solicitacaoServicoItemAtendimentoDTO.setCdBairro(servicoAtendimento.getCdBairroNovo());
		solicitacaoServicoItemAtendimentoDTO.setCodLogradouro(servicoAtendimento.getCdLogradouroNovo());
		solicitacaoServicoItemAtendimentoDTO.setOrigemMatricula(servicoAtendimento.getOrigemMatricula());
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		headers.set("Authorization", this.getToken());
		RestTemplate restTemplate = new RestTemplate();
		String url = urlMicroservico.getUrlServico() + "/backend-servico/atendimento/itemAtendimentoSS";
		try {
			HttpEntity<?> request = new HttpEntity<>(solicitacaoServicoItemAtendimentoDTO, headers);
			restTemplate.exchange(url, HttpMethod.PUT, request, Void.class);
		} catch (HttpStatusCodeException | ResourceAccessException exception) {
			exception.printStackTrace();
			servicoAtendimento.setSituacao((short) 2);
		}
	}
}
