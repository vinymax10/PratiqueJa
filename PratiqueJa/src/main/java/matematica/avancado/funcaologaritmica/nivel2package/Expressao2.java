package matematica.avancado.funcaologaritmica.nivel2package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;

public class Expressao2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// Domínio de f(x) = log_a(bx - bk) → x > k
		int[] bases = {2, 3, 4, 5};
		int a = bases[rand.nextInt(bases.length)];
		int b = 1 + rand.nextInt(3); // coeficiente 1, 2 ou 3
		int k = 1 + rand.nextInt(5); // limiar 1..5

		// argumento: bx - b*k (sempre anula em x=k)
		int c = b * k;

		String argStr = (b == 1) ? "x - " + c : (b + "x - " + c);
		addParagrafo("Determine o domínio da função \\(f(x) = \\log_{" + a + "}(" + argStr + ")\\).");

		String correto = "\\(x > " + k + "\\)";
		List<String> dist = new ArrayList<>();
		dist.add("\\(x > " + (k + 1) + "\\)");
		dist.add(k > 1 ? "\\(x > " + (k - 1) + "\\)" : "\\(x \\geq 1\\)");
		dist.add("\\(x > 0\\)");
		embaralharEAdicionarAlternativas(correto, dist);

		String res = "O logaritmo só existe para argumento positivo: \\(\\\\\\)";
		res += "\\(" + argStr + " > 0 \\Rightarrow ";
		if (b > 1)
			res += b + "x > " + c + " \\Rightarrow ";
		res += "x > " + k + "\\) \\(\\\\\\)";
		res += "Domínio: \\(\\mathbf{x > " + k + "}\\)";
		setResolucao(res);
	}
}
