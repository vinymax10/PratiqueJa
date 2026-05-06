package modelo.exercicio;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.javers.core.metamodel.annotation.DiffIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import modelo.Entidade;
import modelo.auditoria.AuditLabel;
import modelo.auditoria.GeneroGramatical;
import modelo.matematica.Conta;
import modelo.usuario.Usuario;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = { "exercicioPadrao", "contas", "usuario", "resultadoExercicio" })
@Data
@Entity
public class Exercicio implements Serializable, Entidade
{
	private static final long serialVersionUID = 1L;

	@DiffIgnore
	@Id
	@GeneratedValue
	@EqualsAndHashCode.Include
	private Long id;

	@DiffIgnore
	@ManyToOne(optional = true)
	@JoinColumn(nullable = true)
	private ExercicioPadrao exercicioPadrao;

	@DiffIgnore
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "exercicio")
	private List<Conta> contas = new ArrayList<Conta>();

	@AuditLabel(value = "realizado")
	private boolean realizado = false;

	@AuditLabel(value = "número de contas realizadas")
	private double numContasRealizadas;

	@AuditLabel(value = "número de corretas")
	private int numCorretas;

	@AuditLabel(value = "realização", genero = GeneroGramatical.FEMININO)
	private LocalDate realizacao;

	@AuditLabel(value = "prazo")
	private LocalDate prazo;

	@AuditLabel(value = "nota", genero = GeneroGramatical.FEMININO)
	private double nota;

	@DiffIgnore
	@ManyToOne
	private Usuario usuario;

	@DiffIgnore
	@OneToOne
	private ResultadoExercicio resultadoExercicio;

	public void calculaNota()
	{
		nota = 100 * (double) numCorretas / numContasRealizadas;
	}

	public void incrementaContasRealizadas()
	{
		numContasRealizadas++;
	}

	public void incrementaContasCorretas()
	{
		numCorretas++;
	}

	public double getNotaPorcentagem()
	{
		return nota;
	}
}
