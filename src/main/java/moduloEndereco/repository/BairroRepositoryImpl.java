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

import moduloEndereco.model.Bairro;
import moduloEndereco.repository.filter.BairroFilter;
import moduloEndereco.service.dto.BairroWrapperDTO;
import moduloEndereco.service.mapper.BairroMapper;

public class BairroRepositoryImpl  implements BairroRepositoryQuery{

	@PersistenceContext
	private EntityManager manager;
	@Autowired
	private BairroMapper bairroMapper;
	
	@Override
	public BairroWrapperDTO filtrar(BairroFilter bairroFilter, Pageable pageable) {
		BairroWrapperDTO bairroWrapperDTO = new BairroWrapperDTO();
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Bairro> criteria = builder.createQuery(Bairro.class);
		Root<Bairro> root = criteria.from(Bairro.class);
		criteria.orderBy(QueryUtils.toOrders(pageable.getSort(), root, builder));
		Predicate[] predicates = criarRestricoes(bairroFilter,builder,root);
		criteria.where(predicates);
		
		TypedQuery<Bairro> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query,pageable);
	
		bairroWrapperDTO.setListBairroDTO(bairroMapper.toDto(query.getResultList()));
		bairroWrapperDTO.setTotalRegistros(this.total(bairroFilter));
		return bairroWrapperDTO;
	}
	@Override
	public BairroWrapperDTO filtrar(BairroFilter bairroFilter) {
		BairroWrapperDTO bairroWrapperDTO = new BairroWrapperDTO();
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Bairro> criteria = builder.createQuery(Bairro.class);
		Root<Bairro> root = criteria.from(Bairro.class);
		Predicate[] predicates = criarRestricoes(bairroFilter,builder,root);
		criteria.where(predicates);
		
		TypedQuery<Bairro> query = manager.createQuery(criteria);
	
		bairroWrapperDTO.setListBairroDTO(bairroMapper.toDto(query.getResultList()));
		bairroWrapperDTO.setTotalRegistros(this.total(bairroFilter));
		return bairroWrapperDTO;
		
	}

	private Predicate[] criarRestricoes(BairroFilter bairroFilter,CriteriaBuilder builder, Root<Bairro> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		if(bairroFilter.getCdBairro()!=null) {
			predicates.add(builder.equal(builder.lower(root.get("idBairroIdLocalidade").get("cdBairro")), bairroFilter.getCdBairro()));
		}
		if(bairroFilter.getCdLocalidade()!=null) {
			predicates.add(builder.equal(builder.lower(root.get("idBairroIdLocalidade").get("cdLocalidade")), bairroFilter.getCdLocalidade()));
		}
		if (bairroFilter.getNomeBairro()!=null) {
			predicates.add(builder.like(builder.lower(root.get("nomeBairro")), "%" + bairroFilter.getNomeBairro().toLowerCase()+"%"));
		}
		predicates.add(builder.isNull(root.get("dataHoraExclusao")));
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}

	private void adicionarRestricoesDePaginacao(TypedQuery<Bairro> query,Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina =pageable.getPageSize();
		int primeiroDaPagina = paginaAtual * totalRegistrosPorPagina;
		
		
		query.setFirstResult(primeiroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);

	}
	
	private Long total(BairroFilter bairroFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Bairro> root = criteria.from(Bairro.class);
		
		Predicate[] predicates = criarRestricoes(bairroFilter, builder, root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
		
	}


	

}
