package moduloEndereco.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import moduloEndereco.model.TipoLogradouro;

@Repository
public interface TipoLogradouroRepository extends JpaRepository<TipoLogradouro, Long> {

	List<TipoLogradouro> findAllByOrderByNome();
	
	TipoLogradouro  findBySigla(String sigla);

}
