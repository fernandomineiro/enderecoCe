package moduloEndereco.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import moduloEndereco.model.LogCpc;
import moduloEndereco.service.dto.LogCpcDTO;

@Mapper(componentModel = "spring")
public interface LogCpcMapper extends EntityMapper<LogCpcDTO, LogCpc> {
	
	@Mappings({ @Mapping(source = "localidade", target = "numeroLocalidade.localidade")   })
	LogCpc toEntity(LogCpcDTO logCpcDTO);

	@Mappings({ @Mapping(source = "numeroLocalidade.localidade", target = "localidade")})
	LogCpcDTO toDto(LogCpc logCpc);


}
