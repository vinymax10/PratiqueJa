package modelo.academico;

import java.util.ArrayList;
import java.util.List;

import org.javers.core.metamodel.annotation.DiffIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import modelo.Config;
import modelo.Entidade;
import modelo.auditoria.AuditLabel;
import modelo.auditoria.GeneroGramatical;
import modelo.exercicio.ExercicioPadrao;
import modelo.questao.Questao;
import modelo.teste.TestePadrao;
import util.StringAux;

@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@ToString(exclude = { "exerciciosPadrao", "questoes", "testePadrao" })
@Data
@Entity
public class AssuntoCurso extends Config implements Entidade
{
	private static final long serialVersionUID = 1L;

	@DiffIgnore
	@Id
	@GeneratedValue
	@EqualsAndHashCode.Include
	private Long id;

	@AuditLabel(value = "nome", atributo = "nome")
	private String nome;

	@Column(length = 255)
	@Size(max = 255)
	@AuditLabel(value = "chave", genero = GeneroGramatical.FEMININO)
	private String chave;

	@AuditLabel(value = "módulo")
	private Modulo modulo;

	@AuditLabel(value = "habilitado")
	private boolean habilidado;

	@AuditLabel(value = "exibir aula", genero = GeneroGramatical.FEMININO)
	private boolean showAula;

	@AuditLabel(value = "exibir anotação", genero = GeneroGramatical.FEMININO)
	private boolean showAnotacao;

	@AuditLabel(value = "exibir exercício")
	private boolean showExercicio;

	@AuditLabel(value = "exibir questão", genero = GeneroGramatical.FEMININO)
	private boolean showQuestao;

	@DiffIgnore
	@OneToMany(mappedBy = "assuntoCurso")
	private List<ExercicioPadrao> exerciciosPadrao = new ArrayList<ExercicioPadrao>();

	@DiffIgnore
	@OneToMany(mappedBy = "assuntoCurso")
	private List<Questao> questoes = new ArrayList<Questao>();

	@DiffIgnore
	@OneToOne
	private TestePadrao testePadrao;

	@AuditLabel(value = "hashtag", genero = GeneroGramatical.FEMININO)
	private String hashtag;

	@Transient
	private int numStar;

	@Column(length = 1023)
	@Size(max = 1023)
	@AuditLabel(value = "descrição", genero = GeneroGramatical.FEMININO)
	private String descricao;

	public String getChaveImage()
	{
		return StringAux.toLowerCaseFirst(chave);
	}

	public String getModuloImage()
	{
		return modulo.toString().toLowerCase();
	}
}
