package Modelo.Instagram;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import Modelo.Entidade;
import Modelo.Configuracao.Opcao;

@Entity
public class Hashtag implements Serializable, Entidade, Opcao
{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(length = 255)
	@Size(max = 255)
	private String nome;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome.trim();
	}

	@Override
	public String toString()
	{
		return "Hashtag: " + (id != null ? "id=" + id + ", " : "") + (nome != null ? "nome=" + nome : "");
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Hashtag other = (Hashtag) obj;
		if (nome == null)
		{
			if (other.nome != null)
				return false;
		}
		else if (!nome.equals(other.nome))
			return false;
		return true;
	}

}
