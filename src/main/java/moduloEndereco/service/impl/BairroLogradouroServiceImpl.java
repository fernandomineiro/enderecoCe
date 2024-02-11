package moduloEndereco.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import moduloEndereco.autenticacao.JwtTokenProvider;
import moduloEndereco.model.BairroLogradouro;
import moduloEndereco.model.IdLogradouroIdLocalidade;
import moduloEndereco.repository.BairroLogradouroRepository;
import moduloEndereco.service.AuditoriaService;
import moduloEndereco.service.BairroLogradouroService;
import moduloEndereco.util.Constants;

@Service
@Transactional
public class BairroLogradouroServiceImpl implements BairroLogradouroService {

	@Autowired
	private BairroLogradouroRepository bairroLogradouroRepository;
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	@Autowired
	private AuditoriaService auditoriaService;

	@Override
	public void salvarBairroLogradouro(List<Short> idsBairro, IdLogradouroIdLocalidade idLogradouroIdLocalidade,
			String token) {
		Long idUsuario = jwtTokenProvider.buscarIdUsuario(token);
		for (Short id : idsBairro) {
			BairroLogradouro bairroLogradouro = new BairroLogradouro();
			bairroLogradouro.setEsgoto(' ');
			bairroLogradouro.setCodBairro(id);
			bairroLogradouro.setCodLocalidade(idLogradouroIdLocalidade.getCodLocalidade());
			bairroLogradouro.setCodLogradouro(idLogradouroIdLocalidade.getCodLogradouro());
			bairroLogradouroRepository.save(bairroLogradouro);
			auditoriaService.gerarAuditoria(idLogradouroIdLocalidade.getCodLogradouro().longValue(),
					Constants.EMPTY_STRING, bairroLogradouro.toJson(), 9L, "Logradouro", idUsuario);

		}

	}

	@Override
	public void excluirBairroLogradouro(IdLogradouroIdLocalidade idLogradouroIdLocalidade, String token) {
		Long idUsuario = jwtTokenProvider.buscarIdUsuario(token);
		this.excluirBairroLogradouro(
				bairroLogradouroRepository.findByCodLocalidadeAndCodLogradouro(
						idLogradouroIdLocalidade.getCodLocalidade(), idLogradouroIdLocalidade.getCodLogradouro()),
				idUsuario, idLogradouroIdLocalidade.getCodLogradouro());

	}

	@Override
	public void atualizarBairroLogradouro(List<Short> listIdBairroNovo,
			IdLogradouroIdLocalidade idLogradouroIdLocalidade, String token) {
		Long idUsuario = jwtTokenProvider.buscarIdUsuario(token);

		List<BairroLogradouro> listBairroAntigo = bairroLogradouroRepository.findByCodLocalidadeAndCodLogradouro(
				idLogradouroIdLocalidade.getCodLocalidade(), idLogradouroIdLocalidade.getCodLogradouro());

		List<Short> listIdBairroAntigo = listBairroAntigo.stream().map(e -> e.getCodBairro())
				.collect(Collectors.toList());

		for (Short idBairro : listIdBairroNovo) {
			// listBairroAntigo = listBairroAntigo.stream()
			// .filter(e -> e.getCodBairro().byteValue() !=
			// idBairro.byteValue()).collect(Collectors.toList());

			listBairroAntigo = listBairroAntigo.stream().filter(e -> e.getCodBairro().shortValue() != idBairro)
					.collect(Collectors.toList());
		}

		for (Short idBairro : listIdBairroAntigo) {
			// listIdBairroNovo = listIdBairroNovo.stream().filter(e -> e.byteValue() !=
			// idBairro.byteValue()).collect(Collectors.toList());

			listIdBairroNovo = listIdBairroNovo.stream().filter(e -> e.shortValue() != idBairro)
					.collect(Collectors.toList());
		}

		if (!listBairroAntigo.isEmpty())
			this.excluirBairroLogradouro(listBairroAntigo, idUsuario, idLogradouroIdLocalidade.getCodLogradouro());
		if (!listIdBairroNovo.isEmpty())
			this.salvarBairroLogradouro(listIdBairroNovo, idLogradouroIdLocalidade, token);
	}

	@Override
	public List<Short> listaBairro(IdLogradouroIdLocalidade idLogradouroIdLocalidade) {
		return bairroLogradouroRepository
				.findByCodLocalidadeAndCodLogradouro(idLogradouroIdLocalidade.getCodLocalidade(),
						idLogradouroIdLocalidade.getCodLogradouro())
				.stream().map(e -> e.getCodBairro()).collect(Collectors.toList());
	}

	private void excluirBairroLogradouro(List<BairroLogradouro> listBairroLogradouro, Long idUsuario,
			Short idLogradouro) {
		for (BairroLogradouro bairroLogradouro : listBairroLogradouro) {
			bairroLogradouroRepository.delete(bairroLogradouro);
			auditoriaService.gerarAuditoria(idLogradouro.longValue(), bairroLogradouro.toJson(), Constants.EMPTY_STRING,
					9L, "Logradouro", idUsuario);
		}
	}

}
