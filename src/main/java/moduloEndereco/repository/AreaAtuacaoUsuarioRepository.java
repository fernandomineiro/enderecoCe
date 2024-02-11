package moduloEndereco.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import moduloEndereco.model.AreaAtuacao;
import moduloEndereco.model.AreaAtuacaoUsuario;

@Repository
public interface AreaAtuacaoUsuarioRepository  extends JpaRepository<AreaAtuacaoUsuario,Long> {

	AreaAtuacaoUsuario findByAreaAtuacaoAndIdUsuario(AreaAtuacao areaAtuacao, Long idUsuario);
	 
	List<AreaAtuacaoUsuario> findByAreaAtuacao(AreaAtuacao areaAtuacao);
	
	List<AreaAtuacaoUsuario> findByIdUsuario(Long idUsuario);
	
}
