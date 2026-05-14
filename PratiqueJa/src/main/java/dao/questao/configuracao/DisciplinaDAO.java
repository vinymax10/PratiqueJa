package dao.questao.configuracao;

import java.util.ArrayList;
import java.util.List;

import dao.DAO;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import modelo.academico.Disciplina;

public class DisciplinaDAO extends DAO<Disciplina>
{
	private static final long serialVersionUID = 1L;

	public DisciplinaDAO()
	{
		super(Disciplina.class);
	}

	public List<Disciplina> filtrar(String nome)
	{

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Disciplina> query = builder.createQuery(Disciplina.class);
		Root<Disciplina> fromDisciplina = query.from(Disciplina.class);

		List<Predicate> predicates = new ArrayList<>();

		if(nome != null && !nome.isBlank())
		{
			predicates.add(builder.like(fromDisciplina.<String>get("nome"), "%" + nome + "%"));
		}

		TypedQuery<Disciplina> typedQuery = em.createQuery(query.select(fromDisciplina).where(predicates.toArray(new Predicate[0])).distinct(true));
		return typedQuery.getResultList();
	}
}
