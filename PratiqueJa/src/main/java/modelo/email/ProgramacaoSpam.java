package modelo.email;

import java.io.Serializable;
import java.time.LocalDate;

import org.javers.core.metamodel.annotation.DiffIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import modelo.Entidade;
import modelo.academico.AssuntoCurso;
import modelo.auditoria.AuditLabel;
import modelo.auditoria.GeneroGramatical;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = { "configSpam", "assuntoCurso" })
@Data
@Entity
public class ProgramacaoSpam implements Serializable, Entidade
{
	private static final long serialVersionUID = 1L;

	@DiffIgnore
	@Id
	@GeneratedValue
	@EqualsAndHashCode.Include
	private Long id;

	@AuditLabel(value = "data", genero = GeneroGramatical.FEMININO)
	private LocalDate data;

	@AuditLabel(value = "ordem")
	private int ordem;

	@AuditLabel(value = "assunto do curso", atributo = "nome")
	@ManyToOne
	private AssuntoCurso assuntoCurso;

	@DiffIgnore
	@ManyToOne
	private ConfigSpam configSpam;

	public ProgramacaoSpam clone()
	{
		ProgramacaoSpam clone = new ProgramacaoSpam();
		clone.assuntoCurso = this.assuntoCurso;
		clone.configSpam = this.configSpam;
		clone.data = this.data;
		clone.ordem = this.ordem;
		return clone;
	}

	public void updateData()
	{
		LocalDate ultimoEnvio = configSpam.getUltimoEnvio();
		data = ultimoEnvio.plusDays((ordem + 1) * configSpam.getDiasIntervalo());
	}
}
