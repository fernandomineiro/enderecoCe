package moduloEndereco.service.mapper;

import org.mapstruct.Mapper;

import moduloEndereco.model.HorarioAtendimento;
import moduloEndereco.service.dto.HorarioAtendimentoDTO;

@Mapper(componentModel = "spring")
public interface HorarioAtendimentoMapper extends EntityMapper<HorarioAtendimentoDTO, HorarioAtendimento> {

}
