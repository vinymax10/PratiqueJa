package matematica.basico.divisaonatural;

import java.util.Random;

// Biblioteca de enunciados de divisão com resto (empacotamento): um total $a é agrupado
// em grupos de $b; pergunta-se quantos grupos completos se formam e quantos sobram.
// $a é o total e $b o tamanho de cada grupo.
public class TextoDivisaoResto
{
	static ProblemaDivisaoResto problemas[] = {

	// --- embalar em caixas/pacotes ---
	new ProblemaDivisaoResto("\\($a\\) chocolates serão embalados em caixas com \\($b\\) unidades cada. Quantas caixas completas se formam e quantos chocolates sobram?"),
	new ProblemaDivisaoResto("\\($a\\) ovos serão colocados em cartelas de \\($b\\) ovos. Quantas cartelas completas se formam e quantos ovos sobram?"),
	new ProblemaDivisaoResto("\\($a\\) garrafas serão guardadas em engradados de \\($b\\) garrafas. Quantos engradados completos e quantas garrafas sobram?"),
	new ProblemaDivisaoResto("\\($a\\) parafusos serão divididos em saquinhos de \\($b\\). Quantos saquinhos completos se formam e quantos parafusos sobram?"),
	new ProblemaDivisaoResto("\\($a\\) biscoitos serão empacotados em pacotes de \\($b\\). Quantos pacotes completos e quantos biscoitos sobram?"),
	new ProblemaDivisaoResto("\\($a\\) canetas serão postas em estojos de \\($b\\). Quantos estojos completos se formam e quantas canetas sobram?"),
	new ProblemaDivisaoResto("\\($a\\) maçãs serão arrumadas em caixas de \\($b\\). Quantas caixas completas e quantas maçãs sobram?"),
	new ProblemaDivisaoResto("\\($a\\) velas serão embaladas em pacotes de \\($b\\). Quantos pacotes completos se formam e quantas velas sobram?"),
	new ProblemaDivisaoResto("\\($a\\) sabonetes serão guardados em caixas de \\($b\\). Quantas caixas completas e quantos sabonetes sobram?"),
	new ProblemaDivisaoResto("\\($a\\) comprimidos serão postos em cartelas de \\($b\\). Quantas cartelas completas se formam e quantos comprimidos sobram?"),
	new ProblemaDivisaoResto("\\($a\\) lápis serão organizados em caixas de \\($b\\). Quantas caixas completas e quantos lápis sobram?"),
	new ProblemaDivisaoResto("\\($a\\) bombons serão colocados em potes de \\($b\\). Quantos potes completos se formam e quantos bombons sobram?"),

	// --- organizar em grupos/equipes ---
	new ProblemaDivisaoResto("\\($a\\) alunos serão divididos em equipes de \\($b\\). Quantas equipes completas se formam e quantos alunos sobram?"),
	new ProblemaDivisaoResto("\\($a\\) atletas serão organizados em times de \\($b\\). Quantos times completos e quantos atletas sobram?"),
	new ProblemaDivisaoResto("\\($a\\) pessoas viajarão em vans de \\($b\\) lugares. Quantas vans completas se formam e quantas pessoas sobram para a última van?"),
	new ProblemaDivisaoResto("\\($a\\) crianças serão organizadas em grupos de \\($b\\) para uma gincana. Quantos grupos completos e quantas crianças sobram?"),
	new ProblemaDivisaoResto("\\($a\\) cadeiras serão arrumadas em fileiras de \\($b\\). Quantas fileiras completas se formam e quantas cadeiras sobram?"),
	new ProblemaDivisaoResto("\\($a\\) convidados serão acomodados em mesas de \\($b\\) lugares. Quantas mesas completas e quantos convidados sobram?"),
	new ProblemaDivisaoResto("\\($a\\) soldados serão organizados em pelotões de \\($b\\). Quantos pelotões completos se formam e quantos soldados sobram?"),
	new ProblemaDivisaoResto("\\($a\\) dançarinos serão divididos em grupos de \\($b\\). Quantos grupos completos e quantos dançarinos sobram?"),
	new ProblemaDivisaoResto("\\($a\\) passageiros embarcarão em barcos de \\($b\\) lugares. Quantos barcos completos se formam e quantos passageiros sobram?"),
	new ProblemaDivisaoResto("\\($a\\) escoteiros serão divididos em patrulhas de \\($b\\). Quantas patrulhas completas e quantos escoteiros sobram?"),

	// --- produção e lotes ---
	new ProblemaDivisaoResto("Uma fábrica produziu \\($a\\) peças que serão organizadas em lotes de \\($b\\). Quantos lotes completos há e quantas peças sobram?"),
	new ProblemaDivisaoResto("\\($a\\) tijolos serão empilhados em paletes de \\($b\\). Quantos paletes completos se formam e quantos tijolos sobram?"),
	new ProblemaDivisaoResto("\\($a\\) camisetas serão embaladas em fardos de \\($b\\). Quantos fardos completos e quantas camisetas sobram?"),
	new ProblemaDivisaoResto("\\($a\\) folhetos serão divididos em blocos de \\($b\\). Quantos blocos completos se formam e quantos folhetos sobram?"),
	new ProblemaDivisaoResto("\\($a\\) pães serão colocados em sacolas de \\($b\\). Quantas sacolas completas e quantos pães sobram?"),
	new ProblemaDivisaoResto("\\($a\\) peças de roupa serão penduradas em araras de \\($b\\). Quantas araras completas se formam e quantas peças sobram?"),
	new ProblemaDivisaoResto("\\($a\\) garrafas de suco serão postas em caixas de \\($b\\). Quantas caixas completas e quantas garrafas sobram?"),
	new ProblemaDivisaoResto("\\($a\\) blocos de montar serão guardados em sacos de \\($b\\). Quantos sacos completos se formam e quantos blocos sobram?"),

	// --- distribuir (rotas, dias, semanas) ---
	new ProblemaDivisaoResto("Uma escola recebeu \\($a\\) livros para distribuir em pacotes de \\($b\\). Quantos pacotes completos e quantos livros sobram?"),
	new ProblemaDivisaoResto("$nomeM tem \\($a\\) figurinhas e cola \\($b\\) por página. Quantas páginas completas ele preenche e quantas figurinhas sobram?"),
	new ProblemaDivisaoResto("Uma confeitaria fez \\($a\\) docinhos e coloca \\($b\\) por bandeja. Quantas bandejas completas e quantos docinhos sobram?"),
	new ProblemaDivisaoResto("\\($a\\) cartas serão entregues em maços de \\($b\\). Quantos maços completos se formam e quantas cartas sobram?"),
	new ProblemaDivisaoResto("$nomeF lê \\($a\\) páginas em sessões de \\($b\\) páginas. Quantas sessões completas e quantas páginas sobram?"),
	new ProblemaDivisaoResto("\\($a\\) reais serão guardados em envelopes de \\($b\\) reais. Quantos envelopes completos se formam e quantos reais sobram?"),
	new ProblemaDivisaoResto("\\($a\\) minutos de vídeo serão divididos em capítulos de \\($b\\) minutos. Quantos capítulos completos e quantos minutos sobram?"),
	new ProblemaDivisaoResto("\\($a\\) doces serão divididos em saquinhos de \\($b\\) para festa. Quantos saquinhos completos e quantos doces sobram?"),

	// --- comida ---
	new ProblemaDivisaoResto("\\($a\\) laranjas serão postas em cestas de \\($b\\). Quantas cestas completas se formam e quantas laranjas sobram?"),
	new ProblemaDivisaoResto("\\($a\\) fatias de bolo serão servidas em pratos de \\($b\\). Quantos pratos completos e quantas fatias sobram?"),
	new ProblemaDivisaoResto("\\($a\\) salgados serão arrumados em bandejas de \\($b\\). Quantas bandejas completas se formam e quantos salgados sobram?"),
	new ProblemaDivisaoResto("\\($a\\) ovos de páscoa serão postos em caixas de \\($b\\). Quantas caixas completas e quantos ovos sobram?"),
	new ProblemaDivisaoResto("\\($a\\) pãezinhos serão divididos em cestas de \\($b\\). Quantas cestas completas se formam e quantos pãezinhos sobram?"),
	new ProblemaDivisaoResto("\\($a\\) frutas serão empacotadas em bandejas de \\($b\\). Quantas bandejas completas e quantas frutas sobram?"),

	// --- animais e natureza ---
	new ProblemaDivisaoResto("\\($a\\) peixes serão divididos em aquários de \\($b\\). Quantos aquários completos se formam e quantos peixes sobram?"),
	new ProblemaDivisaoResto("\\($a\\) mudas serão plantadas em canteiros de \\($b\\). Quantos canteiros completos e quantas mudas sobram?"),
	new ProblemaDivisaoResto("\\($a\\) galinhas serão alojadas em galinheiros de \\($b\\). Quantos galinheiros completos se formam e quantas galinhas sobram?"),
	new ProblemaDivisaoResto("\\($a\\) flores serão arranjadas em buquês de \\($b\\). Quantos buquês completos e quantas flores sobram?"),
	new ProblemaDivisaoResto("\\($a\\) sementes serão postas em vasos de \\($b\\). Quantos vasos completos se formam e quantas sementes sobram?"),
	new ProblemaDivisaoResto("\\($a\\) filhotes serão acomodados em ninhos de \\($b\\). Quantos ninhos completos e quantos filhotes sobram?"),

	// --- objetos do dia a dia ---
	new ProblemaDivisaoResto("\\($a\\) adesivos serão colados em cartelas de \\($b\\). Quantas cartelas completas se formam e quantos adesivos sobram?"),
	new ProblemaDivisaoResto("\\($a\\) botões serão postos em cartões de \\($b\\). Quantos cartões completos e quantos botões sobram?"),
	new ProblemaDivisaoResto("\\($a\\) pregos serão divididos em caixinhas de \\($b\\). Quantas caixinhas completas se formam e quantos pregos sobram?"),
	new ProblemaDivisaoResto("\\($a\\) bolinhas de gude serão guardadas em saquinhos de \\($b\\). Quantos saquinhos completos e quantas bolinhas sobram?"),
	new ProblemaDivisaoResto("\\($a\\) moedas serão empilhadas em rolinhos de \\($b\\). Quantos rolinhos completos se formam e quantas moedas sobram?"),
	new ProblemaDivisaoResto("\\($a\\) lâmpadas serão postas em caixas de \\($b\\). Quantas caixas completas e quantas lâmpadas sobram?"),
	new ProblemaDivisaoResto("\\($a\\) cromos serão colados em álbuns de \\($b\\) por folha. Quantas folhas completas se formam e quantos cromos sobram?"),

	// --- esporte e eventos ---
	new ProblemaDivisaoResto("\\($a\\) ingressos serão divididos em lotes de \\($b\\). Quantos lotes completos se formam e quantos ingressos sobram?"),
	new ProblemaDivisaoResto("\\($a\\) bolas serão guardadas em sacos de \\($b\\). Quantos sacos completos e quantas bolas sobram?"),
	new ProblemaDivisaoResto("\\($a\\) medalhas serão postas em estojos de \\($b\\). Quantos estojos completos se formam e quantas medalhas sobram?"),
	new ProblemaDivisaoResto("\\($a\\) troféus serão arrumados em prateleiras de \\($b\\). Quantas prateleiras completas e quantos troféus sobram?"),
	new ProblemaDivisaoResto("\\($a\\) participantes serão divididos em chaves de \\($b\\). Quantas chaves completas se formam e quantos participantes sobram?"),

	// --- variados ---
	new ProblemaDivisaoResto("\\($a\\) cadeiras serão emprestadas em lotes de \\($b\\). Quantos lotes completos se formam e quantas cadeiras sobram?"),
	new ProblemaDivisaoResto("\\($a\\) cestas básicas serão entregues em caminhonetes de \\($b\\). Quantas caminhonetes completas e quantas cestas sobram?"),
	new ProblemaDivisaoResto("\\($a\\) cadernos serão amarrados em maços de \\($b\\). Quantos maços completos se formam e quantos cadernos sobram?"),
	new ProblemaDivisaoResto("\\($a\\) quadros serão pendurados em painéis de \\($b\\). Quantos painéis completos e quantos quadros sobram?"),
	new ProblemaDivisaoResto("\\($a\\) ferramentas serão postas em maletas de \\($b\\). Quantas maletas completas se formam e quantas ferramentas sobram?"),
	new ProblemaDivisaoResto("\\($a\\) garrafas de água serão distribuídas em fardos de \\($b\\). Quantos fardos completos e quantas garrafas sobram?"),
	new ProblemaDivisaoResto("\\($a\\) pincéis serão postos em kits de \\($b\\). Quantos kits completos se formam e quantos pincéis sobram?"),
	new ProblemaDivisaoResto("\\($a\\) tampinhas serão guardadas em potes de \\($b\\). Quantos potes completos e quantas tampinhas sobram?"),
	new ProblemaDivisaoResto("\\($a\\) selos serão organizados em folhas de \\($b\\). Quantas folhas completas se formam e quantos selos sobram?"),
	new ProblemaDivisaoResto("\\($a\\) lanches serão postos em bandejas de \\($b\\). Quantas bandejas completas e quantos lanches sobram?"),
	new ProblemaDivisaoResto("\\($a\\) revistas serão amarradas em pacotes de \\($b\\). Quantos pacotes completos se formam e quantas revistas sobram?"),
	new ProblemaDivisaoResto("\\($a\\) brinquedos serão postos em caixas de \\($b\\). Quantas caixas completas e quantos brinquedos sobram?"),
	new ProblemaDivisaoResto("\\($a\\) mudas de café serão plantadas em filas de \\($b\\). Quantas filas completas se formam e quantas mudas sobram?"),
	new ProblemaDivisaoResto("\\($a\\) latas de tinta serão postas em prateleiras de \\($b\\). Quantas prateleiras completas e quantas latas sobram?"),
	new ProblemaDivisaoResto("\\($a\\) cartões de visita serão divididos em maços de \\($b\\). Quantos maços completos se formam e quantos cartões sobram?"),
	new ProblemaDivisaoResto("\\($a\\) pilhas serão postas em cartelas de \\($b\\). Quantas cartelas completas se formam e quantas pilhas sobram?"),
	new ProblemaDivisaoResto("\\($a\\) cookies serão postos em embalagens de \\($b\\). Quantas embalagens completas e quantos cookies sobram?"),
	new ProblemaDivisaoResto("\\($a\\) parafusos serão postos em kits de montagem de \\($b\\). Quantos kits completos se formam e quantos parafusos sobram?"),
	new ProblemaDivisaoResto("\\($a\\) cadeiras de praia serão guardadas em depósitos de \\($b\\). Quantos depósitos completos e quantas cadeiras sobram?"),
	new ProblemaDivisaoResto("\\($a\\) ovos serão postos em bandejas de \\($b\\). Quantas bandejas completas se formam e quantos ovos sobram?"),

	};

	public static ProblemaDivisaoResto getProblema()
	{
		Random rand = new Random();
		return problemas[rand.nextInt(problemas.length)].clone();
	}
}
