package filtro.questao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import modelo.academico.Assunto;
import modelo.questao.Dificuldade;
import modelo.academico.Ano;
import modelo.academico.Banca;
import modelo.academico.Disciplina;
import modelo.academico.Orgao;
import lombok.Data;

@Data
public class FiltroQuestao implements Serializable
{
	private static final long serialVersionUID = 1L;

	private String conteudo;
	private Ano ano;
	private Banca banca;
	private Orgao orgao;

	// Seleção múltipla (vista de estudante)
	private List<Ano> anos = new ArrayList<>();
	private List<Banca> bancas = new ArrayList<>();
	private List<Orgao> orgaos = new ArrayList<>();
	private List<Assunto> assuntos = new ArrayList<>();
	private List<Dificuldade> dificuldades = new ArrayList<>();
	private Disciplina disciplina;
	private Dificuldade dificuldade;
	private Boolean revisada;
	private int ordemInsercao;
	private String chave;
	private Long id;
	private String ids;
	private Boolean resolucaoLatex;
	private Assunto assunto;
	private Boolean acertei;
	private Boolean respondida;
	private Boolean malFormulada;

	public void limpar()
	{
		ano = null;
		banca = null;
		orgao = null;
		disciplina = null;
		dificuldade = null;
		revisada = null;
		ordemInsercao = 0;
		chave = null;
		id = null;
		ids = null;
		resolucaoLatex = null;
		malFormulada = null;
		assunto = null;
		acertei = null;
		respondida = null;
		conteudo = "";
		anos = new ArrayList<>();
		bancas = new ArrayList<>();
		orgaos = new ArrayList<>();
		assuntos = new ArrayList<>();
		dificuldades = new ArrayList<>();
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

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Assunto getAssunto()
	{
		return assunto;
	}

	public void setAssunto(Assunto assunto)
	{
		this.assunto = assunto;
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
		+ (orgao != null ? "orgao=" + orgao + ", " : "")
		+ (disciplina != null ? "disciplina=" + disciplina + ", " : "")
		+ (dificuldade != null ? "dificuldade=" + dificuldade + ", " : "")
		+ (revisada != null ? "revisada=" + revisada + ", " : "") + "ordemInsercao=" + ordemInsercao + ", "
		+ (chave != null ? "chave=" + chave + ", " : "") + "id=" + id + ", "
		+ (resolucaoLatex != null ? "resolucaoLatex=" + resolucaoLatex + ", " : "")
		+ (assunto != null ? "assunto=" + assunto + ", " : "")
		+ (acertei != null ? "acertei=" + acertei + ", " : "") + (respondida != null ? "respondida=" + respondida : "");
	}

}
