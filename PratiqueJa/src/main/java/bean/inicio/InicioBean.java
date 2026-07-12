package bean.inicio;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dao.exercicio.ResultadoExercicioDAO;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import modelo.academico.Assunto;
import modelo.exercicio.ResultadoExercicio;
import modelo.usuario.Usuario;
import service.avaliacao.CreditoAvaliacaoService;
import service.publicacao.CreditoPostService;
import util.StringAux;
import web.session.Sessao;

/**
 * Apoio à tela inicial (/inicio.xhtml): resumo de progresso do usuário logado.
 * Em visitante deslogado todos os valores ficam zerados e a tela mostra a versão pública.
 */
@Getter
@Named
@ViewScoped
public class InicioBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Inject
	private ResultadoExercicioDAO resultadoExercicioDAO;

	@Inject
	private CreditoPostService creditoPostService;

	@Inject
	private CreditoAvaliacaoService creditoAvaliacaoService;

	private Usuario usuario;

	/** Total de exercícios já realizados pelo usuário. */
	private int totalResolvidos;

	/** Dias consecutivos praticando, terminando hoje ou ontem (0 = sequência quebrada). */
	private int streakDias;

	/** Data da última prática (null se nunca praticou). */
	private LocalDate ultimaPratica;

	/** Assunto do exercício mais recente, para retomar de onde parou (null se nunca praticou). */
	private Assunto ultimoAssunto;

	@PostConstruct
	public void init()
	{
		usuario = Sessao.getUsuarioLogado();
		if(usuario == null)
			return;

		List<ResultadoExercicio> resultados = resultadoExercicioDAO.listar(usuario);
		if(resultados == null || resultados.isEmpty())
			return;

		totalResolvidos = resultados.size();
		ultimaPratica = resultados.get(0).getRealizacao(); // listar() ordena por realizacao DESC
		streakDias = calcularStreak(resultados);

		if(resultados.get(0).getExercicio() != null)
			ultimoAssunto = resultados.get(0).getExercicio().getAssunto();
	}

	/** true quando há algum progresso para exibir (evita renderizar a faixa vazia). */
	public boolean isTemProgresso()
	{
		return totalResolvidos > 0;
	}

	public String statusValidade(LocalDate validade)
	{
		return StringAux.statusValidade(validade);
	}

	public int getCreditosRestantesPost()
	{
		if(usuario == null || usuario.getPerfilCriador() == null)
			return 0;
		return creditoPostService.creditosRestantes(usuario, usuario.getPerfilCriador());
	}

	public int getCreditosRestantesAvaliacao()
	{
		if(usuario == null)
			return 0;
		return creditoAvaliacaoService.creditosRestantes(usuario);
	}

	private int calcularStreak(List<ResultadoExercicio> resultados)
	{
		Set<LocalDate> dias = new HashSet<>();
		for(ResultadoExercicio resultado : resultados)
			if(resultado.getRealizacao() != null)
				dias.add(resultado.getRealizacao());

		if(dias.isEmpty())
			return 0;

		LocalDate hoje = LocalDate.now();
		// se a última prática não foi hoje nem ontem, a sequência está quebrada
		if(!dias.contains(hoje) && !dias.contains(hoje.minusDays(1)))
			return 0;

		LocalDate dia = dias.contains(hoje) ? hoje : hoje.minusDays(1);
		int streak = 0;
		while(dias.contains(dia))
		{
			streak++;
			dia = dia.minusDays(1);
		}
		return streak;
	}
}
