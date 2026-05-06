package modelo.questao;

import java.io.Serializable;

import org.javers.core.metamodel.annotation.DiffIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import modelo.Entidade;
import modelo.auditoria.AuditLabel;
import modelo.auditoria.GeneroGramatical;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = { "questao" })
@Data
@Entity
public class Alternativa implements Serializable, Entidade
{
	private static final long serialVersionUID = 1L;

	@DiffIgnore
	@Id
	@GeneratedValue
	@EqualsAndHashCode.Include
	private Long id;

	@Column(length = 1023)
	@Size(max = 1023)
	@AuditLabel(value = "texto")
	private String texto;

	@AuditLabel(value = "correta", genero = GeneroGramatical.FEMININO)
	private boolean correta = false;

	@AuditLabel(value = "ordem")
	private int ordem;

	@AuditLabel(value = "quantidade escolhida", genero = GeneroGramatical.FEMININO)
	private int qtnEscolhida;

	@DiffIgnore
	@ManyToOne
	private Questao questao;

	public void incrementaQtnEscolhida()
	{
		this.qtnEscolhida++;
	}

	public String getLetra()
	{
		return "" + (char) (65 + ordem);
	}
}
