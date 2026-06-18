package matematica.intermediario.semelhancaangulos.nivel3package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.intermediario.semelhancaangulos.AuxSemelhancaAngulos;
import matematica.intermediario.semelhancaangulos.nivel1package.ConfigSemelhancaAngulos1;

public class Exercicio13 extends GeradorExercicio
{
	// Mesmos problemas do nivel1/Exercicio1, com expressão algébrica no ângulo desconhecido
	String[][] problemas = {
	{ "a=x,b", "", "i=a" },
	{ "a=x,c", "", "c=a->a+c" },
	{ "a=x,d", "", "c=a->d+a" },
	{ "b=x,a", "", "i=b" },
	{ "b=x,c", "", "c=b->c+b" },
	{ "b=x,d", "", "c=b->b+d" },
	{ "c=x,a", "", "c=c->a+c" },
	{ "c=x,b", "", "c=c->c+b" },
	{ "c=x,d", "", "i=c" },
	{ "d=x,a", "", "c=d->d+a" },
	{ "d=x,b", "", "c=d->b+d" },
	{ "d=x,c", "", "i=d" },
	};

	@Override
	protected void construir()
	{
		int pos = rand.nextInt(problemas.length);

		String angleImage = problemas[pos][0];
		String angleResult = problemas[pos][1];
		String instrucao = problemas[pos][2];

		int a = 20 + rand.nextInt(50);
		int b = 180 - a;

		ConfigSemelhancaAngulos1 config = new ConfigSemelhancaAngulos1(a, a, b, b);

		int resultado = 1 + rand.nextInt(50);
		String resultadoCorreto = "" + resultado;

		AuxSemelhancaAngulos.mostrarAngulosExpressao(angleImage, config, resultado);

		BufferedImage image = config.criarImagem();

		AuxSemelhancaAngulos.mostrarAngulos(angleResult, config);

		String resolucao = AuxSemelhancaAngulos.resolucao(instrucao, config);

		addParagrafo("Encontre o valor de \\(x\\):");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
