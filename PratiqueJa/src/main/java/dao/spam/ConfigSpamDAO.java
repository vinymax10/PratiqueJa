package dao.spam;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import dao.DAO;
import modelo.email.ConfigSpam;
import modelo.usuario.Usuario;

public class ConfigSpamDAO extends DAO<ConfigSpam>
{
	private static final long serialVersionUID = 1L;

	public ConfigSpamDAO()
	{
		super(ConfigSpam.class);
	}

	public ConfigSpam buscar()
	{

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<ConfigSpam> query = builder.createQuery(ConfigSpam.class);
		Root<ConfigSpam> fromConfigSpam = query.from(ConfigSpam.class);

		List<Predicate> predicates = new ArrayList<>();

		TypedQuery<ConfigSpam> typedQuery = em.createQuery(query.select(fromConfigSpam).where(predicates.toArray(new Predicate[0])));
		List<ConfigSpam> list = typedQuery.getResultList();

		if(list.size()>0)
			return list.get(0);
		
		return null;
	}
	
	public List<Usuario> usuarioSpam()
	{

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Usuario> query = builder.createQuery(Usuario.class);
		Root<Usuario> fromUsuario = query.from(Usuario.class);

		List<Predicate> predicates = new ArrayList<>();

		predicates.add(builder.equal(fromUsuario.get("recebeSpam"), true));
		
		TypedQuery<Usuario> typedQuery = em.createQuery(query.select(fromUsuario).where(predicates.toArray(new Predicate[0])));
		List<Usuario> list = typedQuery.getResultList();

		return list;
	}
}
