package moduloEndereco.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import moduloEndereco.model.IdLogVarLoc;
import moduloEndereco.model.LogVarLoc;
import moduloEndereco.repository.LogVarLocRepository;
import moduloEndereco.service.LogVarLocService;


@Service
public class LogVarLocServiceImpl  implements LogVarLocService{

	@Autowired
	private LogVarLocRepository logVarLocRepository;
	
	@Override
	public void salvar(List<String> listVarLocalidade) {
	  for(String varString: listVarLocalidade) {
		  LogVarLoc logVarLoc = new LogVarLoc();
		  String campos[] = varString.split("@");
		  logVarLoc.setIdLogVarLoc(new IdLogVarLoc(Integer.parseInt(campos[0]), Integer.parseInt(campos[1])));
		  logVarLoc.setValTx(campos[2]);
		  logVarLocRepository.save(logVarLoc);
		
	  }
	  
	
		
	}

}
