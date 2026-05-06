package matematica.basico.mmcmdc.nivel3package;

import java.util.Random;

import matematica.basico.mmcmdc.ProblemaMmcMdc;
import matematica.basico.mmcmdc.TipoProblema;

public class TextoMmcMdc
{
	static ProblemaMmcMdc problemas[] = {
	new ProblemaMmcMdc("Três amigos correm em uma pista circular. Um completa a volta em $a minutos, outro em $b minutos e o terceiro em $c minutos. Em quanto tempo se encontrarão no ponto de partida?", TipoProblema.MmcTres),
	new ProblemaMmcMdc("Três luzes piscam em intervalos regulares: $a segundos, $b segundos e $c segundos. Em quanto tempo elas piscarão juntas novamente?", TipoProblema.MmcTres),
	new ProblemaMmcMdc("Três ônibus partem de um terminal a cada $a, $b e $c minutos. Em quanto tempo sairão juntos novamente pela primeira vez?", TipoProblema.MmcTres),
	new ProblemaMmcMdc("Três alarmes tocam a cada $a, $b e $c minutos. Em quanto tempo tocarão juntos novamente pela primeira vez?", TipoProblema.MmcTres),
	new ProblemaMmcMdc("Três robôs se recarregam a cada $a, $b e $c horas. Quando precisarão de recarga ao mesmo tempo pela primeira vez?", TipoProblema.MmcTres),
	new ProblemaMmcMdc("Três serviços de entrega saem do centro de distribuição a cada $a, $b e $c horas. Quando sairão juntos pela primeira vez?", TipoProblema.MmcTres),
	new ProblemaMmcMdc("Três astros passam por uma constelação a cada $a, $b e $c anos. Quando passarão juntos de novo pela primeira vez?", TipoProblema.MmcTres),
	new ProblemaMmcMdc("Três fogos de artifício estouram a cada $a, $b e $c segundos. Quando explodirão juntos pela primeira vez?", TipoProblema.MmcTres),
	new ProblemaMmcMdc("Três sensores disparam alertas a cada $a, $b e $c segundos. Em quanto tempo dispararão ao mesmo tempo pela primeira vez?", TipoProblema.MmcTres),
	new ProblemaMmcMdc("Três nadadores percorrem uma piscina em $a, $b e $c segundos. Quando chegarão juntos à borda pela primeira vez?", TipoProblema.MmcTres),
	new ProblemaMmcMdc("Três fontes jorram água a cada $a, $b e $c minutos. Em quanto tempo jorrarão juntas pela primeira vez?", TipoProblema.MmcTres),
	new ProblemaMmcMdc("Três trens partem simultaneamente a cada $a, $b e $c minutos. Em quanto tempo partirão juntos novamente pela primeira vez?", TipoProblema.MmcTres),
	new ProblemaMmcMdc("Três cozinheiros assam pães em $a, $b e $c minutos. Quando assarão juntos um novo lote pela primeira vez?", TipoProblema.MmcTres),
	new ProblemaMmcMdc("Três faróis piscam a cada $a, $b e $c segundos. Em quanto tempo piscarão juntos novamente pela primeira vez?", TipoProblema.MmcTres),
	new ProblemaMmcMdc("Três drones retornam à base a cada $a, $b e $c minutos. Quando pousarão juntos novamente pela primeira vez?", TipoProblema.MmcTres),
	new ProblemaMmcMdc("Três caminhões descarregam mercadorias a cada $a, $b e $c minutos. Em quanto tempo descarregarão juntos pela primeira vez?", TipoProblema.MmcTres),
	new ProblemaMmcMdc("Três sistemas fazem backup a cada $a, $b e $c horas. Quando farão backup juntos pela primeira vez?", TipoProblema.MmcTres),
	new ProblemaMmcMdc("Três relógios atrasados tocam alarmes a cada $a, $b e $c minutos. Quando tocarão juntos pela primeira vez?", TipoProblema.MmcTres),
	new ProblemaMmcMdc("Três cataventos completam voltas a cada $a, $b e $c segundos. Quando estarão na mesma posição pela primeira vez?", TipoProblema.MmcTres),
	new ProblemaMmcMdc("Três semáforos ficam vermelhos a cada $a, $b e $c segundos. Quando ficarão vermelhos juntos pela primeira vez?", TipoProblema.MmcTres),
	new ProblemaMmcMdc("Três impressoras imprimem um lote a cada $a, $b e $c minutos. Em quanto tempo imprimirão juntas pela primeira vez?", TipoProblema.MmcTres),
	new ProblemaMmcMdc("Três servidores são reiniciados a cada $a, $b e $c minutos. Quando serão reiniciados juntos pela primeira vez?", TipoProblema.MmcTres),
	new ProblemaMmcMdc("Três amigos treinam corrida, dando voltas em $a, $b e $c minutos. Quando terminarão juntos pela primeira vez?", TipoProblema.MmcTres),
	new ProblemaMmcMdc("Três engenheiros fazem inspeções a cada $a, $b e $c dias. Quando farão a inspeção juntos pela primeira vez?", TipoProblema.MmcTres),
	new ProblemaMmcMdc("Três ciclistas dão voltas em uma pista em $a, $b e $c minutos. Quando se encontrarão no ponto de partida pela primeira vez?", TipoProblema.MmcTres),
	new ProblemaMmcMdc("Três barcos fazem um percurso em $a, $b e $c minutos. Quando se encontrarão no ponto de partida pela primeira vez?", TipoProblema.MmcTres),
	new ProblemaMmcMdc("Três planetas completam órbitas em $a, $b e $c dias. Quando estarão alinhados novamente pela primeira vez?", TipoProblema.MmcTres),
	new ProblemaMmcMdc("Três elevadores param a cada $a, $b e $c andares. Em qual andar pararão juntos pela primeira vez?", TipoProblema.MmcTres),
	new ProblemaMmcMdc("Três máquinas produzem peças a cada $a, $b e $c minutos. Quando produzirão a mesma peça juntas pela primeira vez?", TipoProblema.MmcTres),
	new ProblemaMmcMdc("Três equipes de limpeza limpam um local a cada $a, $b e $c horas. Quando terminarão juntas pela primeira vez?", TipoProblema.MmcTres),
	new ProblemaMmcMdc("Três satélites orbitam a Terra a cada $a, $b e $c horas. Quando estarão alinhados novamente pela primeira vez?", TipoProblema.MmcTres),
	new ProblemaMmcMdc("Três torneiras pingam a cada $a, $b e $c segundos. Em quanto tempo pingarão juntas pela primeira vez?", TipoProblema.MmcTres),
	new ProblemaMmcMdc("Três velas acendem a cada $a, $b e $c minutos. Em quanto tempo acenderão juntas pela primeira vez?", TipoProblema.MmcTres),
	new ProblemaMmcMdc("Três sinos tocam a cada $a, $b e $c minutos. Em quanto tempo tocarão juntos pela primeira vez?", TipoProblema.MmcTres),
	new ProblemaMmcMdc("Três luzes de Natal piscam a cada $a, $b e $c segundos. Em quanto tempo piscarão juntas pela primeira vez?", TipoProblema.MmcTres),
	new ProblemaMmcMdc("Três gotículas de chuva caem a cada $a, $b e $c segundos. Em quanto tempo cairão juntas pela primeira vez?", TipoProblema.MmcTres),
	new ProblemaMmcMdc("Três folhas caem de uma árvore a cada $a, $b e $c minutos. Em quanto tempo cairão juntas pela primeira vez?", TipoProblema.MmcTres),
	new ProblemaMmcMdc("Três pássaros cantam a cada $a, $b e $c segundos. Em quanto tempo cantarão juntos pela primeira vez?", TipoProblema.MmcTres),
	new ProblemaMmcMdc("Três ondas quebram na praia a cada $a, $b e $c segundos. Em quanto tempo quebrarão juntas pela primeira vez?", TipoProblema.MmcTres),
	new ProblemaMmcMdc("Três bolas quicam a cada $a, $b e $c segundos. Em quanto tempo quicarão juntas pela primeira vez?", TipoProblema.MmcTres),
	new ProblemaMmcMdc("Um florista tem $a rosas, $b tulipas e $c lírios. Ele quer fazer buquês idênticos com o máximo de flores de cada tipo. Quantos buquês ele pode fazer?", TipoProblema.MdcTres),
	new ProblemaMmcMdc("Uma confeiteira fez $a brigadeiros, $b beijinhos e $c cajuzinhos. Ela quer embalar os doces em caixas com a mesma quantidade de cada tipo. Quantas caixas ela precisa?", TipoProblema.MdcTres),
	new ProblemaMmcMdc("Uma papelaria tem $a lápis, $b canetas e $c borrachas. O dono quer montar kits com o máximo de itens de cada tipo. Quantos kits ele pode montar?", TipoProblema.MdcTres),
	new ProblemaMmcMdc("Um marceneiro tem $a tábuas de pinho, $b tábuas de carvalho e $c tábuas de cedro. Ele quer cortar as tábuas em pedaços iguais, do maior tamanho possível. Qual o comprimento de cada pedaço?", TipoProblema.MdcTres),
	new ProblemaMmcMdc("Uma costureira tem $a metros de tecido estampado, $b metros de tecido liso e $c metros de tecido xadrez. Ela quer cortar os tecidos em pedaços iguais, do maior tamanho possível. Qual o comprimento de cada pedaço?", TipoProblema.MdcTres),
	new ProblemaMmcMdc("Uma fábrica de brinquedos tem $a bolas, $b carrinhos e $c bonecas. Eles querem embalar os brinquedos em caixas com a mesma quantidade de cada tipo. Quantas caixas eles precisam?", TipoProblema.MdcTres),
	new ProblemaMmcMdc("Um agricultor colheu $a laranjas, $b maçãs e $c bananas. Ele quer distribuir as frutas em sacolas com a mesma quantidade de cada tipo. Quantas sacolas ele precisa?", TipoProblema.MdcTres),
	new ProblemaMmcMdc("Uma loja de animais tem $a rações para cães, $b rações para gatos e $c rações para pássaros. Eles querem montar kits com a mesma quantidade de cada tipo. Quantos kits eles podem montar?", TipoProblema.MdcTres),
	new ProblemaMmcMdc("Um padeiro fez $a pães de queijo, $b pães franceses e $c pães integrais. Ele quer embalar os pães em sacos com a mesma quantidade de cada tipo. Quantos sacos ele precisa?", TipoProblema.MdcTres),
	new ProblemaMmcMdc("Uma livraria tem $a livros de aventura, $b livros de romance e $c livros de suspense. Eles querem montar kits com a mesma quantidade de cada tipo. Quantos kits eles podem montar?", TipoProblema.MdcTres),
	new ProblemaMmcMdc("Um açougueiro tem $a kg de carne bovina, $b kg de carne suína e $c kg de carne de frango. Ele quer dividir as carnes em porções iguais, do maior tamanho possível. Qual o peso de cada porção?", TipoProblema.MdcTres),
	new ProblemaMmcMdc("Uma loja de eletrônicos tem $a celulares, $b tablets e $c notebooks. Eles querem montar kits com a mesma quantidade de cada tipo. Quantos kits eles podem montar?", TipoProblema.MdcTres),
	new ProblemaMmcMdc("Uma fábrica de doces tem $a balas de coco, $b balas de hortelã e $c balas de morango. Eles querem embalar as balas em pacotes com a mesma quantidade de cada tipo. Quantos pacotes eles precisam?", TipoProblema.MdcTres),
	new ProblemaMmcMdc("Um jardineiro tem $a mudas de rosas, $b mudas de margaridas e $c mudas de girassóis. Ele quer plantar as mudas em fileiras com a mesma quantidade de cada tipo. Quantas fileiras ele pode fazer?", TipoProblema.MdcTres),
	new ProblemaMmcMdc("Uma loja de roupas tem $a camisetas, $b calças e $c meias. Eles querem montar kits com a mesma quantidade de cada tipo. Quantos kits eles podem montar?", TipoProblema.MdcTres),
	new ProblemaMmcMdc("Um químico tem $a ml de ácido sulfúrico, $b ml de ácido clorídrico e $c ml de ácido nítrico. Ele quer dividir os ácidos em recipientes iguais, do maior tamanho possível. Qual o volume de cada recipiente?", TipoProblema.MdcTres),
	new ProblemaMmcMdc("Uma fábrica de parafusos tem $a parafusos de aço, $b parafusos de ferro e $c parafusos de alumínio. Eles querem embalar os parafusos em caixas com a mesma quantidade de cada tipo. Quantas caixas eles precisam?", TipoProblema.MdcTres),
	new ProblemaMmcMdc("Um fazendeiro tem $a vacas, $b cavalos e $c porcos. Ele quer dividir os animais em grupos com a mesma quantidade de cada tipo. Quantos grupos ele pode formar?", TipoProblema.MdcTres),
	new ProblemaMmcMdc("Uma loja de tintas tem $a litros de tinta branca, $b litros de tinta azul e $c litros de tinta vermelha. Eles querem embalar as tintas em latas com a mesma quantidade de cada tipo. Quantas latas eles precisam?", TipoProblema.MdcTres),
	new ProblemaMmcMdc("Um professor tem $a canetas vermelhas, $b canetas azuis e $c canetas pretas. Ele quer distribuir as canetas entre o maior número possível de seus alunos, dando a mesma quantidade de cada cor para cada aluno. Quantos alunos seriam?", TipoProblema.MdcTres),
	new ProblemaMmcMdc("Uma fábrica de biscoitos tem $a biscoitos de chocolate, $b biscoitos de morango e $c biscoitos de leite. Eles querem embalar os biscoitos em pacotes com a mesma quantidade de cada tipo. Quantos pacotes eles precisam?", TipoProblema.MdcTres),
	new ProblemaMmcMdc("Um marceneiro tem $a pregos, $b parafusos e $c arruelas. Ele quer montar kits com a mesma quantidade de cada tipo. Quantos kits ele pode montar?", TipoProblema.MdcTres),
	new ProblemaMmcMdc("Uma loja de frutas tem $a mangas, $b abacaxis e $c melões. Eles querem distribuir as frutas em cestas com a mesma quantidade de cada tipo. Quantas cestas eles precisam?", TipoProblema.MdcTres),
	new ProblemaMmcMdc("Um colecionador tem $a selos, $b moedas e $c cartões postais. Ele quer organizar sua coleção em álbuns com a mesma quantidade de cada item. Quantos álbuns ele precisa?", TipoProblema.MdcTres),
	new ProblemaMmcMdc("Uma padaria fez $a sonhos, $b carolinas e $c bombas de chocolate. Eles querem embalar os doces em caixas com a mesma quantidade de cada tipo. Quantas caixas eles precisam?", TipoProblema.MdcTres),
	new ProblemaMmcMdc("Um artesão tem $a metros de corda, $b metros de barbante e $c metros de fio de lã. Ele quer cortar os materiais em pedaços iguais, do maior tamanho possível. Qual o comprimento de cada pedaço?", TipoProblema.MdcTres),
	new ProblemaMmcMdc("Uma fábrica de velas tem $a velas brancas, $b velas azuis e $c velas amarelas. Eles querem embalar as velas em caixas com a mesma quantidade de cada cor. Quantas caixas eles precisam?", TipoProblema.MdcTres),
	new ProblemaMmcMdc("Um açougueiro tem $a linguiças, $b salsichas e $c hambúrgueres. Ele quer montar kits para churrasco com a mesma quantidade de cada item. Quantos kits ele pode montar?", TipoProblema.MdcTres),
	new ProblemaMmcMdc("Uma floricultura tem $a orquídeas, $b girassóis e $c violetas. Eles querem montar arranjos de flores com a mesma quantidade de cada tipo. Quantos arranjos eles podem montar?", TipoProblema.MdcTres),
	new ProblemaMmcMdc("Um confeiteiro tem $a kg de chocolate, $b kg de doce de leite e $c kg de pasta americana. Ele quer dividir os ingredientes em porções iguais, do maior tamanho possível. Qual o peso de cada porção?", TipoProblema.MdcTres),
	new ProblemaMmcMdc("Uma loja de ferramentas tem $a chaves de fenda, $b chaves inglesas e $c alicates. Eles querem montar kits com a mesma quantidade de cada ferramenta. Quantos kits eles podem montar?", TipoProblema.MdcTres),
	new ProblemaMmcMdc("Um agricultor colheu $a abóboras, $b melancias e $c morangos. Ele quer distribuir as frutas em caixas com a mesma quantidade de cada tipo. Quantas caixas ele precisa?", TipoProblema.MdcTres),
	new ProblemaMmcMdc("Uma fábrica de brinquedos tem $a ursinhos de pelúcia, $b carrinhos de controle remoto e $c jogos de tabuleiro. Eles querem embalar os brinquedos em caixas com a mesma quantidade de cada tipo. Quantas caixas eles precisam?", TipoProblema.MdcTres),
	new ProblemaMmcMdc("Um padeiro fez $a bolos de cenoura, $b bolos de chocolate e $c bolos de laranja. Ele quer embalar os bolos em caixas com a mesma quantidade de cada tipo. Quantas caixas ele precisa?", TipoProblema.MdcTres),
	new ProblemaMmcMdc("Uma livraria tem $a livros de poesia, $b livros de contos e $c livros de crônicas. Eles querem montar kits com a mesma quantidade de cada tipo. Quantos kits eles podem montar?", TipoProblema.MdcTres),
	new ProblemaMmcMdc("Um açougueiro tem $a kg de carne moída, $b kg de linguiça e $c kg de frango. Ele quer dividir as carnes em porções iguais, do maior tamanho possível. Qual o peso de cada porção?", TipoProblema.MdcTres),
	new ProblemaMmcMdc("Uma loja de eletrônicos tem $a fones de ouvido, $b caixas de som e $c microfones. Eles querem montar kits com a mesma quantidade de cada tipo. Quantos kits eles podem montar?", TipoProblema.MdcTres),
	new ProblemaMmcMdc("Uma fábrica de doces tem $a pirulitos, $b chicletes e $c balas de goma. Eles querem embalar os doces em pacotes com a mesma quantidade de cada tipo. Quantos pacotes eles precisam?", TipoProblema.MdcTres),
	new ProblemaMmcMdc("Um jardineiro tem $a mudas de rosas, $b mudas de lírios e $c mudas de tulipas. Ele quer plantar as mudas em fileiras com a mesma quantidade de cada tipo. Quantas fileiras ele pode fazer?", TipoProblema.MdcTres),
	new ProblemaMmcMdc("Uma loja de roupas tem $a camisas, $b bermudas e $c bonés. Eles querem montar kits com a mesma quantidade de cada tipo. Quantos kits eles podem montar?", TipoProblema.MdcTres)
	};

	public static ProblemaMmcMdc getProblemaMMC()
	{
		Random rand = new Random();
		int index = rand.nextInt(problemas.length);
		return problemas[index].clone();
	}

}
