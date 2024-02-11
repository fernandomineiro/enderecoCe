package moduloEndereco.service.mapper;

import org.mapstruct.Mapper;

import moduloEndereco.model.ServicoAtendimento;
import moduloEndereco.service.dto.ServicoAtendimentoProcessamentoDTO;

@Mapper(componentModel = "spring")
public interface ServicoAtendimentoProcessamentoMapper extends EntityMapper<ServicoAtendimentoProcessamentoDTO, ServicoAtendimento> {

}
