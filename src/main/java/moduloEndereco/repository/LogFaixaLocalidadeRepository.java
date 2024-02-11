package moduloEndereco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import moduloEndereco.model.IdLogFaixaLocalidade;
import moduloEndereco.model.LogFaixaLocalidade;

@Repository
public interface LogFaixaLocalidadeRepository extends JpaRepository<LogFaixaLocalidade,IdLogFaixaLocalidade>{

}
