package filtro.questao;

import java.io.Serializable;

import lombok.Data;
import modelo.academico.Disciplina;

@Data
public class FiltroAssunto implements Serializable
{
	private static final long serialVersionUID = 1L;

	private String nome = "";
	private Disciplina disciplina;
	private Boolean ativo;

	public void limpar()
	{
		nome = "";
		disciplina = null;
		ativo = null;
	}
}
