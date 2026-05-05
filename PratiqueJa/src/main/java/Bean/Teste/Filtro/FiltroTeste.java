package Bean.Teste.Filtro;

import java.io.Serializable;
import java.time.LocalDate;

import Modelo.AssuntoCurso.AssuntoCurso;
import Modelo.Teste.TestePadrao;

public class FiltroTeste implements Serializable
{
	private static final long serialVersionUID = 1L;

	private TestePadrao testePadrao;
	
	private Boolean realizado;
	
	private LocalDate inicioRealizacao;
	private LocalDate terminoRealizacao;
	
	private Double nota;
	
	private String nome;

	private AssuntoCurso assuntoCurso;
	
	public void limpar()
	{
		nome="";
		testePadrao=null;
		realizado=null;
		inicioRealizacao=null;
		terminoRealizacao=null;
		nota=null;
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

	public TestePadrao getTestePadrao()
	{
		return testePadrao;
	}

	public void setTestePadrao(TestePadrao testePadrao)
	{
		this.testePadrao = testePadrao;
	}

	public Boolean getRealizado()
	{
		return realizado;
	}

	public void setRealizado(Boolean realizado)
	{
		this.realizado = realizado;
	}

	public LocalDate getInicioRealizacao()
	{
		return inicioRealizacao;
	}

	public void setInicioRealizacao(LocalDate inicioRealizacao)
	{
		this.inicioRealizacao = inicioRealizacao;
	}

	public LocalDate getTerminoRealizacao()
	{
		return terminoRealizacao;
	}

	public void setTerminoRealizacao(LocalDate terminoRealizacao)
	{
		this.terminoRealizacao = terminoRealizacao;
	}

	public Double getNota()
	{
		return nota;
	}

	public void setNota(Double nota)
	{
		this.nota = nota;
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
		return (testePadrao != null ? "testePadrao=" + testePadrao + ", " : "")
		+ (realizado != null ? "realizado=" + realizado + ", " : "")
		+ (inicioRealizacao != null ? "inicioRealizacao=" + inicioRealizacao + ", " : "")
		+ (terminoRealizacao != null ? "terminoRealizacao=" + terminoRealizacao + ", " : "")
		+ (nota != null ? "nota=" + nota + ", " : "") + (nome != null ? "nome=" + nome + ", " : "")
		+ (assuntoCurso != null ? "assuntoCurso=" + assuntoCurso : "");
	}

}
