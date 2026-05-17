package dao.seguranca;

import java.util.ArrayList;
import java.util.List;

import dao.DAO;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import modelo.seguranca.PasswordResetToken;
import modelo.usuario.Usuario;

public class PasswordResetTokenDAO extends DAO<PasswordResetToken>
{
	private static final long serialVersionUID = 1L;

	public PasswordResetTokenDAO()
	{
		super(PasswordResetToken.class);
	}
	
	public List<PasswordResetToken> tokenAtivos(Usuario usuario)
	{
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<PasswordResetToken> query = builder.createQuery(PasswordResetToken.class);
		Root<PasswordResetToken> fromPasswordResetToken = query.from(PasswordResetToken.class);

	    List<Predicate> predicates = new ArrayList<>();
		
    	predicates.add( builder.equal(
    	fromPasswordResetToken.get("usuario").get("id"), usuario.getId()));
	    
    	predicates.add( builder.equal(
    	fromPasswordResetToken.get("usado"), false));
    	
		TypedQuery<PasswordResetToken> typedQuery = em.createQuery(query.select(fromPasswordResetToken)
		.where(predicates.toArray(new Predicate[0]))
		);
		List<PasswordResetToken> list = typedQuery.getResultList();

		return list;
	}
	
	public PasswordResetToken token(String token)
	{
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<PasswordResetToken> query = builder.createQuery(PasswordResetToken.class);
		Root<PasswordResetToken> fromPasswordResetToken = query.from(PasswordResetToken.class);

	    List<Predicate> predicates = new ArrayList<>();
		
    	predicates.add( builder.equal(
    	fromPasswordResetToken.get("token"),token));
	    
		TypedQuery<PasswordResetToken> typedQuery = em.createQuery(query.select(fromPasswordResetToken)
		.where(predicates.toArray(new Predicate[0]))
		);
		List<PasswordResetToken> list = typedQuery.getResultList();
		
		return list.isEmpty() ? null : list.get(0);
	}
	
}
