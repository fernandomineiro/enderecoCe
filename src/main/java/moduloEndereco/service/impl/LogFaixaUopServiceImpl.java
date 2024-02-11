package moduloEndereco.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import moduloEndereco.model.IdLogFaixaUop;
import moduloEndereco.model.LogFaixaUop;
import moduloEndereco.repository.LogFaixaUopRepository;
import moduloEndereco.repository.LogUnidadeOpRepository;
import moduloEndereco.service.LogFaixaUopService;

@Service
public class LogFaixaUopServiceImpl implements LogFaixaUopService{

	@Autowired
	private LogFaixaUopRepository logFaixaUopRepository;
	@Autowired
	private LogUnidadeOpRepository logUnidadeOpRepository;
	
	@Override
	public void salvar(List<String> listFaixaUop) {
		for(String faixaUop : listFaixaUop ) {
			LogFaixaUop logFaixaUop = new LogFaixaUop();
			String campos[] = faixaUop.split("@");
			if(logUnidadeOpRepository.findById(Integer.parseInt(campos[0])).isEmpty())
				continue;
			logFaixaUop.setIdLogFaixaUop(new IdLogFaixaUop(Integer.parseInt(campos[0]), Integer.parseInt(campos[1])));
			logFaixaUop.setFncFinal(Integer.parseInt(campos[2]));
			logFaixaUopRepository.save(logFaixaUop);
		}
		
	}

}
