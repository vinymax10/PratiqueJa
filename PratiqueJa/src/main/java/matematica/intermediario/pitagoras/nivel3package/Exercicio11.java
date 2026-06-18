package matematica.intermediario.pitagoras.nivel3package;

import matematica.GeradorExercicio;
import matematica.intermediario.pitagoras.ResolucaoPitagoras;
import matematica.intermediario.pitagoras.dados.Dados;
import matematica.intermediario.pitagoras.dados.DadosInteiro;
import matematica.intermediario.pitagoras.dados.NoPitagoras;

public class Exercicio11 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		Dados dados = new DadosInteiro();
		int x1 = rand.nextInt(20);
		int x2 = x1 + (int) dados.base.magnitude();
		int y1 = rand.nextInt(20);
		int y2 = y1 + (int) dados.altura.magnitude();

		NoPitagoras a = dados.hipotenusa;
		NoPitagoras b = dados.base;
		NoPitagoras c = dados.altura;

		String resultadoCorreto = "" + a.show();

		String resolucao = ResolucaoPitagoras.formulaDistancia() + "\\\\";
		resolucao += "d =\\sqrt{(" + x2 + "-" + x1 + ")^2 + (" + y2 + "-" + y1 + ")^2}\\\\";
		resolucao += "d =\\sqrt{" + b.show() + "^2 + " + c.show() + "^2}\\\\";
		resolucao += "d =\\sqrt{" + b.quad() + " + " + c.quad() + "}\\\\";
		resolucao += "d =\\sqrt{" + (b.quad() + c.quad()) + "} = " + a.show() + "\\\\";

		addParagrafo("Qual a distância entre os pontos \\( (" + x1 + "," + y1 + ")\\) e \\( (" + x2 + "," + y2 + ")\\)?");
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
