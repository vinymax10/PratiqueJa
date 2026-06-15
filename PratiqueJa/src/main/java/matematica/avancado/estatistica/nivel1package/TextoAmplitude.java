package matematica.avancado.estatistica.nivel1package;

import java.util.Random;

public class TextoAmplitude
{
	static ProblemaAmplitude  problemas[]= {
	new ProblemaAmplitude("As temperaturas (em °C) registradas durante a semana foram: $v. Qual é a amplitude térmica?"),
	new ProblemaAmplitude("As idades dos participantes de um evento são: $v. Qual é a amplitude das idades?"),
	new ProblemaAmplitude("As notas de uma turma foram: $v. Qual é a amplitude das notas?"),
	new ProblemaAmplitude("Os preços (em reais) de um produto em várias lojas são: $v. Qual é a amplitude dos preços?"),
	new ProblemaAmplitude("As alturas (em cm) de alguns alunos são: $v. Qual é a amplitude das alturas?"),
	new ProblemaAmplitude("Os tempos (em minutos) de prova de atletas foram: $v. Qual é a amplitude dos tempos?"),
	new ProblemaAmplitude("As quantidades de chuva (em mm) por mês foram: $v. Qual é a amplitude pluviométrica?"),
	new ProblemaAmplitude("Os pesos (em kg) de algumas encomendas são: $v. Qual é a amplitude dos pesos?"),
	new ProblemaAmplitude("As pontuações obtidas em um campeonato foram: $v. Qual é a amplitude das pontuações?"),
	new ProblemaAmplitude("Os números de visitantes diários de um museu foram: $v. Qual é a amplitude?"),
	new ProblemaAmplitude("Os salários (em centenas de reais) de funcionários são: $v. Qual é a amplitude salarial?"),
	new ProblemaAmplitude("As velocidades (em km/h) medidas em um trecho foram: $v. Qual é a amplitude das velocidades?"),
	new ProblemaAmplitude("As quantidades de produtos vendidos por dia foram: $v. Qual é a amplitude das vendas?"),
	new ProblemaAmplitude("As notas de avaliação de um serviço foram: $v. Qual é a amplitude?")
	};

	public static ProblemaAmplitude getProblema()
	{
		Random rand=new Random();
		int index=rand.nextInt(problemas.length);
		return problemas[index].clone();
	}

}
