package modelo.instagram;

import java.io.Serializable;

import org.javers.core.metamodel.annotation.DiffIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import modelo.Entidade;
import modelo.auditoria.AuditLabel;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Data
@Entity
public class Background implements Serializable, Entidade
{
	private static final long serialVersionUID = 1L;

	@DiffIgnore
	@Id
	@GeneratedValue
	@EqualsAndHashCode.Include
	private Long id;

	@AuditLabel(value = "endereço")
	private String endereco;

	@AuditLabel(value = "nome", atributo = "nome")
	private String nome;

	@AuditLabel(value = "feed")
	private boolean feed;
}
