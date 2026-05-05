package Modelo.Teste;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import Modelo.Entidade;
import Modelo.Exercicio.ExercicioPadrao;

@Entity
public class ConteudoTeste implements Serializable, Entidade
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne(optional = true)
	@JoinColumn(nullable = true)
	private ExercicioPadrao exercicioPadrao;

	private int quantidade;
	
	@ManyToOne(optional = true)
	@JoinColumn(nullable = true)
	private TestePadrao testePadrao;

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

	public int getQuantidade()
	{
		return quantidade;
	}

	public void setQuantidade(int quantidade)
	{
		this.quantidade = quantidade;
	}

	public TestePadrao getTestePadrao()
	{
		return testePadrao;
	}

	public void setTestePadrao(TestePadrao testePadrao)
	{
		this.testePadrao = testePadrao;
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
		ConteudoTeste other = (ConteudoTeste) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString()
	{
		return "ConteudoTeste: " + (id != null ? "id=" + id + ", " : "") + "quantidade=" + quantidade;
	}


}