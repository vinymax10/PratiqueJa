package DAO.Usuario;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import Bean.Usuario.Filtro.FiltroTurma;
import DAO.DAO;
import Modelo.Usuario.Turma;

public class TurmaDAO extends DAO<Turma>
{
	private static final long serialVersionUID = 1L;

	public TurmaDAO()
	{
		super(Turma.class);
	}

	public List<Turma> buscar(FiltroTurma filtroTurma)
	{
		em.clear();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Turma> query = builder.createQuery(Turma.class);
		Root<Turma> fromTurma = query.from(Turma.class);

		Predicate predicate = builder.and();

		if(filtroTurma.getNome()!= null && !filtroTurma.getNome().equals(""))
			predicate = builder.and(predicate, builder.like(fromTurma.get("nome"), "%" + filtroTurma.getNome() + "%"));

		if(filtroTurma.getAssuntoAtual() != null)
			predicate = builder.and(predicate, builder.equal(fromTurma.get("assuntoAtual").get("id"), filtroTurma.getAssuntoAtual().getId()));

		TypedQuery<Turma> typedQuery = em.createQuery(query.select(fromTurma).where(predicate));
		List<Turma> list = typedQuery.getResultList();

		return list;
	}
	
	public List<Turma> todas()
	{
		em.clear();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Turma> query = builder.createQuery(Turma.class);
		Root<Turma> fromTurma = query.from(Turma.class);

		Predicate predicate = builder.and();

		TypedQuery<Turma> typedQuery = em.createQuery(query.select(fromTurma).where(predicate));
		List<Turma> list = typedQuery.getResultList();

		return list;
	}
	
}
