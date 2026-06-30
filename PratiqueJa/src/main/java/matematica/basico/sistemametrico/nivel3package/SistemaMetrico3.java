package matematica.basico.sistemametrico.nivel3package;

import java.util.Arrays;
import matematica.GeradorExercicio;

// Tempo com contexto: filmes, provas, viagens, corridas
public class SistemaMetrico3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = rand.nextInt(4);

		if (tipo == 0)
		{
			// Hh Mmin → total em minutos
			int h = 1 + rand.nextInt(3);
			int m = rand.nextInt(4) * 15; // 0, 15, 30 ou 45 min
			int total = h * 60 + m;

			String duracao = h + "\\,\\text{h}" + (m > 0 ? "\\," + m + "\\,\\text{min}" : "");
			String[] eventos = {"Um filme tem duração de", "Uma prova dura", "Uma viagem de trem leva"};
			addParagrafo(eventos[rand.nextInt(eventos.length)] + " \\(" + duracao + "\\).");
			addParagrafo("Quantos minutos isso representa?");
			// Quando m==0, h*60 == total → d1 colide com correta; usar total-5 como fallback
			int d1 = m > 0 ? h * 60 : total - 5;
			embaralharEAdicionarAlternativas(
				"\\(" + total + "\\,\\text{min}\\)",
				Arrays.asList(
					"\\(" + d1 + "\\,\\text{min}\\)",
					"\\(" + (total + 15) + "\\,\\text{min}\\)",
					"\\(" + (h * 100 + m) + "\\,\\text{min}\\)"
				)
			);
			addResolucao("Converter as horas para minutos e somar:");
			addResolucao("\\(" + h + "\\,\\text{h} = " + (h * 60) + "\\,\\text{min}\\)");
			addResolucao("\\(" + (h * 60) + " + " + m + " = \\mathbf{" + total + "}\\,\\text{min}\\)");
		}
		else if (tipo == 1)
		{
			// total em segundos → horas
			int h = 1 + rand.nextInt(4);
			int s = h * 3600;

			String[] eventos = {"Uma maratona durou", "Um exame ocupa", "Uma palestra tem"};
			addParagrafo(eventos[rand.nextInt(eventos.length)] + " \\(" + s + "\\,\\text{s}\\).");
			addParagrafo("Quantas horas isso representa?");
			// s/60==h*60: d1 e d2 eram iguais; substituir d2 por s (não converteu segundos)
			embaralharEAdicionarAlternativas(
				"\\(" + h + "\\,\\text{h}\\)",
				Arrays.asList(
					"\\(" + (s / 60) + "\\,\\text{h}\\)",
					"\\(" + s + "\\,\\text{h}\\)",
					"\\(" + (h + 1) + "\\,\\text{h}\\)"
				)
			);
			addResolucao("\\(1\\,\\text{h} = 3600\\,\\text{s}\\), logo dividir por 3600:");
			addResolucao("\\(" + s + " \\div 3600 = \\mathbf{" + h + "}\\,\\text{h}\\)");
		}
		else if (tipo == 2)
		{
			// total em minutos → h e min (forma mista)
			int h   = 1 + rand.nextInt(4);
			int m30 = rand.nextBoolean() ? 30 : 0;
			int total = h * 60 + m30;
			String hStr = m30 > 0 ? h + "\\,\\text{h}\\,30\\,\\text{min}" : h + "\\,\\text{h}";

			String[] eventos = {"Uma cirurgia durou", "O treino de natação tem", "A aula durou"};
			addParagrafo(eventos[rand.nextInt(eventos.length)] + " \\(" + total + "\\,\\text{min}\\).");
			addParagrafo("Como expressar isso em horas e minutos?");
			embaralharEAdicionarAlternativas(
				"\\(" + hStr + "\\)",
				Arrays.asList(
					"\\(" + (h + 1) + "\\,\\text{h}\\)",
					"\\(" + h + "\\,\\text{h}\\," + (m30 > 0 ? 15 : 30) + "\\,\\text{min}\\)",
					"\\(" + (h * 60 + (m30 > 0 ? 1 : 0)) + "\\,\\text{min}\\)"
				)
			);
			addResolucao("Dividir por 60 para obter horas e minutos:");
			addResolucao(
				"\\(" + total + " \\div 60 = " + h + "\\)" +
				(m30 > 0
					? " com resto " + m30 + " min"
					: " exato (sem resto)")
			);
			addResolucao("\\(\\mathbf{" + hStr + "}\\)");
		}
		else
		{
			// dias → horas
			int dias = 1 + rand.nextInt(7);
			int h    = dias * 24;

			String[] eventos = {"Uma viagem de navio durou", "Uma expedição levou", "Um evento ocorreu durante"};
			addParagrafo(eventos[rand.nextInt(eventos.length)] + " \\(" + dias + "\\) dia" + (dias > 1 ? "s" : "") + ".");
			addParagrafo("Quantas horas isso representa?");
			embaralharEAdicionarAlternativas(
				"\\(" + h + "\\,\\text{h}\\)",
				Arrays.asList(
					"\\(" + (dias * 12) + "\\,\\text{h}\\)",
					"\\(" + (dias * 60) + "\\,\\text{h}\\)",
					"\\(" + (h + 24) + "\\,\\text{h}\\)"
				)
			);
			addResolucao("\\(1\\,\\text{dia} = 24\\,\\text{h}\\), logo multiplicar por 24:");
			addResolucao("\\(" + dias + " \\times 24 = \\mathbf{" + h + "}\\,\\text{h}\\)");
		}
	}
}
