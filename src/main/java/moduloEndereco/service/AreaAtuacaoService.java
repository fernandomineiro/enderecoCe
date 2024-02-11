package moduloEndereco.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import moduloEndereco.model.AreaAtuacao;
import moduloEndereco.service.dto.AreaAtuacaoBairroDTO;
import moduloEndereco.service.dto.AreaAtuacaoDTO;
import moduloEndereco.service.dto.AreaAtuacaoWrapperDTO;
import moduloEndereco.service.dto.MensagemTipoGenericoDTO;

public interface AreaAtuacaoService {

	public AreaAtuacaoWrapperDTO pesquisarAreaAtuacao(String nome,Pageable pageable);
	
	public AreaAtuacaoDTO buscarPorId(Long id);
	
	public Long salvar(AreaAtuacaoBairroDTO areaAtuacaoBairroDTO,String token);
	
	public void excluir(Long id,String token);
	
	public Long atualizar(AreaAtuacaoBairroDTO areaAtuacaoBairroDTO,String token);
	
	public List<AreaAtuacao> listarAreaAtuacaoPorUsuario(String token);
	
	public MensagemTipoGenericoDTO<Boolean> validarAreaAtuacao(Short cdCidade, Short cdBairro,String token);
	
	
}
