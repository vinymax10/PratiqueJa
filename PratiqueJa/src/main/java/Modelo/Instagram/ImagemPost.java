package Modelo.Instagram;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;

import Modelo.Entidade;

@Entity
public class ImagemPost implements Serializable, Entidade
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(length = 1023)
	@Size(max = 1023)
	private String endImagem;
	
	@Column(length = 255)
	@Size(max = 255)
	private String nome;

	@ManyToOne
	private ConfigPost configPost;
	
	private boolean feed;

	private int ordem;

	@OneToOne(mappedBy = "logo")
	private ConfigPost configPostLogo;

//  Volta
	@OneToMany(mappedBy = "backgroundFeed")
	private List<ProgramacaoPost> programacoesFeed = new ArrayList<ProgramacaoPost>();
	
//  Volta
	@OneToMany(mappedBy = "backgroundReel")
	private List<ProgramacaoPost> programacoesReel = new ArrayList<ProgramacaoPost>();
	
	public Long getId()
	{
		return id;
	}

	public String getEndImagem()
	{
		return endImagem;
	}

	public void setEndImagem(String endImagem)
	{
		this.endImagem = endImagem;
	}

	public ConfigPost getConfigPost()
	{
		return configPost;
	}

	public void setConfigPost(ConfigPost configPost)
	{
		this.configPost = configPost;
	}

	public int getOrdem()
	{
		return ordem;
	}

	public void setOrdem(int ordem)
	{
		this.ordem = ordem;
	}

	public ConfigPost getConfigPostLogo()
	{
		return configPostLogo;
	}

	public void setConfigPostLogo(ConfigPost configPostLogo)
	{
		this.configPostLogo = configPostLogo;
	}

	public boolean isFeed()
	{
		return feed;
	}

	public void setFeed(boolean feed)
	{
		this.feed = feed;
	}

	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
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
		ImagemPost other = (ImagemPost) obj;
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
		return "ImagemPost [id=" + id + ", endDocumentacao=" + endImagem + "]";
	}

	public List<ProgramacaoPost> getProgramacoesFeed()
	{
		return programacoesFeed;
	}

	public void setProgramacoesFeed(List<ProgramacaoPost> programacoesFeed)
	{
		this.programacoesFeed = programacoesFeed;
	}

	public List<ProgramacaoPost> getProgramacoesReel()
	{
		return programacoesReel;
	}

	public void setProgramacoesReel(List<ProgramacaoPost> programacoesReel)
	{
		this.programacoesReel = programacoesReel;
	}

}
