package moduloEndereco.modulodeendereco.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;

import moduloEndereco.autenticacao.JwtTokenProvider;
import moduloEndereco.model.Bairro;
import moduloEndereco.model.IdBairroIdLocalidade;
import moduloEndereco.model.MunicipioLocalidade;
import moduloEndereco.modulodeendereco.config.ConfigTest;
import moduloEndereco.repository.BairroRepository;
import moduloEndereco.repository.MunicipioLocalidadeRepository;
import moduloEndereco.service.AuditoriaService;
import moduloEndereco.service.dto.BairroDTO;
import moduloEndereco.service.impl.BairroServiceImpl;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BairroServiceImplTest extends ConfigTest {

	@SpyBean
	private BairroServiceImpl service;
	
	@MockBean
	private BairroRepository bairroRepository;
	@MockBean
	private AuditoriaService auditoriaService;
	@MockBean
	private JwtTokenProvider jwtTokenProvider;
	@MockBean
	private MunicipioLocalidadeRepository municipioLocalidadeRepository;
	
    private BairroDTO bairroDto;
    
    private Bairro bairro;
    
    private IdBairroIdLocalidade idBairroIdLocalidade;
    
    private MunicipioLocalidade municipioLocalidade;
	
	@Before
	public void setup() {		
		this.bairroDto = new BairroDTO();
		this.bairroDto.setId(1);
		this.bairroDto.setNomeBairro("Teste");
		this.bairroDto.setCdLocalidade((short) 2);
		this.bairroDto.setCdBairro((short) 2);
		
		this.idBairroIdLocalidade = new IdBairroIdLocalidade();
		this.idBairroIdLocalidade.setCdBairro((short)2);
		this.idBairroIdLocalidade.setCdLocalidade((short)2);
		this.bairro = new Bairro();
		this.bairro.setId(1);
		this.bairro.setNomeBairro("Teste");
		this.bairro.setIdBairroIdLocalidade(idBairroIdLocalidade);
		
		this.municipioLocalidade = new MunicipioLocalidade();
		this.municipioLocalidade.setCdCidade((short) 2);
		this.municipioLocalidade.setDcCidade("Teste");
	}
	@Test
	public void salvarBairro() {
		when(bairroRepository.findFirstByOrderByIdDesc()).thenReturn(null);
		service.salvar(bairroDto, this.getTokenUsuario());
	}
	@Ignore
	@Test
	public void atulizarBairro() {
		when(bairroRepository.findByIdBairroIdLocalidadeAndDataHoraExclusao(idBairroIdLocalidade,null)).thenReturn(bairro);
		when(bairroRepository.save(any(Bairro.class))).thenReturn(bairro);
		when(municipioLocalidadeRepository.findById(bairroDto.getCdLocalidade())).thenReturn(Optional.of(municipioLocalidade));
		service.atualizar(bairroDto, this.getTokenUsuario());
	}
	
	
}
