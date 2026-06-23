package matematica.intermediario.funcoes.nivel1package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.intermediario.funcoes.DiagramaFuncao;
import matematica.intermediario.funcoes.ImagemDiagrama;

public class Exercicio4 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		boolean gerarInjetora = rand.nextBoolean();
		DiagramaFuncao d = gerarInjetora
				? DiagramaFuncao.gerarFuncaoInjetora(rand)
				: DiagramaFuncao.gerarFuncaoNaoInjetora(rand);

		BufferedImage img = ImagemDiagrama.criar(d, 1 + rand.nextInt(6));

		addParagrafo("A função representada no diagrama é injetora (injeção)?");
		addParagrafoImagem(img);
		gerarAlternativasBoolean(d.eInjetora);

		if(d.eInjetora)
		{
			addResolucao("Uma função é injetora quando elementos distintos do domínio têm "
				+ "imagens distintas no contradomínio.");
			addResolucao("Analisando o diagrama: cada elemento de \\(B\\) recebe no máximo uma seta.");
			addResolucao("Portanto, a função \\(\\textbf{é}\\) injetora.");
		}
		else
		{
			int e1   = d.dominio[d.idxColisao1];
			int e2   = d.dominio[d.idxColisao2];
			int imag = d.contradominio[d.mapeamento[d.idxColisao1][0]];
			addResolucao("Uma função é injetora quando elementos distintos do domínio têm "
				+ "imagens distintas no contradomínio.");
			addResolucao("No diagrama, \\(" + e1 + "\\) e \\(" + e2 + "\\) apontam ambos para "
				+ "\\(" + imag + "\\): dois elementos com a mesma imagem.");
			addResolucao("Portanto, a função \\(\\textbf{não é}\\) injetora.");
		}
	}
}
