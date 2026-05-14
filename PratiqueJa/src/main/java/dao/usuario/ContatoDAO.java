package dao.usuario;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import dao.DAO;
import modelo.usuario.Contato;
import filtro.usuario.FiltroContato;

public class ContatoDAO extends DAO<Contato>
{
	private static final long serialVersionUID = 1L;

	public ContatoDAO()
	{
		super(Contato.class);
	}

	public List<Contato> buscar(FiltroContato filtro)
	{
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Contato> query = builder.createQuery(Contato.class);
		Root<Contato> fromContato = query.from(Contato.class);

		List<Predicate> predicates = new ArrayList<>();

		if(filtro.getNomeUsuario() != null && !filtro.getNomeUsuario().isBlank())
		{
			predicates.add(builder.like(fromContato.get("nomeUsuario"), "%" + filtro.getNomeUsuario() + "%"));
		}
		
		if(filtro.getEmail() != null && !filtro.getEmail().isBlank())
		{
			predicates.add(builder.like(fromContato.get("email"), "%" + filtro.getEmail() + "%"));
		}
		
		if(filtro.getId() != null)
			predicates.add(builder.equal(fromContato.get("id"), filtro.getId()));

		if(filtro.getMensagem() != null && !filtro.getMensagem().isBlank())
			predicates.add(builder.like(fromContato.get("mensagem"), "%" + filtro.getMensagem() + "%"));

		if(filtro.getAssunto() != null && !filtro.getAssunto().isBlank())
			predicates.add(builder.like(fromContato.get("assunto"), "%" + filtro.getAssunto() + "%"));

		if(filtro.getPeriodo() != null)
	    {
	    	if(filtro.getPeriodo().size()>0)
		    	predicates.add(builder.greaterThanOrEqualTo(
		    	fromContato.get("data"), filtro.getPeriodo().get(0)));
	    	
	    	if(filtro.getPeriodo().size()>1)
	    		predicates.add(builder.lessThanOrEqualTo(
	    		fromContato.get("data"), filtro.getPeriodo().get(1)));
	    }
		
		if(filtro.getRespondido() != null)
			predicates.add(builder.equal(fromContato.get("respondido"), filtro.getRespondido().booleanValue()));

		TypedQuery<Contato> typedQuery = em.createQuery(query.select(fromContato).where(predicates.toArray(new Predicate[0])));

		List<Contato> list = typedQuery.getResultList();

		return list;
	}
}
