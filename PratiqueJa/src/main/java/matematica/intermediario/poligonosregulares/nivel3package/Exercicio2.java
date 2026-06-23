package matematica.intermediario.poligonosregulares.nivel3package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.intermediario.poligonosregulares.ResolucaoPoligonoRegular;
import matematica.intermediario.poligonosregulares.config.ConfigPoligonoRegular;

// Hexágono: dado a = m√3, calcular A usando A = P·a/2
// l = 2m, P = 12m, A = P·a/2 = 12m·m√3/2 = 6m²√3 → resposta x = 6m²
public class Exercicio2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int m = 1 + rand.nextInt(5); // m = 1..5
		int l = 2 * m;
		int x = 6 * m * m;           // A = 6m²√3

		ConfigPoligonoRegular config = new ConfigPoligonoRegular(6, true, "l", "" + m + "\\sqrt{3}");
		BufferedImage image = config.criarImagem();

		addParagrafo("O apótema de um hexágono regular mede \\(" + m + "\\sqrt{3}\\). Sabendo que a área é \\(x\\sqrt{3}\\), qual é o valor de \\(x\\)?");
		addParagrafoImagem(image);
		gerarAlternativas("" + x);
		addResolucao("Passo 1: encontrar \\(l\\) e \\(P\\)");
		addResolucao("\\(" + ResolucaoPoligonoRegular.formulaApotemaHexagono() + "\\)");
		addResolucao("\\(" + m + "\\sqrt{3} = \\dfrac{l\\sqrt{3}}{2} \\Rightarrow l = " + l + "\\)");
		addResolucao("\\(P = 6 \\times " + l + " = " + (6 * l) + "\\)");
		addResolucao("Passo 2: calcular \\(A\\)");
		addResolucao("\\(" + ResolucaoPoligonoRegular.formulaArea() + "\\)");
		addResolucao("\\(A = \\dfrac{" + (6 * l) + " \\times " + m + "\\sqrt{3}}{2} = \\mathbf{" + x + "}\\sqrt{3}\\)");
	}
}
