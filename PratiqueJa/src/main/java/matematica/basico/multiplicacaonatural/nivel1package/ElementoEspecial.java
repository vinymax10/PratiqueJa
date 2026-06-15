package matematica.basico.multiplicacaonatural.nivel1package;

import java.util.ArrayList;
import java.util.List;

import matematica.GeradorExercicio;

public class ElementoEspecial extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int algo = 10 + rand.nextInt(491);
		boolean neutro = rand.nextBoolean();

		if (neutro)
		{
			addParagrafo("Qual é o valor de \\(\\square\\) na equação"
				+ " \\(\\square \\times " + algo + " = " + algo + "\\)?");

			List<String> distrais = new ArrayList<>();
			distrais.add(formatarNumero(0));
			distrais.add(formatarNumero(2));
			distrais.add(formatarNumero(algo));
			embaralharEAdicionarAlternativas(formatarNumero(1), distrais);

			String res = "O elemento neutro da multiplicação é \\(1\\):"
				+ " qualquer número multiplicado por \\(1\\) é ele mesmo. \\(\\\\\\)";
			res += "\\(\\square \\times " + algo + " = " + algo
				+ " \\Rightarrow \\square = \\mathbf{1}\\)";
			setResolucao(res);
		}
		else
		{
			addParagrafo("Qual é o valor de \\(\\square\\) na equação"
				+ " \\(\\square \\times " + algo + " = 0\\)?");

			List<String> distrais = new ArrayList<>();
			distrais.add(formatarNumero(1));
			distrais.add(formatarNumero(algo));
			distrais.add(formatarNumero(algo + 1));
			embaralharEAdicionarAlternativas(formatarNumero(0), distrais);

			String res = "O elemento absorvente da multiplicação é \\(0\\):"
				+ " qualquer número multiplicado por \\(0\\) é \\(0\\). \\(\\\\\\)";
			res += "\\(\\square \\times " + algo + " = 0"
				+ " \\Rightarrow \\square = \\mathbf{0}\\)";
			setResolucao(res);
		}
	}
}
