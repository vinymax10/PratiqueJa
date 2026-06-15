package matematica.avancado.estatistica.nivel3package;

import java.util.Random;

public class TextoQuartis
{
	static ProblemaQuartis  problemas[]= {
	new ProblemaQuartis("Os dados em ordem crescente são: $v. Qual é o primeiro quartil \\(Q_1\\)?", TipoQuartil.Q1),
	new ProblemaQuartis("As notas ordenadas de um grupo são: $v. Qual é o primeiro quartil \\(Q_1\\)?", TipoQuartil.Q1),
	new ProblemaQuartis("As idades ordenadas de 7 pessoas são: $v. Qual é o valor de \\(Q_1\\)?", TipoQuartil.Q1),
//
	new ProblemaQuartis("Os dados em ordem crescente são: $v. Qual é a mediana \\(Q_2\\)?", TipoQuartil.Q2),
	new ProblemaQuartis("As temperaturas ordenadas (em °C) foram: $v. Qual é a mediana \\(Q_2\\)?", TipoQuartil.Q2),
	new ProblemaQuartis("Os tempos ordenados (em minutos) de 7 atletas foram: $v. Qual é a mediana \\(Q_2\\)?", TipoQuartil.Q2),
//
	new ProblemaQuartis("Os dados em ordem crescente são: $v. Qual é o terceiro quartil \\(Q_3\\)?", TipoQuartil.Q3),
	new ProblemaQuartis("As notas ordenadas de um grupo são: $v. Qual é o terceiro quartil \\(Q_3\\)?", TipoQuartil.Q3),
	new ProblemaQuartis("Os salários ordenados (em milhares) de 7 funcionários são: $v. Qual é o valor de \\(Q_3\\)?", TipoQuartil.Q3),
//
	new ProblemaQuartis("Os dados em ordem crescente são: $v. Qual é a amplitude interquartílica \\(IQR = Q_3 - Q_1\\)?", TipoQuartil.IQR),
	new ProblemaQuartis("As idades ordenadas de 7 pessoas são: $v. Qual é a amplitude interquartílica \\(IQR\\)?", TipoQuartil.IQR),
	new ProblemaQuartis("As notas ordenadas de um grupo são: $v. Qual é a amplitude interquartílica \\(IQR\\)?", TipoQuartil.IQR)
	};

	public static ProblemaQuartis getProblema()
	{
		Random rand=new Random();
		int index=rand.nextInt(problemas.length);
		return problemas[index].clone();
	}

}
