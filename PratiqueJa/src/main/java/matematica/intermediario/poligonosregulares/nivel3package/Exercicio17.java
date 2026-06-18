package matematica.intermediario.poligonosregulares.nivel3package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.intermediario.poligonosregulares.ResolucaoPoligonoRegular;
import matematica.intermediario.poligonosregulares.config.ConfigPoligonoRegular;

// Quadrado: dado l (par), calcular A = l² via P·a/2
// l = 2k → P = 4l, a = l/2 = k, A = P·a/2 = l²
public class Exercicio17 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int k = 1 + rand.nextInt(6);  // k = 1..6
		int l = 2 * k;
		int p = 4 * l;
		int aApotema = k;             // a = l/2
		int area = l * l;             // A = l²

		String res = "Passo 1: calcular \\(P\\) e o apótema \\(a\\) do quadrado";
		res += "\\(\\\\\\)";
		res += "\\(" + ResolucaoPoligonoRegular.formulaPerimetro() + "\\)";
		res += "\\(\\\\\\)";
		res += "\\(P = 4 \\times " + l + " = " + p + ", \\quad a = \\dfrac{" + l + "}{2} = " + aApotema + "\\)";
		res += "\\(\\\\\\)";
		res += "Passo 2: calcular \\(A\\)";
		res += "\\(\\\\\\)";
		res += "\\(" + ResolucaoPoligonoRegular.formulaArea() + "\\)";
		res += "\\(\\\\\\)";
		res += "\\(A = \\dfrac{" + p + " \\times " + aApotema + "}{2} = \\mathbf{" + area + "}\\)";

		ConfigPoligonoRegular config = new ConfigPoligonoRegular(4, true, "" + l, "" + aApotema);
		BufferedImage image = config.criarImagem();

		addParagrafo("Um quadrado tem lado \\(" + l + "\\). Qual é a sua área?");
		addParagrafoImagem(image);
		gerarAlternativas("" + area);
		setResolucao(res);
	}
}
