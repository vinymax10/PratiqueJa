package dao.usuario;

import java.util.ArrayList;
import java.util.List;

import filtro.usuario.FiltroPagamento;
import dao.DAO;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import modelo.usuario.Pagamento;

public class PagamentoDAO extends DAO<Pagamento>
{
	private static final long serialVersionUID = 1L;

	public PagamentoDAO()
	{
		super(Pagamento.class);
	}

	public List<Pagamento> buscar(FiltroPagamento filtroPagamento)
	{

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Pagamento> query = builder.createQuery(Pagamento.class);
		Root<Pagamento> fromPagamento = query.from(Pagamento.class);

		List<Predicate> predicates = new ArrayList<>();

		if(filtroPagamento.getNomeUsuario() != null && !filtroPagamento.getNomeUsuario().isBlank())
			predicates.add(builder.like(fromPagamento.get("usuario").get("nome"), "%" + filtroPagamento.getNomeUsuario() + "%"));

		if(filtroPagamento.getId() != null)
			predicates.add(builder.equal(fromPagamento.get("id"), filtroPagamento.getId()));

		if(filtroPagamento.getDataInicio() != null)
			predicates.add(builder.greaterThanOrEqualTo(fromPagamento.get("data"), filtroPagamento.getDataInicio()));

		if(filtroPagamento.getDataFim() != null)
			predicates.add(builder.lessThanOrEqualTo(fromPagamento.get("data"), filtroPagamento.getDataFim()));

		if(filtroPagamento.getValor() != 0)
			predicates.add(builder.equal(fromPagamento.get("valor"), filtroPagamento.getValor()));
		
		if(filtroPagamento.getTipoPagamento() != null)
			predicates.add(builder.equal(fromPagamento.get("tipoPagamento"), filtroPagamento.getTipoPagamento()));
		
		if(filtroPagamento.getPlano() != null)
			predicates.add(builder.equal(fromPagamento.get("plano"), filtroPagamento.getPlano()));
		
		if(filtroPagamento.getPago() != null)
			predicates.add(builder.equal(fromPagamento.get("pago"), filtroPagamento.getPago().booleanValue()));
		
		TypedQuery<Pagamento> typedQuery = em.createQuery(query.select(fromPagamento).where(predicates.toArray(new Predicate[0])).distinct(true));
		List<Pagamento> list = typedQuery.getResultList();

		return list;
	}
	
	public boolean contem(Pagamento pagamento)
	{

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Pagamento> query = builder.createQuery(Pagamento.class);
		Root<Pagamento> fromPagamento = query.from(Pagamento.class);

		List<Predicate> predicates = new ArrayList<>();
		
		predicates.add(builder.equal(fromPagamento.get("data"), pagamento.getData()));
		predicates.add(builder.equal(fromPagamento.get("plano"), pagamento.getPlano()));
		predicates.add(builder.equal(fromPagamento.get("tipoPagamento"), pagamento.getTipoPagamento()));
		predicates.add(builder.equal(fromPagamento.get("usuario").get("id"), pagamento.getUsuario().getId()));
		
		TypedQuery<Pagamento> typedQuery = em.createQuery(query.select(fromPagamento).where(predicates.toArray(new Predicate[0])).distinct(true));
		List<Pagamento> list = typedQuery.getResultList();
		
		return list.size()!=0;
	}
	
}
