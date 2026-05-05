package Bean.Teste.Filtro;

import java.io.Serializable;
import java.time.LocalDate;

import Modelo.AssuntoCurso.AssuntoCurso;
import Modelo.AssuntoCurso.Enum.Modulo;
import Modelo.Teste.TestePadrao;
import Modelo.Usuario.Usuario;

public class FiltroResultadoTeste implements Serializable
{
	private static final long serialVersionUID = 1L;

	private String nomeUsuario;

	private LocalDate inicioRealizacao;
	private LocalDate terminoRealizacao;

	private AssuntoCurso assuntoCurso;
	
	private Modulo modulo;

	private TestePadrao testePadrao;
	
	private Usuario usuario;

	public void limpar()
	{
		nomeUsuario="";
		inicioRealizacao=null;
		terminoRealizacao=null;
		assuntoCurso=null;
		testePadrao=null;		
		usuario=null;
		modulo=null;
	}
	
	public String getNomeUsuario()
	{
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario)
	{
		this.nomeUsuario = nomeUsuario;
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

	public AssuntoCurso getAssuntoCurso()
	{
		return assuntoCurso;
	}

	public void setAssuntoCurso(AssuntoCurso assuntoCurso)
	{
		this.assuntoCurso = assuntoCurso;
	}

	public Usuario getUsuario()
	{
		return usuario;
	}

	public void setUsuario(Usuario usuario)
	{
		this.usuario = usuario;
	}

	public TestePadrao getTestePadrao()
	{
		return testePadrao;
	}

	public void setTestePadrao(TestePadrao testePadrao)
	{
		this.testePadrao = testePadrao;
	}

	public Modulo getModulo()
	{
		return modulo;
	}

	public void setModulo(Modulo modulo)
	{
		this.modulo = modulo;
	}

	@Override
	public String toString()
	{
		return (nomeUsuario != null ? "nomeUsuario=" + nomeUsuario + ", " : "")
		+ (inicioRealizacao != null ? "inicioRealizacao=" + inicioRealizacao + ", " : "")
		+ (terminoRealizacao != null ? "terminoRealizacao=" + terminoRealizacao + ", " : "")
		+ (assuntoCurso != null ? "assuntoCurso=" + assuntoCurso + ", " : "")
		+ (modulo != null ? "modulo=" + modulo + ", " : "")
		+ (testePadrao != null ? "testePadrao=" + testePadrao + ", " : "")
		+ (usuario != null ? "usuario=" + usuario : "");
	}

}
