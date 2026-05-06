package dao.spam;

import java.util.List;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import dao.DAO;
import modelo.spam.ConfigSpam;
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
		em.clear();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<ConfigSpam> query = builder.createQuery(ConfigSpam.class);
		Root<ConfigSpam> fromConfigSpam = query.from(ConfigSpam.class);

		Predicate predicate = builder.and();

		TypedQuery<ConfigSpam> typedQuery = em.createQuery(query.select(fromConfigSpam).where(predicate));
		List<ConfigSpam> list = typedQuery.getResultList();

		if(list.size()>0)
			return list.get(0);
		
		return null;
	}
	
	public List<Usuario> usuarioSpam()
	{
		em.clear();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Usuario> query = builder.createQuery(Usuario.class);
		Root<Usuario> fromUsuario = query.from(Usuario.class);

		Predicate predicate = builder.and();

		predicate = builder.and(predicate, builder.equal(fromUsuario.get("recebeSpam"), true));
		
		TypedQuery<Usuario> typedQuery = em.createQuery(query.select(fromUsuario).where(predicate));
		List<Usuario> list = typedQuery.getResultList();

		return list;
	}
}
