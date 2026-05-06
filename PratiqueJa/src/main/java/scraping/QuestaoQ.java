package scraping;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class QuestaoQ implements Serializable
{
	private static final long serialVersionUID = 1L;

	private String id;

	private String ano;

	private String banca;

	private String orgao;

	private List<String> assuntos = new ArrayList<String>();

	private List<Paragrafo> paragrafos = new ArrayList<Paragrafo>();

	private List<Alternativa> alternativas = new ArrayList<Alternativa>();

	private String disciplina;

	private String prova;

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getAno()
	{
		return ano;
	}

	public void setAno(String ano)
	{
		this.ano = ano;
	}

	public String getBanca()
	{
		return banca;
	}

	public void setBanca(String banca)
	{
		this.banca = banca;
	}

	public String getOrgao()
	{
		return orgao;
	}

	public void setOrgao(String orgao)
	{
		this.orgao = orgao;
	}

	public List<String> getAssuntos()
	{
		return assuntos;
	}

	public void setAssuntos(List<String> assuntos)
	{
		this.assuntos = assuntos;
	}

	public String getDisciplina()
	{
		return disciplina;
	}

	public void setDisciplina(String disciplina)
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

	public List<Alternativa> getAlternativas()
	{
		return alternativas;
	}

	public void setAlternativas(List<Alternativa> alternativas)
	{
		this.alternativas = alternativas;
	}

	public static long getSerialversionuid()
	{
		return serialVersionUID;
	}

	public String getProva()
	{
		return prova;
	}

	public void setProva(String prova)
	{
		this.prova = prova;
	}

	@Override
	public String toString()
	{
		return "Questao [id=" + id + ", ano=" + ano + ", banca=" + banca + ", orgao=" + orgao + ", prova=" + prova + "\n assuntos=" + assuntos
		+ "\n paragrafos=" + paragrafos + "\ndisciplina=" + disciplina + "\n alternativas=" + alternativas + "";
	}

}