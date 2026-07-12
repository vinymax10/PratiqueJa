package modelo.exercicio;

import java.io.Serializable;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

import org.javers.core.metamodel.annotation.DiffIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import modelo.Entidade;
import modelo.academico.Assunto;
import modelo.auditoria.AuditLabel;
import modelo.auditoria.GeneroGramatical;
import modelo.pdf.Visibilidade;
import modelo.teste.EtapaTeste;

@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = { })
@Data
@Entity
public class Exercicio implements Serializable, Entidade
{
	private static final long serialVersionUID = 1L;

	@DiffIgnore
	@Id
	@GeneratedValue
	@EqualsAndHashCode.Include
	private Long id;

	@DiffIgnore
	@ManyToOne
	private Assunto assunto;

	@DiffIgnore
	@ManyToOne
	private EtapaTeste etapaTeste;

	@AuditLabel(value = "nível")
	private Nivel nivel;
	
	@AuditLabel(value = "global")
	private boolean global=true;

	@AuditLabel(value = "visibilidade")
	private Visibilidade visibilidade;
	
	@DiffIgnore
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "exercicio")
	@OrderBy("ordem")
	private List<ParagrafoExercicio> paragrafos = new ArrayList<ParagrafoExercicio>();

	@DiffIgnore
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "exercicio")
	@OrderBy("ordem")
	private List<AlternativaExercicio> alternativas = new ArrayList<>();

	@DiffIgnore
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "exercicio")
	private List<ResultadoExercicio> resultadosExercicio = new ArrayList<>();

	@AuditLabel(value = "ordem")
	protected int ordem;

	@Column(length = 255)
	@Size(max = 255)
	@AuditLabel(value = "tamanho da fonte LaTeX")
	protected String sizeFontTextLatex;

	@DiffIgnore
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "exercicio")
	@OrderBy("ordem")
	private List<ParagrafoResolucao> resolucaoParagrafos = new ArrayList<ParagrafoResolucao>();

	@Lob
	protected Blob imagemResolucao;
	
	@Transient
	private AlternativaExercicio alternativaEscolhida;
	
	@Transient
	private boolean showEstatistica;

	@Transient
	private boolean showResolucaoComentada=false;

	@Transient
	private boolean jaMostrouResolucaoComentada;

	@Transient
	protected boolean jaFezQuestao = false;

	/** Feedback inline após clicar Responder. null = ainda não respondeu. */
	@Transient
	private Boolean feedbackAcertou;

	@Transient
	private String feedbackLetraCorreta;

	@Transient
	private boolean feedbackSemSelecao;
	
	public void toogleResolucaoComentada()
	{
		if(showEstatistica)
			showEstatistica = !showEstatistica;
		showResolucaoComentada = !showResolucaoComentada;
	}

	public void toogleEstatistica()
	{
		if(showResolucaoComentada)
			showResolucaoComentada = !showResolucaoComentada;
		showEstatistica = !showEstatistica;
	}

	public AlternativaExercicio correta()
	{
		for(AlternativaExercicio alternativa : alternativas)
		{
			if(alternativa.isCorreta())
				return alternativa;
		}
		return null;
	}

	/** Resumo do enunciado (primeiro parágrafo com texto) para listagens. */
	public String getEnunciadoResumo()
	{
		for(ParagrafoExercicio paragrafo : paragrafos)
		{
			if(paragrafo.getTexto() != null && !paragrafo.getTexto().isBlank())
				return paragrafo.getTexto();
			if(paragrafo.isTipoImagem())
				return "[imagem]";
		}
		return "";
	}

	// ── Resolução (lista de parágrafos; a String é apenas uma view legada) ──────

	/** Linhas da resolução (texto de cada parágrafo não-vazio), na ordem. */
	public List<String> getResolucaoLinhas()
	{
		List<String> linhas = new ArrayList<>();
		for(ParagrafoResolucao paragrafo : resolucaoParagrafos)
			if(paragrafo.getTexto() != null && !paragrafo.getTexto().isBlank())
				linhas.add(paragrafo.getTexto());
		return linhas;
	}

	/**
	 * View LaTeX (modo texto) para os PDFs: junta os parágrafos com "\\" (quebra de linha
	 * em modo texto). O "\\" fica entre segmentos "\(...\)", nunca dentro do math — por isso
	 * é seguro (foi exatamente o bug que a migração para parágrafos corrigiu).
	 */
	public String getResolucaoLatex()
	{
		if(resolucaoParagrafos.isEmpty())
			return null;
		StringBuilder sb = new StringBuilder();
		for(ParagrafoResolucao paragrafo : resolucaoParagrafos)
		{
			if(paragrafo.getTexto() == null)
				continue;
			if(sb.length() > 0)
				sb.append(" \\\\ ");
			sb.append(paragrafo.getTexto());
		}
		return sb.length() == 0 ? null : sb.toString();
	}

	/**
	 * View para HTML/MathJax: junta os parágrafos com {@code <br/>} (cada parágrafo é um
	 * segmento "\(...\)" ou texto). Nas telas web o "\\" não vira quebra visual, então a
	 * quebra entre passos precisa ser HTML.
	 */
	public String getResolucaoHtml()
	{
		if(resolucaoParagrafos.isEmpty())
			return null;
		StringBuilder sb = new StringBuilder();
		for(ParagrafoResolucao paragrafo : resolucaoParagrafos)
		{
			if(paragrafo.getTexto() == null || paragrafo.getTexto().isBlank())
				continue;
			if(sb.length() > 0)
				sb.append("<br/>");
			sb.append(paragrafo.getTexto());
		}
		return sb.length() == 0 ? null : sb.toString();
	}

}
