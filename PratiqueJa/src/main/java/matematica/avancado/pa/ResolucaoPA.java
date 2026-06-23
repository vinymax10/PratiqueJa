package matematica.avancado.pa;

import matematica.DefinicaoCores;
import matematica.ParCor;
import matematica.Racional;
import matematica.expressao.MyExpression;

public class ResolucaoPA
{

	public static String[] n_esimo(Racional a1, Racional r, int termo)
	{
		Racional parcial = r.mult(new Racional(termo - 1));
		parcial.fatoracao(2);
		Racional resultado=a1.add(parcial);
		resultado.fatoracao(2);

		return new String[]
		{
			formula(),
			"a_1=" + a1.showDfrac() + ", n=" + termo + ", " + "r="
			+ (a(a1,r,2).showDfrac() + "-" + a1.showDfrac()) + "=" + r.showDfrac(),
			"a_{" + termo + "} = " + a1.showDfrac() + "+(" + termo + "-1)\\cdot" + r.showDfrac(),
			"a_{" + termo + "} = " + a1.showDfrac() + "+" + (termo - 1) + "\\cdot" + r.showDfrac(),
			"a_{" + termo + "} = " + a1.showDfrac() + "+" + parcial.showDfrac() + "="
			+ resultado.showDfrac()
		};
	}

	/**
	 * Passos controlados por este pacote; o último elemento é o bloco gerado pelo
	 * helper externo {@code MyExpression.resolverLatex()} (não editável, pode conter
	 * "\\" internos), que o chamador repassa intacto em um único {@code addResolucao}.
	 */
	public static String[] numeroTermos(Racional a1, Racional r, Racional an)
	{
		String expressao = an.toString() + " = " + a1.toString() + "+" + r.toString() + "n - "
		+ r.toString();
		MyExpression myExpression = new MyExpression(expressao);

		return new String[]
		{
			formula(),
			"a_1=" + a1.showDfrac() + ", a_n=" + an.showDfrac() + ", " + "r="
			+ (a(a1,r,2).showDfrac() + "-" + a1.showDfrac()) + "=" + r.showDfrac(),
			an.showDfrac() + " = " + a1.showDfrac() + "+(n-1)\\cdot" + r.showDfrac(),
			myExpression.resolverLatex()
		};
	}

	public static String[] x2(Racional a1, Racional r)
	{
		return new String[]
		{
			formula(),
			"a_1=" + a1.showDfrac() + ", n=2, " + "x=a_2",
			"r=" + a(a1, r, 4).showDfrac() + "-" + a(a1, r, 3).showDfrac() + "=" + r.showDfrac(),
			"x=" + a1.showDfrac() + "+(2-1)\\cdot" + r.showDfrac(),
			"x=" + a1.showDfrac() + "+1\\cdot" + r.showDfrac(),
			"x=" + a1.showDfrac() + "+" + r.showDfrac() + "=" + a(a1, r, 2).showDfrac()
		};
	}

	public static String[] x3(Racional a1, Racional r)
	{
		return new String[]
		{
			formula(),
			"a_1=" + a1.showDfrac() + ", n=3, " + "x=a_3",
			"r=" + a(a1, r, 2).showDfrac() + "-" + a1.showDfrac() + "=" + r.showDfrac(),
			"x=" + a1.showDfrac() + "+(3-1)\\cdot" + r.showDfrac(),
			"x=" + a1.showDfrac() + "+2\\cdot" + r.showDfrac(),
			"x=" + a1.showDfrac() + "+" + r.mult(new Racional(2)).showDfrac() + "="
			+ a(a1, r, 3).showDfrac()
		};
	}

	public static String[] x4(Racional a1, Racional r)
	{
		return new String[]
		{
			formula(),
			"a_1=" + a1.showDfrac() + ", n=4, " + "x=a_4",
			"r=" + a(a1, r, 2).showDfrac() + "-" + a1.showDfrac() + "=" + r.showDfrac(),
			"x=" + a1.showDfrac() + "+(4-1)\\cdot" + r.showDfrac(),
			"x=" + a1.showDfrac() + "+3\\cdot" + r.showDfrac(),
			"x=" + a1.showDfrac() + "+" + r.mult(new Racional(3)).showDfrac() + "="
			+ a(a1, r, 4).showDfrac()
		};
	}

	public static String[] x1(Racional a1, Racional r)
	{
		return new String[]
		{
			formula(),
			"n=2, " + "x=a_1",
			"r=" + a(a1, r, 3).showDfrac() + "-" + a(a1, r, 2).showDfrac() + "=" + r.showDfrac(),
			a(a1, r, 2).showDfrac() + "=x+(2-1)\\cdot" + r.showDfrac(),
			a(a1, r, 2).showDfrac() + "=x+1\\cdot" + r.showDfrac(),
			a(a1, r, 2).showDfrac() + "=x+" + r.showDfrac(),
			"x=" + a(a1, r, 2).showDfrac() + "-" + r.showDfrac() + "=" + a1.showDfrac()
		};
	}

