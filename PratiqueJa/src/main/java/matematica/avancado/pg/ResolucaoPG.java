package matematica.avancado.pg;

import matematica.DefinicaoCores;
import matematica.ParCor;
import matematica.Racional;
import matematica.expressao.MyExpression;

public class ResolucaoPG
{

	public static String n_esimo(Racional a1, Racional q, int n)
	{
		String resolucao = formula() + "\\\\";
		resolucao += "a_1=" + a1.showDfrac() + ", n=" + n + ", " + "q="
		+ "\\dfrac{"+(a(a1,q,2).showDfrac() + "}{" + a1.showDfrac()) + "}=" + q.showDfrac() + "\\\\";
		resolucao += "a_{" + n + "} = " + a1.showDfrac() + "\\cdot " + q.showDfrac() + "^{("+n+"-1)}";
		resolucao += " = " + a1.showDfrac() + "\\cdot " + q.showDfrac() + "^{"+(n-1)+"}" + "\\\\";
		Racional parcial=q.pow(new Racional(n-1));
//		parcial.fatoracao(2);
		resolucao += "a_{" + n + "} = " + a1.showDfrac() + "\\cdot " + parcial.showDfrac();
		parcial=a1.mult(parcial);

		resolucao += " = \\mathbf{" + parcial.showDfrac() + "}";

		return resolucao;
	}

	public static String n_esimoFrac(Racional a1, Racional q, int n)
	{
		String resolucao = formula() + "\\\\";
		resolucao += "a_1=" + a1.showDfrac() + ", n=" + n + "\\\\";
		
		resolucao += "q="+a(a1,q,2).showDfrac()+" \\div "+a1.showDfrac()+" = "
		+a(a1,q,2).showDfrac()+" \\cdot "+a1.inverter().showDfrac()+" = "+ q.showDfrac() + "\\\\";
		
		resolucao += "a_{" + n + "} = " + a1.showDfrac() + "\\cdot (" + q.showDfrac() + ")^{("+n+"-1)}";
		resolucao += " = " + a1.showDfrac() + "\\cdot (" + q.showDfrac() + ")^{"+(n-1)+"}" + "\\\\";
		Racional parcial=q.pow(new Racional(n-1));
//		parcial.fatoracao(2);
		resolucao += "a_{" + n + "} = " + a1.showDfrac() + "\\cdot " + parcial.showDfrac();
		parcial=a1.mult(parcial);

		resolucao += " = \\mathbf{" + parcial.showDfrac() + "}";
		parcial.setSimplificou(false);
		parcial.fatoracao(2);
		if(parcial.isSimplificou())
			resolucao += " = \\mathbf{" + parcial.showDfrac() + "}";


		return resolucao;
	}

	public static String numeroTermos(Racional a1, Racional q, Racional an, int n)
	{
		String resolucao = formula() + "\\\\";
		resolucao += "a_1=" + a1.showDfrac() + ", a_n=" + an.showDfrac() + ", " + "q="
		+ "\\dfrac{"+(a(a1,q,2).showDfrac() + "}{" + a1.showDfrac()) + "}=" + q.showDfrac() + "\\\\";

		resolucao += an.showDfrac() + " = " + a1.showDfrac() + "\\cdot " + q.showDfrac() + "^{(n-1)}"+ "\\\\";
		resolucao += "\\dfrac{"+an.showDfrac()+"}{" +a1.showDfrac()+"} = " + q.showDfrac() + "^{(n-1)}"+ "\\\\";
		Racional parcial=an.div(a1);
		parcial.fatoracao(2);
		
		resolucao += parcial.showDfrac()+" = " + q.showDfrac() + "^{(n-1)}"+ "\\\\";
		resolucao += q.showDfrac()+"^{"+(n-1)+"} = " + q.showDfrac() + "^{(n-1)}"+ "\\\\";
		resolucao += "n-1 = " + (n-1)+ "\\\\";
		resolucao += "n = " + (n-1)+"+"+1;
		resolucao += " = \\mathbf{" + n + "}";

		return resolucao;
	}

