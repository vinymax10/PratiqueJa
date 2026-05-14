package modelo.publicacao;

import org.javers.core.metamodel.annotation.DiffIgnore;

import jakarta.persistence.Column;
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
import modelo.Config;
import modelo.Entidade;
import modelo.auditoria.AuditLabel;
import modelo.auditoria.GeneroGramatical;

@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@ToString(exclude = { "configPost" })
@Data
@Entity
public class Cta extends Config implements Entidade
{
	@DiffIgnore
	@Id
	@GeneratedValue
	@EqualsAndHashCode.Include
	private Long id;

	@Column(length = 1023)
	@Size(max = 1023)
	@AuditLabel(value = "nome")
	private String nome;

	@Enumerated(EnumType.STRING)
	@AuditLabel(value = "finalidade", genero = GeneroGramatical.FEMININO)
	private FinalidadeCta finalidadeCta;

	@DiffIgnore
	@ManyToOne
	private ConfigPost configPost;

	public void setNome(String nome)
	{
		this.nome = nome.trim();
	}
}
