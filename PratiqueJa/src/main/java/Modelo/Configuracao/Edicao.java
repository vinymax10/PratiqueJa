package Modelo.Configuracao;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import Modelo.Entidade;
import Modelo.AssuntoCurso.Enum.Modulo;

@Entity
public class Edicao implements Serializable, Entidade
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	private int edicaoBasico=1;

	private int edicaoIntermediario=1;

	private int edicaoAvancado=1;

	public void incrementa(Modulo modulo)
	{
		switch(modulo)
		{
			case Basico: edicaoBasico++;break;
			case Intermediario: edicaoIntermediario++;break;
			case Avancado: edicaoAvancado++;break;
			default:
				break;
		}
	}
	
	public int edicaoModulo(Modulo modulo)
	{
		switch(modulo)
		{
			case Basico: return edicaoBasico;
			case Intermediario: return edicaoIntermediario;
			case Avancado: return edicaoAvancado;
			default:
				break;
		}
		return 0;
	}
	
	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public int getEdicaoBasico()
	{
		return edicaoBasico;
	}

	public void setEdicaoBasico(int edicaoBasico)
	{
		this.edicaoBasico = edicaoBasico;
	}

	public int getEdicaoIntermediario()
	{
		return edicaoIntermediario;
	}

	public void setEdicaoIntermediario(int edicaoIntermediario)
	{
		this.edicaoIntermediario = edicaoIntermediario;
	}

	public int getEdicaoAvancado()
	{
		return edicaoAvancado;
	}

	public void setEdicaoAvancado(int edicaoAvancado)
	{
		this.edicaoAvancado = edicaoAvancado;
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
		Edicao other = (Edicao) obj;
		return Objects.equals(id, other.id);
	}

}