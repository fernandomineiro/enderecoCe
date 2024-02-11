package moduloEndereco.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import moduloEndereco.model.IdLogVarLog;
import moduloEndereco.model.LogVarLog;
import moduloEndereco.repository.LogLogradouroRepository;
import moduloEndereco.repository.LogVarLogRepository;
import moduloEndereco.service.LogVarLogService;


@Service
public class LogVarLogServiceImpl  implements LogVarLogService{

	@Autowired
	private LogVarLogRepository logVarLogRepository;
	@Autowired
	private LogLogradouroRepository logLogradouroRepository;
	
	@Override
	public void salvar(List<String> listVarBairro) {
		for(String varBairro: listVarBairro) {
			LogVarLog logVarLog = new LogVarLog();
			String campos[]=varBairro.split("@");
			if(logLogradouroRepository.findById(Integer.parseInt(campos[0])).isEmpty())
				continue;
			logVarLog.setIdLogVarLog(new IdLogVarLog(Integer.parseInt(campos[0]), Integer.parseInt(campos[1])));
			
			logVarLog.setTloTx(campos[2]);
			logVarLog.setVloTx(campos[3]);
			logVarLogRepository.save(logVarLog);
			
			
		}
	
		
		
		
	}

}
