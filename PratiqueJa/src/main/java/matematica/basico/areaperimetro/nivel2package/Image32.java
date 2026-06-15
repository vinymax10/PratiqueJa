package matematica.basico.areaperimetro.nivel2package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.ConfigImagem;
import matematica.basico.areaperimetro.config.ConfigTrapezio;

public class Image32 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int B = 2 * (5 + rand.nextInt(15));
		int b = (int) (((double) B) * 0.65);
		int l = (int) (((double) B) * 0.7);

		String perimetro = "" + (B + b + l + l);

		String resultadoCorreto = "" + B;

		String resolucao = ResolucaoAreaPerimetro.formulaPerimetroTrapezio() + "\\\\";
		resolucao += "b=" + b + ",\\quad c=" + l + ",\\quad d=" + l + "\\\\";
		resolucao += "a+" + b + "+" + l + "+" + l + "=" + perimetro + "\\\\";
		resolucao += "a=" + perimetro + "-" + b + "-" + l + "-" + l + "=" + B;

		ConfigImagem config = new ConfigTrapezio("a", b + "", "", l + "", l + "", false);
		BufferedImage image = config.criarImagem();

		addParagrafo("Se o perímetro do trapézio é \\(" + perimetro + "\\), qual o valor de \\(a\\)?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
