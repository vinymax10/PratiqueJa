package dao.instagram;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import modelo.publicacao.ConfigPost;
import modelo.publicacao.ImagemPost;
import dao.DAO;

public class ImagemPostDAO extends DAO<ImagemPost>
{
	private static final long serialVersionUID = 1L;

	public ImagemPostDAO()
	{
		super(ImagemPost.class);
	}

	public List<ImagemPost> carrega(ConfigPost configPost, boolean feed)
	{

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<ImagemPost> query = builder.createQuery(ImagemPost.class);
		Root<ImagemPost> fromImagemPost = query.from(ImagemPost.class);

		List<Predicate> predicates = new ArrayList<>();

		predicates.add(builder.equal(fromImagemPost.get("configPost").get("id"), configPost.getId()));
		predicates.add(builder.equal(fromImagemPost.get("feed"), feed));
		
		TypedQuery<ImagemPost> typedQuery = em
		.createQuery(query.select(fromImagemPost).where(predicates.toArray(new Predicate[0])));

		List<ImagemPost> list = typedQuery.getResultList();

		return list;
	}
}
