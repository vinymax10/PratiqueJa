package filtro.academico;

import java.io.Serializable;

import lombok.Data;
import modelo.academico.Modulo;

@Data
public class FiltroAssunto implements Serializable
{
	private static final long serialVersionUID = 1L;

	private String nomeAssunto;

	private String chave;

	private Modulo modulo;

	private String assunto;

	private Boolean mostrarTesteConteudo;

	public void limpar()
	{
		nomeAssunto = "";
		chave = "";
		modulo = null;
		assunto = "";
		mostrarTesteConteudo = null;
	}
}