	public static String numeroTermosFrac(Racional a1, Racional q, Racional an, int n)
	{
		String resolucao = formula() + "\\\\";
		resolucao += "a_1=" + a1.showDfrac() + ", a_n=" + an.showDfrac() + "\\\\";
		
		resolucao += "q="+a(a1,q,2).showDfrac()+" \\div "+a1.showDfrac()+" = "
		+a(a1,q,2).showDfrac()+" \\cdot "+a1.inverter().showDfrac()+" = "+ q.showDfrac() + "\\\\";
		
		resolucao += a1.showDfrac() + "\\cdot (" + q.showDfrac() + ")^{(n-1)}"+ " = "+an.showDfrac()+"\\\\";
		resolucao += "(" + q.showDfrac() + ")^{(n-1)}"+ " = "+an.showDfrac()+" \\cdot " +a1.inverter().showDfrac()+"\\\\";
		Racional parcial=an.div(a1);
		parcial.fatoracao(2);
		
		resolucao += "(" + q.showDfrac() + ")^{(n-1)}"+ " = "+parcial.showDfrac()+"\\\\";
		resolucao += "(" + q.showDfrac() + ")^{(n-1)}"+ " = "+"("+ q.showDfrac()+")^{"+(n-1)+"}\\\\";
		resolucao += "n-1 = " + (n-1)+ "\\\\";
		resolucao += "n = " + (n-1)+"+"+1;
		resolucao += " = \\mathbf{" + n + "}";

		return resolucao;
	}

	public static String x2(Racional a1, Racional q)
	{
		String resolucao = formula() + "\\\\";
		resolucao += "a_1=" + a1.showDfrac() + ", n=2, " + "x=a_2\\\\";
		resolucao += "q="
		+ "\\dfrac{"+(a(a1,q,4).showDfrac() + "}{" + a(a1,q,3).showDfrac()) + "}=" + q.showDfrac() + "\\\\";
		
		resolucao += "x = " + a1.showDfrac() + "\\cdot" + q.showDfrac() + "^{(2-1)}";
		resolucao += " = " + a1.showDfrac() + "\\cdot" + q.showDfrac() + "^{1}" + "\\\\";
		Racional parcial=q.pow(new Racional(1));
//		parcial.fatoracao(2);
		resolucao += "x = " + a1.showDfrac() + "\\cdot " + parcial.showDfrac();
		parcial=a1.mult(parcial);

		resolucao += " = \\mathbf{" + parcial.showDfrac() + "}";

		return resolucao;
	}

	public static String x2Frac(Racional a1, Racional q)
	{
		String resolucao = formula() + "\\\\";
		resolucao += "a_1=" + a1.showDfrac() + ", n=2, " + "x=a_2\\\\";
		
		resolucao += "q="+a(a1,q,4).showDfrac()+" \\div "+a(a1,q,3).showDfrac()+" = "
		+a(a1,q,4).showDfrac()+" \\cdot "+a(a1,q,3).inverter().showDfrac()+" = "+ q.showDfrac() + "\\\\";
		
		resolucao += "x = " + a1.showDfrac() + "\\cdot (" + q.showDfrac() + ")^{(2-1)}";
		resolucao += " = " + a1.showDfrac() + "\\cdot (" + q.showDfrac() + ")^{1}" + "\\\\";
		Racional parcial=q.pow(new Racional(1));
//		parcial.fatoracao(2);
		resolucao += "x = " + a1.showDfrac() + "\\cdot " + parcial.showDfrac();
		parcial=a1.mult(parcial);

		resolucao += " = \\mathbf{" + parcial.showDfrac() + "}";
		parcial.setSimplificou(false);
		parcial.fatoracao(2);
		if(parcial.isSimplificou())
			resolucao += " = \\mathbf{" + parcial.showDfrac() + "}";

		return resolucao;
	}

