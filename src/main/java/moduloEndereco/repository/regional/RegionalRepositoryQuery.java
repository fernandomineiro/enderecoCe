package moduloEndereco.repository.regional;

import org.springframework.data.domain.Pageable;

import moduloEndereco.repository.filter.RegionalFilter;
import moduloEndereco.service.dto.RegionalWrapperDTO;

public interface RegionalRepositoryQuery {

	public RegionalWrapperDTO filtrar(RegionalFilter regionalFilter, Pageable pageable);
}
