package dao.academico;

import java.util.ArrayList;
import java.util.List;

import dao.DAO;
import filtro.questao.FiltroAssunto;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import modelo.academico.Assunto;

public class AssuntoDAO extends DAO<Assunto>
{
	private static final long serialVersionUID = 1L;

	public AssuntoDAO()
	{
		super(Assunto.class);
	}

	public List<Assunto> buscar(FiltroAssunto filtro)
	{
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Assunto> query = builder.createQuery(Assunto.class);
		Root<Assunto> fromAssunto = query.from(Assunto.class);

		List<Predicate> predicates = new ArrayList<>();

		if(filtro.getNome() != null && !filtro.getNome().isBlank())
			predicates.add(builder.like(
				builder.upper(fromAssunto.get("nome")), "%" + filtro.getNome().toUpperCase() + "%"));

		if(filtro.getDisciplina() != null)
			predicates.add(builder.equal(
				fromAssunto.get("disciplina"), filtro.getDisciplina()));

		if(filtro.getAtivo() != null)
			predicates.add(builder.equal(
				fromAssunto.get("ativo"), filtro.getAtivo()));

		TypedQuery<Assunto> typedQuery = em.createQuery(query.select(fromAssunto).where(predicates.toArray(new Predicate[0])).distinct(true));
		return typedQuery.getResultList();
	}

	public List<Assunto> filtrar(String nome)
	{
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Assunto> query = builder.createQuery(Assunto.class);
		Root<Assunto> fromAssunto = query.from(Assunto.class);

		List<Predicate> predicates = new ArrayList<>();

		if(nome != null && !nome.isBlank())
		{
			predicates.add(builder.like(fromAssunto.<String>get("nome"), "%" + nome + "%"));
		}

		TypedQuery<Assunto> typedQuery = em.createQuery(query.select(fromAssunto).where(predicates.toArray(new Predicate[0])).distinct(true));
		List<Assunto> list = typedQuery.getResultList();

		return list;
	}
}
