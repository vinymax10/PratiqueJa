package matematica.basico.sistemametrico;

import java.util.ArrayList;
import java.util.List;

import matematica.GeradorExercicio;

// Base com helpers de conversão para os geradores de sistema métrico.
// As versões "Latex" recebem a unidade já formatada (ex.: "\\text{m}^2");
// as versões simples recebem só o nome da unidade (ex.: "kg") e o envolvem em \text{}.
public abstract class MetricoBase extends GeradorExercicio
{
	// ---- unidade simples (sem expoente) ----
	protected void multiplicar(int valor, String unidOrigem, long fator, String unidDestino)
	{
		multiplicarLatex(valor, "\\text{" + unidOrigem + "}", fator, "\\text{" + unidDestino + "}");
	}

	protected void dividirExato(int resultado, String unidDestino, long fator, String unidOrigem)
	{
		dividirExatoLatex(resultado, "\\text{" + unidDestino + "}", fator, "\\text{" + unidOrigem + "}");
	}

	// ---- unidade com LaTeX livre (área/volume com expoente) ----
	// Conversão de unidade MAIOR para MENOR: valor × fator (resultado inteiro).
	protected void multiplicarLatex(int valor, String unidOrigem, long fator, String unidDestino)
	{
		long resultado = valor * fator;

		addParagrafo("Converta para \\(" + unidDestino + "\\):");
		addParagrafo("\\(" + valor + "\\," + unidOrigem + "\\)");

		List<String> distratores = new ArrayList<>();
		adicionarDistrator(distratores, valor * (fator / 10), unidDestino, resultado);
		adicionarDistrator(distratores, valor * fator * 10, unidDestino, resultado);
		adicionarDistrator(distratores, valor + fator, unidDestino, resultado);
		adicionarDistrator(distratores, valor * fator / 100 * 10, unidDestino, resultado);

		embaralharEAdicionarAlternativas("\\(" + resultado + "\\," + unidDestino + "\\)", distratores);

		addResolucao("\\(1\\," + unidOrigem + " = " + fator + "\\," + unidDestino + "\\), logo multiplicar por " + fator + ":");
		addResolucao("\\(" + valor + " \\times " + fator + " = \\mathbf{" + resultado + "}\\," + unidDestino + "\\)");
	}

	// Conversão de unidade MENOR para MAIOR (divisão exata): origem = resultado × fator.
	protected void dividirExatoLatex(int resultado, String unidDestino, long fator, String unidOrigem)
	{
		long valor = resultado * fator;

		addParagrafo("Converta para \\(" + unidDestino + "\\):");
		addParagrafo("\\(" + valor + "\\," + unidOrigem + "\\)");

		List<String> distratores = new ArrayList<>();
		adicionarDistrator(distratores, (long) resultado * 10, unidDestino, resultado);
		adicionarDistrator(distratores, (long) resultado * 100, unidDestino, resultado);
		adicionarDistrator(distratores, valor, unidDestino, resultado);
		adicionarDistrator(distratores, (long) resultado + fator, unidDestino, resultado);

		embaralharEAdicionarAlternativas("\\(" + resultado + "\\," + unidDestino + "\\)", distratores);

		addResolucao("\\(1\\," + unidDestino + " = " + fator + "\\," + unidOrigem + "\\), logo dividir por " + fator + ":");
		addResolucao("\\(" + valor + " \\div " + fator + " = \\mathbf{" + resultado + "}\\," + unidDestino + "\\)");
	}

	private void adicionarDistrator(List<String> distratores, long valor, String unidadeLatex, long correto)
	{
		if(distratores.size() >= 3) return;
		if(valor == correto || valor <= 0) return;
		String alt = "\\(" + valor + "\\," + unidadeLatex + "\\)";
		if(!distratores.contains(alt))
			distratores.add(alt);
	}
}
