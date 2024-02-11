package moduloEndereco.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import moduloEndereco.autenticacao.JwtTokenProvider;
import moduloEndereco.comparator.MunicipioLocalidadeWrapperComparator;
import moduloEndereco.excecoes.MsgGenericaPersonalizadaException;
import moduloEndereco.model.AreaAtuacao;
import moduloEndereco.model.Bairro;
import moduloEndereco.model.Logradouro;
import moduloEndereco.model.MunicipioLocalidade;
import moduloEndereco.model.Regional;
import moduloEndereco.repository.AreaAtuacaoBairroRepository;
import moduloEndereco.repository.AreaAtuacaoUsuarioRepository;
import moduloEndereco.repository.BairroRepository;
import moduloEndereco.repository.HorarioAtendimentoRepository;
import moduloEndereco.repository.LogradouroRepository;
import moduloEndereco.repository.MunicipioLocalidadeRepository;
import moduloEndereco.repository.MunicipioLocalidadeRepositoryQuery;
import moduloEndereco.repository.RegionalRepository;
import moduloEndereco.service.AuditoriaService;
import moduloEndereco.service.MunicipioLocalidadeService;
import moduloEndereco.service.dto.HorarioAtendimentoDTO;
import moduloEndereco.service.dto.MunicipioDTO;
import moduloEndereco.service.dto.MunicipioLocalidadeDTO;
import moduloEndereco.service.dto.MunicipioLocalidadeRetornoDTO;
import moduloEndereco.service.dto.MunicipioLocalidadeWrapper;
import moduloEndereco.service.dto.MunicipioLocalidadeWrapperDTO;
import moduloEndereco.service.dto.RegionalDTO;
import moduloEndereco.service.mapper.HorarioAtendimentoMapper;
import moduloEndereco.service.mapper.MunicipioLocalidadeMapper;
import moduloEndereco.service.mapper.MunicipioLocalidadeRetornoMapper;
import moduloEndereco.service.mapper.MunicipioMapper;
import moduloEndereco.service.mapper.RegionalMapper;
import moduloEndereco.util.Constants;

@Service
@Transactional
public class MunicipioLocalidadeServiceImpl implements MunicipioLocalidadeService {

	@Autowired
	private MunicipioLocalidadeRepository municipioLocalidadeRepository;
	@Autowired
	private AreaAtuacaoUsuarioRepository areaAtuacaoUsuarioRepository;
	@Autowired
	private AreaAtuacaoBairroRepository areaAtuacaoBairroRepository;
	@Autowired
	private HorarioAtendimentoRepository horarioAtendimentoRepository;
	@Autowired
	private LogradouroRepository logradouroRepository;
	@Autowired
	private BairroRepository bairroRepository;
	@Autowired
	private RegionalRepository regionalRepository;
	@Autowired
	private MunicipioLocalidadeRepositoryQuery municipioLocalidadeRepositoryQuery;

	@Autowired
	private MunicipioMapper municipioMapper;
	@Autowired
	private MunicipioLocalidadeMapper municipioLocalidadeMapper;
	@Autowired
	private MunicipioLocalidadeRetornoMapper municipioLocalidadeRetornoMapper;
	@Autowired
	private HorarioAtendimentoMapper horarioAtendimentoMapper;
	@Autowired
	private RegionalMapper regionalMapper;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	@Autowired
	private AuditoriaService auditoriaService;

	private Long idEntidade = 7L;

	@Override
	public List<MunicipioDTO> listarTodosMunicipiosAtivos() {
		return municipioMapper.toDto(municipioLocalidadeRepository.listarTodosMunicipiosAtivos());
	}

	@Override
	public List<HorarioAtendimentoDTO> listarTodosHorariosAtendimento() {
		return horarioAtendimentoMapper.toDto(horarioAtendimentoRepository.findAll());
	}

	@Override
	public List<MunicipioDTO> listarLocalidade() {
		return municipioMapper.toDto(municipioLocalidadeRepository.findAllByDataHoraExclusaoIsNullOrderByDcCidade());
	}

	@Override
	public List<MunicipioDTO> listarTodasLocalidades() {
		return municipioMapper.toDto(municipioLocalidadeRepository.listarTodasLocalidades());
	}

	@Override
	public List<RegionalDTO> listarTodasRegionaisAtivas() {
		return regionalMapper.toDto(regionalRepository.findAllByDataRemocaoIsNullOrderByNomeRegional());
	}

	@Override
	public MunicipioLocalidadeDTO pesquisarAtivoPorId(Short id) {
		return municipioLocalidadeMapper.toDto(municipioLocalidadeRepository.pesquisarAtivoPorId(id));
	}

