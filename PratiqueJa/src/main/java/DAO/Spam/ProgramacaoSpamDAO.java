package DAO.Spam;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import DAO.DAO;
import Modelo.Spam.ProgramacaoSpam;

public class ProgramacaoSpamDAO extends DAO<ProgramacaoSpam>
{
	private static final long serialVersionUID = 1L;

	public ProgramacaoSpamDAO()
	{
		super(ProgramacaoSpam.class);
	}

	public List<ProgramacaoSpam> hoje()
	{
		em.clear();
		
		LocalDate hoje=LocalDate.now();
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<ProgramacaoSpam> query = builder.createQuery(ProgramacaoSpam.class);
		Root<ProgramacaoSpam> fromProgramacaoSpam = query.from(ProgramacaoSpam.class);

		Predicate predicate = builder.and();

		predicate = builder.and(predicate, builder.lessThanOrEqualTo(fromProgramacaoSpam.get("data"), hoje));
		
		TypedQuery<ProgramacaoSpam> typedQuery = em
		.createQuery(query.select(fromProgramacaoSpam).where(predicate));

		List<ProgramacaoSpam> list = typedQuery.getResultList();

		return list;
	}
}
