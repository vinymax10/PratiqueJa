package Modelo.Exercicio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import Modelo.Entidade;
import Modelo.AssuntoCurso.AssuntoCurso;
import Modelo.Exercicio.Enum.Nivel;
import Modelo.Exercicio.Enum.TipoExercicio;

@Entity
public class ExercicioPadrao implements Serializable, Entidade
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	private AssuntoCurso assuntoCurso;

	private Nivel nivel;

	private String nome;

	private int quantidade;

	private String enunciado;
	private String enunciadoSingular;

	private String descricao;

	private TipoExercicio tipoExercicio;

//	Mostrar o exercicio novamente na resolução em pdf.
	private boolean mostrarResolucao;

	private boolean imagemQuadrada;

//  Volta
	@OneToMany(orphanRemoval = true, mappedBy = "exercicioPadrao")
	private List<ResultadoExercicio> resultados = new ArrayList<ResultadoExercicio>();

	public static long getSerialversionuid()
	{
		return serialVersionUID;
	}

	public Long getId()
	{
		return id;
	}

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

	public List<ResultadoExercicio> getResultados()
	{
		return resultados;
	}

	public void setResultados(List<ResultadoExercicio> resultados)
	{
		this.resultados = resultados;
	}

	public String getClasse()
	{
		return "Matematica." + assuntoCurso.getModulo() + "." + assuntoCurso.getChave() + "." + nome;
	}

	public Nivel getNivel()
	{
		return nivel;
	}

	public void setNivel(Nivel nivel)
	{
		this.nivel = nivel;
	}

	public int getQuantidade()
	{
		return quantidade;
	}

	public void setQuantidade(int quantidade)
	{
		this.quantidade = quantidade;
	}

	public String getEnunciadoSingular()
	{
		return enunciadoSingular;
	}

	public void setEnunciadoSingular(String enunciadoSingular)
	{
		this.enunciadoSingular = enunciadoSingular;
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

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		ExercicioPadrao other = (ExercicioPadrao) obj;
		if(id == null)
		{
			if(other.id != null)
				return false;
		}
		else if(!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return "ExercicioPadrao: " + (assuntoCurso != null ? "assuntoCurso=" + assuntoCurso.getNome() + ", " : "")
		+ (nivel != null ? "nivel=" + nivel + ", " : "") + (nome != null ? "nome=" + nome + ", " : "") + "quantidade="
		+ quantidade;
	}

	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public String getEnunciado()
	{
		return enunciado;
	}

	public void setEnunciado(String enunciado)
	{
		this.enunciado = enunciado;
	}

	public TipoExercicio getTipoExercicio()
	{
		return tipoExercicio;
	}

	public void setTipoExercicio(TipoExercicio tipoExercicio)
	{
		this.tipoExercicio = tipoExercicio;
	}

	public String getDescricao()
	{
		return descricao;
	}

	public void setDescricao(String descricao)
	{
		this.descricao = descricao;
	}

	public AssuntoCurso getAssuntoCurso()
	{
		return assuntoCurso;
	}

	public void setAssuntoCurso(AssuntoCurso assuntoCurso)
	{
		this.assuntoCurso = assuntoCurso;
	}

	public boolean isMostrarResolucao()
	{
		return mostrarResolucao;
	}

	public void setMostrarResolucao(boolean mostrarResolucao)
	{
		this.mostrarResolucao = mostrarResolucao;
	}

	public boolean isImagemQuadrada()
	{
		return imagemQuadrada;
	}

	public void setImagemQuadrada(boolean imagemQuadrada)
	{
		this.imagemQuadrada = imagemQuadrada;
	}

}