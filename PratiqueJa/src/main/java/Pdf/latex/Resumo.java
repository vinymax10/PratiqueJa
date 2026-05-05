package Pdf.latex;

public class Resumo
{
	public String assuntos;
	public String execicios;
	public String questoes;
	public String listas;
	public String paginas;

	public Resumo()
	{
		super();
	}

	public Resumo(String assuntos, String execicios, String questoes, String listas, String paginas)
	{
		super();
		this.assuntos = assuntos;
		this.execicios = execicios;
		this.questoes = questoes;
		this.listas = listas;
		this.paginas = paginas;
	}

	public String getAssuntos()
	{
		return assuntos;
	}

	public void setAssuntos(String assuntos)
	{
		this.assuntos = assuntos;
	}

	public String getExecicios()
	{
		return execicios;
	}

	public void setExecicios(String execicios)
	{
		this.execicios = execicios;
	}

	public String getQuestoes()
	{
		return questoes;
	}

	public void setQuestoes(String questoes)
	{
		this.questoes = questoes;
	}

	public String getPaginas()
	{
		return paginas;
	}

	public void setPaginas(String paginas)
	{
		this.paginas = paginas;
	}

	public String getListas()
	{
		return listas;
	}

	public void setListas(String listas)
	{
		this.listas = listas;
	}

}
