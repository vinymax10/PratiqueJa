package filtro.usuario;

import java.io.Serializable;

import lombok.Data;

@Data
public class FiltroProduto implements Serializable
{
	private static final long serialVersionUID = 1L;

	private String nome;
	private Boolean ativo;

	public void limpar()
	{
		nome = null;
		ativo = null;
	}
}
