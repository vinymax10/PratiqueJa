package modelo.exercicio;

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
import modelo.usuario.Usuario;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = { "usuario", "exercicio" })
@Data
@Entity
public class ResultadoExercicio implements Serializable, Entidade
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

	@DiffIgnore
	@ManyToOne
	private Exercicio exercicio;

	@AuditLabel(value = "realização", genero = GeneroGramatical.FEMININO)
	private LocalDate realizacao;

	@AuditLabel(value = "nota", genero = GeneroGramatical.FEMININO)
	private double nota;

	public double getNotaPorcentagem()
	{
		return nota;
	}
}