	public static String x3(Racional a1, Racional q)
	{
		String resolucao = formula() + "\\\\";
		resolucao += "a_1=" + a1.showDfrac() + ", n=3, " + "x=a_3\\\\";
		
		resolucao += "q="
		+ "\\dfrac{"+(a(a1,q,2).showDfrac() + "}{" + a1.showDfrac()) + "}=" + q.showDfrac() + "\\\\";
		
		resolucao += "x = " + a1.showDfrac() + "\\cdot" + q.showDfrac() + "^{(3-1)}";
		resolucao += " = " + a1.showDfrac() + "\\cdot" + q.showDfrac() + "^{2}" + "\\\\";
		Racional parcial=q.pow(new Racional(2));
//		parcial.fatoracao(2);
		resolucao += "x = " + a1.showDfrac() + "\\cdot " + parcial.showDfrac();
		parcial=a1.mult(parcial);

		resolucao += " = \\mathbf{" + parcial.showDfrac() + "}";

		return resolucao;
	}

	public static String x3Frac(Racional a1, Racional q)
	{
		String resolucao = formula() + "\\\\";
		resolucao += "a_1=" + a1.showDfrac() + ", n=3, " + "x=a_3\\\\";
		
		resolucao += "q="+a(a1,q,2).showDfrac()+" \\div "+a1.showDfrac()+" = "
		+a(a1,q,2).showDfrac()+" \\cdot "+a1.inverter().showDfrac()+" = "+ q.showDfrac() + "\\\\";
		
		resolucao += "x = " + a1.showDfrac() + "\\cdot (" + q.showDfrac() + ")^{(3-1)}";
		resolucao += " = " + a1.showDfrac() + "\\cdot (" + q.showDfrac() + ")^{2}" + "\\\\";
		Racional parcial=q.pow(new Racional(2));
//		parcial.fatoracao(2);
		resolucao += "x = " + a1.showDfrac() + "\\cdot " + parcial.showDfrac();
		parcial=a1.mult(parcial);

		resolucao += " = \\mathbf{" + parcial.showDfrac() + "}";
		parcial.setSimplificou(false);
		parcial.fatoracao(2);
		if(parcial.isSimplificou())
			resolucao += " = \\mathbf{" + parcial.showDfrac() + "}";

		return resolucao;
	}

	public static String x4(Racional a1, Racional q)
	{
		String resolucao = formula() + "\\\\";
		resolucao += "a_1=" + a1.showDfrac() + ", n=4, " + "x=a_4\\\\";
		resolucao += "q="
		+ "\\dfrac{"+(a(a1,q,2).showDfrac() + "}{" + a1.showDfrac()) + "}=" + q.showDfrac() + "\\\\";
		
		resolucao += "x = " + a1.showDfrac() + "\\cdot" + q.showDfrac() + "^{(4-1)}";
		resolucao += " = " + a1.showDfrac() + "\\cdot" + q.showDfrac() + "^{3}" + "\\\\";
		Racional parcial=q.pow(new Racional(3));
//		parcial.fatoracao(2);
		resolucao += "x = " + a1.showDfrac() + "\\cdot " + parcial.showDfrac();
		parcial=a1.mult(parcial);

		resolucao += " = \\mathbf{" + parcial.showDfrac() + "}";

		return resolucao;
	}

	public static String x4Frac(Racional a1, Racional q)
	{
		String resolucao = formula() + "\\\\";
		resolucao += "a_1=" + a1.showDfrac() + ", n=4, " + "x=a_4\\\\";
		
		resolucao += "q="+a(a1,q,2).showDfrac()+" \\div "+a1.showDfrac()+" = "
		+a(a1,q,2).showDfrac()+" \\cdot "+a1.inverter().showDfrac()+" = "+ q.showDfrac() + "\\\\";
		
		resolucao += "x = " + a1.showDfrac() + "\\cdot (" + q.showDfrac() + ")^{(4-1)}";
		resolucao += " = " + a1.showDfrac() + "\\cdot (" + q.showDfrac() + ")^{3}" + "\\\\";
		Racional parcial=q.pow(new Racional(3));
//		parcial.fatoracao(2);
		resolucao += "x = " + a1.showDfrac() + "\\cdot " + parcial.showDfrac();
		parcial=a1.mult(parcial);

		resolucao += " = \\mathbf{" + parcial.showDfrac() + "}";
		parcial.setSimplificou(false);
		parcial.fatoracao(2);
		if(parcial.isSimplificou())
			resolucao += " = \\mathbf{" + parcial.showDfrac() + "}";

		return resolucao;
	}

