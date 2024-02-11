package moduloEndereco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import moduloEndereco.model.LogBairro;

@Repository
public interface LogBairroRepository extends JpaRepository<LogBairro,Integer> {
	
	LogBairro findByNumeroBairro(Integer numeroBairro);

}
