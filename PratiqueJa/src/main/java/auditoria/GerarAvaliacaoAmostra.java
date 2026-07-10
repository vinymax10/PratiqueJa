package auditoria;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import matematica.ExercicioFactory;
import modelo.avaliacao.FormatoAvaliacao;
import modelo.avaliacao.PedidoAvaliacao;
import modelo.avaliacao.TipoGabarito;
import modelo.exercicio.Exercicio;
import pdf.exercicio.LayoutLista;
import service.avaliacao.BlocoExercicio;
import service.avaliacao.GeradorAvaliacaoPdfService;

/**
 * Gera uma avaliação PDF de amostra a partir de dispatchers GeradorExercicio informados na linha
 * de comando, SEM precisar do banco/CDI (monta os {@link BlocoExercicio} na mão em vez de
 * consultar {@code ExercicioPadraoDAO}). Usado para inspecionar visualmente o resultado de
 * correções nos geradores (ver {@code formato_resolucao.md}).
 *
 * Execução: mvn -q compile exec:java -Dexec.mainClass=auditoria.GerarAvaliacaoAmostra
 *   -Dexec.args="<titulo> <saida.pdf> <FQCN1>:<qtd1> <FQCN2>:<qtd2> ..."
 *
 * Requer: tex-new/pratiqueja.sty no projeto e xelatex no PATH.
 */
public class GerarAvaliacaoAmostra
{
	public static void main(String[] args) throws Exception
	{
		if(args.length < 3)
		{
			System.err.println("Uso: -Dexec.args=\"<titulo> <saida.pdf> <FQCN1>:<qtd1> [<FQCN2>:<qtd2> ...]\"");
			return;
		}

		String titulo = args[0];
		Path saida = Path.of(args[1]);

		List<BlocoExercicio> blocos = new ArrayList<>();
		for(int i = 2; i < args.length; i++)
		{
			String[] partes = args[i].split(":");
			String classe = partes[0];
			int qtd = partes.length > 1 ? Integer.parseInt(partes[1]) : 6;

			List<Exercicio> exercicios = new ArrayList<>(qtd);
			for(int j = 0; j < qtd; j++)
				exercicios.add(ExercicioFactory.gerar(classe, j));

			blocos.add(new BlocoExercicio(FormatoAvaliacao.ALTERNATIVAS, LayoutLista.PADRAO, exercicios));
			System.err.println("Bloco: " + classe + " x" + qtd);
		}

		PedidoAvaliacao pedido = new PedidoAvaliacao();
		pedido.setTitulo(titulo);
		pedido.setDataAvaliacao(LocalDate.now());
		pedido.setTipoGabarito(TipoGabarito.COM_RESOLUCAO);

		Path styDir = Path.of("tex-new");
		Path workDir = Files.createTempDirectory("amostra-avaliacao");
		String xelatexExe = "xelatex";

		GeradorAvaliacaoPdfService servico = new GeradorAvaliacaoPdfService();
		byte[] pdf = servico.gerarAvaliacao(pedido, blocos, "AMOSTRA", styDir, xelatexExe, workDir, true, null);

		Files.write(saida, pdf);
		System.err.println("PDF gerado: " + saida.toAbsolutePath() + " (" + pdf.length + " bytes)");
	}
}
