package dao.pdf;

import java.util.ArrayList;
import java.util.List;

import dao.DAO;
import filtro.pdf.FiltroPdf;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import modelo.academico.Assunto;
import modelo.pdf.Pdf;
import modelo.pdf.TipoPdf;
import modelo.pdf.VisibilidadePdf;

public class PdfDAO extends DAO<Pdf>
{
	private static final long serialVersionUID = 1L;

	public PdfDAO()
	{
		super(Pdf.class);
	}

	public List<Pdf> buscar(FiltroPdf filtro)
	{
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Pdf> query = builder.createQuery(Pdf.class);
		Root<Pdf> from = query.from(Pdf.class);

		List<Predicate> predicates = new ArrayList<>();

		if (filtro.getAssunto() != null)
			predicates.add(builder.equal(from.<Assunto>get("assunto").get("id"), filtro.getAssunto().getId()));

		if (filtro.getVisibilidade() != null)
			predicates.add(builder.equal(from.<VisibilidadePdf>get("visibilidade"), filtro.getVisibilidade()));

		if (filtro.getTipo() != null)
			predicates.add(builder.equal(from.<TipoPdf>get("tipo"), filtro.getTipo()));

		if (filtro.getDescricao() != null && !filtro.getDescricao().isBlank())
			predicates.add(builder.like(from.<String>get("descricao"), "%" + filtro.getDescricao() + "%"));

		TypedQuery<Pdf> typedQuery = em.createQuery(
			query.select(from)
				.where(predicates.toArray(new Predicate[0]))
				.orderBy(
					builder.asc(from.get("assunto").get("modulo")),
					builder.asc(from.get("assunto").get("ordem")),
					builder.asc(from.get("visibilidade"))
				)
		);

		return typedQuery.getResultList();
	}
}
