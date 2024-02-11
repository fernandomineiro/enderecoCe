package moduloEndereco.service.mapper;

import org.mapstruct.Mapper;

import moduloEndereco.model.TipoLogradouro;
import moduloEndereco.service.dto.TipoLogradouroDTO;

@Mapper(componentModel = "spring")
public interface  TipoLogradouroMapper  extends EntityMapper<TipoLogradouroDTO, TipoLogradouro> {

		
		TipoLogradouro toEntity(TipoLogradouroDTO tipoLogradouroDTO);

}
