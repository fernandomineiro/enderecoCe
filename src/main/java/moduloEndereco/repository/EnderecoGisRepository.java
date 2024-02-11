package moduloEndereco.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import moduloEndereco.model.EnderecoGis;

@Repository
public interface EnderecoGisRepository extends JpaRepository<EnderecoGis,Integer>, EnderecoGisRepositoryQuery  {
    List<EnderecoGis> findBySituacao(short situacao);
    
    List<EnderecoGis> findByIdIn(List<Integer> ids);
}
