package modelo.exercicio;

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
import modelo.pdf.Visibilidade;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Data
@Entity
public class ConfigExercicio implements Serializable, Entidade
{
	private static final long serialVersionUID = 1L;

	@DiffIgnore
	@Id
	@GeneratedValue
	@EqualsAndHashCode.Include
	private Long id;

	@Enumerated(EnumType.STRING)
	@AuditLabel(value = "nível")
	private Nivel nivel;

	@AuditLabel(value = "quantidade")
	private int quantidade;

	@Enumerated(EnumType.STRING)
	@AuditLabel(value = "visibilidade", genero = modelo.auditoria.GeneroGramatical.FEMININO)
	private Visibilidade visibilidade;
}
