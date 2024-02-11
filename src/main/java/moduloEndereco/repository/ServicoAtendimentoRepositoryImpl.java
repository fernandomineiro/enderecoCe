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

import moduloEndereco.model.ServicoAtendimento;
import moduloEndereco.repository.filter.ServicoAtendimentoGisFilter;

public class ServicoAtendimentoRepositoryImpl implements ServicoAtendimentoRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<ServicoAtendimento> filtrarSS(ServicoAtendimentoGisFilter servicoAtendimentoGisFilter) {

		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ServicoAtendimento> criteria = builder.createQuery(ServicoAtendimento.class);
		Root<ServicoAtendimento> root = criteria.from(ServicoAtendimento.class);

		Predicate[] predicates = criarRestricoes(servicoAtendimentoGisFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<ServicoAtendimento> query = manager.createQuery(criteria);

		return query.getResultList();
	}

	private Predicate[] criarRestricoes(ServicoAtendimentoGisFilter servicoAtendimentoGisFilter,
			CriteriaBuilder builder, Root<ServicoAtendimento> root) {
		List<Predicate> predicates = new ArrayList<>();

		predicates.add(
				builder.between(root.get("dataInclusao"), servicoAtendimentoGisFilter.getDataInicio().atStartOfDay(),
						servicoAtendimentoGisFilter.getDataFim().plusDays(1).atStartOfDay()));

		if (servicoAtendimentoGisFilter.getMatriculaImovel() != null) {
			predicates
					.add(builder.equal(root.get("matriculaImovel"), servicoAtendimentoGisFilter.getMatriculaImovel()));
		}

		if (servicoAtendimentoGisFilter.getCdAtendimento() != null) {
			predicates.add(builder.equal(root.get("cdAtendimento"), servicoAtendimentoGisFilter.getCdAtendimento()));
		}

		if (servicoAtendimentoGisFilter.getRefAtendimento() != null) {
			predicates.add(builder.equal(root.get("refAtendimento"), servicoAtendimentoGisFilter.getRefAtendimento()));
		}

		if (servicoAtendimentoGisFilter.getSequencial() != null) {
			predicates.add(builder.equal(root.get("sequencial"), servicoAtendimentoGisFilter.getSequencial()));
		}

		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
