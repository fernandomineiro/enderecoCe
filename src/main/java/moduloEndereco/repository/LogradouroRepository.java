package moduloEndereco.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import moduloEndereco.model.IdLogradouroIdLocalidade;
import moduloEndereco.model.Logradouro;

@Repository
public interface LogradouroRepository
		extends JpaRepository<Logradouro, IdLogradouroIdLocalidade>, LogradouroRepositoryQuery {

	List<Logradouro> findAllByOrderByNomeLogradouro();

	Logradouro findFirstByOrderByIdDesc();

	Logradouro findByIdLogradouroIdLocalidadeAndDataHoraExclusao(IdLogradouroIdLocalidade idLogradouroIdLocalidade,
			Date date);
	
	List<Logradouro> findByIdLogradouroIdLocalidadeInAndDataHoraExclusaoIsNull(List<IdLogradouroIdLocalidade> listIdLogradouroIdLocalidade);

	List<Logradouro> findByIdLogradouroIdLocalidade_CodLocalidadeAndDataHoraExclusaoIsNull(Short cdLocalidade);
}
