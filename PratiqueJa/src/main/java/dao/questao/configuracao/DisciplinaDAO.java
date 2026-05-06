package dao.questao.configuracao;

import java.util.List;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import dao.DAO;
import modelo.questao.configuracao.Disciplina;

public class DisciplinaDAO extends DAO<Disciplina>
{
	private static final long serialVersionUID = 1L;

	public DisciplinaDAO()
	{
		super(Disciplina.class);
	}

	public List<Disciplina> filtrar(String nome)
	{
		em.clear();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Disciplina> query = builder.createQuery(Disciplina.class);
		Root<Disciplina> fromDisciplina = query.from(Disciplina.class);

		Predicate predicate = builder.and();

		if(!nome.equals(""))
		{
			predicate = builder.and(predicate, builder.like(fromDisciplina.<String>get("nome"), "%" + nome + "%"));
		}

		TypedQuery<Disciplina> typedQuery = em.createQuery(query.select(fromDisciplina).where(predicate).distinct(true));
		List<Disciplina> list = typedQuery.getResultList();

		return list;
	}

}
