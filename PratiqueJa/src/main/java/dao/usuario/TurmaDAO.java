package dao.usuario;

import java.util.List;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import dao.DAO;
import modelo.usuario.Turma;
import bean.usuario.filtro.FiltroTurma;

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
