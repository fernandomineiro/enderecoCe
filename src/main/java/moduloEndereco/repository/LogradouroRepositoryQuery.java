package moduloEndereco.repository;

import org.springframework.data.domain.Pageable;

import moduloEndereco.repository.filter.LogradouroFilter;
import moduloEndereco.service.dto.LogradouroWrapperDTO;

public interface LogradouroRepositoryQuery {

	public LogradouroWrapperDTO filtrar(LogradouroFilter logradouroFilter, Pageable pageable);
	
	public LogradouroWrapperDTO filtrar(LogradouroFilter logradouroFilter);
}
