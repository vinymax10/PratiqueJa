package dao.instagram;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import modelo.publicacao.ConfigPost;
import modelo.publicacao.Cta;
import modelo.publicacao.FinalidadeCta;
import dao.DAO;

public class CtaDAO extends DAO<Cta>
{
	private static final long serialVersionUID = 1L;

	public CtaDAO()
	{
		super(Cta.class);
	}

	public String getAnyCta(FinalidadeCta finalidadeCta)
	{

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Cta> query = builder.createQuery(Cta.class);
		Root<Cta> fromCta = query.from(Cta.class);
		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.isNull(fromCta.<String>get("configPost")));
		predicates.add(builder.equal(fromCta.get("finalidadeCta"),finalidadeCta));

	    Expression<Double> randExpr = builder.function("RAND", Double.class);
		query.select(fromCta);
		query.where(predicates.toArray(new Predicate[0]));
		query.orderBy(builder.asc(randExpr));
		
		TypedQuery<Cta> typedQuery = em.createQuery(query);
		typedQuery.setMaxResults(1);
		List <Cta> ctas = typedQuery.getResultList();

		if(ctas.size()>0)
			return ctas.get(0).getNome();
		else
			return "";
	}
	
	
	public String getAnyCta(ConfigPost configPost)
	{

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Cta> query = builder.createQuery(Cta.class);
		Root<Cta> fromCta = query.from(Cta.class);
		
		List<Predicate> predicates = new ArrayList<>();
		Join<Cta, ConfigPost> configPostJoin = fromCta.join("configPost");

		predicates.add(builder.equal(configPostJoin.get("id"),configPost.getId()));
		predicates.add(builder.equal(fromCta.get("finalidadeCta"),configPost.getFinalidadeCta()));

	    Expression<Double> randExpr = builder.function("RAND", Double.class);
		query.select(fromCta);
		query.where(predicates.toArray(new Predicate[0]));
		query.orderBy(builder.asc(randExpr));
		
		TypedQuery<Cta> typedQuery = em.createQuery(query);
		typedQuery.setMaxResults(1);
		List <Cta> ctas = typedQuery.getResultList();

		if(ctas.size()>0)
			return ctas.get(0).getNome();
		else
			return "";
	}
	
	public List<Cta> filtrar(String nome)
	{

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Cta> query = builder.createQuery(Cta.class);
		Root<Cta> fromCta = query.from(Cta.class);

		List<Predicate> predicates = new ArrayList<>();

		if(nome != null && !nome.isBlank())
		{
			predicates.add(builder.like(fromCta.<String>get("nome"), "%" + nome + "%"));
		}

		TypedQuery<Cta> typedQuery = em.createQuery(query.select(fromCta).where(predicates.toArray(new Predicate[0])).distinct(true));
		List<Cta> list = typedQuery.getResultList();

		return list;
	}
	
	public List<Cta> listarGenericas()
	{

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Cta> query = builder.createQuery(Cta.class);
		Root<Cta> fromCta = query.from(Cta.class);

		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.isNull(fromCta.<String>get("configPost")));

		TypedQuery<Cta> typedQuery = em.createQuery(query.select(fromCta)
			.where(predicates.toArray(new Predicate[0]))
			.orderBy(builder.asc(fromCta.get("ordem"))));
		List<Cta> list = typedQuery.getResultList();

		return list;
	}

}
