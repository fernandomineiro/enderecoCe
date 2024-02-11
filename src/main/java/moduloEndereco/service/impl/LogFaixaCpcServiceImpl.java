package moduloEndereco.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import moduloEndereco.model.IdLogFaixaCpc;
import moduloEndereco.model.LogFaixaCpc;
import moduloEndereco.repository.LogFaixaCpcRepository;
import moduloEndereco.service.LogFaixaCpcService;


@Service
public class LogFaixaCpcServiceImpl implements LogFaixaCpcService {
	
	@Autowired
	private LogFaixaCpcRepository logFaixaCpcRepository;

	@Override
	public void salvar(List<String> listFaixaCpc) {
		for(String faixaCpc : listFaixaCpc) {
			LogFaixaCpc logFaixaCpc = new LogFaixaCpc();
			String campos[]= faixaCpc.split("@");
			logFaixaCpc.setIdLogFaixaCpc(new IdLogFaixaCpc(Integer.parseInt(campos[0]), campos[1]));
			logFaixaCpc.setCpcFinal(campos[2]);
			logFaixaCpcRepository.save(logFaixaCpc);
		}
		
		
	}

}
