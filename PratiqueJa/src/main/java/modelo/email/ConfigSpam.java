package modelo.email;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.javers.core.metamodel.annotation.DiffIgnore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import modelo.Entidade;
import modelo.auditoria.AuditLabel;
import modelo.auditoria.GeneroGramatical;
import modelo.exercicio.Nivel;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = { "programacoesSpam" })
@Data
@Entity
public class ConfigSpam implements Serializable, Entidade
{
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(ConfigSpam.class);

	@DiffIgnore
	@Id
	@GeneratedValue
	@EqualsAndHashCode.Include
	private Long id;

	@AuditLabel(value = "último envio")
	private LocalDate ultimoEnvio = LocalDate.now();

	@AuditLabel(value = "ativo")
	private boolean ativo;

	@AuditLabel(value = "dias de intervalo")
	private int diasIntervalo = 3;

	@AuditLabel(value = "quantidade nível 1", genero = GeneroGramatical.FEMININO)
	private int qtnNivel1 = 3;

	@AuditLabel(value = "quantidade nível 2", genero = GeneroGramatical.FEMININO)
	private int qtnNivel2 = 3;

	@AuditLabel(value = "quantidade nível 3", genero = GeneroGramatical.FEMININO)
	private int qtnNivel3 = 3;

	@DiffIgnore
	@OneToMany(orphanRemoval = true, mappedBy = "configSpam")
	@OrderBy("ordem")
	private List<ProgramacaoSpam> programacoesSpam = new ArrayList<ProgramacaoSpam>();

	public int qtn(Nivel nivel)
	{
		if(nivel == Nivel.Nivel1)
			return qtnNivel1;

		if(nivel == Nivel.Nivel2)
			return qtnNivel2;

		if(nivel == Nivel.Nivel3)
			return qtnNivel3;

		return 0;
	}

	public boolean podeGerar()
	{
		if(!ativo)
		{
			LOG.debug("Não pode gerar ConfigSpam pois não está ativo.");
			return false;
		}
		return true;
	}
}
