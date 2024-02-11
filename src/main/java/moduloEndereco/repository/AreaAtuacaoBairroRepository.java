package moduloEndereco.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import moduloEndereco.model.AreaAtuacao;
import moduloEndereco.model.AreaAtuacaoBairro;

@Repository
public interface AreaAtuacaoBairroRepository extends JpaRepository<AreaAtuacaoBairro,Long> {
	
	List<AreaAtuacaoBairro> findByAreaAtuacao(AreaAtuacao areaAtuacao);

	List<AreaAtuacaoBairro> findByAreaAtuacaoIn(List<AreaAtuacao> listAreaAtuacao);
	

	
}
