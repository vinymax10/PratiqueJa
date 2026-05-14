package dao.instagram;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import modelo.publicacao.ConviteGrupo;
import dao.DAO;

public class ConviteGrupoDAO extends DAO<ConviteGrupo>
{
	private static final long serialVersionUID = 1L;

	public ConviteGrupoDAO()
	{
		super(ConviteGrupo.class);
	}

	public List<ConviteGrupo> filtrar(String nome)
	{

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<ConviteGrupo> query = builder.createQuery(ConviteGrupo.class);
		Root<ConviteGrupo> fromConviteGrupo = query.from(ConviteGrupo.class);

		List<Predicate> predicates = new ArrayList<>();

		if(nome != null && !nome.isBlank())
		{
			predicates.add(builder.like(fromConviteGrupo.<String>get("nome"), "%" + nome + "%"));
		}

		TypedQuery<ConviteGrupo> typedQuery = em.createQuery(query.select(fromConviteGrupo).where(predicates.toArray(new Predicate[0])).distinct(true));
		List<ConviteGrupo> list = typedQuery.getResultList();

		return list;
	}

}
