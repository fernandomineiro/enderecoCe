package moduloEndereco.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import moduloEndereco.model.LogCpc;
import moduloEndereco.model.LogLocalidade;
import moduloEndereco.repository.LogCpcRepository;
import moduloEndereco.service.LogCpcService;
import moduloEndereco.service.dto.LogCpcDTO;
import moduloEndereco.service.mapper.LogCpcMapper;

@Service
public class LogCpcServiceImpl implements LogCpcService {

	@Autowired
	private LogCpcRepository logCpcRepository;

	@Autowired
	private LogCpcMapper logCpcMapper;

	@Override
	public void salvar(List<String> listCpc) {
		for (String cpc : listCpc) {
			String campos[] = cpc.split("@");
			LogCpc logCpc = new LogCpc();
			logCpc.setNumeroCpc(Integer.parseInt(campos[0]));
			logCpc.setUf(campos[1]);
			LogLocalidade logLocalidade = new LogLocalidade();
			logLocalidade.setNumeroLocalidade(Integer.parseInt(campos[2]));
			logCpc.setNumeroLocalidade(logLocalidade);
			logCpc.setNomeCpc(campos[3]);
			logCpc.setEnderecoCpc(campos[4]);
			logCpc.setCep(campos[5]);
			logCpcRepository.save(logCpc);

		}

	}

	@Override
	public List<LogCpcDTO> listarEnderecoPorCep(String cep) {

		List<LogCpcDTO> logCpc = logCpcMapper.toDto(logCpcRepository.findByCep(cep));

		return logCpc;

	}

}
