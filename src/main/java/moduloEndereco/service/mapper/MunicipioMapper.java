package moduloEndereco.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import moduloEndereco.model.MunicipioLocalidade;
import moduloEndereco.service.dto.MunicipioDTO;

@Mapper(componentModel = "spring")
public interface MunicipioMapper extends EntityMapper<MunicipioDTO, MunicipioLocalidade> {

	@Mappings({ @Mapping(source = "regional.codRegional", target = "cdRegional")})
	MunicipioDTO toDto(MunicipioLocalidade municipioLocalidade);
}
