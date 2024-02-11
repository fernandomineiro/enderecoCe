package moduloEndereco.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import moduloEndereco.model.LogLogradouro;
import moduloEndereco.service.dto.LogLogradouroCepDTO;

@Mapper(componentModel = "spring")
public interface LogLogradouroCepMapper extends EntityMapper<LogLogradouroCepDTO, LogLogradouro> {

	@Mappings({ @Mapping(source = "localidade", target = "numeroLocalidade.localidade"),
		@Mapping(source = "siglaLogradouro", target = "tloTx")     })
	LogLogradouro toEntity(LogLogradouroCepDTO logLogradouroCepDTO);

	@Mappings({ @Mapping(source = "numeroLocalidade.localidade", target = "localidade"),
	@Mapping(source = "tloTx", target = "siglaLogradouro")})
	LogLogradouroCepDTO toDto(LogLogradouro logLogradouro);

}
