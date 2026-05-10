package dao.configuracao;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import dao.DAO;
import modelo.configuracao.Edicao;

public class EdicaoDAO extends DAO<Edicao>
{
	private static final long serialVersionUID = 1L;

	public EdicaoDAO()
	{
		super(Edicao.class);
	}

	public Edicao buscar()
	{

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Edicao> query = builder.createQuery(Edicao.class);
		Root<Edicao> fromEdicao = query.from(Edicao.class);

		List<Predicate> predicates = new ArrayList<>();

		TypedQuery<Edicao> typedQuery = em.createQuery(query.select(fromEdicao).where(predicates.toArray(new Predicate[0])));
		List<Edicao> list = typedQuery.getResultList();

		if(list.size()>0)
			return list.get(0);
		
		return null;
	}
}
