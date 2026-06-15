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

	@Size(max = 511)
	@AuditLabel(value = "caminho")
	private String caminho;

	@Enumerated(EnumType.STRING)
	@AuditLabel(value = "visibilidade", genero = modelo.auditoria.GeneroGramatical.FEMININO)
	private VisibilidadePdf visibilidade;

	@Size(max = 511)
	@AuditLabel(value = "descrição", genero = modelo.auditoria.GeneroGramatical.FEMININO)
	private String descricao;
}
