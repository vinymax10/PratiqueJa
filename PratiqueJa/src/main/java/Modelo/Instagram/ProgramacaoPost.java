package Modelo.Instagram;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import Modelo.Entidade;
import Modelo.AssuntoCurso.AssuntoCurso;

@Entity
public class ProgramacaoPost implements Serializable, Entidade
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	private LocalDate data;
	
	private int ordem;
	
	private boolean alternativaReel=true;
	private boolean teste=false;
	private boolean avulsa=false;

//	-------------Feed-------------
	private boolean backgroundAleatorioFeed=true;
	
	private boolean basePadraoFeed=true;
	
	@ManyToOne
	@JoinColumn(nullable = true)
	private ImagemPost backgroundFeed;

	@ManyToOne
	@JoinColumn(nullable = true)
	private Background padraoFeed;
	
//	-------------Reel-------------
	private boolean backgroundAleatorioReel=true;
	
	private boolean basePadraoReel=true;
	
	@ManyToOne
	@JoinColumn(nullable = true)
	private ImagemPost backgroundReel;
	
	@ManyToOne
	@JoinColumn(nullable = true)
	private Background padraoReel;

//--------------------------------------
	
	@ManyToOne
	private AssuntoCurso assuntoCurso;
	
	@ManyToOne
	private ConfigPost configPost;
	
	public ProgramacaoPost clone()
	{
		ProgramacaoPost clone=new ProgramacaoPost();
		clone.alternativaReel=this.alternativaReel;
		clone.assuntoCurso=this.assuntoCurso;
		clone.backgroundAleatorioFeed=this.backgroundAleatorioFeed;
		clone.backgroundAleatorioReel=this.backgroundAleatorioReel;
		clone.backgroundFeed=this.backgroundFeed;
		clone.backgroundReel=this.backgroundReel;
		clone.basePadraoFeed=this.basePadraoFeed;
		clone.basePadraoReel=this.basePadraoReel;
		clone.configPost=this.configPost;
		clone.data=this.data;
		clone.ordem=this.ordem;
		clone.padraoFeed=this.padraoFeed;
		clone.padraoReel=this.padraoReel;
		clone.teste=this.teste;

		return clone;
	}
	
	
	public void updateData()
	{
		LocalDate ultimoEnvio=configPost.getUltimoEnvio();
		PerfilCriador perfilCriador=configPost.getPerfilCriador();
		
		data=ultimoEnvio.plusDays((ordem+1)*perfilCriador.getIntervalo());
	}
	
	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public LocalDate getData()
	{
		return data;
	}

	public void setData(LocalDate data)
	{
		this.data = data;
	}

	public AssuntoCurso getAssuntoCurso()
	{
		return assuntoCurso;
	}

	public void setAssuntoCurso(AssuntoCurso assuntoCurso)
	{
		this.assuntoCurso = assuntoCurso;
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

	public boolean isBackgroundAleatorioFeed()
	{
		return backgroundAleatorioFeed;
	}

	public void setBackgroundAleatorioFeed(boolean backgroundAleatorioFeed)
	{
		this.backgroundAleatorioFeed = backgroundAleatorioFeed;
	}

	public boolean isBasePadraoFeed()
	{
		return basePadraoFeed;
	}

	public void setBasePadraoFeed(boolean basePadraoFeed)
	{
		this.basePadraoFeed = basePadraoFeed;
	}

	public ImagemPost getBackgroundFeed()
	{
		return backgroundFeed;
	}

	public void setBackgroundFeed(ImagemPost backgroundFeed)
	{
		this.backgroundFeed = backgroundFeed;
	}

	public boolean isBackgroundAleatorioReel()
	{
		return backgroundAleatorioReel;
	}

	public void setBackgroundAleatorioReel(boolean backgroundAleatorioReel)
	{
		this.backgroundAleatorioReel = backgroundAleatorioReel;
	}

	public boolean isBasePadraoReel()
	{
		return basePadraoReel;
	}

	public void setBasePadraoReel(boolean basePadraoReel)
	{
		this.basePadraoReel = basePadraoReel;
	}

	public ImagemPost getBackgroundReel()
	{
		return backgroundReel;
	}

	public void setBackgroundReel(ImagemPost backgroundReel)
	{
		this.backgroundReel = backgroundReel;
	}

	public Background getPadraoReel()
	{
		return padraoReel;
	}

	public void setPadraoReel(Background padraoReel)
	{
		this.padraoReel = padraoReel;
	}

	public Background getPadraoFeed()
	{
		return padraoFeed;
	}

	public void setPadraoFeed(Background padraoFeed)
	{
		this.padraoFeed = padraoFeed;
	}

	public boolean isAlternativaReel()
	{
		return alternativaReel;
	}

	public void setAlternativaReel(boolean alternativaReel)
	{
		this.alternativaReel = alternativaReel;
	}

	public boolean isTeste()
	{
		return teste;
	}

	public void setTeste(boolean teste)
	{
		this.teste = teste;
	}

	public boolean isAvulsa()
	{
		return avulsa;
	}

	public void setAvulsa(boolean avulsa)
	{
		this.avulsa = avulsa;
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
		ProgramacaoPost other = (ProgramacaoPost) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString()
	{
		return "ProgramacaoPost: " + (id != null ? "id=" + id + ", " : "") 
		+ (data != null ? "data=" + data + ", " : "")
		+ "ordem=" + ordem + ", alternativaReel=" + alternativaReel + ", backgroundAleatorioFeed="
		+ backgroundAleatorioFeed + ", basePadraoFeed=" + basePadraoFeed + ", "
		+ (backgroundFeed != null ? "backgroundFeed=" + backgroundFeed + ", " : "")
		+ (padraoFeed != null ? "padraoFeed=" + padraoFeed + ", " : "") + "backgroundAleatorioReel="
		+ backgroundAleatorioReel + ", basePadraoReel=" + basePadraoReel + ", "
		+ (backgroundReel != null ? "backgroundReel=" + backgroundReel + ", " : "")
		+ (padraoReel != null ? "padraoReel=" + padraoReel + ", " : "")
		+ (assuntoCurso != null ? "assuntoCurso=" + assuntoCurso.getNome() + ", " : ""
		);
	}

}
