package moduloEndereco.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import moduloEndereco.model.AreaAtuacao;
import moduloEndereco.model.AreaAtuacaoUsuario;
import moduloEndereco.repository.AreaAtuacaoUsuarioRepository;
import moduloEndereco.service.AreaAtuacaoUsuarioService;
import moduloEndereco.service.dto.AreaAtuacaoUsuarioDTO;
import moduloEndereco.service.mapper.AreaAtuacaoUsuarioMapper;
import moduloEndereco.util.LogErro;

@Transactional
@Service
public class AreaAtuacaoUsuarioServiceImpl implements AreaAtuacaoUsuarioService{

	@Autowired
	private AreaAtuacaoUsuarioRepository areaAtuacaoUsuarioRepository;

	@Autowired
	private AreaAtuacaoUsuarioMapper areaAtuacaoUsuarioMapper;
	
	@Autowired
	private LogErro logErro;

	
	@Override
	public void salvar(AreaAtuacaoUsuarioDTO areaAtuacaoUsuarioDTO) {
		if (!(this.validarStatus(areaAtuacaoUsuarioDTO.getStatus()))
				||(areaAtuacaoUsuarioDTO.getIdAreaAtuacao()==null ||(areaAtuacaoUsuarioDTO.getIdUsuario()==null))) {
			logErro.logErro("Erro ao salvar Área de Atuação de Usuário",areaAtuacaoUsuarioDTO.toString());
		}else {
			
			
			if(areaAtuacaoUsuarioDTO.getStatus().equalsIgnoreCase("E")) {
				AreaAtuacao areaAtuacao = new AreaAtuacao();
				areaAtuacao.setId(areaAtuacaoUsuarioDTO.getIdAreaAtuacao());
				AreaAtuacaoUsuario areaAtuacaoUsuario=areaAtuacaoUsuarioRepository.findByAreaAtuacaoAndIdUsuario(areaAtuacao, areaAtuacaoUsuarioDTO.getIdUsuario());
				areaAtuacaoUsuarioRepository.delete(areaAtuacaoUsuario);
			}else {
				AreaAtuacaoUsuario areaAtuacaoUsuario= areaAtuacaoUsuarioMapper.toEntity(areaAtuacaoUsuarioDTO);
				areaAtuacaoUsuarioRepository.save(areaAtuacaoUsuario);
			}
		}
		
	}


	private boolean validarStatus(String status) {
		if(status.equalsIgnoreCase("A")||status.equalsIgnoreCase("E"))
			return true;
		else 
			return false;
	}

}
