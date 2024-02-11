package moduloEndereco.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import moduloEndereco.model.LogBairro;
import moduloEndereco.model.LogLocalidade;
import moduloEndereco.repository.LogBairroRepository;
import moduloEndereco.service.LogBairroService;


@Service
public class LogBairroServiceImpl implements LogBairroService{

	@Autowired
	private LogBairroRepository logBairroRepository;
	
	@Override
	public void salvar(List<String> listBairro) {
		for(String bairro: listBairro) {
			LogBairro logBairro = new LogBairro();
			String campos[]=bairro.split("@");
			logBairro.setNumeroBairro(Integer.parseInt(campos[0]));
			logBairro.setUf(campos[1]);
			LogLocalidade numeroLocalidade = new LogLocalidade();
			numeroLocalidade.setNumeroLocalidade(Integer.parseInt(campos[2]));
			logBairro.setNumeroLocalidade(numeroLocalidade);
			logBairro.setBairro(campos[3]);
			if(bairro.endsWith("@"))
				logBairro.setAbreviacaoBairro(null);
			else
				logBairro.setAbreviacaoBairro(campos[4]);			
			logBairroRepository.save(logBairro);
			
			
		}
		
		
	}

}
