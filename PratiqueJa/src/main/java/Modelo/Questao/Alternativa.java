package Modelo.Questao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

import Modelo.Entidade;

@Entity
public class Alternativa implements Serializable, Entidade
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(length = 1023)
	@Size(max = 1023)
	private String texto;

	private boolean correta = false;

	private int ordem;

	private int qtnEscolhida;

	@ManyToOne
	private Questao questao;

	public Long getId()
	{
		return id;
	}

	public String getTexto()
	{
		return texto;
	}

	public void setTexto(String texto)
	{
		this.texto = texto;
	}

	public boolean isCorreta()
	{
		return correta;
	}

	public void setCorreta(boolean correta)
	{
		this.correta = correta;
	}

	public int getQtnEscolhida()
	{
		return qtnEscolhida;
	}

	public void setQtnEscolhida(int qtnEscolhida)
	{
		this.qtnEscolhida = qtnEscolhida;
	}

	public void incrementaQtnEscolhida()
	{
		this.qtnEscolhida++;
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
		Alternativa other = (Alternativa) obj;
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
		return "Alternativa [id=" + id + ", texto=" + texto + ", correta=" + correta + "]";
	}

	public Questao getQuestao()
	{
		return questao;
	}

	public void setQuestao(Questao questao)
	{
		this.questao = questao;
	}

	public int getOrdem()
	{
		return ordem;
	}

	public void setOrdem(int ordem)
	{
		this.ordem = ordem;
	}

	public String getLetra()
	{
		return "" + (char) (65 + ordem);
	}
}
