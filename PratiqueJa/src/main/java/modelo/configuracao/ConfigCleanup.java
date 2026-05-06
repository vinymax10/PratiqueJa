package modelo.configuracao;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import modelo.Entidade;

@Entity
public class ConfigCleanup implements Serializable, Entidade
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	private int diasRemoverTesteRealizado;
	
	private int diasRemoverTesteNaoRealizado;
	
	private int diasRemoverExercicioRealizado;
	
	private int diasRemoverExercicioNaoRealizado;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getDiasRemoverTesteRealizado() {
		return diasRemoverTesteRealizado;
	}

	public void setDiasRemoverTesteRealizado(int diasRemoverTesteRealizado) {
		this.diasRemoverTesteRealizado = diasRemoverTesteRealizado;
	}

	public int getDiasRemoverTesteNaoRealizado() {
		return diasRemoverTesteNaoRealizado;
	}

	public void setDiasRemoverTesteNaoRealizado(int diasRemoverTesteNaoRealizado) {
		this.diasRemoverTesteNaoRealizado = diasRemoverTesteNaoRealizado;
	}

	public int getDiasRemoverExercicioRealizado() {
		return diasRemoverExercicioRealizado;
	}

	public void setDiasRemoverExercicioRealizado(int diasRemoverExercicioRealizado) {
		this.diasRemoverExercicioRealizado = diasRemoverExercicioRealizado;
	}

	public int getDiasRemoverExercicioNaoRealizado() {
		return diasRemoverExercicioNaoRealizado;
	}

	public void setDiasRemoverExercicioNaoRealizado(int diasRemoverExercicioNaoRealizado) {
		this.diasRemoverExercicioNaoRealizado = diasRemoverExercicioNaoRealizado;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConfigCleanup other = (ConfigCleanup) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "ConfigCleanup: " + (id != null ? "id=" + id + ", " : "") + "diasRemoverTesteRealizado="
				+ diasRemoverTesteRealizado + ", diasRemoverTesteNaoRealizado=" + diasRemoverTesteNaoRealizado
				+ ", diasRemoverExercicioRealizado=" + diasRemoverExercicioRealizado
				+ ", diasRemoverExercicioNaoRealizado=" + diasRemoverExercicioNaoRealizado;
	}
	
}