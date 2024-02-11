package moduloEndereco.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import moduloEndereco.model.LogLocalidade;
import moduloEndereco.repository.LogLocalidadeRepository;
import moduloEndereco.service.LogLocalidadeService;
import moduloEndereco.service.dto.LogLocalidadeDTO;
import moduloEndereco.service.mapper.LogLocalidadeMapper;


@Service
public class LogLocalidadeServiceImpl implements LogLocalidadeService {

	@Autowired
	private LogLocalidadeRepository logLocalidadeRepository;
	@Autowired
	private LogLocalidadeMapper logLocalidadeMapper;
	
	@Override
	public void salvar(List<String> listLocalidade) {
		for(String localidade:listLocalidade) {
			LogLocalidade logLocalidade = new LogLocalidade();
			String campos[] = localidade.split("@");
			logLocalidade.setNumeroLocalidade(Integer.parseInt(campos[0]));
			logLocalidade.setUf(campos[1]);
			logLocalidade.setLocalidade(campos[2]);
			if(campos[3].isEmpty())
				logLocalidade.setCep(null);
			else
				logLocalidade.setCep(campos[3]);
			logLocalidade.setLocInSit(campos[4]);
			logLocalidade.setLocInTipoLoc(campos[5]);
			if(campos[6].isEmpty())
				logLocalidade.setLocNuSub(null);
			else
				logLocalidade.setLocNuSub(Integer.parseInt(campos[6]));
			if(campos[7].isEmpty())
				logLocalidade.setLocalidadeAbreviada(null);
			else
				logLocalidade.setLocalidadeAbreviada(campos[7]);
			if(localidade.endsWith("@"))
				logLocalidade.setNumeroMunicipio(null);
			else
				logLocalidade.setNumeroMunicipio(campos[8]);
			logLocalidadeRepository.save(logLocalidade);
		}
		
	}

	@Override
	public List<LogLocalidadeDTO> buscarPorCep(String cep) {
		return logLocalidadeMapper.toDto(logLocalidadeRepository.findByCep(cep));
	}

}
