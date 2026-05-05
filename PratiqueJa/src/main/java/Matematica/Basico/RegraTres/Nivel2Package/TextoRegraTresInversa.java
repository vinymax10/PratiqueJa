package Matematica.Basico.RegraTres.Nivel2Package;

import java.util.Random;

import Matematica.Basico.RegraTres.ProblemaRegraTres;
import Matematica.Basico.RegraTres.TipoProporcao;

public class TextoRegraTresInversa
{
	static ProblemaRegraTres  problemas[]= {
	new ProblemaRegraTres(
	"Se $a pedreiros constroem um muro em $b horas, quantas horas $c pedreiros fariam o mesmo trabalho?",
	"Pedreiros","Horas","",TipoProporcao.IABCX,1,40),
	new ProblemaRegraTres(
	"Se $a torneiras enchem um tanque em $b minutos, quanto tempo levam $c torneiras?",
	"Torneiras","Minutos","",TipoProporcao.IABCX,1,60),
	new ProblemaRegraTres(
	"Se $a pintores pintam uma casa em $b horas, em quantas horas $c pintores fariam o mesmo serviço?",
	"Pintores","Horas","",TipoProporcao.IABCX,1,40),
	new ProblemaRegraTres(
	"Se $a caminhões transportam uma carga em $b minutos, quanto tempo levariam $c caminhões?",
	"Caminhões","Minutos","",TipoProporcao.IABCX,1,1800),
	new ProblemaRegraTres(
	"Se $a trabalhadores fazem um serviço em $b horas, quantas horas levariam $c trabalhadores?",
	"Trabalhadores","Horas","",TipoProporcao.IABCX,1,40),
	new ProblemaRegraTres(
	"Se $a máquinas produzem 1000 peças em $b minutos, quanto tempo levariam $c máquinas?",
	"Máquinas","Minutos","",TipoProporcao.IABCX,1,1800),
	new ProblemaRegraTres(
	"Se $a trabalhadores constroem uma casa em $b dias, quantos trabalhadores seriam necessários para construir em $c dias?",
	"Trabalhadores","Dias","",TipoProporcao.IABXC,1,540),
	new ProblemaRegraTres(
	"Se $a operários terminam uma obra em $b dias, quantos dias $c operários fariam o mesmo trabalho?",
	"Operários","Dias","",TipoProporcao.IABCX,1,540),
	new ProblemaRegraTres(
	"$a mangueira enchem uma piscina em $b minutos. Se usarmos $c mangueiras idênticas, em quanto tempo a piscina ficará cheia?",
	"Mangueira","Minutos","",TipoProporcao.IABCX,1,1500),
	new ProblemaRegraTres(
	"Se $a pessoas limpam um parque em $b horas, em quantas horas $c pessoas fariam o mesmo serviço?",
	"Pessoas","Horas","",TipoProporcao.IABCX,1,1200),
	new ProblemaRegraTres(
	"Se $a impressoras imprimem 100 mil folhas em $b minutos, quanto tempo levariam $c impressoras?",
	"Impressoras","Minutos","",TipoProporcao.IABCX,1,3000),
	new ProblemaRegraTres(
	"$a fontes enchem um tanque em $b minutos. Se forem ligadas $c fontes idênticas, em quanto tempo o tanque será cheio?",
	"Fontes","Minutos","",TipoProporcao.IABCX,1,1500),
	new ProblemaRegraTres(
	"Se $a alunos limpam uma quadra em $b minutos, quantos minutos levariam $c alunos?",
	"Alunos","Minutos","",TipoProporcao.IABCX,1,180),
	new ProblemaRegraTres(
	"$a bombas esvaziam um poço em $b minutos. Se forem usadas $c bombas iguais, em quanto tempo o poço será esvaziado?",
	"Bombas","Minutos","",TipoProporcao.IABCX,1,180),
	new ProblemaRegraTres(
	"Se $a carpinteiros fazem um móvel em $b horas, em quantos horas $c carpinteiros fariam o mesmo móvel?",
	"Carpinteiros","Horas","",TipoProporcao.IABCX,1,120),
	new ProblemaRegraTres(
	"$a funcionários terminam um projeto em $b dias. Em quantos dias $c funcionários fariam o mesmo projeto?",
	"Funcionários","Dias","",TipoProporcao.IABCX,1,40),
	new ProblemaRegraTres(
	"Uma equipe de $a técnicos instala uma máquina em $b minutos. Quanto tempo levaria uma equipe de $c técnicos?",
	"Técnicos","Minutos","",TipoProporcao.IABCX,1,240),
	new ProblemaRegraTres(
	"$a máquinas enche 10000 garrafas em $b minutos. Em quanto tempo $c máquinas fariam o mesmo serviço?",
	"Máquinas","Minutos","",TipoProporcao.IABCX,1,100),
	new ProblemaRegraTres(
	"Uma horta com $a regadores fica molhada em $b minutos. Quanto tempo levaria com $c regadores?",
	"Regadores","Minutos","",TipoProporcao.IABCX,1,40),
	new ProblemaRegraTres(
	"Uma turma de $a amigos organizam uma festa em $b dias. Se o grupo tiver $c amigos, em quantos dias terminarão os preparativos?",
	"Amigos","Dias","",TipoProporcao.IABCX,1,40),
	new ProblemaRegraTres(
	"Um grupo de $a trabalhadores limpam um parque em $b horas. Se tivermos $c trabalhadores, quantos horas levarão?",
	"Trabalhadores","Horas","",TipoProporcao.IABCX,1,1200),
	new ProblemaRegraTres(
	"Um fazendeiro usa $a máquinas para colher toda a produção em $b horas. Se ele usar $c máquinas, quanto tempo levará?",
	"Máquinas","Horas","",TipoProporcao.IABCX,1,1200),
	new ProblemaRegraTres(
	"Uma fábrica usa $a máquinas para produzir 10.000 peças em $b minutos. Se tivermos $c máquinas funcionando, quanto tempo levarão?",
	"Máquinas","Minutos","",TipoProporcao.IABCX,1,600),
	new ProblemaRegraTres(
	"$a bombas d’água enchem um reservatório em $b minutos. Se forem ligadas $c bombas idênticas, quanto tempo levará?",
	"Bombas","Minutos","",TipoProporcao.IABCX,1,180),
	new ProblemaRegraTres(
	"Uma gráfica imprime 100.000 jornais com $a impressoras em $b minutos. Se usarem $c impressoras, quanto tempo levarão?",
	"Impressoras","Minutos","",TipoProporcao.IABCX,1,120),
	new ProblemaRegraTres(
	"Um grupo de $a jardineiros cortam a grama de um parque em $b dias. Se forem $c jardineiros, em quantos dias terminarão?",
	"Jardineiros","Dias","",TipoProporcao.IABCX,1,50),
	new ProblemaRegraTres(
	"Uma empresa entrega 1000 encomendas em $b dias com $a motoboys. Se houver $c motoboys, em quantos dias será feita a entrega?",
	"Motoboys","Dias","",TipoProporcao.IABCX,1,66),
	new ProblemaRegraTres(
	"Um grupo de $a voluntários organiza uma biblioteca em $b dias. Se houver $c voluntários, quanto tempo levarão?",
	"voluntários","Dias","",TipoProporcao.IABCX,1,60),
	new ProblemaRegraTres(
	"Uma equipe de $a pintores pinta um prédio em $b dias. Se houver $c pintores, em quantos dias terminarão o serviço?",
	"Pintores","Dias","",TipoProporcao.IABCX,1,900),
	new ProblemaRegraTres(
	"Uma equipe de construção ergue um muro em $b dias com $a operários. Se forem $c operários, quanto tempo levarão?",
	"Operários","Dias","",TipoProporcao.IABCX,1,60),
	new ProblemaRegraTres(
	"Uma lanchonete atende 20 clientes em $b minutos com $a atendentes. Se houver $c atendentes, quanto tempo levarão?",
	"Atendentes","Minutos","",TipoProporcao.IABCX,1,60),
	new ProblemaRegraTres(
	"Uma empresa finaliza um projeto em $b dias com $a funcionários. Se houver $c funcionários, em quantos dias o projeto será concluído?",
	"Funcionários","Dias","",TipoProporcao.IABCX,1,40),
	new ProblemaRegraTres(
	"$a torneiras gotejando enche um balde em $b minutos. Se $c torneiras idênticas estiverem gotejando, quanto tempo levará?",
	"Torneiras","Minutos","",TipoProporcao.IABCX,1,60),
	new ProblemaRegraTres(
	"Uma fábrica monta 100 móveis em $b horas com $a funcionários. Se $c funcionários trabalharem, quanto tempo levarão?",
	"Funcionários","Horas","",TipoProporcao.IABCX,1,400),
	new ProblemaRegraTres(
	"Uma equipe de resgate retira 300 toneladas de escombros em $b dias com $a socorristas. Se forem $c socorristas, em quantos dias terminarão?",
	"Socorristas","Dias","",TipoProporcao.IABCX,1,100),
	new ProblemaRegraTres(
	"Um restaurante prepara 1000 refeições em $b horas com $a cozinheiros. Se houver $c cozinheiros, quanto tempo levarão?",
	"Cozinheiros","Horas","",TipoProporcao.IABCX,1,50),
	new ProblemaRegraTres(
	"Uma equipe de filmagem grava um documentário em $b dias com $a pessoas. Se forem $c pessoas, em quantos dias terminarão?",
	"Pessoas","Dias","",TipoProporcao.IABCX,1,300),
	new ProblemaRegraTres(
	"Uma lavanderia lava 3000 kg de roupas em $b horas com $a máquinas. Se usarem $c máquinas, quanto tempo levarão?",
	"Máquinas","Horas","",TipoProporcao.IABCX,1,60),
	new ProblemaRegraTres(
	"Uma equipe de limpeza higieniza um shopping em $b horas com $a funcionários. Se forem $c funcionários, quanto tempo levarão?",
	"Funcionários","Horas","",TipoProporcao.IABCX,1,500),
	new ProblemaRegraTres(
	"Uma gráfica imprime 10.000 cartazes em $b horas com $a impressoras. Se $c impressoras forem usadas, quanto tempo levarão?",
	"Impressoras","Horas","",TipoProporcao.IABCX,1,100),
	new ProblemaRegraTres(
	"Uma construtora ergue um prédio em $b meses com $a operários. Se forem $c operários, em quantos meses será concluído?",
	"Operários","Meses","",TipoProporcao.IABCX,1,120),
	new ProblemaRegraTres(
	"Um grupo de $a mecânicos conserta 100 carros em $b horas. Se forem $c mecânicos, quanto tempo levarão?",
	"Mecânicos","Horas","",TipoProporcao.IABCX,1,300),
	new ProblemaRegraTres(
	"Uma linha de produção embala 100.000 chocolates em $b horas com $a máquinas. Se usarem $c máquinas, em quanto tempo embalarão?",
	"Máquinas","Horas","",TipoProporcao.IABCX,1,200),
	new ProblemaRegraTres(
	"Uma horta precisa de $b horas para ser irrigada com $a regadores. Se forem usados $c regadores, quantas horas levarão?",
	"Regadores","Horas","",TipoProporcao.IABCX,1,40),
	new ProblemaRegraTres(
	"Uma fábrica embala 1000 caixas de suco em $b horas com $a funcionários. Se $c funcionários trabalharem, quanto tempo levarão?",
	"Funcionários","Horas","",TipoProporcao.IABCX,1,50),
	new ProblemaRegraTres(
	"Uma escola organiza um evento esportivo em $b dias com $a professores. Se $c professores organizarem, em quantos dias terminarão?",
	"Professores","Dias","",TipoProporcao.IABCX,1,70),
	new ProblemaRegraTres(
	"$a caminhões transporta 1000 toneladas de carga em $b horas. Se forem usados $c caminhões idênticos, quanto tempo levarão?",
	"Caminhões","Horas","",TipoProporcao.IABCX,1,100),
	new ProblemaRegraTres(
	"Um time de engenheiros projeta um edifício em $b dias com $a profissionais. Se forem $c engenheiros, em quantos dias concluirão?",
	"Profissionais","Dias","",TipoProporcao.IABCX,1,40),
	new ProblemaRegraTres(
	"Uma gráfica encaderna 1.000 livros em $b horas com $a máquinas. Se utilizarem $c máquinas, quanto tempo levarão?",
	"Máquinas","Horas","",TipoProporcao.IABCX,1,100),
	new ProblemaRegraTres(
	"Um grupo de pedreiros reforma uma casa em $b dias com $a trabalhadores. Se forem $c trabalhadores, quanto tempo levarão?",
	"Trabalhadores","Dias","",TipoProporcao.IABCX,1,540),
	new ProblemaRegraTres(
	"Uma empresa atende 100 clientes em $b minutos com $a atendentes. Se houver $c atendentes, em quanto tempo atenderão?",
	"Atendentes","Minutos","",TipoProporcao.IABCX,1,300),
	new ProblemaRegraTres(
	"Um hotel arruma 100 quartos em $b horas com $a camareiras. Se houver $c camareiras, quanto tempo levarão?",
	"Camareiras","Horas","",TipoProporcao.IABCX,1,50),
	new ProblemaRegraTres(
	"Uma turma de $a alunos resolvem 10 exercícios em $b minutos. Se $c alunos resolverem, em quanto tempo terminarão?",
	"Alunos","Minutos","",TipoProporcao.IABCX,1,100),
	new ProblemaRegraTres(
	"Uma fazenda colhe 100 toneladas de milho em $b dias com $a trabalhadores. Se forem $c, quanto tempo levarão?",
	"Trabalhadores","Dias","",TipoProporcao.IABCX,1,200)
//	new ProblemaProporcao(
//	"",
//	"","","",TipoProporcao.IABCX),
	};
	
	public static ProblemaRegraTres getProblemaProporcao()
	{
		Random rand=new Random();
		int index=rand.nextInt(problemas.length);
		return problemas[index].clone();
	}
	
}
