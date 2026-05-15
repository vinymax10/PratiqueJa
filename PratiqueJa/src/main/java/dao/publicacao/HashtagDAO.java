package dao.publicacao;

import java.util.ArrayList;
import java.util.List;

import dao.DAO;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import modelo.publicacao.Hashtag;

public class HashtagDAO extends DAO<Hashtag>
{
	private static final long serialVersionUID = 1L;

	public HashtagDAO()
	{
		super(Hashtag.class);
	}

	public String getAny(int number)
	{

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Hashtag> query = builder.createQuery(Hashtag.class);
		Root<Hashtag> fromHashtag = query.from(Hashtag.class);

		Expression<Double> randExpr = builder.function("RAND", Double.class);
		query.select(fromHashtag);
		query.orderBy(builder.asc(randExpr));

		TypedQuery<Hashtag> typedQuery = em.createQuery(query);
		typedQuery.setMaxResults(number);
		List<Hashtag> list = typedQuery.getResultList();

		String resultado="";
		for(Hashtag hashtag : list)
			resultado+=hashtag.getNome()+" ";

		return resultado;
	}

	public List<Hashtag> filtrar(String nome)
	{

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Hashtag> query = builder.createQuery(Hashtag.class);
		Root<Hashtag> fromHashtag = query.from(Hashtag.class);

		List<Predicate> predicates = new ArrayList<>();

		if(nome != null && !nome.isBlank())
		{
			predicates.add(builder.like(fromHashtag.<String>get("nome"), "%" + nome + "%"));
		}

		TypedQuery<Hashtag> typedQuery = em.createQuery(query.select(fromHashtag).where(predicates.toArray(new Predicate[0])).distinct(true));
		List<Hashtag> list = typedQuery.getResultList();

		return list;
	}

}
