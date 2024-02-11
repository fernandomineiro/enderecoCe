package moduloEndereco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import moduloEndereco.model.IdLogFaixaBairro;
import moduloEndereco.model.LogFaixaBairro;

@Repository
public interface LogFaixaBairroRepository extends JpaRepository<LogFaixaBairro,IdLogFaixaBairro>  {

}
