package auditoria;

import java.util.List;

import matematica.ExercicioFactory;
import modelo.exercicio.AlternativaExercicio;
import modelo.exercicio.Exercicio;
import modelo.exercicio.ParagrafoExercicio;

/**
 * Imprime o enunciado + resolução real de N execuções de um gerador, para leitura humana
 * durante a revisão de elaboração das resoluções ({@code formato_resolucao.md}).
 *
 * Execução: mvn -q compile exec:java -Dexec.mainClass=auditoria.AmostraGerador
 *   -Dexec.args="matematica.intermediario.sistemaequacoes.SistemaEquacoesNivel1 5"
 */
public class AmostraGerador
{
	public static void main(String[] args) throws Exception
	{
		if(args.length < 1)
		{
			System.err.println("Uso: -Dexec.args=\"<FQCN do dispatcher> [N execuções, default 5]\"");
			return;
		}

		String classe = args[0];
		int n = args.length > 1 ? Integer.parseInt(args[1]) : 5;

		for(int i = 0; i < n; i++)
		{
			System.out.println("=== " + classe + " #" + i + " ===");
			try
			{
				Exercicio ex = ExercicioFactory.gerar(classe, i);

				System.out.println("-- enunciado --");
				for(ParagrafoExercicio p : ex.getParagrafos())
					System.out.println(p.isTipoImagem() ? "[imagem]" : p.getTexto());

				System.out.println("-- alternativas --");
				for(AlternativaExercicio a : ex.getAlternativas())
					System.out.println((a.isCorreta() ? "* " : "  ") + a.getTexto());

				System.out.println("-- resolução --");
				List<String> linhas = ex.getResolucaoLinhas();
				for(int j = 0; j < linhas.size(); j++)
					System.out.println((j + 1) + ". " + linhas.get(j));
			}
			catch(Exception | LinkageError e)
			{
				System.out.println("ERRO: " + e);
			}
			System.out.println();
		}
	}
}
