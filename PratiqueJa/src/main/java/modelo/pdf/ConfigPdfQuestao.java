package modelo.pdf;

import java.io.Serializable;

import org.javers.core.metamodel.annotation.DiffIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import modelo.Entidade;
import modelo.auditoria.AuditLabel;
import modelo.auditoria.GeneroGramatical;
import modelo.questao.Dificuldade;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Data
@Entity
public class ConfigPdfQuestao implements Serializable, Entidade
{
	private static final long serialVersionUID = 1L;

	@DiffIgnore
	@Id
	@GeneratedValue
	@EqualsAndHashCode.Include
	private Long id;

	@Enumerated(EnumType.STRING)
	@AuditLabel(value = "nível")
	private Dificuldade dificuldade;

	@AuditLabel(value = "quantidade")
	private int quantidade;
	
	@AuditLabel(value = "todos")
	private boolean todos;

	@Enumerated(EnumType.STRING)
	@AuditLabel(value = "visibilidade", genero = GeneroGramatical.FEMININO)
	private Visibilidade visibilidade;

	@AuditLabel(value = "com resolução", genero = GeneroGramatical.FEMININO)
	private boolean comResolucao;
}
