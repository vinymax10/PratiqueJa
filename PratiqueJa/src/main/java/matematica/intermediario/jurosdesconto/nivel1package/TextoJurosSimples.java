package matematica.intermediario.jurosdesconto.nivel1package;

import java.util.Random;

public class TextoJurosSimples
{
	static ProblemaJurosSimples  problemas[]= {
	new ProblemaJurosSimples(
	"Um investidor aplica R$ $c,00 em um fundo que rende $i% ao mês. Quanto ele terá de juros após $t meses?",
	TipoJurosSimples.XCIT),
	new ProblemaJurosSimples(
	"Um empréstimo de R$ $c,00 foi tomado com uma taxa de juros simples de $i% ao mês por $t meses. Qual o valor total a ser pago ao final do período?",
	TipoJurosSimples.XJCIT),
	new ProblemaJurosSimples(
	"Um capital de R$ $c,00 foi investido a uma taxa de $i% ao mês. Qual será o valor dos juros após $t meses?",
	TipoJurosSimples.XCIT),
	new ProblemaJurosSimples(
	"João emprestou R$ $c,00 a um amigo com uma taxa de $i% ao mês. Se o empréstimo for pago em $t meses, quanto João receberá de juros?",
	TipoJurosSimples.XCIT),
	new ProblemaJurosSimples(
	"Uma loja vende um produto financiado por R$ $c,00, com juros simples de $i% ao mês por $t meses. Qual será o valor final do produto?",
	TipoJurosSimples.XJCIT),
	new ProblemaJurosSimples(
	"Um banco oferece um investimento com juros simples de $i% ao mês. Se você aplicar R$ $c,00 por $t meses, quanto receberá de juros?",
	TipoJurosSimples.XCIT),
	new ProblemaJurosSimples(
	"Uma dívida de R$ $c,00 tem juros simples de $i% ao mês. Se o pagamento for feito após $t meses, qual será o valor total pago?",
	TipoJurosSimples.XJCIT),
	new ProblemaJurosSimples(
	"Pedro fez uma aplicação de R$ $c,00 a uma taxa de $i% ao mês. Qual o total de juros que ele ganhará após $t meses?",
	TipoJurosSimples.XCIT),
	new ProblemaJurosSimples(
	"Um comerciante toma um empréstimo de  R$ $c,00 com taxa de juros simples de $i% ao mês por $t meses. Qual o valor dos juros?",
	TipoJurosSimples.XCIT),
	new ProblemaJurosSimples(
	"Uma empresa investiu R$ $c,00 a uma taxa de $i% ao semestre. Qual o montante ao final de $t semestres?",
	TipoJurosSimples.XJCIT),
	new ProblemaJurosSimples(
	"Se um capital de  R$ $c,00 render juros simples de $i% ao mês por $t meses, quanto será o montante final?",
	TipoJurosSimples.XJCIT),
	new ProblemaJurosSimples(
	"Uma pessoa investe  R$ $c,00 em uma aplicação com juros simples de $i% ao trimestre. Após $t anos, qual será o total de juros acumulado?",
	TipoJurosSimples.XCIT),
	new ProblemaJurosSimples(
	"Um banco cobra juros simples de $i% ao mês sobre um empréstimo de  R$ $c,00. Se o cliente pagar após $t meses, quanto ele pagará de juros?",
	TipoJurosSimples.XCIT),
	new ProblemaJurosSimples(
	"Maria pegou um empréstimo de R$ $c,00 com juros simples de $i% ao ano. Quanto pagará de juros após $t anos?",
	TipoJurosSimples.XCIT),
	new ProblemaJurosSimples(
	"Uma aplicação de R$ $c,00 rende juros simples de $i% ao mês. Quanto o investidor terá após $t meses?",
	TipoJurosSimples.XJCIT),
	new ProblemaJurosSimples(
	"Um comerciante financia um equipamento por R$ $c,00 com uma taxa de juros simples de $i% ao mês. Se ele pagar em $t meses, quanto pagará no total?",
	TipoJurosSimples.XJCIT),
	new ProblemaJurosSimples(
	"Um investimento de R$ $c,00 foi aplicado a uma taxa de $i% ao semestre. Qual o montante acumulado após $t semestres?",
	TipoJurosSimples.XCIT),
	new ProblemaJurosSimples(
	"Um empréstimo de R$ $c,00 teve um total de juros de R$ $j,00 após $t meses. Qual foi a taxa de juros mensal aplicada?",
	TipoJurosSimples.JCXT),
	new ProblemaJurosSimples(
	" Um investidor quer obter R$ $j,00 de juros ao final de $t meses aplicando R$ $c,00. Qual deve ser a taxa de juros mensal dessa aplicação?",
	TipoJurosSimples.JCXT),
	new ProblemaJurosSimples(
	"Qual é o juro simples obtido ao aplicar um capital de R$ $c,00 durante $t anos a uma taxa de $i% ao ano?",
	TipoJurosSimples.XCIT),
	new ProblemaJurosSimples(
	"Um investidor aplicou R$ $c,00 por $t meses a uma taxa de $i% ao mês. Qual foi o juro gerado?",
	TipoJurosSimples.XCIT),
	new ProblemaJurosSimples(
	"Se um empréstimo de R$ $c,00 foi tomado por $t meses a uma taxa de $i% ao mês, qual será o juro total pago?",
	TipoJurosSimples.XCIT),
	new ProblemaJurosSimples(
	"Uma quantia de R$ $c,00  foi aplicada a uma taxa de $i% ao ano por um período de $t anos. Quanto será o juro gerado?",
	TipoJurosSimples.XCIT),
	new ProblemaJurosSimples(
	"Um banco cobra uma taxa de $i% ao mês sobre um empréstimo de R$ $c,00. Se o tempo for de $t meses, qual será o juro a ser pago?",
	TipoJurosSimples.XCIT),
	new ProblemaJurosSimples(
	"Um investimento gerou um juro de R$ $j,00 após $t anos a uma taxa de $i% ao ano. Qual era o capital inicial?",
	TipoJurosSimples.JXIT),
	new ProblemaJurosSimples(
	"Se um empréstimo resultou em um juro de R$ $j,00 após $t meses a uma taxa de $i% ao mês, qual foi o valor emprestado?",
	TipoJurosSimples.JXIT),
	new ProblemaJurosSimples(
	"O juro total de uma aplicação foi de R$ $j,00 após $t meses, considerando uma taxa de $i% ao mês. Qual era o capital inicial?",
	TipoJurosSimples.JXIT),
	new ProblemaJurosSimples(
	"Após $t anos investindo a uma taxa de $i% ao ano, obteve-se um juro de R$ $j,00. Qual era o capital aplicado?",
	TipoJurosSimples.JXIT),
	new ProblemaJurosSimples(
	"Um empréstimo de duração $t meses gerou um juro de R$ $j,00 a uma taxa de $i% ao mês. Quanto foi emprestado inicialmente?",
	TipoJurosSimples.JXIT),
	new ProblemaJurosSimples(
	"Um investimento inicial de R$ $c,00 gerou um juro de R$ $j,00 após $t anos. Qual foi a taxa de juros ao ano?",
	TipoJurosSimples.JCXT),
	new ProblemaJurosSimples(
	"Se um capital de R$ $c,00 gerou um juro de R$ $j,00 em $t meses, qual foi a taxa de juros mensal aplicada?",
	TipoJurosSimples.JCXT),
	new ProblemaJurosSimples(
	"Um empréstimo de R$ $c,00 resultou em um juro de R$ $j,00 após $t dias. Qual foi a taxa de juros diária aplicada?",
	TipoJurosSimples.JCXT),
	new ProblemaJurosSimples(
	"Após $t anos, um investimento de R$ $c,00 resultou em um juro de R$ $j,00. Qual a taxa de juros anual?",
	TipoJurosSimples.JCXT),
	new ProblemaJurosSimples(
	"Uma aplicação de R$ $c,00 gerou um juro de R$ $j,00 ao longo de $t meses. Qual foi a taxa de juros mensal?",
	TipoJurosSimples.JCXT),
	new ProblemaJurosSimples(
	"Um capital de R$ $c,00 foi aplicado a uma taxa de $i% ao ano e gerou um juro de R$ $j,00. Por quantos anos ele ficou investido?",
	TipoJurosSimples.JCIX),
	new ProblemaJurosSimples(
	"Um empréstimo de R$ $c,00 gerou um juro de R$ $j,00 a uma taxa de $i% ao mês. Qual foi o tempo do empréstimo em meses?",
	TipoJurosSimples.JCIX),
	new ProblemaJurosSimples(
	"Uma aplicação de R$ $c,00 a uma taxa de $i% ao mês gerou um juro de R$ $j,00. Por quantos meses o dinheiro ficou investido?",
	TipoJurosSimples.JCIX),
	new ProblemaJurosSimples(
	"Se um capital de R$ $c,00 foi investido a uma taxa de $i% ao ano e gerou um juro de R$ $j,00, quanto tempo durou o investimento?",
	TipoJurosSimples.JCIX),
	new ProblemaJurosSimples(
	"Um empréstimo de R$ $c,00 foi concedido a uma taxa de $i% ao mês e resultou em um juro de R$ $j,00. Qual foi a duração do empréstimo em meses?",
	TipoJurosSimples.JCIX),
	new ProblemaJurosSimples(
	"Qual é o juro obtido ao aplicar um capital de R$ $c,00 durante $t anos a uma taxa de $i% ao ano?",
	TipoJurosSimples.XCIT),
	new ProblemaJurosSimples(
	"Se um capital de R$ $c,00 for investido por $t meses a uma taxa de $i% ao mês, qual será o juro gerado?",
	TipoJurosSimples.XCIT),
	new ProblemaJurosSimples(
	"Um empréstimo de R$ $c,00 foi feito por $t meses a uma taxa de $i% ao mês. Quanto será pago de juros?",
	TipoJurosSimples.XCIT),
	new ProblemaJurosSimples(
	"Um capital de R$ $c,00 foi aplicado por $t anos a uma taxa de $i% ao ano. Qual será o juro total?",
	TipoJurosSimples.XCIT),
	new ProblemaJurosSimples(
	"Um banco empresta R$ $c,00 a uma taxa de $i% ao mês por $t meses. Qual será o valor do juro?",
	TipoJurosSimples.XCIT),
	new ProblemaJurosSimples(
	"Um investimento gerou um juro de R$ $j,00 após $t anos a uma taxa de $i% ao ano. Qual era o capital inicial?",
	TipoJurosSimples.JXIT),
	new ProblemaJurosSimples(
	"Se um empréstimo resultou em um juro de R$ $j,00 após $t meses a uma taxa de $i% ao mês, qual foi o valor emprestado?",
	TipoJurosSimples.JXIT),
	new ProblemaJurosSimples(
	"Um juro de R$ $j,00 foi gerado por um capital aplicado a uma taxa de $i% ao mês durante $t meses. Qual era o capital inicial?",
	TipoJurosSimples.JXIT),
	new ProblemaJurosSimples(
	"Uma aplicação gerou um juro de R$ $j,00 em $t anos, considerando uma taxa de $i% ao ano. Qual era o capital aplicado?",
	TipoJurosSimples.JXIT),
	new ProblemaJurosSimples(
	"Se um empréstimo gerou um juro de R$ $j,00 em $t meses a uma taxa de $i% ao mês, qual foi o capital emprestado?",
	TipoJurosSimples.JXIT),
	new ProblemaJurosSimples(
	"Um capital de R$ $c,00 gerou um juro de R$ $j,00 após $t anos. Qual foi a taxa de juros anual?",
	TipoJurosSimples.JCXT),
	new ProblemaJurosSimples(
	"Se um investimento de R$ $c,00 resultou em um juro de R$ $j,00 após $t meses, qual foi a taxa de juros mensal aplicada?",
	TipoJurosSimples.JCXT),
	new ProblemaJurosSimples(
	"Um empréstimo de R$ $c,00 teve um juro de R$ $j,00 após $t dias. Qual foi a taxa de juros diária?",
	TipoJurosSimples.JCXT),
	new ProblemaJurosSimples(
	"Após $t anos, um capital de R$ $c,00 gerou um juro de R$ $j,00. Qual foi a taxa de juros ao ano?",
	TipoJurosSimples.JCXT),
	new ProblemaJurosSimples(
	"Uma aplicação de R$ $c,00 rendeu um juro de R$ $j,00 ao longo de $t meses. Qual foi a taxa de juros mensal?",
	TipoJurosSimples.JCXT),
	new ProblemaJurosSimples(
	"Um capital de R$ $c,00 foi aplicado a uma taxa de $i% ao ano e gerou um juro de R$ $j,00. Por quantos anos ele ficou investido?",
	TipoJurosSimples.JCIX),
	new ProblemaJurosSimples(
	"Um empréstimo de R$ $c,00 teve um juro de R$ $j,00 a uma taxa de $i% ao mês. Qual foi o tempo do empréstimo em meses?",
	TipoJurosSimples.JCIX),
	new ProblemaJurosSimples(
	"Um capital de R$ $c,00 foi investido a uma taxa de $i% ao mês e gerou um juro de R$ $j,00. Por quantos meses ele ficou investido?",
	TipoJurosSimples.JCIX),
	new ProblemaJurosSimples(
	"Após $t anos, um capital de R$ $c,00 gerou um juro de R$ $j,00 a uma taxa de $i% ao ano. Quanto tempo durou o investimento?",
	TipoJurosSimples.JCIX),
	new ProblemaJurosSimples(
	"Um empréstimo de R$ $c,00 resultou em um juro de R$ $j,00 a uma taxa de $i% ao mês. Qual foi o período do empréstimo?",
	TipoJurosSimples.JCIX),
	};
	
	public static ProblemaJurosSimples getProblemaJuros()
	{
		Random rand=new Random();
		int index=rand.nextInt(problemas.length);
		return problemas[index].clone();
	}
	
}
