package dao.instagram;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import modelo.publicacao.ProgramacaoPost;
import dao.DAO;

public class ProgramacaoPostDAO extends DAO<ProgramacaoPost>
{
	private static final long serialVersionUID = 1L;

	public ProgramacaoPostDAO()
	{
		super(ProgramacaoPost.class);
	}

	public List<ProgramacaoPost> hoje()
	{
		
		LocalDate hoje=LocalDate.now();
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<ProgramacaoPost> query = builder.createQuery(ProgramacaoPost.class);
		Root<ProgramacaoPost> fromProgramacaoPost = query.from(ProgramacaoPost.class);

		List<Predicate> predicates = new ArrayList<>();

		predicates.add(builder.lessThanOrEqualTo(fromProgramacaoPost.get("data"), hoje));
		
		TypedQuery<ProgramacaoPost> typedQuery = em
		.createQuery(query.select(fromProgramacaoPost).where(predicates.toArray(new Predicate[0])));

		List<ProgramacaoPost> list = typedQuery.getResultList();

		return list;
	}
}
