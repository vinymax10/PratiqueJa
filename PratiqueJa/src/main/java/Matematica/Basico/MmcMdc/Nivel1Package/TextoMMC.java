package Matematica.Basico.MmcMdc.Nivel1Package;

import java.util.Random;

import Matematica.Basico.MmcMdc.ProblemaMmcMdc;
import Matematica.Basico.MmcMdc.TipoProblema;

public class TextoMMC
{
	static ProblemaMmcMdc problemas[] = {
	new ProblemaMmcMdc(
	"Dois faróis piscam em tempos diferentes: um a cada $a segundos e outro a cada $b segundos. Depois de quanto tempo eles piscarão juntos novamente?",
	TipoProblema.MmcDuas),
	new ProblemaMmcMdc("Dois trens partem simultaneamente, um a cada $a minutos e outro a cada $b minutos. Em quanto tempo partirão juntos novamente?",
	TipoProblema.MmcDuas), 
	new ProblemaMmcMdc("Uma impressora imprime um lote a cada $a minutos e outra a cada $b minutos. Em quanto tempo imprimirão juntas novamente?", 
	TipoProblema.MmcDuas), 
	new ProblemaMmcMdc("Dois ciclistas dão voltas em uma pista. Um completa a volta em $a minutos e outro em $b minutos. Em quanto tempo se encontrarão no ponto de partida?", 
	TipoProblema.MmcDuas), 
	new ProblemaMmcMdc("Uma máquina embala produtos a cada $a segundos e outra a cada $b segundos. Em quanto tempo embalarão juntas um item?", 
	TipoProblema.MmcDuas),
	new ProblemaMmcMdc("Dois alarmes disparam em intervalos de $a e $b minutos. Em quanto tempo tocarão juntos novamente?", 
	TipoProblema.MmcDuas), 
	new ProblemaMmcMdc("Dois ônibus partem de um terminal a cada $a e $b minutos. Em quanto tempo sairão juntos novamente?", 
	TipoProblema.MmcDuas), 
	new ProblemaMmcMdc("Dois nadadores percorrem uma piscina em tempos de $a e $b segundos. Quando chegarão juntos à borda novamente?", 
	TipoProblema.MmcDuas),
	new ProblemaMmcMdc("Uma fonte jorra água a cada $a minutos e outra a cada $b minutos. Em quanto tempo jorrarão juntas?", 
	TipoProblema.MmcDuas), 
	new ProblemaMmcMdc("Duas lâmpadas piscam em intervalos de $a e $b segundos. Quando piscarão juntas de novo?", 
	TipoProblema.MmcDuas), 
	new ProblemaMmcMdc("Dois servidores são reiniciados a cada $a e $b minutos. Quando serão reiniciados juntos novamente?", 
	TipoProblema.MmcDuas),
	new ProblemaMmcMdc("Dois caminhões descarregam mercadorias a cada $a e $b minutos. Em quanto tempo descarregarão juntos?", 
	TipoProblema.MmcDuas), 
	new ProblemaMmcMdc("Dois amigos treinam corrida, um dando uma volta em $a minutos e outro em $b minutos. Quando terminarão juntos?", 
	TipoProblema.MmcDuas), 
	new ProblemaMmcMdc("Dois fogos de artifício estouram a cada $a e $b segundos. Quando explodirão juntos novamente?", 
	TipoProblema.MmcDuas),
	new ProblemaMmcMdc("Dois relógios atrasados tocam alarmes a cada $a e $b minutos. Quando tocarão juntos?", 
	TipoProblema.MmcDuas), 
	new ProblemaMmcMdc("Dois barcos fazem um percurso em $a e $b minutos. Quando se encontrarão no ponto de partida?", 
	TipoProblema.MmcDuas), 
	new ProblemaMmcMdc("Um elevador para a cada $a andares e outro a cada $b andares. Em qual andar pararão juntos primeiro?", 
	TipoProblema.MmcDuas),
	new ProblemaMmcMdc("Dois planetas completam órbitas em $a e $b dias. Quando estarão alinhados novamente?", 
	TipoProblema.MmcDuas), 
	new ProblemaMmcMdc("Dois engenheiros fazem medições em tempos de $a e $b horas. Quando medirão juntos de novo?", 
	TipoProblema.MmcDuas), 
	new ProblemaMmcMdc("Dois sensores disparam alertas a cada $a e $b segundos. Em quanto tempo dispararão ao mesmo tempo?", 
	TipoProblema.MmcDuas),
	new ProblemaMmcMdc("Dois sistemas fazem backup em tempos de $a e $b horas. Quando farão backup juntos?", 
	TipoProblema.MmcDuas), 
	new ProblemaMmcMdc("Dois robôs se recarregam a cada $a e $b horas. Quando precisarão de recarga ao mesmo tempo?", 
	TipoProblema.MmcDuas), 
	new ProblemaMmcMdc("Dois drones retornam à base em intervalos de $a e $b minutos. Quando pousarão juntos novamente?", 
	TipoProblema.MmcDuas),
	new ProblemaMmcMdc("Dois torneios ocorrem a cada $a e $b dias. Quando coincidirão na mesma data novamente?", 
	TipoProblema.MmcDuas), 
	new ProblemaMmcMdc("Dois engenheiros fazem inspeções periódicas a cada $a e $b dias. Quando farão a mesma inspeção juntos?", 
	TipoProblema.MmcDuas), 
	new ProblemaMmcMdc("Dois jogos são lançados a cada $a e $b anos. Quando serão lançados no mesmo ano?", 
	TipoProblema.MmcDuas),
	new ProblemaMmcMdc("Dois trens fazem a mesma rota em tempos de $a e $b minutos. Quando partirão juntos novamente?", 
	TipoProblema.MmcDuas), 
	new ProblemaMmcMdc("Dois astros passam por uma constelação a cada $a e $b anos. Quando passarão juntos de novo?", 
	TipoProblema.MmcDuas), 
	new ProblemaMmcMdc("Dois cozinheiros assam pães em $a e $b minutos. Quando assarão juntos um novo lote?", 
	TipoProblema.MmcDuas),
	new ProblemaMmcMdc("Dois cataventos completam voltas a cada $a e $b segundos. Quando estarão na mesma posição de novo?", 
	TipoProblema.MmcDuas),
	new ProblemaMmcMdc("Dois serviços de entrega saem do centro de distribuição a cada $a e $b horas. Quando sairão juntos?", 
	TipoProblema.MmcDuas),
	new ProblemaMmcMdc("Dois aplicativos sincronizam dados a cada $a e $b minutos. Quando farão sincronização simultânea?", 
	TipoProblema.MmcDuas),
	new ProblemaMmcMdc("Dois semáforos ficam vermelhos a cada $a e $b segundos. Quando ficarão vermelhos juntos novamente?", 
	TipoProblema.MmcDuas),
	};

	public static ProblemaMmcMdc getProblemaMMC()
	{
		Random rand = new Random();
		int index = rand.nextInt(problemas.length);
		return problemas[index].clone();
	}

}
