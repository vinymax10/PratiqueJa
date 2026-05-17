package dao.auditoria;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import filtro.auditoria.FiltroAuditoria;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import modelo.auditoria.AuditoriaEvento;
import dao.DAO;

public class AuditoriaEventoDAO extends DAO<AuditoriaEvento>
{
	private static final long serialVersionUID = 1L;

	public AuditoriaEventoDAO()
	{
		super(AuditoriaEvento.class);
	}

	public List<AuditoriaEvento> buscar(FiltroAuditoria filtro)
	{
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<AuditoriaEvento> query = builder.createQuery(AuditoriaEvento.class);
		Root<AuditoriaEvento> fromAuditoriaEvento = query.from(AuditoriaEvento.class);

		List<Predicate> predicates = new ArrayList<>();

//		-----------Entidade-----------
		Predicate pEntidade1 = null;
		Predicate pEntidade2 = null;

		if(filtro.getEntidade() != null && !filtro.getEntidade().isBlank())
			pEntidade1 = builder.equal(fromAuditoriaEvento.get("entidade"), filtro.getEntidade());

		if(filtro.getEntidade2() != null && !filtro.getEntidade2().isBlank())
			pEntidade2 = builder.equal(fromAuditoriaEvento.get("entidade"), filtro.getEntidade2());

		if(pEntidade1 != null && pEntidade2 != null)
			predicates.add(builder.or(pEntidade1, pEntidade2));
		else if(pEntidade1 != null)
			predicates.add(pEntidade1);
		else if(pEntidade2 != null)
			predicates.add(pEntidade2);

//		-----------EntidadeId-----------
		Predicate pEntidadeId1 = null;
		Predicate pEntidadeId2 = null;

		if(filtro.getEntidadeId() != null)
			pEntidadeId1 = builder.equal(fromAuditoriaEvento.get("entidadeId"), filtro.getEntidadeId());

		if(filtro.getEntidadeId2() != null)
			pEntidadeId2 = builder.equal(fromAuditoriaEvento.get("entidadeId"), filtro.getEntidadeId2());

		if(pEntidadeId1 != null && pEntidadeId2 != null)
			predicates.add(builder.or(pEntidadeId1, pEntidadeId2));
		else if(pEntidadeId1 != null)
			predicates.add(pEntidadeId1);
		else if(pEntidadeId2 != null)
			predicates.add(pEntidadeId2);

//		--------------------------------

		if(filtro.getTipo() != null)
			predicates.add(builder.equal(fromAuditoriaEvento.get("tipo"), filtro.getTipo()));

		if(filtro.getUsuario() != null)
			predicates.add(builder.equal(fromAuditoriaEvento.get("usuario").get("id"), filtro.getUsuario().getId()));

		if(filtro.getResumo() != null && !filtro.getResumo().isBlank())
			predicates.add(builder.like(fromAuditoriaEvento.get("resumo"), "%" + filtro.getResumo() + "%"));

		if(filtro.getIp() != null && !filtro.getIp().isBlank())
			predicates.add(builder.equal(fromAuditoriaEvento.get("ip"), filtro.getIp()));

		if(filtro.getUserAgent() != null && !filtro.getUserAgent().isBlank())
			predicates.add(builder.like(fromAuditoriaEvento.get("userAgent"), "%" + filtro.getUserAgent() + "%"));

		if(filtro.getPeriodo() != null && !filtro.getPeriodo().isEmpty())
		{
			predicates.add(builder.greaterThanOrEqualTo(
				fromAuditoriaEvento.get("dataEvento"), filtro.getPeriodo().get(0)));

			if(filtro.getPeriodo().size() > 1)
			{
				LocalDateTime dataFinalFimDoDia = filtro.getPeriodo().get(1).atTime(LocalTime.MAX);
				predicates.add(builder.lessThanOrEqualTo(
					fromAuditoriaEvento.get("dataEvento"), dataFinalFimDoDia));
			}
		}

		TypedQuery<AuditoriaEvento> typedQuery = em
		.createQuery(query.select(fromAuditoriaEvento).where(predicates.toArray(new Predicate[0])).orderBy(builder.asc(fromAuditoriaEvento.get("dataEvento"))));
		List<AuditoriaEvento> list = typedQuery.getResultList();

		return list;
	}

}
