package moduloEndereco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import moduloEndereco.model.IdLogFaixaUop;
import moduloEndereco.model.LogFaixaUop;

@Repository
public interface LogFaixaUopRepository extends  JpaRepository<LogFaixaUop,IdLogFaixaUop> {

}
