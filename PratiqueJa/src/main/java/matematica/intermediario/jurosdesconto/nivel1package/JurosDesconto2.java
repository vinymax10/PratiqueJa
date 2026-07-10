package matematica.intermediario.jurosdesconto.nivel1package;

import matematica.GeradorExercicio;
import matematica.ParCor;

public class JurosDesconto2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int iMensal = 1 + rand.nextInt(5);
		int tipo = rand.nextInt(6);

		String pergunta, resultado;
		int i1, i2;
		String t1Label, t2Label;
		String fatorNum, fatorDen;

		switch(tipo)
		{
			case 0:
			{
				int iAnual = iMensal * 12;
				pergunta = "Uma taxa de " + iMensal + "% ao mês é proporcional a quantos % ao ano?";
				resultado = iAnual + "\\%";
				i1 = iMensal; i2 = iAnual;
				t1Label = "1\\text{ mês}"; t2Label = "12\\text{ meses}";
				fatorNum = "12"; fatorDen = "1";
				break;
			}
			case 1:
			{
				int iAnual = iMensal * 12;
				pergunta = "Uma taxa de " + iAnual + "% ao ano é proporcional a quantos % ao mês?";
				resultado = iMensal + "\\%";
				i1 = iAnual; i2 = iMensal;
				t1Label = "12\\text{ meses}"; t2Label = "1\\text{ mês}";
				fatorNum = "1"; fatorDen = "12";
				break;
			}
			case 2:
			{
				int iTrimestral = iMensal * 3;
				pergunta = "Uma taxa de " + iMensal + "% ao mês é proporcional a quantos % ao trimestre?";
				resultado = iTrimestral + "\\%";
				i1 = iMensal; i2 = iTrimestral;
				t1Label = "1\\text{ mês}"; t2Label = "3\\text{ meses}";
				fatorNum = "3"; fatorDen = "1";
				break;
			}
			case 3:
			{
				int iTrimestral = iMensal * 3;
				pergunta = "Uma taxa de " + iTrimestral + "% ao trimestre é proporcional a quantos % ao mês?";
				resultado = iMensal + "\\%";
				i1 = iTrimestral; i2 = iMensal;
				t1Label = "3\\text{ meses}"; t2Label = "1\\text{ mês}";
				fatorNum = "1"; fatorDen = "3";
				break;
			}
			case 4:
			{
				int iSemestral = iMensal * 6;
				pergunta = "Uma taxa de " + iMensal + "% ao mês é proporcional a quantos % ao semestre?";
				resultado = iSemestral + "\\%";
				i1 = iMensal; i2 = iSemestral;
				t1Label = "1\\text{ mês}"; t2Label = "6\\text{ meses}";
				fatorNum = "6"; fatorDen = "1";
				break;
			}
			default:
			{
				int iSemestral = iMensal * 6;
				pergunta = "Uma taxa de " + iSemestral + "% ao semestre é proporcional a quantos % ao mês?";
				resultado = iMensal + "\\%";
				i1 = iSemestral; i2 = iMensal;
				t1Label = "6\\text{ meses}"; t2Label = "1\\text{ mês}";
				fatorNum = "1"; fatorDen = "6";
				break;
			}
		}

		addParagrafo(pergunta);
		gerarAlternativas(resultado);

		addResolucao("\\(" + ParCor.formula("i_2 = i_1 \\cdot \\dfrac{t_2}{t_1}") + "\\)");
		addResolucao("\\(i_1=" + i1 + "\\%, \\quad t_1=" + t1Label + ", \\quad t_2=" + t2Label + "\\)");
		if(fatorDen.equals("1"))
			addResolucao("\\(i_2=" + i1 + "\\% \\cdot \\dfrac{" + fatorNum + "}{" + fatorDen + "} = \\mathbf{" + i2 + "\\%}\\)");
		else
			addResolucao("\\(i_2=" + i1 + "\\% \\cdot \\dfrac{" + fatorNum + "}{" + fatorDen
				+ "} = \\dfrac{" + i1 + "}{" + fatorDen + "} = \\mathbf{" + i2 + "\\%}\\)");
	}
}
