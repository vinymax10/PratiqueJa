package matematica.avancado.probabilidade.nivel3package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;
import matematica.Racional;

// Distribuição Binomial: P(X = k) = C(n,k) × p^k × (1-p)^(n-k)
// Cenários com p = 1/2 (moeda) ou p = 1/6 (dado) para que o resultado seja uma fração simples.
public class Exercicio5 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// Tipos de cenário: 0 = moeda p=1/2, 1 = dado p=1/6
		boolean ehMoeda = rand.nextBoolean();

		if (ehMoeda)
			construirMoeda();
		else
			construirDado();
	}

	// P(X = k) com p = 1/2, n ∈ {3,4,5}, k ∈ {1,...,n-1}
	private void construirMoeda()
	{
		int n = 3 + rand.nextInt(3);  // 3, 4 ou 5
		int k = 1 + rand.nextInt(n - 1);  // 1..n-1

		int cnk = combinacao(n, k);
		// P(X=k) = C(n,k) / 2^n
		int den = 1 << n;  // 2^n
		int num = cnk;     // C(n,k) × 1^k × 1^(n-k)

		Racional res = new Racional(num, den);
		String fraRes = res.showDfrac();
		res.fatoracao(2);
		boolean resSimp = res.isSimplificou();

		addParagrafo("Uma moeda honesta é lançada " + n + " vezes."
				+ " Qual a probabilidade de sair cara em exatamente " + k
				+ " lançamento" + (k > 1 ? "s" : "") + "?");

		List<String> distratores = new ArrayList<>();
		// d1: k+1 caras
		int numD1 = combinacao(n, Math.min(k + 1, n));
		Racional d1 = new Racional(numD1, den); d1.fatoracao(2);
		distratores.add("\\(" + d1.showDfrac() + "\\)");
		// d2: esquece o C(n,k) — apenas 1/2^n
		Racional d2 = new Racional(1, den); d2.fatoracao(2);
		distratores.add("\\(" + d2.showDfrac() + "\\)");
		// d3: k/n
		Racional d3 = new Racional(k, n); d3.fatoracao(2);
		distratores.add("\\(" + d3.showDfrac() + "\\)");
		embaralharEAdicionarAlternativas("\\(" + res.showDfrac() + "\\)", distratores);

		String resolucao = "Usando a distribuição binomial \\(P(X=k) = C_{n,k} \\cdot p^k \\cdot (1-p)^{n-k}\\):\\(\\\\\\)";
		resolucao += "\\(n = " + n + ", \\quad k = " + k + ", \\quad p = \\dfrac{1}{2} \\\\";
		resolucao += "C_{" + n + "," + k + "} = \\dfrac{" + n + "!}{" + k + "! \\cdot " + (n - k) + "!} = " + cnk + " \\\\";
		resolucao += "P(X=" + k + ") = " + cnk + " \\cdot \\left(\\dfrac{1}{2}\\right)^{" + k + "} \\cdot \\left(\\dfrac{1}{2}\\right)^{" + (n - k) + "} \\\\ ";
		resolucao += "= \\dfrac{" + cnk + "}{" + den + "}";
		if (resSimp)
			resolucao += " = \\mathbf{" + res.showDfrac() + "}\\)";
		else
			resolucao += " = \\mathbf{" + fraRes + "}\\)";
		setResolucao(resolucao);
	}

	// P(X = k) com p = 1/6 (sair face específica em dado), n ∈ {2,3}, k ∈ {1}
	private void construirDado()
	{
		int n = 2 + rand.nextInt(2);  // 2 ou 3
		int k = 1;  // exatamente 1 vez (valores simples)
		int face = 1 + rand.nextInt(6);

		// P = C(n,1) × (1/6)^1 × (5/6)^(n-1)
		int cnk = n;  // C(n,1) = n
		long num5 = pow5(n - 1);  // 5^(n-1)
		long den6 = pow6(n);      // 6^n

		long num = cnk * num5;
		Racional res = new Racional((int) num, (int) den6);
		String fraRes = res.showDfrac();
		res.fatoracao(2);
		boolean resSimp = res.isSimplificou();

		addParagrafo("Um dado honesto de seis faces é lançado " + n + " vezes."
				+ " Qual a probabilidade de o número " + face
				+ " aparecer em exatamente 1 lançamento?");

		List<String> distratores = new ArrayList<>();
		// d1: esquece o C(n,1) — usa (1/6)×(5/6)^(n-1)
		Racional d1 = new Racional((int) num5, (int) den6); d1.fatoracao(2);
		distratores.add("\\(" + d1.showDfrac() + "\\)");
		// d2: P(sair ao menos uma vez) = 1 - (5/6)^n
		long favAoMenos = (long) den6 - pow5(n);
		Racional d2 = new Racional((int) favAoMenos, (int) den6); d2.fatoracao(2);
		distratores.add("\\(" + d2.showDfrac() + "\\)");
		// d3: n/6
		Racional d3 = new Racional(n, 6); d3.fatoracao(2);
		distratores.add("\\(" + d3.showDfrac() + "\\)");
		embaralharEAdicionarAlternativas("\\(" + res.showDfrac() + "\\)", distratores);

		String p5str = n == 2 ? "\\dfrac{5}{6}" : "\\left(\\dfrac{5}{6}\\right)^2";
		String resolucao = "Usando a distribuição binomial \\(P(X=k) = C_{n,k} \\cdot p^k \\cdot (1-p)^{n-k}\\):\\(\\\\\\)";
		resolucao += "\\(n = " + n + ", \\quad k = 1, \\quad p = \\dfrac{1}{6} \\\\";
		resolucao += "C_{" + n + ",1} = " + cnk + " \\\\";
		resolucao += "P(X=1) = " + cnk + " \\cdot \\dfrac{1}{6} \\cdot " + p5str + " \\\\ ";
		resolucao += "= \\dfrac{" + num + "}{" + den6 + "}";
		if (resSimp)
			resolucao += " = \\mathbf{" + res.showDfrac() + "}\\)";
		else
			resolucao += " = \\mathbf{" + fraRes + "}\\)";
		setResolucao(resolucao);
	}

	private static int combinacao(int n, int k)
	{
		if (k == 0 || k == n) return 1;
		if (k > n - k) k = n - k;
		long r = 1;
		for (int i = 0; i < k; i++)
			r = r * (n - i) / (i + 1);
		return (int) r;
	}

	private static long pow5(int exp)
	{
		long r = 1;
		for (int i = 0; i < exp; i++) r *= 5;
		return r;
	}

	private static long pow6(int exp)
	{
		long r = 1;
		for (int i = 0; i < exp; i++) r *= 6;
		return r;
	}
}
