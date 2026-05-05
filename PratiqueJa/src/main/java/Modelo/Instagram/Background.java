package Modelo.Instagram;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import Modelo.Entidade;

@Entity
public class Background implements Serializable,Entidade
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	
	private String endereco;
	
	private String nome;

	private boolean feed;
	
	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getEndereco()
	{
		return endereco;
	}

	public void setEndereco(String endereco)
	{
		this.endereco = endereco;
	}

	public boolean isFeed()
	{
		return feed;
	}

	public void setFeed(boolean feed)
	{
		this.feed = feed;
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
		Background other = (Background) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString()
	{
		return "Background [id=" + id + ", endereco=" + endereco + ", nome=" + nome + "]";
	}
	
}
