package Modelo.Questao;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;

import Modelo.Entidade;

@Entity
public class Paragrafo implements Serializable, Entidade, Comparable<Paragrafo>
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(length = 4095)
	@Size(max = 4095)
	private String texto;

	private int ordem;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private ImagemFile imagemFile;

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
		Paragrafo other = (Paragrafo) obj;
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
		return "Paragrafo [id=" + id + ", texto=" + texto + ", ordem=" + ordem + "]";
	}

	public boolean isTipoImagem()
	{
		if(imagemFile != null)
			return true;

		return false;
	}

	public int getOrdem()
	{
		return ordem;
	}

	public void setOrdem(int ordem)
	{
		this.ordem = ordem;
	}

	public ImagemFile getImagemFile()
	{
		return imagemFile;
	}

	public void setImagemFile(ImagemFile imagemFile)
	{
		this.imagemFile = imagemFile;
	}

	public Questao getQuestao()
	{
		return questao;
	}

	public void setQuestao(Questao questao)
	{
		this.questao = questao;
	}

	@Override
	public int compareTo(Paragrafo o)
	{
		return ordem - o.ordem;
	}

}
