package filtro.usuario;

import java.io.Serializable;

import lombok.Data;
import modelo.usuario.PerfilUsuario;
import modelo.usuario.Turma;

@Data
public class FiltroUsuario implements Serializable
{
	private static final long serialVersionUID = 1L;

	private String nome;
	private String email;

	private Turma turma;

	private PerfilUsuario perfil;

	private Boolean criador;

	private Boolean recebeSpam;

	public void limpar()
	{
		nome = null;
		email = null;
		turma = null;
		perfil = null;
		criador = null;
		recebeSpam = null;
	}

}
