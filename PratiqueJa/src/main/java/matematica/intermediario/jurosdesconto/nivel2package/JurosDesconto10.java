package matematica.intermediario.jurosdesconto.nivel2package;

import matematica.GeradorExercicio;
import matematica.ParCor;
import matematica.Racional;

public class JurosDesconto10 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// Gera A primeiro (múltiplo de 200) e i ∈ {5%,10%,20%} para garantir N inteiro
		Racional a = new Racional((1 + rand.nextInt(50)) * 200);
		int[] iOpcoes = {5, 10, 20};
		int iNum = iOpcoes[rand.nextInt(3)];
		Racional i = new Racional(iNum, 100);
		Racional t = new Racional(1 + rand.nextInt(4));

		Racional it = i.mult(t);
		it.fatoracao(2);
		Racional denominador = new Racional(1).add(it);
		denominador.fatoracao(2);
		Racional n = a.mult(denominador);
		n.fatoracao(2);
		Racional d = n.minus(a);
		d.fatoracao(2);

		int tipo = rand.nextInt(3);

		String formulaA = ParCor.formula("A = \\dfrac{N}{1 + i \\cdot t}");
		String formulaD = ParCor.formula("D = N - A");
		String resolucao = formulaA + "\\\\";

		switch(tipo)
		{
			case 0:
				addParagrafo("Um título de R$ " + n + ",00 é descontado racionalmente à taxa de "
						+ i.porcentagem() + "% ao mês por " + t + " meses. Qual é o valor atual?");
				gerarAlternativas(a.toString());
				resolucao += "N=" + n + ", \\quad i=" + i.porcentagem() + "\\%, \\quad t=" + t + "\\\\";
				resolucao += "A=\\dfrac{" + n + "}{1+" + i.showDfrac() + " \\cdot " + t + "}\\\\";
				resolucao += "A=\\dfrac{" + n + "}{1+" + it.showDfrac() + "}\\\\";
				resolucao += "A=\\dfrac{" + n + "}{" + denominador.showDfrac() + "}=" + a.showDfrac();
				break;

			case 1:
				addParagrafo("O valor atual de um título é R$ " + a + ",00 após desconto racional de "
						+ i.porcentagem() + "% ao mês por " + t + " meses. Qual é o valor nominal?");
				gerarAlternativas(n.toString());
				resolucao += "A=" + a + ", \\quad i=" + i.porcentagem() + "\\%, \\quad t=" + t + "\\\\";
				resolucao += "N=A \\cdot (1+i \\cdot t)\\\\";
				resolucao += "N=" + a + " \\cdot (1+" + i.showDfrac() + " \\cdot " + t + ")\\\\";
				resolucao += "N=" + a + " \\cdot " + denominador.showDfrac() + "=" + n.showDfrac();
				break;

			default:
				addParagrafo("Um título de R$ " + n + ",00 é descontado racionalmente à taxa de "
						+ i.porcentagem() + "% ao mês por " + t + " meses. Qual é o valor do desconto?");
				gerarAlternativas(d.toString());
				resolucao += "N=" + n + ", \\quad i=" + i.porcentagem() + "\\%, \\quad t=" + t + "\\\\";
				resolucao += "A=\\dfrac{" + n + "}{" + denominador.showDfrac() + "}=" + a.showDfrac() + "\\\\";
				resolucao += formulaD + "\\\\";
				resolucao += "D=" + n + "-" + a.showDfrac() + "=" + d.showDfrac();
				break;
		}

		setResolucao("\\(" + resolucao + "\\)");
	}
}
