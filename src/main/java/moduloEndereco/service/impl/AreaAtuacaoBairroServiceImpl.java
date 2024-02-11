package moduloEndereco.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import moduloEndereco.model.AreaAtuacao;
import moduloEndereco.model.AreaAtuacaoBairro;
import moduloEndereco.model.Bairro;
import moduloEndereco.model.IdBairroIdLocalidade;
import moduloEndereco.model.Regional;
import moduloEndereco.repository.AreaAtuacaoBairroRepository;
import moduloEndereco.repository.MunicipioLocalidadeRepository;
import moduloEndereco.service.AreaAtuacaoBairroService;
import moduloEndereco.service.AuditoriaService;
import moduloEndereco.service.dto.AreaAtuacaoBairroDTO;
import moduloEndereco.service.dto.BairroDTO;
import moduloEndereco.service.dto.BairroLocalidadeDTO;
import moduloEndereco.service.dto.MunicipioDTO;
import moduloEndereco.service.dto.RegionalDTO;
import moduloEndereco.service.mapper.BairroMapper;
import moduloEndereco.service.mapper.MunicipioMapper;
import moduloEndereco.service.mapper.RegionalMapper;
import moduloEndereco.util.Constants;

@Service
@Transactional
public class AreaAtuacaoBairroServiceImpl implements AreaAtuacaoBairroService {

	@Autowired
	private AreaAtuacaoBairroRepository areaAtuacaoBairroRepository;
	@Autowired
	private MunicipioLocalidadeRepository municipioLocalidadeRepository;
	@Autowired
	private RegionalMapper regionalMapper;
	@Autowired
	private MunicipioMapper municipioMapper;
	@Autowired
	private BairroMapper bairroMapper;
	@Autowired
	private AuditoriaService auditoriaService;

	@Override
	public List<RegionalDTO> listRegional(Long idAreaAtuacao) {
		AreaAtuacao areaAtuacao = new AreaAtuacao();
		areaAtuacao.setId(idAreaAtuacao);
		List<Short> listLocalidade = areaAtuacaoBairroRepository.findByAreaAtuacao(areaAtuacao).stream()
				.map(e -> e.getBairro().getIdBairroIdLocalidade().getCdLocalidade()).collect(Collectors.toList());
		List<Regional> listRegional = municipioLocalidadeRepository.findByCdCidadeIn(listLocalidade).stream()
				.map(e -> e.getRegional()).distinct().collect(Collectors.toList());
		if (listRegional.size() > 1)
			listRegional.sort((a, b) -> a.getNomeRegional().compareTo(b.getNomeRegional()));
		return regionalMapper.toDto(listRegional);

	}

	@Override
	public List<MunicipioDTO> listLocalidade(Long idAreaAtuacao, Integer idRegional) {
		AreaAtuacao areaAtuacao = new AreaAtuacao();
		areaAtuacao.setId(idAreaAtuacao);
		List<Short> listLocalidade = areaAtuacaoBairroRepository.findByAreaAtuacao(areaAtuacao).stream()
				.map(e -> e.getBairro().getIdBairroIdLocalidade().getCdLocalidade()).collect(Collectors.toList());
		List<MunicipioDTO> listMunicipioDTO = municipioMapper
				.toDto(municipioLocalidadeRepository.findByCdCidadeIn(listLocalidade).stream()
						.filter(e -> e.getRegional().getCodRegional().equals(idRegional)).collect(Collectors.toList()));
		if (listMunicipioDTO.size() > 1)
			listMunicipioDTO.sort((a, b) -> a.getDcCidade().compareTo(b.getDcCidade()));
		return listMunicipioDTO;
	}

	@Override
	public List<BairroDTO> listBairro(Long idAreaAtuacao, Short idLocalidade) {
		AreaAtuacao areaAtuacao = new AreaAtuacao();
		areaAtuacao.setId(idAreaAtuacao);
		List<Bairro> listBairro = areaAtuacaoBairroRepository.findByAreaAtuacao(areaAtuacao).stream()
				.filter(e -> e.getBairro().getIdBairroIdLocalidade().getCdLocalidade().equals(idLocalidade))
				.map(e -> e.getBairro()).collect(Collectors.toList());
		listBairro.sort((a, b) -> a.getNomeBairro().compareTo(b.getNomeBairro()));
		return bairroMapper.toDto(listBairro);
	}
	
