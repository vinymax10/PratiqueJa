package matematica.basico.regratres.nivel1package;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import matematica.GeradorExercicio;

// Conversão de número decimal para porcentagem
public class RegraTres10 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int centesimos = 1 + rand.nextInt(99); // 1..99
		String decimal = "0{,}" + (centesimos < 10 ? "0" + centesimos : "" + centesimos);
		int p = centesimos;

		addParagrafo("Escreva o número decimal \\(" + decimal + "\\) na forma de porcentagem.");

		List<String> distratores = new ArrayList<>();
		Set<Integer> usados = new HashSet<>();
		usados.add(p);
		int[] tentativas = {5, -5, 10, -10, 1, -1, 20, -20};
		for(int delta : tentativas)
		{
			if(distratores.size() >= 3) break;
			int alt = p + delta;
			if(alt > 0 && alt < 100 && usados.add(alt))
				distratores.add("\\(" + alt + "\\%\\)");
		}
		embaralharEAdicionarAlternativas("\\(" + p + "\\%\\)", distratores);

		String res = "Para converter um decimal em porcentagem, multiplicamos por 100 (deslocamos a vírgula duas casas): \\(\\\\\\)";
		res += "\\(" + decimal + " \\times 100 = \\mathbf{" + p + "\\%}\\)";
		setResolucao(res);
	}
}
