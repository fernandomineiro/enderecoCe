package moduloEndereco.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import moduloEndereco.model.LogGrandeUsuario;
import moduloEndereco.service.dto.LogGrandeUsuarioDTO;

@Mapper(componentModel = "spring")
public interface LogGrandeUsuarioMapper extends EntityMapper<LogGrandeUsuarioDTO, LogGrandeUsuario> {
	
	@Mappings({ @Mapping(source = "localidade", target = "numeroLocalidade.localidade") })
	LogGrandeUsuario toEntity(LogGrandeUsuarioDTO logGrandeUsuarioDTO);

	@Mappings({ @Mapping(source = "numeroLocalidade.localidade", target = "localidade") })
	LogGrandeUsuarioDTO toDto(LogGrandeUsuario logGrandeUsuario);

}
