package moduloEndereco.modulodeendereco.services;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;

import moduloEndereco.excecoes.MsgGenericaPersonalizadaException;
import moduloEndereco.modulodeendereco.config.ConfigTest;
import moduloEndereco.repository.RegionalRepository;
import moduloEndereco.service.dto.RegionalDTO;
import moduloEndereco.service.impl.RegionalServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RegionalServiceImplTest extends ConfigTest {

	@SpyBean
	private RegionalServiceImpl service;
	
	@MockBean
	private RegionalRepository repository;
	
	private RegionalDTO dto;
	
	@Before
	public void setup() {		
		this.dto = new RegionalDTO();
		this.dto.setCodRegional(41);
		this.dto.setNomeRegional("CANCUM");
		this.dto.setStatus("A");
	}
	
//	@Test
//	public void executarProcedimentoSalvar_sucesso() {
////		this.dto.setNomeRegional("BALI" + Instant.now());
////		RegionalDTO dtoRetorno = this.service.executarProcedimentoSalvar(this.dto, this.getTokenUsuario());
////		assertEquals(dtoRetorno.getNomeRegional(),this.dto.getNomeRegional());
//	}
		
	
	@Test
	public void tratarCodRegionalNaoEncontrado() {
		try {
		this.service.consultarRegionalPorID(0);
		} catch (MsgGenericaPersonalizadaException e) {
			assert(e.getMessage().equals("Código regional não encontrado"));
		}
	}
	
	
	@Test
	public void setarStatusDefault() {
		this.dto.setStatus(null);
		RegionalDTO dtoRetorno = this.service.setarStatusDefault(this.dto);
		assertEquals("A", dtoRetorno.getStatus());
		this.dto.setStatus("C");
		dtoRetorno = this.service.setarStatusDefault(this.dto);
		assertEquals("C", dtoRetorno.getStatus());
	}
	
	@Test
	public void verificarPresencaDeDados() {
		this.dto.setNomeRegional(null);
		try {
			this.service.verificarPresencaDeDados(this.dto);;
			assert(true);
		} catch (MsgGenericaPersonalizadaException e) {
			assert(e.getMessage().equals("Dado não encontrado para salvar o registro"));
		}
	}	
}
