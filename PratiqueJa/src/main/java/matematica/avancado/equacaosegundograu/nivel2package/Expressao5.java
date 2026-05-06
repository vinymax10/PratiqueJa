package matematica.avancado.equacaosegundograu.nivel2package;

import matematica.Auxiliar;
import matematica.expressao.MyExpression;
import modelo.matematica.Conta;

public class Expressao5 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Expressao5(int index)
	{
		super(index);
		int x = 1 + rand.nextInt(9);
		int delta = 1 + rand.nextInt(9);
		int a = 1 + rand.nextInt(9);
		if (rand.nextBoolean())
			a *= -1;

		int b = delta - (2 * a * x);
		int c = (-x * delta) + (a * x * x);

		textLatex = "f(x)=";

		textLatex += Auxiliar.getNumber(a, "x^2", true);
		textLatex += "+bx";
		textLatex += Auxiliar.getNumber(c, "", false);

		int num = 1 + rand.nextInt(19);

		int resultado = (a * num * num) + (b * num) + c;
		resultadoCorreto = "" + b;

		pergunta ="Encontre \\( b \\) tendo \\( f(" + num + ")=" + resultado+" \\)";

		resolucaoLatex="f(" + num + ")="+a+" \\cdot "+num+"^2"+"+b \\cdot "+num+
		Auxiliar.getNumber(c,"", false)+"="+resultado+"\\\\";

		MyExpression expressao = new MyExpression(a+"*"+(num*num)+"+b *"+num+
		Auxiliar.getNumber(c,"", false)+"="+resultado);
		resolucaoLatex += expressao.resolverLatex();

	}

	public static void main(String[] args)
	{
		new Expressao5(1);
	}

}
