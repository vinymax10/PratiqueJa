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

public class Image16 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int l = 2 + rand.nextInt(8); // l = 2..9

		NoPitagoras base = new NoPitagoras(l, false);
		NoPitagoras altura = new NoPitagoras(l, false);
		NoPitagoras hipotenusa = new NoPitagoras(2 * l * l, true);
		hipotenusa.str = "a";

		DadosEspecial dados = new DadosEspecial(base, altura, hipotenusa, 1.0);

		Config config = Config.build1(dados);
		BufferedImage image = config.criarImagem();

		addParagrafo("Qual o valor de \\(a\\)?");
		addParagrafoImagem(image);

		String resultadoCorreto = hipotenusa.show();

		Set<String> usados = new HashSet<>();
		usados.add(resultadoCorreto);
		List<String> distratores = new ArrayList<>();
		int[] deltas = {1, -1, 2, -2, 3, 4};
		for(int d : deltas)
		{
			if(distratores.size() >= 3) break;
			int lAlt = l + d;
			if(lAlt <= 0) continue;
			String alt = "\\sqrt{" + (2 * lAlt * lAlt) + "}";
			if(usados.add(alt)) distratores.add(alt);
		}
		List<String> distrLatex = new ArrayList<>();
		for(String d : distratores) distrLatex.add("\\(" + d + "\\)");
		embaralharEAdicionarAlternativas("\\(" + resultadoCorreto + "\\)", distrLatex);

		String res = "No triângulo isósceles retângulo (45°-45°-90°), os dois catetos são iguais:";
		res += "\\(\\\\\\)";
		res += "\\(" + ResolucaoPitagoras.formula() + "\\)";
		res += "\\(\\\\\\)";
		res += "\\(" + l + "^2 + " + l + "^2 = a^2 \\\\";
		res += (l * l) + " + " + (l * l) + " = a^2 \\\\";
		res += "a^2 = " + (2 * l * l) + " \\\\";
		res += "a = \\mathbf{\\sqrt{" + (2 * l * l) + "}}\\)";
		setResolucao(res);
	}
}
