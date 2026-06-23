package matematica.basico.racionais;

import java.util.ArrayList;
import java.util.List;

import matematica.GeradorExercicio;
import matematica.Racional;

// Base com helpers de exercício para os geradores de racionais (Fase 2).
// Usa instâncias frescas de Racional nas operações (que mutam) e monta o
// enunciado a partir dos inteiros, evitando contaminação de estado.
public abstract class AgrupadorRacional extends GeradorExercicio
{
	protected void soma(int maxNum, int maxDen)
	{
		int a = 1 + rand.nextInt(maxNum), b = 2 + rand.nextInt(maxDen - 1);
		int c = 1 + rand.nextInt(maxNum), d = 2 + rand.nextInt(maxDen - 1);
		String[] resol = ResolucaoRacionais.resolucaoCompleta(a, b, c, d, true);
		Racional res = new Racional(a, b).add(new Racional(c, d));
		res.fatoracao(2);
		addParagrafo("Calcule:");
		addParagrafo("\\(\\dfrac{" + a + "}{" + b + "} + \\dfrac{" + c + "}{" + d + "} =\\)");
		gerarAlternativas(res.toString());
		for(String passo : resol)
			addResolucao("\\(" + passo + "\\)");
	}

	protected void subtracao(int maxNum, int maxDen)
	{
		int a, b, c, d;
		do
		{
			a = 1 + rand.nextInt(maxNum); b = 2 + rand.nextInt(maxDen - 1);
			c = 1 + rand.nextInt(maxNum); d = 2 + rand.nextInt(maxDen - 1);
		}
		while(a * d - c * b <= 0); // garante resultado positivo
		String[] resol = ResolucaoRacionais.resolucaoCompleta(a, b, c, d, false);
		Racional res = new Racional(a, b).minus(new Racional(c, d));
		res.fatoracao(2);
		addParagrafo("Calcule:");
		addParagrafo("\\(\\dfrac{" + a + "}{" + b + "} - \\dfrac{" + c + "}{" + d + "} =\\)");
		gerarAlternativas(res.toString());
		for(String passo : resol)
			addResolucao("\\(" + passo + "\\)");
	}

	protected void multiplicacao(int maxNum, int maxDen)
	{
		int a = 1 + rand.nextInt(maxNum), b = 2 + rand.nextInt(maxDen - 1);
		int c = 1 + rand.nextInt(maxNum), d = 2 + rand.nextInt(maxDen - 1);
		String[] resol = ResolucaoRacionais.Multiplicacao(a, b, c, d);
		Racional res = new Racional(a, b).mult(new Racional(c, d));
		res.fatoracao(2);
		addParagrafo("Calcule:");
		addParagrafo("\\(\\dfrac{" + a + "}{" + b + "} \\times \\dfrac{" + c + "}{" + d + "} =\\)");
		gerarAlternativas(res.toString());
		for(String passo : resol)
			addResolucao("\\(" + passo + "\\)");
	}

	protected void divisao(int maxNum, int maxDen)
	{
		int a = 1 + rand.nextInt(maxNum), b = 2 + rand.nextInt(maxDen - 1);
		int c = 1 + rand.nextInt(maxNum), d = 2 + rand.nextInt(maxDen - 1);
		String[] resol = ResolucaoRacionais.divisao(a, b, c, d);
		Racional res = new Racional(a, b).div(new Racional(c, d));
		res.fatoracao(2);
		addParagrafo("Calcule:");
		addParagrafo("\\(\\dfrac{" + a + "}{" + b + "} \\div \\dfrac{" + c + "}{" + d + "} =\\)");
		gerarAlternativas(res.toString());
		for(String passo : resol)
			addResolucao("\\(" + passo + "\\)");
	}

	protected void simplificar()
	{
		int a = 2 + rand.nextInt(8), b = 2 + rand.nextInt(8);
		while(a == b) b = 2 + rand.nextInt(8);
		int k = 2 + rand.nextInt(5);
		int num = a * k, den = b * k;
		Racional res = new Racional(num, den);
		res.fatoracao(2);
		long mdc = gcd(num, den);
		addParagrafo("Simplifique a fração:");
		addParagrafo("\\(\\dfrac{" + num + "}{" + den + "}\\)");
		gerarAlternativas(res);
		addResolucao("Dividimos numerador e denominador pelo MDC.");
		addResolucao("\\(\\text{MDC}(" + num + ",\\," + den + ") = " + mdc + "\\).");
		addResolucao("\\(\\dfrac{" + num + "}{" + den + "} = \\dfrac{" + num + " \\div " + mdc + "}{" + den + " \\div " + mdc + "} = " + res.showDfrac() + "\\)");
	}

	protected void equivalente()
	{
		int a = 2 + rand.nextInt(9), b = 2 + rand.nextInt(8);
		while(a == b) a = 2 + rand.nextInt(9);
		int k = 2 + rand.nextInt(5);
		int numDest = a * k, denDest = b * k;
		addParagrafo("Encontre o numerador que completa a fração equivalente:");
		addParagrafo("\\(\\dfrac{" + a + "}{" + b + "} = \\dfrac{\\,?\\,}{" + denDest + "}\\)");
		gerarAlternativasInteiras(numDest);
		addResolucao("Para obter denominador \\(" + denDest + "\\) a partir de \\(" + b + "\\), multiplicamos por \\(" + k + "\\).");
		addResolucao("Aplicamos o mesmo fator ao numerador:");
		addResolucao("\\(\\dfrac{" + a + "}{" + b + "} = \\dfrac{" + a + " \\times " + k + "}{" + b + " \\times " + k + "} = \\dfrac{" + numDest + "}{" + denDest + "}\\)");
	}

