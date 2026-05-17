package bean.exercicio;

import java.io.Serializable;

import lombok.Data;
import modelo.usuario.Usuario;

@Data
public class ConfigDownload implements Serializable
{
	private static final long serialVersionUID = 1L;

	private boolean identificacao = true;
	private boolean respostas = true;
	private boolean resolucao = true;
	private Usuario usuario;
}
