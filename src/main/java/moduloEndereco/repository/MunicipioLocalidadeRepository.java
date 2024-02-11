package moduloEndereco.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import moduloEndereco.model.MunicipioLocalidade;
import moduloEndereco.model.Regional;

@Repository
public interface MunicipioLocalidadeRepository extends JpaRepository<MunicipioLocalidade,Short> {

	@Query("select ml "
			+ "from MunicipioLocalidade ml "
			+ "where ml.cdCidade = ml.cdMunicipio "
			+ "and ml.dataHoraExclusao is null order by ml.dcCidade ")
	List<MunicipioLocalidade> listarTodosMunicipiosAtivos();
	
	@Query("select ml "
			+ "from MunicipioLocalidade ml "
			+ "where ml.cdCidade = :id "
			+ "and ml.dataHoraExclusao is null")
	MunicipioLocalidade pesquisarAtivoPorId(Short id);
	
	List<MunicipioLocalidade> findAllByDataHoraExclusaoIsNullOrderByDcCidade(); 
	
	List<MunicipioLocalidade> findAllByRegionalAndDataHoraExclusaoIsNull(Regional regional);
	
	List<MunicipioLocalidade> findByCdCidadeIn(List<Short> ids);
	

	List<MunicipioLocalidade> findByDcCidadeIn(List<String> list);
	
	@Query("select ml "
			+ "from MunicipioLocalidade ml "
			+ "where ml.cdCidade <> ml.cdMunicipio "
			+ "order by ml.dcCidade ")
	List<MunicipioLocalidade> listarTodasLocalidades();
	
	@Query("select ml "
			+ "from MunicipioLocalidade ml "
		    + "where ml.dataHoraExclusao is null "
			+ "order by ml.dcCidade ")
	List<MunicipioLocalidade> listarMunicipiosLocalidades();
	
}
