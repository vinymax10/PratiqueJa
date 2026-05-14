package dao.questao.configuracao;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import dao.DAO;
import modelo.academico.Ano;

public class AnoDAO extends DAO<Ano>
{
	private static final long serialVersionUID = 1L;

	public AnoDAO()
	{
		super(Ano.class);
	}

	public List<Ano> filtrar(String nome)
	{
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Ano> query = builder.createQuery(Ano.class);
		Root<Ano> fromAno = query.from(Ano.class);

		List<Predicate> predicates = new ArrayList<>();

		if(nome != null && !nome.isBlank())
		{
			predicates.add(builder.like(fromAno.<String>get("nome"), "%" + nome + "%"));
		}

		TypedQuery<Ano> typedQuery = em.createQuery(query.select(fromAno).where(predicates.toArray(new Predicate[0])).distinct(true));
		List<Ano> list = typedQuery.getResultList();

		return list;
	}

}
