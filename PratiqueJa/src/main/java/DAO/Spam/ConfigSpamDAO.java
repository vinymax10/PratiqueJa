package DAO.Spam;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import DAO.DAO;
import Modelo.Spam.ConfigSpam;
import Modelo.Usuario.Usuario;

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
