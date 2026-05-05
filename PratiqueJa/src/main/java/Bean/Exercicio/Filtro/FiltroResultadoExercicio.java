package Bean.Exercicio.Filtro;

import java.io.Serializable;
import java.time.LocalDate;

import Modelo.AssuntoCurso.AssuntoCurso;
import Modelo.AssuntoCurso.Enum.Modulo;
import Modelo.Exercicio.Enum.Nivel;
import Modelo.Exercicio.Enum.TipoExercicio;
import Modelo.Usuario.Usuario;

public class FiltroResultadoExercicio implements Serializable
{
	private static final long serialVersionUID = 1L;

	private String nomeUsuario;

	private LocalDate inicioRealizacao;
	private LocalDate terminoRealizacao;

	private AssuntoCurso assuntoCurso;

	private Modulo modulo;

	private Nivel nivel;

	private String nomeExercicio;

	private String enunciado;

	private String descricao;

	private TipoExercicio tipoExercicio;
	
	private Usuario usuario;

	public void limpar()
	{
		nomeUsuario="";
		inicioRealizacao=null;
		terminoRealizacao=null;
		assuntoCurso=null;
		modulo=null;
		nivel=null;
		nomeExercicio="";
		enunciado="";
		descricao="";
		tipoExercicio=null;		
		usuario=null;
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

	public Nivel getNivel()
	{
		return nivel;
	}

	public void setNivel(Nivel nivel)
	{
		this.nivel = nivel;
	}

	public String getNomeExercicio()
	{
		return nomeExercicio;
	}

	public void setNomeExercicio(String nomeExercicio)
	{
		this.nomeExercicio = nomeExercicio;
	}

	public String getEnunciado()
	{
		return enunciado;
	}

	public void setEnunciado(String enunciado)
	{
		this.enunciado = enunciado;
	}

	public String getDescricao()
	{
		return descricao;
	}

	public void setDescricao(String descricao)
	{
		this.descricao = descricao;
	}

	public TipoExercicio getTipoExercicio()
	{
		return tipoExercicio;
	}

	public void setTipoExercicio(TipoExercicio tipoExercicio)
	{
		this.tipoExercicio = tipoExercicio;
	}

	public Modulo getModulo()
	{
		return modulo;
	}

	public void setModulo(Modulo modulo)
	{
		this.modulo = modulo;
	}

	public Usuario getUsuario()
	{
		return usuario;
	}

	public void setUsuario(Usuario usuario)
	{
		this.usuario = usuario;
	}

	@Override
	public String toString()
	{
		return (nomeUsuario != null ? "nomeUsuario=" + nomeUsuario + ", " : "")
		+ (inicioRealizacao != null ? "inicioRealizacao=" + inicioRealizacao + ", " : "")
		+ (terminoRealizacao != null ? "terminoRealizacao=" + terminoRealizacao + ", " : "")
		+ (assuntoCurso != null ? "assuntoCurso=" + assuntoCurso + ", " : "")
		+ (modulo != null ? "modulo=" + modulo + ", " : "") + (nivel != null ? "nivel=" + nivel + ", " : "")
		+ (nomeExercicio != null ? "nomeExercicio=" + nomeExercicio + ", " : "")
		+ (enunciado != null ? "enunciado=" + enunciado + ", " : "")
		+ (descricao != null ? "descricao=" + descricao + ", " : "")
		+ (tipoExercicio != null ? "tipoExercicio=" + tipoExercicio + ", " : "")
		+ (usuario != null ? "usuario=" + usuario : "");
	}

}
