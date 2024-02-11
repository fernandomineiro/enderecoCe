package moduloEndereco.repository;

import java.util.List;

import moduloEndereco.model.EnderecoGis;
import moduloEndereco.repository.filter.EnderecoGisFilter;

public interface EnderecoGisRepositoryQuery {

	public List<EnderecoGis> filtrar(EnderecoGisFilter enderecoFilter);
}