	public static String x1(Racional a1, Racional q)
	{
		String resolucao = formula() + "\\\\";
		resolucao += "n=2, " + "x=a_1, ";
		resolucao += "q="
		+ "\\dfrac{"+(a(a1,q,3).showDfrac() + "}{" + a(a1,q,2).showDfrac()) + "}=" + q.showDfrac() + "\\\\";
		
		resolucao += a(a1, q, 2).showDfrac()+" = x \\cdot" + q.showDfrac() + "^{(2-1)}";
		resolucao += " = x \\cdot" + q.showDfrac() + "^{1}" + "\\\\";
//		parcial.fatoracao(2);
		resolucao += a(a1, q, 2).showDfrac()+" = x \\cdot" + q.showDfrac()+ "\\\\";
		resolucao += "x = \\dfrac{"+a(a1, q, 2).showDfrac()+"}{" + q.showDfrac()+"}";
		resolucao += " = \\mathbf{" + a1.showDfrac() + "}";

		return resolucao;
	}

	public static String x1Frac(Racional a1, Racional q)
	{
		String resolucao = formula() + "\\\\";
		resolucao += "n=2, " + "x=a_1 "+ "\\\\";
		
		resolucao += "q="+a(a1,q,3).showDfrac()+" \\div "+a(a1,q,2).showDfrac()+" = "
		+a(a1,q,3).showDfrac()+" \\cdot "+a(a1,q,2).inverter().showDfrac()+" = "+ q.showDfrac() + "\\\\";
		
		resolucao += a(a1, q, 2).showDfrac()+" = x \\cdot (" + q.showDfrac() + ")^{(2-1)}";
		resolucao += " = x \\cdot (" + q.showDfrac() + ")^{1}" + "\\\\";
//		parcial.fatoracao(2);
		resolucao += a(a1, q, 2).showDfrac()+" = x \\cdot" + q.showDfrac()+ "\\\\";
		resolucao += "x ="+a(a1, q, 2).showDfrac()+"\\div" + q.showDfrac();
		resolucao += " ="+a(a1, q, 2).showDfrac()+"\\cdot" + q.inverter().showDfrac();
		resolucao += " = \\mathbf{" + a1.showDfrac() + "}";

		return resolucao;
	}

	public static String resolucaoSoma(Racional a1, Racional q, int n)
	{
		String resolucao = formulaSoma() + "\\\\";
		resolucao += "a_1=" + a1.showDfrac() + ", n=" + n + ", ";
		resolucao += "q="
		+ "\\dfrac{"+(a(a1,q,2).showDfrac() + "}{" + a1.showDfrac()) + "}=" + q.showDfrac() + "\\\\";
		
		resolucao += "S_{" + n + "} = \\dfrac{" + a1.showDfrac() + "\\cdot (" + q.showDfrac() + "^{"+n+"}-1)}{"+q.showDfrac()+"-1}\\\\";
		
		Racional parcial=q.pow(new Racional(n));
		parcial.fatoracao(2);
		Racional denominador=q.minus(new Racional(1));

		resolucao += "S_{" + n + "} = \\dfrac{" + a1.showDfrac() + "\\cdot (" + parcial.showDfrac() + "-1)}{"+denominador.showDfrac()+"}\\\\";

		parcial=parcial.minus(new Racional(1));
		
		if(denominador.igual(new Racional(1)))
		{
			resolucao += "S_{" + n + "} = " + a1.showDfrac() + "\\cdot " + parcial.showDfrac() ;
			parcial=a1.mult(parcial);
			resolucao += " = \\mathbf{" + parcial.showDfrac() + "}";
		}
		else
		{
			resolucao += "S_{" + n + "} = \\dfrac{" + a1.showDfrac() + "\\cdot " + parcial.showDfrac() + "}{"+denominador.showDfrac()+"}\\\\";
			parcial=a1.mult(parcial);
			resolucao += "S_{" + n + "} = \\dfrac{" + parcial.showDfrac() + "}{"+denominador.showDfrac()+"}";
		}

		parcial=parcial.div(denominador);
		parcial.setSimplificou(false);
		parcial.fatoracao(2);
		if(parcial.isSimplificou())
			resolucao += " = \\mathbf{" + parcial.showDfrac() + "}";

		return resolucao;
	}

