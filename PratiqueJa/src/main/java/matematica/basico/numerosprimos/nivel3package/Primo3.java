package matematica.basico.numerosprimos.nivel3package;

import java.util.ArrayList;
import java.util.List;

import matematica.basico.numerosprimos.AgrupadorPrimo;

// Dado N = p × q, qual dos números abaixo é coprimo com N (MDC = 1)?
public class Primo3 extends AgrupadorPrimo
{
	@Override
	protected void construir()
	{
		int p = LISTA_PRIMOS[rand.nextInt(3)];     // 2, 3 ou 5
		int q = LISTA_PRIMOS[3 + rand.nextInt(4)]; // 7, 11, 13 ou 17
		int n = p * q;

		// r1 e r2: dois menores primos distintos de p e q
		int r1 = 0, r2 = 0;
		for(int primo : LISTA_PRIMOS)
		{
			if(primo == p || primo == q) continue;
			if(r1 == 0) { r1 = primo; continue; }
			if(r2 == 0) { r2 = primo; break; }
		}

		// Resposta correta: r1 (primo que não é p nem q → MDC(n, r1) = 1)
		int correto = r1;

		// Distradores: números que compartilham fator com n
		// e1 = p × r1 → tem fator p em comum com n
		// e2 = q × r2 → tem fator q em comum com n
		// e3 = p × r2 → tem fator p em comum com n
		int e1 = p * r1;
		int e2 = q * r2;
		int e3 = p * r2;

		List<String> erradas = new ArrayList<>();
		for(int e : new int[]{e1, e2, e3})
		{
			String s = "\\(" + e + "\\)";
			if(e != correto && !erradas.contains(s))
				erradas.add(s);
		}
		// Fallback em caso de colisão improvável
		for(int extra = 3; erradas.size() < 3; extra++)
		{
			int cand = p * extra;
			String s = "\\(" + cand + "\\)";
			if(cand != correto && !erradas.contains(s) && mdc(n, cand) > 1)
				erradas.add(s);
		}

		addParagrafo(
			"Dado \\(N = " + p + " \\times " + q + " = " + n + "\\), " +
			"qual dos números abaixo é coprimo com \\(N\\) (\\(\\text{MDC} = 1\\))?"
		);
		embaralharEAdicionarAlternativas("\\(" + correto + "\\)", erradas);

		setResolucao(
			"\\(\\begin{aligned}" +
			"& N = " + p + " \\times " + q +
			" \\Rightarrow \\text{fatores primos de } N\\text{: }\\{" + p + ",\\," + q + "\\}\\\\" +
			"& " + correto + "\\text{ não tem } " + p + "\\text{ nem } " + q + "\\text{ como fator}\\\\" +
			"& \\text{MDC}(" + n + ",\\," + correto + ") = \\mathbf{1}" +
			" \\Rightarrow \\text{coprimos}" +
			"\\end{aligned}\\)"
		);
	}
}
