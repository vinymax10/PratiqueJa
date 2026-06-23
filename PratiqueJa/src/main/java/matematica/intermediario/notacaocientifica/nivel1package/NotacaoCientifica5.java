package matematica.intermediario.notacaocientifica.nivel1package;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import matematica.GeradorExercicio;

// Comparação de grandezas: identificar o maior número entre quatro em NC
public class NotacaoCientifica5 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int base = 3 + rand.nextInt(5); // expoente mínimo: 3..7
		// quatro expoentes consecutivos: base, base+1, base+2, base+3
		int[] exps = {base, base + 1, base + 2, base + 3};
		int[] mant = new int[4];
		for (int i = 0; i < 4; i++)
			mant[i] = 1 + rand.nextInt(9);

		// maior é sempre o de expoente base+3, independente da mantissa
		// (10^{base+3} > 9 × 10^{base+2} = 9 × 10^{base+2} < 10^{base+3})
		String maior = "\\(" + mant[3] + " \\times 10^{" + exps[3] + "}\\)";
		String e1   = "\\(" + mant[0] + " \\times 10^{" + exps[0] + "}\\)";
		String e2   = "\\(" + mant[1] + " \\times 10^{" + exps[1] + "}\\)";
		String e3   = "\\(" + mant[2] + " \\times 10^{" + exps[2] + "}\\)";

		List<String> opcoes = new ArrayList<>(Arrays.asList(maior, e1, e2, e3));
		Collections.shuffle(opcoes, rand);
		StringBuilder numeros = new StringBuilder();
		for (int i = 0; i < opcoes.size(); i++)
		{
			if (i > 0) numeros.append(i == opcoes.size() - 1 ? " e " : ", ");
			numeros.append(opcoes.get(i));
		}

		addParagrafo("Qual é o maior número entre " + numeros + "?");
		embaralharEAdicionarAlternativas(maior, Arrays.asList(e1, e2, e3));
		addResolucao("Comparar pelos expoentes: maior expoente corresponde ao maior número.");
		addResolucao("\\(10^{" + exps[3] + "} > 10^{" + exps[2] + "} > 10^{" + exps[1] + "} > 10^{" + exps[0] + "}\\)");
		addResolucao("O maior expoente é " + exps[3] + ", portanto o maior número é:");
		addResolucao("\\(\\mathbf{" + mant[3] + " \\times 10^{" + exps[3] + "}}\\)");
	}
}
