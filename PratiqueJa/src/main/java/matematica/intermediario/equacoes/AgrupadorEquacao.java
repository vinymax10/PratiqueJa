package matematica.intermediario.equacoes;

import java.util.ArrayList;
import java.util.List;

import matematica.GeradorExercicio;

// Base com helpers MANUAIS (determinísticos, resultado inteiro) para equações do 1º grau.
public abstract class AgrupadorEquacao extends GeradorExercicio
{
	protected String var()
	{
		return "" + (char) (97 + rand.nextInt(23)); // a..w
	}

	// ax + b = c
	protected void axMaisB(int maxA, int maxX, int maxB)
	{
		int a = 2 + rand.nextInt(maxA - 1), x = 1 + rand.nextInt(maxX), b = 1 + rand.nextInt(maxB);
		int c = a * x + b;
		String v = var();
		addParagrafo("Encontre \\(" + v + "\\)");
		addParagrafo("\\(" + a + v + " + " + b + " = " + c + "\\)");
		gerarAlternativas("" + x);
		setResolucao(
			"Subtraímos \\(" + b + "\\) dos dois lados: \\(\\\\\\)" +
			"\\(" + a + v + " = " + c + " - " + b + " = " + (c - b) + "\\). \\(\\\\\\)" +
			"\\(" + v + " = \\dfrac{" + (c - b) + "}{" + a + "} = \\mathbf{" + x + "}\\)"
		);
	}

	// ax - b = c
	protected void axMenosB(int maxA, int maxX, int maxB)
	{
		int a = 2 + rand.nextInt(maxA - 1), x = 1 + rand.nextInt(maxX), b = 1 + rand.nextInt(maxB);
		int c = a * x - b;
		String v = var();
		addParagrafo("Encontre \\(" + v + "\\)");
		addParagrafo("\\(" + a + v + " - " + b + " = " + c + "\\)");
		gerarAlternativas("" + x);
		setResolucao(
			"Somamos \\(" + b + "\\) aos dois lados: \\(\\\\\\)" +
			"\\(" + a + v + " = " + c + " + " + b + " = " + (c + b) + "\\). \\(\\\\\\)" +
			"\\(" + v + " = \\dfrac{" + (c + b) + "}{" + a + "} = \\mathbf{" + x + "}\\)"
		);
	}

	// ax + b = cx + d  (a > c)
	protected void doisLados(int maxA, int maxX, int maxB)
	{
		int a = 3 + rand.nextInt(maxA - 2);
		int c = 1 + rand.nextInt(a - 1);
		int x = 1 + rand.nextInt(maxX);
		int coef = a - c;
		int b = 1 + rand.nextInt(maxB);
		int d = b + coef * x;
		String v = var();
		String cStr = c > 1 ? "" + c : "";
		addParagrafo("Encontre \\(" + v + "\\)");
		addParagrafo("\\(" + a + v + " + " + b + " = " + cStr + v + " + " + d + "\\)");
		gerarAlternativas("" + x);
		String res = "Agrupamos os termos com \\(" + v + "\\) à esquerda e as constantes à direita: \\(\\\\\\)";
		res += "\\(" + a + v + " - " + cStr + v + " = " + d + " - " + b + "\\). \\(\\\\\\)";
		res += "\\(" + (coef > 1 ? coef : "") + v + " = " + (d - b) + "\\). \\(\\\\\\)";
		res += "\\(" + v + " = \\dfrac{" + (d - b) + "}{" + coef + "} = \\mathbf{" + x + "}\\)";
		setResolucao(res);
	}

	// a(x + b) = c
	protected void comParenteses(int maxA, int maxX, int maxB)
	{
		int a = 2 + rand.nextInt(maxA - 1), x = 1 + rand.nextInt(maxX), b = 1 + rand.nextInt(maxB);
		int c = a * (x + b);
		String v = var();
		addParagrafo("Encontre \\(" + v + "\\)");
		addParagrafo("\\(" + a + "(" + v + " + " + b + ") = " + c + "\\)");
		gerarAlternativas("" + x);
		setResolucao(
			"Aplicamos a distributiva: \\(\\\\\\)" +
			"\\(" + a + v + " + " + (a * b) + " = " + c + "\\). \\(\\\\\\)" +
			"\\(" + a + v + " = " + c + " - " + (a * b) + " = " + (c - a * b) + "\\). \\(\\\\\\)" +
			"\\(" + v + " = \\dfrac{" + (c - a * b) + "}{" + a + "} = \\mathbf{" + x + "}\\)"
		);
	}

