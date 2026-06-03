package filtro.teste;

import java.io.Serializable;

import lombok.Data;
import modelo.academico.Assunto;

@Data
public class FiltroTestePadrao implements Serializable
{
	private static final long serialVersionUID = 1L;

	private String nome;

	private Double duracao;

	private Double notaMinima;

	private Assunto assunto;

	public void limpar()
	{
		nome = null;
		duracao = null;
		notaMinima = null;
		assunto = null;
	}
}
