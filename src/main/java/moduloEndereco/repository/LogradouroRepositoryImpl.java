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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.query.QueryUtils;

import moduloEndereco.model.Logradouro;
import moduloEndereco.repository.filter.LogradouroFilter;
import moduloEndereco.service.dto.LogradouroWrapperDTO;
import moduloEndereco.service.mapper.LogradouroMapper;



public class LogradouroRepositoryImpl implements LogradouroRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;
	@Autowired
	private LogradouroMapper logradouroMapper;

	@Override
	public LogradouroWrapperDTO filtrar(LogradouroFilter logradouroFilter, Pageable pageable) {
		LogradouroWrapperDTO logradouroWrapperDTO = new LogradouroWrapperDTO();
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Logradouro> criteria = builder.createQuery(Logradouro.class);
		Root<Logradouro> root = criteria.from(Logradouro.class);
		criteria.orderBy(QueryUtils.toOrders(pageable.getSort(), root, builder));
		Predicate[] predicates = criarRestricoes(logradouroFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<Logradouro> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);

		logradouroWrapperDTO.setListLogradouroDTO(logradouroMapper.toDto(query.getResultList()));
		logradouroWrapperDTO.setTotalRegistros(this.total(logradouroFilter));
		return logradouroWrapperDTO;
	}


	@Override
	public LogradouroWrapperDTO filtrar(LogradouroFilter logradouroFilter) {
		LogradouroWrapperDTO logradouroWrapperDTO = new LogradouroWrapperDTO();
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Logradouro> criteria = builder.createQuery(Logradouro.class);
		Root<Logradouro> root = criteria.from(Logradouro.class);
		Predicate[] predicates = criarRestricoes(logradouroFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<Logradouro> query = manager.createQuery(criteria);

		logradouroWrapperDTO.setListLogradouroDTO(logradouroMapper.toDto(query.getResultList()));
		logradouroWrapperDTO.setTotalRegistros(this.total(logradouroFilter));
		return logradouroWrapperDTO;
	}
	private Predicate[] criarRestricoes(LogradouroFilter logradouroFilter, CriteriaBuilder builder,
			Root<Logradouro> root) {
		List<Predicate> predicates = new ArrayList<>();

		if (logradouroFilter.getCodLogradouro() != null) {
			predicates.add(builder.equal(root.get("idLogradouroIdLocalidade").get("codLogradouro"),
					logradouroFilter.getCodLogradouro()));
		}
		if (logradouroFilter.getCodLocalidade() != null) {
			predicates.add(builder.equal(root.get("idLogradouroIdLocalidade").get("codLocalidade"),
					logradouroFilter.getCodLocalidade()));
		}
		if (logradouroFilter.getNomeLogradouro() != null) {
			predicates.add(builder.like(builder.lower(root.get("nomeLogradouro")),
					"%" + logradouroFilter.getNomeLogradouro().toLowerCase() + "%"));
		}

		if (logradouroFilter.getSiglaLogradouro() != null) {
			predicates.add(builder.like(builder.lower(root.get("siglaLogradouro")),
					"%" + logradouroFilter.getSiglaLogradouro().toLowerCase() + "%"));
		}
		predicates.add(builder.isNull(root.get("dataHoraExclusao")));

		return predicates.toArray(new Predicate[predicates.size()]);
	}

	private void adicionarRestricoesDePaginacao(TypedQuery<Logradouro> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroDaPagina = paginaAtual * totalRegistrosPorPagina;

		query.setFirstResult(primeiroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);

	}

	private Long total(LogradouroFilter logradouroFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Logradouro> root = criteria.from(Logradouro.class);

		Predicate[] predicates = criarRestricoes(logradouroFilter, builder, root);
		criteria.where(predicates);

		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();

	}


}
