package bean.download;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import modelo.academico.AssuntoCurso;
import modelo.usuario.Usuario;

@Data
public class SetDownload implements Serializable
{
	private static final long serialVersionUID = 1L;

	private String nomeArquivo;

	private boolean identificacaoExercicio = false;

	private boolean identificacaoQuestao = false;

	private boolean respostasExercicio = true;

	private boolean respostasQuestao = true;

	private boolean resolucaoExercicio = true;

	private boolean resolucaoQuestao = true;

	private boolean anotacao = true;

	private boolean questao = false;

	private Usuario usuario;

	private List<AssuntoCurso> assuntosCurso;

	private int quantidadeNivel1=1;

	private int quantidadeNivel2;

	private int quantidadeNivel3;

}
