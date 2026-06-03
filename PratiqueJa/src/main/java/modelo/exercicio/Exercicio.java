package modelo.exercicio;

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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import modelo.Entidade;
import modelo.auditoria.AuditLabel;
import modelo.questao.Alternativa;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = { "exercicioPadrao", "resultadosExercicio" })
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
	@ManyToOne(optional = true)
	@JoinColumn(nullable = true)
	private ExercicioPadrao exercicioPadrao;

	@AuditLabel(value = "global")
	private boolean global;

	@Column(length = 4095)
	@Size(max = 4095)
	@AuditLabel(value = "enunciado")
	private String enunciado;

	@Column(length = 4095)
	@Size(max = 4095)
	@AuditLabel(value = "resolução", genero = modelo.auditoria.GeneroGramatical.FEMININO)
	private String resolucao;

	@DiffIgnore
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "exercicio")
	@OrderBy("ordem")
	private List<Alternativa> alternativas = new ArrayList<>();

	@DiffIgnore
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "exercicio")
	private List<ResultadoExercicio> resultadosExercicio = new ArrayList<>();

	@Transient
	private Alternativa alternativaEscolhida;

	@Transient
	private boolean showEstatistica;

	@Transient
	private boolean showResolucaoComentada;

	@Transient
	private Boolean feedbackAcertou;

	@Transient
	private String feedbackLetraCorreta;

	@Transient
	private boolean feedbackSemSelecao;

	public void toogleResolucaoComentada()
	{
		if(showEstatistica)
			showEstatistica = false;
		showResolucaoComentada = !showResolucaoComentada;
	}

	public void toogleEstatistica()
	{
		if(showResolucaoComentada)
			showResolucaoComentada = false;
		showEstatistica = !showEstatistica;
	}

	public Alternativa correta()
	{
		for(Alternativa alternativa : alternativas)
			if(alternativa.isCorreta())
				return alternativa;
		return null;
	}
}
