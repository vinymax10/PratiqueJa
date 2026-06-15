package dao.pdf;

import java.util.ArrayList;
import java.util.List;

import dao.DAO;
import filtro.pdf.FiltroConfigExercicio;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import modelo.exercicio.Nivel;
import modelo.pdf.ConfigExercicio;
import modelo.pdf.VisibilidadePdf;

public class ConfigExercicioDAO extends DAO<ConfigExercicio>
{
	private static final long serialVersionUID = 1L;

	public ConfigExercicioDAO()
	{
		super(ConfigExercicio.class);
	}

	public List<ConfigExercicio> buscar(FiltroConfigExercicio filtro)
	{
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<ConfigExercicio> query = builder.createQuery(ConfigExercicio.class);
		Root<ConfigExercicio> from = query.from(ConfigExercicio.class);

		List<Predicate> predicates = new ArrayList<>();

		if (filtro.getNivel() != null)
			predicates.add(builder.equal(from.<Nivel>get("nivel"), filtro.getNivel()));

		if (filtro.getVisibilidade() != null)
			predicates.add(builder.equal(from.<VisibilidadePdf>get("visibilidade"), filtro.getVisibilidade()));

		TypedQuery<ConfigExercicio> typedQuery = em.createQuery(
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
