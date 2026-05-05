package Matematica.Avancado.JurosDesconto.Nivel3Package;

import java.util.Random;

public class TextoJurosComposto
{
	static ProblemaJurosComposto  problemas[]= {
	new ProblemaJurosComposto(
	"Um capital de R\\(\\$\\) $c foi investido em um regime de juros compostos com uma taxa de $i\\(\\%\\) ao ano por $t anos. Qual será o montante ao final do período?",
	TipoJurosComposto.XCIT),
	new ProblemaJurosComposto(
	"Uma aplicação inicial de R\\(\\$\\) $c é feita a uma taxa de juros compostos de $i\\(\\%\\) ao mês durante $t meses. Qual será o montante acumulado?",
	TipoJurosComposto.XCIT),
	new ProblemaJurosComposto(
	"Um empréstimo de R\\(\\$\\) $c foi contratado com juros compostos a uma taxa de $i\\(\\%\\) ao dia por $t dias. Qual será o valor total a ser pago ao final?",
	TipoJurosComposto.XCIT),
	new ProblemaJurosComposto(
	"Um depósito de R\\(\\$\\) $c cresce sob a regra de juros compostos, com uma taxa de $i\\(\\%\\) ao ano por $t anos. Qual será o saldo final?",
	TipoJurosComposto.XCIT),
	new ProblemaJurosComposto(
	"Se um investimento inicial de R\\(\\$\\) $c for aplicado a uma taxa de juros compostos de $i\\(\\%\\) ao mês, qual será o valor acumulado após $t meses?",
	TipoJurosComposto.XCIT),
	new ProblemaJurosComposto(
	"Um investimento resultou em um montante de R\\(\\$\\) $m após $t anos sob um regime de juros compostos com taxa de $i\\(\\%\\) ao ano. Qual era o capital inicial?",
	TipoJurosComposto.MXIT),
	new ProblemaJurosComposto(
	"Um empréstimo foi concedido sob juros compostos, resultando em um montante de R\\(\\$\\) $m após $t meses a uma taxa de $i\\(\\%\\) ao mês. Qual foi o valor inicial emprestado?",
	TipoJurosComposto.MXIT),
	new ProblemaJurosComposto(
	"Um montante de R\\(\\$\\) $m foi acumulado após $t dias com uma taxa de $i\\(\\%\\) ao dia, considerando juros compostos. Qual era o capital investido?",
	TipoJurosComposto.MXIT),
	new ProblemaJurosComposto(
	"Um financiamento seguiu a regra de juros compostos e, após $t anos a uma taxa de $i\\(\\%\\) ao ano, chegou ao valor de R\\(\\$\\) $m. Qual foi o valor inicial financiado?",
	TipoJurosComposto.MXIT),
	new ProblemaJurosComposto(
	"Um investimento cresceu para R\\(\\$\\) $m após $t meses sob a influência de juros compostos com taxa de $i\\(\\%\\) ao mês. Qual foi o valor inicial aplicado?",
	TipoJurosComposto.MXIT),
	new ProblemaJurosComposto(
	"Um capital de R\\(\\$\\) $c foi aplicado sob juros compostos a uma taxa de $i\\(\\%\\) ao ano e resultou em um montante de R\\(\\$\\) $m. Por quantos anos ele ficou investido?",
	TipoJurosComposto.MCIX),
	new ProblemaJurosComposto(
	"Um empréstimo de R\\(\\$\\) $c gerou um montante de R\\(\\$\\) $m a uma taxa de $i\\(\\%\\) ao mês, considerando juros compostos. Qual foi o tempo do empréstimo em meses?",
	TipoJurosComposto.MCIX),
	new ProblemaJurosComposto(
	"Uma aplicação inicial de R\\(\\$\\) $c foi feita com juros compostos a uma taxa de $i\\(\\%\\) ao dia e resultou em um montante de R\\(\\$\\) $m. Por quantos dias o dinheiro ficou investido?",
	TipoJurosComposto.MCIX),
	new ProblemaJurosComposto(
	"Um capital de R\\(\\$\\) $c cresceu para R\\(\\$\\) $m a uma taxa de $i\\(\\%\\) ao ano, seguindo juros compostos. Qual foi a duração do investimento?",
	TipoJurosComposto.MCIX),
	new ProblemaJurosComposto(
	"Um financiamento de R\\(\\$\\) $c cresceu para R\\(\\$\\) $m a uma taxa de $i\\(\\%\\) ao mês sob juros compostos. Em quanto tempo esse montante foi alcançado?",
	TipoJurosComposto.MCIX),
	new ProblemaJurosComposto(
	"Um capital de R\\(\\$\\) $c foi investido em um regime de juros compostos com uma taxa de $i\\(\\%\\) ao ano por $t anos. Qual será o juros ao final do período?",
	TipoJurosComposto.XXCIT),
	new ProblemaJurosComposto(
	"Uma aplicação inicial de R\\(\\$\\) $c é feita a uma taxa de juros compostos de $i\\(\\%\\) ao mês durante $t meses. Qual será o juros acumulado?",
	TipoJurosComposto.XXCIT),
	new ProblemaJurosComposto(
	"Um empréstimo de R\\(\\$\\) $c foi contratado com juros compostos a uma taxa de $i\\(\\%\\) ao dia por $t dias. Qual será o valor do juros a ser pago ao final?",
	TipoJurosComposto.XXCIT),
	new ProblemaJurosComposto(
	"Um depósito de R\\(\\$\\) $c cresce sob a regra de juros compostos, com uma taxa de $i\\(\\%\\) ao ano por $t anos. Qual será o juros final?",
	TipoJurosComposto.XXCIT),
	new ProblemaJurosComposto(
	"Se um investimento inicial de R\\(\\$\\) $c for aplicado a uma taxa de juros compostos de $i\\(\\%\\) ao mês, qual será o valor do juros após $t meses?",
	TipoJurosComposto.XXCIT),
	new ProblemaJurosComposto(
	"Um investimento resultou em um montante de R\\(\\$\\) $m após $t anos sob um regime de juros compostos com taxa de $i\\(\\%\\) ao ano. Qual será o valor do juros?",
	TipoJurosComposto.XMXIT),
	new ProblemaJurosComposto(
	"Um empréstimo foi concedido sob juros compostos, resultando em um montante de R\\(\\$\\) $m após $t meses a uma taxa de $i\\(\\%\\) ao mês. Qual será o valor do juros?",
	TipoJurosComposto.XMXIT),
	new ProblemaJurosComposto(
	"Um montante de R\\(\\$\\) $m foi acumulado após $t dias com uma taxa de $i\\(\\%\\) ao dia, considerando juros compostos. Qual será o valor do juros?",
	TipoJurosComposto.XMXIT),
	new ProblemaJurosComposto(
	"Um financiamento seguiu a regra de juros compostos e, após $t anos a uma taxa de $i\\(\\%\\) ao ano, chegou ao valor de R\\(\\$\\) $m. Qual será o valor do juros?",
	TipoJurosComposto.XMXIT),
	new ProblemaJurosComposto(
	"Um investimento cresceu para R\\(\\$\\) $m após $t meses sob a influência de juros compostos com taxa de $i\\(\\%\\) ao mês. Qual será o valor do juros?",
	TipoJurosComposto.XMXIT),
	new ProblemaJurosComposto(
	"Um capital de R\\(\\$\\) $c foi investido sob o regime de juros compostos a uma taxa de $i\\(\\%\\) ao período. Sabendo que o montante acumulado foi de R\\(\\$\\) $m, descubra em quanto tempo essa quantia foi obtida.",
	TipoJurosComposto.MCIX),
	new ProblemaJurosComposto(
	"Um montante final de R\\(\\$\\) $m foi obtido após $t períodos de aplicação sob juros compostos. Se a taxa de juros foi de $i\\(\\%\\) ao período, qual foi o capital inicial aplicado?",
	TipoJurosComposto.MXIT),
	new ProblemaJurosComposto(
	"Qual montante será obtido após $t períodos se um capital de R\\(\\$\\) $c for investido sob juros compostos com taxa de $i\\(\\%\\) ao período?",
	TipoJurosComposto.XCIT),
	new ProblemaJurosComposto(
	"Um montante de R\\(\\$\\) $m foi obtido a partir de um investimento de R\\(\\$\\) $c sob juros compostos com taxa $i\\(\\%\\) ao período. Quantos períodos $t foram necessários para atingir esse valor? ",
	TipoJurosComposto.MCIX),
	new ProblemaJurosComposto(
	"Qual deve ser o capital inicial para que, após $t períodos, a um juros composto de $i\\(\\%\\), se obtenha um montante de R\\(\\$\\) $m?",
	TipoJurosComposto.MXIT),
	new ProblemaJurosComposto(
	"Se um investidor deseja obter um montante de R\\(\\$\\) $m após $t períodos com uma taxa de juros composta de $i\\(\\%\\), qual deve ser o capital inicial aplicado? ",
	TipoJurosComposto.MXIT),
	new ProblemaJurosComposto(
	"Um montante de R\\(\\$\\) $m foi alcançado a partir de um capital de R\\(\\$\\) $c sob juros compostos com taxa $i\\(\\%\\) ao período. Descubra o tempo necessário para esse crescimento.",
	TipoJurosComposto.MCIX),
	new ProblemaJurosComposto(
	"Se um investidor aplicou R\\(\\$\\) $c a uma taxa composta de $i\\(\\%\\) ao período, qual será o montante após $t períodos?",
	TipoJurosComposto.XCIT),
	};

	public static ProblemaJurosComposto getProblemaJuros()
	{
		Random rand=new Random();
		int index=rand.nextInt(problemas.length);
		return problemas[index].clone();
	}
	
}
