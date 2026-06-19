package matematica.basico.subtracaonatural;

import java.util.Random;

// Biblioteca de enunciados de PROVA REAL da subtração: alguém calculou a - b = c e
// confere somando c + b (deve recuperar o minuendo a). Placeholders: $a, $b, $c, $nomeM/$nomeF.
public class TextoProvaReal
{
	static ProblemaProvaReal problemas[] = {

	new ProblemaProvaReal("$nomeM calculou que \\($a\\) - \\($b\\) = \\($c\\). Para conferir pela prova real, somou \\($c\\) + \\($b\\). Qual deve ser o resultado?"),
	new ProblemaProvaReal("$nomeF obteve \\($a\\) - \\($b\\) = \\($c\\) e, para verificar, somou \\($c\\) + \\($b\\). Qual deve ser o resultado?"),
	new ProblemaProvaReal("Um caixa registrou que \\($a\\) - \\($b\\) = \\($c\\). Para conferir, somou \\($c\\) + \\($b\\). Qual deve ser o resultado para o cálculo estar correto?"),
	new ProblemaProvaReal("O contador encontrou \\($a\\) - \\($b\\) = \\($c\\) e fez a prova real somando \\($c\\) + \\($b\\). Que valor deve obter?"),
	new ProblemaProvaReal("$nomeM anotou \\($a\\) - \\($b\\) = \\($c\\). Conferindo com a soma \\($c\\) + \\($b\\), qual deve ser o resultado?"),
	new ProblemaProvaReal("Uma aluna chegou a \\($a\\) - \\($b\\) = \\($c\\). Para confirmar, somou \\($c\\) + \\($b\\). Qual deve ser o resultado?"),
	new ProblemaProvaReal("$nomeF resolveu \\($a\\) - \\($b\\) = \\($c\\) e verificou pela prova real com \\($c\\) + \\($b\\). Qual deve ser o resultado?"),
	new ProblemaProvaReal("O vendedor calculou \\($a\\) - \\($b\\) = \\($c\\) e conferiu somando \\($c\\) + \\($b\\). Que resultado deve aparecer?"),
	new ProblemaProvaReal("$nomeM obteve \\($a\\) - \\($b\\) = \\($c\\). Para ter certeza, somou \\($c\\) + \\($b\\). Qual deve ser o resultado?"),
	new ProblemaProvaReal("A professora corrigiu \\($a\\) - \\($b\\) = \\($c\\) e usou a prova real \\($c\\) + \\($b\\). Qual deve ser o resultado?"),
	new ProblemaProvaReal("$nomeF fez a conta \\($a\\) - \\($b\\) = \\($c\\) e conferiu com \\($c\\) + \\($b\\). Que valor deve encontrar?"),
	new ProblemaProvaReal("Um aluno escreveu \\($a\\) - \\($b\\) = \\($c\\). Para checar, somou \\($c\\) + \\($b\\). Qual deve ser o resultado?"),
	new ProblemaProvaReal("$nomeM achou \\($a\\) - \\($b\\) = \\($c\\) e, na prova real, somou \\($c\\) + \\($b\\). Qual deve ser o resultado?"),
	new ProblemaProvaReal("A tesoureira registrou \\($a\\) - \\($b\\) = \\($c\\) e conferiu somando \\($c\\) + \\($b\\). Que valor deve obter?"),
	new ProblemaProvaReal("$nomeF calculou que \\($a\\) - \\($b\\) = \\($c\\). Somando \\($c\\) + \\($b\\) para verificar, qual deve ser o resultado?"),
	new ProblemaProvaReal("Um estudante obteve \\($a\\) - \\($b\\) = \\($c\\) e fez a prova real \\($c\\) + \\($b\\). Qual deve ser o resultado?"),
	new ProblemaProvaReal("$nomeM encontrou \\($a\\) - \\($b\\) = \\($c\\) e conferiu com a soma \\($c\\) + \\($b\\). Que resultado deve dar?"),
	new ProblemaProvaReal("O caixa do mercado anotou \\($a\\) - \\($b\\) = \\($c\\). Para conferir, somou \\($c\\) + \\($b\\). Qual deve ser o resultado?"),
	new ProblemaProvaReal("$nomeF terminou a conta \\($a\\) - \\($b\\) = \\($c\\) e verificou com \\($c\\) + \\($b\\). Qual deve ser o resultado?"),
	new ProblemaProvaReal("Um operador de caixa calculou \\($a\\) - \\($b\\) = \\($c\\) e conferiu somando \\($c\\) + \\($b\\). Que valor deve aparecer?"),
	new ProblemaProvaReal("$nomeM registrou \\($a\\) - \\($b\\) = \\($c\\) e usou a prova real \\($c\\) + \\($b\\). Qual deve ser o resultado?"),
	new ProblemaProvaReal("A aluna obteve \\($a\\) - \\($b\\) = \\($c\\) e somou \\($c\\) + \\($b\\) para conferir. Qual deve ser o resultado?"),
	new ProblemaProvaReal("$nomeF chegou a \\($a\\) - \\($b\\) = \\($c\\). Para checar, fez \\($c\\) + \\($b\\). Que resultado deve obter?"),
	new ProblemaProvaReal("Um bancário calculou \\($a\\) - \\($b\\) = \\($c\\) e conferiu com \\($c\\) + \\($b\\). Qual deve ser o resultado?"),
	new ProblemaProvaReal("$nomeM fez \\($a\\) - \\($b\\) = \\($c\\) e, para confirmar, somou \\($c\\) + \\($b\\). Qual deve ser o resultado?"),
	new ProblemaProvaReal("A contadora encontrou \\($a\\) - \\($b\\) = \\($c\\) e verificou pela prova real \\($c\\) + \\($b\\). Que valor deve dar?"),
	new ProblemaProvaReal("$nomeF anotou \\($a\\) - \\($b\\) = \\($c\\). Conferindo com \\($c\\) + \\($b\\), qual deve ser o resultado?"),
	new ProblemaProvaReal("Um aluno calculou \\($a\\) - \\($b\\) = \\($c\\) e somou \\($c\\) + \\($b\\) para verificar. Qual deve ser o resultado?"),
	new ProblemaProvaReal("$nomeM obteve \\($a\\) - \\($b\\) = \\($c\\) e conferiu o resultado com \\($c\\) + \\($b\\). Que valor deve encontrar?"),
	new ProblemaProvaReal("A vendedora registrou \\($a\\) - \\($b\\) = \\($c\\). Para conferir, somou \\($c\\) + \\($b\\). Qual deve ser o resultado?"),
	new ProblemaProvaReal("$nomeF resolveu \\($a\\) - \\($b\\) = \\($c\\) e fez a prova real somando \\($c\\) + \\($b\\). Qual deve ser o resultado?"),
	new ProblemaProvaReal("Um professor obteve \\($a\\) - \\($b\\) = \\($c\\) e conferiu com \\($c\\) + \\($b\\). Qual deve ser o resultado?"),
	new ProblemaProvaReal("$nomeM achou \\($a\\) - \\($b\\) = \\($c\\). Para checar a conta, somou \\($c\\) + \\($b\\). Que valor deve dar?"),
	new ProblemaProvaReal("Uma caixa de loja calculou \\($a\\) - \\($b\\) = \\($c\\) e conferiu somando \\($c\\) + \\($b\\). Qual deve ser o resultado?"),
	new ProblemaProvaReal("$nomeF encontrou \\($a\\) - \\($b\\) = \\($c\\) e verificou com \\($c\\) + \\($b\\). Qual deve ser o resultado?"),
	new ProblemaProvaReal("Um aluno do quinto ano fez \\($a\\) - \\($b\\) = \\($c\\) e conferiu com \\($c\\) + \\($b\\). Que resultado deve obter?"),
	new ProblemaProvaReal("$nomeM calculou \\($a\\) - \\($b\\) = \\($c\\) e somou \\($c\\) + \\($b\\) na prova real. Qual deve ser o resultado?"),
	new ProblemaProvaReal("A tesoureira do clube obteve \\($a\\) - \\($b\\) = \\($c\\) e conferiu com \\($c\\) + \\($b\\). Qual deve ser o resultado?"),
	new ProblemaProvaReal("$nomeF registrou \\($a\\) - \\($b\\) = \\($c\\). Para verificar, fez \\($c\\) + \\($b\\). Qual deve ser o resultado?"),
	new ProblemaProvaReal("Um comerciante calculou \\($a\\) - \\($b\\) = \\($c\\) e conferiu somando \\($c\\) + \\($b\\). Que valor deve aparecer?"),
	new ProblemaProvaReal("$nomeM obteve \\($a\\) - \\($b\\) = \\($c\\). Conferindo pela prova real \\($c\\) + \\($b\\), qual deve ser o resultado?"),
	new ProblemaProvaReal("A aluna anotou \\($a\\) - \\($b\\) = \\($c\\) e somou \\($c\\) + \\($b\\) para conferir. Qual deve ser o resultado?"),
	new ProblemaProvaReal("$nomeF fez a subtração \\($a\\) - \\($b\\) = \\($c\\) e verificou com \\($c\\) + \\($b\\). Que valor deve dar?"),
	new ProblemaProvaReal("Um caixa eletrônico calculou \\($a\\) - \\($b\\) = \\($c\\). Para conferir, somou \\($c\\) + \\($b\\). Qual deve ser o resultado?"),
	new ProblemaProvaReal("$nomeM resolveu \\($a\\) - \\($b\\) = \\($c\\) e conferiu o valor com \\($c\\) + \\($b\\). Qual deve ser o resultado?"),
	new ProblemaProvaReal("O monitor da turma obteve \\($a\\) - \\($b\\) = \\($c\\) e fez a prova real \\($c\\) + \\($b\\). Qual deve ser o resultado?"),
	new ProblemaProvaReal("$nomeF calculou \\($a\\) - \\($b\\) = \\($c\\). Para ter certeza, somou \\($c\\) + \\($b\\). Que resultado deve obter?"),
	new ProblemaProvaReal("Um estudante encontrou \\($a\\) - \\($b\\) = \\($c\\) e conferiu com \\($c\\) + \\($b\\). Qual deve ser o resultado?"),
	new ProblemaProvaReal("$nomeM anotou \\($a\\) - \\($b\\) = \\($c\\) e verificou pela prova real \\($c\\) + \\($b\\). Qual deve ser o resultado?"),
	new ProblemaProvaReal("A funcionária do banco obteve \\($a\\) - \\($b\\) = \\($c\\) e conferiu com \\($c\\) + \\($b\\). Que valor deve dar?"),
	new ProblemaProvaReal("$nomeF achou \\($a\\) - \\($b\\) = \\($c\\). Conferindo com \\($c\\) + \\($b\\), qual deve ser o resultado?"),
	new ProblemaProvaReal("Um aluno calculou \\($a\\) - \\($b\\) = \\($c\\) e, para checar, somou \\($c\\) + \\($b\\). Qual deve ser o resultado?"),
	new ProblemaProvaReal("$nomeM obteve \\($a\\) - \\($b\\) = \\($c\\) e usou a soma \\($c\\) + \\($b\\) como prova real. Qual deve ser o resultado?"),
	new ProblemaProvaReal("A professora de matemática fez \\($a\\) - \\($b\\) = \\($c\\) e conferiu com \\($c\\) + \\($b\\). Qual deve ser o resultado?"),
	new ProblemaProvaReal("$nomeF registrou \\($a\\) - \\($b\\) = \\($c\\) e somou \\($c\\) + \\($b\\) para verificar. Que valor deve obter?"),
	new ProblemaProvaReal("Um caixa de supermercado calculou \\($a\\) - \\($b\\) = \\($c\\) e conferiu com \\($c\\) + \\($b\\). Qual deve ser o resultado?"),
	new ProblemaProvaReal("$nomeM encontrou \\($a\\) - \\($b\\) = \\($c\\). Para conferir, somou \\($c\\) + \\($b\\). Qual deve ser o resultado?"),
	new ProblemaProvaReal("A aluna obteve \\($a\\) - \\($b\\) = \\($c\\) e verificou pela prova real \\($c\\) + \\($b\\). Qual deve ser o resultado?"),
	new ProblemaProvaReal("$nomeF calculou \\($a\\) - \\($b\\) = \\($c\\) e conferiu somando \\($c\\) + \\($b\\). Qual deve ser o resultado?"),
	new ProblemaProvaReal("Um contador fez \\($a\\) - \\($b\\) = \\($c\\). Para checar, somou \\($c\\) + \\($b\\). Que valor deve dar?"),
	new ProblemaProvaReal("$nomeM obteve \\($a\\) - \\($b\\) = \\($c\\) e conferiu com \\($c\\) + \\($b\\). Qual deve ser o resultado?"),
	new ProblemaProvaReal("A monitora obteve \\($a\\) - \\($b\\) = \\($c\\) e fez a prova real \\($c\\) + \\($b\\). Qual deve ser o resultado?"),
	new ProblemaProvaReal("$nomeF anotou \\($a\\) - \\($b\\) = \\($c\\). Verificando com \\($c\\) + \\($b\\), qual deve ser o resultado?"),
	new ProblemaProvaReal("Um aluno registrou \\($a\\) - \\($b\\) = \\($c\\) e somou \\($c\\) + \\($b\\) para conferir. Qual deve ser o resultado?"),
	new ProblemaProvaReal("$nomeM resolveu \\($a\\) - \\($b\\) = \\($c\\) e conferiu pela prova real \\($c\\) + \\($b\\). Que valor deve obter?"),
	new ProblemaProvaReal("A vendedora encontrou \\($a\\) - \\($b\\) = \\($c\\) e conferiu com \\($c\\) + \\($b\\). Qual deve ser o resultado?"),
	new ProblemaProvaReal("$nomeF fez \\($a\\) - \\($b\\) = \\($c\\) e, para confirmar, somou \\($c\\) + \\($b\\). Qual deve ser o resultado?"),
	new ProblemaProvaReal("Um caixa registrou \\($a\\) - \\($b\\) = \\($c\\) e conferiu com a soma \\($c\\) + \\($b\\). Qual deve ser o resultado?"),
	new ProblemaProvaReal("$nomeM calculou \\($a\\) - \\($b\\) = \\($c\\). Conferindo com \\($c\\) + \\($b\\), que valor deve obter?"),
	new ProblemaProvaReal("A estudante obteve \\($a\\) - \\($b\\) = \\($c\\) e verificou com \\($c\\) + \\($b\\). Qual deve ser o resultado?"),
	new ProblemaProvaReal("$nomeF achou \\($a\\) - \\($b\\) = \\($c\\) e fez a prova real somando \\($c\\) + \\($b\\). Qual deve ser o resultado?"),
	new ProblemaProvaReal("Um professor registrou \\($a\\) - \\($b\\) = \\($c\\) e conferiu com \\($c\\) + \\($b\\). Que resultado deve dar?"),
	new ProblemaProvaReal("$nomeM obteve \\($a\\) - \\($b\\) = \\($c\\) e somou \\($c\\) + \\($b\\) para verificar. Qual deve ser o resultado?"),
	new ProblemaProvaReal("A caixa do cinema calculou \\($a\\) - \\($b\\) = \\($c\\) e conferiu com \\($c\\) + \\($b\\). Qual deve ser o resultado?"),
	new ProblemaProvaReal("$nomeF encontrou \\($a\\) - \\($b\\) = \\($c\\). Para conferir, somou \\($c\\) + \\($b\\). Que valor deve obter?"),
	new ProblemaProvaReal("Um aluno obteve \\($a\\) - \\($b\\) = \\($c\\) e usou \\($c\\) + \\($b\\) como prova real. Qual deve ser o resultado?"),
	new ProblemaProvaReal("$nomeM anotou \\($a\\) - \\($b\\) = \\($c\\) e conferiu com \\($c\\) + \\($b\\). Qual deve ser o resultado?"),
	new ProblemaProvaReal("A tesoureira calculou \\($a\\) - \\($b\\) = \\($c\\) e verificou somando \\($c\\) + \\($b\\). Qual deve ser o resultado?"),
	new ProblemaProvaReal("$nomeF registrou \\($a\\) - \\($b\\) = \\($c\\) e conferiu pela prova real \\($c\\) + \\($b\\). Que valor deve dar?"),
	new ProblemaProvaReal("Um comerciante obteve \\($a\\) - \\($b\\) = \\($c\\) e conferiu com \\($c\\) + \\($b\\). Qual deve ser o resultado?"),

	};

	public static ProblemaProvaReal getProblema()
	{
		Random rand = new Random();
		return problemas[rand.nextInt(problemas.length)].clone();
	}
}