	public static String resolucaoSomaFrac(Racional a1, Racional q, int n)
	{
		String resolucao = formulaSoma() + "\\\\";
		resolucao += "a_1=" + a1.showDfrac() + ", n=" + n + "\\\\";
		resolucao += "q="+a(a1,q,2).showDfrac()+" \\div "+a1.showDfrac()+" = "
		+a(a1,q,2).showDfrac()+" \\cdot "+a1.inverter().showDfrac()+" = "+ q.showDfrac() + "\\\\";
		
		resolucao += "S_{" + n + "} = \\dfrac{" + a1.showDfrac() + "\\cdot ( (" + q.showDfrac() + ")^{"+n+"}-1)}{"+q.showDfrac()+"-1}\\\\";
		
		Racional parcial=q.pow(new Racional(n));
		parcial.fatoracao(2);
		Racional denominador=q.minus(new Racional(1));

		resolucao += "S_{" + n + "} = \\dfrac{" + a1.showDfrac() + "\\cdot (" + parcial.showDfrac() + "-1)}{"+denominador.showDfrac()+"}";

		parcial=parcial.minus(new Racional(1));
		parcial.fatoracao(2);
		if(denominador.igual(new Racional(1)))
		{
			resolucao += " = " + a1.showDfrac() + "\\cdot " + parcial.showDfrac() ;
			parcial=a1.mult(parcial);
			resolucao += " = \\mathbf{" + parcial.showDfrac() + "}";
		}
		else
		{
			resolucao += " = \\dfrac{" + a1.showDfrac() + "\\cdot " + parcial.showDfrac() + "}{"+denominador.showDfrac()+"}\\\\";
			parcial=a1.mult(parcial);
			parcial.fatoracao(2);

			resolucao += "S_{" + n + "} = \\dfrac{" + parcial.showDfrac() + "}{"+denominador.showDfrac()+"}";
			resolucao += " = "+parcial.showDfrac() +"\\cdot"+denominador.inverter().showDfrac();
		}

		parcial=parcial.div(denominador);
		parcial.setSimplificou(false);
		parcial.fatoracao(2);
		if(parcial.isSimplificou())
			resolucao += " = \\mathbf{" + parcial.showDfrac() + "}";

		return resolucao;
	}

	public static String resolucaoSoma2(Racional a1, Racional q, int n)
	{
		String resolucao = formulaSoma() + "\\\\";
		Racional soma = ResolucaoPG.soma(a1, q, n);

		resolucao += "a_1=" + a1.showDfrac() + ", S_n=" + soma.showDfrac() + ", ";
		resolucao += "q="
		+ "\\dfrac{"+(a(a1,q,2).showDfrac() + "}{" + a1.showDfrac()) + "}=" + q.showDfrac() + "\\\\";
		
		resolucao += soma.showDfrac() + " = \\dfrac{" + a1.showDfrac() + "\\cdot (" + q.showDfrac() + "^{n}-1)}{"+q.showDfrac()+"-1}\\\\";
		
		Racional denominador=q.minus(new Racional(1));

		resolucao += "\\dfrac{" + a1.showDfrac() + "\\cdot "+q.showDfrac() + "^{n} - "+a1.showDfrac()+"}{"+denominador.showDfrac()+"} = "+soma.showDfrac()+" \\\\";

		resolucao += a1.showDfrac() + "\\cdot "+q.showDfrac() + "^{n} - "+a1.showDfrac()+" = "+soma.showDfrac()+ " \\cdot "+denominador.showDfrac();
		Racional parcial=soma.mult(denominador);
//		parcial.fatoracao(2);
		resolucao += "="+parcial.showDfrac()+"\\\\";

		resolucao += a1.showDfrac() + "\\cdot "+q.showDfrac() + "^{n} = "+parcial.showDfrac()+"+"+a1.showDfrac();

		parcial=parcial.add(a1);
//		parcial.fatoracao(2);
		
		resolucao += "="+parcial.showDfrac()+" \\\\";

		resolucao += q.showDfrac() + "^{n} ="+"\\dfrac{"+parcial.showDfrac()+"}{"+a1.showDfrac()+"}";

		parcial=parcial.div(a1);
		parcial.fatoracao(2);
		resolucao +=" = "+parcial.showDfrac()+" \\\\";

		resolucao += q.showDfrac() + "^{n} ="+q.showDfrac()+"^{"+n+"} \\Rightarrow n = \\mathbf{" + n + "}";

		return resolucao;
	}

