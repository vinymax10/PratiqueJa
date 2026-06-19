package dao.pdf;

import java.util.ArrayList;
import java.util.List;

import dao.DAO;
import filtro.pdf.FiltroConfigPdfExercicio;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import modelo.exercicio.Nivel;
import modelo.pdf.ConfigPdfExercicio;
import modelo.pdf.Visibilidade;

public class ConfigPdfExercicioDAO extends DAO<ConfigPdfExercicio>
{
	private static final long serialVersionUID = 1L;

	public ConfigPdfExercicioDAO()
	{
		super(ConfigPdfExercicio.class);
	}

	public List<ConfigPdfExercicio> buscar(FiltroConfigPdfExercicio filtro)
	{
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<ConfigPdfExercicio> query = builder.createQuery(ConfigPdfExercicio.class);
		Root<ConfigPdfExercicio> from = query.from(ConfigPdfExercicio.class);

		List<Predicate> predicates = new ArrayList<>();

		if (filtro.getNivel() != null)
			predicates.add(builder.equal(from.<Nivel>get("nivel"), filtro.getNivel()));

		if (filtro.getVisibilidade() != null)
			predicates.add(builder.equal(from.<Visibilidade>get("visibilidade"), filtro.getVisibilidade()));

		TypedQuery<ConfigPdfExercicio> typedQuery = em.createQuery(
			query.select(from)
				.where(predicates.toArray(new Predicate[0]))
				.orderBy(
					builder.asc(from.get("nivel")),
					builder.asc(from.get("visibilidade")),
					builder.asc(from.get("comAlternativas"))
				)
		);

		return typedQuery.getResultList();
	}
}
