package moduloEndereco.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import moduloEndereco.model.IdLogFaixaBairro;
import moduloEndereco.model.LogFaixaBairro;
import moduloEndereco.repository.LogFaixaBairroRepository;
import moduloEndereco.service.LogFaixaBairroService;


@Service
public class LogFaixaBairroServiceImpl implements LogFaixaBairroService {

	@Autowired
	private LogFaixaBairroRepository logFaixaBairroRepository;
	@Override
	public void salvar(List<String> listFaixaBairro) {
		int count=0;
		for(String faixaBairro : listFaixaBairro) {
			count++;
			LogFaixaBairro logFaixaBairro = new LogFaixaBairro();
			String campos[]=faixaBairro.split("@");
			logFaixaBairro.setIdLogFaixaBairro(new IdLogFaixaBairro(Integer.parseInt(campos[0]), campos[1]));
			logFaixaBairro.setFbcCepFim(campos[2]);
			logFaixaBairroRepository.save(logFaixaBairro);
			if(count==15000) {
				logFaixaBairroRepository.flush();
				count=0;
			}
			
		}
		
		
	}

}
