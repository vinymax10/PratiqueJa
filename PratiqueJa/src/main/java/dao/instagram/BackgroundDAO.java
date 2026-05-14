package dao.instagram;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import modelo.publicacao.Background;
import dao.DAO;

public class BackgroundDAO extends DAO<Background>
{
	private static final long serialVersionUID = 1L;

	public BackgroundDAO()
	{
		super(Background.class);
	}

	public List<Background> carregar(boolean feed)
	{

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Background> query = builder.createQuery(Background.class);
		Root<Background> fromBackground = query.from(Background.class);

		List<Predicate> predicates = new ArrayList<>();

		predicates.add(builder.equal(fromBackground.get("feed"), feed));

		TypedQuery<Background> typedQuery = em.createQuery(query.select(fromBackground).where(predicates.toArray(new Predicate[0])).distinct(true));
		List<Background> list = typedQuery.getResultList();

		return list;
	}
}
