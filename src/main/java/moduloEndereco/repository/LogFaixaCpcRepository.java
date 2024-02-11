package moduloEndereco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import moduloEndereco.model.IdLogFaixaCpc;
import moduloEndereco.model.LogFaixaCpc;

@Repository
public interface LogFaixaCpcRepository extends JpaRepository<LogFaixaCpc,IdLogFaixaCpc> {

}
