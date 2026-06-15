package filtro.pdf;

import java.io.Serializable;

import lombok.Data;
import modelo.academico.Assunto;
import modelo.pdf.VisibilidadePdf;

@Data
public class FiltroPdf implements Serializable
{
	private static final long serialVersionUID = 1L;

	private Assunto assunto;

	private VisibilidadePdf visibilidade;

	private String descricao;

	public void limpar()
	{
		assunto      = null;
		visibilidade = null;
		descricao    = null;
	}
}
