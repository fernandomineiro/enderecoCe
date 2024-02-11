package moduloEndereco.repository;


import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import moduloEndereco.model.ServicoAtendimento;
import moduloEndereco.service.dto.ServicoAtendimentoProcessadoDTO;


@Repository
public interface ServicoAtendimentoRepository extends JpaRepository<ServicoAtendimento, Long>,ServicoAtendimentoRepositoryQuery{

	List<ServicoAtendimento> findBySituacao(short situacao,Pageable pageable);
	List<ServicoAtendimento> findBySituacao(short situacao);
	long countBySituacao(short situacao);
	
	@Query(value="select new moduloEndereco.service.dto.ServicoAtendimentoProcessadoDTO("
			+ "servAtend.cdAtendimento,"
			+ "servAtend.refAtendimento,"
			+ "servAtend.sequencial,"
			+ "servAtend.matriculaImovel, "
			+ "servAtend.dv, "
			+ "municipiolocalidade.dcCidade, "
			+ "servAtend.cdBairroAntigo, "
			+ "servAtend.descricaoBairroAntigo, "
			+ "servAtend.cdBairroNovo, "
			+ "servAtend.descricaoBairroNovo, "
			+ "servAtend.cdLogradouroAntigo, "
			+ "servAtend.descricaoLogradouroAntigo, "
			+ "servAtend.cdLogradouroNovo, "
			+ "servAtend.descricaoLogradouroNovo, "
			+ "servAtend.dataInclusao, "
			+ "servAtend.dataExcucao,"
			+ "servAtend.sequenciaInclusao,"
			+ "servAtend.situacao) "
			+ "from ServicoAtendimento servAtend "
			+ " join MunicipioLocalidade municipiolocalidade on municipiolocalidade.cdCidade = servAtend.cdLocalidade " 
		    + " where servAtend.situacao <> 3 ")
			// + "order by servAtend.sequenciaInclusao ") 
	List<ServicoAtendimentoProcessadoDTO> buscarServicoAtendimentoProcessado(
			Pageable pageable); 
	
	@Query(value="select count(*) "
			+ "from ServicoAtendimento servAtend "
			+ " join MunicipioLocalidade municipiolocalidade on municipiolocalidade.cdCidade = servAtend.cdLocalidade " 
		    + " where servAtend.situacao <> 3") 
	Long buscarServicoAtendimentoProcessado(); 
}
