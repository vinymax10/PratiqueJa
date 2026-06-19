package matematica.basico.subtracaonatural;

import java.util.Random;

// Biblioteca de enunciados de subtração "resta": de um total $a retira-se $b e pergunta-se
// quanto sobrou. $a é o total inicial e $b a quantidade retirada (b < a).
public class TextoResta
{
	static ProblemaResta problemas[] = {

	// --- pessoas e pertences ---
	new ProblemaResta("Numa caixa havia \\($a\\) balas. $nomeF comeu \\($b\\) balas. Quantas balas restaram?"),
	new ProblemaResta("$nomeM tinha \\($a\\) figurinhas e deu \\($b\\) para um amigo. Com quantas figurinhas ele ficou?"),
	new ProblemaResta("$nomeF tinha \\($a\\) reais e gastou \\($b\\). Com quantos reais ela ficou?"),
	new ProblemaResta("$nomeM colecionava \\($a\\) selos e perdeu \\($b\\). Quantos selos restaram?"),
	new ProblemaResta("$nomeF tinha \\($a\\) doces e distribuiu \\($b\\). Quantos doces sobraram?"),
	new ProblemaResta("$nomeM guardava \\($a\\) moedas no cofre e usou \\($b\\). Quantas moedas restaram?"),
	new ProblemaResta("$nomeF comprou \\($a\\) adesivos e colou \\($b\\). Quantos adesivos ainda tem?"),
	new ProblemaResta("$nomeM tinha \\($a\\) bolinhas de gude e perdeu \\($b\\) num jogo. Quantas restaram?"),
	new ProblemaResta("$nomeF tinha \\($a\\) fotos no celular e apagou \\($b\\). Quantas fotos sobraram?"),
	new ProblemaResta("$nomeM tinha \\($a\\) lápis e emprestou \\($b\\). Com quantos lápis ele ficou?"),

	// --- escola ---
	new ProblemaResta("Numa turma de \\($a\\) alunos, \\($b\\) faltaram à aula. Quantos alunos estavam presentes?"),
	new ProblemaResta("Uma escola tinha \\($a\\) alunos matriculados e \\($b\\) se transferiram. Quantos alunos restaram?"),
	new ProblemaResta("Havia \\($a\\) cadeiras no auditório e \\($b\\) foram ocupadas. Quantas cadeiras ficaram livres?"),
	new ProblemaResta("Uma prova tinha \\($a\\) questões e $nomeM já respondeu \\($b\\). Quantas questões faltam?"),
	new ProblemaResta("A professora levou \\($a\\) folhas e usou \\($b\\) na atividade. Quantas folhas sobraram?"),
	new ProblemaResta("Foram impressas \\($a\\) apostilas e distribuídas \\($b\\). Quantas apostilas restaram?"),
	new ProblemaResta("Na biblioteca havia \\($a\\) livros e \\($b\\) foram emprestados. Quantos livros restaram na prateleira?"),
	new ProblemaResta("Um caderno tinha \\($a\\) páginas e $nomeF preencheu \\($b\\). Quantas páginas ainda estão em branco?"),

	// --- comércio e dinheiro ---
	new ProblemaResta("Uma livraria tinha \\($a\\) livros e vendeu \\($b\\) durante o dia. Quantos livros restaram?"),
	new ProblemaResta("Um mercado tinha \\($a\\) caixas de leite e vendeu \\($b\\). Quantas caixas restaram?"),
	new ProblemaResta("Uma loja tinha \\($a\\) camisetas em estoque e vendeu \\($b\\). Quantas camisetas sobraram?"),
	new ProblemaResta("$nomeM saiu de casa com \\($a\\) reais e gastou \\($b\\) no almoço. Com quanto ele voltou?"),
	new ProblemaResta("Uma farmácia tinha \\($a\\) caixas de remédio e vendeu \\($b\\). Quantas caixas restaram?"),
	new ProblemaResta("Um quiosque tinha \\($a\\) sorvetes e vendeu \\($b\\) no calor. Quantos sorvetes sobraram?"),
	new ProblemaResta("Uma banca recebeu \\($a\\) revistas e vendeu \\($b\\). Quantas revistas restaram?"),
	new ProblemaResta("Uma loja de brinquedos tinha \\($a\\) bonecas e vendeu \\($b\\). Quantas bonecas sobraram?"),
	new ProblemaResta("Um caixa tinha \\($a\\) reais e foram retirados \\($b\\) para troco. Quanto restou no caixa?"),

	// --- comida e cozinha ---
	new ProblemaResta("Uma padaria assou \\($a\\) pães e vendeu \\($b\\) pela manhã. Quantos pães restaram?"),
	new ProblemaResta("Numa cozinha havia \\($a\\) ovos e foram usados \\($b\\) numa receita. Quantos ovos sobraram?"),
	new ProblemaResta("Uma confeitaria fez \\($a\\) brigadeiros e serviu \\($b\\) na festa. Quantos brigadeiros restaram?"),
	new ProblemaResta("Havia \\($a\\) fatias de pizza e foram comidas \\($b\\). Quantas fatias sobraram?"),
	new ProblemaResta("Um pomar tinha \\($a\\) maçãs e foram colhidas \\($b\\). Quantas maçãs ficaram nas árvores?"),
	new ProblemaResta("Uma fazenda colheu \\($a\\) kg de laranja e entregou \\($b\\) kg ao mercado. Quantos kg restaram?"),
	new ProblemaResta("Um restaurante tinha \\($a\\) pratos e serviu \\($b\\) no almoço. Quantos pratos sobraram?"),
	new ProblemaResta("$nomeF comprou \\($a\\) balas e comeu \\($b\\). Quantas balas sobraram?"),

	// --- transporte e viagem ---
	new ProblemaResta("O trem partiu com \\($a\\) passageiros e \\($b\\) desceram na estação seguinte. Quantos passageiros restaram?"),
	new ProblemaResta("Um ônibus saiu com \\($a\\) passageiros e \\($b\\) desembarcaram. Quantos continuaram a bordo?"),
	new ProblemaResta("Uma viagem tem \\($a\\) km e $nomeM já percorreu \\($b\\). Quantos km ainda faltam?"),
	new ProblemaResta("Um estacionamento tinha \\($a\\) carros e \\($b\\) saíram. Quantos carros ficaram?"),
	new ProblemaResta("Um avião tinha \\($a\\) assentos e \\($b\\) foram ocupados. Quantos assentos ficaram livres?"),
	new ProblemaResta("Numa corrida de \\($a\\) km, $nomeF já correu \\($b\\) km. Quantos km faltam para o fim?"),

	// --- natureza e animais ---
	new ProblemaResta("Um aquário tinha \\($a\\) peixes e \\($b\\) foram transferidos. Quantos peixes restaram?"),
	new ProblemaResta("Uma fazenda tinha \\($a\\) galinhas e vendeu \\($b\\). Quantas galinhas restaram?"),
	new ProblemaResta("Num parque havia \\($a\\) mudas e \\($b\\) não sobreviveram. Quantas mudas restaram?"),
	new ProblemaResta("Um apiário tinha \\($a\\) potes de mel e foram vendidos \\($b\\). Quantos potes sobraram?"),
	new ProblemaResta("Numa lagoa havia \\($a\\) patos e \\($b\\) voaram para outro lugar. Quantos patos ficaram?"),
	new ProblemaResta("Um viveiro tinha \\($a\\) plantas e \\($b\\) foram doadas. Quantas plantas restaram?"),

	// --- cidade, trabalho, produção ---
	new ProblemaResta("Uma cidade tinha \\($a\\) habitantes e \\($b\\) se mudaram. Quantos habitantes restaram?"),
	new ProblemaResta("Um armazém tinha \\($a\\) kg de arroz e distribuiu \\($b\\) kg em doações. Quantos kg restaram?"),
	new ProblemaResta("Uma empresa produziu \\($a\\) peças e \\($b\\) foram devolvidas por defeito. Quantas ficaram em estoque?"),
	new ProblemaResta("Uma fábrica tinha \\($a\\) caixas e despachou \\($b\\). Quantas caixas restaram?"),
	new ProblemaResta("Um hospital tinha \\($a\\) máscaras e usou \\($b\\) no dia. Quantas máscaras sobraram?"),
	new ProblemaResta("Uma obra recebeu \\($a\\) sacos de cimento e usou \\($b\\). Quantos sacos sobraram?"),
	new ProblemaResta("Um depósito guardava \\($a\\) garrafas e \\($b\\) foram entregues. Quantas garrafas restaram?"),
	new ProblemaResta("Uma gráfica imprimiu \\($a\\) panfletos e distribuiu \\($b\\). Quantos panfletos restaram?"),

	// --- eventos ---
	new ProblemaResta("Um teatro tinha \\($a\\) ingressos e vendeu \\($b\\). Quantos ingressos sobraram?"),
	new ProblemaResta("Uma festa tinha \\($a\\) lembrancinhas e foram entregues \\($b\\). Quantas sobraram?"),
	new ProblemaResta("Um cinema tinha \\($a\\) lugares e \\($b\\) foram ocupados. Quantos lugares ficaram vazios?"),
	new ProblemaResta("Um show vendeu \\($a\\) ingressos e \\($b\\) pessoas já entraram. Quantas ainda não entraram?"),
	new ProblemaResta("Uma exposição esperava \\($a\\) visitantes e \\($b\\) já chegaram. Quantos ainda faltam chegar?"),

	// --- casa e objetos ---
	new ProblemaResta("$nomeF tinha \\($a\\) contas e usou \\($b\\) para fazer um colar. Quantas contas sobraram?"),
	new ProblemaResta("Um rolo tinha \\($a\\) metros de fita e foram cortados \\($b\\). Quantos metros sobraram?"),
	new ProblemaResta("Numa caixa de peças havia \\($a\\) parafusos e foram usados \\($b\\). Quantos parafusos restaram?"),
	new ProblemaResta("$nomeM tinha \\($a\\) blocos de montar e perdeu \\($b\\). Quantos blocos restaram?"),
	new ProblemaResta("Uma costureira tinha \\($a\\) botões e usou \\($b\\). Quantos botões sobraram?"),
	new ProblemaResta("Havia \\($a\\) velas no armário e foram acesas \\($b\\). Quantas velas sobraram?"),

	// --- tempo, medidas e variados ---
	new ProblemaResta("Um reservatório tinha \\($a\\) litros e foram consumidos \\($b\\). Quantos litros restaram?"),
	new ProblemaResta("Um tanque tinha \\($a\\) litros de combustível e gastou \\($b\\). Quantos litros sobraram?"),
	new ProblemaResta("$nomeM tinha \\($a\\) minutos para a prova e já usou \\($b\\). Quantos minutos restam?"),
	new ProblemaResta("Uma campanha tinha a meta de \\($a\\) doações e já recebeu \\($b\\). Quantas faltam para a meta?"),
	new ProblemaResta("Um pacote trazia \\($a\\) folhas e foram usadas \\($b\\). Quantas folhas restaram?"),
	new ProblemaResta("$nomeF leu \\($b\\) páginas de um livro de \\($a\\) páginas. Quantas páginas faltam?"),
	new ProblemaResta("Um jardim tinha \\($a\\) flores e \\($b\\) murcharam. Quantas flores continuam vivas?"),
	new ProblemaResta("Uma turma juntou \\($a\\) tampinhas e entregou \\($b\\) para reciclagem. Quantas sobraram?"),
	new ProblemaResta("$nomeM tinha \\($a\\) cromos e trocou \\($b\\) com os amigos. Quantos cromos restaram?"),
	new ProblemaResta("Um estoque tinha \\($a\\) unidades e \\($b\\) foram vendidas. Quantas unidades restaram?"),
	new ProblemaResta("Uma sala tinha \\($a\\) lugares e \\($b\\) pessoas se sentaram. Quantos lugares estão livres?"),
	new ProblemaResta("Havia \\($a\\) ingressos para a peça e \\($b\\) foram reservados. Quantos ainda estão disponíveis?"),
	new ProblemaResta("Um clube tinha \\($a\\) sócios e \\($b\\) cancelaram a inscrição. Quantos sócios restaram?"),
	new ProblemaResta("$nomeF tinha \\($a\\) reais na poupança e sacou \\($b\\). Quanto sobrou na conta?"),
	new ProblemaResta("Um caminhão levava \\($a\\) caixas e descarregou \\($b\\) na primeira parada. Quantas caixas continuam no caminhão?"),
	new ProblemaResta("Uma horta tinha \\($a\\) pés de alface e foram colhidos \\($b\\). Quantos pés restaram?"),

	};

	public static ProblemaResta getProblema()
	{
		Random rand = new Random();
		return problemas[rand.nextInt(problemas.length)].clone();
	}
}
