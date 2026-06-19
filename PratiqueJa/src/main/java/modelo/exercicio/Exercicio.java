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

	@Column(length = 1023)
	@Size(max = 1023)
	@AuditLabel(value = "resolução LaTeX", genero = GeneroGramatical.FEMININO)
	protected String resolucao;

	@Lob
	protected Blob imagemResolucao;
	
	@Transient
	private AlternativaExercicio alternativaEscolhida;
	
	@Transient
	private boolean showEstatistica;

	@Transient
	private boolean showResolucaoComentada=true;

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

}
