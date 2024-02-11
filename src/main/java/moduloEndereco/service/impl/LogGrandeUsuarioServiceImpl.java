package moduloEndereco.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import moduloEndereco.model.LogGrandeUsuario;
import moduloEndereco.model.LogLocalidade;
import moduloEndereco.model.LogLogradouro;
import moduloEndereco.repository.LogGrandeUsuarioRepository;
import moduloEndereco.repository.LogLogradouroRepository;
import moduloEndereco.service.LogGrandeUsuarioService;
import moduloEndereco.service.dto.LogGrandeUsuarioDTO;
import moduloEndereco.service.mapper.LogGrandeUsuarioMapper;

@Service
public class LogGrandeUsuarioServiceImpl implements LogGrandeUsuarioService {

	@Autowired
	private LogGrandeUsuarioRepository logGrandeUsuarioRepository;
	@Autowired
	private LogLogradouroRepository logLogradouroRepository;

	@Autowired
	private LogGrandeUsuarioMapper logGrandeUsuarioMapper;

	@Override
	public void salvar(List<String> listGrandeUsuario) {
		for (String grandeUsuario : listGrandeUsuario) {
			LogGrandeUsuario logGrandeUsuario = new LogGrandeUsuario();
			String campos[] = grandeUsuario.split("@");
			logGrandeUsuario.setNumeroGRU(Integer.parseInt(campos[0]));
			logGrandeUsuario.setUf(campos[1]);
			LogLocalidade logLocalidade = new LogLocalidade();
			logLocalidade.setNumeroLocalidade(Integer.parseInt(campos[2]));
			logGrandeUsuario.setNumeroLocalidade(logLocalidade);
			logGrandeUsuario.setNumeroBairro(Integer.parseInt(campos[3]));
			if (campos[4].isEmpty()) {
				logGrandeUsuario.setNumeroLogradouro(null);
			} else if (logLogradouroRepository.findById(Integer.parseInt(campos[4])).isEmpty()) {
				continue;
			} else {
				LogLogradouro logLogradouro = new LogLogradouro();
				logLogradouro.setNumeroLogradouro(Integer.parseInt(campos[4]));
				logGrandeUsuario.setNumeroLogradouro(logLogradouro);
			}
			logGrandeUsuario.setNomeGRU(campos[5]);
			logGrandeUsuario.setEnderecoGRU(campos[6]);
			logGrandeUsuario.setCep(campos[7]);
			if (campos[8].isEmpty())
				logGrandeUsuario.setNomeAbreviadoGRU(null);
			else
				logGrandeUsuario.setNomeAbreviadoGRU(campos[8]);
			logGrandeUsuarioRepository.save(logGrandeUsuario);

		}

	}

	@Override
	public List<LogGrandeUsuarioDTO> listarEnderecoPorCep(String cep) {

		List<LogGrandeUsuarioDTO> logGrandeUsuario = logGrandeUsuarioMapper.toDto(logGrandeUsuarioRepository.findByCep(cep));

		return logGrandeUsuario;

	}

}
