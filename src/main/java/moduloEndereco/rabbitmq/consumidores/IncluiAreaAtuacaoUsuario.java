package moduloEndereco.rabbitmq.consumidores;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;

import moduloEndereco.rabbitmq.EndPoint;
import moduloEndereco.service.AreaAtuacaoUsuarioService;
import moduloEndereco.service.dto.AreaAtuacaoUsuarioDTO;
import moduloEndereco.util.LogErro;


@Component
@Scope("prototype")
public class IncluiAreaAtuacaoUsuario implements Runnable, Consumer{
	@Autowired
	private EndPoint endPoint;

	private Channel channel;

	private Gson gson;

	@Autowired
	private AreaAtuacaoUsuarioService areaAtuacaoUsuarioService;
	
	@Autowired
	private LogErro logErro;

	@Value("${rabbitmq.incluiAreaAtuacaoUsuario.nomeFila}")
	private String nomeFila;
	@Value("${rabbitmq.incluiAreaAtuacaoUsuario.nomeRota}")
	private String nomeRota;
	@Value("${rabbitmq.incluiAreaAtuacaoUsuario.nomeExchange}")
	private String nomeExchange;

	public IncluiAreaAtuacaoUsuario() throws IOException {
		this.gson = new Gson();
	}

	public void criarFila() throws Exception {
		this.channel = endPoint.criarFila(nomeExchange, "fanout", nomeFila, nomeRota, null);
	}

	public void run() {
		try {
			channel.basicConsume(nomeFila, false, this);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void handleDelivery(String consumerTag, Envelope env, BasicProperties props, byte[] body)
			throws IOException {

	 try {	String message = new String(body, "UTF-8");

		AreaAtuacaoUsuarioDTO areaAtuacaoUsuarioDTO = gson.fromJson(message, AreaAtuacaoUsuarioDTO.class);

		areaAtuacaoUsuarioService.salvar(areaAtuacaoUsuarioDTO);
		channel.basicAck(env.getDeliveryTag(), false);
	 }catch(Exception e) {
		 logErro.logErro("Erro ao salvar Área de Atuação usuário ", e.getMessage());
		}
	}

	@Override
	public void handleConsumeOk(String consumerTag) {
		
		
	}

	@Override
	public void handleCancelOk(String consumerTag) {
		
		
	}

	@Override
	public void handleCancel(String consumerTag) throws IOException {
	
		
	}

	@Override
	public void handleShutdownSignal(String consumerTag, ShutdownSignalException sig) {
		
		
	}

	@Override
	public void handleRecoverOk(String consumerTag) {
		
		
	}

	
}
