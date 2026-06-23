package matematica.avancado.trigoadicao.nivel1package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;

// Identificação: qual expansão corresponde a sen(α±β) ou cos(α±β)?
public class Exercicio9 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// As quatro fórmulas de adição/subtração
		String fSenMais  = "\\(\\operatorname{sen}\\,\\alpha\\cos\\beta + \\cos\\alpha\\,\\operatorname{sen}\\,\\beta\\)";
		String fSenMenos = "\\(\\operatorname{sen}\\,\\alpha\\cos\\beta - \\cos\\alpha\\,\\operatorname{sen}\\,\\beta\\)";
		String fCosMenos = "\\(\\cos\\alpha\\cos\\beta - \\operatorname{sen}\\,\\alpha\\,\\operatorname{sen}\\,\\beta\\)";
		String fCosMais  = "\\(\\cos\\alpha\\cos\\beta + \\operatorname{sen}\\,\\alpha\\,\\operatorname{sen}\\,\\beta\\)";

		int choice = rand.nextInt(4);

		String formula, correta, regra;
		List<String> distratores = new ArrayList<>();

		if (choice == 0) {
			formula    = "\\(\\operatorname{sen}(\\alpha+\\beta)\\)";
			correta    = fSenMais;
			regra      = "\\(\\operatorname{sen}(\\alpha+\\beta) = \\operatorname{sen}\\,\\alpha\\cos\\beta + \\cos\\alpha\\,\\operatorname{sen}\\,\\beta\\)";
			distratores.add(fSenMenos);
			distratores.add(fCosMenos);
			distratores.add(fCosMais);
		} else if (choice == 1) {
			formula    = "\\(\\operatorname{sen}(\\alpha-\\beta)\\)";
			correta    = fSenMenos;
			regra      = "\\(\\operatorname{sen}(\\alpha-\\beta) = \\operatorname{sen}\\,\\alpha\\cos\\beta - \\cos\\alpha\\,\\operatorname{sen}\\,\\beta\\)";
			distratores.add(fSenMais);
			distratores.add(fCosMenos);
			distratores.add(fCosMais);
		} else if (choice == 2) {
			formula    = "\\(\\cos(\\alpha+\\beta)\\)";
			correta    = fCosMenos;
			regra      = "\\(\\cos(\\alpha+\\beta) = \\cos\\alpha\\cos\\beta - \\operatorname{sen}\\,\\alpha\\,\\operatorname{sen}\\,\\beta\\)";
			distratores.add(fSenMais);
			distratores.add(fSenMenos);
			distratores.add(fCosMais);
		} else {
			formula    = "\\(\\cos(\\alpha-\\beta)\\)";
			correta    = fCosMais;
			regra      = "\\(\\cos(\\alpha-\\beta) = \\cos\\alpha\\cos\\beta + \\operatorname{sen}\\,\\alpha\\,\\operatorname{sen}\\,\\beta\\)";
			distratores.add(fSenMais);
			distratores.add(fSenMenos);
			distratores.add(fCosMenos);
		}

		addParagrafo("Qual é a expansão de " + formula + "?");
		embaralharEAdicionarAlternativas(correta, distratores);

		addResolucao("Pela fórmula de adição/subtração trigonométrica:");
		addResolucao(regra);
	}
}