	protected void mistaParaImpropria()
	{
		int d = 2 + rand.nextInt(7);
		int n = 1 + rand.nextInt(5);
		int r = 1 + rand.nextInt(d - 1);
		int num = n * d + r;
		addParagrafo("Converta a fração mista em fração imprópria:");
		addParagrafo("\\(" + n + "\\dfrac{" + r + "}{" + d + "}\\)");
		gerarAlternativas(new Racional(num, d).toString());
		addResolucao("Multiplicamos a parte inteira pelo denominador e somamos o numerador.");
		addResolucao("\\(" + n + "\\dfrac{" + r + "}{" + d + "} = \\dfrac{" + n + " \\times " + d + " + " + r + "}{" + d + "} = \\dfrac{" + num + "}{" + d + "}\\)");
	}

	protected void impropriaParaMista()
	{
		int d = 2 + rand.nextInt(7);
		int q = 1 + rand.nextInt(5);
		int r = 1 + rand.nextInt(d - 1);
		int num = q * d + r;
		String correta = q + "\\dfrac{" + r + "}{" + d + "}";
		List<String> distratores = new ArrayList<>();
		distratores.add("\\(" + (q + 1) + "\\dfrac{" + r + "}{" + d + "}\\)");
		distratores.add("\\(" + q + "\\dfrac{" + (r % (d - 1) + 1) + "}{" + d + "}\\)");
		distratores.add("\\(" + (q > 1 ? q - 1 : q + 2) + "\\dfrac{" + r + "}{" + d + "}\\)");
		addParagrafo("Escreva a fração imprópria como número misto:");
		addParagrafo("\\(\\dfrac{" + num + "}{" + d + "}\\)");
		embaralharEAdicionarAlternativas("\\(" + correta + "\\)", distratores);
		addResolucao("Dividimos o numerador pelo denominador: o quociente é a parte inteira e o resto é o novo numerador.");
		addResolucao("\\(" + num + " \\div " + d + " = " + q + "\\) resto \\(" + r + "\\).");
		addResolucao("\\(\\dfrac{" + num + "}{" + d + "} = " + correta + "\\)");
	}

	protected void comparar()
	{
		List<int[]> fracs = new ArrayList<>();
		List<Double> vals = new ArrayList<>();
		int tentativas = 0;
		while(fracs.size() < 4 && tentativas++ < 100)
		{
			int a = 1 + rand.nextInt(9), b = 2 + rand.nextInt(8);
			double v = (double) a / b;
			boolean distinto = true;
			for(double x : vals) if(Math.abs(x - v) < 0.001) { distinto = false; break; }
			if(distinto) { fracs.add(new int[]{a, b}); vals.add(v); }
		}
		int idxMax = 0;
		for(int i = 1; i < vals.size(); i++) if(vals.get(i) > vals.get(idxMax)) idxMax = i;

		int[] maior = fracs.get(idxMax);
		List<String> distratores = new ArrayList<>();
		for(int i = 0; i < fracs.size(); i++)
			if(i != idxMax) distratores.add("\\(\\dfrac{" + fracs.get(i)[0] + "}{" + fracs.get(i)[1] + "}\\)");
		String corretaFrac = "\\(\\dfrac{" + maior[0] + "}{" + maior[1] + "}\\)";
		addParagrafo("Qual das frações " + listarOpcoes(corretaFrac, distratores) + " é a maior?");
		embaralharEAdicionarAlternativas(corretaFrac, distratores);
		addResolucao(
			"Reduzindo as frações ao mesmo denominador (ou comparando seus valores decimais), " +
			"a maior é \\(\\mathbf{\\dfrac{" + maior[0] + "}{" + maior[1] + "}}\\)."
		);
	}

	protected void potencia()
	{
		int a = 2 + rand.nextInt(3), b = 2 + rand.nextInt(3);
		while(a == b) b = 2 + rand.nextInt(3);
		int n = 2 + rand.nextInt(2); // 2 ou 3
		long num = pow(a, n), den = pow(b, n);
		Racional res = new Racional(num, den);
		res.fatoracao(2);
		addParagrafo("Calcule:");
		addParagrafo("\\(\\left(\\dfrac{" + a + "}{" + b + "}\\right)^{" + n + "} =\\)");
		gerarAlternativas(res.toString());
		addResolucao("Elevamos numerador e denominador ao expoente:");
		addResolucao("\\(\\left(\\dfrac{" + a + "}{" + b + "}\\right)^{" + n + "} = \\dfrac{" + a + "^{" + n + "}}{" + b + "^{" + n + "}} = \\dfrac{" + num + "}{" + den + "}\\)");
	}

	protected void fracaoDeQuantidade()
	{
		int q = 2 + rand.nextInt(6);
		int p = 1 + rand.nextInt(q - 1);
		int mult = 2 + rand.nextInt(20);
		int total = q * mult;
		int resultado = p * mult;
		addParagrafo("Quanto é \\(\\dfrac{" + p + "}{" + q + "}\\) de \\(" + total + "\\)?");
		gerarAlternativasInteiras(resultado);
		addResolucao("Multiplicamos a fração pelo total:");
		addResolucao("\\(\\dfrac{" + p + "}{" + q + "} \\times " + total + " = \\dfrac{" + p + " \\times " + total + "}{" + q + "} = \\dfrac{" + (p * total) + "}{" + q + "} = \\mathbf{" + resultado + "}\\)");
	}

	private static long pow(int base, int exp)
	{
		long r = 1;
		for(int i = 0; i < exp; i++) r *= base;
		return r;
	}

	private static long gcd(long a, long b)
	{
		a = Math.abs(a); b = Math.abs(b);
		while(b != 0) { long t = b; b = a % b; a = t; }
		return a;
	}
}
