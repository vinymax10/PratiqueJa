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

	public List<Pagamento> buscar(FiltroPagamento filtro)
	{

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Pagamento> query = builder.createQuery(Pagamento.class);
		Root<Pagamento> fromPagamento = query.from(Pagamento.class);

		List<Predicate> predicates = new ArrayList<>();

		if(filtro.getNomeUsuario() != null && !filtro.getNomeUsuario().isBlank())
			predicates.add(builder.like(fromPagamento.get("usuario").get("nome"), "%" + filtro.getNomeUsuario() + "%"));

		if(filtro.getId() != null)
			predicates.add(builder.equal(fromPagamento.get("id"), filtro.getId()));

		if(filtro.getPeriodo() != null)
	    {
	    	if(filtro.getPeriodo().size()>0)
		    	predicates.add(builder.greaterThanOrEqualTo(
		    	fromPagamento.get("data"), filtro.getPeriodo().get(0)));
	    	
	    	if(filtro.getPeriodo().size()>1)
	    		predicates.add(builder.lessThanOrEqualTo(
	    		fromPagamento.get("data"), filtro.getPeriodo().get(1)));
	    }
		
		if(filtro.getValor() != null)
			predicates.add(builder.equal(fromPagamento.get("valor"), filtro.getValor()));
		
		if(filtro.getTipoPagamento() != null)
			predicates.add(builder.equal(fromPagamento.get("tipoPagamento"), filtro.getTipoPagamento()));
		
		if(filtro.getPlano() != null)
			predicates.add(builder.equal(fromPagamento.get("plano"), filtro.getPlano()));
		
		if(filtro.getPago() != null)
			predicates.add(builder.equal(fromPagamento.get("pago"), filtro.getPago().booleanValue()));
		
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
