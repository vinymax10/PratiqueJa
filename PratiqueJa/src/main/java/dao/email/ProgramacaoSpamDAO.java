package dao.email;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import dao.DAO;
import modelo.email.ProgramacaoSpam;

public class ProgramacaoSpamDAO extends DAO<ProgramacaoSpam>
{
	private static final long serialVersionUID = 1L;

	public ProgramacaoSpamDAO()
	{
		super(ProgramacaoSpam.class);
	}

	public List<ProgramacaoSpam> hoje()
	{

		LocalDate hoje=LocalDate.now();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<ProgramacaoSpam> query = builder.createQuery(ProgramacaoSpam.class);
		Root<ProgramacaoSpam> fromProgramacaoSpam = query.from(ProgramacaoSpam.class);

		List<Predicate> predicates = new ArrayList<>();

		predicates.add(builder.lessThanOrEqualTo(fromProgramacaoSpam.get("data"), hoje));

		TypedQuery<ProgramacaoSpam> typedQuery = em
		.createQuery(query.select(fromProgramacaoSpam).where(predicates.toArray(new Predicate[0])));

		List<ProgramacaoSpam> list = typedQuery.getResultList();

		return list;
	}
}
