package modelo.exercicio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.javers.core.metamodel.annotation.DiffIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import modelo.Entidade;
import modelo.assuntocurso.AssuntoCurso;
import modelo.auditoria.AuditLabel;
import modelo.auditoria.GeneroGramatical;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = { "assuntoCurso", "resultados" })
@Data
@Entity
public class ExercicioPadrao implements Serializable, Entidade
{
	private static final long serialVersionUID = 1L;

	@DiffIgnore
	@Id
	@GeneratedValue
	@EqualsAndHashCode.Include
	private Long id;

	@DiffIgnore
	@ManyToOne
	private AssuntoCurso assuntoCurso;

	@AuditLabel(value = "nível")
	private Nivel nivel;

	@AuditLabel(value = "nome")
	private String nome;

	@AuditLabel(value = "quantidade", genero = GeneroGramatical.FEMININO)
	private int quantidade;

	@AuditLabel(value = "enunciado")
	private String enunciado;

	@AuditLabel(value = "enunciado singular")
	private String enunciadoSingular;

	@AuditLabel(value = "descrição", genero = GeneroGramatical.FEMININO)
	private String descricao;

	@AuditLabel(value = "tipo de exercício")
	private TipoExercicio tipoExercicio;

	@AuditLabel(value = "mostrar resolução")
	private boolean mostrarResolucao;

	@AuditLabel(value = "imagem quadrada", genero = GeneroGramatical.FEMININO)
	private boolean imagemQuadrada;

	@DiffIgnore
	@OneToMany(orphanRemoval = true, mappedBy = "exercicioPadrao")
	private List<ResultadoExercicio> resultados = new ArrayList<ResultadoExercicio>();

	public String enderecoImage()
	{
		String prefixo = "/image/matematica/";
		String sufixo = ".png";
		String modulo = assuntoCurso.getModulo().toString().toLowerCase();
		String assunto = assuntoCurso.getChave().substring(0, 1).toLowerCase() + assuntoCurso.getChave().substring(1);
		String nivel = this.nivel.toString().toLowerCase();
		return prefixo + modulo + "/" + assunto + "/" + nivel + sufixo;
	}

	public List<Integer> numEstrelas()
	{
		int numEstrelas = Integer.valueOf(nivel.getNome().split(" ")[1]);
		List<Integer> list = new ArrayList<Integer>();
		for(int i = 0; i < numEstrelas; i++)
			list.add(0);
		return list;
	}

	public String getClasse()
	{
		return "matematica." + assuntoCurso.getModulo().toString().toLowerCase() + "." + assuntoCurso.getChave().toLowerCase() + "." + nome;
	}

	public String getNivelRomano()
	{
		switch(nivel)
		{
			case Nivel1:
				return "I";
			case Nivel2:
				return "II";
			case Nivel3:
				return "III";
			default:
				return "";
		}
	}
}
