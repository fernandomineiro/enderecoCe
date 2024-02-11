package moduloEndereco.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import moduloEndereco.model.IdLogFaixaLocalidade;
import moduloEndereco.model.LogFaixaLocalidade;
import moduloEndereco.repository.LogFaixaLocalidadeRepository;
import moduloEndereco.service.LogFaixaLocalidadeService;


@Service
public class LogFaixaLocalidadeFaixaServiceImpl implements LogFaixaLocalidadeService {

	@Autowired
	private LogFaixaLocalidadeRepository logFaixaLocalidadeRepository;
	@Override
	public void salvar(List<String> listFaixaLocalidade) {
		for(String faixaLocalidade: listFaixaLocalidade) {
			LogFaixaLocalidade logFaixaLocalidade = new LogFaixaLocalidade();
			String campos[] = faixaLocalidade.split("@");
			logFaixaLocalidade.setIdLogFaixaLocalidade(new IdLogFaixaLocalidade(Integer.parseInt(campos[0]), campos[1]));
			logFaixaLocalidade.setLocCepFim(campos[2]);
			logFaixaLocalidadeRepository.save(logFaixaLocalidade);
			
		}
		
		
	}

}
