package moduloEndereco.service.mapper;

import org.mapstruct.Mapper;

import moduloEndereco.model.Regional;
import moduloEndereco.service.dto.RegionalDTO;



@Mapper(componentModel = "spring")
public interface RegionalMapper  extends EntityMapper<RegionalDTO, Regional> {

}
