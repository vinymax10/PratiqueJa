package Matematica.Basico.RegraTres.Nivel3Package;

import java.util.Random;

import Matematica.Basico.RegraTres.ProblemaRegraTres;
import Matematica.Basico.RegraTres.TipoProporcao;

public class TextoRegraTresComposta
{
	static ProblemaRegraTres  problemas[]= {
	new ProblemaRegraTres(
	"Se $a máquinas produzem $b peças em $c minutos, em quantos minutos $d máquinas produzem $e peças?",
	"Máquinas","Peças","Minutos",TipoProporcao.IABCDEX,1,100,60),
	new ProblemaRegraTres(
	"Se $a pessoas colhem $b kg de maçãs em $c minutos, quantos kg serão colhidos por $d pessoas em $e minutos?",
	"Pessoas","Kg","Minutos",TipoProporcao.IABCDXE,1,100,60),
	new ProblemaRegraTres(
	"Se $a trabalhadores produzem $b peças em $c minutos, quantos trabalhadores são necessários para produzirem $d peças em $e minutos?",
	"Trabalhadores","Peças","Minutos",TipoProporcao.IABCXDE,1,100,60),
	new ProblemaRegraTres(
	"Se $a operários constroem $b metros de muro em $c dias, em quantos dias $d operários constroem $e metros de muro?",
	"Operários","Metros","Dias",TipoProporcao.IABCDEX,1,300,60),
	new ProblemaRegraTres(
	"Se $a trabalhadores constroem $b metros de estrada em $c dias, quantos metros $d trabalhadores conseguem construir em $e dias?",
	"Trabalhadores","Metros","Dias",TipoProporcao.IABCDXE,1,100,60),
	new ProblemaRegraTres(
	"Se $a pintores pintam $b \\(m^2\\) em $c horas, quantos pintores são necessários para pintar $d \\(m^2\\) em $e horas?",
	"Pintores","\\(m^2\\)","Horas",TipoProporcao.IABCXDE,1,1000,60),
	new ProblemaRegraTres(
	"Se $a trabalhadores constroem $b tijolos em $c horas, em quanto tempo $d trabalhadores constroem $e tijolos?",
	"Trabalhadores","Tijolos","Horas",TipoProporcao.IABCDEX,1,2000,50),
	new ProblemaRegraTres(
	"Se $a trabalhadores constroem $b \\(m^2\\) de parede em $c dias, quantos \\(m^2\\) de parede $d trabalhadores constroem em $e dias?",
	"Trabalhadores","\\(m^2\\)","Dias",TipoProporcao.IABCDXE,1,1500,100),
	new ProblemaRegraTres(
	"Se $a carros consomem $b litros de combustível em $c horas, quantos carros são necessários para consumir $d litros em $e horas?",
	"Carros","Litros","Horas",TipoProporcao.IABCXDE,1,1000,100),
	new ProblemaRegraTres(
	"Se $a cozinheiros preparam $b pratos em $c horas, em quanto tempo $d cozinheiros preparam $e pratos?",
	"Cozinheiros","Pratos","Horas",TipoProporcao.IABCDEX,1,1000,100),
	new ProblemaRegraTres(
	"Se $a motoristas percorrem $b km em $c horas, quantos quilômetros serão percorridos por $d motoristas em $e horas?",
	"Motoristas","Km","Horas",TipoProporcao.IABCDXE,1,10000,100),
	new ProblemaRegraTres(
	"Se $a eletricistas instalam $b luminárias em $c horas, quantos eletricistas são necessários para instalar $d luminárias em $e horas?",
	"Eletricistas","Luminárias","Horas",TipoProporcao.IABCXDE,1,1000,100),
	new ProblemaRegraTres(
	"Se $a trabalhadores colhem $b kg de uvas em $c horas, em quantas horas $d trabalhadores colhem $e kg de uvas?",
	"Trabalhadores","Kg","Horas",TipoProporcao.IABCDEX,1,1000,60),
	new ProblemaRegraTres(
	"Se $a agricultores colhem $b kg de maçãs em $c horas, quantos agricultores são necessários para colher $d kg de maçãs em $e horas?",
	"Agricultores","Kg","Horas",TipoProporcao.IABCXDE,1,1000,60),
	new ProblemaRegraTres(
	"Se $a jardineiros plantam $b árvores em $c horas, em quanto tempo $d jardineiros plantem $e árvores?",
	"Jardineiros","Árvores","Horas",TipoProporcao.IABCDEX,1,1000,100),
	new ProblemaRegraTres(
	"Se $a mecânicos consertam $b carros em $c horas, quantos carros serão consertados por $d mecânicos em $e horas?",
	"Mecânicos","Carros","Horas",TipoProporcao.IABCDXE,1,100,200),
	new ProblemaRegraTres(
	"Se $a funcionários empacotam $b caixas em $c horas, quantos funcionários são necessários para empacotar $d caixas em $e horas?",
	"Funcionários","Caixas","Horas",TipoProporcao.IABCXDE,1,1000,80),
	new ProblemaRegraTres(
	"Se $a costureiras fazem $b roupas em $c dias, em quanto tempo $d costureiras fazem $e roupas?",
	"Costureiras","Roupas","Dias",TipoProporcao.IABCDEX,1,1000,200),
	new ProblemaRegraTres(
	"Se $a agricultores plantam $b hectares em $c meses, quantos hectares serão plantados por $d agricultores em $e meses?",
	"Agricultores","Hectares","Meses",TipoProporcao.IABCDXE,1,1000,30),
	new ProblemaRegraTres(
	"Se $a engenheiros fazem $b projetos em $c meses, quantos engenheiros são necessários para fazer $d projetos em $e meses?",
	"Engenheiros","Projetos","Meses",TipoProporcao.IABCXDE,1,1000,300),
	new ProblemaRegraTres(
	"Se $a soldadores soldam $b peças em $c horas, em quanto tempo $d soldadores soldam $e peças?",
	"Soldadores","Peças","Horas",TipoProporcao.IABCDEX,1,1000,150),
	new ProblemaRegraTres(
	"Se $a artistas pintam $b quadros em $c dias, quantos quadros serão pintados por $d artistas em $e dias?",
	"Artistas","Quadros","Dias",TipoProporcao.IABCDXE,1,100,80),
	new ProblemaRegraTres(
	"Se $a assistentes digitalizam $b documentos em $c horas, quantos assistentes são necessários para digitalizar $d documentos em $e horas?",
	"Assistentes","Documentos","Horas",TipoProporcao.IABCXDE,1,2000,80),
	new ProblemaRegraTres(
	"Se $a faxineiros limpam $b salas em $c horas, em quanto tempo $d faxineiros limpam $e salas?",
	"Faxineiros","Salas","Horas",TipoProporcao.IABCDEX,1,1000,150),
	new ProblemaRegraTres(
	"Se $a carpinteiros fabricam $b móveis em $c dias, quantos móveis serão fabricados por $d carpinteiros em $e dias?",
	"Carpinteiros","Móveis","Dias",TipoProporcao.IABCDXE,1,500,150),
	new ProblemaRegraTres(
	"Se $a pesquisadores conduzem $b experimentos em $c meses, quantos pesquisadores são necessários para conduzir $d experimentos em $e meses?",
	"Pesquisadores","Experimentos","Meses",TipoProporcao.IABCXDE,1,1000,120),
	new ProblemaRegraTres(
	"Se $a vendedores vendem $b produtos em $c horas, quantos produtos serão vendidos por $d vendedores em $e horas?",
	"Vendedores","Produtos","Horas",TipoProporcao.IABCDXE,1,1000,80),
	new ProblemaRegraTres(
	"Se $a pesquisadores escrevem $b artigos em $c meses, quantos pesquisadores são necessários para escrever $d artigos em $e meses?",
	"Pesquisadores","Artigos","Meses",TipoProporcao.IABCXDE,1,50,150),
	new ProblemaRegraTres(
	"Se $a médicos atendem $b consultas em $c horas, em quanto tempo $d médicos atendem $e consultas?",
	"Médicos","Consultas","Horas",TipoProporcao.IABCDEX,1,200,60),
	new ProblemaRegraTres(
	"Se $a estudantes fazem $b tarefas em $c horas, quantas tarefas serão feitas por $d estudantes em $e horas?",
	"Estudantes","Tarefas","Horas",TipoProporcao.IABCDXE,1,200,60),
	new ProblemaRegraTres(
	"Se $a cientistas analisam $b amostras em $c dias, em quantos dias $d cientistas analisam $e amostras?",
	"Cientistas","Amostras","Dias",TipoProporcao.IABCDEX,1,1000,100),
	new ProblemaRegraTres(
	"Se $a fotógrafos tiram $b fotos em $c minutos, quantas fotos serão tiradas por $d fotógrafos em $e minutos?",
	"Fotógrafos","Fotos","Minutos",TipoProporcao.IABCDXE,1,250,60),
	new ProblemaRegraTres(
	"Se $a pilotos fazem $b voos em $c meses, quantos pilotos são necessários para fazer $d voos em $e meses?",
	"Pilotos","Voos","Meses",TipoProporcao.IABCXDE,1,2000,60),
	new ProblemaRegraTres(
	"Se $a limpadores limpam $b vidros em $c horas, em quantas horas $d limpadores limpam $e vidros?",
	"Limpadores","Vidros","Horas",TipoProporcao.IABCDEX,1,1000,100),
	new ProblemaRegraTres(
	"Se $a bancários processam $b empréstimos em $c dias, em quantas dias $d bancários processam $e empréstimos?",
	"Bancários","Empréstimos","Dias",TipoProporcao.IABCDEX,1,400,60),
	new ProblemaRegraTres(
	"Se $a cabeleireiros fazem $b cortes de cabelo em $c horas, em quantas horas $d cabeleireiros fazem $e cortes de cabelo?",
	"Cabeleireiros","Cortes","Horas",TipoProporcao.IABCDEX,1,100,50),
	new ProblemaRegraTres(
	"Se $a ladrilhadores colocam $b \\(m^2\\) de cerâmica em $c horas, em quantas horas $d ladrilhadores colocam $e \\(m^2\\) de cerâmica?",
	"Ladrilhadores","\\(m^2\\)","Horas",TipoProporcao.IABCDEX,1,200,60),
	new ProblemaRegraTres(
	"Se $a farmacêuticos preparam $b medicamentos em $c horas, quantos medicamentos serão preparados por $d farmacêuticos em $e horas?",
	"Farmacêuticos","Medicamentos","Horas",TipoProporcao.IABCDXE,1,2000,80),
	new ProblemaRegraTres(
	"Se $a técnicos fazem manutenção em $b equipamentos em $c horas, em quantas horas $d técnicos fazem manutenção em $e equipamentos?",
	"Técnicos","Equipamentos","Horas",TipoProporcao.IABCDEX,1,100,100),
	new ProblemaRegraTres(
	"Se $a bibliotecários catalogam $b livros em $c dias, quantos livros serão catalogados por $d bibliotecários em $e dias?",
	"Bibliotecários","Livros","Dias",TipoProporcao.IABCDXE,1,300,60),
	};
	
	public static ProblemaRegraTres getProblemaProporcao()
	{
		Random rand=new Random();
		int index=rand.nextInt(problemas.length);
		return problemas[index].clone();
	}
	
}
