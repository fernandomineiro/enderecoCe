package moduloEndereco.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import moduloEndereco.model.LogLogradouro;

@Repository
public interface LogLogradouroRepository extends JpaRepository<LogLogradouro, Integer> {

	List<LogLogradouro> findByCep(String cep);
	

}
