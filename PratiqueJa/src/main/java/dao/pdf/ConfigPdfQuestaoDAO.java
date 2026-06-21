package dao.pdf;

import java.util.ArrayList;
import java.util.List;

import dao.DAO;
import filtro.pdf.FiltroConfigPdfQuestao;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import modelo.pdf.ConfigPdfQuestao;
import modelo.pdf.Visibilidade;
import modelo.questao.Dificuldade;

public class ConfigPdfQuestaoDAO extends DAO<ConfigPdfQuestao>
{
	private static final long serialVersionUID = 1L;

	public ConfigPdfQuestaoDAO()
	{
		super(ConfigPdfQuestao.class);
	}

	public List<ConfigPdfQuestao> buscar(FiltroConfigPdfQuestao filtro)
	{
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<ConfigPdfQuestao> query = builder.createQuery(ConfigPdfQuestao.class);
		Root<ConfigPdfQuestao> from = query.from(ConfigPdfQuestao.class);

		List<Predicate> predicates = new ArrayList<>();

		if (filtro.getDificuldade() != null)
			predicates.add(builder.equal(from.<Dificuldade>get("dificuldade"), filtro.getDificuldade()));

		if (filtro.getVisibilidade() != null)
			predicates.add(builder.equal(from.<Visibilidade>get("visibilidade"), filtro.getVisibilidade()));

		TypedQuery<ConfigPdfQuestao> typedQuery = em.createQuery(
			query.select(from)
				.where(predicates.toArray(new Predicate[0]))
				.orderBy(
					builder.asc(from.get("dificuldade")),
					builder.asc(from.get("visibilidade")),
					builder.asc(from.get("todos"))
				)
		);

		return typedQuery.getResultList();
	}

	public List<ConfigPdfQuestao> todos()
	{
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<ConfigPdfQuestao> query = builder.createQuery(ConfigPdfQuestao.class);
		Root<ConfigPdfQuestao> from = query.from(ConfigPdfQuestao.class);

		TypedQuery<ConfigPdfQuestao> typedQuery = em.createQuery(
			query.select(from)
				.orderBy(
					builder.asc(from.get("dificuldade")),
					builder.asc(from.get("visibilidade"))
				)
		);

		return typedQuery.getResultList();
	}
}
