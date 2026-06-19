package matematica.basico.multiplicacaonatural;

import java.util.Random;

// Biblioteca de enunciados de multiplicação "grupos de": $a grupos (vezes) de $b unidades.
// $a é a quantidade de grupos/repetições e $b o tamanho de cada grupo.
public class TextoProduto
{
	static ProblemaProduto problemas[] = {

	// --- caixas, fileiras e arranjos ---
	new ProblemaProduto("Uma escola comprou \\($a\\) caixas de lápis com \\($b\\) lápis em cada caixa. Quantos lápis foram comprados no total?"),
	new ProblemaProduto("Um jardim tem \\($a\\) fileiras com \\($b\\) flores em cada fileira. Quantas flores há no jardim?"),
	new ProblemaProduto("Um ônibus tem \\($a\\) fileiras de assentos com \\($b\\) assentos em cada uma. Quantos assentos há no ônibus?"),
	new ProblemaProduto("Uma prateleira tem \\($a\\) colunas com \\($b\\) livros em cada coluna. Quantos livros há na prateleira?"),
	new ProblemaProduto("Uma caixa de chocolates tem \\($a\\) fileiras com \\($b\\) chocolates cada. Quantos chocolates há na caixa?"),
	new ProblemaProduto("Um auditório tem \\($a\\) fileiras com \\($b\\) cadeiras em cada uma. Quantas cadeiras há no auditório?"),
	new ProblemaProduto("Um depósito tem \\($a\\) prateleiras, cada uma com \\($b\\) caixas. Quantas caixas há no depósito?"),
	new ProblemaProduto("Um pomar tem \\($a\\) fileiras com \\($b\\) árvores cada. Quantas árvores há no pomar?"),
	new ProblemaProduto("Um estacionamento tem \\($a\\) filas com \\($b\\) vagas em cada fila. Quantas vagas há ao todo?"),
	new ProblemaProduto("Uma horta tem \\($a\\) canteiros com \\($b\\) mudas em cada um. Quantas mudas há na horta?"),
	new ProblemaProduto("Um tabuleiro tem \\($a\\) linhas com \\($b\\) casas em cada linha. Quantas casas há no tabuleiro?"),
	new ProblemaProduto("Uma estante tem \\($a\\) prateleiras com \\($b\\) potes cada. Quantos potes há na estante?"),

	// --- embalagens e pacotes ---
	new ProblemaProduto("Uma loja recebeu \\($a\\) pacotes com \\($b\\) balas em cada pacote. Quantas balas chegaram?"),
	new ProblemaProduto("Foram compradas \\($a\\) cartelas com \\($b\\) ovos cada. Quantos ovos foram comprados?"),
	new ProblemaProduto("Um mercado vende \\($a\\) caixas com \\($b\\) garrafas em cada caixa. Quantas garrafas há ao todo?"),
	new ProblemaProduto("Uma fábrica embalou \\($a\\) sacos com \\($b\\) parafusos em cada saco. Quantos parafusos foram embalados?"),
	new ProblemaProduto("Uma confeitaria fez \\($a\\) bandejas com \\($b\\) docinhos cada. Quantos docinhos foram feitos?"),
	new ProblemaProduto("Foram entregues \\($a\\) engradados com \\($b\\) refrigerantes em cada um. Quantos refrigerantes ao todo?"),
	new ProblemaProduto("Uma papelaria tem \\($a\\) blocos com \\($b\\) folhas em cada bloco. Quantas folhas há ao todo?"),
	new ProblemaProduto("Um atacado recebeu \\($a\\) fardos com \\($b\\) camisetas em cada fardo. Quantas camisetas chegaram?"),
	new ProblemaProduto("Uma cesta tem \\($a\\) dúzias com \\($b\\) frutas em cada porção. Quantas frutas há na cesta?"),
	new ProblemaProduto("Foram montadas \\($a\\) caixas com \\($b\\) brinquedos cada. Quantos brinquedos foram embalados?"),

	// --- tempo e taxa (a vezes de b) ---
	new ProblemaProduto("$nomeM treina \\($b\\) horas por dia. Quantas horas ele treina em \\($a\\) dias?"),
	new ProblemaProduto("Uma fábrica produz \\($b\\) peças por minuto. Quantas peças produz em \\($a\\) minutos?"),
	new ProblemaProduto("Uma gráfica imprime \\($b\\) folhetos por hora. Quantos folhetos imprime em \\($a\\) horas?"),
	new ProblemaProduto("$nomeF lê \\($b\\) páginas por dia. Quantas páginas ela lê em \\($a\\) dias?"),
	new ProblemaProduto("Uma torneira enche \\($b\\) litros por minuto. Quantos litros enche em \\($a\\) minutos?"),
	new ProblemaProduto("Um padeiro assa \\($b\\) pães por fornada. Quantos pães assa em \\($a\\) fornadas?"),
	new ProblemaProduto("Um ciclista percorre \\($b\\) km por hora. Quantos km percorre em \\($a\\) horas?"),
	new ProblemaProduto("Uma costureira faz \\($b\\) peças por dia. Quantas peças faz em \\($a\\) dias?"),
	new ProblemaProduto("$nomeM poupa \\($b\\) reais por semana. Quanto ele poupa em \\($a\\) semanas?"),
	new ProblemaProduto("Uma máquina envasa \\($b\\) potes por minuto. Quantos potes envasa em \\($a\\) minutos?"),
	new ProblemaProduto("Um restaurante serve \\($b\\) refeições por dia. Quantas refeições serve em \\($a\\) dias?"),

	// --- grupos de pessoas/coisas ---
	new ProblemaProduto("Uma excursão tem \\($a\\) ônibus com \\($b\\) passageiros em cada um. Quantas pessoas viajam?"),
	new ProblemaProduto("Uma escola tem \\($a\\) turmas com \\($b\\) alunos em cada turma. Quantos alunos há na escola?"),
	new ProblemaProduto("Um time tem \\($a\\) equipes com \\($b\\) jogadores cada. Quantos jogadores há ao todo?"),
	new ProblemaProduto("Um evento tem \\($a\\) mesas com \\($b\\) convidados em cada mesa. Quantos convidados há?"),
	new ProblemaProduto("Um coral tem \\($a\\) grupos com \\($b\\) cantores cada. Quantos cantores há no coral?"),
	new ProblemaProduto("Uma colônia de férias tem \\($a\\) cabanas com \\($b\\) crianças cada. Quantas crianças há?"),
	new ProblemaProduto("Um congresso tem \\($a\\) salas com \\($b\\) participantes em cada sala. Quantos participantes ao todo?"),
	new ProblemaProduto("Uma escola de dança tem \\($a\\) turmas com \\($b\\) bailarinos cada. Quantos bailarinos há?"),

	// --- dinheiro (preço unitário) ---
	new ProblemaProduto("$nomeF comprou \\($a\\) cadernos a \\($b\\) reais cada. Quanto ela gastou no total?"),
	new ProblemaProduto("Um cliente levou \\($a\\) ingressos a \\($b\\) reais cada. Quanto pagou ao todo?"),
	new ProblemaProduto("Uma loja vendeu \\($a\\) camisetas a \\($b\\) reais cada. Quanto arrecadou?"),
	new ProblemaProduto("$nomeM comprou \\($a\\) livros a \\($b\\) reais cada. Quanto gastou no total?"),
	new ProblemaProduto("Foram vendidos \\($a\\) lanches a \\($b\\) reais cada. Qual foi o total arrecadado?"),
	new ProblemaProduto("Uma papelaria vendeu \\($a\\) canetas a \\($b\\) reais a unidade. Quanto recebeu?"),
	new ProblemaProduto("Uma feira vendeu \\($a\\) cestas a \\($b\\) reais cada. Qual foi o valor total?"),

	// --- comida e produção ---
	new ProblemaProduto("Uma pizzaria corta cada pizza em \\($b\\) fatias. Quantas fatias há em \\($a\\) pizzas?"),
	new ProblemaProduto("Cada caixa de ovos tem \\($b\\) ovos. Quantos ovos há em \\($a\\) caixas?"),
	new ProblemaProduto("Cada pacote tem \\($b\\) biscoitos. Quantos biscoitos há em \\($a\\) pacotes?"),
	new ProblemaProduto("Uma fazenda tem \\($a\\) vacas que dão \\($b\\) litros de leite cada por dia. Quantos litros ao dia?"),
	new ProblemaProduto("Cada bandeja leva \\($b\\) pãezinhos. Quantos pãezinhos há em \\($a\\) bandejas?"),
	new ProblemaProduto("Um pomar dá \\($b\\) frutas por árvore. Quantas frutas dão \\($a\\) árvores?"),
	new ProblemaProduto("Cada saco tem \\($b\\) laranjas. Quantas laranjas há em \\($a\\) sacos?"),

	// --- objetos do dia a dia ---
	new ProblemaProduto("Cada caixa de azulejos cobre \\($b\\) ladrilhos. Quantos ladrilhos há em \\($a\\) caixas?"),
	new ProblemaProduto("Um álbum tem \\($a\\) páginas com \\($b\\) figurinhas em cada página. Quantas figurinhas cabem?"),
	new ProblemaProduto("Cada caderno tem \\($b\\) folhas. Quantas folhas há em \\($a\\) cadernos?"),
	new ProblemaProduto("Uma estante comporta \\($a\\) gavetas com \\($b\\) objetos cada. Quantos objetos cabem?"),
	new ProblemaProduto("Cada cartela tem \\($b\\) adesivos. Quantos adesivos há em \\($a\\) cartelas?"),
	new ProblemaProduto("Um painel tem \\($a\\) linhas de \\($b\\) lâmpadas cada. Quantas lâmpadas há no painel?"),
	new ProblemaProduto("Cada caixa de lápis de cor tem \\($b\\) cores. Quantos lápis há em \\($a\\) caixas?"),

	// --- mais variados ---
	new ProblemaProduto("Uma plantação tem \\($a\\) ruas com \\($b\\) pés de café cada. Quantos pés de café há?"),
	new ProblemaProduto("Um prédio tem \\($a\\) andares com \\($b\\) apartamentos em cada andar. Quantos apartamentos há?"),
	new ProblemaProduto("Uma sala de cinema tem \\($a\\) fileiras com \\($b\\) poltronas cada. Quantas poltronas há?"),
	new ProblemaProduto("Um galpão tem \\($a\\) baias com \\($b\\) animais em cada baia. Quantos animais há?"),
	new ProblemaProduto("Uma quadra tem \\($a\\) arquibancadas com \\($b\\) lugares cada. Quantos lugares há ao todo?"),
	new ProblemaProduto("Um colégio distribuiu \\($a\\) kits com \\($b\\) materiais em cada kit. Quantos materiais foram distribuídos?"),
	new ProblemaProduto("Uma biblioteca tem \\($a\\) estantes com \\($b\\) livros cada. Quantos livros há na biblioteca?"),
	new ProblemaProduto("Um aquário tem \\($a\\) tanques com \\($b\\) peixes em cada um. Quantos peixes há?"),
	new ProblemaProduto("Uma fábrica de calçados produz \\($b\\) pares por hora. Quantos pares produz em \\($a\\) horas?"),
	new ProblemaProduto("Um trem tem \\($a\\) vagões com \\($b\\) lugares cada. Quantos lugares há no trem?"),
	new ProblemaProduto("Uma loja organizou \\($a\\) vitrines com \\($b\\) produtos cada. Quantos produtos foram expostos?"),
	new ProblemaProduto("Cada turma plantou \\($b\\) árvores. Quantas árvores plantaram \\($a\\) turmas?"),
	new ProblemaProduto("Um festival tem \\($a\\) barracas que vendem \\($b\\) lanches cada. Quantos lanches no total?"),
	new ProblemaProduto("Cada caixa-d'água enche \\($b\\) baldes. Quantos baldes enchem \\($a\\) caixas-d'água?"),
	new ProblemaProduto("Um teatro tem \\($a\\) setores com \\($b\\) cadeiras em cada setor. Quantas cadeiras há ao todo?"),
	new ProblemaProduto("Cada caixa de leite tem \\($b\\) unidades. Quantas unidades há em \\($a\\) caixas?"),
	new ProblemaProduto("Uma escola tem \\($a\\) salas com \\($b\\) carteiras em cada sala. Quantas carteiras há na escola?"),
	new ProblemaProduto("$nomeM lê \\($b\\) páginas por hora. Quantas páginas lê em \\($a\\) horas?"),
	new ProblemaProduto("Cada fileira do estádio tem \\($b\\) lugares. Quantos lugares há em \\($a\\) fileiras?"),

	};

	public static ProblemaProduto getProblema()
	{
		Random rand = new Random();
		return problemas[rand.nextInt(problemas.length)].clone();
	}
}