	// x/b + c = d
	protected void comFracao(int maxB, int maxX, int maxC)
	{
		int b = 2 + rand.nextInt(maxB - 1), q = 1 + rand.nextInt(maxX), c = 1 + rand.nextInt(maxC);
		int x = b * q;
		int d = q + c;
		String v = var();
		addParagrafo("Encontre \\(" + v + "\\)");
		addParagrafo("\\(\\dfrac{" + v + "}{" + b + "} + " + c + " = " + d + "\\)");
		gerarAlternativas("" + x);
		setResolucao(
			"Subtraímos \\(" + c + "\\) dos dois lados: \\(\\\\\\)" +
			"\\(\\dfrac{" + v + "}{" + b + "} = " + d + " - " + c + " = " + q + "\\). \\(\\\\\\)" +
			"Multiplicamos por \\(" + b + "\\): \\(\\\\\\)" +
			"\\(" + v + " = " + q + " \\times " + b + " = \\mathbf{" + x + "}\\)"
		);
	}

	// ax + b (>, <, >=, <=) c  — inequação do 1º grau (coeficiente positivo, sentido mantido)
	protected void inequacao(int maxA, int maxX, int maxB)
	{
		int a = 2 + rand.nextInt(maxA - 1), xB = 1 + rand.nextInt(maxX), b = 1 + rand.nextInt(maxB);
		int c = a * xB + b;
		String[] sinais = {">", "<", "\\geq", "\\leq"};
		String s = sinais[rand.nextInt(4)];
		String sOpp = s.equals(">") ? "<" : s.equals("<") ? ">" : s.equals("\\geq") ? "\\leq" : "\\geq";
		String v = var();

		addParagrafo("Resolva a inequação e indique a solução:");
		addParagrafo("\\(" + a + v + " + " + b + " " + s + " " + c + "\\)");

		List<String> distratores = new ArrayList<>();
		distratores.add("\\(" + v + " " + sOpp + " " + xB + "\\)");
		distratores.add("\\(" + v + " " + s + " " + (xB + 1) + "\\)");
		distratores.add("\\(" + v + " " + s + " " + (xB > 1 ? xB - 1 : xB + 2) + "\\)");
		embaralharEAdicionarAlternativas("\\(" + v + " " + s + " " + xB + "\\)", distratores);

		setResolucao(
			"Isolamos \\(" + v + "\\): \\(\\\\\\)" +
			"\\(" + a + v + " " + s + " " + c + " - " + b + " = " + (c - b) + "\\). \\(\\\\\\)" +
			"Dividimos por \\(" + a + " > 0\\) (o sentido se mantém): \\(\\\\\\)" +
			"\\(" + v + " " + s + " \\dfrac{" + (c - b) + "}{" + a + "} = \\mathbf{" + xB + "}\\)"
		);
	}

	protected void problema(int maxA, int maxX, int maxB)
	{
		int a = 2 + rand.nextInt(maxA - 1), x = 1 + rand.nextInt(maxX), b = 1 + rand.nextInt(maxB);
		int c = a * x + b;
		int tipo = rand.nextInt(3);
		if(tipo == 0)
			addParagrafo("Um número multiplicado por \\(" + a + "\\) e somado a \\(" + b + "\\) resulta em \\(" + c + "\\). Qual é o número?");
		else if(tipo == 1)
			addParagrafo("O \\(" + a + "\\)º múltiplo de um número, acrescido de \\(" + b + "\\), é igual a \\(" + c + "\\). Qual é esse número?");
		else
			addParagrafo("Pensei em um número, multipliquei por \\(" + a + "\\), somei \\(" + b + "\\) e obtive \\(" + c + "\\). Em que número pensei?");
		gerarAlternativas("" + x);
		setResolucao(
			"Seja \\(x\\) o número. A equação é \\(" + a + "x + " + b + " = " + c + "\\). \\(\\\\\\)" +
			"\\(" + a + "x = " + c + " - " + b + " = " + (c - b) + "\\). \\(\\\\\\)" +
			"\\(x = \\dfrac{" + (c - b) + "}{" + a + "} = \\mathbf{" + x + "}\\)"
		);
	}
}
