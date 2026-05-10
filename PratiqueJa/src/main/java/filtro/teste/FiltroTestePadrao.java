package filtro.teste;

import java.io.Serializable;

import modelo.assuntocurso.AssuntoCurso;

public class FiltroTestePadrao implements Serializable
{
	private static final long serialVersionUID = 1L;

	private String nome;

	private Double duracao;

	private Double notaMinima;
	
	private AssuntoCurso assuntoCurso;

	public void limpar()
	{
		nome="";
		duracao=null;
		notaMinima=null;
		assuntoCurso=null;
	}
	
	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public Double getDuracao()
	{
		return duracao;
	}

	public void setDuracao(Double duracao)
	{
		this.duracao = duracao;
	}

	public Double getNotaMinima()
	{
		return notaMinima;
	}

	public void setNotaMinima(Double notaMinima)
	{
		this.notaMinima = notaMinima;
	}

	public AssuntoCurso getAssuntoCurso()
	{
		return assuntoCurso;
	}

	public void setAssuntoCurso(AssuntoCurso assuntoCurso)
	{
		this.assuntoCurso = assuntoCurso;
	}

	@Override
	public String toString()
	{
		return (nome != null ? "nome=" + nome + ", " : "") + (duracao != null ? "duracao=" + duracao + ", " : "")
		+ (notaMinima != null ? "notaMinima=" + notaMinima : "");
	}

}
