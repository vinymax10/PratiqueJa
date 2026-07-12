package modelo.usuario;

import java.io.Serializable;
import java.time.LocalDate;

import org.javers.core.metamodel.annotation.DiffIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import modelo.Entidade;
import modelo.auditoria.AuditLabel;
import modelo.auditoria.GeneroGramatical;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = { "usuario" })
@Data
@Entity
public class Assinatura implements Serializable, Entidade
{
	private static final long serialVersionUID = 1L;

	@DiffIgnore
	@Id
	@GeneratedValue
	@EqualsAndHashCode.Include
	private Long id;

	@DiffIgnore
	@ManyToOne
	private Usuario usuario;

	@ManyToOne
	@AuditLabel(value = "produto")
	private Produto produto;

	@AuditLabel(value = "código do assinante Hotmart")
	private String subscriberCodeHotmart;

	@AuditLabel(value = "início", genero = GeneroGramatical.FEMININO)
	private LocalDate dataInicio;

	@AuditLabel(value = "validade", genero = GeneroGramatical.FEMININO)
	private LocalDate dataValidade;

	@Enumerated(EnumType.STRING)
	@AuditLabel(value = "status")
	private StatusAssinatura status = StatusAssinatura.Ativa;

	@AuditLabel(value = "cancelamento", genero = GeneroGramatical.FEMININO)
	private LocalDate dataCancelamento;
}
