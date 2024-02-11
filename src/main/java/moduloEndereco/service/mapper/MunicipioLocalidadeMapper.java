package moduloEndereco.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import moduloEndereco.model.MunicipioLocalidade;
import moduloEndereco.service.dto.MunicipioLocalidadeDTO;

@Mapper(componentModel = "spring")
public interface MunicipioLocalidadeMapper extends EntityMapper<MunicipioLocalidadeDTO, MunicipioLocalidade> {

	@Mappings({ @Mapping(source = "cdRegional", target = "regional.codRegional") })
	MunicipioLocalidade toEntity(MunicipioLocalidadeDTO municipioLocalidadeDTO);

	@Mappings({ @Mapping(source = "regional.codRegional", target = "cdRegional") })
	MunicipioLocalidadeDTO toDto(MunicipioLocalidade municipioLocalidade);
}
