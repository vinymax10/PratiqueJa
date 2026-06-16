package modelo.avaliacao;

import java.io.Serializable;

import org.javers.core.metamodel.annotation.DiffIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import modelo.Entidade;
import modelo.academico.Assunto;
import modelo.auditoria.AuditLabel;
import modelo.exercicio.Nivel;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = { "pedidoAvaliacao", "assunto" })
@Data
@Entity
public class ItemPedidoAvaliacao implements Serializable, Entidade
{
	private static final long serialVersionUID = 1L;

	@DiffIgnore
	@Id
	@GeneratedValue
	@EqualsAndHashCode.Include
	private Long id;

	@DiffIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	private PedidoAvaliacao pedidoAvaliacao;

	@ManyToOne
	@AuditLabel(value = "assunto")
	private Assunto assunto;

	@Enumerated(EnumType.STRING)
	@AuditLabel(value = "nível")
	private Nivel nivel;

	@Enumerated(EnumType.STRING)
	@AuditLabel(value = "tipo de questão")
	private FormatoAvaliacao formato = FormatoAvaliacao.ALTERNATIVAS;

	@AuditLabel(value = "quantidade")
	private int quantidade;

	@AuditLabel(value = "ordem")
	private int ordem;
}