	@Override
	public List<BairroDTO> listBairro(Long idAreaAtuacao) {
		AreaAtuacao areaAtuacao = new AreaAtuacao();
		areaAtuacao.setId(idAreaAtuacao);
		List<Bairro> listBairro = areaAtuacaoBairroRepository.findByAreaAtuacao(areaAtuacao).stream()
				.map(e -> e.getBairro()).collect(Collectors.toList());
		listBairro.sort((a, b) -> a.getNomeBairro().compareTo(b.getNomeBairro()));
		return bairroMapper.toDto(listBairro);
	}

	@Override
	public void salvar(AreaAtuacaoBairroDTO areaAtuacaoBairroDTO, Long idUsuario) {
		List<BairroLocalidadeDTO> lisBairroLocalidadeDTO = areaAtuacaoBairroDTO.getListBairroLocalidade();
		AreaAtuacao areaAtuacao = new AreaAtuacao();
		areaAtuacao.setId(areaAtuacaoBairroDTO.getIdAreaAtuacao());
		for (BairroLocalidadeDTO bairroLocalidadeDTO : lisBairroLocalidadeDTO) {
			Bairro bairro = new Bairro();
			bairro.setIdBairroIdLocalidade(
					new IdBairroIdLocalidade(bairroLocalidadeDTO.getCdBairro(), bairroLocalidadeDTO.getCdLocalidade()));
			AreaAtuacaoBairro areaAtuacaoBairro = new AreaAtuacaoBairro();
			areaAtuacaoBairro.setAreaAtuacao(areaAtuacao);
			areaAtuacaoBairro.setBairro(bairro);
			areaAtuacaoBairroRepository.save(areaAtuacaoBairro);
			String areaAtuacaoBairroJson = bairroLocalidadeDTO.toJson();
			auditoriaService.gerarAuditoria(areaAtuacao.getId(), Constants.EMPTY_STRING, areaAtuacaoBairroJson, 10L,
					"AreaAtuacao", idUsuario);
		}

	}

	@Override
	public void excluir(Long idAreaAtuacao, Long idUsuario) {
		AreaAtuacao areaAtuacao = new AreaAtuacao();
		areaAtuacao.setId(idAreaAtuacao);
		List<AreaAtuacaoBairro> listAreaAtuacaoBairro = areaAtuacaoBairroRepository.findByAreaAtuacao(areaAtuacao);
		for (AreaAtuacaoBairro areaAtuacaoBairro : listAreaAtuacaoBairro) {
			BairroLocalidadeDTO bairroLocalidadeDTO = new BairroLocalidadeDTO();
			bairroLocalidadeDTO.setCdBairro(areaAtuacaoBairro.getBairro().getIdBairroIdLocalidade().getCdBairro());
			bairroLocalidadeDTO
					.setCdLocalidade(areaAtuacaoBairro.getBairro().getIdBairroIdLocalidade().getCdLocalidade());
			areaAtuacaoBairroRepository.delete(areaAtuacaoBairro);
			String areaAtuacaoBairroJson = bairroLocalidadeDTO.toJson();
			auditoriaService.gerarAuditoria(idAreaAtuacao, areaAtuacaoBairroJson, Constants.EMPTY_STRING, 10L,
					"AreaAtuacao", idUsuario);

		}

	}

	@Override
	public void atualizar(AreaAtuacaoBairroDTO areaAtuacaoBairroDTO, Long idUsuario) {
		AreaAtuacao areaAtuacao = new AreaAtuacao();
		areaAtuacao.setId(areaAtuacaoBairroDTO.getIdAreaAtuacao());
		List<AreaAtuacaoBairro> listAreaAtuacaoBairroAntigo = areaAtuacaoBairroRepository
				.findByAreaAtuacao(areaAtuacao);
		List<AreaAtuacaoBairro> listAreaAtuacaoBairroCompleta= this.excluir(listAreaAtuacaoBairroAntigo, areaAtuacaoBairroDTO, idUsuario);
		this.salvar(listAreaAtuacaoBairroCompleta, areaAtuacaoBairroDTO, idUsuario);

	}
	
	@Override
	public List<IdBairroIdLocalidade> listarBairroPorAreaAtuaco(List<AreaAtuacao> listAreaAtuacao) {
		return areaAtuacaoBairroRepository
				.findByAreaAtuacaoIn(listAreaAtuacao).stream().map(e -> e.getBairro().getIdBairroIdLocalidade())
				.distinct().collect(Collectors.toList());
	}


