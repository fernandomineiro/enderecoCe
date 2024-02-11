package moduloEndereco.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import moduloEndereco.model.IdLogVarBai;
import moduloEndereco.model.LogVarBai;

@Repository
public interface LogVarBaiRepository extends JpaRepository<LogVarBai,IdLogVarBai>  {
	
	List<LogVarBai> findByIdLogVarBai_baiNu(Integer idBairro);

}
