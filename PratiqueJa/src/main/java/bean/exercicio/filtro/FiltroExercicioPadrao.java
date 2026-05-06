package bean.exercicio.filtro;

import java.io.Serializable;

import modelo.assuntocurso.AssuntoCurso;
import modelo.assuntocurso.Modulo;
import modelo.exercicio.Nivel;
import modelo.exercicio.TipoExercicio;

public class FiltroExercicioPadrao implements Serializable
{
	private static final long serialVersionUID = 1L;

	private AssuntoCurso assuntoCurso;

	private Modulo modulo;

	private Nivel nivel;

	private String nome;

	private String enunciado;

	private String descricao;

	private TipoExercicio tipoExercicio;
	
	private int quantidade;
	
	private Boolean mostrarResolucao;

	public AssuntoCurso getAssuntoCurso()
	{
		return assuntoCurso;
	}

	public void setAssuntoCurso(AssuntoCurso assuntoCurso)
	{
		this.assuntoCurso = assuntoCurso;
	}

	public Modulo getModulo()
	{
		return modulo;
	}

	public void setModulo(Modulo modulo)
	{
		this.modulo = modulo;
	}

	public Nivel getNivel()
	{
		return nivel;
	}

	public void setNivel(Nivel nivel)
	{
		this.nivel = nivel;
	}

	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
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

	public int getQuantidade()
	{
		return quantidade;
	}

	public void setQuantidade(int quantidade)
	{
		this.quantidade = quantidade;
	}

	public Boolean getMostrarResolucao()
	{
		return mostrarResolucao;
	}

	public void setMostrarResolucao(Boolean mostrarResolucao)
	{
		this.mostrarResolucao = mostrarResolucao;
	}

	

}
