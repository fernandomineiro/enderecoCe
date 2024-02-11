package moduloEndereco.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UrlMicroservico {

	@Value("${microservico.imovel}")
	private String urlImovel;

	@Value("${microservico.servico}")
	private String urlServico;
	
	@Value("${microservico.cronograma}")
	private String urlCronogramaFatura;

	public String getUrlImovel() {
		return urlImovel;
	}

	public void setUrlImovel(String urlImovel) {
		this.urlImovel = urlImovel;
	}

	public String getUrlServico() {
		return urlServico;
	}

	public void setUrlServico(String urlServico) {
		this.urlServico = urlServico;
	}

	public String getUrlCronogramaFatura() {
		return urlCronogramaFatura;
	}

	public void setUrlCronogramaFatura(String urlCronogramaFatura) {
		this.urlCronogramaFatura = urlCronogramaFatura;
	}

}
