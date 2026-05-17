package modelo.seguranca;

import java.io.Serializable;

import org.javers.core.metamodel.annotation.DiffIgnore;

import lombok.Data;
import modelo.usuario.Usuario;

@Data
public class Permissao<T> implements Serializable
{
	@DiffIgnore
	protected boolean podeEditar;

	@DiffIgnore
	protected boolean podeRemover;

	@DiffIgnore
	protected boolean podeCarregar;

	@DiffIgnore
	protected boolean podeAdicionar;

	public void update(T entidade, Usuario usuario) {}

	protected void negarTudo()
	{
		this.podeEditar = false;
		this.podeRemover = false;
		this.podeCarregar = false;
		this.podeAdicionar = false;
	}
}
