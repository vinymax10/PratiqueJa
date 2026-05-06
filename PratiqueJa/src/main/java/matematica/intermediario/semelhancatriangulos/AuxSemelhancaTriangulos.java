package matematica.intermediario.semelhancatriangulos;

import java.util.Random;

import matematica.Racional;
import matematica.expressao.MyExpression;


public class AuxSemelhancaTriangulos
{

	public static String gerarExpressaoLatex(int resultado, Racional arco)
	{
		Random rand = new Random();
		int a = 1 + rand.nextInt(9);
		Racional b = arco.minus(new Racional(a * resultado));
		b.fatoracao(2);

		MyExpression expressao = new MyExpression(a + "x+" + b.toString());
		String str = expressao.imprimir();
		return str;
	}
	
}
