package Bean.AssuntoCurso;

import java.io.Serializable;

import Modelo.AssuntoCurso.Enum.Modulo;

public class FiltroAssuntoCurso implements Serializable
{
	private static final long serialVersionUID = 1L;

	private String nomeAssuntoCurso;

	private String chave;

	private Modulo modulo;

	private String assunto;
	
	private Boolean habilidado;

	private Boolean showAula;

	private Boolean showAnotacao;

	private Boolean showExercicio;

	private Boolean showQuestao;

	private int indice;

	public void limpar()
	{
		nomeAssuntoCurso="";
		chave="";
		modulo=null;
		assunto="";
		showAula=null;
		showAnotacao=null;
		showExercicio=null;
		showQuestao=null;
		habilidado=null;
		indice=0;
	}
	
	public String getNomeAssuntoCurso()
	{
		return nomeAssuntoCurso;
	}

	public void setNomeAssuntoCurso(String nomeAssuntoCurso)
	{
		this.nomeAssuntoCurso = nomeAssuntoCurso;
	}

	public String getChave()
	{
		return chave;
	}

	public void setChave(String chave)
	{
		this.chave = chave;
	}

	public Modulo getModulo()
	{
		return modulo;
	}

	public void setModulo(Modulo modulo)
	{
		this.modulo = modulo;
	}

	public String getAssunto()
	{
		return assunto;
	}

	public void setAssunto(String assunto)
	{
		this.assunto = assunto;
	}

	public Boolean getShowAula()
	{
		return showAula;
	}

	public void setShowAula(Boolean showAula)
	{
		this.showAula = showAula;
	}

	public Boolean getShowAnotacao()
	{
		return showAnotacao;
	}

	public void setShowAnotacao(Boolean showAnotacao)
	{
		this.showAnotacao = showAnotacao;
	}

	public Boolean getShowExercicio()
	{
		return showExercicio;
	}

	public void setShowExercicio(Boolean showExercicio)
	{
		this.showExercicio = showExercicio;
	}

	public Boolean getShowQuestao()
	{
		return showQuestao;
	}

	public void setShowQuestao(Boolean showQuestao)
	{
		this.showQuestao = showQuestao;
	}

	public int getIndice()
	{
		return indice;
	}

	public void setIndice(int indice)
	{
		this.indice = indice;
	}

	public Boolean getHabilidado()
	{
		return habilidado;
	}

	public void setHabilidado(Boolean habilidado)
	{
		this.habilidado = habilidado;
	}

	@Override
	public String toString()
	{
		return (nomeAssuntoCurso != null ? "nomeAssuntoCurso=" + nomeAssuntoCurso + ", " : "")
		+ (chave != null ? "chave=" + chave + ", " : "") + (modulo != null ? "modulo=" + modulo + ", " : "")
		+ (assunto != null ? "assunto=" + assunto + ", " : "") + (showAula != null ? "showAula=" + showAula + ", " : "")
		+ (showAnotacao != null ? "showAnotacao=" + showAnotacao + ", " : "")
		+ (showExercicio != null ? "showExercicio=" + showExercicio + ", " : "")
		+ (showQuestao != null ? "showQuestao=" + showQuestao + ", " : "") + "indice=" + indice;
	}


}
