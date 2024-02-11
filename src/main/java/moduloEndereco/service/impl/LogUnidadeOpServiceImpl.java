package moduloEndereco.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import moduloEndereco.model.LogLocalidade;
import moduloEndereco.model.LogUnidadeOp;
import moduloEndereco.repository.LogLogradouroRepository;
import moduloEndereco.repository.LogUnidadeOpRepository;
import moduloEndereco.service.LogUnidadeOpService;
import moduloEndereco.service.dto.LogUnidadeOpDTO;
import moduloEndereco.service.mapper.LogUnidadeOpMapper;

@Service
public class LogUnidadeOpServiceImpl implements LogUnidadeOpService {

	@Autowired
	private LogUnidadeOpRepository logUnidadeOpRepository;
	@Autowired
	private LogLogradouroRepository logLogradouroRepository;
	@Autowired
	private LogUnidadeOpMapper logUnidadeOpMapper;
	
	@Override
	public void salvar(List<String> listUnidadeOp) {
		for(String unidadeOp: listUnidadeOp) {
			LogUnidadeOp logUnidadeOp = new LogUnidadeOp();
			String campos[] = unidadeOp.split("@");
			logUnidadeOp.setNumeroUnidadeOp(Integer.parseInt(campos[0]));
			logUnidadeOp.setUf(campos[1]);
			LogLocalidade logLocalidade = new LogLocalidade();
			logLocalidade.setNumeroLocalidade(Integer.parseInt(campos[2]));
			logUnidadeOp.setNumeroLocalidade(logLocalidade);
			logUnidadeOp.setBaiNu(Integer.parseInt(campos[3]));
			if(campos[4].isEmpty()) {
				logUnidadeOp.setNumeroLogradouro(null);
			}else if(logLogradouroRepository.findById(Integer.parseInt(campos[4])).isEmpty()) {
				continue;
			}else {	
				logUnidadeOp.setNumeroLogradouro(Integer.parseInt(campos[4]));
			}
			logUnidadeOp.setNomeUnidadeOp(campos[5]);
			logUnidadeOp.setEnderecoUnidadaOp(campos[6]);
			logUnidadeOp.setCep(campos[7]);
			logUnidadeOp.setUnidadeOpInCp(campos[8]);
			if(campos.length<10)
				logUnidadeOp.setNomeUnidadeOpAbreviada(null);
			else
				logUnidadeOp.setNomeUnidadeOpAbreviada(campos[9]);
			logUnidadeOpRepository.save(logUnidadeOp);
		}
		
	}

	@Override
	public List<LogUnidadeOpDTO> buscarCep(String cep) {
		return logUnidadeOpMapper.toDto(logUnidadeOpRepository.findByCep(cep));
	}

}
