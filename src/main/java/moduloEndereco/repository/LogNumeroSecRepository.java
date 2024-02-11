package moduloEndereco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import moduloEndereco.model.LogNumeroSec;

@Repository
public interface LogNumeroSecRepository  extends JpaRepository<LogNumeroSec,Integer> {

}
