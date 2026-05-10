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

	public List<Contato> buscar(FiltroContato filtroContato)
	{
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Contato> query = builder.createQuery(Contato.class);
		Root<Contato> fromContato = query.from(Contato.class);

		List<Predicate> predicates = new ArrayList<>();

		if(filtroContato.getNomeUsuario() != null && !filtroContato.getNomeUsuario().isBlank())
		{
			predicates.add(builder.like(fromContato.get("nomeUsuario"), "%" + filtroContato.getNomeUsuario() + "%"));
		}
		
		if(filtroContato.getEmail() != null && !filtroContato.getEmail().isBlank())
		{
			predicates.add(builder.like(fromContato.get("email"), "%" + filtroContato.getEmail() + "%"));
		}
		
		if(filtroContato.getId() != null)
			predicates.add(builder.equal(fromContato.get("id"), filtroContato.getId()));

		if(filtroContato.getMensagem() != null && !filtroContato.getMensagem().isBlank())
			predicates.add(builder.like(fromContato.get("mensagem"), "%" + filtroContato.getMensagem() + "%"));

		if(filtroContato.getAssunto() != null && !filtroContato.getAssunto().isBlank())
			predicates.add(builder.like(fromContato.get("assunto"), "%" + filtroContato.getAssunto() + "%"));

		if(filtroContato.getDataInicio() != null)
			predicates.add(builder.greaterThanOrEqualTo(fromContato.get("data"), filtroContato.getDataInicio()));

		if(filtroContato.getDataFim() != null)
			predicates.add(builder.lessThanOrEqualTo(fromContato.get("data"), filtroContato.getDataFim()));

		if(filtroContato.getRespondido() != null)
			predicates.add(builder.equal(fromContato.get("respondido"), filtroContato.getRespondido().booleanValue()));

		TypedQuery<Contato> typedQuery = em.createQuery(query.select(fromContato).where(predicates.toArray(new Predicate[0])));

		List<Contato> list = typedQuery.getResultList();

		return list;
	}
}
