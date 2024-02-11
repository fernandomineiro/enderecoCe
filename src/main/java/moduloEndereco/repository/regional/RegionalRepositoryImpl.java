package moduloEndereco.repository.regional;

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

import moduloEndereco.model.Regional;
import moduloEndereco.repository.filter.RegionalFilter;
import moduloEndereco.service.dto.RegionalWrapperDTO;
import moduloEndereco.service.mapper.RegionalMapper;

public class RegionalRepositoryImpl implements RegionalRepositoryQuery {

	
	@PersistenceContext
	private EntityManager manager;
	@Autowired
	private RegionalMapper regionalMapper;
	
	@Override
	public RegionalWrapperDTO filtrar(RegionalFilter regionalFilter, Pageable pageable) {
		RegionalWrapperDTO regionalWrapperDTO = new RegionalWrapperDTO();
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Regional> criteria = builder.createQuery(Regional.class);
		Root<Regional> root = criteria.from(Regional.class);
		criteria.orderBy(QueryUtils.toOrders(pageable.getSort(), root, builder));
		Predicate[] predicates = criarRestricoes(regionalFilter,builder,root);
		criteria.where(predicates);
		TypedQuery<Regional> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query,pageable);
		regionalWrapperDTO.setListRegionalDTO(regionalMapper.toDto(query.getResultList()));
		regionalWrapperDTO.setTotalRegistros(this.total(regionalFilter));
		return regionalWrapperDTO;
	}
	

	private Predicate[] criarRestricoes(RegionalFilter regionalFilter,CriteriaBuilder builder, Root<Regional> root) {
		List<Predicate> predicates = new ArrayList<>();
		if(regionalFilter.getCodRegional() != null) {
			predicates.add(builder.equal(builder.lower(root.get("codRegional")), regionalFilter.getCodRegional()));
		}	
		if (regionalFilter.getNomeRegional()!=null) {
			predicates.add(builder.like(builder.lower(root.get("nomeRegional")), "%" + regionalFilter.getNomeRegional().toLowerCase()+"%"));
		}
		predicates.add(builder.isNull(root.get("dataRemocao")));
		return predicates.toArray(new Predicate[predicates.size()]);
	}

	private void adicionarRestricoesDePaginacao(TypedQuery<Regional> query,Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina =pageable.getPageSize();
		int primeiroDaPagina = paginaAtual * totalRegistrosPorPagina;
		
		
		query.setFirstResult(primeiroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);

	}
	
	private Long total(RegionalFilter regionalFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Regional> root = criteria.from(Regional.class);
		
		Predicate[] predicates = criarRestricoes(regionalFilter, builder, root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
		
	}

}
