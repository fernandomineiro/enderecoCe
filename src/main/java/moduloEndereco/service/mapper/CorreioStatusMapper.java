package moduloEndereco.service.mapper;

import org.mapstruct.Mapper;

import moduloEndereco.model.CorreioStatus;
import moduloEndereco.service.dto.CorreioStatusDTO;

@Mapper(componentModel = "spring")
public interface CorreioStatusMapper  extends EntityMapper<CorreioStatusDTO, CorreioStatus> {

}
