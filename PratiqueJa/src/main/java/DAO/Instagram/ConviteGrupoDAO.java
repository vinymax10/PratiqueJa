package DAO.Instagram;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import DAO.DAO;
import Modelo.Instagram.ConviteGrupo;

public class ConviteGrupoDAO extends DAO<ConviteGrupo>
{
	private static final long serialVersionUID = 1L;

	public ConviteGrupoDAO()
	{
		super(ConviteGrupo.class);
	}

	public List<ConviteGrupo> filtrar(String nome)
	{
		em.clear();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<ConviteGrupo> query = builder.createQuery(ConviteGrupo.class);
		Root<ConviteGrupo> fromConviteGrupo = query.from(ConviteGrupo.class);

		Predicate predicate = builder.and();

		if(!nome.equals(""))
		{
			predicate = builder.and(predicate, builder.like(fromConviteGrupo.<String>get("nome"), "%" + nome + "%"));
		}

		TypedQuery<ConviteGrupo> typedQuery = em.createQuery(query.select(fromConviteGrupo).where(predicate).distinct(true));
		List<ConviteGrupo> list = typedQuery.getResultList();

		return list;
	}

}
