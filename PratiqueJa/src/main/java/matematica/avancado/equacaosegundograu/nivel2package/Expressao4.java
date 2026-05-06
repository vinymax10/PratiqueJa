package matematica.avancado.equacaosegundograu.nivel2package;

import matematica.Auxiliar;
import matematica.expressao.MyExpression;
import modelo.matematica.Conta;

public class Expressao4 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Expressao4(int index)
	{
		super(index);
		int x = 1 + rand.nextInt(9);
		int delta = 1 + rand.nextInt(9);
		int a = 1 + rand.nextInt(9);
		if (rand.nextBoolean())
			a *= -1;

		int b = delta - (2 * a * x);
		int c = (-x * delta) + (a * x * x);

		textLatex = "f(x)=ax^2";

		textLatex += Auxiliar.getNumber(b, "x", false);
		textLatex += Auxiliar.getNumber(c, "", false);

		int num = 1 + rand.nextInt(19);

		int resultado = (a * num * num) + (b * num) + c;
		resultadoCorreto = "" + a;

		pergunta = "Encontre \\( a \\) tendo  \\( f(" + num + ")=" + resultado+" \\)";

		resolucaoLatex="f(" + num + ")=a"+num+"^2"+Auxiliar.getNumber(b,"", false)+" \\cdot "+num+
		""+Auxiliar.getNumber(c,"", false)+"="+resultado+"\\\\";

		MyExpression expressao = new MyExpression("a"+(num*num)+Auxiliar.getNumber(b,"", false)+"*"+num+
		""+Auxiliar.getNumber(c,"", false)+"="+resultado);
		
		resolucaoLatex += expressao.resolverLatex();

	}

	public static void main(String[] args)
	{
		new Expressao4(1);
	}

}
