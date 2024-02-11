package moduloEndereco.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import moduloEndereco.model.IdLogVarBai;
import moduloEndereco.model.LogVarBai;
import moduloEndereco.repository.LogVarBaiRepository;
import moduloEndereco.service.LogVarBaiService;


@Service
public class LogVarBaiServiceImpl implements LogVarBaiService {

	@Autowired
	private LogVarBaiRepository logVarBaiRepository;
	
	@Override
	public void salvar(List<String> listVarBairro) {
		int count=0;
		for(String varBairro: listVarBairro) {
			count++;
			LogVarBai logVarBai = new LogVarBai();
			String campos[] = varBairro.split("@");
			logVarBai.setIdLogVarBai(new IdLogVarBai(Integer.parseInt(campos[0]), Integer.parseInt(campos[1])));
			logVarBai.setVdbTx(campos[2]);
			logVarBaiRepository.save(logVarBai);
			if(count==15000) {
				logVarBaiRepository.flush();
				count=0;
			}
		}
		
	}

}
