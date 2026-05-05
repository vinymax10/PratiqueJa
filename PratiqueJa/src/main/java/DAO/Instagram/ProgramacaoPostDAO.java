package DAO.Instagram;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import DAO.DAO;
import Modelo.Instagram.ProgramacaoPost;

public class ProgramacaoPostDAO extends DAO<ProgramacaoPost>
{
	private static final long serialVersionUID = 1L;

	public ProgramacaoPostDAO()
	{
		super(ProgramacaoPost.class);
	}

	public List<ProgramacaoPost> hoje()
	{
		em.clear();
		
		LocalDate hoje=LocalDate.now();
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<ProgramacaoPost> query = builder.createQuery(ProgramacaoPost.class);
		Root<ProgramacaoPost> fromProgramacaoPost = query.from(ProgramacaoPost.class);

		Predicate predicate = builder.and();

		predicate = builder.and(predicate, builder.lessThanOrEqualTo(fromProgramacaoPost.get("data"), hoje));
		
		TypedQuery<ProgramacaoPost> typedQuery = em
		.createQuery(query.select(fromProgramacaoPost).where(predicate));

		List<ProgramacaoPost> list = typedQuery.getResultList();

		return list;
	}
}
