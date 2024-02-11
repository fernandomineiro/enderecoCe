package moduloEndereco.batch;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import moduloEndereco.model.CorreioStatus;
import moduloEndereco.repository.CorreioStatusRepository;
import moduloEndereco.service.LogBairroService;
import moduloEndereco.service.LogCpcService;
import moduloEndereco.service.LogFaixaBairroService;
import moduloEndereco.service.LogFaixaCpcService;
import moduloEndereco.service.LogFaixaLocalidadeService;
import moduloEndereco.service.LogFaixaUfService;
import moduloEndereco.service.LogFaixaUopService;
import moduloEndereco.service.LogGrandeUsuarioService;
import moduloEndereco.service.LogLocalidadeService;
import moduloEndereco.service.LogLogradouroService;
import moduloEndereco.service.LogNumeroSecService;
import moduloEndereco.service.LogUnidadeOpService;
import moduloEndereco.service.LogVarBaiService;
import moduloEndereco.service.LogVarLocService;
import moduloEndereco.service.LogVarLogService;

@Component
public class CorreioRotina implements Runnable {

	@Autowired
	private LogFaixaUfService logUfService;
	@Autowired
	private LogLocalidadeService logLocalidadeService;
	@Autowired
	private LogVarLocService logVarLocService;
	@Autowired
	private LogFaixaLocalidadeService logFaixaLocalidadeService;
	@Autowired
	private LogBairroService logBairroService;
	@Autowired
	private LogVarBaiService logVarBaiService;
	@Autowired
	private LogFaixaBairroService logFaixaBairroService;
	@Autowired
	private LogCpcService logCpcService;
	@Autowired
	private LogFaixaCpcService logFaixaCpcService;
	@Autowired
	private LogLogradouroService logLogradouroService;
	@Autowired
	private LogVarLogService logVarLogService;
	@Autowired
	private LogNumeroSecService logNumeroSecService;
	@Autowired
	private LogGrandeUsuarioService logGrandeUsuarioService;
	@Autowired
	private LogUnidadeOpService logUnidadeOpService;
	@Autowired
	private LogFaixaUopService logFaixaUopService;
	@Autowired
	private CorreioStatusRepository correioStatusRepository;
	private List<String> nomesArquivos;

	private CorreioStatus correioStatus;
	
	@Override
	public void run() {

		for (String nomeArquivo : this.getNomesArquivos()) {
			Path path = Paths.get(nomeArquivo);
			try {
				this.salvarArquivo(Files.readAllLines(path, StandardCharsets.ISO_8859_1), nomeArquivo);
				Files.delete(path);
				correioStatus.setUltimoArquivoExecutado(nomeArquivo);
				correioStatusRepository.save(correioStatus);
			} catch (IllegalStateException  | IOException e) {
				e.printStackTrace();
				correioStatus.setStatus("falha");
				correioStatus.setDataHoraFimProcesso(new Date());
				correioStatusRepository.save(correioStatus);
			    
			}catch (Exception e) {
				e.printStackTrace();
				correioStatus.setStatus("falha");
				correioStatus.setDataHoraFimProcesso(new Date());
				correioStatusRepository.save(correioStatus);
			}
			
		}
		if(!correioStatus.getStatus().equals("falha")) {
			correioStatus.setStatus("processado");
			correioStatus.setDataHoraFimProcesso(new Date());
			correioStatusRepository.save(correioStatus);
		}
	}

	public List<String> getNomesArquivos() {
		return nomesArquivos;
	}

	public void setNomesArquivos(List<String> nomesArquivos) {
		this.nomesArquivos = nomesArquivos;
	}

	public CorreioStatus getCorreioStatus() {
		return correioStatus;
	}

	public void setCorreioStatus(CorreioStatus correioStatus) {
		this.correioStatus = correioStatus;
	}

	private void salvarArquivo(List<String> registros, String nomeArquivo) throws Exception {
		if (nomeArquivo.equalsIgnoreCase("LOG_FAIXA_UF.txt")) 
			logUfService.salvar(registros);
		else if (nomeArquivo.equalsIgnoreCase("LOG_LOCALIDADE.txt")||nomeArquivo.equalsIgnoreCase("LOG_LOCALIDADE_ES.txt"))
			logLocalidadeService.salvar(registros);
		else if (nomeArquivo.equalsIgnoreCase("LOG_VAR_LOC.txt"))
			logVarLocService.salvar(registros);
		else if(nomeArquivo.equalsIgnoreCase("LOG_FAIXA_LOCALIDADE.txt"))
			logFaixaLocalidadeService.salvar(registros);
		else if(nomeArquivo.equalsIgnoreCase("LOG_BAIRRO.txt"))
			logBairroService.salvar(registros);
		else if(nomeArquivo.equalsIgnoreCase("LOG_VAR_BAI.txt"))
			logVarBaiService.salvar(registros);
		else if(nomeArquivo.equalsIgnoreCase("LOG_FAIXA_BAIRRO.txt"))
			logFaixaBairroService.salvar(registros);
		else if(nomeArquivo.equalsIgnoreCase("LOG_CPC.txt"))
			logCpcService.salvar(registros);
		else if(nomeArquivo.equalsIgnoreCase("LOG_FAIXA_CPC.txt"))
			logFaixaCpcService.salvar(registros);
		else if(nomeArquivo.startsWith("LOG_LOGRADOURO_")&&nomeArquivo.endsWith("TXT"))
			logLogradouroService.salvar(registros);
		else if(nomeArquivo.equalsIgnoreCase("LOG_VAR_LOG.txt"))
			logVarLogService.salvar(registros);
		else if(nomeArquivo.equalsIgnoreCase("LOG_NUM_SEC.TXT"))
			logNumeroSecService.salvar(registros);
		else if(nomeArquivo.equalsIgnoreCase("LOG_GRANDE_USUARIO.TXT"))	
			logGrandeUsuarioService.salvar(registros);
		else if(nomeArquivo.equalsIgnoreCase("LOG_UNID_OPER.txt"))
			logUnidadeOpService.salvar(registros);
		else if(nomeArquivo.equalsIgnoreCase("LOG_FAIXA_UOP.TXT"))
			logFaixaUopService.salvar(registros);
			
	}

}
