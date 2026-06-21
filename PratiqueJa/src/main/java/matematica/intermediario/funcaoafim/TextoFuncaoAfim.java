package matematica.intermediario.funcaoafim;

import java.util.Random;

import static matematica.intermediario.funcaoafim.TipoFuncaoAfim.CRESCENTE;
import static matematica.intermediario.funcaoafim.TipoFuncaoAfim.DECRESCENTE;

// Biblioteca de enunciados de modelagem por função do 1º grau f(x) = ±a·x + b.
// Cada entrada traz o template (com $a taxa, $b inicial, $k alvo, $nomeM/$nomeF),
// a variante estrutural (crescente/decrescente) e a unidade da resposta.
// getProblema() sorteia e clona um template; os valores são gerados depois pelo Problema.
public class TextoFuncaoAfim
{
	static ProblemaFuncaoAfim problemas[] = {

	// ===================== CRESCENTE  f(x) = a·x + b =====================

	// --- taxa fixa + valor por unidade (dinheiro) ---
	new ProblemaFuncaoAfim("Um táxi cobra \\(R\\$\\ $b,00\\) de bandeirada e \\(R\\$\\ $a,00\\) por quilômetro rodado. Quantos quilômetros foram percorridos em uma corrida de \\(R\\$\\ $k,00\\)?", CRESCENTE, "km"),
	new ProblemaFuncaoAfim("Uma empresa cobra \\(R\\$\\ $b,00\\) de taxa fixa e \\(R\\$\\ $a,00\\) por hora de serviço. A quantas horas corresponde um total de \\(R\\$\\ $k,00\\)?", CRESCENTE, "horas"),
	new ProblemaFuncaoAfim("Um encanador cobra \\(R\\$\\ $b,00\\) pela visita e \\(R\\$\\ $a,00\\) por hora de trabalho. Quantas horas ele trabalhou se a conta foi \\(R\\$\\ $k,00\\)?", CRESCENTE, "horas"),
	new ProblemaFuncaoAfim("Um eletricista cobra \\(R\\$\\ $b,00\\) de deslocamento e \\(R\\$\\ $a,00\\) por hora. Quantas horas durou um serviço de \\(R\\$\\ $k,00\\)?", CRESCENTE, "horas"),
	new ProblemaFuncaoAfim("Um plano de internet custa \\(R\\$\\ $b,00\\) de assinatura mais \\(R\\$\\ $a,00\\) por GB excedente. Quantos GB excedentes geraram uma fatura de \\(R\\$\\ $k,00\\)?", CRESCENTE, "GB"),
	new ProblemaFuncaoAfim("Uma transportadora cobra \\(R\\$\\ $b,00\\) de taxa base mais \\(R\\$\\ $a,00\\) por quilograma. Quantos quilogramas tinha uma encomenda que custou \\(R\\$\\ $k,00\\)?", CRESCENTE, "kg"),
	new ProblemaFuncaoAfim("O aluguel de uma bicicleta custa \\(R\\$\\ $b,00\\) fixos mais \\(R\\$\\ $a,00\\) por hora. Por quantas horas a bicicleta foi alugada se o valor pago foi \\(R\\$\\ $k,00\\)?", CRESCENTE, "horas"),
	new ProblemaFuncaoAfim("Uma gráfica cobra \\(R\\$\\ $b,00\\) pela arte mais \\(R\\$\\ $a,00\\) por camiseta estampada. Quantas camisetas foram feitas em um pedido de \\(R\\$\\ $k,00\\)?", CRESCENTE, "camisetas"),
	new ProblemaFuncaoAfim("Uma copiadora cobra \\(R\\$\\ $b,00\\) de preparação mais \\(R\\$\\ $a,00\\) por cópia. Quantas cópias foram feitas em um serviço de \\(R\\$\\ $k,00\\)?", CRESCENTE, "cópias"),
	new ProblemaFuncaoAfim("Um mototáxi cobra \\(R\\$\\ $b,00\\) de saída mais \\(R\\$\\ $a,00\\) por quilômetro. Quantos quilômetros teve uma corrida de \\(R\\$\\ $k,00\\)?", CRESCENTE, "km"),
	new ProblemaFuncaoAfim("Uma academia cobra \\(R\\$\\ $b,00\\) de matrícula e \\(R\\$\\ $a,00\\) por mês. Depois de quantos meses o gasto total chega a \\(R\\$\\ $k,00\\)?", CRESCENTE, "meses"),
	new ProblemaFuncaoAfim("Um curso cobra \\(R\\$\\ $b,00\\) de inscrição mais \\(R\\$\\ $a,00\\) por aula. A quantas aulas corresponde um total de \\(R\\$\\ $k,00\\)?", CRESCENTE, "aulas"),
	new ProblemaFuncaoAfim("Uma doceria cobra \\(R\\$\\ $b,00\\) de embalagem mais \\(R\\$\\ $a,00\\) por doce. Quantos doces tinha um pedido de \\(R\\$\\ $k,00\\)?", CRESCENTE, "doces"),
	new ProblemaFuncaoAfim("Um fotógrafo cobra \\(R\\$\\ $b,00\\) pela sessão mais \\(R\\$\\ $a,00\\) por foto revelada. Quantas fotos foram reveladas em um trabalho de \\(R\\$\\ $k,00\\)?", CRESCENTE, "fotos"),
	new ProblemaFuncaoAfim("Uma costureira cobra \\(R\\$\\ $b,00\\) pelo molde mais \\(R\\$\\ $a,00\\) por peça. Quantas peças tinha uma encomenda de \\(R\\$\\ $k,00\\)?", CRESCENTE, "peças"),
	new ProblemaFuncaoAfim("Um guincho cobra \\(R\\$\\ $b,00\\) de saída mais \\(R\\$\\ $a,00\\) por quilômetro rebocado. Quantos quilômetros teve um serviço de \\(R\\$\\ $k,00\\)?", CRESCENTE, "km"),
	new ProblemaFuncaoAfim("Um transporte escolar cobra \\(R\\$\\ $b,00\\) de matrícula e \\(R\\$\\ $a,00\\) por mês. A quantos meses corresponde um total pago de \\(R\\$\\ $k,00\\)?", CRESCENTE, "meses"),
	new ProblemaFuncaoAfim("Um plano de telefone custa \\(R\\$\\ $b,00\\) de assinatura mais \\(R\\$\\ $a,00\\) por minuto excedente. Quantos minutos excedentes geraram uma conta de \\(R\\$\\ $k,00\\)?", CRESCENTE, "minutos"),
	new ProblemaFuncaoAfim("Um estacionamento cobra \\(R\\$\\ $b,00\\) pela primeira hora e \\(R\\$\\ $a,00\\) por hora adicional. A quantas horas adicionais corresponde um total de \\(R\\$\\ $k,00\\)?", CRESCENTE, "horas"),
	new ProblemaFuncaoAfim("Um sistema cobra \\(R\\$\\ $b,00\\) de instalação mais \\(R\\$\\ $a,00\\) por licença. Quantas licenças tinha um contrato de \\(R\\$\\ $k,00\\)?", CRESCENTE, "licenças"),
	new ProblemaFuncaoAfim("Uma viagem fretada custa \\(R\\$\\ $b,00\\) fixos mais \\(R\\$\\ $a,00\\) por quilômetro. Quantos quilômetros teve uma viagem de \\(R\\$\\ $k,00\\)?", CRESCENTE, "km"),
	new ProblemaFuncaoAfim("Um pintor cobra \\(R\\$\\ $b,00\\) de material mais \\(R\\$\\ $a,00\\) por metro quadrado. Quantos metros quadrados foram pintados em um serviço de \\(R\\$\\ $k,00\\)?", CRESCENTE, "m²"),
	new ProblemaFuncaoAfim("Uma oficina cobra \\(R\\$\\ $b,00\\) de diagnóstico mais \\(R\\$\\ $a,00\\) por hora de mão de obra. Quantas horas teve um conserto de \\(R\\$\\ $k,00\\)?", CRESCENTE, "horas"),
	new ProblemaFuncaoAfim("Um jardineiro cobra \\(R\\$\\ $b,00\\) de visita mais \\(R\\$\\ $a,00\\) por hora. Quantas horas durou um serviço de \\(R\\$\\ $k,00\\)?", CRESCENTE, "horas"),
	new ProblemaFuncaoAfim("Um hotel cobra \\(R\\$\\ $b,00\\) de taxa de limpeza mais \\(R\\$\\ $a,00\\) por diária. A quantas diárias corresponde uma conta de \\(R\\$\\ $k,00\\)?", CRESCENTE, "diárias"),
	new ProblemaFuncaoAfim("Uma loja cobra \\(R\\$\\ $b,00\\) pela arte e \\(R\\$\\ $a,00\\) por adesivo. Quantos adesivos tinha um pedido de \\(R\\$\\ $k,00\\)?", CRESCENTE, "adesivos"),

	// --- poupança / acumulação ---
	new ProblemaFuncaoAfim("$nomeM tem \\(R\\$\\ $b,00\\) guardados e economiza \\(R\\$\\ $a,00\\) por mês. Em quantos meses ele terá \\(R\\$\\ $k,00\\)?", CRESCENTE, "meses"),
	new ProblemaFuncaoAfim("$nomeF tem \\(R\\$\\ $b,00\\) no cofrinho e guarda \\(R\\$\\ $a,00\\) por semana. Em quantas semanas ela terá \\(R\\$\\ $k,00\\)?", CRESCENTE, "semanas"),
	new ProblemaFuncaoAfim("$nomeF começou com \\(R\\$\\ $b,00\\) e poupa \\(R\\$\\ $a,00\\) a cada mês. Em quantos meses seu saldo será \\(R\\$\\ $k,00\\)?", CRESCENTE, "meses"),

	// --- crescimento linear (contagem subindo) ---
	new ProblemaFuncaoAfim("Um reservatório tem \\($b\\,\\text{L}\\) de água e recebe \\($a\\,\\text{L}\\) por minuto. Em quantos minutos terá \\($k\\,\\text{L}\\)?", CRESCENTE, "minutos"),
	new ProblemaFuncaoAfim("Uma muda mede \\($b\\,\\text{cm}\\) e cresce \\($a\\,\\text{cm}\\) por semana. Em quantas semanas atingirá \\($k\\,\\text{cm}\\)?", CRESCENTE, "semanas"),
	new ProblemaFuncaoAfim("Um canal tem \\($b\\) inscritos e ganha \\($a\\) novos inscritos por dia. Em quantos dias terá \\($k\\) inscritos?", CRESCENTE, "dias"),
	new ProblemaFuncaoAfim("$nomeM tem \\($b\\) figurinhas e ganha \\($a\\) por semana. Em quantas semanas terá \\($k\\) figurinhas?", CRESCENTE, "semanas"),
	new ProblemaFuncaoAfim("Uma biblioteca tem \\($b\\) livros e adquire \\($a\\) por mês. Em quantos meses o acervo chegará a \\($k\\) livros?", CRESCENTE, "meses"),
	new ProblemaFuncaoAfim("Um depósito tem \\($b\\) unidades de um produto e recebe \\($a\\) por dia. Em quantos dias o estoque chegará a \\($k\\) unidades?", CRESCENTE, "dias"),
	new ProblemaFuncaoAfim("Um perfil tem \\($b\\) seguidores e ganha \\($a\\) por semana. Em quantas semanas terá \\($k\\) seguidores?", CRESCENTE, "semanas"),
	new ProblemaFuncaoAfim("Uma piscina tem \\($b\\,\\text{L}\\) e é enchida a \\($a\\,\\text{L}\\) por minuto. Em quantos minutos terá \\($k\\,\\text{L}\\)?", CRESCENTE, "minutos"),
	new ProblemaFuncaoAfim("$nomeM já percorreu \\($b\\,\\text{km}\\) e mantém \\($a\\,\\text{km}\\) por hora. Em quantas horas o total percorrido será \\($k\\,\\text{km}\\)?", CRESCENTE, "horas"),
	new ProblemaFuncaoAfim("$nomeF já leu \\($b\\) páginas de um livro e lê \\($a\\) páginas por dia. Em quantos dias terá lido \\($k\\) páginas?", CRESCENTE, "dias"),
	new ProblemaFuncaoAfim("$nomeM tem \\($b\\) selos e ganha \\($a\\) por mês. Em quantos meses sua coleção terá \\($k\\) selos?", CRESCENTE, "meses"),
	new ProblemaFuncaoAfim("Uma fazenda tem \\($b\\) cabeças de gado e o rebanho aumenta \\($a\\) por mês. Em quantos meses haverá \\($k\\) cabeças?", CRESCENTE, "meses"),
	new ProblemaFuncaoAfim("Um galpão tem \\($b\\) caixas e recebe \\($a\\) por dia. Em quantos dias haverá \\($k\\) caixas?", CRESCENTE, "dias"),

	// ===================== DECRESCENTE  f(x) = -a·x + b =====================

	new ProblemaFuncaoAfim("A temperatura de um objeto parte de \\($b\\,\\text{°C}\\) e diminui \\($a\\,\\text{°C}\\) por minuto. Após quantos minutos chegará a \\($k\\,\\text{°C}\\)?", DECRESCENTE, "minutos"),
	new ProblemaFuncaoAfim("Um tanque tem \\($b\\,\\text{L}\\) e esvazia \\($a\\,\\text{L}\\) por minuto. Em quantos minutos restarão \\($k\\,\\text{L}\\)?", DECRESCENTE, "minutos"),
	new ProblemaFuncaoAfim("$nomeM deve \\(R\\$\\ $b,00\\) e paga \\(R\\$\\ $a,00\\) por mês. Em quantos meses a dívida cairá para \\(R\\$\\ $k,00\\)?", DECRESCENTE, "meses"),
	new ProblemaFuncaoAfim("Uma vela tem \\($b\\,\\text{cm}\\) e queima \\($a\\,\\text{cm}\\) por hora. Após quantas horas restarão \\($k\\,\\text{cm}\\)?", DECRESCENTE, "horas"),
	new ProblemaFuncaoAfim("Uma loja tem \\($b\\) unidades em estoque e vende \\($a\\) por dia. Em quantos dias restarão \\($k\\) unidades?", DECRESCENTE, "dias"),
	new ProblemaFuncaoAfim("Uma caixa d'água tem \\($b\\,\\text{L}\\) e a casa consome \\($a\\,\\text{L}\\) por dia. Em quantos dias restarão \\($k\\,\\text{L}\\)?", DECRESCENTE, "dias"),
	new ProblemaFuncaoAfim("$nomeF tem \\(R\\$\\ $b,00\\) e gasta \\(R\\$\\ $a,00\\) por dia. Em quantos dias o saldo será \\(R\\$\\ $k,00\\)?", DECRESCENTE, "dias"),
	new ProblemaFuncaoAfim("Um gerador tem \\($b\\,\\text{L}\\) de combustível e consome \\($a\\,\\text{L}\\) por hora. Em quantas horas restarão \\($k\\,\\text{L}\\)?", DECRESCENTE, "horas"),
	new ProblemaFuncaoAfim("Um celular tem \\($b\\) minutos de crédito e gasta \\($a\\) por dia. Em quantos dias restarão \\($k\\) minutos?", DECRESCENTE, "dias"),
	new ProblemaFuncaoAfim("Um rolo de papel tem \\($b\\,\\text{m}\\) e a impressora usa \\($a\\,\\text{m}\\) por dia. Em quantos dias restarão \\($k\\,\\text{m}\\)?", DECRESCENTE, "dias"),
	new ProblemaFuncaoAfim("Um pacote de ração tem \\($b\\,\\text{kg}\\) e o cão consome \\($a\\,\\text{kg}\\) por dia. Em quantos dias restarão \\($k\\,\\text{kg}\\)?", DECRESCENTE, "dias"),
	new ProblemaFuncaoAfim("Uma obra tem \\($b\\) tijolos e usa \\($a\\) por dia. Em quantos dias restarão \\($k\\) tijolos?", DECRESCENTE, "dias"),
	new ProblemaFuncaoAfim("O corpo tem \\($b\\,\\text{mg}\\) de um medicamento e elimina \\($a\\,\\text{mg}\\) por hora. Em quantas horas restarão \\($k\\,\\text{mg}\\)?", DECRESCENTE, "horas"),
	new ProblemaFuncaoAfim("O nível de um rio está em \\($b\\,\\text{cm}\\) e baixa \\($a\\,\\text{cm}\\) por dia. Em quantos dias chegará a \\($k\\,\\text{cm}\\)?", DECRESCENTE, "dias"),
	new ProblemaFuncaoAfim("$nomeM pesa \\($b\\,\\text{kg}\\) e perde \\($a\\,\\text{kg}\\) por mês com a dieta. Em quantos meses chegará a \\($k\\,\\text{kg}\\)?", DECRESCENTE, "meses"),
	new ProblemaFuncaoAfim("Um teatro tem \\($b\\) ingressos e vende \\($a\\) por hora. Em quantas horas restarão \\($k\\) ingressos?", DECRESCENTE, "horas"),
	new ProblemaFuncaoAfim("Um posto tem \\($b\\) doses de vacina e aplica \\($a\\) por dia. Em quantos dias restarão \\($k\\) doses?", DECRESCENTE, "dias"),
	new ProblemaFuncaoAfim("Uma bobina tem \\($b\\,\\text{m}\\) de fio e a máquina usa \\($a\\,\\text{m}\\) por dia. Em quantos dias restarão \\($k\\,\\text{m}\\)?", DECRESCENTE, "dias"),
	new ProblemaFuncaoAfim("Uma lata tem \\($b\\,\\text{L}\\) de tinta e a obra usa \\($a\\,\\text{L}\\) por dia. Em quantos dias restarão \\($k\\,\\text{L}\\)?", DECRESCENTE, "dias"),
	new ProblemaFuncaoAfim("Um bloco de gelo tem \\($b\\,\\text{cm}\\) de altura e derrete \\($a\\,\\text{cm}\\) por hora. Em quantas horas restarão \\($k\\,\\text{cm}\\)?", DECRESCENTE, "horas"),
	new ProblemaFuncaoAfim("Há \\($b\\) toras de lenha e a lareira queima \\($a\\) por dia. Em quantos dias restarão \\($k\\) toras?", DECRESCENTE, "dias"),
	new ProblemaFuncaoAfim("Uma livraria tem \\($b\\) exemplares de um título e vende \\($a\\) por dia. Em quantos dias restarão \\($k\\) exemplares?", DECRESCENTE, "dias"),
	new ProblemaFuncaoAfim("Uma fila tem \\($b\\) pessoas e o caixa atende \\($a\\) por hora. Em quantas horas restarão \\($k\\) pessoas?", DECRESCENTE, "horas"),
	new ProblemaFuncaoAfim("Um escritório tem \\($b\\) galões de água e consome \\($a\\) por semana. Em quantas semanas restarão \\($k\\) galões?", DECRESCENTE, "semanas"),
	new ProblemaFuncaoAfim("Uma cartela tem \\($b\\) comprimidos e o paciente toma \\($a\\) por dia. Em quantos dias restarão \\($k\\) comprimidos?", DECRESCENTE, "dias"),
	new ProblemaFuncaoAfim("Há \\($b\\,\\text{m}\\) de tecido e a confecção corta \\($a\\,\\text{m}\\) por dia. Em quantos dias restarão \\($k\\,\\text{m}\\)?", DECRESCENTE, "dias"),
	new ProblemaFuncaoAfim("Um saco tem \\($b\\,\\text{kg}\\) de sementes e planta-se \\($a\\,\\text{kg}\\) por dia. Em quantos dias restarão \\($k\\,\\text{kg}\\)?", DECRESCENTE, "dias"),
	new ProblemaFuncaoAfim("Um cliente tem \\($b\\) pontos de fidelidade e gasta \\($a\\) por mês. Em quantos meses restarão \\($k\\) pontos?", DECRESCENTE, "meses"),
	new ProblemaFuncaoAfim("Uma caixa tem \\($b\\) parafusos e a montagem usa \\($a\\) por dia. Em quantos dias restarão \\($k\\) parafusos?", DECRESCENTE, "dias"),
	new ProblemaFuncaoAfim("Faltam \\($b\\,\\text{km}\\) para o destino e o carro percorre \\($a\\,\\text{km}\\) por hora. Em quantas horas faltarão \\($k\\,\\text{km}\\)?", DECRESCENTE, "horas"),
	new ProblemaFuncaoAfim("Um caderno tem \\($b\\) folhas e $nomeF usa \\($a\\) por dia. Em quantos dias restarão \\($k\\) folhas?", DECRESCENTE, "dias"),
	new ProblemaFuncaoAfim("Um cartão tem \\($b\\) passagens e usa-se \\($a\\) por dia. Em quantos dias restarão \\($k\\) passagens?", DECRESCENTE, "dias"),
	new ProblemaFuncaoAfim("Uma farmácia tem \\($b\\) caixas de um remédio e vende \\($a\\) por dia. Em quantos dias restarão \\($k\\) caixas?", DECRESCENTE, "dias"),
	new ProblemaFuncaoAfim("Uma obra tem \\($b\\) sacos de cimento e usa \\($a\\) por dia. Em quantos dias restarão \\($k\\) sacos?", DECRESCENTE, "dias"),
	new ProblemaFuncaoAfim("Um filtro tem \\($b\\,\\text{L}\\) e fornece \\($a\\,\\text{L}\\) por dia. Em quantos dias restarão \\($k\\,\\text{L}\\)?", DECRESCENTE, "dias"),
	new ProblemaFuncaoAfim("Uma fábrica tem \\($b\\) velas e despacha \\($a\\) por dia. Em quantos dias restarão \\($k\\) velas?", DECRESCENTE, "dias"),
	new ProblemaFuncaoAfim("Há \\($b\\) sacos de areia e a obra usa \\($a\\) por dia. Em quantos dias restarão \\($k\\) sacos?", DECRESCENTE, "dias"),
	new ProblemaFuncaoAfim("Um curso tem \\($b\\) vagas e preenche \\($a\\) por dia. Em quantos dias restarão \\($k\\) vagas?", DECRESCENTE, "dias"),

	};

	public static ProblemaFuncaoAfim getProblema()
	{
		Random rand = new Random();
		return problemas[rand.nextInt(problemas.length)].clone();
	}
}
