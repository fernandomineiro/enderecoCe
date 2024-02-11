package moduloEndereco.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import moduloEndereco.model.Bairro;
import moduloEndereco.model.IdBairroIdLocalidade;



@Repository
public interface BairroRepository extends JpaRepository<Bairro,IdBairroIdLocalidade>,BairroRepositoryQuery {

  Bairro findFirstByOrderByIdDesc();
  
  Bairro findByIdBairroIdLocalidadeAndDataHoraExclusao(IdBairroIdLocalidade idBairroIdLocalidade,Date date);
  
  List<Bairro> findByIdBairroIdLocalidade_CdLocalidadeAndDataHoraExclusaoIsNullOrderByNomeBairro(Short cdLocalidade);
  
  List<Bairro> findByIdBairroIdLocalidadeIn(List<IdBairroIdLocalidade> listIdBairroIdLocalidade);
  
  List<Bairro> findByDataHoraExclusaoIsNullOrderByNomeBairro();
  
  List<Bairro> findAllByOrderByNomeBairroAsc();
}
