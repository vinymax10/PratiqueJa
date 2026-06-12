package matematica.intermediario.jurosdesconto.nivel3package;

import java.util.Random;

public class TextoDescontoComposto
{
	static ProblemaDescontoComposto  problemas[]= {
	new ProblemaDescontoComposto(
	"Um título de valor nominal R$ $n foi descontado comercialmente a uma taxa de $i% ao mês durante $t meses. Qual o valor do desconto composto?",
	TipoDescontoComposto.XXNIT),
	new ProblemaDescontoComposto(
	"Um título com valor de face R$ $n foi antecipado com desconto comercial composto a uma taxa de $i% ao ano por $t anos. Determine o desconto.",
	TipoDescontoComposto.XXNIT),
	new ProblemaDescontoComposto(
	"Um credor aplicou um desconto composto sobre um título de valor nominal R$ $n, considerando uma taxa de $i% ao trimestre durante $t trimestres. Qual foi o desconto?",
	TipoDescontoComposto.XXNIT),
	new ProblemaDescontoComposto(
	"Um cheque pré-datado de R$ $n foi descontado com taxa de $i% ao bimestre durante $t bimestres. Qual o desconto composto aplicado?",
	TipoDescontoComposto.XXNIT),
	new ProblemaDescontoComposto(
	"Uma empresa descontou um título de R$ $n a uma taxa de $i% ao semestre por $t semestres. Qual o valor do desconto composto?",
	TipoDescontoComposto.XXNIT),
	new ProblemaDescontoComposto(
	"Um pagamento antecipado de um título de R$ $n foi feito com desconto comercial composto, aplicando uma taxa de $i% ao mês durante $t meses. Calcule o valor do desconto.",
	TipoDescontoComposto.XXNIT),
	new ProblemaDescontoComposto(
	"Um banco aplicou desconto composto sobre um título de R$ $n por um período de $t meses a uma taxa de $i% ao mês. Qual o desconto gerado?",
	TipoDescontoComposto.XXNIT),
	new ProblemaDescontoComposto(
	"Um título de R$ $n sofreu um desconto composto com taxa de $i% ao ano por um período de $t anos. Qual foi o valor do desconto?",
	TipoDescontoComposto.XXNIT),
	new ProblemaDescontoComposto(
	"Um título de R$ $n foi descontado comercialmente com taxa de $i% ao mês por $t meses. Qual o valor atual desse título?",
	TipoDescontoComposto.XNIT),
	new ProblemaDescontoComposto(
	"Um cheque no valor de R$ $n foi descontado comercialmente a uma taxa de $i% ao bimestre por $t bimestres. Determine o valor atual.",
	TipoDescontoComposto.XNIT),
	new ProblemaDescontoComposto(
	"Uma empresa descontou um título de R$ $n a uma taxa de $i% ao semestre por $t semestres. Quanto foi recebido no ato do desconto?",
	TipoDescontoComposto.XNIT),
	new ProblemaDescontoComposto(
	"Um título de R$ $n sofreu um desconto composto de taxa $i% ao trimestre durante $t trimestres. Calcule seu valor atual.",
	TipoDescontoComposto.XNIT),
	new ProblemaDescontoComposto(
	"Um banco aplicou um desconto composto sobre um título de R$ $n a uma taxa de $i% ao mês durante $t meses. Qual o valor atual do título?",
	TipoDescontoComposto.XNIT),
	new ProblemaDescontoComposto(
	"Um pagamento antecipado foi feito com desconto comercial composto sobre um título de R$ $n, com taxa de $i% ao ano e prazo de $t anos. Qual o valor atual?",
	TipoDescontoComposto.XNIT),
	new ProblemaDescontoComposto(
	"Uma nota promissória de R$ $n foi antecipada com desconto comercial composto a uma taxa de $i% ao mês por $t meses. Determine o valor atual.",
	TipoDescontoComposto.XNIT),
	new ProblemaDescontoComposto(
	"Um título de R$ $n foi descontado com taxa de $i% ao trimestre por $t trimestres utilizando o desconto comercial composto. Quanto foi recebido pelo beneficiário?",
	TipoDescontoComposto.XNIT),
	new ProblemaDescontoComposto(
	"Um título foi descontado e seu valor atual ficou em R$ $a. Sabendo que a taxa de desconto composto foi de $i% ao mês por $t meses, qual era o valor nominal do título?",
	TipoDescontoComposto.AXIT),
	new ProblemaDescontoComposto(
	"Um banco pagou R$ $a a um investidor após descontar um título com taxa de $i% ao mês por $t meses utilizando o desconto comercial composto. Qual era o valor nominal do título?",
	TipoDescontoComposto.AXIT),
	new ProblemaDescontoComposto(
	"Após um desconto composto de taxa $i% ao mês durante $t meses, um título ficou com o valor atual de R$ $a. Qual era o seu valor nominal?",
	TipoDescontoComposto.AXIT),
	new ProblemaDescontoComposto(
	"Um título foi descontado durante $t meses a uma taxa de $i% ao mês, resultando em um valor atual de R$ $a utilizando o desconto comercial composto. Qual era seu valor nominal?",
	TipoDescontoComposto.AXIT),
	new ProblemaDescontoComposto(
	"Um comerciante recebeu R$ $a antecipando um título. Se a taxa de desconto composto foi de $i% ao mês durante $t meses, qual era o valor nominal do título?",
	TipoDescontoComposto.AXIT),
	new ProblemaDescontoComposto(
	"Um investidor antecipou um título e recebeu R$ $a após um desconto composto de $i% ao mês por $t meses. Qual era o valor nominal?",
	TipoDescontoComposto.AXIT),
	new ProblemaDescontoComposto(
	"Um título teve um valor atual de R$ $a após um desconto composto de $i% ao mês por $t meses. Qual era seu valor nominal?  ",
	TipoDescontoComposto.AXIT),
	new ProblemaDescontoComposto(
	"Após um desconto composto de $i% ao mês por $t meses, um título ficou com o valor atual de R$ $a. Qual era seu valor nominal?",
	TipoDescontoComposto.AXIT),
	new ProblemaDescontoComposto(
	"Um título com valor nominal de R$ $n foi descontado pelo método de desconto composto a uma taxa de $i% ao período. Sabendo que o valor atual do título é R$ $a, por quanto tempo ele ficou sujeito ao desconto?",
	TipoDescontoComposto.ANIX),
	new ProblemaDescontoComposto(
	"Um título que originalmente valia R$ $n foi antecipado utilizando desconto composto com taxa de $i% ao período. Se o valor recebido no ato da antecipação foi R$ $a, por quantos períodos o título foi descontado?",
	TipoDescontoComposto.ANIX),
	new ProblemaDescontoComposto(
	"Um banco aplica desconto composto sobre um título de R$ $n, com taxa de $i% ao período. O valor atual do título após o desconto foi de R$ $a. Qual foi o tempo do desconto?  ",
	TipoDescontoComposto.ANIX),
	new ProblemaDescontoComposto(
	"Um investidor antecipou um título de R$ $n, sofrendo desconto composto com taxa de $i% ao período. Se o valor atual desse título foi de R$ $a, determine o tempo de desconto. ",
	TipoDescontoComposto.ANIX),
	new ProblemaDescontoComposto(
	"Uma empresa negociou um título de R$ $n com desconto composto, recebendo um valor atual de R$ $a. Se a taxa aplicada foi de $i% ao período, por quantos períodos o título foi descontado?",
	TipoDescontoComposto.ANIX),
	new ProblemaDescontoComposto(
	"Um título de R$ $n sofreu um desconto composto a uma taxa de $i% ao período, resultando em um valor atual de R$ $a. Por quanto tempo o título foi descontado antes do vencimento?",
	TipoDescontoComposto.ANIX),
	new ProblemaDescontoComposto(
	"Um título de R$ $n foi resgatado antes do vencimento com desconto composto. Se a taxa aplicada foi de $i% ao período e o valor atual foi R$ $a, determine o tempo de antecipação.",
	TipoDescontoComposto.ANIX),
	new ProblemaDescontoComposto(
	"Um banco oferece antecipação de recebíveis utilizando desconto composto. Se um título de R$ $n teve um valor atual de R$ $a, com taxa de $i% ao período, por quantos períodos ele foi descontado?",
	TipoDescontoComposto.ANIX),
	new ProblemaDescontoComposto(
	"Um título foi emitido com um valor atual de R$ $a e está sujeito a um desconto composto a uma taxa de $i% ao mês durante $t meses. Qual será o desconto total aplicado?",
	TipoDescontoComposto.XAXIT),
	new ProblemaDescontoComposto(
	"Um investidor antecipou o pagamento de um título e recebeu um valor atual de R$ $a. O banco aplicou um desconto composto com taxa de $i% ao mês durante $t meses. Qual foi o desconto aplicado?",
	TipoDescontoComposto.XAXIT),
	new ProblemaDescontoComposto(
	"Um banco oferece antecipação de um título pelo método de desconto composto. O cliente recebeu R$ $a após um desconto a uma taxa de $i% ao mês durante $t meses. Qual foi o valor do desconto?",
	TipoDescontoComposto.XAXIT),
	new ProblemaDescontoComposto(
	"Um título sofreu um desconto composto por $t meses com uma taxa de $i% ao mês. O valor atual recebido foi R$ $a. Qual foi o desconto aplicado? ",
	TipoDescontoComposto.XAXIT),
	new ProblemaDescontoComposto(
	"Uma empresa antecipou um título que, devido a um desconto composto de taxa $i% ao mês por $t meses, teve um valor atual de R$ $a. Qual foi o desconto total aplicado?",
	TipoDescontoComposto.XAXIT),
	new ProblemaDescontoComposto(
	"Um título foi descontado utilizando a regra do desconto composto. O valor atual recebido foi R$ $a, após um período de $t meses com uma taxa de $i% ao mês. Qual foi o desconto aplicado?",
	TipoDescontoComposto.XAXIT),
	new ProblemaDescontoComposto(
	"Um comerciante resgatou antecipadamente um título e recebeu um valor atual de R$ $a após um desconto composto aplicado a uma taxa de $i% ao mês durante $t meses. Qual foi o valor do desconto sobre o título?",
	TipoDescontoComposto.XAXIT),
	new ProblemaDescontoComposto(
	"Um título foi descontado por um banco seguindo a regra do desconto composto, com uma taxa de $i% ao mês e um prazo de $t meses. O valor atual recebido foi R$ $a. Qual foi o desconto aplicado?",
	TipoDescontoComposto.XAXIT),
	};

	public static ProblemaDescontoComposto getProblemaDesconto()
	{
		Random rand=new Random();
		int index=rand.nextInt(problemas.length);
		return problemas[index].clone();
	}
	
}
