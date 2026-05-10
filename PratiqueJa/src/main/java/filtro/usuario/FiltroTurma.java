package filtro.usuario;

import java.io.Serializable;

import modelo.assuntocurso.AssuntoCurso;

public class FiltroTurma implements Serializable
{
	private static final long serialVersionUID = 1L;

	private Long id;

	private String nome;
	
	private AssuntoCurso assuntoAtual;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public AssuntoCurso getAssuntoAtual() {
		return assuntoAtual;
	}

	public void setAssuntoAtual(AssuntoCurso assuntoAtual) {
		this.assuntoAtual = assuntoAtual;
	}

	@Override
	public String toString() {
		return "FiltroTurma: " + (id != null ? "id=" + id + ", " : "") + (nome != null ? "nome=" + nome + ", " : "")
				+ (assuntoAtual != null ? "assuntoAtual=" + assuntoAtual : "");
	}

}
