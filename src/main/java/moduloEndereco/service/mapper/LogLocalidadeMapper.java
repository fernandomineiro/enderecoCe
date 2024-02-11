package moduloEndereco.service.mapper;

import org.mapstruct.Mapper;

import moduloEndereco.model.LogLocalidade;
import moduloEndereco.service.dto.LogLocalidadeDTO;

@Mapper(componentModel = "spring")
public interface LogLocalidadeMapper extends EntityMapper<LogLocalidadeDTO, LogLocalidade>{

}
