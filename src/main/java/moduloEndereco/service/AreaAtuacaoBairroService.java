package moduloEndereco.service;

import java.util.List;

import moduloEndereco.model.AreaAtuacao;
import moduloEndereco.model.IdBairroIdLocalidade;
import moduloEndereco.service.dto.AreaAtuacaoBairroDTO;
import moduloEndereco.service.dto.BairroDTO;
import moduloEndereco.service.dto.MunicipioDTO;
import moduloEndereco.service.dto.RegionalDTO;

public interface AreaAtuacaoBairroService {

	public List<RegionalDTO> listRegional(Long idAreaAtuacao);
	
	public List<MunicipioDTO> listLocalidade(Long idAreaAtuacao,Integer idRegional);
	
	public List<BairroDTO> listBairro(Long idAreaAtuacao,Short idLocalidade);
	
	public List<BairroDTO> listBairro(Long idAreaAtuacao);
	
	public void salvar(AreaAtuacaoBairroDTO areaAtuacaoBairroDTO, Long idUsuario);
	
	public void excluir(Long idAreaAtuacao,Long idUsuario);
	
	public void atualizar(AreaAtuacaoBairroDTO areaAtuacaoBairroDTO, Long idUsuario);
	
	public List<IdBairroIdLocalidade> listarBairroPorAreaAtuaco(List<AreaAtuacao> listAtuacao);
}
