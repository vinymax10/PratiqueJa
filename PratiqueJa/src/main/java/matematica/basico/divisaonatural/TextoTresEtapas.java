package matematica.basico.divisaonatural;

import java.util.Random;

// Biblioteca de enunciados de divisão em dois passos: um total $a é repartido igualmente
// em $b grupos e cada grupo em $c subgrupos. $a é o total, $b e $c os dois divisores.
public class TextoTresEtapas
{
	static ProblemaTresEtapas problemas[] = {

	// --- organizações e setores ---
	new ProblemaTresEtapas("Uma empresa tem \\($a\\) funcionários divididos igualmente em \\($b\\) departamentos. Cada departamento é subdividido em \\($c\\) equipes iguais. Quantos funcionários há em cada equipe?"),
	new ProblemaTresEtapas("Um armazém recebeu \\($a\\) caixas distribuídas igualmente em \\($b\\) galpões. Cada galpão divide as caixas em \\($c\\) setores iguais. Quantas caixas há em cada setor?"),
	new ProblemaTresEtapas("Uma escola tem \\($a\\) livros divididos igualmente entre \\($b\\) andares. Cada andar distribui seus livros em \\($c\\) salas iguais. Quantos livros há em cada sala?"),
	new ProblemaTresEtapas("Uma fábrica produziu \\($a\\) peças repartidas igualmente em \\($b\\) linhas. Cada linha separa as peças em \\($c\\) lotes iguais. Quantas peças há em cada lote?"),
	new ProblemaTresEtapas("Uma rede tem \\($a\\) produtos divididos igualmente entre \\($b\\) lojas. Cada loja organiza os produtos em \\($c\\) prateleiras iguais. Quantos produtos há em cada prateleira?"),
	new ProblemaTresEtapas("Um hospital tem \\($a\\) leitos divididos igualmente em \\($b\\) alas. Cada ala separa os leitos em \\($c\\) quartos iguais. Quantos leitos há em cada quarto?"),
	new ProblemaTresEtapas("Uma transportadora tem \\($a\\) encomendas divididas igualmente entre \\($b\\) caminhões. Cada caminhão separa as encomendas em \\($c\\) rotas iguais. Quantas encomendas há em cada rota?"),
	new ProblemaTresEtapas("Uma editora imprimiu \\($a\\) livros divididos igualmente entre \\($b\\) caixas. Cada caixa é separada em \\($c\\) pacotes iguais. Quantos livros há em cada pacote?"),
	new ProblemaTresEtapas("Um almoxarifado tem \\($a\\) itens divididos igualmente em \\($b\\) estantes. Cada estante separa os itens em \\($c\\) gavetas iguais. Quantos itens há em cada gaveta?"),
	new ProblemaTresEtapas("Uma cooperativa tem \\($a\\) sacas divididas igualmente entre \\($b\\) silos. Cada silo reparte as sacas em \\($c\\) compartimentos iguais. Quantas sacas há em cada compartimento?"),

	// --- escola e turmas ---
	new ProblemaTresEtapas("Uma escola tem \\($a\\) alunos divididos igualmente em \\($b\\) turnos. Cada turno é organizado em \\($c\\) turmas iguais. Quantos alunos há em cada turma?"),
	new ProblemaTresEtapas("Uma universidade tem \\($a\\) estudantes divididos igualmente entre \\($b\\) cursos. Cada curso é dividido em \\($c\\) turmas iguais. Quantos estudantes há em cada turma?"),
	new ProblemaTresEtapas("Uma escola comprou \\($a\\) cadernos divididos igualmente entre \\($b\\) séries. Cada série reparte os cadernos entre \\($c\\) turmas iguais. Quantos cadernos vão para cada turma?"),
	new ProblemaTresEtapas("Um colégio tem \\($a\\) carteiras divididas igualmente em \\($b\\) andares. Cada andar distribui as carteiras em \\($c\\) salas iguais. Quantas carteiras há em cada sala?"),
	new ProblemaTresEtapas("Uma biblioteca tem \\($a\\) livros divididos igualmente em \\($b\\) seções. Cada seção organiza os livros em \\($c\\) estantes iguais. Quantos livros há em cada estante?"),
	new ProblemaTresEtapas("Uma escola arrecadou \\($a\\) brinquedos divididos igualmente entre \\($b\\) creches. Cada creche reparte os brinquedos em \\($c\\) salas iguais. Quantos brinquedos há em cada sala?"),

	// --- eventos e pessoas ---
	new ProblemaTresEtapas("Um evento tem \\($a\\) convidados divididos igualmente em \\($b\\) salões. Cada salão organiza os convidados em \\($c\\) mesas iguais. Quantos convidados há em cada mesa?"),
	new ProblemaTresEtapas("Uma excursão tem \\($a\\) participantes divididos igualmente em \\($b\\) ônibus. Cada ônibus separa as pessoas em \\($c\\) grupos iguais. Quantas pessoas há em cada grupo?"),
	new ProblemaTresEtapas("Um congresso tem \\($a\\) inscritos divididos igualmente entre \\($b\\) auditórios. Cada auditório separa os inscritos em \\($c\\) fileiras iguais. Quantos inscritos há em cada fileira?"),
	new ProblemaTresEtapas("Uma colônia de férias tem \\($a\\) crianças divididas igualmente em \\($b\\) chalés. Cada chalé organiza as crianças em \\($c\\) quartos iguais. Quantas crianças há em cada quarto?"),
	new ProblemaTresEtapas("Um torneio tem \\($a\\) jogadores divididos igualmente em \\($b\\) chaves. Cada chave forma \\($c\\) times iguais. Quantos jogadores há em cada time?"),
	new ProblemaTresEtapas("Um coral tem \\($a\\) cantores divididos igualmente em \\($b\\) naipes. Cada naipe é organizado em \\($c\\) fileiras iguais. Quantos cantores há em cada fileira?"),
	new ProblemaTresEtapas("Um desfile tem \\($a\\) integrantes divididos igualmente em \\($b\\) alas. Cada ala forma \\($c\\) blocos iguais. Quantos integrantes há em cada bloco?"),

	// --- comida e produção ---
	new ProblemaTresEtapas("Uma cozinha preparou \\($a\\) salgados divididos igualmente em \\($b\\) travessas. Cada travessa é separada em \\($c\\) porções iguais. Quantos salgados há em cada porção?"),
	new ProblemaTresEtapas("Uma confeitaria fez \\($a\\) docinhos divididos igualmente em \\($b\\) bandejas. Cada bandeja organiza os docinhos em \\($c\\) forminhas iguais por fileira. Quantos docinhos há em cada fileira?"),
	new ProblemaTresEtapas("Uma fazenda colheu \\($a\\) laranjas divididas igualmente em \\($b\\) caminhões. Cada caminhão reparte as laranjas em \\($c\\) caixas iguais. Quantas laranjas há em cada caixa?"),
	new ProblemaTresEtapas("Um restaurante recebeu \\($a\\) ovos divididos igualmente entre \\($b\\) cozinhas. Cada cozinha separa os ovos em \\($c\\) cestos iguais. Quantos ovos há em cada cesto?"),
	new ProblemaTresEtapas("Uma padaria assou \\($a\\) pães divididos igualmente em \\($b\\) carrinhos. Cada carrinho separa os pães em \\($c\\) bandejas iguais. Quantos pães há em cada bandeja?"),
	new ProblemaTresEtapas("Uma fábrica de doces fez \\($a\\) balas divididas igualmente em \\($b\\) lotes. Cada lote é embalado em \\($c\\) pacotes iguais. Quantas balas há em cada pacote?"),
	new ProblemaTresEtapas("Uma cozinha industrial fez \\($a\\) marmitas divididas igualmente entre \\($b\\) bairros. Cada bairro reparte as marmitas em \\($c\\) pontos iguais. Quantas marmitas há em cada ponto?"),

	// --- agricultura e natureza ---
	new ProblemaTresEtapas("Uma plantação tem \\($a\\) mudas divididas igualmente em \\($b\\) talhões. Cada talhão organiza as mudas em \\($c\\) fileiras iguais. Quantas mudas há em cada fileira?"),
	new ProblemaTresEtapas("Um sítio tem \\($a\\) árvores divididas igualmente em \\($b\\) pomares. Cada pomar separa as árvores em \\($c\\) linhas iguais. Quantas árvores há em cada linha?"),
	new ProblemaTresEtapas("Uma granja tem \\($a\\) galinhas divididas igualmente em \\($b\\) galpões. Cada galpão separa as galinhas em \\($c\\) baias iguais. Quantas galinhas há em cada baia?"),
	new ProblemaTresEtapas("Um aquário tem \\($a\\) peixes divididos igualmente em \\($b\\) tanques. Cada tanque separa os peixes em \\($c\\) compartimentos iguais. Quantos peixes há em cada compartimento?"),
	new ProblemaTresEtapas("Um viveiro tem \\($a\\) sementes divididas igualmente em \\($b\\) bandejas. Cada bandeja organiza as sementes em \\($c\\) fileiras iguais. Quantas sementes há em cada fileira?"),
	new ProblemaTresEtapas("Uma reserva tem \\($a\\) mudas divididas igualmente entre \\($b\\) trilhas. Cada trilha reparte as mudas em \\($c\\) pontos iguais. Quantas mudas há em cada ponto?"),

	// --- dinheiro ---
	new ProblemaTresEtapas("$nomeM dividiu \\($a\\) reais igualmente entre \\($b\\) filhos. Cada filho separou seu dinheiro em \\($c\\) cofrinhos iguais. Quanto há em cada cofrinho?"),
	new ProblemaTresEtapas("Um prêmio de \\($a\\) reais foi dividido igualmente entre \\($b\\) equipes. Cada equipe repartiu o valor entre \\($c\\) integrantes iguais. Quanto cada integrante recebeu?"),
	new ProblemaTresEtapas("Uma doação de \\($a\\) reais foi dividida igualmente entre \\($b\\) instituições. Cada instituição repartiu o valor em \\($c\\) projetos iguais. Quanto há em cada projeto?"),
	new ProblemaTresEtapas("$nomeF dividiu \\($a\\) reais igualmente por \\($b\\) meses. Em cada mês ela separou o valor em \\($c\\) semanas iguais. Quanto pode gastar por semana?"),
	new ProblemaTresEtapas("Uma empresa distribuiu \\($a\\) reais de bônus igualmente entre \\($b\\) setores. Cada setor repartiu entre \\($c\\) funcionários iguais. Quanto cada um recebeu?"),

	// --- transporte e logística ---
	new ProblemaTresEtapas("Uma frota transportou \\($a\\) passageiros divididos igualmente em \\($b\\) viagens. Cada viagem usou \\($c\\) vans iguais. Quantos passageiros foram em cada van?"),
	new ProblemaTresEtapas("Um porto recebeu \\($a\\) contêineres divididos igualmente em \\($b\\) navios. Cada navio organiza os contêineres em \\($c\\) pilhas iguais. Quantos contêineres há em cada pilha?"),
	new ProblemaTresEtapas("Um aeroporto despachou \\($a\\) malas divididas igualmente em \\($b\\) voos. Cada voo separa as malas em \\($c\\) carrinhos iguais. Quantas malas há em cada carrinho?"),
	new ProblemaTresEtapas("Uma distribuidora tem \\($a\\) garrafas divididas igualmente entre \\($b\\) depósitos. Cada depósito organiza as garrafas em \\($c\\) engradados iguais. Quantas garrafas há em cada engradado?"),
	new ProblemaTresEtapas("Uma gráfica imprimiu \\($a\\) folhetos divididos igualmente entre \\($b\\) caixas. Cada caixa separa os folhetos em \\($c\\) maços iguais. Quantos folhetos há em cada maço?"),

	// --- objetos e materiais ---
	new ProblemaTresEtapas("Uma loja tem \\($a\\) lápis divididos igualmente em \\($b\\) caixas. Cada caixa separa os lápis em \\($c\\) estojos iguais. Quantos lápis há em cada estojo?"),
	new ProblemaTresEtapas("Um depósito tem \\($a\\) parafusos divididos igualmente em \\($b\\) caixotes. Cada caixote separa os parafusos em \\($c\\) saquinhos iguais. Quantos parafusos há em cada saquinho?"),
	new ProblemaTresEtapas("Uma papelaria tem \\($a\\) folhas divididas igualmente em \\($b\\) resmas. Cada resma é separada em \\($c\\) blocos iguais. Quantas folhas há em cada bloco?"),
	new ProblemaTresEtapas("Uma fábrica tem \\($a\\) azulejos divididos igualmente em \\($b\\) paletes. Cada palete separa os azulejos em \\($c\\) caixas iguais. Quantos azulejos há em cada caixa?"),
	new ProblemaTresEtapas("Uma costureira tem \\($a\\) botões divididos igualmente em \\($b\\) potes. Cada pote separa os botões em \\($c\\) cartelas iguais. Quantos botões há em cada cartela?"),
	new ProblemaTresEtapas("Um estúdio tem \\($a\\) fotos divididas igualmente em \\($b\\) álbuns. Cada álbum organiza as fotos em \\($c\\) páginas iguais. Quantas fotos há em cada página?"),

	// --- coleções e variados ---
	new ProblemaTresEtapas("$nomeM tem \\($a\\) figurinhas divididas igualmente em \\($b\\) álbuns. Cada álbum organiza as figurinhas em \\($c\\) páginas iguais. Quantas figurinhas há em cada página?"),
	new ProblemaTresEtapas("$nomeF tem \\($a\\) contas divididas igualmente em \\($b\\) caixas. Cada caixa separa as contas em \\($c\\) saquinhos iguais. Quantas contas há em cada saquinho?"),
	new ProblemaTresEtapas("Um museu tem \\($a\\) peças divididas igualmente em \\($b\\) alas. Cada ala organiza as peças em \\($c\\) vitrines iguais. Quantas peças há em cada vitrine?"),
	new ProblemaTresEtapas("Uma fazenda tem \\($a\\) bois divididos igualmente em \\($b\\) pastos. Cada pasto separa os bois em \\($c\\) currais iguais. Quantos bois há em cada curral?"),
	new ProblemaTresEtapas("Uma campanha arrecadou \\($a\\) alimentos divididos igualmente entre \\($b\\) postos. Cada posto monta \\($c\\) cestas iguais. Quantos alimentos há em cada cesta?"),
	new ProblemaTresEtapas("Um clube tem \\($a\\) cadeiras divididas igualmente em \\($b\\) quadras. Cada quadra organiza as cadeiras em \\($c\\) fileiras iguais. Quantas cadeiras há em cada fileira?"),
	new ProblemaTresEtapas("Uma loja recebeu \\($a\\) camisetas divididas igualmente em \\($b\\) seções. Cada seção organiza as camisetas em \\($c\\) araras iguais. Quantas camisetas há em cada arara?"),
	new ProblemaTresEtapas("Um cinema tem \\($a\\) poltronas divididas igualmente em \\($b\\) salas. Cada sala organiza as poltronas em \\($c\\) fileiras iguais. Quantas poltronas há em cada fileira?"),
	new ProblemaTresEtapas("Uma feira tem \\($a\\) barracas divididas igualmente em \\($b\\) ruas. Cada rua organiza as barracas em \\($c\\) quarteirões iguais. Quantas barracas há em cada quarteirão?"),
	new ProblemaTresEtapas("Uma fábrica tem \\($a\\) caixas divididas igualmente em \\($b\\) andares. Cada andar separa as caixas em \\($c\\) corredores iguais. Quantas caixas há em cada corredor?"),
	new ProblemaTresEtapas("Um centro de distribuição tem \\($a\\) pacotes divididos igualmente em \\($b\\) esteiras. Cada esteira separa os pacotes em \\($c\\) lotes iguais. Quantos pacotes há em cada lote?"),
	new ProblemaTresEtapas("Uma orquestra tem \\($a\\) partituras divididas igualmente em \\($b\\) seções. Cada seção reparte as partituras entre \\($c\\) músicos iguais. Quantas partituras há para cada músico?"),
	new ProblemaTresEtapas("Uma loja tem \\($a\\) sapatos divididos igualmente em \\($b\\) depósitos. Cada depósito organiza os sapatos em \\($c\\) prateleiras iguais. Quantos sapatos há em cada prateleira?"),
	new ProblemaTresEtapas("Um parque tem \\($a\\) flores divididas igualmente em \\($b\\) jardins. Cada jardim organiza as flores em \\($c\\) canteiros iguais. Quantas flores há em cada canteiro?"),
	new ProblemaTresEtapas("Uma escola recebeu \\($a\\) tablets divididos igualmente entre \\($b\\) laboratórios. Cada laboratório distribui os tablets em \\($c\\) bancadas iguais. Quantos tablets há em cada bancada?"),
	new ProblemaTresEtapas("Um zoológico tem \\($a\\) animais divididos igualmente em \\($b\\) setores. Cada setor organiza os animais em \\($c\\) recintos iguais. Quantos animais há em cada recinto?"),
	new ProblemaTresEtapas("Uma fábrica de calçados tem \\($a\\) pares divididos igualmente em \\($b\\) contêineres. Cada contêiner separa os pares em \\($c\\) caixas iguais. Quantos pares há em cada caixa?"),
	new ProblemaTresEtapas("Uma editora tem \\($a\\) revistas divididas igualmente entre \\($b\\) bancas. Cada banca organiza as revistas em \\($c\\) prateleiras iguais. Quantas revistas há em cada prateleira?"),
	new ProblemaTresEtapas("Um supermercado tem \\($a\\) latas divididas igualmente em \\($b\\) corredores. Cada corredor organiza as latas em \\($c\\) prateleiras iguais. Quantas latas há em cada prateleira?"),
	new ProblemaTresEtapas("Uma escola tem \\($a\\) medalhas divididas igualmente entre \\($b\\) modalidades. Cada modalidade reparte as medalhas entre \\($c\\) equipes iguais. Quantas medalhas há para cada equipe?"),
	new ProblemaTresEtapas("Uma fábrica tem \\($a\\) caixotes divididos igualmente em \\($b\\) galpões. Cada galpão organiza os caixotes em \\($c\\) filas iguais. Quantos caixotes há em cada fila?"),
	new ProblemaTresEtapas("Um hotel tem \\($a\\) toalhas divididas igualmente em \\($b\\) andares. Cada andar separa as toalhas em \\($c\\) rouparias iguais. Quantas toalhas há em cada rouparia?"),
	new ProblemaTresEtapas("Uma horta tem \\($a\\) sementes divididas igualmente em \\($b\\) estufas. Cada estufa organiza as sementes em \\($c\\) canteiros iguais. Quantas sementes há em cada canteiro?"),
	new ProblemaTresEtapas("Uma loja tem \\($a\\) bonés divididos igualmente entre \\($b\\) filiais. Cada filial organiza os bonés em \\($c\\) expositores iguais. Quantos bonés há em cada expositor?"),
	new ProblemaTresEtapas("Um laboratório tem \\($a\\) amostras divididas igualmente em \\($b\\) salas. Cada sala separa as amostras em \\($c\\) bancadas iguais. Quantas amostras há em cada bancada?"),
	new ProblemaTresEtapas("Uma fábrica tem \\($a\\) garrafas divididas igualmente em \\($b\\) turnos. Cada turno embala as garrafas em \\($c\\) caixas iguais. Quantas garrafas há em cada caixa?"),
	new ProblemaTresEtapas("Uma escola tem \\($a\\) lápis divididos igualmente entre \\($b\\) turmas. Cada turma reparte os lápis entre \\($c\\) grupos iguais. Quantos lápis há em cada grupo?"),
	new ProblemaTresEtapas("Um depósito tem \\($a\\) caixas divididas igualmente em \\($b\\) seções. Cada seção empilha as caixas em \\($c\\) colunas iguais. Quantas caixas há em cada coluna?"),

	};

	public static ProblemaTresEtapas getProblema()
	{
		Random rand = new Random();
		return problemas[rand.nextInt(problemas.length)].clone();
	}
}
