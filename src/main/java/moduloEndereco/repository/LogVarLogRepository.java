package moduloEndereco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import moduloEndereco.model.IdLogVarLog;
import moduloEndereco.model.LogVarLog;

@Repository
public interface LogVarLogRepository extends  JpaRepository<LogVarLog,IdLogVarLog>{

}
