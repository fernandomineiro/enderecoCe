package moduloEndereco.service.mapper;

import org.mapstruct.Mapper;

import moduloEndereco.model.ServicoAtendimento;
import moduloEndereco.service.dto.ServicoAtendimentoGisDTO;

@Mapper(componentModel = "spring")
public interface ServicoAtendimentoGisMapper extends EntityMapper<ServicoAtendimentoGisDTO, ServicoAtendimento> {

}
