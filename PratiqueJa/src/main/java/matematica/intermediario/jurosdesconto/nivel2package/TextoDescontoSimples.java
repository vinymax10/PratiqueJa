package matematica.intermediario.jurosdesconto.nivel2package;

import java.util.Random;

public class TextoDescontoSimples
{
	static ProblemaDescontoSimples  problemas[]= {
	new ProblemaDescontoSimples(
	"Um título de valor nominal R\\(\\$\\) $n,00 foi descontado a uma taxa de $i\\(\\%\\) ao mês durante $t meses. Qual o valor atual do título?",
	TipoDescontoSimples.XDNIT),
	new ProblemaDescontoSimples(
	"Um banco oferece desconto simples sobre um título de R\\(\\$\\) $n,00 que vence em $t meses, com uma taxa de $i\\(\\%\\) ao mês. Qual será o valor atual desse título?",
	TipoDescontoSimples.XDNIT),
	new ProblemaDescontoSimples(
	"Um título tem valor nominal de R\\(\\$\\) $n,00 e foi descontado por $t meses a uma taxa de $i\\(\\%\\) ao mês. Qual foi o valor do desconto $d?",
	TipoDescontoSimples.XNIT),
	new ProblemaDescontoSimples(
	"Um título com valor nominal de R\\(\\$\\) $n,00 foi descontado a uma taxa de $i\\(\\%\\) ao mês durante $t meses. Qual foi o valor do desconto aplicado?",
	TipoDescontoSimples.XNIT),
	new ProblemaDescontoSimples(
	"Um investidor antecipa um título de R\\(\\$\\) $n,00 com taxa de desconto de $i\\(\\%\\) ao mês por $t meses. Quanto foi descontado?",
	TipoDescontoSimples.XNIT),
	new ProblemaDescontoSimples(
	"Um banco oferece desconto simples sobre um título de R\\(\\$\\) $n,00, aplicando uma taxa de $i\\(\\%\\) ao mês por $t meses. Qual o desconto total?",
	TipoDescontoSimples.XNIT),
	new ProblemaDescontoSimples(
	"Uma empresa antecipa um título de R\\(\\$\\) $n,00 a uma taxa de $i\\(\\%\\) ao mês durante $t meses. Qual será o valor do desconto?",
	TipoDescontoSimples.XNIT),
	new ProblemaDescontoSimples(
	"Um comerciante precisou antecipar um título de R\\(\\$\\) $n,00. O banco aplicou uma taxa de $i\\(\\%\\) ao mês por $t meses. Qual o valor do desconto?",
	TipoDescontoSimples.XNIT),
	new ProblemaDescontoSimples(
	"Um cliente solicita o desconto de um título de R\\(\\$\\) $n,00 por $t meses com taxa de $i\\(\\%\\) ao mês. Qual será o valor descontado?",
	TipoDescontoSimples.XNIT),
	new ProblemaDescontoSimples(
	"Uma empresa financeira aplicou um desconto simples sobre um título de R\\(\\$\\) $n,00, utilizando uma taxa de $i\\(\\%\\) ao mês durante $t meses. Qual foi o valor do desconto?",
	TipoDescontoSimples.XNIT),
	new ProblemaDescontoSimples(
	"O desconto aplicado sobre um título de R\\(\\$\\) $n,00 foi calculado com taxa de $i\\(\\%\\) ao mês por $t meses. Qual foi o desconto?",
	TipoDescontoSimples.XNIT),
	new ProblemaDescontoSimples(
	"Um título de valor nominal R\\(\\$\\) $n,00 foi descontado a uma taxa de $i\\(\\%\\) ao mês durante $t meses. Qual foi o desconto aplicado?",
	TipoDescontoSimples.XNIT),
	new ProblemaDescontoSimples(
	"Um investidor antecipou um título de R\\(\\$\\) $n,00 com uma taxa de $i\\(\\%\\) ao mês durante $t meses. Qual foi o desconto?",
	TipoDescontoSimples.XNIT),
	new ProblemaDescontoSimples(
	"Um banco aplicou um desconto simples sobre um título de R\\(\\$\\) $n,00, utilizando uma taxa de $i\\(\\%\\) ao mês por $t meses. Qual foi o valor descontado?",
	TipoDescontoSimples.XNIT),
	new ProblemaDescontoSimples(
	"Um comerciante precisou antecipar um pagamento de R\\(\\$\\) $n,00 e o banco aplicou um desconto de $i\\(\\%\\) ao mês por $t meses. Quanto foi descontado?",
	TipoDescontoSimples.XNIT),
	new ProblemaDescontoSimples(
	"Um título de R\\(\\$\\) $n,00 foi descontado a uma taxa de $i\\(\\%\\) ao mês durante $t meses. Qual foi o valor do desconto?",
	TipoDescontoSimples.XNIT),
	new ProblemaDescontoSimples(
	"Um banco aplica desconto simples em títulos. Se um título de R\\(\\$\\) $n,00 foi antecipado com taxa de $i\\(\\%\\) ao mês por $t meses, qual o valor do desconto?",
	TipoDescontoSimples.XNIT),
	new ProblemaDescontoSimples(
	"Um título de R\\(\\$\\) $n,00 foi resgatado antes do vencimento com uma taxa de $i\\(\\%\\) ao mês por $t meses. Qual foi o desconto?",
	TipoDescontoSimples.XNIT),
	new ProblemaDescontoSimples(
	"Um investidor antecipou um título de R\\(\\$\\) $n,00 e sofreu um desconto simples. Se a taxa foi $i\\(\\%\\) ao mês e o tempo foi $t meses, qual foi o desconto?",
	TipoDescontoSimples.XNIT),
	new ProblemaDescontoSimples(
	"Um título de R\\(\\$\\) $n,00 foi descontado a uma taxa de $i\\(\\%\\) ao mês por $t meses. Qual será o valor atual recebido?",
	TipoDescontoSimples.XDNIT),
	new ProblemaDescontoSimples(
	"Um comerciante antecipou um título de R\\(\\$\\) $n,00 com taxa de $i\\(\\%\\) ao mês durante $t meses. Quanto ele recebeu após o desconto?",
	TipoDescontoSimples.XDNIT),
	new ProblemaDescontoSimples(
	"Um banco aplicou desconto simples sobre um título de R\\(\\$\\) $n,00, utilizando uma taxa de $i\\(\\%\\) ao mês por $t meses. Qual será o valor atual?",
	TipoDescontoSimples.XDNIT),
	new ProblemaDescontoSimples(
	"Uma empresa de factoring antecipou um título de R\\(\\$\\) $n,00 com taxa de $i\\(\\%\\) ao mês por $t meses. Qual foi o valor recebido?",
	TipoDescontoSimples.XDNIT),
	new ProblemaDescontoSimples(
	"Um investidor vendeu um título de R\\(\\$\\) $n,00 antes do vencimento. O banco aplicou uma taxa de $i\\(\\%\\) ao mês por $t meses. Qual foi o valor líquido recebido?",
	TipoDescontoSimples.XDNIT),
	new ProblemaDescontoSimples(
	"Um empresário antecipou um título de R\\(\\$\\) $n,00, sendo aplicado um desconto simples com taxa de $i\\(\\%\\) ao mês por $t meses. Qual o valor atual?",
	TipoDescontoSimples.XDNIT),
	new ProblemaDescontoSimples(
	"O desconto simples de um título foi calculado com valor nominal R\\(\\$\\) $n,00, taxa de $i\\(\\%\\) ao mês e prazo de $t meses. Qual será o valor atual?",
	TipoDescontoSimples.XDNIT),
	new ProblemaDescontoSimples(
	"Quanto uma pessoa recebeu após descontar um título de R\\(\\$\\) $n,00 com taxa de $i\\(\\%\\) ao mês por $t meses?",
	TipoDescontoSimples.XDNIT),
	new ProblemaDescontoSimples(
	"Um título de R\\(\\$\\) $n,00 foi descontado a uma taxa de $i\\(\\%\\) ao mês durante $t meses. Qual será o valor atual recebido?",
	TipoDescontoSimples.XDNIT),
	new ProblemaDescontoSimples(
	"Um investidor antecipou um título de R\\(\\$\\) $n,00 com taxa de $i\\(\\%\\) ao mês por $t meses. Qual será o valor atual recebido?",
	TipoDescontoSimples.XDNIT),
	new ProblemaDescontoSimples(
	"Um banco aplica desconto simples sobre títulos. Se um título de R\\(\\$\\) $n,00 foi antecipado com taxa de $i\\(\\%\\) ao mês por $t meses, qual será o valor atual?",
	TipoDescontoSimples.XDNIT),
	new ProblemaDescontoSimples(
	"Um título de R\\(\\$\\) $n,00 sofreu um desconto simples a uma taxa de $i\\(\\%\\) ao mês por $t meses. Qual foi o valor atual recebido?",
	TipoDescontoSimples.XDNIT),
	new ProblemaDescontoSimples(
	"Um comerciante descontou um título de R\\(\\$\\) $n,00 a uma taxa de $i\\(\\%\\) ao mês por $t meses. Qual o valor atual?",
	TipoDescontoSimples.XDNIT),
	new ProblemaDescontoSimples(
	"Um banco aplicou um desconto sobre um título de R\\(\\$\\) $n,00 a uma taxa de $i\\(\\%\\) ao mês por $t meses. Qual foi o valor recebido?",
	TipoDescontoSimples.XDNIT),
	new ProblemaDescontoSimples(
	"Um título de R\\(\\$\\) $n,00 foi resgatado antes do vencimento, sofrendo um desconto simples com taxa de $i\\(\\%\\) ao mês por $t meses. Qual foi o valor atual?",
	TipoDescontoSimples.XDNIT),
	new ProblemaDescontoSimples(
	"Um título sofreu um desconto de R\\(\\$\\) $d,00 com taxa de $i\\(\\%\\) ao mês por $t meses. Qual era o valor nominal do título?",
	TipoDescontoSimples.DXIT),
	new ProblemaDescontoSimples(
	"Um comerciante antecipou um título e teve um desconto de R\\(\\$\\) $d,00 a uma taxa de $i\\(\\%\\) ao mês por $t meses. Qual era o valor inicial do título?",
	TipoDescontoSimples.DXIT),
	new ProblemaDescontoSimples(
	"Uma empresa financeira aplicou um desconto de R\\(\\$\\) $d,00 sobre um título, utilizando uma taxa de $i\\(\\%\\) ao mês e prazo de $t meses. Qual era o valor nominal?",
	TipoDescontoSimples.DXIT),
	new ProblemaDescontoSimples(
	"Um banco aplicou desconto simples e reduziu um título em R\\(\\$\\) $d,00. Sabendo que a taxa foi de $i\\(\\%\\) ao mês por $t meses, qual era o valor nominal?",
	TipoDescontoSimples.DXIT),
	new ProblemaDescontoSimples(
	"Um investidor recebeu um desconto de R\\(\\$\\) $d,00 ao antecipar um título com taxa de $i\\(\\%\\) ao mês durante $t meses. Qual era o valor nominal?",
	TipoDescontoSimples.DXIT),
	new ProblemaDescontoSimples(
	"Um título foi descontado com um abatimento de R\\(\\$\\) $d,00, considerando taxa de $i\\(\\%\\) ao mês por $t meses. Qual era o valor total do título?",
	TipoDescontoSimples.DXIT),
	new ProblemaDescontoSimples(
	"Um banco concedeu um desconto de R\\(\\$\\) $d,00 sobre um título, cuja taxa foi de $i\\(\\%\\) ao mês e prazo de $t meses. Qual era o valor nominal?",
	TipoDescontoSimples.DXIT),
	new ProblemaDescontoSimples(
	"Uma empresa teve um desconto de R\\(\\$\\) $d,00 sobre um título aplicado a $i\\(\\%\\) ao mês por $t meses. Qual era o valor do título antes do desconto?",
	TipoDescontoSimples.DXIT),
	new ProblemaDescontoSimples(
	"Um título sofreu um desconto de R\\(\\$\\) $d,00 devido a uma taxa de $i\\(\\%\\) ao mês por $t meses. Qual era seu valor nominal?",
	TipoDescontoSimples.DXIT),
	new ProblemaDescontoSimples(
	"Um banco aplicou um desconto de R\\(\\$\\) $d,00 sobre um título, com taxa de $i\\(\\%\\) ao mês e prazo de $t meses. Qual era o valor nominal?",
	TipoDescontoSimples.DXIT),
	new ProblemaDescontoSimples(
	"Um comerciante antecipou um título, sofrendo um desconto de R\\(\\$\\) $d,00 com taxa de $i\\(\\%\\) ao mês por $t meses. Qual era o valor nominal?",
	TipoDescontoSimples.DXIT),
	new ProblemaDescontoSimples(
	"O desconto aplicado sobre um título foi de R\\(\\$\\) $d,00. Sabendo que a taxa de desconto foi de $i\\(\\%\\) ao mês e o tempo de desconto foi de $t meses, qual era o valor nominal?",
	TipoDescontoSimples.DXIT),
	new ProblemaDescontoSimples(
	"Um banco concedeu um desconto simples de R\\(\\$\\) $d,00 sobre um título, com taxa de $i\\(\\%\\) ao mês e prazo de $t meses. Qual era o valor nominal?",
	TipoDescontoSimples.DXIT),
	new ProblemaDescontoSimples(
	"Um título foi antecipado sofrendo um desconto de R\\(\\$\\) $d,00, com taxa de $i\\(\\%\\) ao mês e prazo de $t meses. Qual era o valor nominal?",
	TipoDescontoSimples.DXIT),
	new ProblemaDescontoSimples(
	"Um investidor teve um desconto de R\\(\\$\\) $d,00 sobre um título. Sabendo que a taxa de desconto era de $i\\(\\%\\) ao mês e o tempo foi de $t meses, qual era o valor nominal?",
	TipoDescontoSimples.DXIT),
	new ProblemaDescontoSimples(
	"Um título sofreu um desconto de R\\(\\$\\) $d,00 com uma taxa de $i\\(\\%\\) ao mês por $t meses. Qual era seu valor nominal?",
	TipoDescontoSimples.DXIT),
	new ProblemaDescontoSimples(
	"Um título de R\\(\\$\\) $n,00 teve um desconto de R\\(\\$\\) $d,00 após $t meses. Qual foi a taxa de desconto ao mês aplicada?",
	TipoDescontoSimples.DNXT),
	new ProblemaDescontoSimples(
	"Um comerciante antecipou um título de R\\(\\$\\) $n,00 e sofreu um desconto de R\\(\\$\\) $d,00 ao longo de $t meses. Qual foi a taxa de desconto ao mês?",
	TipoDescontoSimples.DNXT),
	new ProblemaDescontoSimples(
	"Um banco aplicou um desconto de R\\(\\$\\) $d,00 sobre um título de R\\(\\$\\) $n,00 por $t meses. Qual foi a taxa de desconto ao mês?",
	TipoDescontoSimples.DNXT),
	new ProblemaDescontoSimples(
	"Um empresário recebeu um desconto de R\\(\\$\\) $d,00 ao antecipar um título de R\\(\\$\\) $n,00 por $t meses. Qual foi a taxa de desconto ao mês utilizada?",
	TipoDescontoSimples.DNXT),
	new ProblemaDescontoSimples(
	"O desconto simples aplicado sobre um título de R\\(\\$\\) $n,00 foi de R\\(\\$\\) $d,00 ao longo de $t meses. Qual foi a taxa de desconto ao mês?",
	TipoDescontoSimples.DNXT),
	new ProblemaDescontoSimples(
	"Um investidor obteve um desconto de R\\(\\$\\) $d,00 em um título de R\\(\\$\\) $n,00 descontado por $t meses. Qual foi a taxa ao mês?",
	TipoDescontoSimples.DNXT),
	new ProblemaDescontoSimples(
	"Uma financeira aplicou um desconto de R\\(\\$\\) $d,00 sobre um título de R\\(\\$\\) $n,00 por $t meses. Qual foi a taxa de desconto ao mês utilizada?",
	TipoDescontoSimples.DNXT),
	new ProblemaDescontoSimples(
	"Um título de R\\(\\$\\) $n,00 teve um abatimento de R\\(\\$\\) $d,00 ao longo de $t meses. Qual foi a taxa de desconto ao mês?",
	TipoDescontoSimples.DNXT),
	new ProblemaDescontoSimples(
	"Um título de R\\(\\$\\) $n,00 sofreu um desconto simples de R\\(\\$\\) $d,00 em $t meses. Qual foi a taxa de desconto ao mês?",
	TipoDescontoSimples.DNXT),
	new ProblemaDescontoSimples(
	"Um banco aplicou um desconto de R\\(\\$\\) $d,00 sobre um título de R\\(\\$\\) $n,00 durante $t meses. Qual foi a taxa de desconto ao mês utilizada?",
	TipoDescontoSimples.DNXT),
	new ProblemaDescontoSimples(
	"Um comerciante antecipou um título de R\\(\\$\\) $n,00 e sofreu um desconto de R\\(\\$\\) $d,00 em $t meses. Qual foi a taxa de desconto ao mês?",
	TipoDescontoSimples.DNXT),
	new ProblemaDescontoSimples(
	"Um investidor teve um título de R\\(\\$\\) $n,00 descontado em $t meses e sofreu um desconto de R\\(\\$\\) $d,00. Qual foi a taxa de desconto ao mês?",
	TipoDescontoSimples.DNXT),
	new ProblemaDescontoSimples(
	"Um banco aplicou um desconto simples sobre um título de R\\(\\$\\) $n,00, reduzindo R\\(\\$\\) $d,00 após $t meses. Qual foi a taxa de desconto ao mês?",
	TipoDescontoSimples.DNXT),
	new ProblemaDescontoSimples(
	"Um título foi descontado e teve um desconto de R\\(\\$\\) $d,00 em $t meses sobre um valor nominal de R\\(\\$\\) $n,00. Qual foi a taxa de desconto ao mês aplicada?",
	TipoDescontoSimples.DNXT),
	new ProblemaDescontoSimples(
	"Um título de R\\(\\$\\) $n,00 sofreu um desconto simples de R\\(\\$\\) $d,00 após $t meses. Qual foi a taxa de desconto ao mês?",
	TipoDescontoSimples.DNXT),
	new ProblemaDescontoSimples(
	"Um comerciante antecipou um título de R\\(\\$\\) $n,00, sofrendo um desconto de R\\(\\$\\) $d,00 em $t meses. Qual foi a taxa de desconto ao mês?",
	TipoDescontoSimples.DNXT),
	new ProblemaDescontoSimples(
	"Um título de R\\(\\$\\) $n,00 sofreu um desconto de R\\(\\$\\) $d,00 com taxa de $i\\(\\%\\) ao mês. Por quantos meses ele ficou sujeito ao desconto?",
	TipoDescontoSimples.DNIX),
	new ProblemaDescontoSimples(
	"Um comerciante antecipou um título de R\\(\\$\\) $n,00 e teve um desconto de R\\(\\$\\) $d,00 com taxa de $i\\(\\%\\) ao mês. Qual foi o tempo do desconto?",
	TipoDescontoSimples.DNIX),
	new ProblemaDescontoSimples(
	"Um banco aplicou um desconto de R\\(\\$\\) $d,00 sobre um título de R\\(\\$\\) $n,00 com taxa de $i\\(\\%\\) ao mês. Qual foi o prazo do desconto?",
	TipoDescontoSimples.DNIX),
	new ProblemaDescontoSimples(
	"Uma empresa financeira aplicou um desconto simples de R\\(\\$\\) $d,00 sobre um título de R\\(\\$\\) $n,00 com taxa de $i\\(\\%\\) ao mês. Qual foi o tempo?",
	TipoDescontoSimples.DNIX),
	new ProblemaDescontoSimples(
	"Um título teve um abatimento de R\\(\\$\\) $d,00 e uma taxa de desconto de $i\\(\\%\\) ao mês. Qual foi o tempo da operação?",
	TipoDescontoSimples.DNIX),
	new ProblemaDescontoSimples(
	"Um banco concedeu um desconto de R\\(\\$\\) $d,00 sobre um título de R\\(\\$\\) $n,00, considerando uma taxa de $i\\(\\%\\) ao mês. Por quanto tempo o desconto foi aplicado?",
	TipoDescontoSimples.DNIX),
	new ProblemaDescontoSimples(
	"Um título de R\\(\\$\\) $n,00 sofreu um abatimento de R\\(\\$\\) $d,00, com uma taxa de desconto de $i\\(\\%\\) ao mês. Qual foi o tempo do desconto?",
	TipoDescontoSimples.DNIX),
	new ProblemaDescontoSimples(
	"Um investidor teve um desconto de R\\(\\$\\) $d,00 sobre um título de R\\(\\$\\) $n,00, considerando uma taxa de $i\\(\\%\\) ao mês. Qual foi o prazo?",
	TipoDescontoSimples.DNIX),
	new ProblemaDescontoSimples(
	"Um título de R\\(\\$\\) $n,00 sofreu um desconto de R\\(\\$\\) $d,00 com uma taxa de $i\\(\\%\\) ao mês. Por quantos meses o título foi descontado?",
	TipoDescontoSimples.DNIX),
	new ProblemaDescontoSimples(
	"Um comerciante antecipou um título de R\\(\\$\\) $n,00 e sofreu um desconto de R\\(\\$\\) $d,00 com uma taxa de $i\\(\\%\\) ao mês. Qual foi o tempo do desconto?",
	TipoDescontoSimples.DNIX),
	new ProblemaDescontoSimples(
	"Um banco aplicou um desconto de R\\(\\$\\) $d,00 sobre um título de R\\(\\$\\) $n,00 com taxa de $i\\(\\%\\) ao mês. Qual foi o prazo do desconto?",
	TipoDescontoSimples.DNIX),
	new ProblemaDescontoSimples(
	"Um título de R\\(\\$\\) $n,00 sofreu um desconto de R\\(\\$\\) $d,00 a uma taxa de $i\\(\\%\\) ao mês. Qual foi o tempo da operação?",
	TipoDescontoSimples.DNIX),
	new ProblemaDescontoSimples(
	"Um título foi antecipado com um desconto de R\\(\\$\\) $d,00 sobre um valor nominal de R\\(\\$\\) $n,00, com taxa de $i\\(\\%\\) ao mês. Qual foi o tempo do desconto?",
	TipoDescontoSimples.DNIX),
	new ProblemaDescontoSimples(
	"Um investidor sofreu um desconto de R\\(\\$\\) $d,00 sobre um título de R\\(\\$\\) $n,00 a uma taxa de $i\\(\\%\\) ao mês. Por quantos meses o título foi descontado?",
	TipoDescontoSimples.DNIX),
	new ProblemaDescontoSimples(
	"Um título sofreu um desconto de R\\(\\$\\) $d,00 com taxa de $i\\(\\%\\) ao mês. Qual foi o tempo do desconto?",
	TipoDescontoSimples.DNIX),
	new ProblemaDescontoSimples(
	"Um banco aplicou um desconto de R\\(\\$\\) $d,00 sobre um título de R\\(\\$\\) $n,00, utilizando uma taxa de $i\\(\\%\\) ao mês. Qual foi o tempo da operação?",
	TipoDescontoSimples.DNIX),
	};
	
	public static ProblemaDescontoSimples getProblemaDesconto()
	{
		Random rand=new Random();
		int index=rand.nextInt(problemas.length);
		return problemas[index].clone();
	}
	
}
