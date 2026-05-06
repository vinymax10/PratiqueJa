package modelo.questao;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import modelo.Entidade;
import modelo.usuario.Usuario;

@Entity
public class ResultadoQuestao implements Serializable, Entidade
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

//  Volta
	@ManyToOne
	private Usuario usuario;

	@ManyToOne
	private Questao questao;

	private boolean acertou;

	public static long getSerialversionuid()
	{
		return serialVersionUID;
	}

	public Long getId()
	{
		return id;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((questao == null) ? 0 : questao.hashCode());
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
		ResultadoQuestao other = (ResultadoQuestao) obj;
		if(questao == null)
		{
			if(other.questao != null)
				return false;
		}
		else if(!questao.equals(other.questao))
			return false;
		return true;
	}

	public Usuario getUsuario()
	{
		return usuario;
	}

	public void setUsuario(Usuario usuario)
	{
		this.usuario = usuario;
	}

	public Questao getQuestao()
	{
		return questao;
	}

	public void setQuestao(Questao questao)
	{
		this.questao = questao;
	}

	public boolean isAcertou()
	{
		return acertou;
	}

	public void setAcertou(boolean acertou)
	{
		this.acertou = acertou;
	}

}
