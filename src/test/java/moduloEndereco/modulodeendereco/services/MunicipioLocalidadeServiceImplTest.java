package moduloEndereco.modulodeendereco.services;

import static org.mockito.Mockito.when;

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
import moduloEndereco.model.MunicipioLocalidade;
import moduloEndereco.model.Regional;
import moduloEndereco.modulodeendereco.config.ConfigTest;
import moduloEndereco.repository.MunicipioLocalidadeRepository;
import moduloEndereco.service.AuditoriaService;
import moduloEndereco.service.dto.MunicipioLocalidadeDTO;
import moduloEndereco.service.dto.MunicipioLocalidadeWrapperDTO;
import moduloEndereco.service.impl.MunicipioLocalidadeServiceImpl;
import moduloEndereco.service.mapper.MunicipioLocalidadeMapper;
import moduloEndereco.service.mapper.MunicipioLocalidadeRetornoMapper;



@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MunicipioLocalidadeServiceImplTest extends ConfigTest {

	@SpyBean
	private MunicipioLocalidadeServiceImpl service;
	
	@MockBean
	private MunicipioLocalidadeRepository municipioLocalidadeRepository;
	@MockBean
	private MunicipioLocalidadeWrapperDTO municipioLocalidadeWrapperDTO;
	@MockBean
	private MunicipioLocalidadeMapper municipioLocalidadeMapper;
	@MockBean
	private MunicipioLocalidadeRetornoMapper municipioLocalidadeRetornoMapper;
	@MockBean
	private AuditoriaService auditoriaService;
	@MockBean
	private JwtTokenProvider jwtTokenProvider;

	
    private MunicipioLocalidadeDTO municipioLocalidadeDto;
    
    private MunicipioLocalidade municipioLocalidade;
    
    @Before
	public void setup() {		
		this.municipioLocalidadeDto = new MunicipioLocalidadeDTO();
		this.municipioLocalidadeDto.setCdCidade((short)1);
		this.municipioLocalidadeDto.setCdMunicipio((short)1);
		this.municipioLocalidadeDto.setCdRegional(1);
		this.municipioLocalidadeDto.setCdTarifa((short)1);
		this.municipioLocalidadeDto.setDcCidade("Teste");
		this.municipioLocalidadeDto.setDensidade((float)1);
		this.municipioLocalidadeDto.setEnderecoAtendimento("Teste");
		this.municipioLocalidadeDto.setIcms((float)1);
		this.municipioLocalidadeDto.setIssqn((float)1);
		this.municipioLocalidadeDto.setMaint("A");
		this.municipioLocalidadeDto.setMetropolitana("Teste");
		this.municipioLocalidadeDto.setMunicipio("Teste");
		this.municipioLocalidadeDto.setPopulacao(1);
		this.municipioLocalidadeDto.setTemAgua("S");
		this.municipioLocalidadeDto.setTpLeitura(1);
		this.municipioLocalidadeDto.setTpCodigoBarras(1);
		this.municipioLocalidadeDto.setHorarioAtendimento((short)1);
		this.municipioLocalidadeDto.setEmail("Teste");
		this.municipioLocalidadeDto.setTemDispEsgoto("S");
		
		
        this.municipioLocalidade = new MunicipioLocalidade();
		this.municipioLocalidade.setCdCidade((short)1);
		this.municipioLocalidade.setCdMunicipio((short)1);
		Regional regional= new Regional();
		regional.setCodRegional(1);
     	this.municipioLocalidade.setRegional(regional);
		this.municipioLocalidade.setCdTarifa((short)1);
		this.municipioLocalidade.setDcCidade("Teste");
		this.municipioLocalidade.setDensidade((float)1);
		this.municipioLocalidade.setEnderecoAtendimento("Teste");
		this.municipioLocalidade.setIcms((float)1);
		this.municipioLocalidade.setIssqn((float)1);
		this.municipioLocalidade.setMaint("A");
		this.municipioLocalidade.setMetropolitana("Teste");
		this.municipioLocalidade.setMunicipio("Teste");
		this.municipioLocalidade.setPopulacao(1);
		this.municipioLocalidade.setTemAgua("S");
		this.municipioLocalidade.setTpLeitura(1);
		this.municipioLocalidade.setTpCodigoBarras(1);
		this.municipioLocalidade.setHorarioAtendimento((short)1);
		this.municipioLocalidade.setEmail("Teste");
		this.municipioLocalidade.setTemDispEsgoto("S");
	
	}
    
    @Ignore
    public void incluirMunicipioLocalidade() {
    	//when(municipioLocalidadeRetornoMapper.toEntity(municipioLocalidadeWrapperDTO.getListMunicipioLocalidadeRetornoDTO())).thenReturn(new ArrayList<>());
    	//when(municipioLocalidadeRepository.pesquisarMunicipioLocalidade(municipioLocalidade.getCdCidade(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null)).thenReturn(municipioLocalidadeWrapperDTO);
    	//when(municipioLocalidadeRepository.pesquisarMunicipioLocalidade(null, null, null, null, municipioLocalidade.getDcCidade(), null, null, null, null, null, null, null, null, null, null, null, null, null, null)).thenReturn(municipioLocalidadeWrapperDTO);
    	when(municipioLocalidadeMapper.toEntity(this.municipioLocalidadeDto)).thenReturn(municipioLocalidade);
    	service.incluirMunicipioLocalidade(municipioLocalidadeDto, this.getTokenUsuario());
    }
    
    @Test
    public void alterarMunicipioLocalidade() {
    	when(municipioLocalidadeRepository.pesquisarAtivoPorId(municipioLocalidadeDto.getCdCidade())).thenReturn(municipioLocalidade);
    	when(municipioLocalidadeRepository.saveAndFlush(municipioLocalidade)).thenReturn(municipioLocalidade);
    	when(municipioLocalidadeMapper.toEntity(this.municipioLocalidadeDto)).thenReturn(municipioLocalidade);
    	when(municipioLocalidadeMapper.toDto(municipioLocalidade)).thenReturn(municipioLocalidadeDto);
    	service.alterarMunicipioLocalidade(municipioLocalidadeDto, this.getTokenUsuario());
    }
    
    @Test
    public void excluirMunicipioLocalidade() {
    	when(municipioLocalidadeRepository.pesquisarAtivoPorId(municipioLocalidadeDto.getCdCidade())).thenReturn(municipioLocalidade);
    	when(municipioLocalidadeRepository.saveAndFlush(municipioLocalidade)).thenReturn(municipioLocalidade);
    	service.excluirMunicipioLocalidade(municipioLocalidadeDto.getCdCidade(), this.getTokenUsuario());
    }
    
    
}
