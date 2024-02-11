package moduloEndereco.service.mapper;

import org.mapstruct.Mapper;

import moduloEndereco.model.LogLogradouro;
import moduloEndereco.service.dto.LogLogradouroDTO;

@Mapper(componentModel = "spring")
public interface LogLogradouroMapper extends EntityMapper<LogLogradouroDTO, LogLogradouro> {

}
