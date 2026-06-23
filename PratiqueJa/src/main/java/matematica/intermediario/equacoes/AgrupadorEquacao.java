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
		addResolucao("Subtraímos \\(" + b + "\\) dos dois lados:");
		addResolucao("\\(" + a + v + " = " + c + " - " + b + " = " + (c - b) + "\\).");
		addResolucao("\\(" + v + " = \\dfrac{" + (c - b) + "}{" + a + "} = \\mathbf{" + x + "}\\)");
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
		addResolucao("Somamos \\(" + b + "\\) aos dois lados:");
		addResolucao("\\(" + a + v + " = " + c + " + " + b + " = " + (c + b) + "\\).");
		addResolucao("\\(" + v + " = \\dfrac{" + (c + b) + "}{" + a + "} = \\mathbf{" + x + "}\\)");
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
		addResolucao("Agrupamos os termos com \\(" + v + "\\) à esquerda e as constantes à direita:");
		addResolucao("\\(" + a + v + " - " + cStr + v + " = " + d + " - " + b + "\\).");
		addResolucao("\\(" + (coef > 1 ? coef : "") + v + " = " + (d - b) + "\\).");
		addResolucao("\\(" + v + " = \\dfrac{" + (d - b) + "}{" + coef + "} = \\mathbf{" + x + "}\\)");
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
		addResolucao("Aplicamos a distributiva:");
		addResolucao("\\(" + a + v + " + " + (a * b) + " = " + c + "\\).");
		addResolucao("\\(" + a + v + " = " + c + " - " + (a * b) + " = " + (c - a * b) + "\\).");
		addResolucao("\\(" + v + " = \\dfrac{" + (c - a * b) + "}{" + a + "} = \\mathbf{" + x + "}\\)");
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
		addResolucao("Subtraímos \\(" + c + "\\) dos dois lados:");
		addResolucao("\\(\\dfrac{" + v + "}{" + b + "} = " + d + " - " + c + " = " + q + "\\).");
		addResolucao("Multiplicamos por \\(" + b + "\\):");
		addResolucao("\\(" + v + " = " + q + " \\times " + b + " = \\mathbf{" + x + "}\\)");
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

		addResolucao("Isolamos \\(" + v + "\\):");
		addResolucao("\\(" + a + v + " " + s + " " + c + " - " + b + " = " + (c - b) + "\\).");
		addResolucao("Dividimos por \\(" + a + " > 0\\) (o sentido se mantém):");
		addResolucao("\\(" + v + " " + s + " \\dfrac{" + (c - b) + "}{" + a + "} = \\mathbf{" + xB + "}\\)");
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
		addResolucao("Seja \\(x\\) o número. A equação é \\(" + a + "x + " + b + " = " + c + "\\).");
		addResolucao("\\(" + a + "x = " + c + " - " + b + " = " + (c - b) + "\\).");
		addResolucao("\\(x = \\dfrac{" + (c - b) + "}{" + a + "} = \\mathbf{" + x + "}\\)");
	}
}
