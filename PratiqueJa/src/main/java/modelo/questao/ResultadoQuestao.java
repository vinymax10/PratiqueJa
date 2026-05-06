package modelo.questao;

import java.io.Serializable;

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
import modelo.usuario.Usuario;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = { "usuario", "questao" })
@Data
@Entity
public class ResultadoQuestao implements Serializable, Entidade
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
	private Questao questao;

	@AuditLabel(value = "acertou")
	private boolean acertou;
}
