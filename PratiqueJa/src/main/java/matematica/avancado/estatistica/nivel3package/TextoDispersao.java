package matematica.avancado.estatistica.nivel3package;

import java.util.Random;

public class TextoDispersao
{
	static ProblemaDispersao  problemas[]= {
	new ProblemaDispersao("As notas de 5 alunos foram: $v. Qual é a variância dessas notas?", TipoDispersao.Variancia),
	new ProblemaDispersao("As idades (em anos) de 5 pessoas são: $v. Qual é a variância das idades?", TipoDispersao.Variancia),
	new ProblemaDispersao("As temperaturas (em °C) medidas em 5 dias foram: $v. Qual é a variância?", TipoDispersao.Variancia),
	new ProblemaDispersao("Os tempos (em minutos) de 5 corredores foram: $v. Qual é a variância dos tempos?", TipoDispersao.Variancia),
	new ProblemaDispersao("As quantidades produzidas em 5 dias foram: $v. Qual é a variância da produção?", TipoDispersao.Variancia),
	new ProblemaDispersao("Os pesos (em kg) de 5 pacotes são: $v. Qual é a variância dos pesos?", TipoDispersao.Variancia),
//
	new ProblemaDispersao("As notas de 5 alunos foram: $v. Qual é o desvio padrão dessas notas?", TipoDispersao.DesvioPadrao),
	new ProblemaDispersao("As idades (em anos) de 5 pessoas são: $v. Qual é o desvio padrão das idades?", TipoDispersao.DesvioPadrao),
	new ProblemaDispersao("As temperaturas (em °C) medidas em 5 dias foram: $v. Qual é o desvio padrão?", TipoDispersao.DesvioPadrao),
	new ProblemaDispersao("Os tempos (em minutos) de 5 corredores foram: $v. Qual é o desvio padrão dos tempos?", TipoDispersao.DesvioPadrao),
	new ProblemaDispersao("As alturas (em cm acima de 1 m) de 5 plantas são: $v. Qual é o desvio padrão?", TipoDispersao.DesvioPadrao),
	new ProblemaDispersao("Os pontos obtidos por 5 jogadores foram: $v. Qual é o desvio padrão das pontuações?", TipoDispersao.DesvioPadrao)
	};

	public static ProblemaDispersao getProblema()
	{
		Random rand=new Random();
		int index=rand.nextInt(problemas.length);
		return problemas[index].clone();
	}

}
