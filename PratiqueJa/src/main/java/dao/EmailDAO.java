package dao;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import modelo.email.Email;
import modelo.email.StatusEmail;

public class EmailDAO extends DAO<Email>
{
	private static final long serialVersionUID = 1L;

	public EmailDAO()
	{
		super(Email.class);
	}

	public List<Email> listarPendentes()
	{
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Email> query = builder.createQuery(Email.class);
		Root<Email> fromEmail = query.from(Email.class);

	    List<Predicate> predicates = new ArrayList<>();
	    
        predicates.add(builder.equal(fromEmail.get("status"), StatusEmail.PENDENTE));
	    
		TypedQuery<Email> typedQuery = em.createQuery(query.select(fromEmail)
		.where(predicates.toArray(new Predicate[0]))
		);
		List<Email> list = typedQuery.getResultList();

		return list;
	}
}
