package modelo.instagram;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OrderBy;
import jakarta.validation.constraints.Size;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import modelo.Entidade;
import modelo.configuracao.Opcao;
import modelo.usuario.Usuario;

@Entity
public class ConfigPost implements Serializable, Entidade, Opcao
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(length = 255)
	@Size(max = 255)
	private String nome;
	
	@Column(length = 255)
	@Size(max = 255)
	private String corFonte;
	
	@Column(length = 255)
	@Size(max = 255)
	private String corTitulo;
	
	@Column(length = 255)
	@Size(max = 255)
	private String corNome;
	
	@Column(length = 255)
	@Size(max = 255)
	private String corFormula;
	
	@Column(length = 4096)
	@Size(max = 4096)
	private String descricaoCTA;
	
	private LocalDate ultimoEnvio=LocalDate.now();
	
	private boolean ativo;
	
	@Enumerated(EnumType.STRING)
	private FinalidadeCta finalidadeCta=FinalidadeCta.Ensino;
	
	@Enumerated(EnumType.STRING)
	private PerfilCriador perfilCriador=PerfilCriador.Basico;
	
	private int transparencia;
	
	@OneToMany(orphanRemoval = true, mappedBy = "configPost")
	@OrderBy("ordem")
	@NotFound(action = NotFoundAction.IGNORE)
	private List<ImagemPost> imagensFundo = new ArrayList<ImagemPost>();
	
	@OneToMany(orphanRemoval = true, mappedBy = "configPost")
	@NotFound(action = NotFoundAction.IGNORE)
	private List<Cta> ctas = new ArrayList<Cta>();
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private ImagemPost logo;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private ImagemPost testeExeFeed;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private ImagemPost testeResFeed;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private ImagemPost testeExeReel;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private ImagemPost testeResReel;
	
	@OneToOne
	private Usuario usuario;
	
	@OneToMany(orphanRemoval = true, mappedBy = "configPost")
	@OrderBy("ordem")
	@NotFound(action = NotFoundAction.IGNORE)
	private List<ProgramacaoPost> programacoesPost = new ArrayList<ProgramacaoPost>();
	
	public boolean podeGerar()
	{
		if(!ativo)
		{
//			System.out.println("Não pode gerar ConfigPost pois não está ativo.");
			return false;
		}
		
		if(usuario.getEmail()==null||usuario.getEmail().equals(""))
		{
//			System.out.println("Não pode gerar ConfigPost pois usuário não tem email.");
			return false;
		}
		
		if(logo==null)
		{
//			System.out.println("Não pode gerar ConfigPost pois não tem logo.");
			return false;
		}
		
		if(nome==null)
		{
//			System.out.println("Não pode gerar ConfigPost pois não tem nome.");
			return false;
		}
		return true;
	}
	
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

	public String getCorFonte()
	{
		return corFonte;
	}

	public void setCorFonte(String corFonte)
	{
		this.corFonte = corFonte;
	}

	public String getCorTitulo()
	{
		return corTitulo;
	}

	public void setCorTitulo(String corTitulo)
	{
		this.corTitulo = corTitulo;
	}

	public List<ImagemPost> getImagensFundo()
	{
		return imagensFundo;
	}

	public void setImagensFundo(List<ImagemPost> imagensFundo)
	{
		this.imagensFundo = imagensFundo;
	}

	public Usuario getUsuario()
	{
		return usuario;
	}

	public void setUsuario(Usuario usuario)
	{
		this.usuario = usuario;
	}

	public ImagemPost getLogo()
	{
		return logo;
	}

	public void setLogo(ImagemPost logo)
	{
		this.logo = logo;
	}

	public int getTransparencia()
	{
		return transparencia;
	}
	
	public double getTransparenciaPorc()
	{
		return (double)transparencia/100;
	}
	

	public void setTransparencia(int transparencia)
	{
		this.transparencia = transparencia;
	}

	public List<ProgramacaoPost> getProgramacoesPost()
	{
		return programacoesPost;
	}

	public void setProgramacoesPost(List<ProgramacaoPost> programacoesPost)
	{
		this.programacoesPost = programacoesPost;
	}

	public LocalDate getUltimoEnvio()
	{
		return ultimoEnvio;
	}

	public void setUltimoEnvio(LocalDate ultimoEnvio)
	{
		this.ultimoEnvio = ultimoEnvio;
	}


	public PerfilCriador getPerfilCriador()
	{
		return perfilCriador;
	}

	public void setPerfilCriador(PerfilCriador perfilCriador)
	{
		this.perfilCriador = perfilCriador;
	}

	public String getCorNome()
	{
		return corNome;
	}

	public void setCorNome(String corNome)
	{
		this.corNome = corNome;
	}

	public ImagemPost getTesteExeFeed()
	{
		return testeExeFeed;
	}
	
	public String getCorFormula()
	{
		return corFormula;
	}

	public void setCorFormula(String corFormula)
	{
		this.corFormula = corFormula;
	}

	public List<ImagemPost> getListTesteFeed()
	{
		List<ImagemPost> list=new ArrayList<ImagemPost>();
		if(testeExeFeed!=null) list.add(testeExeFeed);
		if(testeResFeed!=null) list.add(testeResFeed);
		return list;
	}

	public List<ImagemPost> getListTesteReel()
	{
		List<ImagemPost> list=new ArrayList<ImagemPost>();
		if(testeExeReel!=null) list.add(testeExeReel);
		if(testeResReel!=null) list.add(testeResReel);
		return list;
	}
	
	public void setTesteExeFeed(ImagemPost testeExeFeed)
	{
		this.testeExeFeed = testeExeFeed;
	}

	public ImagemPost getTesteResFeed()
	{
		return testeResFeed;
	}

	public void setTesteResFeed(ImagemPost testeResFeed)
	{
		this.testeResFeed = testeResFeed;
	}

	public ImagemPost getTesteExeReel()
	{
		return testeExeReel;
	}

	public void setTesteExeReel(ImagemPost testeExeReel)
	{
		this.testeExeReel = testeExeReel;
	}

	public ImagemPost getTesteResReel()
	{
		return testeResReel;
	}

	public void setTesteResReel(ImagemPost testeResReel)
	{
		this.testeResReel = testeResReel;
	}

	public String getDescricaoCTA()
	{
		return descricaoCTA;
	}

	public void setDescricaoCTA(String descricaoCTA)
	{
		this.descricaoCTA = descricaoCTA;
	}

	public List<Cta> getCtas()
	{
		return ctas;
	}

	public void setCtas(List<Cta> ctas)
	{
		this.ctas = ctas;
	}

	public boolean isAtivo()
	{
		return ativo;
	}

	public void setAtivo(boolean ativo)
	{
		this.ativo = ativo;
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
		return "ConfigPost: " + (id != null ? "id=" + id + ", " : "") + (nome != null ? "nome=" + nome : "");
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
		ConfigPost other = (ConfigPost) obj;
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