	public static String resolucaoSoma2Frac(Racional a1, Racional q, int n)
	{
		String resolucao = formulaSoma() + "\\\\";
		Racional soma = ResolucaoPG.soma(a1, q, n);

		resolucao += "a_1=" + a1.showDfrac() + ", S_n=" + soma.showDfrac() + "\\\\";
		resolucao += "q="+a(a1,q,2).showDfrac()+" \\div "+a1.showDfrac()+" = "
		+a(a1,q,2).showDfrac()+" \\cdot "+a1.inverter().showDfrac()+" = "+ q.showDfrac() + "\\\\";
		
		resolucao += soma.showDfrac() + " = \\dfrac{" + a1.showDfrac() + "\\cdot (" + q.showDfrac() + "^{n}-1)}{"+q.showDfrac()+"-1}\\\\";
		
		Racional denominador=q.minus(new Racional(1));

		resolucao += "\\dfrac{" + a1.showDfrac() + "\\cdot "+q.showDfrac() + "^{n} - "+a1.showDfrac()+"}{"+denominador.showDfrac()+"} = "+soma.showDfrac()+" \\\\";

		resolucao += a1.showDfrac() + "\\cdot "+q.showDfrac() + "^{n} - "+a1.showDfrac()+" = "+soma.showDfrac()+ " \\cdot "+denominador.showDfrac();
		Racional parcial=soma.mult(denominador);
//		parcial.fatoracao(2);
		resolucao += "="+parcial.showDfrac()+"\\\\";

		resolucao += a1.showDfrac() + "\\cdot "+q.showDfrac() + "^{n} = "+parcial.showDfrac()+"+"+a1.showDfrac();

		parcial=parcial.add(a1);
		parcial.fatoracao(2);
		
		resolucao += "="+parcial.showDfrac()+" \\\\";

		resolucao += q.showDfrac() + "^{n} ="+""+parcial.showDfrac()+" \\cdot "+a1.inverter().showDfrac();

		parcial=parcial.div(a1);
		parcial.fatoracao(2);
		resolucao +=" = "+parcial.showDfrac()+" \\\\";

		resolucao += q.showDfrac() + "^{n} ="+q.showDfrac()+"^{"+n+"} \\Rightarrow n = \\mathbf{" + n + "}";

		return resolucao;
	}

	public static String resolucaoSoma3(Racional a1, Racional q, int n)
	{
		String resolucao = formulaSoma() + "\\\\";
		Racional soma = ResolucaoPG.soma(a1, q, n);
		resolucao += "S_n=" + soma.showDfrac() + ", x=a_1, ";
		resolucao += "q="
		+ "\\dfrac{"+(a(a1,q,n).showDfrac() + "}{" + a(a1,q,(n-1)).showDfrac()) + "}=" + q.showDfrac() + "\\\\";
		
		resolucao += soma.showDfrac() + " = \\dfrac{ x \\cdot (" + q.showDfrac() + "^{"+n+"}-1)}{"+q.showDfrac()+"-1}\\\\";

		Racional parcial=q.pow(new Racional(n));
		Racional denominador=q.minus(new Racional(1));

		resolucao += soma.showDfrac() + " = \\dfrac{ x \\cdot (" + parcial.showDfrac() + "-1)}{"+denominador.showDfrac()+"}\\\\";

		parcial=parcial.minus(new Racional(1));
		resolucao += soma.showDfrac() + " \\cdot "+ denominador.showDfrac()+" = x \\cdot " + parcial.showDfrac() + "\\\\";
		Racional parcial2=soma.mult(denominador);
		
//		resolucao +=  parcial.showDfrac() + "x = "+ parcial2.showDfrac() + "\\\\";
		
		String expressao = parcial.toString() + "x = "+parcial2.toString();
		MyExpression myExpression = new MyExpression(expressao);
		resolucao += myExpression.resolverLatex();
		
		return resolucao;
	}
	
