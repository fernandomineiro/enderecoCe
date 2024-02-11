package moduloEndereco.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import moduloEndereco.excecoes.MsgGenericaPersonalizadaException;
import moduloEndereco.model.Bairro;
import moduloEndereco.model.IdBairroIdLocalidade;
import moduloEndereco.model.IdLogradouroIdLocalidade;
import moduloEndereco.model.Logradouro;
import moduloEndereco.model.MunicipioLocalidade;
import moduloEndereco.repository.BairroRepository;
import moduloEndereco.repository.LogradouroRepository;
import moduloEndereco.repository.MunicipioLocalidadeRepository;
import moduloEndereco.repository.ServicoAtendimentoRepository;
import moduloEndereco.repository.filter.ServicoAtendimentoGisFilter;
import moduloEndereco.service.ServicoAtendimentoService;
import moduloEndereco.service.dto.ServicoAtendimentoGisDTO;
import moduloEndereco.service.dto.ServicoAtendimentoProcessadoDTO;
import moduloEndereco.service.dto.SolicitacaoAtendimentoProcessadoWrapperDTO;
import moduloEndereco.service.dto.SolicitacaoAtendimentoProcessamentoWrapperDTO;
import moduloEndereco.service.mapper.ServicoAtendimentoGisMapper;
import moduloEndereco.service.mapper.ServicoAtendimentoProcessamentoMapper;

@Service
public class ServicoAtendimentoServiceImpl implements ServicoAtendimentoService {

	@Autowired
	private ServicoAtendimentoRepository servicoAtendimentoRepository;
	
	@Autowired
	private MunicipioLocalidadeRepository municipioLocalidadeRepository;
	
	@Autowired
	private LogradouroRepository logradouroRepository;
	
	@Autowired
	private BairroRepository bairroRepository;

	@Autowired
	private ServicoAtendimentoProcessamentoMapper servicoAtendimentoProcessamentoMapper;

	@Autowired
	private ServicoAtendimentoGisMapper servicoAtendimentoGisMapper;

	@Override
	public SolicitacaoAtendimentoProcessamentoWrapperDTO buscarProcessamento(Pageable pageable) {
		SolicitacaoAtendimentoProcessamentoWrapperDTO solicitacaoAtendimentoProcessamentoWrapperDTO = new SolicitacaoAtendimentoProcessamentoWrapperDTO();
		solicitacaoAtendimentoProcessamentoWrapperDTO
				.setListServicoAtendimentoProcessamentoDTO(servicoAtendimentoProcessamentoMapper
						.toDto(servicoAtendimentoRepository.findBySituacao((short) 3, pageable)).parallelStream()
						.peek(e -> e.setStatus("processamento")).collect(Collectors.toList()));
		solicitacaoAtendimentoProcessamentoWrapperDTO
				.setTotalRegistros(servicoAtendimentoRepository.countBySituacao((short) 3));
		return solicitacaoAtendimentoProcessamentoWrapperDTO;
	}

	@Override
	public SolicitacaoAtendimentoProcessadoWrapperDTO buscarProcessado(Pageable pageable) {
		SolicitacaoAtendimentoProcessadoWrapperDTO solicitacaoAtendimentoProcessadoWrapperDTO = new SolicitacaoAtendimentoProcessadoWrapperDTO();
		List<ServicoAtendimentoProcessadoDTO> listServicoAtendimentoProcessadoDTO = servicoAtendimentoRepository
				.buscarServicoAtendimentoProcessado(pageable);
		listServicoAtendimentoProcessadoDTO = listServicoAtendimentoProcessadoDTO.parallelStream().peek(e -> {
			if (e.getSituacao().byteValue() == 1)
				e.setStatusProcessamento("Sucesso");
		}).peek(e -> {
			if (e.getSituacao().byteValue() == 2)
				e.setStatusProcessamento("Falha");
		}).collect(Collectors.toList());

		solicitacaoAtendimentoProcessadoWrapperDTO
				.setListServicoAtendimentoProcessadoDTO(listServicoAtendimentoProcessadoDTO);
		solicitacaoAtendimentoProcessadoWrapperDTO
				.setTotalRegistros(servicoAtendimentoRepository.buscarServicoAtendimentoProcessado());
		return solicitacaoAtendimentoProcessadoWrapperDTO;
	}

	@Override
	public List<ServicoAtendimentoGisDTO> filtrarSS(ServicoAtendimentoGisFilter servicoAtendimentoGisFilter) {
		List<ServicoAtendimentoGisDTO> listServicoAtendimentoGisDTO = new ArrayList<>();
		
		List<ServicoAtendimentoGisDTO> listServicoAtendimentoGisSemLocalidade = new ArrayList<>();

		if (servicoAtendimentoGisFilter.getDataInicio() != null && servicoAtendimentoGisFilter.getDataFim() != null) {
			
			

			listServicoAtendimentoGisDTO = servicoAtendimentoGisMapper
					.toDto(servicoAtendimentoRepository.filtrarSS(servicoAtendimentoGisFilter));
			
			
			
			
			/*List<MunicipioLocalidade> municipioLocalidadeDTO = this.buscarImoveis(listServicoAtendimentoGisDTO.stream()
					.map(e -> e.getCdLocalidade()).distinct().collect(Collectors.toList()));*/

			
			/*List<MunicipioLocalidade> municipioLocalidadeDTO = municipioLocalidadeRepository.findByCdCidadeIn(listServicoAtendimentoGisDTO.stream()
						.map(e -> e.getCdLocalidade()).distinct().collect(Collectors.toList()));*/

			listServicoAtendimentoGisDTO.removeIf(e -> e.getCdLocalidade().byteValue() == 0);
			
			listServicoAtendimentoGisDTO = listServicoAtendimentoGisDTO.stream()
					.peek(e -> e.setDescricaoBairroAntigo(bairroRepository.findById(new IdBairroIdLocalidade(e.getCdBairroAntigo(), e.getCdLocalidade())).get()
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

			
			
			if (servicoAtendimentoGisFilter.getLocalidade() != null) {

				List<MunicipioLocalidade> listLocalidade = municipioLocalidadeRepository
						.findByDcCidadeIn(servicoAtendimentoGisFilter.getLocalidade());

				if (!listLocalidade.isEmpty()) {
					listServicoAtendimentoGisDTO  = this.filtrarLocalidade(
							listLocalidade.stream().map(e -> e.getDcCidade()).collect(Collectors.toList()),
							listServicoAtendimentoGisDTO );

				}
				
			} else
				listServicoAtendimentoGisDTO.addAll(listServicoAtendimentoGisSemLocalidade);
			
			
			
			

		} else {

			throw new MsgGenericaPersonalizadaException("Data Inicio e Data Fim são obrigatórias");

		}

		return listServicoAtendimentoGisDTO;
	}

	
	private List<ServicoAtendimentoGisDTO> filtrarLocalidade(List<String> listLocalidade,List<ServicoAtendimentoGisDTO> servicoAtendimentoGisDTO) {

		List<ServicoAtendimentoGisDTO> servicoAtendimentoGisNova = new ArrayList<>();

		for (ServicoAtendimentoGisDTO enderecoGis : servicoAtendimentoGisDTO) {

			for (String loc : listLocalidade) {
				if (enderecoGis.getLocalidade().equals(loc)) {
					servicoAtendimentoGisNova.add(enderecoGis);
					break;

				}

			}

		}

		return servicoAtendimentoGisNova;
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
	
	
	

}
