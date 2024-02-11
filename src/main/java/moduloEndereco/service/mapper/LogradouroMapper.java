package moduloEndereco.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import moduloEndereco.model.Logradouro;
import moduloEndereco.service.dto.LogradouroDTO;

@Mapper(componentModel = "spring")
public interface LogradouroMapper extends EntityMapper<LogradouroDTO, Logradouro> {

	@Mappings({ @Mapping(source = "codLogradouro", target = "idLogradouroIdLocalidade.codLogradouro"),
			@Mapping(source = "codLocalidade", target = "idLogradouroIdLocalidade.codLocalidade"),
			@Mapping(source = "idTipoLogradouro", target = "tipoLogradouro.id")})
	Logradouro toEntity(LogradouroDTO logradouroDTO);

	@Mappings({ @Mapping(source = "idLogradouroIdLocalidade.codLogradouro", target = "codLogradouro"),
			@Mapping(source = "idLogradouroIdLocalidade.codLocalidade", target = "codLocalidade"),
			@Mapping(source = "tipoLogradouro.id", target = "idTipoLogradouro")})
	LogradouroDTO toDto(Logradouro logradouro);
}


