package modelo.avaliacao;

import java.io.Serializable;

import org.javers.core.metamodel.annotation.DiffIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import modelo.Entidade;
import modelo.auditoria.AuditLabel;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Data
@Entity
public class ConfigPedidoAvaliacao implements Serializable, Entidade
{
	private static final long serialVersionUID = 1L;

	@DiffIgnore
	@Id
	@GeneratedValue
	@EqualsAndHashCode.Include
	private Long id;

	@AuditLabel(value = "máx. exercícios por avaliação")
	private int maxExerciciosPorAvaliacao = 40;

	@AuditLabel(value = "máx. avaliações por solicitação")
	private int maxAvaliacoesPorSolicitacao = 50;

	@AuditLabel(value = "dias de retenção do PDF")
	private int diasRetencaoPdf = 30;
}
