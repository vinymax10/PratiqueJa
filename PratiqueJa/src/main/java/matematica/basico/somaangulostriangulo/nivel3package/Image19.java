package matematica.basico.somaangulostriangulo.nivel3package;

import matematica.GeradorExercicio;
import matematica.basico.somaangulostriangulo.ResolucaoSAT2;
import matematica.expressao.MyExpression;

public class Image19 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a, b;
		do
		{
			a = 20 + rand.nextInt(40);
			b = 20 + rand.nextInt(40);
		}
		while (a + b >= 150);

		int ext = a + b;
		int x = 1 + rand.nextInt(20);
		int c = 1 + rand.nextInt(10);
		int d = ext - (c * x);

		MyExpression expressao = new MyExpression(c + "x+" + d);
		String strExt = expressao.imprimir();

		String resultadoCorreto = "" + x + "°";
		String resolucao = new MyExpression(strExt + "=" + a + "+" + b).resolverLatex();

		addParagrafo("Dois ângulos internos de um triângulo medem \\(" + a + "^\\circ\\) e \\(" + b + "^\\circ\\). O ângulo externo no terceiro vértice mede \\(" + strExt + "\\). Encontre \\(x\\):");
		gerarAlternativas(resultadoCorreto);
		addResolucao("\\(" + ResolucaoSAT2.boldLastResult(resolucao) + "\\)");
	}
}
