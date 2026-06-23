package matematica.basico.divisibilidade;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import matematica.GeradorExercicio;

// Helpers genéricos para os geradores de divisibilidade (válidos p/ qualquer divisor).
public abstract class DivisibilidadeBase extends GeradorExercicio
{
	// "Qual dos números abaixo é divisível por d?"
	protected void multiplaEscolha(int d)
	{
		int correto;
		do { correto = 100 + rand.nextInt(900); } while(correto % d != 0);

		List<String> erradas = new ArrayList<>();
		Set<Integer> usados = new HashSet<>();
		usados.add(correto);
		while(erradas.size() < 3)
		{
			int c = 100 + rand.nextInt(900);
			if(c % d != 0 && usados.add(c)) erradas.add("\\(" + c + "\\)");
		}

		addParagrafo("Qual dos números " + listarOpcoes("\\(" + correto + "\\)", erradas) + " é divisível por \\(" + d + "\\)?");
		embaralharEAdicionarAlternativas("\\(" + correto + "\\)", erradas);
		addResolucao("\\(" + correto + " \\div " + d + " = " + (correto / d) + "\\) (resto \\(0\\)), logo \\(" + correto + "\\) é divisível por \\(" + d + "\\).");
		addResolucao("Os demais deixam resto diferente de zero.");
	}

	// "Qual dos números abaixo NÃO é divisível por d?"
	protected void multiplaEscolhaNao(int d)
	{
		int correto;
		do { correto = 100 + rand.nextInt(900); } while(correto % d == 0);

		List<String> erradas = new ArrayList<>();
		Set<Integer> usados = new HashSet<>();
		usados.add(correto);
		while(erradas.size() < 3)
		{
			int c = 100 + rand.nextInt(900);
			if(c % d == 0 && usados.add(c)) erradas.add("\\(" + c + "\\)");
		}

		addParagrafo("Qual dos números " + listarOpcoes("\\(" + correto + "\\)", erradas) + " NÃO é divisível por \\(" + d + "\\)?");
		embaralharEAdicionarAlternativas("\\(" + correto + "\\)", erradas);
		addResolucao("\\(" + correto + " \\div " + d + " = " + (correto / d) + "\\) com resto \\(" + (correto % d) + "\\), logo \\(" + correto + "\\) não é divisível por \\(" + d + "\\).");
		addResolucao("Os demais são divisíveis (resto \\(0\\)).");
	}

	// "Qual é o resto da divisão de N por d?"
	protected void resto(int d)
	{
		int n = 100 + rand.nextInt(900);
		int r = n % d;

		addParagrafo("Qual é o resto da divisão de \\(" + n + "\\) por \\(" + d + "\\)?");
		gerarAlternativasInteiras(r);
		addResolucao("\\(" + n + " = " + d + " \\times " + (n / d) + " + " + r + "\\), logo o resto é \\(\\mathbf{" + r + "}\\).");
	}

	// "d é divisor de N?" (Sim/Não), N de 3 dígitos
	protected void ehDivisor(int d)
	{
		int n = 100 + rand.nextInt(900);
		boolean querSim = rand.nextBoolean();
		int resto = n % d;
		if(querSim) n += (d - resto) % d;
		else if(resto == 0) n++;
		boolean div = n % d == 0;

		addParagrafo("\\(" + d + "\\) é divisor de \\(" + n + "\\)?");
		gerarAlternativasBoolean(div);
		if(div)
			addResolucao("\\(" + n + " \\div " + d + " = " + (n / d) + "\\) (resto \\(0\\)), logo \\(" + d + "\\) é divisor de \\(" + n + "\\): \\(\\mathbf{Sim}\\).");
		else
			addResolucao("\\(" + n + " \\div " + d + "\\) deixa resto \\(" + (n % d) + "\\), logo \\(" + d + "\\) não é divisor: \\(\\mathbf{Não}\\).");
	}

	// "N é divisível por d?" (Sim/Não), N de 3 dígitos — resolução por divisão direta
	protected void boolDivisivel(int d)
	{
		int n = 100 + rand.nextInt(900);
		boolean querSim = rand.nextBoolean();
		int resto = n % d;
		if(querSim) n += (d - resto) % d;
		else if(resto == 0) n++;
		boolean div = n % d == 0;

		addParagrafo(n + " é divisível por " + d + "?");
		gerarAlternativasBoolean(div);
		if(div)
			addResolucao("\\(" + n + " \\div " + d + " = " + (n / d) + "\\) (resto \\(0\\)), logo é divisível por \\(" + d + "\\).");
		else
			addResolucao("\\(" + n + " \\div " + d + "\\) deixa resto \\(" + (n % d) + "\\), logo não é divisível por \\(" + d + "\\).");
	}

	// "N é divisível por 11?" — regra da soma alternada dos algarismos
	protected void boolPor11()
	{
		int d = 11;
		int n = 1000 + rand.nextInt(9000);
		boolean querSim = rand.nextBoolean();
		int resto = n % d;
		if(querSim) n += (d - resto) % d;
		else if(resto == 0) n++;
		boolean div = n % d == 0;

		String s = String.valueOf(n);
		int soma = 0;
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < s.length(); i++)
		{
			int dig = s.charAt(i) - '0';
			int sinal = (i % 2 == 0) ? 1 : -1;
			soma += sinal * dig;
			if(i == 0) sb.append(dig);
			else sb.append(sinal > 0 ? " + " : " - ").append(dig);
		}

		addParagrafo(n + " é divisível por 11?");
		gerarAlternativasBoolean(div);
		addResolucao("A soma alternada dos algarismos é \\(" + sb + " = " + soma + "\\).");
		if(div) addResolucao("Como \\(" + soma + "\\) é múltiplo de 11 (ou \\(0\\)), \\(" + n + "\\) é divisível por 11.");
		else addResolucao("Como \\(" + soma + "\\) não é múltiplo de 11, \\(" + n + "\\) não é divisível por 11.");
	}
}
