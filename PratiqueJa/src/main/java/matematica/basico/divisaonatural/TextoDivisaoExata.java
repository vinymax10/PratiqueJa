package matematica.basico.divisaonatural;

import java.util.Random;

// Biblioteca de enunciados de divisão exata: um total $a repartido igualmente em $b partes;
// pergunta-se quanto cabe em cada parte. $a é o total (múltiplo de $b) e $b o número de partes.
public class TextoDivisaoExata
{
	static ProblemaDivisaoExata problemas[] = {

	// --- distribuir entre pessoas ---
	new ProblemaDivisaoExata("Uma professora tem \\($a\\) balas para distribuir igualmente entre \\($b\\) alunos. Quantas balas cada aluno recebe?"),
	new ProblemaDivisaoExata("$nomeM tem \\($a\\) figurinhas e quer dividi-las igualmente em \\($b\\) álbuns. Quantas figurinhas vão para cada álbum?"),
	new ProblemaDivisaoExata("\\($a\\) estudantes foram organizados em \\($b\\) filas com o mesmo número de pessoas. Quantos estudantes há em cada fila?"),
	new ProblemaDivisaoExata("$nomeF repartiu \\($a\\) bombons igualmente entre \\($b\\) amigos. Quantos bombons cada um recebeu?"),
	new ProblemaDivisaoExata("\\($a\\) cartas foram distribuídas igualmente entre \\($b\\) jogadores. Quantas cartas cada jogador recebeu?"),
	new ProblemaDivisaoExata("Um prêmio de \\($a\\) reais será dividido igualmente entre \\($b\\) ganhadores. Quanto cada um recebe?"),
	new ProblemaDivisaoExata("$nomeM dividiu \\($a\\) adesivos igualmente entre \\($b\\) primos. Quantos adesivos cada primo recebeu?"),
	new ProblemaDivisaoExata("\\($a\\) convidados foram acomodados igualmente em \\($b\\) mesas. Quantos convidados há em cada mesa?"),
	new ProblemaDivisaoExata("Uma turma de \\($a\\) alunos foi dividida em \\($b\\) grupos iguais. Quantos alunos há em cada grupo?"),
	new ProblemaDivisaoExata("$nomeF separou \\($a\\) lápis igualmente em \\($b\\) estojos. Quantos lápis ficaram em cada estojo?"),
	new ProblemaDivisaoExata("\\($a\\) crianças foram distribuídas igualmente em \\($b\\) vans. Quantas crianças há em cada van?"),
	new ProblemaDivisaoExata("Um treinador separou \\($a\\) atletas em \\($b\\) equipes iguais. Quantos atletas há em cada equipe?"),

	// --- repartir em recipientes ---
	new ProblemaDivisaoExata("\\($a\\) laranjas foram colocadas em \\($b\\) cestas com a mesma quantidade. Quantas laranjas há em cada cesta?"),
	new ProblemaDivisaoExata("Um pacote tem \\($a\\) biscoitos divididos em \\($b\\) grupos iguais. Quantos biscoitos há em cada grupo?"),
	new ProblemaDivisaoExata("\\($a\\) ovos foram guardados igualmente em \\($b\\) caixas. Quantos ovos há em cada caixa?"),
	new ProblemaDivisaoExata("\\($a\\) maçãs foram colocadas igualmente em \\($b\\) sacolas. Quantas maçãs há em cada sacola?"),
	new ProblemaDivisaoExata("\\($a\\) bombons foram divididos igualmente em \\($b\\) potes. Quantos bombons há em cada pote?"),
	new ProblemaDivisaoExata("\\($a\\) parafusos foram separados igualmente em \\($b\\) caixinhas. Quantos parafusos há em cada caixinha?"),
	new ProblemaDivisaoExata("\\($a\\) flores foram arranjadas igualmente em \\($b\\) vasos. Quantas flores há em cada vaso?"),
	new ProblemaDivisaoExata("\\($a\\) garrafas foram organizadas igualmente em \\($b\\) engradados. Quantas garrafas há em cada engradado?"),
	new ProblemaDivisaoExata("\\($a\\) chocolates foram embalados igualmente em \\($b\\) caixas. Quantos chocolates há em cada caixa?"),
	new ProblemaDivisaoExata("\\($a\\) canetas foram distribuídas igualmente em \\($b\\) pacotes. Quantas canetas há em cada pacote?"),

	// --- arranjos em fileiras ---
	new ProblemaDivisaoExata("\\($a\\) cadeiras foram organizadas em \\($b\\) fileiras iguais. Quantas cadeiras há em cada fileira?"),
	new ProblemaDivisaoExata("\\($a\\) árvores foram plantadas em \\($b\\) fileiras iguais. Quantas árvores há em cada fileira?"),
	new ProblemaDivisaoExata("\\($a\\) livros foram colocados igualmente em \\($b\\) prateleiras. Quantos livros há em cada prateleira?"),
	new ProblemaDivisaoExata("\\($a\\) azulejos cobrem \\($b\\) paredes iguais. Quantos azulejos há em cada parede?"),
	new ProblemaDivisaoExata("\\($a\\) mudas foram plantadas igualmente em \\($b\\) canteiros. Quantas mudas há em cada canteiro?"),
	new ProblemaDivisaoExata("\\($a\\) carros foram estacionados igualmente em \\($b\\) filas. Quantos carros há em cada fila?"),
	new ProblemaDivisaoExata("\\($a\\) quadros foram pendurados igualmente em \\($b\\) salas. Quantos quadros há em cada sala?"),
	new ProblemaDivisaoExata("\\($a\\) figurinhas foram coladas igualmente em \\($b\\) páginas. Quantas figurinhas há em cada página?"),

	// --- dinheiro e medidas ---
	new ProblemaDivisaoExata("$nomeM dividiu \\($a\\) reais igualmente entre \\($b\\) cofrinhos. Quanto ele colocou em cada cofrinho?"),
	new ProblemaDivisaoExata("Uma conta de \\($a\\) reais foi dividida igualmente entre \\($b\\) amigos. Quanto cada um pagou?"),
	new ProblemaDivisaoExata("\\($a\\) litros de suco foram colocados igualmente em \\($b\\) jarras. Quantos litros há em cada jarra?"),
	new ProblemaDivisaoExata("\\($a\\) metros de corda foram cortados em \\($b\\) pedaços iguais. Quantos metros tem cada pedaço?"),
	new ProblemaDivisaoExata("\\($a\\) kg de ração foram divididos igualmente entre \\($b\\) baias. Quantos kg ficaram em cada baia?"),
	new ProblemaDivisaoExata("$nomeF dividiu \\($a\\) reais de mesada igualmente por \\($b\\) semanas. Quanto ela pode gastar por semana?"),
	new ProblemaDivisaoExata("\\($a\\) minutos de aula foram divididos igualmente em \\($b\\) atividades. Quantos minutos tem cada atividade?"),
	new ProblemaDivisaoExata("\\($a\\) gramas de tempero foram repartidos igualmente em \\($b\\) potinhos. Quantos gramas há em cada potinho?"),

	// --- produção e trabalho ---
	new ProblemaDivisaoExata("Uma fábrica produziu \\($a\\) peças que serão divididas igualmente entre \\($b\\) lojas. Quantas peças cada loja recebe?"),
	new ProblemaDivisaoExata("\\($a\\) tarefas foram divididas igualmente entre \\($b\\) funcionários. Quantas tarefas cada um fará?"),
	new ProblemaDivisaoExata("\\($a\\) pacotes serão entregues igualmente por \\($b\\) carteiros. Quantos pacotes cada carteiro entrega?"),
	new ProblemaDivisaoExata("\\($a\\) cestas básicas serão distribuídas igualmente em \\($b\\) bairros. Quantas cestas vão para cada bairro?"),
	new ProblemaDivisaoExata("\\($a\\) ingressos foram divididos igualmente entre \\($b\\) escolas. Quantos ingressos cada escola recebeu?"),
	new ProblemaDivisaoExata("\\($a\\) uniformes foram distribuídos igualmente entre \\($b\\) times. Quantos uniformes cada time recebeu?"),
	new ProblemaDivisaoExata("\\($a\\) mudas foram doadas igualmente para \\($b\\) escolas. Quantas mudas cada escola recebeu?"),
	new ProblemaDivisaoExata("\\($a\\) livros foram doados igualmente para \\($b\\) bibliotecas. Quantos livros cada biblioteca recebeu?"),

	// --- comida ---
	new ProblemaDivisaoExata("Uma pizza foi cortada e \\($a\\) fatias foram divididas igualmente entre \\($b\\) pessoas. Quantas fatias cada uma comeu?"),
	new ProblemaDivisaoExata("\\($a\\) pães foram divididos igualmente em \\($b\\) sacolas. Quantos pães há em cada sacola?"),
	new ProblemaDivisaoExata("\\($a\\) docinhos foram colocados igualmente em \\($b\\) bandejas. Quantos docinhos há em cada bandeja?"),
	new ProblemaDivisaoExata("\\($a\\) frutas foram repartidas igualmente em \\($b\\) cestas. Quantas frutas há em cada cesta?"),
	new ProblemaDivisaoExata("\\($a\\) salgados foram divididos igualmente entre \\($b\\) mesas. Quantos salgados foram para cada mesa?"),
	new ProblemaDivisaoExata("\\($a\\) litros de leite foram divididos igualmente em \\($b\\) baldes. Quantos litros há em cada balde?"),
	new ProblemaDivisaoExata("\\($a\\) ovos de páscoa foram distribuídos igualmente entre \\($b\\) crianças. Quantos ovos cada criança recebeu?"),

	// --- animais e natureza ---
	new ProblemaDivisaoExata("\\($a\\) peixes foram divididos igualmente em \\($b\\) aquários. Quantos peixes há em cada aquário?"),
	new ProblemaDivisaoExata("\\($a\\) galinhas foram distribuídas igualmente em \\($b\\) galinheiros. Quantas galinhas há em cada galinheiro?"),
	new ProblemaDivisaoExata("\\($a\\) sementes foram plantadas igualmente em \\($b\\) vasos. Quantas sementes há em cada vaso?"),
	new ProblemaDivisaoExata("\\($a\\) filhotes foram acomodados igualmente em \\($b\\) ninhos. Quantos filhotes há em cada ninho?"),
	new ProblemaDivisaoExata("\\($a\\) abelhas foram divididas igualmente em \\($b\\) colmeias. Quantas abelhas há em cada colmeia?"),
	new ProblemaDivisaoExata("\\($a\\) árvores foram divididas igualmente em \\($b\\) trilhas. Quantas árvores há em cada trilha?"),

	// --- variados ---
	new ProblemaDivisaoExata("\\($a\\) tijolos foram divididos igualmente entre \\($b\\) pedreiros. Quantos tijolos cada um usou?"),
	new ProblemaDivisaoExata("\\($a\\) cromos foram repartidos igualmente entre \\($b\\) colegas. Quantos cromos cada um recebeu?"),
	new ProblemaDivisaoExata("\\($a\\) cadeiras foram emprestadas igualmente para \\($b\\) salas. Quantas cadeiras foram para cada sala?"),
	new ProblemaDivisaoExata("\\($a\\) balões foram divididos igualmente entre \\($b\\) festas. Quantos balões foram para cada festa?"),
	new ProblemaDivisaoExata("\\($a\\) medalhas foram distribuídas igualmente entre \\($b\\) escolas. Quantas medalhas cada escola recebeu?"),
	new ProblemaDivisaoExata("\\($a\\) pincéis foram divididos igualmente em \\($b\\) kits. Quantos pincéis há em cada kit?"),
	new ProblemaDivisaoExata("\\($a\\) bolas foram divididas igualmente em \\($b\\) caixas de material. Quantas bolas há em cada caixa?"),
	new ProblemaDivisaoExata("\\($a\\) cartões foram repartidos igualmente entre \\($b\\) participantes. Quantos cartões cada um recebeu?"),
	new ProblemaDivisaoExata("\\($a\\) brinquedos foram divididos igualmente entre \\($b\\) creches. Quantos brinquedos cada creche recebeu?"),
	new ProblemaDivisaoExata("\\($a\\) selos foram colados igualmente em \\($b\\) páginas do álbum. Quantos selos há em cada página?"),
	new ProblemaDivisaoExata("\\($a\\) lanches foram divididos igualmente entre \\($b\\) turmas. Quantos lanches cada turma recebeu?"),
	new ProblemaDivisaoExata("\\($a\\) cadernos foram distribuídos igualmente entre \\($b\\) alunos. Quantos cadernos cada aluno recebeu?"),
	new ProblemaDivisaoExata("\\($a\\) ferramentas foram divididas igualmente em \\($b\\) oficinas. Quantas ferramentas há em cada oficina?"),
	new ProblemaDivisaoExata("\\($a\\) pedras preciosas foram divididas igualmente em \\($b\\) cofres. Quantas pedras há em cada cofre?"),
	new ProblemaDivisaoExata("\\($a\\) garrafas de água foram divididas igualmente entre \\($b\\) corredores. Quantas garrafas cada um recebeu?"),
	new ProblemaDivisaoExata("\\($a\\) cestos de roupa foram divididos igualmente em \\($b\\) varais. Quantos cestos foram para cada varal?"),
	new ProblemaDivisaoExata("\\($a\\) ingressos de cinema foram divididos igualmente entre \\($b\\) famílias. Quantos ingressos cada família recebeu?"),
	new ProblemaDivisaoExata("\\($a\\) doces foram divididos igualmente entre \\($b\\) sacolinhas. Quantos doces há em cada sacolinha?"),
	new ProblemaDivisaoExata("\\($a\\) passageiros foram divididos igualmente em \\($b\\) ônibus. Quantos passageiros há em cada ônibus?"),
	new ProblemaDivisaoExata("\\($a\\) tampinhas foram divididas igualmente em \\($b\\) potes. Quantas tampinhas há em cada pote?"),
	new ProblemaDivisaoExata("$nomeF dividiu \\($a\\) contas igualmente em \\($b\\) colares. Quantas contas há em cada colar?"),
	new ProblemaDivisaoExata("\\($a\\) cadernos foram empilhados igualmente em \\($b\\) caixas. Quantos cadernos há em cada caixa?"),
	new ProblemaDivisaoExata("\\($a\\) reais de gorjeta foram divididos igualmente entre \\($b\\) garçons. Quanto cada garçom recebeu?"),

	};

	public static ProblemaDivisaoExata getProblema()
	{
		Random rand = new Random();
		return problemas[rand.nextInt(problemas.length)].clone();
	}
}
