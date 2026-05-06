package modelo.instagram;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;

import modelo.Entidade;
import modelo.configuracao.Opcao;

@Entity
public class ConviteGrupo implements Serializable, Entidade, Opcao
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(length = 1023)
	@Size(max = 1023)
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
		return "Cta: " + (id != null ? "id=" + id + ", " : "") + (nome != null ? "nome=" + nome : "");
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
		ConviteGrupo other = (ConviteGrupo) obj;
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