	private List<AreaAtuacaoBairro> excluir(List<AreaAtuacaoBairro> listAreaAtuacaoBairroAntigo,
			AreaAtuacaoBairroDTO listAreaAtuacaoBairroNovo,Long idUsuario) {
		List<BairroLocalidadeDTO> listBairroLocalidadeDTO= listAreaAtuacaoBairroNovo.getListBairroLocalidade();	
		List<AreaAtuacaoBairro> listAreaAtuacaoBairroCompleta= new ArrayList<>();
		for (BairroLocalidadeDTO bairroLocalidadeDTO:  listBairroLocalidadeDTO) {
			for (AreaAtuacaoBairro areaAtuacaoBairroAntigo : listAreaAtuacaoBairroAntigo) {
				if (bairroLocalidadeDTO.getCdBairro()
						.equals(areaAtuacaoBairroAntigo.getBairro().getIdBairroIdLocalidade().getCdBairro())
						&& (bairroLocalidadeDTO.getCdLocalidade().equals(
								areaAtuacaoBairroAntigo.getBairro().getIdBairroIdLocalidade().getCdLocalidade()))) {
					listAreaAtuacaoBairroCompleta.add(areaAtuacaoBairroAntigo);
					listAreaAtuacaoBairroAntigo.removeIf(e->e.equals(areaAtuacaoBairroAntigo));
					break;
				}else {
					listAreaAtuacaoBairroCompleta.add(areaAtuacaoBairroAntigo);
				}
			}
		}
		
		for(AreaAtuacaoBairro areaAtuacaoBairro: listAreaAtuacaoBairroAntigo) {
			BairroLocalidadeDTO bairroLocalidadeDTO = new BairroLocalidadeDTO();
			bairroLocalidadeDTO.setCdBairro(areaAtuacaoBairro.getBairro().getIdBairroIdLocalidade().getCdBairro());
			bairroLocalidadeDTO
					.setCdLocalidade(areaAtuacaoBairro.getBairro().getIdBairroIdLocalidade().getCdLocalidade());
			areaAtuacaoBairroRepository.delete(areaAtuacaoBairro);
			String areaAtuacaoBairroJson = bairroLocalidadeDTO.toJson();
			auditoriaService.gerarAuditoria(areaAtuacaoBairro.getAreaAtuacao().getId(), areaAtuacaoBairroJson, Constants.EMPTY_STRING, 10L,
					"AreaAtuacao", idUsuario);
		}
		return listAreaAtuacaoBairroCompleta;
	}
	

	private void salvar(List<AreaAtuacaoBairro> listAreaAtuacaoBairroAntigo,
			AreaAtuacaoBairroDTO areaAtuacaoBairro,Long idUsuario) {
		
		List<BairroLocalidadeDTO> listBairroLocalidadeDTO= areaAtuacaoBairro.getListBairroLocalidade();
		for (AreaAtuacaoBairro areaAtuacaoBairroAntigo : listAreaAtuacaoBairroAntigo) {
			for (BairroLocalidadeDTO bairroLocalidadeDTO:  listBairroLocalidadeDTO) {
				if (bairroLocalidadeDTO.getCdBairro()
						.equals(areaAtuacaoBairroAntigo.getBairro().getIdBairroIdLocalidade().getCdBairro())
						&& (bairroLocalidadeDTO.getCdLocalidade().equals(
								areaAtuacaoBairroAntigo.getBairro().getIdBairroIdLocalidade().getCdLocalidade()))) {
					listBairroLocalidadeDTO.removeIf(e->e.equals(bairroLocalidadeDTO));
					break;
				}
			}
		}
		if(!listBairroLocalidadeDTO.isEmpty()) {
		AreaAtuacaoBairroDTO  areaAtuacaoBairroNovo = new AreaAtuacaoBairroDTO();
		areaAtuacaoBairroNovo.setIdAreaAtuacao(areaAtuacaoBairro.getIdAreaAtuacao());
		areaAtuacaoBairroNovo.setNomeAreaAtuacao(areaAtuacaoBairro.getNomeAreaAtuacao());
		areaAtuacaoBairroNovo.setListBairroLocalidade(listBairroLocalidadeDTO);
		this.salvar(areaAtuacaoBairroNovo, idUsuario);
		}
	}

	

}
