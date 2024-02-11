package moduloEndereco.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import moduloEndereco.model.LogLocalidade;
import moduloEndereco.model.LogLogradouro;
import moduloEndereco.repository.LogBairroRepository;
import moduloEndereco.repository.LogLogradouroRepository;
import moduloEndereco.service.LogLogradouroService;
import moduloEndereco.service.dto.LogLogradouroCepDTO;
import moduloEndereco.service.mapper.LogLogradouroCepMapper;

@Service
public class LogLogradouroServiceImpl implements LogLogradouroService {

	@Autowired
	private LogLogradouroRepository logLogradouroRepository;

	@Autowired
	private LogBairroRepository logBairroRepository;

	@Autowired
	private LogLogradouroCepMapper logLogradouroCepMapper;

	@Override
	public void salvar(List<String> listLogradouro) {

		for (String logradouro : listLogradouro) {
			LogLogradouro logLogradouro = new LogLogradouro();
			String campos[] = logradouro.split("@");
			logLogradouro.setNumeroLogradouro(Integer.parseInt(campos[0]));
			logLogradouro.setUf(campos[1]);
			LogLocalidade logLocalidade = new LogLocalidade();
			logLocalidade.setNumeroLocalidade(Integer.parseInt(campos[2]));
			logLogradouro.setNumeroLocalidade(logLocalidade);
			logLogradouro.setBaiNuIni(Integer.parseInt(campos[3]));
			if (campos[4].isEmpty())
				logLogradouro.setBaiNuFim(null);
			else
				logLogradouro.setBaiNuFim(Integer.parseInt(campos[4]));
			logLogradouro.setLogradouro(campos[5]);
			if (campos[6].isEmpty())
				logLogradouro.setComplementoLogradouro(null);
			else
				logLogradouro.setComplementoLogradouro(campos[6]);
			logLogradouro.setCep(campos[7]);
			logLogradouro.setTloTx(campos[8]);
			if (campos[9].isEmpty())
				logLogradouro.setLogStaTlo(null);
			else
				logLogradouro.setLogStaTlo(campos[9]);
			if (campos[10].isEmpty())
				logLogradouro.setLogradouroAbreviado(null);
			else
				logLogradouro.setLogradouroAbreviado(campos[10]);
			logLogradouroRepository.save(logLogradouro);

		}

	}

	@Override
	public List<LogLogradouroCepDTO> listarEnderecoPorCep(String cep) {

		List<LogLogradouroCepDTO> listLogLogradouroCep = logLogradouroCepMapper.toDto(logLogradouroRepository.findByCep(cep));
		if (!listLogLogradouroCep.isEmpty()) {
			for(LogLogradouroCepDTO logLogradouroCepDTO:listLogLogradouroCep)
				logLogradouroCepDTO.setBairro(logBairroRepository.findByNumeroBairro(logLogradouroCepDTO.getBaiNuIni()).getBairro());

		}

		return listLogLogradouroCep;

	}

}
