package moduloEndereco.service.mapper;

import org.mapstruct.Mapper;

import moduloEndereco.model.ServicoAtendimento;
import moduloEndereco.service.dto.ImovelEnderecoDTO;

@Mapper(componentModel = "spring")
public interface ImovelEnderecoMapper  extends EntityMapper<ImovelEnderecoDTO, ServicoAtendimento> {

}
