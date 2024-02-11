package moduloEndereco.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import moduloEndereco.model.Regional;
import moduloEndereco.repository.regional.RegionalRepositoryQuery;

public interface RegionalRepository extends JpaRepository<Regional, Integer>,RegionalRepositoryQuery {

	Regional findByNomeRegionalAndDataRemocaoIsNull(String nome);
	Regional findByCodRegional(Integer cod);
	Regional findByCodRegionalAndStatus(Integer cod, String status);
	List<Regional> findAllByDataRemocaoIsNullOrderByNomeRegional();
}
