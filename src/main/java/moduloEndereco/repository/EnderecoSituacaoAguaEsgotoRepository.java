package moduloEndereco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import moduloEndereco.service.dto.EnderecoSituacaoAguaEsgotoDTO;

@Repository
public interface EnderecoSituacaoAguaEsgotoRepository extends JpaRepository<EnderecoSituacaoAguaEsgotoDTO, Integer>{

	@Query(nativeQuery = true, value = 
			"select		CDALO.SIGLA_LOGRADOURO, " + 
			"			CDALO.DC_LOGRADOURO, " + 
			"			CDAIM.NUM_ENDERECO, " + 
			"			rtrim(CDABA.DC_BAIRRO) DC_BAIRRO, " + 
			"			CDACI.DC_CIDADE, " + 
			"			agua.DC_DESCRICAO SIT_LIGACAO_AGUA, " + 
			"			esgoto.DC_DESCRICAO SIT_LIGACAO_ESGOTO, " + 
			"			CDAIM.MATRICULA_IMOVEL, " + 
			"			CDAIM.CD_CLIENTE, " + 
			"			titular.NOME NOME_TITULAR, " + 
			"			proprietario.NOME NOME_PROPRIETARIO " + 
			"from		CDAIM " + 
			"inner join	CDALO on CDAIM.CD_CIDADE = CDALO.CD_CIDADE and CDAIM.CD_LOGRADOURO = CDALO.CD_LOGRADOURO " + 
			"inner join	CDABA on CDABA.CD_BAIRRO = CDAIM.CD_BAIRRO and CDABA.CD_CIDADE = CDAIM.CD_CIDADE " + 
			"inner join	CDACI on CDACI.CD_CIDADE = CDAIM.CD_CIDADE " + 
			"inner join	CAD_SIT_LIGACAO_AGUA agua on agua.ID_SIT_LIGACAO_AGUA = CDAIM.SIT_LIGACAO_AGUA  " + 
			"inner join	CAD_SIT_LIGACAO_ESGOTO esgoto on esgoto.ID_SIT_LIGACAO_ESGOTO = CDAIM.SIT_LIGACAO_ESGOTO " + 
			"inner join	CDACL titular on titular.CD_CLIENTE = CDAIM.CD_CLIENTE " + 
			"inner join	CDACL proprietario on proprietario.CD_CLIENTE = CDAIM.CD_CLIENTE_PROP " + 
			"where		CDAIM.MATRICULA_IMOVEL = :matriculaImovel " +
			"and		CDAIM.DV = :dv ")
	EnderecoSituacaoAguaEsgotoDTO pesquisarMunicipioLocalidade(Integer matriculaImovel, Integer dv);
	
	@Query(nativeQuery = true, value = 
			"select		CDALO.SIGLA_LOGRADOURO, " + 
			"			CDALO.DC_LOGRADOURO, " + 
			"			:numEndereco NUM_ENDERECO, " + 
			"			rtrim(CDABA.DC_BAIRRO) DC_BAIRRO, " + 
			"			CDACI.DC_CIDADE, " + 
			"			agua.DC_DESCRICAO SIT_LIGACAO_AGUA, " + 
			"			esgoto.DC_DESCRICAO SIT_LIGACAO_ESGOTO, " + 
			"			:matriculaImovel MATRICULA_IMOVEL, " + 
			"			:cdClienteProp CD_CLIENTE, " + 
			"			titular.NOME NOME_TITULAR, " + 
			"			proprietario.NOME NOME_PROPRIETARIO " + 
			"from		CDALO " + 
			"inner join	CDABA on CDABA.CD_CIDADE = CDALO.CD_CIDADE and CDABA.CD_BAIRRO = :cdBairro " + 
			"inner join	CDACI on CDACI.CD_CIDADE = :cdCidade " + 
			"inner join	CAD_SIT_LIGACAO_AGUA agua on agua.ID_SIT_LIGACAO_AGUA = :sitLigacaoAgua  " + 
			"inner join	CAD_SIT_LIGACAO_ESGOTO esgoto on esgoto.ID_SIT_LIGACAO_ESGOTO = :sitLigacaoEsgoto " + 
			"inner join	CDACL titular on titular.CD_CLIENTE = :cdCliente " + 
			"inner join	CDACL proprietario on proprietario.CD_CLIENTE = :cdClienteProp " + 
			"where		CDALO.CD_LOGRADOURO = :cdLogradouro " +
			"and		CDALO.CD_CIDADE = :cdCidade ")
	EnderecoSituacaoAguaEsgotoDTO pesquisarEndereco(
			Integer numEndereco, Integer matriculaImovel, Integer cdClienteProp, 
			Integer cdBairro, Integer sitLigacaoAgua, Integer sitLigacaoEsgoto,
			Integer cdCliente, Integer cdLogradouro, Integer cdCidade);
}
