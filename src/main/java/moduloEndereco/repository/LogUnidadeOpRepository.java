package moduloEndereco.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import moduloEndereco.model.LogUnidadeOp;

@Repository
public interface LogUnidadeOpRepository extends JpaRepository<LogUnidadeOp,Integer> {

	List<LogUnidadeOp> findByCep(String cep);
}
