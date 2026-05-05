package Modelo.Questao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Size;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import Modelo.Entidade;
import Modelo.AssuntoCurso.AssuntoCurso;
import Modelo.Questao.Configuracao.Ano;
import Modelo.Questao.Configuracao.Assunto;
import Modelo.Questao.Configuracao.Banca;
import Modelo.Questao.Configuracao.Disciplina;
import Modelo.Questao.Configuracao.Orgao;
import Modelo.Questao.Enum.Dificuldade;

@Entity
public class Questao implements Serializable, Entidade
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne(optional = true)
	@JoinColumn(nullable = true)
	private Ano ano;

	@ManyToOne(optional = true)
	@JoinColumn(nullable = true)
	private Banca banca;

	@ManyToOne(optional = true)
	@JoinColumn(nullable = true)
	private Orgao orgao;

	@ManyToMany
	@JoinTable(name = "questaoHasAssunto")
	private List<Assunto> assuntos = new ArrayList<Assunto>();

	@ManyToOne(optional = true)
	@JoinColumn(nullable = true)
	private Disciplina disciplina;

	@ManyToOne
	private AssuntoCurso assuntoCurso;

	private Dificuldade dificuldade;

	private int ordemInsercao;

	@Column(length = 255)
	@Size(max = 255)
	private String chave;
	
	@Column(length = 2047)
	@Size(max = 2047)
	private String resolucao;

	@Column(length = 255)
	@Size(max = 255)
	private String prova;

	@OneToMany(orphanRemoval = true, mappedBy = "questao")
	@OrderBy("ordem")
	@NotFound(action = NotFoundAction.IGNORE)
	private List<Paragrafo> paragrafos = new ArrayList<Paragrafo>();

	@OneToMany(orphanRemoval = true, mappedBy = "questao")
	@OrderBy("ordem")
	@NotFound(action = NotFoundAction.IGNORE)
	private List<Alternativa> alternativas = new ArrayList<Alternativa>();

	@OneToMany(orphanRemoval = true, mappedBy = "questao")
	private List<ResultadoQuestao> resultadosQuestao = new ArrayList<ResultadoQuestao>();

	private boolean revisada;

	@Transient
	Alternativa alternativaEscolhida;

	@Transient
	private boolean showEstatistica;

	@Transient
	private boolean showResolucaoComentada;
	
	@Transient
	private boolean jaMostrouResolucaoComentada;
	
	@Transient
	protected boolean jaFezQuestao = false;

	public void toogleResolucaoComentada()
	{
		if(showEstatistica)
			showEstatistica = !showEstatistica;

		showResolucaoComentada = !showResolucaoComentada;
	}

	public void toogleEstatistica()
	{
		if(showResolucaoComentada)
			showResolucaoComentada = !showResolucaoComentada;

		showEstatistica = !showEstatistica;
	}

	public Long getId()
	{
		return id;
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

	public List<Alternativa> getAlternativas()
	{
		return alternativas;
	}

	public void setAlternativas(List<Alternativa> alternativas)
	{
		this.alternativas = alternativas;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public List<Assunto> getAssuntos()
	{
		return assuntos;
	}

	public void setAssuntos(List<Assunto> assuntos)
	{
		this.assuntos = assuntos;
	}

	public Dificuldade getDificuldade()
	{
		return dificuldade;
	}

	public void setDificuldade(Dificuldade dificuldade)
	{
		this.dificuldade = dificuldade;
	}

	public Disciplina getDisciplina()
	{
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina)
	{
		this.disciplina = disciplina;
	}

	public List<Paragrafo> getParagrafos()
	{
		return paragrafos;
	}

	public void setParagrafos(List<Paragrafo> paragrafos)
	{
		this.paragrafos = paragrafos;
	}

	public boolean isShowEstatistica()
	{
		return showEstatistica;
	}

	public Alternativa correta()
	{
		for (Alternativa alternativa : alternativas) 
		{
			if(alternativa.isCorreta())
				return alternativa;
		}
		return null;
	}

	public Alternativa getAlternativaEscolhida()
	{
		return alternativaEscolhida;
	}

	public boolean isShowResolucaoComentada()
	{
		return showResolucaoComentada;
	}

	public void setShowResolucaoComentada(boolean showResolucaoComentada)
	{
		this.showResolucaoComentada = showResolucaoComentada;
	}

	public void setAlternativaEscolhida(Alternativa alternativaEscolhida)
	{
		this.alternativaEscolhida = alternativaEscolhida;
	}

	public void setShowEstatistica(boolean showEstatistica)
	{
		this.showEstatistica = showEstatistica;
	}

	public List<ResultadoQuestao> getResultadosQuestao()
	{
		return resultadosQuestao;
	}

	public void setResultadosQuestao(List<ResultadoQuestao> resultadosQuestao)
	{
		this.resultadosQuestao = resultadosQuestao;
	}

	public boolean isRevisada()
	{
		return revisada;
	}

	public void setRevisada(boolean revisada)
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

	public String getProva()
	{
		return prova;
	}

	public void setProva(String prova)
	{
		this.prova = prova;
	}

	public AssuntoCurso getAssuntoCurso()
	{
		return assuntoCurso;
	}
	
	public String getResolucao()
	{
		return resolucao;
	}

	public void setResolucao(String resolucao)
	{
		this.resolucao = resolucao;
	}

	public void setAssuntoCurso(AssuntoCurso assuntoCurso)
	{
		this.assuntoCurso = assuntoCurso;
	}
	
	public boolean isJaMostrouResolucaoComentada()
	{
		return jaMostrouResolucaoComentada;
	}

	public void setJaMostrouResolucaoComentada(boolean jaMostrouResolucaoComentada)
	{
		this.jaMostrouResolucaoComentada = jaMostrouResolucaoComentada;
	}

	public boolean isJaFezQuestao()
	{
		return jaFezQuestao;
	}

	public void setJaFezQuestao(boolean jaFezQuestao)
	{
		this.jaFezQuestao = jaFezQuestao;
	}

	@Override
	public String toString()
	{
		return "Questao [id=" + id + ", \nano=" + ano + ", \nbanca=" + banca + ", \norgao=" + orgao + ", chave=" + chave + ", prova=" + prova
		+ ", \ndisciplina=" + disciplina + ", \ndificuldade=" + dificuldade + " revisada=" + revisada + "]";
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
		Questao other = (Questao) obj;
		if(id == null)
		{
			if(other.id != null)
				return false;
		}
		else if(!id.equals(other.id))
			return false;
		return true;
	}

}