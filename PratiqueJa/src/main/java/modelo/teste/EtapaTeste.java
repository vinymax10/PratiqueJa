package modelo.teste;

import java.io.Serializable;
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
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import modelo.Entidade;
import modelo.exercicio.ExercicioPadrao;
import modelo.matematica.Conta;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = { "exercicioPadrao", "contas", "teste" })
@Data
@Entity
public class EtapaTeste implements Serializable, Entidade
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
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "etapaTeste")
	private List<Conta> contas = new ArrayList<Conta>();

	@DiffIgnore
	@ManyToOne
	private Teste teste;
}
