package moduloEndereco.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import moduloEndereco.model.LogNumeroSec;
import moduloEndereco.repository.LogLogradouroRepository;
import moduloEndereco.repository.LogNumeroSecRepository;
import moduloEndereco.service.LogNumeroSecService;


@Service
public class LogNumeroServiceImpl implements LogNumeroSecService {

	@Autowired
	private LogNumeroSecRepository logNumeroSecRepository;
	@Autowired
	private LogLogradouroRepository logLogradouroRepository;
	
	@Override
	public void salvar(List<String> listNumeroSec) {
		for(String numeroSec: listNumeroSec) {
			LogNumeroSec logNumeroSec = new LogNumeroSec();
			String campos[] = numeroSec.split("@");
			if(logLogradouroRepository.findById(Integer.parseInt(campos[0])).isEmpty())
				continue;
			logNumeroSec.setNumeroLog(Integer.parseInt(campos[0]));
			logNumeroSec.setSecNuIni(campos[1]);
			logNumeroSec.setSecNuFim(campos[2]);
			logNumeroSec.setSecInLado(campos[3]);
			logNumeroSecRepository.save(logNumeroSec);
	
			
			
		}
		
		
		
	}

}
