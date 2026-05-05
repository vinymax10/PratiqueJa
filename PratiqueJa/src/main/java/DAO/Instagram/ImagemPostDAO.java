package DAO.Instagram;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import DAO.DAO;
import Modelo.Instagram.ConfigPost;
import Modelo.Instagram.ImagemPost;

public class ImagemPostDAO extends DAO<ImagemPost>
{
	private static final long serialVersionUID = 1L;

	public ImagemPostDAO()
	{
		super(ImagemPost.class);
	}

	public List<ImagemPost> carrega(ConfigPost configPost, boolean feed)
	{
		em.clear();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<ImagemPost> query = builder.createQuery(ImagemPost.class);
		Root<ImagemPost> fromImagemPost = query.from(ImagemPost.class);

		Predicate predicate = builder.and();

		predicate = builder.and(predicate, builder.equal(fromImagemPost.get("configPost").get("id"), configPost.getId()));
		predicate = builder.and(predicate, builder.equal(fromImagemPost.get("feed"), feed));
		
		TypedQuery<ImagemPost> typedQuery = em
		.createQuery(query.select(fromImagemPost).where(predicate));

		List<ImagemPost> list = typedQuery.getResultList();

		return list;
	}
}
