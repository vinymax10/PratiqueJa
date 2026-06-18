package matematica.intermediario.pitagoras.nivel1package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.intermediario.pitagoras.ResolucaoPitagoras;
import matematica.intermediario.pitagoras.config.Config;
import matematica.intermediario.pitagoras.dados.DadosEspecial;
import matematica.intermediario.pitagoras.dados.NoPitagoras;

public class Image17 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int l = 2 + rand.nextInt(7); // cateto menor l = 2..8

		// 30-60-90: cateto menor=l, cateto maior=l√3, hipotenusa=2l
		NoPitagoras menor = new NoPitagoras(l, false);
		NoPitagoras maior = new NoPitagoras(3 * l * l, true);
		NoPitagoras hipotenusa = new NoPitagoras(2 * l, false);
		hipotenusa.str = "a";

		double porcent = menor.magnitude() / maior.magnitude();
		DadosEspecial dados = new DadosEspecial(maior, menor, hipotenusa, porcent);

		Config config = Config.build1(dados);
		BufferedImage image = config.criarImagem();

		addParagrafo("Em um triângulo 30°-60°-90°, o cateto menor mede \\(" + l + "\\). Qual o valor de \\(a\\)?");
		addParagrafoImagem(image);

		String resultadoCorreto = "" + (2 * l);
		gerarAlternativas(resultadoCorreto);

		String res = "No triângulo 30°-60°-90°, a hipotenusa é o dobro do cateto menor:";
		res += "\\(\\\\\\)";
		res += "\\(" + ResolucaoPitagoras.formula() + "\\)";
		res += "\\(\\\\\\)";
		res += "\\(" + l + "^2 + (\\sqrt{" + (3 * l * l) + "})^2 = a^2 \\\\";
		res += (l * l) + " + " + (3 * l * l) + " = a^2 \\\\";
		res += "a^2 = " + (4 * l * l) + " \\\\";
		res += "a = \\sqrt{" + (4 * l * l) + "} = \\mathbf{" + (2 * l) + "}\\)";
		setResolucao(res);
	}
}
