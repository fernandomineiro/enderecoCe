package moduloEndereco.repository;


import org.springframework.data.domain.Pageable;

import moduloEndereco.repository.filter.BairroFilter;
import moduloEndereco.service.dto.BairroWrapperDTO;

public interface BairroRepositoryQuery {

	public BairroWrapperDTO filtrar(BairroFilter bairroFilter, Pageable pageable);
	
	public BairroWrapperDTO filtrar(BairroFilter bairroFilter);
}
