package modelo.teste;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import modelo.Entidade;
import modelo.usuario.Usuario;

@Entity
public class ResultadoTeste implements Serializable, Entidade
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	private Usuario usuario;

	@ManyToOne(optional = true)
	@JoinColumn(nullable = true)
	private TestePadrao testePadrao;

	private LocalDate realizacao;

	private double nota;

	public static long getSerialversionuid()
	{
		return serialVersionUID;
	}

	public Long getId()
	{
		return id;
	}

	public double getNota()
	{
		return nota;
	}

	public void setNota(double nota)
	{
		this.nota = nota;
	}

	public Usuario getUsuario()
	{
		return usuario;
	}

	public void setUsuario(Usuario usuario)
	{
		this.usuario = usuario;
	}

	public TestePadrao getTestePadrao()
	{
		return testePadrao;
	}

	public void setTestePadrao(TestePadrao testePadrao)
	{
		this.testePadrao = testePadrao;
	}

	public LocalDate getRealizacao()
	{
		return realizacao;
	}

	public void setRealizacao(LocalDate realizacao)
	{
		this.realizacao = realizacao;
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
		ResultadoTeste other = (ResultadoTeste) obj;
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
		return "ResultadoTeste: " + (id != null ? "id=" + id + ", " : "")
		+ (usuario != null ? "usuario=" + usuario + ", " : "")
		+ (realizacao != null ? "realizacao=" + realizacao + ", " : "") + "nota=" + nota;
	}

}
