package modelo.usuario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.javers.core.metamodel.annotation.DiffIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import modelo.Entidade;
import modelo.assuntocurso.AssuntoCurso;
import modelo.auditoria.AuditLabel;
import modelo.auditoria.GeneroGramatical;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = { "alunos", "assuntoAtual" })
@Data
@Entity
public class Turma implements Serializable, Entidade
{
	private static final long serialVersionUID = 1L;

	@DiffIgnore
	@Id
	@GeneratedValue
	@EqualsAndHashCode.Include
	private Long id;

	@Column(length = 255)
	@Size(max = 255)
	@AuditLabel(value = "nome", atributo = "nome")
	private String nome;

	@DiffIgnore
	@OneToMany(mappedBy = "turma")
	private List<Usuario> alunos = new ArrayList<Usuario>();

	@ManyToOne(optional = true)
	@JoinColumn(nullable = true)
	@AuditLabel(value = "assunto atual", atributo = "nome")
	private AssuntoCurso assuntoAtual;
}
