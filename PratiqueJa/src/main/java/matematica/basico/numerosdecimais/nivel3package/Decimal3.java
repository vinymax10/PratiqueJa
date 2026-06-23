package matematica.basico.numerosdecimais.nivel3package;

import java.util.ArrayList;
import java.util.List;

import matematica.basico.numerosdecimais.AgrupadorDecimal;

// a,bc × d,e — produto com 3 casas decimais no resultado
public class Decimal3 extends AgrupadorDecimal
{
	@Override
	protected void construir()
	{
		// aH: valor em centésimos (2 casas); bT: valor em décimos (1 casa)
		int aH = 111 + rand.nextInt(189);
		int bT = 11 + rand.nextInt(19);
		// produto em milésimos = aH * bT
		int prodM = aH * bT;

		String a = fmtH(aH);
		String b = fmtT(bT);
		String result = fmtM(prodM);

		addParagrafo("Calcule o produto dos números decimais:");
		addParagrafo("\\(" + a + " \\times " + b + " = \\,?\\)");

		// alternativas em milésimos
		String correctStr = "\\(" + result + "\\)";
		List<String> distratores = new ArrayList<>();
		List<Integer> usados = new ArrayList<>();
		usados.add(prodM);
		while(distratores.size() < 3)
		{
			int delta = 1 + rand.nextInt(8);
			int cand = rand.nextBoolean() ? prodM + delta : prodM - delta;
			if(cand > 0 && !usados.contains(cand))
			{
				usados.add(cand);
				distratores.add("\\(" + fmtM(cand) + "\\)");
			}
		}
		embaralharEAdicionarAlternativas(correctStr, distratores);

		addResolucao("\\(" + a + " \\times " + b + " =\\)");
		addResolucao("\\(" + aH + " \\times " + bT + " = " + prodM + "\\)");
		addResolucao("3 casas decimais: \\(" + result + "\\)");
	}
}
