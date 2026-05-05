package Bean.Download;

import java.io.Serializable;
import java.util.List;

import Modelo.AssuntoCurso.AssuntoCurso;
import Modelo.Usuario.Usuario;

public class SetDownload implements Serializable
{
	private static final long serialVersionUID = 1L;

	private String nomeArquivo;
	
	private boolean identificacaoExercicio = false;
	
	private boolean identificacaoQuestao = false;

	private boolean respostasExercicio = true;

	private boolean respostasQuestao = true;

	private boolean resolucaoExercicio = true;
	
	private boolean resolucaoQuestao = true;

	private boolean anotacao = true;
	
	private boolean questao = false;
	
	private Usuario usuario;
	
	private List<AssuntoCurso> assuntosCurso;
	
	private int quantidadeNivel1=1;
	
	private int quantidadeNivel2;
	
	private int quantidadeNivel3;


	public boolean isIdentificacaoExercicio()
	{
		return identificacaoExercicio;
	}

	public void setIdentificacaoExercicio(boolean identificacaoExercicio)
	{
		this.identificacaoExercicio = identificacaoExercicio;
	}

	public boolean isRespostasExercicio()
	{
		return respostasExercicio;
	}

	public void setRespostasExercicio(boolean respostasExercicio)
	{
		this.respostasExercicio = respostasExercicio;
	}

	public boolean isResolucaoExercicio()
	{
		return resolucaoExercicio;
	}

	public void setResolucaoExercicio(boolean resolucaoExercicio)
	{
		this.resolucaoExercicio = resolucaoExercicio;
	}

	public boolean isResolucaoQuestao()
	{
		return resolucaoQuestao;
	}

	public void setResolucaoQuestao(boolean resolucaoQuestao)
	{
		this.resolucaoQuestao = resolucaoQuestao;
	}

	public Usuario getUsuario()
	{
		return usuario;
	}

	public void setUsuario(Usuario usuario)
	{
		this.usuario = usuario;
	}

	public boolean isAnotacao()
	{
		return anotacao;
	}

	public void setAnotacao(boolean anotacao)
	{
		this.anotacao = anotacao;
	}

	public List<AssuntoCurso> getAssuntosCurso()
	{
		return assuntosCurso;
	}

	public void setAssuntosCurso(List<AssuntoCurso> assuntosCurso)
	{
		this.assuntosCurso = assuntosCurso;
	}

	public int getQuantidadeNivel1()
	{
		return quantidadeNivel1;
	}

	public void setQuantidadeNivel1(int quantidadeNivel1)
	{
		this.quantidadeNivel1 = quantidadeNivel1;
	}

	public int getQuantidadeNivel2()
	{
		return quantidadeNivel2;
	}

	public void setQuantidadeNivel2(int quantidadeNivel2)
	{
		this.quantidadeNivel2 = quantidadeNivel2;
	}

	public int getQuantidadeNivel3()
	{
		return quantidadeNivel3;
	}

	public void setQuantidadeNivel3(int quantidadeNivel3)
	{
		this.quantidadeNivel3 = quantidadeNivel3;
	}

	public String getNomeArquivo()
	{
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo)
	{
		this.nomeArquivo = nomeArquivo;
	}
	
	public boolean isQuestao()
	{
		return questao;
	}

	public void setQuestao(boolean questao)
	{
		this.questao = questao;
	}

	public boolean isRespostasQuestao()
	{
		return respostasQuestao;
	}

	public void setRespostasQuestao(boolean respostasQuestao)
	{
		this.respostasQuestao = respostasQuestao;
	}

	public boolean isIdentificacaoQuestao()
	{
		return identificacaoQuestao;
	}

	public void setIdentificacaoQuestao(boolean identificacaoQuestao)
	{
		this.identificacaoQuestao = identificacaoQuestao;
	}

	@Override
	public String toString()
	{
		return "SetDownload [identificacaoExercicio=" + identificacaoExercicio + ", respostasExercicio=" + respostasExercicio + ", resolucaoExercicio=" + resolucaoExercicio + ", anotacao=" + anotacao + ", usuario="
		+ usuario + ", assuntosCurso=" + assuntosCurso + ", quantidadeNivel1=" + quantidadeNivel1 + ", quantidadeNivel2=" + quantidadeNivel2
		+ ", quantidadeNivel3=" + quantidadeNivel3 + "]";
	}

}
