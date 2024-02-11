package moduloEndereco.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import moduloEndereco.model.AreaAtuacaoUsuario;
import moduloEndereco.service.dto.AreaAtuacaoUsuarioDTO;

@Mapper(componentModel = "spring")
public interface AreaAtuacaoUsuarioMapper extends EntityMapper<AreaAtuacaoUsuarioDTO, AreaAtuacaoUsuario>{

	@Mappings({ @Mapping(source = "idAreaAtuacao", target = "areaAtuacao.id")})
	AreaAtuacaoUsuario toEntity(AreaAtuacaoUsuarioDTO areaAtuacaoUsuarioDTO);
}
