package modelo.usuario;

import java.io.Serializable;
import java.time.LocalDate;

import org.javers.core.metamodel.annotation.DiffIgnore;

import jakarta.persistence.Entity;
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
public class Acesso implements Serializable, Entidade
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

	@AuditLabel(value = "data", genero = GeneroGramatical.FEMININO)
	private LocalDate data;

	@AuditLabel(value = "início do acesso")
	private Long inicioAcesso;

	@AuditLabel(value = "minutos")
	private int minutos;

	@AuditLabel(value = "id da sessão")
	private String idSessao;

	@AuditLabel(value = "finalizado")
	private boolean finalizado;

	public void finalizadoToggle()
	{
		finalizado = !finalizado;
	}
}
