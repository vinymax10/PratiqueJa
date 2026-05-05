package Matematica.Basico.ExpressoesNumericas.Nivel3Package;

import Auxiliar.Algebra;
import Matematica.ExpressaoExt;
import Matematica.Racional;
import Modelo.Matematica.Conta;

public class Expressao1 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Expressao1(int indice)
	{
		super(indice);

		int size = 5;
		Racional[] coeficientes = new Racional[size];

		String ope1 = Algebra.sinalPlusMinus();
		String ope2 = Algebra.sinalMenosDiv();
		String ope3 = Algebra.sinalMenosDiv();
		String ope4 = Algebra.sinal();

		String exp, expLatex;

		exp = "(((A" + ope1 + "B)" + ope2 + "C)" + ope3 + "D)" + ope4 + "E";

		if(ope4.equals("/"))
			expLatex = "[(A" + ope1 + "B)" + ope2 + "C]" + ope3 + "D" + ope4 + "E";
		else
			expLatex = "\\{[(A" + ope1 + "B)" + ope2 + "C]" + ope3 + "D\\}" + ope4 + "E";

//		gerando os coeficientes;
		for(int i = 0; i < size; i++)
			coeficientes[i] = new Racional(1 + rand.nextInt(20));
//		-------------------------		

//		evitando coeficientes iguais

		textLatex = Algebra.gerarTextLatexEN(expLatex, coeficientes);

		ExpressaoExt expressao;
		Racional resultado = null;
		try
		{
			expressao = new ExpressaoExt(exp, coeficientes);
			resultado = expressao.calcular();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		resultadoCorreto = "" + resultado.toString();
	}

	public static void main(String[] args)
	{
		for(int i = 0; i < 1000; i++)
			new Expressao1(1);
	}
}
