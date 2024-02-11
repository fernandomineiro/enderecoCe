package moduloEndereco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import moduloEndereco.model.CorreioStatus;

@Repository
public interface CorreioStatusRepository extends JpaRepository<CorreioStatus,Long> {

	CorreioStatus  findFirstByOrderByIdDesc();
}
