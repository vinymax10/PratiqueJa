package Modelo.AssuntoCurso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Size;

import Auxiliar.StringAux;
import Modelo.Entidade;
import Modelo.AssuntoCurso.Enum.Modulo;
import Modelo.Exercicio.ExercicioPadrao;
import Modelo.Questao.Questao;
import Modelo.Teste.TestePadrao;
import jakarta.persistence.Transient;

@Entity
public class AssuntoCurso implements Serializable, Entidade
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	private String nome;

	private int indice;

	@Column(length = 255)
	@Size(max = 255)
	private String chave;

	private Modulo modulo;

	private boolean habilidado;
	
	private boolean showAula;

	private boolean showAnotacao;

	private boolean showExercicio;

	private boolean showQuestao;

	@OneToMany(mappedBy = "assuntoCurso")
	private List<ExercicioPadrao> exerciciosPadrao = new ArrayList<ExercicioPadrao>();

	@OneToMany(mappedBy = "assuntoCurso")
	private List<Questao> questoes = new ArrayList<Questao>();

	@OneToOne
	private TestePadrao testePadrao;
	
	private String hashtag;
	
	@Transient
	private int numStar;
	
	@Column(length = 1023)
	@Size(max = 1023)
	private String descricao;

	public Long getId()
	{
		return id;
	}

	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public String getChave()
	{
		return chave;
	}

	public String getChaveImage()
	{
		return StringAux.toLowerCaseFirst(chave);
	}

	public void setChave(String chave)
	{
		this.chave = chave;
	}

	public Modulo getModulo()
	{
		return modulo;
	}

	public String getModuloImage()
	{
		return modulo.toString().toLowerCase();
	}

	public void setModulo(Modulo modulo)
	{
		this.modulo = modulo;
	}

	public List<ExercicioPadrao> getExerciciosPadrao()
	{
		return exerciciosPadrao;
	}

	public void setExerciciosPadrao(List<ExercicioPadrao> exerciciosPadrao)
	{
		this.exerciciosPadrao = exerciciosPadrao;
	}

	public List<Questao> getQuestoes()
	{
		return questoes;
	}

	public void setQuestoes(List<Questao> questoes)
	{
		this.questoes = questoes;
	}

	public boolean isShowAula()
	{
		return showAula;
	}

	public void setShowAula(boolean showAula)
	{
		this.showAula = showAula;
	}

	public boolean isShowAnotacao()
	{
		return showAnotacao;
	}

	public void setShowAnotacao(boolean showAnotacao)
	{
		this.showAnotacao = showAnotacao;
	}

	public boolean isShowExercicio()
	{
		return showExercicio;
	}

	public void setShowExercicio(boolean showExercicio)
	{
		this.showExercicio = showExercicio;
	}

	public boolean isShowQuestao()
	{
		return showQuestao;
	}

	public void setShowQuestao(boolean showQuestao)
	{
		this.showQuestao = showQuestao;
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
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AssuntoCurso other = (AssuntoCurso) obj;
		if (id == null)
		{
			if (other.id != null)
				return false;
		}
		else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return "AssuntoCurso [id=" + id + ", nome=" + nome + ", chave=" + chave + ", modulo=" + modulo + "]";
	}

	public int getIndice()
	{
		return indice;
	}

	public void setIndice(int indice)
	{
		this.indice = indice;
	}

	public boolean isHabilidado()
	{
		return habilidado;
	}

	public void setHabilidado(boolean habilidado)
	{
		this.habilidado = habilidado;
	}

	public TestePadrao getTestePadrao()
	{
		return testePadrao;
	}

	public void setTestePadrao(TestePadrao testePadrao)
	{
		this.testePadrao = testePadrao;
	}

	public String getHashtag()
	{
		return hashtag;
	}

	public void setHashtag(String hashtag)
	{
		this.hashtag = hashtag;
	}

	public int getNumStar()
	{
		return numStar;
	}

	public void setNumStar(int numStar)
	{
		this.numStar = numStar;
	}

	public String getDescricao()
	{
		return descricao;
	}

	public void setDescricao(String descricao)
	{
		this.descricao = descricao;
	}

}