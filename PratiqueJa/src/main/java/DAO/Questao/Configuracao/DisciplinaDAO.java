package DAO.Questao.Configuracao;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import DAO.DAO;
import Modelo.Questao.Configuracao.Disciplina;

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
