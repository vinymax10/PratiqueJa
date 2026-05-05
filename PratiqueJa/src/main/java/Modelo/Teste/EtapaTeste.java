package Modelo.Teste;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import Modelo.Entidade;
import Modelo.Exercicio.ExercicioPadrao;
import Modelo.Matematica.Conta;

@Entity
public class EtapaTeste implements Serializable, Entidade
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne(optional = true)
	@JoinColumn(nullable = true)
	private ExercicioPadrao exercicioPadrao;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "etapaTeste")
	private List<Conta> contas = new ArrayList<Conta>();

	@ManyToOne
	private Teste teste;

	public Long getId()
	{
		return id;
	}

	public ExercicioPadrao getExercicioPadrao()
	{
		return exercicioPadrao;
	}

	public void setExercicioPadrao(ExercicioPadrao exercicioPadrao)
	{
		this.exercicioPadrao = exercicioPadrao;
	}

	public List<Conta> getContas()
	{
		return contas;
	}

	public void setContas(List<Conta> contas)
	{
		this.contas = contas;
	}

	public Teste getTeste()
	{
		return teste;
	}

	public void setTeste(Teste teste)
	{
		this.teste = teste;
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(id);
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
		EtapaTeste other = (EtapaTeste) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString()
	{
		return "EtapaTeste: " + (id != null ? "id=" + id + ", " : "")
		+ (exercicioPadrao != null ? "exercicio=" + exercicioPadrao + ", " : "") + (contas != null ? "contas=" + contas + ", " : "")
		+ (teste != null ? "teste=" + teste : "");
	}

}