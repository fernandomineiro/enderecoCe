package moduloEndereco.service.mapper;

import org.mapstruct.Mapper;

import moduloEndereco.model.AreaAtuacao;
import moduloEndereco.service.dto.AreaAtuacaoDTO;

@Mapper(componentModel = "spring")
public interface AreaAtuacaoMapper  extends EntityMapper<AreaAtuacaoDTO, AreaAtuacao>  {

}
