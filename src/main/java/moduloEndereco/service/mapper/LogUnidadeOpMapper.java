package moduloEndereco.service.mapper;

import org.mapstruct.Mapper;

import moduloEndereco.model.LogUnidadeOp;
import moduloEndereco.service.dto.LogUnidadeOpDTO;

@Mapper(componentModel = "spring")
public interface LogUnidadeOpMapper extends EntityMapper<LogUnidadeOpDTO, LogUnidadeOp>{
	

}
