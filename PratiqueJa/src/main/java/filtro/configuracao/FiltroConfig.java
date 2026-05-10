package filtro.configuracao;

import java.io.Serializable;

import lombok.Data;

@Data
public class FiltroConfig implements Serializable
{
	private String nome;

	private Boolean ativo;

	public void limpar()
	{
		ativo=null;
		nome="";
	}

}
