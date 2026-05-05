package Modelo.Usuario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Size;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import Modelo.Entidade;
import Modelo.AssuntoCurso.AssuntoCurso;

@Entity
public class Turma implements Serializable, Entidade
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(length = 255)
	@Size(max = 255)
	private String nome;
	
	@OneToMany(mappedBy = "turma")
	@NotFound(action = NotFoundAction.IGNORE)
	private List<Usuario> alunos = new ArrayList<Usuario>();
	
	@ManyToOne(optional = true)
	@JoinColumn(nullable = true)
	private AssuntoCurso assuntoAtual;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Usuario> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Usuario> alunos) {
		this.alunos = alunos;
	}

	public AssuntoCurso getAssuntoAtual() {
		return assuntoAtual;
	}

	public void setAssuntoAtual(AssuntoCurso assuntoAtual) {
		this.assuntoAtual = assuntoAtual;
	}

	public Long getId() {
		return id;
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(id);
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
		Turma other = (Turma) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Turma: " + (id != null ? "id=" + id + ", " : "") + (nome != null ? "nome=" + nome + ", " : "")
				+ (alunos != null ? "usuarios=" + alunos + ", " : "")
				+ (assuntoAtual != null ? "assuntoAtual=" + assuntoAtual : "");
	}

}
