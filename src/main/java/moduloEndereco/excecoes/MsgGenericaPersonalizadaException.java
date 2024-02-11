package moduloEndereco.excecoes;
 
public class MsgGenericaPersonalizadaException  extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public MsgGenericaPersonalizadaException(String mensagem){
		super(mensagem);
	}
}
