package moduloEndereco.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import moduloEndereco.model.AreaAtuacao;

@Repository
public interface AreaAtuacaoRepository extends JpaRepository<AreaAtuacao,Long>{

	List<AreaAtuacao> findByNomeIgnoreCaseContainingAndStatusReg(String nome,String statusReg,Pageable pageable);
	
	int countByNomeIgnoreCaseContainingAndStatusReg(String nome,String statusReg);
	
	List<AreaAtuacao> findByStatusReg(String statusReg,Pageable pageable);
	
	int countByStatusReg(String statusReg);
	
	AreaAtuacao findByNomeAndStatusReg(String nome,String statusReg);
	
	
	
}
