package moduloEndereco.service.mapper;

import org.mapstruct.Mapper;

import moduloEndereco.model.MunicipioLocalidade;
import moduloEndereco.service.dto.MunicipioLocalidadeRetornoDTO;

@Mapper(componentModel = "spring")
public interface MunicipioLocalidadeRetornoMapper extends EntityMapper<MunicipioLocalidadeRetornoDTO, MunicipioLocalidade>{

}
