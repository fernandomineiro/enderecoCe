package moduloEndereco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import moduloEndereco.model.IdLogFaixaUf;
import moduloEndereco.model.LogFaixaUf;

@Repository
public interface LogFaixaUfRepository extends JpaRepository<LogFaixaUf,IdLogFaixaUf> {

}
