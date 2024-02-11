package moduloEndereco.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import moduloEndereco.model.Bairro;
import moduloEndereco.service.dto.BairroDropDownDTO;

@Mapper(componentModel = "spring")
public interface BairroDropDownMapper extends EntityMapper<BairroDropDownDTO, Bairro> {

	@Mappings({ @Mapping(source = "cdBairro", target = "idBairroIdLocalidade.cdBairro"),
			@Mapping(source = "cdLocalidade", target = "idBairroIdLocalidade.cdLocalidade") })
	Bairro toEntity(BairroDropDownDTO bairroDTO);

	@Mappings({ @Mapping(source = "idBairroIdLocalidade.cdBairro", target = "cdBairro"),
			@Mapping(source = "idBairroIdLocalidade.cdLocalidade", target = "cdLocalidade") })
	BairroDropDownDTO toDto(Bairro bairro);
}