	public static String[] resolucaoSoma(Racional a1, Racional r, Racional an, int n)
	{
		Racional parcial=a1.add(an);
		parcial.fatoracao(2);

		String passoParcial = "S_{" + n + "} = \\dfrac{" + parcial.showDfrac() + "\\cdot" + n + "}{2}=";

		parcial=parcial.mult(new Racional(n));
		parcial.fatoracao(2);

		passoParcial += "\\dfrac{" + parcial.showDfrac() + "}{2}";

		Racional meio=new Racional(1,2);
		String passoFinal = "S_{" + n + "}=" + parcial.showDfrac()+" \\cdot "+meio.showDfrac()+"=";
		Racional resultado = parcial.mult(meio);

		passoFinal += resultado.showDfrac();

		resultado.setSimplificou(false);
		resultado.fatoracao(2);

		if(resultado.isSimplificou())
			passoFinal += "=" + resultado.showDfrac();

		return new String[]
		{
			formulaSoma(),
			"a_1=" + a1.showDfrac() + ", n=" + n + ", a_{" + n + "}=" + an.showDfrac(),
			"S_{" + n + "} = \\dfrac{(" + a1.showDfrac() + "+" + an.showDfrac() + ")\\cdot" + n + "}{2}",
			passoParcial,
			passoFinal
		};
	}

	public static String[] resolucaoSoma2(Racional a1, Racional r, Racional an, int n)
	{
		Racional soma = ResolucaoPA.soma(a1, an, n);
		Racional parcial=a1.add(an);
		parcial.fatoracao(2);

		String expressao = soma.toString() + " * 2 =" + parcial.toString() + "* n";
		MyExpression myExpression = new MyExpression(expressao);

		return new String[]
		{
			formulaSoma(),
			"a_1=" + a1.showDfrac() + ", a_n=" + an.showDfrac() + ", S_n=" + soma.showDfrac(),
			soma.showDfrac() + " = \\dfrac{(" + a1.showDfrac() + "+" + an.showDfrac() + ")\\cdot n}{2}",
			soma.showDfrac() + " = \\dfrac{" + parcial.showDfrac() + "\\cdot n}{2}",
			myExpression.resolverLatex()
		};
	}

	public static String[] resolucaoSoma3(Racional a1, Racional r, Racional an, int n)
	{
		Racional soma = ResolucaoPA.soma(a1, an, n);
		String expressao = soma.toString() + " * 2 = "+n+"x+"+an.toString()+"*"+n;
		MyExpression myExpression = new MyExpression(expressao);

		return new String[]
		{
			formulaSoma(),
			"n=" + n + ", a_{"+n+"}=" + an.showDfrac() + ", S_{"+n+"}=" + soma.showDfrac()+", x=a_1",
			soma.showDfrac() + " = \\dfrac{(x+" + an.showDfrac() + ")\\cdot"+n+" }{2}",
			myExpression.resolverLatex()
		};
	}

	public static String[] resolucaoSoma4(Racional a1, Racional r, Racional an, int n)
	{
		Racional soma = ResolucaoPA.soma(a1, an, n);
		String expressao = soma.toString() + " * 2 = "+a1.toString()+"*"+n+"+x"+n;
		MyExpression myExpression = new MyExpression(expressao);

		return new String[]
		{
			formulaSoma(),
			"n=" + n + ", a_1=" + a1.showDfrac() + ", S_{"+n+"}=" + soma.showDfrac()+", x=a_{"+n+"}",
			soma.showDfrac() + " = \\dfrac{("+a1.showDfrac() + "+x)\\cdot"+n+" }{2}",
			myExpression.resolverLatex()
		};
	}

	public static Racional soma(Racional a1, Racional an, int n)
	{
		Racional soma= a1.add(an).mult(new Racional(n)).div(new Racional(2));
		soma.fatoracao(2);
		return soma;
	 }

	public static Racional a(Racional a1, Racional r, int n)
	{
		Racional an = a1;
		for(int i = 0; i < n - 1; i++)
		{
			an = an.add(r);
			an.fatoracao(2);
		}
		
		return an;
	}

	public static String formula()
	{
		return ParCor.formula("a_n=a_1+(n-1) \\cdot r");
	}

	public static String formulaSoma()
	{
		return ParCor.formula("S_n=\\dfrac{(a_1 + a_n) \\cdot n}{2}");
	}
}