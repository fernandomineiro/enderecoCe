package moduloEndereco.autenticacao;

import java.util.Base64;

import org.springframework.stereotype.Component;
 
@Component
public class JwtTokenProvider {


    public Long buscarIdUsuario(String token) {
    	String payloads[] = token.split("\\.");	
    	byte[] decodedBytes = Base64.getDecoder().decode(payloads[1]);
    	String payload = new String(decodedBytes);
    	String login[] = payload.split(",");      
    	return Long.valueOf(login[1].substring(12).replaceAll("\"", ""));
    }
    
    public String buscarLogin(String token) {
    	String payloads[] = token.split("\\.");	
    	byte[] decodedBytes = Base64.getDecoder().decode(payloads[1]);
    	String payload = new String(decodedBytes);
    	String login[] = payload.split(",");      
    	return login[0].substring(7).replaceAll("\"", "");
    }
   
}
