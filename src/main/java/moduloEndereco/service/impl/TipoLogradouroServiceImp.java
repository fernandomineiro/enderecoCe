package moduloEndereco.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import moduloEndereco.repository.TipoLogradouroRepository;
import moduloEndereco.service.TipoLogradouroService;
import moduloEndereco.service.dto.TipoLogradouroDTO;
import moduloEndereco.service.mapper.TipoLogradouroMapper;

@Service
@Transactional
public class TipoLogradouroServiceImp implements TipoLogradouroService {

	@Autowired
	private TipoLogradouroRepository tipoLogradouroRepository;

	@Autowired
	private TipoLogradouroMapper tipoLogradouroMapper;

	@Override
	public List<TipoLogradouroDTO> buscarTipoLogradouro() {
		return tipoLogradouroMapper.toDto(tipoLogradouroRepository.findAllByOrderByNome());
	}

}
