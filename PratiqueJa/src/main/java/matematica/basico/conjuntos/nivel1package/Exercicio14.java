package matematica.basico.conjuntos.nivel1package;

import matematica.GeradorExercicio;
import matematica.basico.conjuntos.Conjunto;
import java.util.ArrayList;
import java.util.List;

public class Exercicio14 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int sizeA = 4 + rand.nextInt(4); // 4..7 elementos
		Conjunto a = new Conjunto(sizeA, 50);
		int pertence = a.getAle();

		List<Integer> naoMembros = new ArrayList<>();
		while (naoMembros.size() < 3)
		{
			int c = rand.nextInt(100);
			if (!a.contem(c) && !naoMembros.contains(c))
				naoMembros.add(c);
		}

		addParagrafo("Qual dos elementos abaixo pertence ao conjunto \\(A\\)?");
		addParagrafo("\\(A = " + a + "\\)");

		List<String> distratores = new ArrayList<>();
		for (int d : naoMembros)
			distratores.add(formatarNumero(d));
		embaralharEAdicionarAlternativas(formatarNumero(pertence), distratores);

		String res = "Um elemento pertence a \\(A\\) se e somente se está listado no conjunto. \\(\\\\\\)";
		res += "\\(" + pertence + " \\in A\\), pois \\(" + pertence + "\\) aparece em \\(A = " + a + "\\).";
		setResolucao(res);
	}
}
