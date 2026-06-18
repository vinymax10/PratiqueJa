package matematica.intermediario.pitagoras.nivel1package;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import matematica.GeradorExercicio;
import matematica.intermediario.pitagoras.ResolucaoPitagoras;
import matematica.intermediario.pitagoras.config.Config;
import matematica.intermediario.pitagoras.dados.DadosEspecial;
import matematica.intermediario.pitagoras.dados.NoPitagoras;

public class Image12 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int l = 2 + rand.nextInt(7); // cateto menor l = 2..8

		// 30-60-90: cateto menor=l, cateto maior=l√3, hipotenusa=2l
		NoPitagoras menor = new NoPitagoras(l, false);
		NoPitagoras maior = new NoPitagoras(3 * l * l, true);
		maior.str = "b";
		NoPitagoras hipotenusa = new NoPitagoras(2 * l, false);

		double porcent = menor.magnitude() / maior.magnitude();
		DadosEspecial dados = new DadosEspecial(maior, menor, hipotenusa, porcent);

		Config config = Config.build1(dados);
		BufferedImage image = config.criarImagem();

		addParagrafo("Em um triângulo 30°-60°-90°, o cateto menor mede \\(" + l + "\\). Qual o valor de \\(b\\)?");
		addParagrafoImagem(image);

		String resultadoCorreto = maior.show();

		Set<String> usados = new HashSet<>();
		usados.add(resultadoCorreto);
		List<String> distratores = new ArrayList<>();
		int[] deltas = {1, -1, 2, -2, 3, 4};
		for(int d : deltas)
		{
			if(distratores.size() >= 3) break;
			int lAlt = l + d;
			if(lAlt <= 0) continue;
			String alt = "\\sqrt{" + (3 * lAlt * lAlt) + "}";
			if(usados.add(alt)) distratores.add(alt);
		}
		List<String> distrLatex = new ArrayList<>();
		for(String d : distratores) distrLatex.add("\\(" + d + "\\)");
		embaralharEAdicionarAlternativas("\\(" + resultadoCorreto + "\\)", distrLatex);

		String res = "No triângulo 30°-60°-90°, o cateto maior vale \\(\\sqrt{3}\\) vezes o cateto menor:";
		res += "\\(\\\\\\)";
		res += "\\(" + ResolucaoPitagoras.formula() + "\\)";
		res += "\\(\\\\\\)";
		res += "\\(a = " + (2 * l) + ", \\quad c = " + l + " \\\\";
		res += l + "^2 + b^2 = " + (2 * l) + "^2 \\\\";
		res += "b^2 = " + (4 * l * l) + " - " + (l * l) + " = " + (3 * l * l) + " \\\\";
		res += "b = \\mathbf{\\sqrt{" + (3 * l * l) + "}}\\)";
		setResolucao(res);
	}
}
