package matematica.basico.numerosprimos.nivel1package;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import matematica.basico.numerosprimos.AgrupadorPrimo;

// Qual dos números abaixo é composto? (múltipla escolha)
public class Primo11 extends AgrupadorPrimo
{
	@Override
	protected void construir()
	{
		int composto;
		do { composto = 4 + rand.nextInt(46); } while(ehPrimo(composto));

		List<String> erradas = new ArrayList<>();
		Set<Integer> usados = new HashSet<>();
		usados.add(composto);
		while(erradas.size() < 3)
		{
			int p = LISTA_PRIMOS[rand.nextInt(LISTA_PRIMOS.length)];
			if(usados.add(p))
				erradas.add("\\(" + p + "\\)");
		}

		int menorFator = fatorar(composto).keySet().iterator().next();

		addParagrafo("Qual dos números " + listarOpcoes("\\(" + composto + "\\)", erradas) + " é composto?");
		embaralharEAdicionarAlternativas("\\(" + composto + "\\)", erradas);

		addResolucao("Um número composto tem outros divisores além de \\(1\\) e dele mesmo.");
		addResolucao("\\(" + composto + " \\div " + menorFator + " = " + (composto / menorFator) + "\\) (exato), logo é divisível por \\(" + menorFator + "\\).");
		addResolucao("Resposta: \\(\\mathbf{" + composto + "}\\).");
	}
}
