package DAO;

import java.util.List;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import Modelo.Email;

public class EmailDAO extends DAO<Email>
{
	private static final long serialVersionUID = 1L;

	public EmailDAO()
	{
		super(Email.class);

	}

	public List<Email> listarTudo()
	{
		em.clear();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Email> query = builder.createQuery(Email.class);
		Root<Email> fromEmail = query.from(Email.class);

		Predicate predicate = builder.and();

		TypedQuery<Email> typedQuery = em.createQuery(query.select(fromEmail).where(predicate).orderBy(builder.asc(fromEmail.get("id"))));
		List<Email> list = typedQuery.getResultList();

		return list;
	}
}
