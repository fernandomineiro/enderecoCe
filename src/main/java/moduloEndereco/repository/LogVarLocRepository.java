package moduloEndereco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import moduloEndereco.model.IdLogVarLoc;
import moduloEndereco.model.LogVarLoc;

@Repository
public interface LogVarLocRepository  extends  JpaRepository<LogVarLoc,IdLogVarLoc>{

}
