package modelo.instagram;

import java.io.Serializable;

import org.javers.core.metamodel.annotation.DiffIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import modelo.Entidade;
import modelo.auditoria.AuditLabel;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Data
@Entity
public class ConviteGrupo implements Serializable, Entidade
{
	private static final long serialVersionUID = 1L;

	@DiffIgnore
	@Id
	@GeneratedValue
	@EqualsAndHashCode.Include
	private Long id;

	@Column(length = 1023)
	@Size(max = 1023)
	@AuditLabel(value = "nome", atributo = "nome")
	private String nome;

	public void setNome(String nome)
	{
		this.nome = nome.trim();
	}
}
