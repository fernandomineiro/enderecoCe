package moduloEndereco.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import moduloEndereco.model.BairroLogradouro;

public interface BairroLogradouroRepository  extends JpaRepository<BairroLogradouro,Integer>{

	List<BairroLogradouro> findByCodLocalidadeAndCodLogradouro(Short codLocalidade, Short codLogradouro);
	
	List<BairroLogradouro> findByCodBairroIn(List<Short> idsBairro);
	
	List<BairroLogradouro> findByCodLogradouro(Short codLogradouro);
	
	List<BairroLogradouro> findByCodBairroAndCodLocalidade(Short idBairro,Short idLocalidade);
	
	
	
}
