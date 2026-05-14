package filtro.assuntocurso;

import java.io.Serializable;

import lombok.Data;
import modelo.academico.Modulo;

@Data
public class FiltroAssuntoCurso implements Serializable
{
	private static final long serialVersionUID = 1L;

	private String nomeAssuntoCurso;

	private String chave;

	private Modulo modulo;

	private String assunto;

	private Boolean habilidado;

	private Boolean showAula;

	private Boolean showAnotacao;

	private Boolean showExercicio;

	private Boolean showQuestao;

	public void limpar()
	{
		nomeAssuntoCurso = "";
		chave = "";
		modulo = null;
		assunto = "";
		showAula = null;
		showAnotacao = null;
		showExercicio = null;
		showQuestao = null;
		habilidado = null;
	}

}
