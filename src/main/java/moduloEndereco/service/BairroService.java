package moduloEndereco.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import moduloEndereco.repository.filter.BairroFilter;
import moduloEndereco.service.dto.BairroDTO;
import moduloEndereco.service.dto.BairroDropDownDTO;
import moduloEndereco.service.dto.BairroRetornoDTO;
import moduloEndereco.service.dto.BairroWrapperRetornoDTO;
import moduloEndereco.service.dto.ImovelLogradouroDTO;
import moduloEndereco.service.dto.ItemAtendimentoRetornoDTO;
import moduloEndereco.service.dto.ItemAtendimentoSSMatriculaImovelWrapperDTO;
import moduloEndereco.service.dto.SolicitacaoServicoRetornoDTO;

public interface BairroService {

	public void salvar(BairroDTO bairroDTO,String token);
	public BairroWrapperRetornoDTO filtrar(BairroFilter bairroFilter, Pageable pageable);
	public BairroRetornoDTO  buscarPorId(Short cdBairro,Short cdLocalidade);
	public BairroRetornoDTO  atualizar(BairroDTO bairroDTO,String token);
	public void excluir(Short cdBairro, Short cdLocalidade,String token);
	public List<BairroDTO> listPorIdLocalidade(Short idLocalidade);
	public List<BairroDTO> buscarTodas();
	public ItemAtendimentoSSMatriculaImovelWrapperDTO listarItemAtendimentoSSMatriculaImovel(Short codBairro,Short codLocalidade);
	public List<BairroDropDownDTO> findAllByOrderByNomeBairroAsc();
	public List<BairroDTO> listarBairroPorAreaAtuacao(Short codLocalidade,String token);
	public List<ImovelLogradouroDTO> buscarMatriculaImovel(Short codBairro, Short codLocalidade);
	public List<ItemAtendimentoRetornoDTO> buscarItemAtendimento(Short codBairro, Short codLocalidade);
	public List<SolicitacaoServicoRetornoDTO> buscarSolicitacaoServico(Short codBairro, Short codLocalidade);
	
}
