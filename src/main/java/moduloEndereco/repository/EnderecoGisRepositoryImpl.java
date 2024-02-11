package moduloEndereco.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import moduloEndereco.model.EnderecoGis;
import moduloEndereco.repository.filter.EnderecoGisFilter;

public class EnderecoGisRepositoryImpl implements EnderecoGisRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<EnderecoGis> filtrar(EnderecoGisFilter enderecoGisFilter) {

		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<EnderecoGis> criteria = builder.createQuery(EnderecoGis.class);
		Root<EnderecoGis> root = criteria.from(EnderecoGis.class);

		Predicate[] predicates = criarRestricoes(enderecoGisFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<EnderecoGis> query = manager.createQuery(criteria);

		return query.getResultList();
	}

	private Predicate[] criarRestricoes(EnderecoGisFilter enderecoGisFilter, CriteriaBuilder builder,
			Root<EnderecoGis> root) {
		List<Predicate> predicates = new ArrayList<>();

		predicates.add(builder.between(root.get("dtInclusao"), enderecoGisFilter.getDataInicioI(),
				enderecoGisFilter.getDataFimI()));

		if (enderecoGisFilter.getMatriculaImovel() != null) {
			predicates.add(builder.equal(root.get("matriculaImovel"), enderecoGisFilter.getMatriculaImovel()));
		}
		
		

		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