	@Override
	public List<MunicipioDTO> listarMunicipiosLocalidades() {
		return municipioMapper.toDto(municipioLocalidadeRepository.listarMunicipiosLocalidades());
	}

	@Override
	public MunicipioLocalidadeWrapperDTO pesquisarMunicipioLocalidade(Short cdCidade, String municipio,
			String localidade, String regional, Short cdTarifa, String temAgua, String temEsgoto, String temDispEsgoto,
			String tipo, Pageable pageable) {

		MunicipioLocalidadeWrapperDTO result = new MunicipioLocalidadeWrapperDTO();
		List<MunicipioLocalidadeWrapper> list = municipioLocalidadeRepositoryQuery.pesquisarMunicipioLocalidade(
				cdCidade, (municipio == null ? null : "%" + municipio + "%"),
				(localidade == null ? null : "%" + localidade + "%"), (regional == null ? null : "%" + regional + "%"),
				cdTarifa, temAgua, temEsgoto, temDispEsgoto, tipo);

		Set<MunicipioLocalidadeWrapper> set = new HashSet<>(list);
		list = new ArrayList<>(set);

		Integer total = list.size();

		String ordenarCampo = "";
		String ordem = "";
		if (pageable.getSort().iterator().hasNext()) {
			Order order = pageable.getSort().iterator().next();
			ordenarCampo = order.getProperty();
			ordem = (order.isAscending() ? "asc" : "desc");
		}

		Collections.sort(list, new MunicipioLocalidadeWrapperComparator(ordenarCampo, ordem));

		Integer inicio = pageable.getPageNumber() * pageable.getPageSize();
		Integer fim = Math.min(inicio + pageable.getPageSize(), total);
		list = list.subList(inicio, fim);

		result.setListMunicipioLocalidadeRetornoDTO(list);
		result.setTotalRegistros(total.longValue());
		return result;
	}

	@Override
	public void incluirMunicipioLocalidade(MunicipioLocalidadeDTO municipioLocalidadeDTO, String token)
			throws RuntimeException {
		if (municipioLocalidadeDTO.getCdCidade().toString().length() > 4)
			throw new MsgGenericaPersonalizadaException("Código inválido.");

		municipioLocalidadeDTO.setDcCidade(municipioLocalidadeDTO.getDcCidade().toUpperCase());
		List<MunicipioLocalidadeWrapper> mlw = municipioLocalidadeRepositoryQuery.pesquisarMunicipioLocalidade(
				municipioLocalidadeDTO.getCdCidade(), null, null, null, null, null, null, null, null);
		if (mlw != null && !mlw.isEmpty()) {
			throw new RuntimeException("O código do Município/Localidade já existe.");
		}

		mlw = municipioLocalidadeRepositoryQuery.pesquisarMunicipioLocalidade(null,
				municipioLocalidadeDTO.getDcCidade(), null, null, null, null, null, null, null);
		if (mlw != null && !mlw.isEmpty()) {
			throw new RuntimeException("O nome do Município já existe.");
		}

		mlw = municipioLocalidadeRepositoryQuery.pesquisarMunicipioLocalidade(null, null,
				municipioLocalidadeDTO.getDcCidade(), null, null, null, null, null, null);
		if (mlw != null && !mlw.isEmpty()) {
			throw new RuntimeException("O nome da Localidade já existe.");
		}

		MunicipioLocalidade municipioLocalidade = municipioLocalidadeMapper.toEntity(municipioLocalidadeDTO);
		if (municipioLocalidade.getMunicipio().equals("S")) {
			municipioLocalidade.setCdMunicipio(municipioLocalidade.getCdCidade());
		}
		municipioLocalidade.setDensidade(0f);
		municipioLocalidade.setIcms(0f);
		municipioLocalidade.setIssqn(0f);
		municipioLocalidade.setMaint("A");
		municipioLocalidade.setMetropolitana("");
		municipioLocalidade.setPopulacao(0);
		municipioLocalidade.setTpLeitura(0);
		municipioLocalidade.setTpCodigoBarras(0);
		municipioLocalidadeRepository.save(municipioLocalidade);
		auditoriaService.gerarAuditoria(municipioLocalidade.getCdCidade().longValue(), Constants.EMPTY_STRING,
				municipioLocalidade.toJson(), idEntidade, "MunicipioLocalidade",
				jwtTokenProvider.buscarIdUsuario(token));
	}

