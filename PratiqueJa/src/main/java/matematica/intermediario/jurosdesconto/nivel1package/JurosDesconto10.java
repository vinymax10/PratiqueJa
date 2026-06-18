package matematica.intermediario.jurosdesconto.nivel1package;

import matematica.GeradorExercicio;
import matematica.ParCor;

public class JurosDesconto10 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int iMensal = 1 + rand.nextInt(5);
		int tipo = rand.nextInt(6);

		String pergunta, resultado, resolucao;
		String formula = ParCor.formula("i_2 = i_1 \\cdot \\dfrac{t_2}{t_1}");

		switch(tipo)
		{
			case 0:
			{
				int iAnual = iMensal * 12;
				pergunta = "Uma taxa de " + iMensal + "% ao mês é proporcional a quantos % ao ano?";
				resultado = iAnual + "\\%";
				resolucao = formula + "\\\\";
				resolucao += "i_1=" + iMensal + "\\%, \\quad t_1=1\\text{ mês}, \\quad t_2=12\\text{ meses}\\\\";
				resolucao += "i_2=" + iMensal + "\\% \\cdot \\dfrac{12}{1} = " + iAnual + "\\%";
				break;
			}
			case 1:
			{
				int iAnual = iMensal * 12;
				pergunta = "Uma taxa de " + iAnual + "% ao ano é proporcional a quantos % ao mês?";
				resultado = iMensal + "\\%";
				resolucao = formula + "\\\\";
				resolucao += "i_1=" + iAnual + "\\%, \\quad t_1=12\\text{ meses}, \\quad t_2=1\\text{ mês}\\\\";
				resolucao += "i_2=" + iAnual + "\\% \\cdot \\dfrac{1}{12} = \\dfrac{" + iAnual + "}{12} = " + iMensal + "\\%";
				break;
			}
			case 2:
			{
				int iTrimestral = iMensal * 3;
				pergunta = "Uma taxa de " + iMensal + "% ao mês é proporcional a quantos % ao trimestre?";
				resultado = iTrimestral + "\\%";
				resolucao = formula + "\\\\";
				resolucao += "i_1=" + iMensal + "\\%, \\quad t_1=1\\text{ mês}, \\quad t_2=3\\text{ meses}\\\\";
				resolucao += "i_2=" + iMensal + "\\% \\cdot \\dfrac{3}{1} = " + iTrimestral + "\\%";
				break;
			}
			case 3:
			{
				int iTrimestral = iMensal * 3;
				pergunta = "Uma taxa de " + iTrimestral + "% ao trimestre é proporcional a quantos % ao mês?";
				resultado = iMensal + "\\%";
				resolucao = formula + "\\\\";
				resolucao += "i_1=" + iTrimestral + "\\%, \\quad t_1=3\\text{ meses}, \\quad t_2=1\\text{ mês}\\\\";
				resolucao += "i_2=" + iTrimestral + "\\% \\cdot \\dfrac{1}{3} = \\dfrac{" + iTrimestral + "}{3} = " + iMensal + "\\%";
				break;
			}
			case 4:
			{
				int iSemestral = iMensal * 6;
				pergunta = "Uma taxa de " + iMensal + "% ao mês é proporcional a quantos % ao semestre?";
				resultado = iSemestral + "\\%";
				resolucao = formula + "\\\\";
				resolucao += "i_1=" + iMensal + "\\%, \\quad t_1=1\\text{ mês}, \\quad t_2=6\\text{ meses}\\\\";
				resolucao += "i_2=" + iMensal + "\\% \\cdot \\dfrac{6}{1} = " + iSemestral + "\\%";
				break;
			}
			default:
			{
				int iSemestral = iMensal * 6;
				pergunta = "Uma taxa de " + iSemestral + "% ao semestre é proporcional a quantos % ao mês?";
				resultado = iMensal + "\\%";
				resolucao = formula + "\\\\";
				resolucao += "i_1=" + iSemestral + "\\%, \\quad t_1=6\\text{ meses}, \\quad t_2=1\\text{ mês}\\\\";
				resolucao += "i_2=" + iSemestral + "\\% \\cdot \\dfrac{1}{6} = \\dfrac{" + iSemestral + "}{6} = " + iMensal + "\\%";
				break;
			}
		}

		addParagrafo(pergunta);
		gerarAlternativas(resultado);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