	public static String resolucaoSoma3Frac(Racional a1, Racional q, int n)
	{
		String resolucao = formulaSoma() + "\\\\";
		Racional soma = ResolucaoPG.soma(a1, q, n);
		resolucao += "S_n=" + soma.showDfrac() + ", x=a_1 \\\\";
		
		resolucao += "q="+a(a1,q,n).showDfrac()+" \\div "+a(a1,q,(n-1)).showDfrac()+" = "
		+a(a1,q,n).showDfrac()+" \\cdot "+a(a1,q,(n-1)).inverter().showDfrac()+" = "+ q.showDfrac() + "\\\\";
		
//		resolucao += "q="
//		+ "\\dfrac{"+(a(a1,q,n).showDfrac() + "}{" + a(a1,q,(n-1)).showDfrac()) + "}=" + q.showDfrac() + "\\\\";
		
		resolucao += soma.showDfrac() + " = \\dfrac{ x \\cdot ( (" + q.showDfrac() + ")^{"+n+"}-1)}{"+q.showDfrac()+"-1}\\\\";

		Racional parcial=q.pow(new Racional(n));
		parcial.fatoracao(2);

		Racional denominador=q.minus(new Racional(1));
		denominador.fatoracao(2);

		resolucao += soma.showDfrac() + " = \\dfrac{ x \\cdot (" + parcial.showDfrac() + "-1)}{"+denominador.showDfrac()+"}\\\\";

		parcial=parcial.minus(new Racional(1));
		parcial.fatoracao(2);
		
		resolucao += soma.showDfrac() + " \\cdot "+ denominador.showDfrac()+" = x \\cdot " + parcial.showDfrac() + "\\\\";
		Racional parcial2=soma.mult(denominador);
		parcial2.fatoracao(2);
		
//		resolucao +=  parcial.showDfrac() + "x = "+ parcial2.showDfrac() + "\\\\";
		
		String expressao = parcial.toString() + "x = "+parcial2.toString();
		MyExpression myExpression = new MyExpression(expressao);
		resolucao += myExpression.resolverLatex();
		
		return resolucao;
	}
	
	public static String resolucaoSoma4(Racional a1, Racional r, Racional an, int n)
	{
		String resolucao = formulaSoma() + "\\\\";
		Racional soma = ResolucaoPG.soma(a1, an, n);
		resolucao += "n=" + n + ", a_1=" + a1.showDfrac() + ", S_{"+n+"}=" + soma.showDfrac()+", x=a_{"+n+"}\\\\";
		resolucao += soma.showDfrac() + " = \\dfrac{("+a1.showDfrac() + "+x)\\cdot"+n+" }{2}\\\\";

		String expressao = soma.toString() + " * 2 = "+a1.toString()+"*"+n+"+x"+n;
		MyExpression myExpression = new MyExpression(expressao);
		resolucao += myExpression.resolverLatex();

		return resolucao;
	}

	public static Racional soma(Racional a1, Racional q, int n)
	{
		Racional soma= q.pow(new Racional(n)).minus(new Racional(1)).mult(a1).div(q.minus(new Racional(1)));
		soma.fatoracao(2);
		return soma;
	 }

	public static Racional a(Racional a1, Racional r, int n)
	{
		Racional an = a1;
		for(int i = 0; i < n - 1; i++)
		{
			an = an.mult(r);
			an.fatoracao(2);
		}
		
		return an;
	}

