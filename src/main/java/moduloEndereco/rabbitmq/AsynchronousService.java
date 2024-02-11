package moduloEndereco.rabbitmq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import moduloEndereco.batch.EnderecoGisRotina;
import moduloEndereco.rabbitmq.consumidores.IncluiAreaAtuacaoUsuario;


 
@Service
public class AsynchronousService {
 
    @Autowired
    private TaskExecutor taskExecutor;
 
    @Autowired
    private ApplicationContext applicationContext;
 
    @EventListener(ApplicationReadyEvent.class)
    public void executeAsynchronously() throws Exception {

        
        IncluiAreaAtuacaoUsuario consumidor1 = applicationContext.getBean(IncluiAreaAtuacaoUsuario.class);
    	consumidor1.criarFila();
        taskExecutor.execute(consumidor1);
        
        EnderecoGisRotina enderecoGis =  applicationContext.getBean(EnderecoGisRotina.class);
        enderecoGis.retornarStatusProcessamento();
    }
 
}