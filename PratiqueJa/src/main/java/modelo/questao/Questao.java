package modelo.questao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.javers.core.metamodel.annotation.DiffIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import modelo.Entidade;
import modelo.academico.AssuntoCurso;
import modelo.auditoria.AuditLabel;
import modelo.auditoria.GeneroGramatical;
import modelo.academico.Ano;
import modelo.academico.Assunto;
import modelo.academico.Banca;
import modelo.academico.Disciplina;
import modelo.academico.Orgao;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = { "assuntoCurso", "assuntos", "paragrafos", "alternativas", "resultadosQuestao" })
@Data
@Entity
public class Questao implements Serializable, Entidade
{
	private static final long serialVersionUID = 1L;

	@DiffIgnore
	@Id
	@GeneratedValue
	@EqualsAndHashCode.Include
	private Long id;

	@ManyToOne(optional = true)
	@JoinColumn(nullable = true)
	@AuditLabel(value = "ano", atributo = "nome")
	private Ano ano;

	@ManyToOne(optional = true)
	@JoinColumn(nullable = true)
	@AuditLabel(value = "banca", genero = GeneroGramatical.FEMININO, atributo = "nome")
	private Banca banca;

	@ManyToOne(optional = true)
	@JoinColumn(nullable = true)
	@AuditLabel(value = "órgão", atributo = "nome")
	private Orgao orgao;

	@DiffIgnore
	@ManyToMany
	@JoinTable(name = "questaoHasAssunto")
	private List<Assunto> assuntos = new ArrayList<Assunto>();

	@ManyToOne(optional = true)
	@JoinColumn(nullable = true)
	@AuditLabel(value = "disciplina", genero = GeneroGramatical.FEMININO, atributo = "nome")
	private Disciplina disciplina;

	@DiffIgnore
	@ManyToOne
	private AssuntoCurso assuntoCurso;

	@AuditLabel(value = "dificuldade", genero = GeneroGramatical.FEMININO)
	private Dificuldade dificuldade;

	@AuditLabel(value = "ordem de inserção")
	private int ordemInsercao;

	@Column(length = 255)
	@Size(max = 255)
	@AuditLabel(value = "chave", genero = GeneroGramatical.FEMININO)
	private String chave;

	@Column(length = 2047)
	@Size(max = 2047)
	@AuditLabel(value = "resolução", genero = GeneroGramatical.FEMININO)
	private String resolucao;

	@Column(length = 255)
	@Size(max = 255)
	@AuditLabel(value = "prova", genero = GeneroGramatical.FEMININO)
	private String prova;

	@DiffIgnore
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "questao")
	@OrderBy("ordem")
	private List<Paragrafo> paragrafos = new ArrayList<Paragrafo>();

	@DiffIgnore
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "questao")
	@OrderBy("ordem")
	private List<Alternativa> alternativas = new ArrayList<Alternativa>();

	@DiffIgnore
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "questao")
	private List<ResultadoQuestao> resultadosQuestao = new ArrayList<ResultadoQuestao>();

	@AuditLabel(value = "revisada", genero = GeneroGramatical.FEMININO)
	private boolean revisada;

	@AuditLabel(value = "mal formulada", genero = GeneroGramatical.FEMININO)
	private boolean malFormulada;

	@AuditLabel(value = "multi assunto")
	private boolean multiAssunto;

	@Column(length = 512)
	@Size(max = 512)
	@AuditLabel(value = "observação da revisão", genero = GeneroGramatical.FEMININO)
	private String obsRevisao;

	@Transient
	Alternativa alternativaEscolhida;

	@Transient
	private boolean showEstatistica;

	@Transient
	private boolean showResolucaoComentada;

	@Transient
	private boolean jaMostrouResolucaoComentada;

	@Transient
	protected boolean jaFezQuestao = false;

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

	public Alternativa correta()
	{
		for(Alternativa alternativa : alternativas)
		{
			if(alternativa.isCorreta())
				return alternativa;
		}
		return null;
	}
}
