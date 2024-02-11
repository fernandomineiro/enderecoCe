package moduloEndereco.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import moduloEndereco.model.Bairro;
import moduloEndereco.service.dto.BairroDTO;

@Mapper(componentModel = "spring")
public interface BairroMapper extends EntityMapper<BairroDTO, Bairro> {

	@Mappings({ @Mapping(source = "cdBairro", target = "idBairroIdLocalidade.cdBairro"),
			@Mapping(source = "cdLocalidade", target = "idBairroIdLocalidade.cdLocalidade") })
	Bairro toEntity(BairroDTO bairroDTO);

	@Mappings({ @Mapping(source = "idBairroIdLocalidade.cdBairro", target = "cdBairro"),
			@Mapping(source = "idBairroIdLocalidade.cdLocalidade", target = "cdLocalidade") })
	BairroDTO toDto(Bairro bairro);
}
