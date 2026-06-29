package modelo.pdf;

import java.io.Serializable;

import org.javers.core.metamodel.annotation.DiffIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import modelo.Entidade;
import modelo.academico.Assunto;
import modelo.auditoria.AuditLabel;
import modelo.exercicio.Nivel;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = { "assunto" })
@Data
@Entity
public class Pdf implements Serializable, Entidade
{
	private static final long serialVersionUID = 1L;

	@DiffIgnore
	@Id
	@GeneratedValue
	@EqualsAndHashCode.Include
	private Long id;

	@DiffIgnore
	@ManyToOne
	private Assunto assunto;

	@AuditLabel(value = "nível")
	@Enumerated(EnumType.STRING)
	private Nivel nivel;
	
	@Size(max = 511)
	@AuditLabel(value = "caminho")
	private String caminho;

	@Enumerated(EnumType.STRING)
	@AuditLabel(value = "tipo")
	private TipoPdf tipo;

	@Enumerated(EnumType.STRING)
	@AuditLabel(value = "visibilidade", genero = modelo.auditoria.GeneroGramatical.FEMININO)
	private Visibilidade visibilidade;

	@Size(max = 511)
	@AuditLabel(value = "descrição", genero = modelo.auditoria.GeneroGramatical.FEMININO)
	private String descricao;
	
	@DiffIgnore
	private int ordem;
}
