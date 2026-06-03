package modelo.publicacao;

import java.io.Serializable;
import java.time.LocalDate;

import org.javers.core.metamodel.annotation.DiffIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import modelo.Entidade;
import modelo.academico.Assunto;
import modelo.auditoria.AuditLabel;
import modelo.auditoria.GeneroGramatical;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = { "configPost", "backgroundFeed", "backgroundReel", "padraoFeed", "padraoReel", "assunto" })
@Data
@Entity
public class ProgramacaoPost implements Serializable, Entidade
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

	@AuditLabel(value = "alternativa reel", genero = GeneroGramatical.FEMININO)
	private boolean alternativaReel = true;

	@AuditLabel(value = "teste")
	private boolean teste = false;

	@AuditLabel(value = "avulsa", genero = GeneroGramatical.FEMININO)
	private boolean avulsa = false;

	@AuditLabel(value = "background aleatório feed")
	private boolean backgroundAleatorioFeed = true;

	@AuditLabel(value = "base padrão feed", genero = GeneroGramatical.FEMININO)
	private boolean basePadraoFeed = true;

	@DiffIgnore
	@ManyToOne
	@JoinColumn(nullable = true)
	private ImagemPost backgroundFeed;

	@DiffIgnore
	@ManyToOne
	@JoinColumn(nullable = true)
	private Background padraoFeed;

	@AuditLabel(value = "background aleatório reel")
	private boolean backgroundAleatorioReel = true;

	@AuditLabel(value = "base padrão reel", genero = GeneroGramatical.FEMININO)
	private boolean basePadraoReel = true;

	@DiffIgnore
	@ManyToOne
	@JoinColumn(nullable = true)
	private ImagemPost backgroundReel;

	@DiffIgnore
	@ManyToOne
	@JoinColumn(nullable = true)
	private Background padraoReel;

	@AuditLabel(value = "assunto", atributo = "nome")
	@ManyToOne
	private Assunto assunto;

	@DiffIgnore
	@ManyToOne
	private ConfigPost configPost;

	public ProgramacaoPost clone()
	{
		ProgramacaoPost clone = new ProgramacaoPost();
		clone.alternativaReel = this.alternativaReel;
		clone.assunto = this.assunto;
		clone.backgroundAleatorioFeed = this.backgroundAleatorioFeed;
		clone.backgroundAleatorioReel = this.backgroundAleatorioReel;
		clone.backgroundFeed = this.backgroundFeed;
		clone.backgroundReel = this.backgroundReel;
		clone.basePadraoFeed = this.basePadraoFeed;
		clone.basePadraoReel = this.basePadraoReel;
		clone.configPost = this.configPost;
		clone.data = this.data;
		clone.ordem = this.ordem;
		clone.padraoFeed = this.padraoFeed;
		clone.padraoReel = this.padraoReel;
		clone.teste = this.teste;
		return clone;
	}

	public void updateData()
	{
		LocalDate ultimoEnvio = configPost.getUltimoEnvio();
		PerfilCriador perfilCriador = configPost.getPerfilCriador();
		data = ultimoEnvio.plusDays((ordem + 1) * perfilCriador.getIntervalo());
	}
}
