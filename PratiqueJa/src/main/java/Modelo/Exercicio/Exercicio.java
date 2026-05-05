package Modelo.Exercicio;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import Modelo.Entidade;
import Modelo.Matematica.Conta;
import Modelo.Usuario.Usuario;

@Entity
public class Exercicio implements Serializable, Entidade
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne(optional = true)
	@JoinColumn(nullable = true)
	private ExercicioPadrao exercicioPadrao;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "exercicio")
	private List<Conta> contas = new ArrayList<Conta>();

	private boolean realizado = false;
	
	private double numContasRealizadas;
	
	private int numCorretas;
	
	private LocalDate realizacao;

	private LocalDate prazo;

	private double nota;

	@ManyToOne
	private Usuario usuario;
	
	@OneToOne
	private ResultadoExercicio resultadoExercicio;

	public void calculaNota()
	{
		nota=100*(double)numCorretas/numContasRealizadas;
	}

	public void incrementaContasRealizadas()
	{
		numContasRealizadas++;
	}
	
	public void incrementaContasCorretas()
	{
		numCorretas++;
	}
	
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

	public Usuario getUsuario()
	{
		return usuario;
	}

	public void setUsuario(Usuario usuario)
	{
		this.usuario = usuario;
	}

	public boolean isRealizado()
	{
		return realizado;
	}

	public void setRealizado(boolean realizado)
	{
		this.realizado = realizado;
	}

	public LocalDate getRealizacao()
	{
		return realizacao;
	}

	public void setRealizacao(LocalDate realizacao)
	{
		this.realizacao = realizacao;
	}

	public LocalDate getPrazo()
	{
		return prazo;
	}

	public void setPrazo(LocalDate prazo)
	{
		this.prazo = prazo;
	}

	public double getNota()
	{
		return nota;
	}

	public void setNota(double nota)
	{
		this.nota = nota;
	}

	public double getNotaPorcentagem()
	{
		return nota;
	}

	public double getNumContasRealizadas()
	{
		return numContasRealizadas;
	}

	public void setNumContasRealizadas(double numContasRealizadas)
	{
		this.numContasRealizadas = numContasRealizadas;
	}

	public int getNumCorretas()
	{
		return numCorretas;
	}

	public void setNumCorretas(int numCorretas)
	{
		this.numCorretas = numCorretas;
	}

	public ResultadoExercicio getResultadoExercicio()
	{
		return resultadoExercicio;
	}

	public void setResultadoExercicio(ResultadoExercicio resultadoExercicio)
	{
		this.resultadoExercicio = resultadoExercicio;
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
		Exercicio other = (Exercicio) obj;
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
		return "Exercicio: " + (id != null ? "id=" + id + ", " : "") + "realizada=" + realizado + ", "
		+ (realizacao != null ? "realizacao=" + realizacao + ", " : "") + (prazo != null ? "prazo=" + prazo + ", " : "")
		+ "nota=" + nota + ", " + (usuario != null ? "usuario=" + usuario : "");
	}

}