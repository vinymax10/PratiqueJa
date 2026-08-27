package dao.email;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import filtro.email.FiltroEmail;
import modelo.email.Email;
import modelo.email.StatusEmail;
import dao.DAO;

public class EmailDAO extends DAO<Email>
{
	private static final long serialVersionUID = 1L;

	public EmailDAO()
	{
		super(Email.class);
	}

	public List<Email> listarPendentes()
	{
		return listarPendentes(Integer.MAX_VALUE);
	}

	/**
	 * Os pendentes, no máximo este tanto e do mais antigo para o mais novo.
	 *
	 * <p><b>O teto não é enfeite.</b> Quem chama é o {@code EmailService.prepararPendentes},
	 * que lê do disco os bytes de todos os anexos de tudo o que voltar daqui — e segura isso
	 * na memória durante o ciclo inteiro. Sem teto, uma fila acumulada de e-mails com anexo
	 * entrava inteira no heap de uma vez, num servidor que ainda divide esse heap com outra
	 * aplicação. O que sobra vai no ciclo seguinte, um minuto depois.</p>
	 *
	 * <p>Ordenado pelo id: o mais antigo é o que está esperando há mais tempo.</p>
	 */
	public List<Email> listarPendentes(int limite)
	{
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Email> query = builder.createQuery(Email.class);
		Root<Email> fromEmail = query.from(Email.class);

		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.equal(fromEmail.get("status"), StatusEmail.PENDENTE));

		TypedQuery<Email> typedQuery = em.createQuery(query.select(fromEmail)
		.where(predicates.toArray(new Predicate[0]))
		.orderBy(builder.asc(fromEmail.get("id"))));

		return typedQuery.setMaxResults(limite).getResultList();
	}

	public List<Email> buscarEnviadosComAnexosAntesDe(LocalDateTime limite)
	{
		return em.createQuery(
			"SELECT DISTINCT e FROM Email e JOIN e.documentosFile d " +
			"WHERE e.status = :status AND e.dataEnvio < :limite",
			Email.class)
			.setParameter("status", StatusEmail.ENVIADO)
			.setParameter("limite", limite)
			.getResultList();
	}

	public List<Email> buscarEnviadosAntesDe(LocalDateTime limite)
	{
		return em.createQuery(
			"SELECT e FROM Email e WHERE e.status = :status AND e.dataEnvio < :limite",
			Email.class)
			.setParameter("status", StatusEmail.ENVIADO)
			.setParameter("limite", limite)
			.getResultList();
	}

	public List<Email> buscar(FiltroEmail filtro)
	{
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Email> query = builder.createQuery(Email.class);
		Root<Email> fromEmail = query.from(Email.class);

		List<Predicate> predicates = new ArrayList<>();

		if(filtro.getDestinatario() != null && !filtro.getDestinatario().isBlank())
			predicates.add(builder.like(builder.lower(fromEmail.get("destinatario")),
				"%" + filtro.getDestinatario().toLowerCase() + "%"));

		if(filtro.getAssunto() != null && !filtro.getAssunto().isBlank())
			predicates.add(builder.like(builder.lower(fromEmail.get("assunto")),
				"%" + filtro.getAssunto().toLowerCase() + "%"));

		if(filtro.getStatus() != null)
			predicates.add(builder.equal(fromEmail.get("status"), filtro.getStatus()));

		if(filtro.getPeriodo() != null && !filtro.getPeriodo().isEmpty())
		{
			LocalDateTime inicioPeriodo = filtro.getPeriodo().get(0).atStartOfDay();
			LocalDateTime terminoPeriodo = filtro.getPeriodo().size() > 1
				? filtro.getPeriodo().get(1).atTime(23, 59, 59)
				: filtro.getPeriodo().get(0).atTime(23, 59, 59);

			predicates.add(builder.between(fromEmail.get("dataEnvio"), inicioPeriodo, terminoPeriodo));
		}

		TypedQuery<Email> typedQuery = em.createQuery(query.select(fromEmail)
		.where(predicates.toArray(new Predicate[0]))
		.orderBy(builder.desc(fromEmail.get("dataEnvio"))));

		return typedQuery.getResultList();
	}
}
