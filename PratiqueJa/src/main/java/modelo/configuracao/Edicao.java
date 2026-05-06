package modelo.configuracao;

import java.io.Serializable;

import org.javers.core.metamodel.annotation.DiffIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import modelo.Entidade;
import modelo.assuntocurso.Modulo;
import modelo.auditoria.AuditLabel;
import modelo.auditoria.GeneroGramatical;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Data
@Entity
public class Edicao implements Serializable, Entidade
{
	private static final long serialVersionUID = 1L;

	@DiffIgnore
	@Id
	@GeneratedValue
	@EqualsAndHashCode.Include
	private Long id;

	@AuditLabel(value = "edição básico", genero = GeneroGramatical.FEMININO)
	private int edicaoBasico = 1;

	@AuditLabel(value = "edição intermediário", genero = GeneroGramatical.FEMININO)
	private int edicaoIntermediario = 1;

	@AuditLabel(value = "edição avançado", genero = GeneroGramatical.FEMININO)
	private int edicaoAvancado = 1;

	public void incrementa(Modulo modulo)
	{
		switch(modulo)
		{
			case Basico: edicaoBasico++; break;
			case Intermediario: edicaoIntermediario++; break;
			case Avancado: edicaoAvancado++; break;
			default: break;
		}
	}

	public int edicaoModulo(Modulo modulo)
	{
		switch(modulo)
		{
			case Basico: return edicaoBasico;
			case Intermediario: return edicaoIntermediario;
			case Avancado: return edicaoAvancado;
			default: break;
		}
		return 0;
	}
}
