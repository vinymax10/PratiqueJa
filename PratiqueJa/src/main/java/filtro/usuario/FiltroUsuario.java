package filtro.usuario;

import java.io.Serializable;

import modelo.usuario.PerfilUsuario;
import modelo.usuario.Turma;

public class FiltroUsuario implements Serializable
{
	private static final long serialVersionUID = 1L;

	private Long id;

	private String nome;
	private String email;
	
	private Turma turma;

	private PerfilUsuario perfil;

	private Boolean criador;
	
	private Boolean recebeSpam;
	
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public PerfilUsuario getPerfil()
	{
		return perfil;
	}

	public void setPerfil(PerfilUsuario perfil)
	{
		this.perfil = perfil;
	}

	public Boolean getCriador()
	{
		return criador;
	}

	public void setCriador(Boolean criador)
	{
		this.criador = criador;
	}

	public Boolean getRecebeSpam()
	{
		return recebeSpam;
	}

	public void setRecebeSpam(Boolean recebeSpam)
	{
		this.recebeSpam = recebeSpam;
	}

	@Override
	public String toString() {
		return "FiltroUsuario: " + (id != null ? "id=" + id + ", " : "") + (nome != null ? "nome=" + nome + ", " : "")
				+ (email != null ? "email=" + email + ", " : "") + (turma != null ? "turma=" + turma : "");
	}

}
