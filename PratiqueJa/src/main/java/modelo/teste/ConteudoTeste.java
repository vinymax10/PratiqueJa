package modelo.teste;

import java.io.Serializable;

import org.javers.core.metamodel.annotation.DiffIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import modelo.Entidade;
import modelo.auditoria.AuditLabel;
import modelo.auditoria.GeneroGramatical;
import modelo.exercicio.ExercicioPadrao;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = { "exercicioPadrao", "testePadrao" })
@Data
@Entity
public class ConteudoTeste implements Serializable, Entidade
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

	@AuditLabel(value = "quantidade", genero = GeneroGramatical.FEMININO)
	private int quantidade;

	@DiffIgnore
	@ManyToOne(optional = true)
	@JoinColumn(nullable = true)
	private TestePadrao testePadrao;
}
