package moduloEndereco.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import moduloEndereco.service.dto.MunicipioLocalidadeWrapper;

@Repository
public interface MunicipioLocalidadeRepositoryQuery extends JpaRepository<MunicipioLocalidadeWrapper,Short> {

	@Query(nativeQuery = true, value = 
			"select distinct " + 
			"m.CD_CIDADE cd_Municipio, " + 
			"m.DC_CIDADE municipio, " + 
			"l.CD_CIDADE cd_Localidade, " + 
			"L.DC_CIDADE localidade, " + 
			"r.DC_REGIONAL regional, " + 
			"l.TEM_AGUA, " + 
			"l.TEM_DISP_ESGOTO, " + 
			"l.TEM_ESGOTO, " + 
			"l.ENDERECO_ATEND endereco_atendimento, " + 
			"h.DESCRICAO horario_atendimento, " + 
			"l.EMAIL " + 
			"from CDACI m " + 
			"inner join CDACI l on m.CD_CIDADE = l.CD_MUNICIPIO " + 
			"inner join CDARE r on r.CD_REGIONAL = iif(l.CD_CIDADE = l.CD_MUNICIPIO, m.cd_regional, l.cd_regional) " + 
			"inner join HORARIO_ATENDIMENTO h on h.CD_HORARIO_ATENDIMENTO = l.horario_atend " + 
			"where m.CD_CIDADE = m.CD_MUNICIPIO " + 
			"and (:cdCidade is null or m.CD_CIDADE = :cdCidade or l.CD_CIDADE = :cdCidade) " + 
			"and (:municipio is null or m.DC_CIDADE like :municipio) " + 
			"and (:localidade is null or l.DC_CIDADE like :localidade) " + 
			"and (:regional is null or r.DC_REGIONAL like :regional) " + 
			"and (:cdTarifa is null or m.CD_TARIFA = :cdTarifa and l.CD_CIDADE = l.CD_MUNICIPIO or l.CD_TARIFA = :cdTarifa) " + 
			"and (:temAgua is null or m.TEM_AGUA = :temAgua and l.CD_CIDADE = l.CD_MUNICIPIO or l.TEM_AGUA = :temAgua) " + 
			"and (:temEsgoto is null or m.TEM_ESGOTO = :temEsgoto and l.CD_CIDADE = l.CD_MUNICIPIO or l.TEM_ESGOTO = :temEsgoto) " + 
			"and (:temDispEsgoto is null or m.TEM_DISP_ESGOTO = :temDispEsgoto and l.CD_CIDADE = l.CD_MUNICIPIO or l.TEM_DISP_ESGOTO = :temDispEsgoto) " + 
			"and (:tipo is null or :tipo = 'M' and m.CD_CIDADE = l.CD_CIDADE or :tipo = 'L' and l.CD_CIDADE <> l.CD_MUNICIPIO) " +
			"and (m.DATA_HORA_EXCLUSAO is null and m.CD_CIDADE = l.CD_CIDADE or l.DATA_HORA_EXCLUSAO is null) ")
	List<MunicipioLocalidadeWrapper> pesquisarMunicipioLocalidade(
			@Param("cdCidade") Short cdCidade,
			@Param("municipio") String municipio,
			@Param("localidade") String localidade,
			@Param("regional") String regional,
			@Param("cdTarifa") Short cdTarifa,
			@Param("temAgua") String temAgua,
			@Param("temEsgoto") String temEsgoto,
			@Param("temDispEsgoto") String temDispEsgoto,
			@Param("tipo") String tipo
			);
}