	@Override
	public MunicipioLocalidadeRetornoDTO alterarMunicipioLocalidade(MunicipioLocalidadeDTO municipioLocalidadeDTO,
			String token) throws RuntimeException {
		municipioLocalidadeDTO.setDcCidade(municipioLocalidadeDTO.getDcCidade().toUpperCase());
		MunicipioLocalidade ml = municipioLocalidadeRepository
				.pesquisarAtivoPorId(municipioLocalidadeDTO.getCdCidade());

		if (ml == null) {
			throw new RuntimeException("O Município/Localidade informado não existe.");
		}

		MunicipioLocalidade municipioLocalidade = municipioLocalidadeMapper.toEntity(municipioLocalidadeDTO);
		municipioLocalidade = municipioLocalidadeRepository.saveAndFlush(municipioLocalidade);
		MunicipioLocalidadeRetornoDTO municipioLocalidadeRetornoDTO = municipioLocalidadeRetornoMapper
				.toDto(municipioLocalidade);
		auditoriaService.gerarAuditoria(municipioLocalidade.getCdCidade().longValue(), ml.toJson(),
				municipioLocalidade.toJson(), idEntidade, "MunicipioLocalidade",
				jwtTokenProvider.buscarIdUsuario(token));
		return municipioLocalidadeRetornoDTO;
	}

	@Override
	public void excluirMunicipioLocalidade(Short id, String token) {
		MunicipioLocalidade municipioLocalidade = municipioLocalidadeRepository.pesquisarAtivoPorId(id);
		this.validarExlcusao(id);

		if (municipioLocalidade == null) {
			throw new RuntimeException("O Município/Localidade informado não existe.");
		}

		municipioLocalidade.setDataHoraExclusao(new Date());
		municipioLocalidade = municipioLocalidadeRepository.saveAndFlush(municipioLocalidade);
		auditoriaService.gerarAuditoria(municipioLocalidade.getCdCidade().longValue(), municipioLocalidade.toJson(),
				Constants.EMPTY_STRING, idEntidade, "MunicipioLocalidade", jwtTokenProvider.buscarIdUsuario(token));
	}

	@Override
	public List<MunicipioDTO> buscarPorRegional(Integer id) {
		Regional regional = new Regional();
		regional.setCodRegional(id);
		return municipioMapper.toDto(municipioLocalidadeRepository.findAllByDataHoraExclusaoIsNullOrderByDcCidade());
	}

	private void validarExlcusao(Short cdLocalidade) {
		List<Logradouro> listLogradouro = logradouroRepository
				.findByIdLogradouroIdLocalidade_CodLocalidadeAndDataHoraExclusaoIsNull(cdLocalidade);

		if (!listLogradouro.isEmpty()) {
			throw new RuntimeException("Existe logradouros associado a esta localidade.");
		}
		List<Bairro> listBairro = bairroRepository
				.findByIdBairroIdLocalidade_CdLocalidadeAndDataHoraExclusaoIsNullOrderByNomeBairro(cdLocalidade);
		if (!listBairro.isEmpty()) {
			throw new RuntimeException("Existe Bairros associado a esta localidade.");
		}
	}

	@Override
	public List<MunicipioDTO> listarMunicipiosLocalidadesPorAreaAtuacao(String token) {
		List<AreaAtuacao> listAreaAtuacao = areaAtuacaoUsuarioRepository
				.findByIdUsuario(jwtTokenProvider.buscarIdUsuario(token)).stream()
				.map(e -> e.getAreaAtuacao()).collect(Collectors.toList());
		List<Short> listCodCidade = areaAtuacaoBairroRepository.findByAreaAtuacaoIn(listAreaAtuacao).stream()
				.map(e -> e.getBairro().getIdBairroIdLocalidade().getCdLocalidade()).distinct().collect(Collectors.toList());
		List<MunicipioLocalidade> listMunicipioLocalidade=municipioLocalidadeRepository.listarMunicipiosLocalidades();
		if(!listAreaAtuacao.isEmpty())
		return municipioMapper.toDto(this.filtrarLocalidadePorArea(listMunicipioLocalidade, listCodCidade));
		else
		return municipioMapper.toDto(listMunicipioLocalidade);	
	}

	private List<MunicipioLocalidade> filtrarLocalidadePorArea(List<MunicipioLocalidade> listMunicipioLocalidade, List<Short> listCodCidade){
		List<MunicipioLocalidade> listNovaMunicipioLocalidade=new ArrayList<>();
		for(Short codCidade:listCodCidade) {
		for(MunicipioLocalidade municipioLocalidade:listMunicipioLocalidade) {
			if(municipioLocalidade.getCdCidade().shortValue()==codCidade.shortValue()) {
				listNovaMunicipioLocalidade.add(municipioLocalidade);
			    break;
			}  
		}
		}
		listNovaMunicipioLocalidade.sort((a, b) -> a.getDcCidade().compareTo(b.getDcCidade()));
		
		return listNovaMunicipioLocalidade;
	}
}
