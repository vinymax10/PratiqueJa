package DAO.Usuario;

import java.util.List;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import Bean.Usuario.Filtro.FiltroPagamento;
import DAO.DAO;
import Modelo.Usuario.Pagamento;

public class PagamentoDAO extends DAO<Pagamento>
{
	private static final long serialVersionUID = 1L;

	public PagamentoDAO()
	{
		super(Pagamento.class);
	}

	public List<Pagamento> buscar(FiltroPagamento filtroPagamento)
	{
		em.clear();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Pagamento> query = builder.createQuery(Pagamento.class);
		Root<Pagamento> fromPagamento = query.from(Pagamento.class);

		Predicate predicate = builder.and();

		if(!filtroPagamento.getNomeUsuario().equals(""))
			predicate = builder.and(predicate, builder.like(fromPagamento.get("usuario").get("nome"), "%" + filtroPagamento.getNomeUsuario() + "%"));

		if(filtroPagamento.getId() != null)
			predicate = builder.and(predicate, builder.equal(fromPagamento.get("id"), filtroPagamento.getId()));

		if(filtroPagamento.getDataInicio() != null)
			predicate = builder.and(predicate, builder.greaterThanOrEqualTo(fromPagamento.get("data"), filtroPagamento.getDataInicio()));

		if(filtroPagamento.getDataFim() != null)
			predicate = builder.and(predicate, builder.lessThanOrEqualTo(fromPagamento.get("data"), filtroPagamento.getDataFim()));

		if(filtroPagamento.getValor() != 0)
			predicate = builder.and(predicate, builder.equal(fromPagamento.get("valor"), filtroPagamento.getValor()));
		
		if(filtroPagamento.getTipoPagamento() != null)
			predicate = builder.and(predicate, builder.equal(fromPagamento.get("tipoPagamento"), filtroPagamento.getTipoPagamento()));
		
		if(filtroPagamento.getPlano() != null)
			predicate = builder.and(predicate, builder.equal(fromPagamento.get("plano"), filtroPagamento.getPlano()));
		
		if(filtroPagamento.getPago() != null)
			predicate = builder.and(predicate, builder.equal(fromPagamento.get("pago"), filtroPagamento.getPago().booleanValue()));
		
		TypedQuery<Pagamento> typedQuery = em.createQuery(query.select(fromPagamento).where(predicate).distinct(true));
		List<Pagamento> list = typedQuery.getResultList();

		return list;
	}
	
	public boolean contem(Pagamento pagamento)
	{
		em.clear();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Pagamento> query = builder.createQuery(Pagamento.class);
		Root<Pagamento> fromPagamento = query.from(Pagamento.class);

		Predicate predicate = builder.and();
		
		predicate = builder.and(predicate, builder.equal(fromPagamento.get("data"), pagamento.getData()));
		predicate = builder.and(predicate, builder.equal(fromPagamento.get("plano"), pagamento.getPlano()));
		predicate = builder.and(predicate, builder.equal(fromPagamento.get("tipoPagamento"), pagamento.getTipoPagamento()));
		predicate = builder.and(predicate, builder.equal(fromPagamento.get("usuario").get("id"), pagamento.getUsuario().getId()));
		
		TypedQuery<Pagamento> typedQuery = em.createQuery(query.select(fromPagamento).where(predicate).distinct(true));
		List<Pagamento> list = typedQuery.getResultList();
		
		return list.size()!=0;
	}
	
}
