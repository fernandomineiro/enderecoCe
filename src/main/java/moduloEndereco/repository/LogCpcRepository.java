package moduloEndereco.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import moduloEndereco.model.LogCpc;

@Repository
public interface LogCpcRepository extends JpaRepository<LogCpc,Integer> {
	
	List<LogCpc> findByCep(String cep);

}
