package DAO.Instagram;

import java.util.List;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

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
