package bean.questao;

import java.io.Serializable;

import modelo.assuntocurso.AssuntoCurso;
import modelo.questao.Dificuldade;
import modelo.questao.configuracao.Ano;
import modelo.questao.configuracao.Assunto;
import modelo.questao.configuracao.Banca;
import modelo.questao.configuracao.Disciplina;
import modelo.questao.configuracao.Orgao;

public class FiltroQuestao implements Serializable
{
	private static final long serialVersionUID = 1L;

	private String conteudo;
	private Ano ano;
	private Banca banca;
	private Orgao orgao;
	private Assunto assunto;
	private Disciplina disciplina;
	private Dificuldade dificuldade;
	private Boolean revisada;
	private int ordemInsercao;
	private String chave;
	private long id;
	private Boolean resolucaoLatex;
	private AssuntoCurso assuntoCurso;
	private Boolean acertei;
	private Boolean respondida;

	public void limpar()
	{
		ano = null;
		banca = null;
		orgao = null;
		assunto = null;
		disciplina = null;
		dificuldade = null;
		revisada = null;
		ordemInsercao = 0;
		chave = null;
		id = 0;
		assuntoCurso = null;
		acertei = null;
		respondida = null;
		conteudo = "";
	}

	public Ano getAno()
	{
		return ano;
	}

	public void setAno(Ano ano)
	{
		this.ano = ano;
	}

	public Banca getBanca()
	{
		return banca;
	}

	public void setBanca(Banca banca)
	{
		this.banca = banca;
	}

	public Orgao getOrgao()
	{
		return orgao;
	}

	public void setOrgao(Orgao orgao)
	{
		this.orgao = orgao;
	}

	public Assunto getAssunto()
	{
		return assunto;
	}

	public void setAssunto(Assunto assunto)
	{
		this.assunto = assunto;
	}

	public Disciplina getDisciplina()
	{
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina)
	{
		this.disciplina = disciplina;
	}

	public Dificuldade getDificuldade()
	{
		return dificuldade;
	}

	public void setDificuldade(Dificuldade dificuldade)
	{
		this.dificuldade = dificuldade;
	}

	public Boolean getRevisada()
	{
		return revisada;
	}

	public void setRevisada(Boolean revisada)
	{
		this.revisada = revisada;
	}

	public int getOrdemInsercao()
	{
		return ordemInsercao;
	}

	public void setOrdemInsercao(int ordemInsercao)
	{
		this.ordemInsercao = ordemInsercao;
	}

	public String getChave()
	{
		return chave;
	}

	public void setChave(String chave)
	{
		this.chave = chave;
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public AssuntoCurso getAssuntoCurso()
	{
		return assuntoCurso;
	}

	public void setAssuntoCurso(AssuntoCurso assuntoCurso)
	{
		this.assuntoCurso = assuntoCurso;
	}

	public Boolean getAcertei()
	{
		return acertei;
	}

	public void setAcertei(Boolean acertei)
	{
		this.acertei = acertei;
	}

	public Boolean getRespondida()
	{
		return respondida;
	}

	public void setRespondida(Boolean respondida)
	{
		this.respondida = respondida;
	}

	public Boolean getResolucaoLatex()
	{
		return resolucaoLatex;
	}

	public void setResolucaoLatex(Boolean resolucaoLatex)
	{
		this.resolucaoLatex = resolucaoLatex;
	}

	public String getConteudo()
	{
		return conteudo;
	}

	public void setConteudo(String conteudo)
	{
		this.conteudo = conteudo;
	}

	@Override
	public String toString()
	{
		return (ano != null ? "ano=" + ano + ", " : "") + (banca != null ? "banca=" + banca + ", " : "")
		+ (orgao != null ? "orgao=" + orgao + ", " : "") + (assunto != null ? "assunto=" + assunto + ", " : "")
		+ (disciplina != null ? "disciplina=" + disciplina + ", " : "")
		+ (dificuldade != null ? "dificuldade=" + dificuldade + ", " : "")
		+ (revisada != null ? "revisada=" + revisada + ", " : "") + "ordemInsercao=" + ordemInsercao + ", "
		+ (chave != null ? "chave=" + chave + ", " : "") + "id=" + id + ", "
		+ (resolucaoLatex != null ? "resolucaoLatex=" + resolucaoLatex + ", " : "")
		+ (assuntoCurso != null ? "assuntoCurso=" + assuntoCurso + ", " : "")
		+ (acertei != null ? "acertei=" + acertei + ", " : "") + (respondida != null ? "respondida=" + respondida : "");
	}

}
