package dao.usuario;

import java.util.List;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import dao.DAO;
import modelo.usuario.Contato;
import bean.usuario.filtro.FiltroContato;

public class ContatoDAO extends DAO<Contato>
{
	private static final long serialVersionUID = 1L;

	public ContatoDAO()
	{
		super(Contato.class);
	}

	public List<Contato> buscar(FiltroContato filtroContato)
	{
		em.clear();
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Contato> query = builder.createQuery(Contato.class);
		Root<Contato> fromContato = query.from(Contato.class);

		Predicate predicate = builder.and();

		if(!filtroContato.getNomeUsuario().equals(""))
		{
			predicate = builder.and(predicate, 
			builder.like(fromContato.get("nomeUsuario"), "%" + filtroContato.getNomeUsuario() + "%"));
		}
		
		if(!filtroContato.getEmail().equals(""))
		{
			predicate = builder.and(predicate, 
			builder.like(fromContato.get("email"), "%" + filtroContato.getEmail() + "%"));
		}
		
		if(filtroContato.getId() != null)
			predicate = builder.and(predicate, builder.equal(fromContato.get("id"), filtroContato.getId()));

		if(!filtroContato.getMensagem().equals(""))
			predicate = builder.and(predicate, builder.like(fromContato.get("mensagem"), "%" + filtroContato.getMensagem() + "%"));

		if(!filtroContato.getAssunto().equals(""))
			predicate = builder.and(predicate, builder.like(fromContato.get("assunto"), "%" + filtroContato.getAssunto() + "%"));

		if(filtroContato.getDataInicio() != null)
			predicate = builder.and(predicate, builder.greaterThanOrEqualTo(fromContato.get("data"), filtroContato.getDataInicio()));

		if(filtroContato.getDataFim() != null)
			predicate = builder.and(predicate, builder.lessThanOrEqualTo(fromContato.get("data"), filtroContato.getDataFim()));

		if(filtroContato.getRespondido() != null)
			predicate = builder.and(predicate, builder.equal(fromContato.get("respondido"), filtroContato.getRespondido().booleanValue()));

		TypedQuery<Contato> typedQuery = em.createQuery(query.select(fromContato).where(predicate));

		List<Contato> list = typedQuery.getResultList();

		return list;
	}
}
