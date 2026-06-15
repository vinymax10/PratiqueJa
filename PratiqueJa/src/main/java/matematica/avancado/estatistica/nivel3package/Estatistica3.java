package matematica.avancado.estatistica.nivel3package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;
import matematica.avancado.estatistica.AuxEstatistica;

// Detecção de outliers pelo critério do boxplot: limite = Q3 + 1,5 × AIQ
public class Estatistica3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// IQR = 2k (k ∈ {6,8,10,12}) → 1,5×IQR = 3k (inteiro)
		int[] kOpcoes = {6, 8, 10, 12};
		int k = kOpcoes[rand.nextInt(kOpcoes.length)];
		int iqr      = 2 * k;
		int limite15 = 3 * k;  // 1,5 × IQR

		// Q1 > 3k garante cerca inferior positiva
		int Q1       = limite15 + 5 + rand.nextInt(15);
		int Q3       = Q1 + iqr;
		int cercaSup = Q3 + limite15;

		// 7 valores ordenados com values[1]=Q1 e values[5]=Q3
		int step = iqr / 4;  // k/2 ∈ {3,4,5,6} — inteiro garantido
		int[] valores = {
			Q1 - step,      // v0: abaixo de Q1, mas dentro da cerca inferior
			Q1,             // v1 = Q1
			Q1 + step,      // v2
			Q1 + iqr / 2,   // v3: mediana
			Q3 - step,      // v4
			Q3,             // v5 = Q3
			Q3 + step,      // v6: acima de Q3, mas dentro da cerca superior
		};

		String[] contextos = {
			"tempo de atendimento (em minutos) registrado em " + valores.length + " atendimentos",
			"pontuação obtida por " + valores.length + " alunos em uma avaliação",
			"consumo de energia (em kWh) de " + valores.length + " residências em um mês",
			"distância percorrida (em km) por " + valores.length + " entregadores em um dia",
		};
		String ctx = contextos[rand.nextInt(contextos.length)];

		addParagrafo("Os dados a seguir representam o " + ctx + ": "
				+ AuxEstatistica.listaStr(valores) + "."
				+ " Calcule o limite superior para detecção de outliers"
				+ " \\(\\left(Q_3 + 1{,}5 \\times AIQ\\right)\\).");

		// Distratores
		int d1 = Q3 + iqr;         // usou 1×AIQ em vez de 1,5
		int d2 = Q3 + 2 * iqr;     // usou 2×AIQ em vez de 1,5
		int d3 = Q1 - limite15;    // usou limite inferior em vez do superior

		List<String> distratores = new ArrayList<>();
		distratores.add("" + d1);
		distratores.add("" + d2);
		distratores.add("" + d3);
		embaralharEAdicionarAlternativas("" + cercaSup, distratores);

		String res = "Para detectar outliers usa-se o critério do boxplot:\\(\\\\\\)";
		res += "Dados ordenados: " + AuxEstatistica.listaStr(valores) + " \\((n = 7)\\).\\(\\\\\\)";
		res += "\\(Q_1 = " + Q1 + "\\) (2º valor), \\(\\quad Q_3 = " + Q3 + "\\) (6º valor)\\(\\\\\\)";
		res += "\\(AIQ = Q_3 - Q_1 = " + Q3 + " - " + Q1 + " = " + iqr + "\\)\\(\\\\\\)";
		res += "Limite superior:\\(\\\\\\)";
		res += "\\(Q_3 + 1{,}5 \\times AIQ = " + Q3 + " + 1{,}5 \\times " + iqr
				+ " = " + Q3 + " + " + limite15 + " = \\mathbf{" + cercaSup + "}\\)";
		setResolucao(res);
	}
}
