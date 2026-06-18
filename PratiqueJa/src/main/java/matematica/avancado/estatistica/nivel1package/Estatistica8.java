package matematica.avancado.estatistica.nivel1package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;

// Classificação de variáveis estatísticas: discreta, contínua, nominal, ordinal
public class Estatistica8 extends GeradorExercicio
{
	// {descrição, índice do tipo, justificativa}
	// Índices: 0=Quantitativa discreta, 1=Quantitativa contínua, 2=Qualitativa nominal, 3=Qualitativa ordinal
	private static final Object[][] VARIAVEIS = {
		{"Número de filhos de uma família",                      0, "é numérica e assume apenas valores inteiros (0, 1, 2, ...)"},
		{"Número de gols em uma partida de futebol",             0, "é numérica e assume apenas valores inteiros (0, 1, 2, ...)"},
		{"Número de carros em uma garagem",                      0, "é numérica e contável — assume valores inteiros"},
		{"Número de páginas de um livro",                        0, "é numérica e contável — assume valores inteiros"},
		{"Quantidade de erros em uma redação",                   0, "é numérica e contável — assume valores inteiros"},
		{"Altura de uma pessoa (em cm)",                         1, "é numérica e mensurável — pode assumir qualquer valor num intervalo contínuo"},
		{"Temperatura corporal (em °C)",                         1, "é numérica e mensurável — pode assumir qualquer valor num intervalo contínuo"},
		{"Salário mensal (em reais)",                            1, "é numérica e mensurável — pode assumir qualquer valor num intervalo contínuo"},
		{"Peso de uma fruta (em gramas)",                        1, "é numérica e mensurável — pode assumir qualquer valor num intervalo contínuo"},
		{"Tempo de conclusão de uma prova (em minutos)",         1, "é numérica e mensurável — pode assumir qualquer valor num intervalo contínuo"},
		{"Cor dos olhos",                                        2, "é categórica e suas categorias não têm ordem entre si"},
		{"Tipo sanguíneo (A, B, AB ou O)",                       2, "é categórica e suas categorias não têm ordem entre si"},
		{"Cor favorita de uma pessoa",                           2, "é categórica e suas categorias não têm ordem entre si"},
		{"Religião declarada",                                   2, "é categórica e suas categorias não têm ordem entre si"},
		{"Bairro de residência",                                 2, "é categórica e suas categorias não têm ordem entre si"},
		{"Grau de escolaridade (fundamental, médio, superior)",  3, "é categórica e suas categorias têm hierarquia bem definida"},
		{"Nível de satisfação (ruim, regular, bom, ótimo)",      3, "é categórica e suas categorias têm hierarquia bem definida"},
		{"Posição em um pódio (1º, 2º, 3º)",                    3, "é categórica e suas categorias têm hierarquia bem definida"},
		{"Nível de dor (leve, moderado, intenso)",               3, "é categórica e suas categorias têm hierarquia bem definida"},
		{"Classificação de risco (baixo, médio, alto)",          3, "é categórica e suas categorias têm hierarquia bem definida"},
	};

	private static final String[] NOMES = {
		"Quantitativa discreta",
		"Quantitativa contínua",
		"Qualitativa nominal",
		"Qualitativa ordinal",
	};

	@Override
	protected void construir()
	{
		Object[] var = VARIAVEIS[rand.nextInt(VARIAVEIS.length)];
		String descricao    = (String) var[0];
		int tipoIdx         = (int)    var[1];
		String justificativa = (String) var[2];
		String tipoCorreto  = NOMES[tipoIdx];

		addParagrafo("Classifique a seguinte variável estatística: \"" + descricao + "\".");

		List<String> distratores = new ArrayList<>();
		for (int i = 0; i < NOMES.length; i++)
			if (i != tipoIdx)
				distratores.add(NOMES[i]);
		embaralharEAdicionarAlternativas(tipoCorreto, distratores);

		String res;
		if (tipoIdx <= 1)
		{
			res = "A variável \"" + descricao + "\" é quantitativa (expressa um valor numérico).\\(\\\\\\)";
			if (tipoIdx == 0)
				res += "Entre as quantitativas: contável → discreta; mensurável → contínua.\\(\\\\\\)";
			res += "Esta variável " + justificativa + ".\\(\\\\\\)";
			res += "Classificação: " + tipoCorreto + ".";
		}
		else
		{
			res = "A variável \"" + descricao + "\" é qualitativa (expressa uma categoria, não um valor numérico).\\(\\\\\\)";
			if (tipoIdx == 2)
				res += "Entre as qualitativas: sem ordem entre categorias → nominal; com ordem → ordinal.\\(\\\\\\)";
			res += "Esta variável " + justificativa + ".\\(\\\\\\)";
			res += "Classificação: " + tipoCorreto + ".";
		}
		setResolucao(res);
	}
}
