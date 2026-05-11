package modelo.teste;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.javers.core.metamodel.annotation.DiffIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import modelo.Entidade;
import modelo.assuntocurso.AssuntoCurso;
import modelo.auditoria.AuditLabel;
import modelo.auditoria.GeneroGramatical;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = { "assuntoCurso", "conteudosTeste" })
@Data
@Entity
public class TestePadrao implements Serializable, Entidade
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
	@OneToOne(mappedBy = "testePadrao")
	private AssuntoCurso assuntoCurso;

	@DiffIgnore
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "testePadrao")
	private List<ConteudoTeste> conteudosTeste = new ArrayList<ConteudoTeste>();

	@AuditLabel(value = "duração", genero = GeneroGramatical.FEMININO)
	private double duracao;

	@AuditLabel(value = "nota mínima", genero = GeneroGramatical.FEMININO)
	private double notaMinima;
}
