package moduloEndereco.modulodeendereco.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;

import moduloEndereco.autenticacao.JwtTokenProvider;
import moduloEndereco.model.AreaAtuacao;
import moduloEndereco.model.AreaAtuacaoUsuario;
import moduloEndereco.modulodeendereco.config.ConfigTest;
import moduloEndereco.rabbitmq.produtores.Produtor;
import moduloEndereco.repository.AreaAtuacaoRepository;
import moduloEndereco.repository.AreaAtuacaoUsuarioRepository;
import moduloEndereco.service.AreaAtuacaoBairroService;
import moduloEndereco.service.AuditoriaService;
import moduloEndereco.service.dto.AreaAtuacaoBairroDTO;
import moduloEndereco.service.dto.AreaAtuacaoDTO;
import moduloEndereco.service.dto.BairroLocalidadeDTO;
import moduloEndereco.service.impl.AreaAtuacaoServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AreaAtuacaoIServiceImplTest extends ConfigTest {

	@SpyBean
	private AreaAtuacaoServiceImpl service;
	@MockBean
	private AreaAtuacaoRepository areaAtuacaoRepository;
	@MockBean
	private AreaAtuacaoUsuarioRepository areaAtuacaoUsuarioRepository;
	@MockBean
	private AuditoriaService auditoriaService;
	@MockBean
	private AreaAtuacaoBairroService areaAtuacaoBairroService;
	@MockBean
	private JwtTokenProvider jwtTokenProvider;
	@MockBean
	private Produtor produtor;

	private AreaAtuacaoDTO areaAtuacaoDto;

	private AreaAtuacao areaAtuacao;
	
	private List<AreaAtuacaoUsuario> listaAreaAtuacao = new ArrayList<AreaAtuacaoUsuario>();
	
	private AreaAtuacaoBairroDTO areaAtuacaoBairroDTO;
	
	@Before
	public void setup() {	
		this.areaAtuacaoDto= new AreaAtuacaoDTO();
		this.areaAtuacaoDto.setId(1L);
		this.areaAtuacaoDto.setNome("teste");
		
		this.areaAtuacao=new AreaAtuacao();
		this.areaAtuacao.setId(1L);
		this.areaAtuacao.setNome("teste");
		
		this.areaAtuacaoBairroDTO=new AreaAtuacaoBairroDTO();
		this.areaAtuacaoBairroDTO.setIdAreaAtuacao(areaAtuacao.getId());
		this.areaAtuacaoBairroDTO.setNomeAreaAtuacao(areaAtuacao.getNome());
		BairroLocalidadeDTO bairroLocalidadeDTO= new BairroLocalidadeDTO();
		bairroLocalidadeDTO.setCdBairro((short)1);
		bairroLocalidadeDTO.setCdLocalidade((short)500);
		List<BairroLocalidadeDTO> listLocalidadeBairro = new ArrayList<>();
		listLocalidadeBairro.add(bairroLocalidadeDTO);
	}
	
	@Test
	public void salvar() {
		when(areaAtuacaoRepository.save(any(AreaAtuacao.class))).thenReturn(areaAtuacao);
		service.salvar(areaAtuacaoBairroDTO, this.getTokenUsuario());
	}
	
	@Test
	public void excluir() {
		when(areaAtuacaoRepository.findById(1L)).thenReturn(Optional.of(areaAtuacao));
		when(areaAtuacaoUsuarioRepository.findByAreaAtuacao(any(AreaAtuacao.class))).thenReturn(listaAreaAtuacao);
		when(areaAtuacaoRepository.save(any(AreaAtuacao.class))).thenReturn(areaAtuacao);
		service.excluir(1L, this.getTokenUsuario());
	}
	
	@Test
	public void atualizar() {
		when(areaAtuacaoRepository.findById(1L)).thenReturn(Optional.of(areaAtuacao));
		service.atualizar(areaAtuacaoBairroDTO, this.getTokenUsuario());
		
	}
}
