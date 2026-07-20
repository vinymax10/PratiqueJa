package service.usuario;

import java.io.Serializable;

import dao.avaliacao.PedidoAvaliacaoDAO;
import dao.exercicio.ResultadoExercicioDAO;
import dao.publicacao.PedidoPostDAO;
import dao.questao.ResultadoQuestaoDAO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import modelo.usuario.Usuario;

/**
 * Reúne as estatísticas de uso de um usuário (posts e avaliações gerados, exercícios e
 * questões resolvidos), extraídas do {@code UsuarioBean} para concentrar as consultas
 * de agregação num único ponto reutilizável.
 */
@ApplicationScoped
public class EstatisticaUsuarioService implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Inject
	private PedidoPostDAO pedidoPostDAO;

	@Inject
	private PedidoAvaliacaoDAO pedidoAvaliacaoDAO;

	@Inject
	private ResultadoExercicioDAO resultadoExercicioDAO;

	@Inject
	private ResultadoQuestaoDAO resultadoQuestaoDAO;

	/** Totais de uso do usuário. Devolve tudo zerado se {@code usuario} for nulo. */
	public Estatisticas calcular(Usuario usuario)
	{
		if(usuario == null || usuario.getId() == null)
			return new Estatisticas(0, 0, 0, 0);

		return new Estatisticas(
			pedidoPostDAO.somarPostsTotal(usuario),
			pedidoAvaliacaoDAO.somarAvaliacoesTotal(usuario),
			(int) resultadoExercicioDAO.contarTotal(usuario),
			(int) resultadoQuestaoDAO.contarTotal(usuario));
	}

	/** Posts e avaliações gerados (soma das quantidades, exceto rascunhos) e itens resolvidos. */
	public record Estatisticas(int posts, int avaliacoes, int exerciciosResolvidos, int questoesResolvidas) {}
}
