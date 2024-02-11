package moduloEndereco.service.mapper;

import org.mapstruct.Mapper;

import moduloEndereco.model.EnderecoGis;
import moduloEndereco.service.dto.EnderecoGisDTO;

@Mapper(componentModel = "spring")
public interface EnderecoGisMapper extends EntityMapper<EnderecoGisDTO, EnderecoGis>{

}
