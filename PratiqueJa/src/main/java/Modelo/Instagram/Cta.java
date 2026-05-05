package Modelo.Instagram;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Size;

import Modelo.Entidade;
import Modelo.Configuracao.Opcao;

@Entity
public class Cta implements Serializable, Entidade, Opcao
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(length = 1023)
	@Size(max = 1023)
	private String nome;
	
	@Enumerated(EnumType.STRING)
	private FinalidadeCta finalidadeCta;
	
	@ManyToOne
	private ConfigPost configPost;

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

	public ConfigPost getConfigPost()
	{
		return configPost;
	}

	public void setConfigPost(ConfigPost configPost)
	{
		this.configPost = configPost;
	}

	public FinalidadeCta getFinalidadeCta()
	{
		return finalidadeCta;
	}

	public void setFinalidadeCta(FinalidadeCta finalidadeCta)
	{
		this.finalidadeCta = finalidadeCta;
	}

	@Override
	public String toString()
	{
		return "Cta [id=" + id + ", nome=" + nome + ", finalidadeCta=" + finalidadeCta + (configPost != null ? "configPost=" + configPost.getUsuario().getFirstNome() : "") + "]";
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
		Cta other = (Cta) obj;
		if (nome == null)
		{
			if (other.nome != null)
				return false;
		}
		else if (!nome.toLowerCase().equals(other.nome.toLowerCase()))
			return false;
		return true;
	}

}
