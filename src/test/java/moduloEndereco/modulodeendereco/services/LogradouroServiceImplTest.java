package moduloEndereco.modulodeendereco.services;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import moduloEndereco.autenticacao.JwtTokenProvider;
import moduloEndereco.model.IdLogradouroIdLocalidade;
import moduloEndereco.model.Logradouro;
import moduloEndereco.model.TipoLogradouro;
import moduloEndereco.modulodeendereco.config.ConfigTest;
import moduloEndereco.repository.LogradouroRepository;
import moduloEndereco.service.AuditoriaService;
import moduloEndereco.service.BairroLogradouroService;
import moduloEndereco.service.dto.LogradouroDTO;
import moduloEndereco.service.impl.LogradouroServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LogradouroServiceImplTest extends ConfigTest {

	@SpyBean
	private LogradouroServiceImpl service;

	@MockBean
	private LogradouroRepository logradouroRepository;
	@MockBean
	private AuditoriaService auditoriaService;
	@MockBean
	private BairroLogradouroService bairroLogradouroService;
	@MockBean
	private RestTemplate restTemplate;
	@MockBean
	private JwtTokenProvider jwtTokenProvider;

	private LogradouroDTO logradouroDto;

	private Logradouro logradouro;
	private TipoLogradouro tipoLogradouro;

	private IdLogradouroIdLocalidade idLogradouroIdLocalidade;

	@Before
	public void setup() {
		this.logradouroDto = new LogradouroDTO();
		this.logradouroDto.setId(1);
		this.logradouroDto.setCodLogradouro((short) 57);
		this.logradouroDto.setCodLocalidade((short) 500);
		this.logradouroDto.setNomeLogradouro("Teste");
		this.logradouroDto.setIdTipoLogradouro(31l);
		this.logradouroDto.setSiglaLogradouro("Rua");
		
		List<Short> idsBairro = new ArrayList<Short>();
		idsBairro.add((short) 2);
		idsBairro.add((short) 3);
		this.logradouroDto.setIdsBairro(idsBairro);

		this.idLogradouroIdLocalidade = new IdLogradouroIdLocalidade();
		this.idLogradouroIdLocalidade.setCodLogradouro((short) 57);
		this.idLogradouroIdLocalidade.setCodLocalidade((short) 500);

		this.logradouro = new Logradouro();
		this.logradouro.setId(1);
		this.logradouro.setIdLogradouroIdLocalidade(idLogradouroIdLocalidade);
		this.logradouro.setNomeLogradouro("Teste");

		this.tipoLogradouro = new TipoLogradouro();
		this.tipoLogradouro.setId(31l);
		this.tipoLogradouro.setNome("Rua");
		this.tipoLogradouro.setSigla("RUA");
		this.logradouro.setTipoLogradouro(tipoLogradouro);

		this.logradouro.setMaint('A');
		this.logradouro.setCodAtendimento(0);
		this.logradouro.setDiametroRedeAgua((short) 0);
		this.logradouro.setDiametroRedeEsgoto((short) 0);
		this.logradouro.setLocalRedeAgua((short) 0);
		this.logradouro.setLocalRedeEsgoto((short) 0);
		this.logradouro.setMaterialRedeAgua((short) 0);
		this.logradouro.setMaterialRedeEsgoto((short) 0);
		this.logradouro.setRefAtendimenoSS(0);
		this.logradouro.setSequencialSS((short) 0);

	}

	@Test
	public void salvarLogradouro() {
		when(logradouroRepository.findFirstByOrderByIdDesc()).thenReturn(null);
		service.salvar(logradouroDto, this.getTokenUsuario());

		bairroLogradouroService.salvarBairroLogradouro(logradouroDto.getIdsBairro(),
				logradouro.getIdLogradouroIdLocalidade(), this.getTokenUsuario());
	}

	

}