	public static String resolucaoSomaInfinita(Racional a1, Racional q)
	{
		String resolucao = formulaSomaInfinita() + "\\\\";
		resolucao += "a_1=" + a1.showDfrac() + ", q=" + q.showDfrac() + "\\\\";

		Racional umMenosQ = new Racional(1).minus(q);
		umMenosQ.fatoracao(2);

		resolucao += "S_\\infty = \\dfrac{" + a1.showDfrac() + "}{1-" + q.showDfrac() + "}\\\\";
		resolucao += "S_\\infty = \\dfrac{" + a1.showDfrac() + "}{" + umMenosQ.showDfrac() + "}\\\\";

		Racional resultado = a1.div(umMenosQ);
		resultado.fatoracao(2);

		resolucao += "S_\\infty = " + a1.showDfrac() + " \\cdot " + umMenosQ.inverter().showDfrac();
		resolucao += " = \\mathbf{" + resultado.showDfrac() + "}";

		return resolucao;
	}

	public static String resolucaoSomaInfinitaFrac(Racional a1, Racional q)
	{
		String resolucao = formulaSomaInfinita() + "\\\\";
		resolucao += "a_1=" + a1.showDfrac() + "\\\\";
		resolucao += "q=" + a(a1, q, 2).showDfrac() + " \\div " + a1.showDfrac() + " = "
				+ a(a1, q, 2).showDfrac() + " \\cdot " + a1.inverter().showDfrac() + " = " + q.showDfrac() + "\\\\";

		Racional umMenosQ = new Racional(1).minus(q);
		umMenosQ.fatoracao(2);

		resolucao += "S_\\infty = \\dfrac{" + a1.showDfrac() + "}{1 - (" + q.showDfrac() + ")}\\\\";
		resolucao += "S_\\infty = \\dfrac{" + a1.showDfrac() + "}{" + umMenosQ.showDfrac() + "}\\\\";
		resolucao += "S_\\infty = " + a1.showDfrac() + " \\cdot " + umMenosQ.inverter().showDfrac();

		Racional resultado = a1.div(umMenosQ);
		resultado.fatoracao(2);

		resolucao += " = \\mathbf{" + resultado.showDfrac() + "}";

		return resolucao;
	}

	public static Racional somaInfinita(Racional a1, Racional q)
	{
		Racional umMenosQ = new Racional(1).minus(q);
		umMenosQ.fatoracao(2);
		Racional res = a1.div(umMenosQ);
		res.fatoracao(2);
		return res;
	}

	public static String resolucaoMediaGeometrica(Racional prev, Racional next, Racional mid)
	{
		String resolucao = ParCor.formula("a_k^2 = a_{k-1} \\cdot a_{k+1}") + "\\\\";

		Racional produto = prev.mult(next);
		produto.fatoracao(2);

		resolucao += "a_k^2 = " + prev.showDfrac() + " \\cdot " + next.showDfrac();
		resolucao += " = " + produto.showDfrac() + "\\\\";
		resolucao += "a_k = \\sqrt{" + produto.showDfrac() + "}";
		resolucao += " = \\mathbf{" + mid.showDfrac() + "}";

		return resolucao;
	}

	public static String resolucaoInterpolacao(Racional a1, Racional an, int k, Racional q)
	{
		String resolucao = ParCor.formula("q = \\sqrt[k+1]{\\dfrac{b}{a}}") + "\\\\";
		resolucao += "a=" + a1.showDfrac() + ", b=" + an.showDfrac() + ", k=" + k + "\\\\";
		resolucao += "q = \\sqrt[" + (k + 1) + "]{\\dfrac{" + an.showDfrac() + "}{" + a1.showDfrac() + "}}\\\\";

		Racional razao = an.div(a1);
		razao.fatoracao(2);

		resolucao += "q = \\sqrt[" + (k + 1) + "]{" + razao.showDfrac() + "}";
		resolucao += " = \\mathbf{" + q.showDfrac() + "}";

		return resolucao;
	}

	public static String formula()
	{
		return ParCor.formula("a_n=a_1 \\cdot q^{(n-1)}");
	}

	public static String formulaSoma()
	{
		return ParCor.formula("S_n=\\dfrac{a_1 \\cdot ( q^n - 1)}{q-1}");
	}

	public static String formulaSomaInfinita()
	{
		return ParCor.formula("S_\\infty=\\dfrac{a_1}{1-q}");
	}
}