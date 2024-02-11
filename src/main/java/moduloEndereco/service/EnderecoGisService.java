package moduloEndereco.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import moduloEndereco.repository.filter.EnderecoGisFilter;
import moduloEndereco.service.dto.CriticaEnderecoDTO;
import moduloEndereco.service.dto.EnderecoGisDTO;
import moduloEndereco.service.dto.EnderecoGisDTOWrapper;
import moduloEndereco.service.dto.EnderecoGisIdsDTO;

public interface EnderecoGisService {

	public EnderecoGisDTOWrapper buscarEnderecosAlterado(Pageable pageable);

	public List<EnderecoGisDTO> buscarEnderecosAlterado(List<Integer> ids);

	public List<CriticaEnderecoDTO> validarAtualizacao(List<Integer> ids);

	public void atualizarEndereco(EnderecoGisIdsDTO enderecoGisIdsDTO, String token);

	public List<EnderecoGisDTO> filtrar(EnderecoGisFilter enderecoGisFilter);

}
