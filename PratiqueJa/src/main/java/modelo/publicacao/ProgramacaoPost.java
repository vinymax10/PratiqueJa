package modelo.publicacao;

import java.io.Serializable;
import java.time.LocalDate;

import org.javers.core.metamodel.annotation.DiffIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@ToString(exclude = { "configPost", "background", "padrao", "assunto" })
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

	// Formato usado quando o plano gera só um post por dia (Básico): Feed ou Reel.
	@Enumerated(EnumType.STRING)
	@AuditLabel(value = "formato")
	private FormatoPost formato = FormatoPost.Feed;

	@AuditLabel(value = "teste")
	private boolean teste = false;

	@AuditLabel(value = "avulsa", genero = GeneroGramatical.FEMININO)
	private boolean avulsa = false;

	// Escolha (aleatória/específica) e origem (padrão/personalizada) do background:
	// valem para Feed e Reel ao mesmo tempo (sempre iguais).
	@AuditLabel(value = "background aleatório")
	private boolean backgroundAleatorio = true;

	@AuditLabel(value = "base padrão", genero = GeneroGramatical.FEMININO)
	private boolean basePadrao = true;

	// Imagem escolhida quando "Personalizada".
	@DiffIgnore
	@ManyToOne
	@JoinColumn(nullable = true)
	private ImagemPost background;

	// Imagem escolhida quando "Padrão".
	@DiffIgnore
	@ManyToOne
	@JoinColumn(nullable = true)
	private Background padrao;

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
		clone.backgroundAleatorio = this.backgroundAleatorio;
		clone.background = this.background;
		clone.basePadrao = this.basePadrao;
		clone.configPost = this.configPost;
		clone.data = this.data;
		clone.formato = this.formato;
		clone.ordem = this.ordem;
		clone.padrao = this.padrao;
		clone.teste = this.teste;
		return clone;
	}

	public void updateData()
	{
		LocalDate ultimoEnvio = configPost.getUltimoEnvio();
		PerfilCriador perfilCriador = configPost.getUsuario().getPerfilCriador();
		// A postagem é diária: o assunto do topo (ordem 0) fica sempre com data = ultimoEnvio+1.
		// Quantos dias esse assunto permanece no topo antes de rotacionar é controlado por
		// ConfigPost.qtdDias (contador diasNoCiclo em registrarEnvio), não pela data.
		data = ultimoEnvio.plusDays((ordem + 1) * perfilCriador.getIntervalo());
	}

	/**
	 * Data de exibição no calendário: o dia em que o BLOCO deste assunto entra no ar. Como cada assunto
	 * publica diariamente por {@code qtdDias} dias antes de rotacionar, os blocos ficam espaçados de
	 * {@code qtdDias} em {@code qtdDias} dias (o campo {@code data} é interno/diário, usado só pelo motor).
	 *
	 * <p>Deriva o início do bloco atual (do topo) a partir de {@code ultimoEnvio} e {@code diasNoCiclo}:
	 * {@code inicioBlocoTopo = ultimoEnvio - (diasNoCiclo - 1)}; os demais assuntos somam múltiplos de
	 * {@code qtdDias} conforme a ordem.</p>
	 */
	@jakarta.persistence.Transient
	public LocalDate getDataInicioBloco()
	{
		if(configPost == null || configPost.getUltimoEnvio() == null)
			return data;

		int qtdDias = Math.max(1, configPost.getQtdDias());
		int diasNoCiclo = configPost.getDiasNoCiclo();
		LocalDate inicioBlocoTopo = configPost.getUltimoEnvio().plusDays(1L - diasNoCiclo);
		return inicioBlocoTopo.plusDays((long) ordem * qtdDias);
	}
}
