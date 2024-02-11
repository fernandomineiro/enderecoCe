package moduloEndereco.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import moduloEndereco.model.LogLocalidade;

@Repository 
public interface LogLocalidadeRepository extends JpaRepository<LogLocalidade,Integer>{

	List<LogLocalidade> findByCep(String cep);
}
