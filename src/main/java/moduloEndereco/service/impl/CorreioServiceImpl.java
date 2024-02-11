package moduloEndereco.service.impl;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import moduloEndereco.batch.CorreioRotina;
import moduloEndereco.excecoes.MsgGenericaPersonalizadaException;
import moduloEndereco.model.CorreioStatus;
import moduloEndereco.repository.CorreioStatusRepository;
import moduloEndereco.service.CorreioService;
import moduloEndereco.service.dto.CorreioStatusDTO;
import moduloEndereco.service.mapper.CorreioStatusMapper;

@Service
public class CorreioServiceImpl implements CorreioService{

	@Autowired
	private CorreioRotina correioRotina;
	@Autowired
	private CorreioStatusRepository correioStatusRepository;
	@Autowired
	private CorreioStatusMapper correioStatusMapper;

	@Override
	public void salvarArquivo(List<MultipartFile> listMultipartFile) {
		List<String> nomeArquivos = new ArrayList<>();
		CorreioStatus correioStatus= correioStatusRepository.findFirstByOrderByIdDesc();
		if(correioStatus==null||correioStatus.getStatus().equals("processado")||correioStatus.getStatus().equals("falha")) {
		for(MultipartFile multipartFile :listMultipartFile) {
		    try {
				multipartFile.transferTo(Paths.get(multipartFile.getOriginalFilename()));
				nomeArquivos.add(multipartFile.getOriginalFilename());
				
			} catch (IllegalStateException  |IOException e) {
				e.printStackTrace();
			
		}
		}
		if(!this.vericarOrdemExecucao(nomeArquivos, correioStatus))
			throw new MsgGenericaPersonalizadaException("Os arquivos estão fora de ordem");
		correioStatus = new CorreioStatus();
		correioStatus.setStatus("processando");
		correioStatus.setDataHoraInicioProcesso(new Date());
		correioStatus=correioStatusRepository.save(correioStatus);
		
		}else {
			 throw new MsgGenericaPersonalizadaException("Existe arquivos sendo processados");
		}
		 correioRotina.setNomesArquivos(nomeArquivos);
		 correioRotina.setCorreioStatus(correioStatus);
		 new Thread(correioRotina).start();
	}
	@Override
	public CorreioStatusDTO buscarStatus() {
		return correioStatusMapper.toDto(correioStatusRepository.findFirstByOrderByIdDesc());
	}

	private boolean vericarOrdemExecucao(List<String> nomeArquivo,CorreioStatus correioStatus) {
		int indice=0;
		int numerosLogradouros= (int)nomeArquivo.stream().filter(e->e.startsWith("LOG_LOGRADOURO")).count();
		if(nomeArquivo.get(indice).equalsIgnoreCase("LOG_FAIXA_UF.txt")||correioStatus==null) {
			indice=1;
			int posicaoMap=indice+1;
			Map<Integer,String> mapaArquivo= this.carregarNomesArquivos();
			while(indice<nomeArquivo.size()) {
				if(nomeArquivo.get(indice).startsWith("LOG_LOGRADOURO")&&posicaoMap==10) {
					indice=indice+numerosLogradouros;
				    posicaoMap++;
				    continue;
				}
				if(!nomeArquivo.get(indice).equalsIgnoreCase(mapaArquivo.get(posicaoMap)))
				 return false;
				
				
				indice++;
				posicaoMap++;
			}		
		}else {
			 int posicaoMap=0;
			 Map<Integer,String> mapaNomeArquivo= this.carregarNomesArquivos();
			 String ultimoArquivoProcessado=correioStatus.getUltimoArquivoExecutado();
			 if(ultimoArquivoProcessado.startsWith("LOG_LOGRADOURO"))
				 ultimoArquivoProcessado="LOG_LOGRADOURO.TXT";
			
			 
			 Integer key = this.buscarChaveMapa(mapaNomeArquivo, ultimoArquivoProcessado);
			 if(ultimoArquivoProcessado.equalsIgnoreCase("LOG_LOGRADOURO.TXT")) {
				  if(numerosLogradouros==0) {
					  posicaoMap=++key;
					  while(posicaoMap<=14&&indice<nomeArquivo.size()) {
					  if(!nomeArquivo.get(indice).equalsIgnoreCase(mapaNomeArquivo.get(posicaoMap)))
						  return false;
					    posicaoMap++;
					    indice++;
					  }
				  }else {
					  posicaoMap=key;
					  if(!nomeArquivo.get(indice).startsWith("LOG_LOGRADOURO"))
						  return false;
					  indice=indice+numerosLogradouros;
					  posicaoMap++;
					  while(posicaoMap<=14&&indice<nomeArquivo.size()) {
						  if(!nomeArquivo.get(indice).equalsIgnoreCase(mapaNomeArquivo.get(posicaoMap)))
							  return false;
						    posicaoMap++;
						    indice++;
						  }
					 
					  }
				  }else {
					  posicaoMap=++key;
					
					  while(posicaoMap<=14&&indice<nomeArquivo.size()) {
						  if(posicaoMap==10&&nomeArquivo.get(indice).startsWith("LOG_LOGRADOURO")) {
							  indice=numerosLogradouros;
							    posicaoMap++;
							    continue;
						  }
						  if(posicaoMap==10&&!nomeArquivo.get(indice).startsWith("LOG_LOGRADOURO"))
							  return false;
						  if(!nomeArquivo.get(indice).equalsIgnoreCase(mapaNomeArquivo.get(posicaoMap)))
							  return false;
						    
						    posicaoMap++;
						    indice++;
						  }
					  }
				  }
					  
			 
			return true;	 
			 
		}
		
	
	
	private Map<Integer,String> carregarNomesArquivos(){
		Map<Integer,String> nomesArquivo= new HashMap<>();
		nomesArquivo.put(1,"LOG_FAIXA_UF.TXT");
		nomesArquivo.put(2,"LOG_LOCALIDADE.TXT");
		nomesArquivo.put(3,"LOG_VAR_LOC.TXT");
		nomesArquivo.put(4,"LOG_FAIXA_LOCALIDADE.TXT");
		nomesArquivo.put(5,"LOG_BAIRRO.TXT");
		nomesArquivo.put(6,"LOG_VAR_BAI.TXT");
		nomesArquivo.put(7,"LOG_FAIXA_BAIRRO.TXT");
		nomesArquivo.put(8,"LOG_CPC.TXT");
		nomesArquivo.put(9,"LOG_FAIXA_CPC.TXT");
		nomesArquivo.put(10,"LOG_LOGRADOURO.TXT");
		nomesArquivo.put(11,"LOG_VAR_LOG.TXT");
		nomesArquivo.put(12,"LOG_NUM_SEC.TXT");
		nomesArquivo.put(13,"LOG_GRANDE_USUARIO.TXT");
		nomesArquivo.put(14,"LOG_UNID_OPER.TXT");
		

		return nomesArquivo;
	}
	
	private Integer  buscarChaveMapa(Map<Integer,String> mapaNomeArquivo,String valor) {
		String ultimoArquivoProcessado=valor;
		return mapaNomeArquivo.entrySet()
                .stream()                       
                .filter(e -> e.getValue().equals(ultimoArquivoProcessado))
                .findFirst()
                .map(Map.Entry::getKey)
                .orElse(null);
	}
}
