package filtro.academico;

import java.io.Serializable;

import lombok.Data;

@Data
public class FiltroOrgao implements Serializable
{
	private static final long serialVersionUID = 1L;

	private String nome = "";
	private String sigla = "";
	private Boolean ativo;

	public void limpar()
	{
		nome = "";
		sigla = "";
		ativo = null;
	}
}
