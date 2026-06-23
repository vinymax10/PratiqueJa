package matematica.basico.numerosprimos.nivel1package;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import matematica.basico.numerosprimos.AgrupadorPrimo;

// Qual dos números abaixo é primo? (múltipla escolha)
public class Primo10 extends AgrupadorPrimo
{
	@Override
	protected void construir()
	{
		int primo = LISTA_PRIMOS[rand.nextInt(LISTA_PRIMOS.length)];

		List<String> erradas = new ArrayList<>();
		Set<Integer> usados = new HashSet<>();
		usados.add(primo);
		while(erradas.size() < 3)
		{
			int c = 4 + rand.nextInt(46);
			if(!ehPrimo(c) && usados.add(c))
				erradas.add("\\(" + c + "\\)");
		}

		addParagrafo("Qual dos números " + listarOpcoes("\\(" + primo + "\\)", erradas) + " é primo?");
		embaralharEAdicionarAlternativas("\\(" + primo + "\\)", erradas);

		addResolucao("Um número primo tem apenas dois divisores: \\(1\\) e ele mesmo.");
		addResolucao("\\(" + primo + "\\) atende a isso; os demais são compostos (têm outros divisores).");
		addResolucao("Resposta: \\(\\mathbf{" + primo + "}\\).");
	}
}
