package Matematica.Basico.RegraTres.Nivel1Package;

import java.util.Random;

import Matematica.Basico.RegraTres.ProblemaRegraTres;
import Matematica.Basico.RegraTres.TipoProporcao;

public class TextoRegraTresSimples
{
	static ProblemaRegraTres  problemas[]= {
	new ProblemaRegraTres(
	"Se $a livros custam R\\(\\$\\) $b,00, quanto custam $c livros no mesmo preço unitário?",
	"Livros","Preço","",TipoProporcao.ABCX,1,50),
	new ProblemaRegraTres(
	"Uma máquina imprime $a páginas em $b minuto. Quantas páginas ela imprimirá em $c minutos?",
	"Página","Minuto","",TipoProporcao.ABXC,1,60),
	new ProblemaRegraTres(
	"Em um mapa, $a cm representa $b km. Qual a distância real entre duas cidades, se elas estão a $c cm uma da outra no mapa?",
	"Cm","Km","",TipoProporcao.ABCX,1,1),
	new ProblemaRegraTres(
	"Se a água de uma piscina aumenta sua altura em $a cm a cada $b minutos, quanto tempo será necessário para que a altura da água aumente em $c cm?",
	"Cm","Minutos","",TipoProporcao.ABCX,1,10),
	new ProblemaRegraTres(
	"Se $a Kg de arroz custam R\\(\\$\\) $b,00, quanto custam $c kg de arroz?",
	"Kg","Preço","",TipoProporcao.ABCX,1,4),
	new ProblemaRegraTres(
	"Uma empresa tem $a funcionários para produzir $b unidades de um produto por dia. Quantos funcionários seriam necessários para produzir $c unidades por dia?",
	"Funcionários","Unidades","",TipoProporcao.ABXC,1,100),
	new ProblemaRegraTres(
	"Um trabalhador ganha R\\(\\$\\) $a,00 por $b horas. Quanto ele receberia se trabalhasse $c horas, mantendo a mesma taxa?",
	"Valor","Hora","",TipoProporcao.ABXC,50,1),
	new ProblemaRegraTres(
	"Se $a pães pesam $b gramas (g), qual é o peso de $c pães?",
	"Gramas","Peso","",TipoProporcao.ABCX,1,50),
	new ProblemaRegraTres(
	"Se $a kg de cimento são necessários para construir $b \\(m^2\\) de parede, quantos kg são necessários para $c \\(m^2\\)?",
	"Kg","\\(m^2\\)","",TipoProporcao.ABXC,20,1),
	new ProblemaRegraTres(
	"Se $a litros de tinta cobre $b \\(m^2\\), quantos litros serão necessários para pintar uma área de $c \\(m^2\\)?",
	"Litros","\\(m^2\\)","",TipoProporcao.ABXC,1,5),
	new ProblemaRegraTres(
	"Um ciclista percorre $a km em $b minutos. Quantos quilômetros ele percorre em $c minutos à mesma velocidade?",
	"Km","Minutos","",TipoProporcao.ABXC,1,1),
	new ProblemaRegraTres(
	"Um agricultor usa $a kg de fertilizante para $b hectares (ha) de terra. Quantos kg ele usará para $c hectares?",
	"Kg","Ha","",TipoProporcao.ABXC,300,1),
	new ProblemaRegraTres(
	"Em uma escola, $a alunos leem $b páginas por semana. Quantas páginas serão lidas por $c alunos em uma semana?",
	"Alunos","Páginas","",TipoProporcao.ABCX,1,50),
	new ProblemaRegraTres(
	"Se $a kg de açúcar custam R\\(\\$\\)$b,00, quanto custariam $c kg de açúcar?",
	"kg","Valor","",TipoProporcao.ABCX,1,1),
	new ProblemaRegraTres(
	"Uma receita para $a pessoas leva $b gramas (g), de arroz. Quanto de arroz é necessário para $c pessoas?",
	"Pessoas","Gramas","",TipoProporcao.ABCX,1,50),
	new ProblemaRegraTres(
	"Se $a metros de fio elétrico custam R\\(\\$\\)$b,00, quanto custa $c metros de fio elétrico?",
	"Metros","Valor","",TipoProporcao.ABCX,1,2),
	new ProblemaRegraTres(
	"Se $a pedreiros constroem $b \\(m^2\\) de parede por dia, quantos pedreiros serão necessários para construir $c \\(m^2\\) por dia?",
	"Pedreiros","\\(m^2\\)","",TipoProporcao.ABXC,1,10),
	new ProblemaRegraTres(
	"Se um pintor leva $a dias para pintar $b casas, quantos dias ele levará para pintar $c casas, mantendo o mesmo ritmo?",
	"Dias","Casas","",TipoProporcao.ABXC,15,1),
	new ProblemaRegraTres(
	"Se $a kg de arroz alimentam $b pessoas, quantos kg de arroz são necessários para alimentar $c pessoas?",
	"kg","Pessoas","",TipoProporcao.ABXC,1,5),
	new ProblemaRegraTres(
	"Se $a homens produzem $b peças por hora, quantos homens seriam necessários para produzir $c peças por hora?",
	"Homens","Peças","",TipoProporcao.ABXC,1,5),
	new ProblemaRegraTres(
	"Em uma fábrica, $a máquinas produzem $b peças por hora. Quantas peças serão produzidas por hora utilizando $c máquinas?",
	"Máquinas","Peças","",TipoProporcao.ABCX,1,100),
	new ProblemaRegraTres(
	"Se $a kg de bolo custam R\\(\\$\\)$b,00, qual é o preço de $c kg de bolo?",
	"Kg","Valor","",TipoProporcao.ABCX,1,15),
	new ProblemaRegraTres(
	"$a livros são lidos em $b dias. Quantos livros seriam lidos em $c dias, mantendo o mesmo ritmo?",
	"Livro","Dias","",TipoProporcao.ABXC,1,5),
	new ProblemaRegraTres(
	"Se $a kg de maçãs custam R\\(\\$\\)$b,00, qual seria o preço de $c kg de maçãs?",
	"Kg","Valor","",TipoProporcao.ABCX,1,5),
	new ProblemaRegraTres(
	"Em uma empresa, $a empregados produzem $b peças por dia. Quantos empregados são necessários para produzir $c peças por dia?",
	"Empregados","Peças","",TipoProporcao.ABXC,1,100),
	new ProblemaRegraTres(
	"Se $a litros de suco de laranja custam R\\(\\$\\)$b,00, qual seria o preço de $c litros de suco de laranja?",
	"Litros","Valor","",TipoProporcao.ABCX,1,5),
	new ProblemaRegraTres(
	"Se $a metros de fita decorativa são suficientes para decorar $b árvores, quantos metros seriam necessários para decorar $c árvores?",
	"Metros","Árvores","",TipoProporcao.ABXC,30,1),
	new ProblemaRegraTres(
	"Se $a trabalhadores produzem $b peças por hora, quantos trabalhadores são necessários para produzir $c peças em uma hora?",
	"Trabalhadores","Peças","",TipoProporcao.ABXC,1,50),
	new ProblemaRegraTres(
	"Em uma plantação, $a árvores produzem $b maçãs por ano. Quantas maçãs seriam produzidas por $c árvores ao ano?",
	"Árvores","Maçãs","",TipoProporcao.ABCX,1,300),
	new ProblemaRegraTres(
	"Se $a lápis custam R\\(\\$\\)$b,00, quanto custariam $c lápis?",
	"Lápis","Valor","",TipoProporcao.ABCX,1,1),
	new ProblemaRegraTres(
	"Se uma máquina imprime $a páginas em $b minutos, quantas páginas serão impressas em $c minutos de trabalho contínuo?",
	"Páginas","Minutos","",TipoProporcao.ABXC,60,1),
	new ProblemaRegraTres(
	"Se um operário produz $a peças em $b horas, quantas peças ele produzirá em $c horas?",
	"Peças","Horas","",TipoProporcao.ABXC,100,1),
	new ProblemaRegraTres(
	"Se $a metros de papel são necessários para embalar $b caixas, quantos metros de papel serão necessários para embalar $c caixas?",
	"Metros","Caixas","",TipoProporcao.ABXC,1,1),
	new ProblemaRegraTres(
	"Se $a pessoas consomem $b litros de água por dia, quantos litros serão consumidos por $c pessoas no mesmo dia?",
	"Pessoas","Litros","",TipoProporcao.ABCX,1,1),
	new ProblemaRegraTres(
	"Se um carro percorre $a km com $b litros de combustível, quantos quilômetros esse carro percorreria com $c litros?",
	"Km","Litros","",TipoProporcao.ABXC,30,1),
	new ProblemaRegraTres(
	"Se $a biscoitos custam R\\(\\$\\)$b,00, quanto custariam $c biscoitos?",
	"Biscoitos","Valor","",TipoProporcao.ABCX,1,1),
	new ProblemaRegraTres(
	"Em uma fábrica, $a máquinas produzem $b peças por hora. Quantas peças serão produzidas por $c máquinas em 1 hora?",
	"Máquinas","Peças","",TipoProporcao.ABCX,1,100),
	new ProblemaRegraTres(
	"Se $a horas de trabalho de um mecânico custam R\\(\\$\\)$b,00, quanto custariam $c horas de trabalho?",
	"Horas","Valor","",TipoProporcao.ABCX,1,30),
	new ProblemaRegraTres(
	"Se $a kg, de feijão são suficientes para $b refeições, quantas kg de feijão seriam necessários para $c refeições?",
	"Kg","Refeições","",TipoProporcao.ABXC,1,5),
	new ProblemaRegraTres(
	"Se uma caminhonete transporta $a toneladas de carga em $b viagens, quantas toneladas ela transportaria em $c viagens?",
	"Toneladas","Viagens","",TipoProporcao.ABCX,1,3),
	new ProblemaRegraTres(
	"Se $a metros de tecido são suficientes para fazer $b cortinas, quantos metros de tecido são necessários para fazer $c cortinas?",
	"Metros","Cortinas","",TipoProporcao.ABXC,10,1),
//	new ProblemaProporcao(
//	"",
//	"","","",TipoProporcao.ABCX),
	};
	
	public static ProblemaRegraTres getProblemaProporcao()
	{
		Random rand=new Random();
		int index=rand.nextInt(problemas.length);
		return problemas[index].clone();
	}
	
}
