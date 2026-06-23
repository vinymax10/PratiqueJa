package matematica.basico.numerosprimos.nivel3package;

import java.util.ArrayList;
import java.util.List;

import matematica.basico.numerosprimos.AgrupadorPrimo;

// Dado N = p × q, qual número é coprimo com N? (segunda variação)
public class Primo8 extends AgrupadorPrimo
{
	@Override
	protected void construir()
	{
		int p = LISTA_PRIMOS[1 + rand.nextInt(3)]; // 3,5,7
		int q = LISTA_PRIMOS[4 + rand.nextInt(3)]; // 11,13,17
		int n = p * q;

		int r1 = 0, r2 = 0;
		for(int primo : LISTA_PRIMOS)
		{
			if(primo == p || primo == q) continue;
			if(r1 == 0) { r1 = primo; continue; }
			if(r2 == 0) { r2 = primo; break; }
		}

		int correto = r1; // primo distinto de p e q → MDC = 1
		List<String> erradas = new ArrayList<>();
		for(int e : new int[]{p * r1, q * r2, p * r2})
		{
			String s = "\\(" + e + "\\)";
			if(e != correto && !erradas.contains(s)) erradas.add(s);
		}
		for(int extra = 3; erradas.size() < 3; extra++)
		{
			int cand = p * extra;
			String s = "\\(" + cand + "\\)";
			if(cand != correto && !erradas.contains(s) && mdc(n, cand) > 1) erradas.add(s);
		}

		addParagrafo("Dado \\(N = " + p + " \\times " + q + " = " + n + "\\), qual dos números " + listarOpcoes("\\(" + correto + "\\)", erradas) + " é coprimo com \\(N\\) (\\(\\text{MDC} = 1\\))?");
		embaralharEAdicionarAlternativas("\\(" + correto + "\\)", erradas);

		addResolucao("\\(N = " + p + " \\times " + q + " \\Rightarrow\\) fatores primos de \\(N\\): \\(\\{" + p + ",\\," + q + "\\}\\).");
		addResolucao("\\(" + correto + "\\) não tem \\(" + p + "\\) nem \\(" + q + "\\) como fator.");
		addResolucao("MDC\\((" + n + ",\\," + correto + ") = \\mathbf{1} \\Rightarrow\\) coprimos.");
	}
}
