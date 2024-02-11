package moduloEndereco.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import moduloEndereco.model.IdLogFaixaUf;
import moduloEndereco.model.LogFaixaUf;
import moduloEndereco.repository.LogFaixaUfRepository;
import moduloEndereco.service.LogFaixaUfService;


@Service
public class LogFaixaUfServiceImpl implements LogFaixaUfService {
 
	@Autowired
	private LogFaixaUfRepository logFaixaUfRepository;
	
	@Override
	public void salvar(List<String> listUf) {
		for(String uf:listUf) {
			LogFaixaUf logFaixaUf = new LogFaixaUf();
			String campos[] = uf.split("@");
		    logFaixaUf.setIdLogFaixaUf(new IdLogFaixaUf(campos[0],campos[1]));
		    logFaixaUf.setUfCepFim(campos[2]);
		    logFaixaUfRepository.save(logFaixaUf);
			
		}
		
	}

}
