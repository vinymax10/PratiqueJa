package DAO.Instagram;

import java.util.List;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import DAO.DAO;
import Modelo.Instagram.Background;

public class BackgroundDAO extends DAO<Background>
{
	private static final long serialVersionUID = 1L;

	public BackgroundDAO()
	{
		super(Background.class);
	}

	public List<Background> carregar(boolean feed)
	{
		em.clear();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Background> query = builder.createQuery(Background.class);
		Root<Background> fromBackground = query.from(Background.class);

		Predicate predicate = builder.and();

		predicate = builder.and(predicate, builder.equal(fromBackground.get("feed"), feed));

		TypedQuery<Background> typedQuery = em.createQuery(query.select(fromBackground).where(predicate).distinct(true));
		List<Background> list = typedQuery.getResultList();

		return list;
	}
}
