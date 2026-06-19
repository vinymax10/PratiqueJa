package matematica.basico.divisaonatural;

import java.util.Random;

// Biblioteca de enunciados de PROVA REAL da divisão: alguém calculou a / b = q e confere
// multiplicando o quociente pelo divisor (b x q deve recuperar o dividendo a).
// Placeholders: $a, $b, $q, $nomeM/$nomeF.
public class TextoProvaReal
{
	static ProblemaProvaReal problemas[] = {

	new ProblemaProvaReal("$nomeF calculou \\($a \\div $b = $q\\). Para verificar, ela multiplicou o quociente pelo divisor. Qual deve ser o resultado?"),
	new ProblemaProvaReal("$nomeM obteve \\($a \\div $b = $q\\) e fez a prova real multiplicando \\($b \\times $q\\). Qual deve ser o resultado?"),
	new ProblemaProvaReal("Um aluno achou \\($a \\div $b = $q\\). Conferindo com \\($b \\times $q\\), qual deve ser o resultado?"),
	new ProblemaProvaReal("A professora corrigiu \\($a \\div $b = $q\\) usando a prova real \\($b \\times $q\\). Que valor deve obter?"),
	new ProblemaProvaReal("$nomeF resolveu \\($a \\div $b = $q\\). Para conferir, multiplicou divisor por quociente. Qual deve ser o resultado?"),
	new ProblemaProvaReal("Um caixa registrou \\($a \\div $b = $q\\). Para checar, calculou \\($b \\times $q\\). Qual deve ser o resultado?"),
	new ProblemaProvaReal("$nomeM encontrou \\($a \\div $b = $q\\) e verificou com \\($b \\times $q\\). Que valor deve dar?"),
	new ProblemaProvaReal("Uma estudante obteve \\($a \\div $b = $q\\). Multiplicando \\($b \\times $q\\) para conferir, qual deve ser o resultado?"),
	new ProblemaProvaReal("$nomeF fez a divisão \\($a \\div $b = $q\\) e conferiu pela prova real. Qual deve ser o resultado de \\($b \\times $q\\)?"),
	new ProblemaProvaReal("Um contador calculou \\($a \\div $b = $q\\) e verificou multiplicando o quociente pelo divisor. Que valor deve obter?"),
	new ProblemaProvaReal("$nomeM anotou \\($a \\div $b = $q\\). Para ter certeza, fez \\($b \\times $q\\). Qual deve ser o resultado?"),
	new ProblemaProvaReal("A monitora obteve \\($a \\div $b = $q\\) e conferiu com \\($b \\times $q\\). Qual deve ser o resultado?"),
	new ProblemaProvaReal("$nomeF achou \\($a \\div $b = $q\\). Conferindo pela prova real \\($b \\times $q\\), que valor deve obter?"),
	new ProblemaProvaReal("Um aluno do quinto ano fez \\($a \\div $b = $q\\) e verificou com \\($b \\times $q\\). Qual deve ser o resultado?"),
	new ProblemaProvaReal("$nomeM calculou \\($a \\div $b = $q\\) e multiplicou \\($b \\times $q\\) na prova real. Qual deve ser o resultado?"),
	new ProblemaProvaReal("A tesoureira obteve \\($a \\div $b = $q\\) e conferiu com \\($b \\times $q\\). Qual deve ser o resultado?"),
	new ProblemaProvaReal("$nomeF registrou \\($a \\div $b = $q\\). Para verificar, fez \\($b \\times $q\\). Qual deve ser o resultado?"),
	new ProblemaProvaReal("Um comerciante calculou \\($a \\div $b = $q\\) e conferiu multiplicando divisor por quociente. Que valor deve aparecer?"),
	new ProblemaProvaReal("$nomeM obteve \\($a \\div $b = $q\\). Conferindo pela prova real \\($b \\times $q\\), qual deve ser o resultado?"),
	new ProblemaProvaReal("A aluna anotou \\($a \\div $b = $q\\) e multiplicou \\($b \\times $q\\) para conferir. Qual deve ser o resultado?"),
	new ProblemaProvaReal("$nomeF fez \\($a \\div $b = $q\\) e verificou com \\($b \\times $q\\). Que valor deve dar?"),
	new ProblemaProvaReal("Um professor obteve \\($a \\div $b = $q\\) e conferiu com \\($b \\times $q\\). Qual deve ser o resultado?"),
	new ProblemaProvaReal("$nomeM resolveu \\($a \\div $b = $q\\) e conferiu o valor com \\($b \\times $q\\). Qual deve ser o resultado?"),
	new ProblemaProvaReal("O monitor da turma obteve \\($a \\div $b = $q\\) e fez a prova real \\($b \\times $q\\). Qual deve ser o resultado?"),
	new ProblemaProvaReal("$nomeF calculou \\($a \\div $b = $q\\). Para ter certeza, multiplicou \\($b \\times $q\\). Que resultado deve obter?"),
	new ProblemaProvaReal("Um estudante encontrou \\($a \\div $b = $q\\) e conferiu com \\($b \\times $q\\). Qual deve ser o resultado?"),
	new ProblemaProvaReal("$nomeM anotou \\($a \\div $b = $q\\) e verificou pela prova real \\($b \\times $q\\). Qual deve ser o resultado?"),
	new ProblemaProvaReal("A funcionária do banco obteve \\($a \\div $b = $q\\) e conferiu com \\($b \\times $q\\). Que valor deve dar?"),
	new ProblemaProvaReal("$nomeF achou \\($a \\div $b = $q\\). Conferindo com \\($b \\times $q\\), qual deve ser o resultado?"),
	new ProblemaProvaReal("Um aluno calculou \\($a \\div $b = $q\\) e, para checar, multiplicou \\($b \\times $q\\). Qual deve ser o resultado?"),
	new ProblemaProvaReal("$nomeM obteve \\($a \\div $b = $q\\) e usou \\($b \\times $q\\) como prova real. Qual deve ser o resultado?"),
	new ProblemaProvaReal("A professora de matemática fez \\($a \\div $b = $q\\) e conferiu com \\($b \\times $q\\). Qual deve ser o resultado?"),
	new ProblemaProvaReal("$nomeF registrou \\($a \\div $b = $q\\) e multiplicou \\($b \\times $q\\) para verificar. Que valor deve obter?"),
	new ProblemaProvaReal("Um caixa de supermercado calculou \\($a \\div $b = $q\\) e conferiu com \\($b \\times $q\\). Qual deve ser o resultado?"),
	new ProblemaProvaReal("$nomeM encontrou \\($a \\div $b = $q\\). Para conferir, multiplicou \\($b \\times $q\\). Qual deve ser o resultado?"),
	new ProblemaProvaReal("A aluna obteve \\($a \\div $b = $q\\) e verificou pela prova real \\($b \\times $q\\). Qual deve ser o resultado?"),
	new ProblemaProvaReal("$nomeF calculou \\($a \\div $b = $q\\) e conferiu multiplicando \\($b \\times $q\\). Qual deve ser o resultado?"),
	new ProblemaProvaReal("Um contador fez \\($a \\div $b = $q\\). Para checar, multiplicou \\($b \\times $q\\). Que valor deve dar?"),
	new ProblemaProvaReal("$nomeM obteve \\($a \\div $b = $q\\) e conferiu com \\($b \\times $q\\). Qual deve ser o resultado?"),
	new ProblemaProvaReal("A monitora obteve \\($a \\div $b = $q\\) e fez a prova real \\($b \\times $q\\). Qual deve ser o resultado?"),
	new ProblemaProvaReal("$nomeF anotou \\($a \\div $b = $q\\). Verificando com \\($b \\times $q\\), qual deve ser o resultado?"),
	new ProblemaProvaReal("Um aluno registrou \\($a \\div $b = $q\\) e multiplicou \\($b \\times $q\\) para conferir. Qual deve ser o resultado?"),
	new ProblemaProvaReal("$nomeM resolveu \\($a \\div $b = $q\\) e conferiu pela prova real \\($b \\times $q\\). Que valor deve obter?"),
	new ProblemaProvaReal("A vendedora encontrou \\($a \\div $b = $q\\) e conferiu com \\($b \\times $q\\). Qual deve ser o resultado?"),
	new ProblemaProvaReal("$nomeF fez \\($a \\div $b = $q\\) e, para confirmar, multiplicou \\($b \\times $q\\). Qual deve ser o resultado?"),
	new ProblemaProvaReal("Um caixa registrou \\($a \\div $b = $q\\) e conferiu com o produto \\($b \\times $q\\). Qual deve ser o resultado?"),
	new ProblemaProvaReal("$nomeM calculou \\($a \\div $b = $q\\). Conferindo com \\($b \\times $q\\), que valor deve obter?"),
	new ProblemaProvaReal("A estudante obteve \\($a \\div $b = $q\\) e verificou com \\($b \\times $q\\). Qual deve ser o resultado?"),
	new ProblemaProvaReal("$nomeF achou \\($a \\div $b = $q\\) e fez a prova real multiplicando \\($b \\times $q\\). Qual deve ser o resultado?"),
	new ProblemaProvaReal("Um professor registrou \\($a \\div $b = $q\\) e conferiu com \\($b \\times $q\\). Que resultado deve dar?"),
	new ProblemaProvaReal("$nomeM obteve \\($a \\div $b = $q\\) e multiplicou \\($b \\times $q\\) para verificar. Qual deve ser o resultado?"),
	new ProblemaProvaReal("A caixa do cinema calculou \\($a \\div $b = $q\\) e conferiu com \\($b \\times $q\\). Qual deve ser o resultado?"),
	new ProblemaProvaReal("$nomeF encontrou \\($a \\div $b = $q\\). Para conferir, multiplicou \\($b \\times $q\\). Que valor deve obter?"),
	new ProblemaProvaReal("Um aluno obteve \\($a \\div $b = $q\\) e usou \\($b \\times $q\\) como prova real. Qual deve ser o resultado?"),
	new ProblemaProvaReal("$nomeM anotou \\($a \\div $b = $q\\) e conferiu com \\($b \\times $q\\). Qual deve ser o resultado?"),
	new ProblemaProvaReal("A tesoureira calculou \\($a \\div $b = $q\\) e verificou multiplicando \\($b \\times $q\\). Qual deve ser o resultado?"),
	new ProblemaProvaReal("$nomeF registrou \\($a \\div $b = $q\\) e conferiu pela prova real \\($b \\times $q\\). Que valor deve dar?"),
	new ProblemaProvaReal("Um comerciante obteve \\($a \\div $b = $q\\) e conferiu com \\($b \\times $q\\). Qual deve ser o resultado?"),
	new ProblemaProvaReal("$nomeM fez a divisão \\($a \\div $b = $q\\) e conferiu com \\($b \\times $q\\). Que valor deve obter?"),
	new ProblemaProvaReal("Uma caixa de loja calculou \\($a \\div $b = $q\\) e conferiu multiplicando \\($b \\times $q\\). Qual deve ser o resultado?"),
	new ProblemaProvaReal("$nomeF obteve \\($a \\div $b = $q\\) e usou a multiplicação \\($b \\times $q\\) como prova real. Qual deve ser o resultado?"),
	new ProblemaProvaReal("Um bancário calculou \\($a \\div $b = $q\\) e conferiu com \\($b \\times $q\\). Qual deve ser o resultado?"),
	new ProblemaProvaReal("$nomeM achou \\($a \\div $b = $q\\). Para checar a conta, multiplicou \\($b \\times $q\\). Que valor deve dar?"),
	new ProblemaProvaReal("A contadora encontrou \\($a \\div $b = $q\\) e verificou pela prova real \\($b \\times $q\\). Que valor deve dar?"),
	new ProblemaProvaReal("$nomeF anotou \\($a \\div $b = $q\\). Conferindo com \\($b \\times $q\\), qual deve ser o resultado?"),
	new ProblemaProvaReal("Um aluno calculou \\($a \\div $b = $q\\) e multiplicou \\($b \\times $q\\) para verificar. Qual deve ser o resultado?"),
	new ProblemaProvaReal("$nomeM obteve \\($a \\div $b = $q\\) e conferiu o resultado com \\($b \\times $q\\). Que valor deve encontrar?"),
	new ProblemaProvaReal("A vendedora registrou \\($a \\div $b = $q\\). Para conferir, multiplicou \\($b \\times $q\\). Qual deve ser o resultado?"),
	new ProblemaProvaReal("$nomeF resolveu \\($a \\div $b = $q\\) e fez a prova real multiplicando \\($b \\times $q\\). Qual deve ser o resultado?"),
	new ProblemaProvaReal("Um professor obteve \\($a \\div $b = $q\\) e conferiu com \\($b \\times $q\\). Qual deve ser o resultado?"),
	new ProblemaProvaReal("$nomeM achou \\($a \\div $b = $q\\). Para checar, multiplicou \\($b \\times $q\\). Que valor deve dar?"),
	new ProblemaProvaReal("Uma caixa de farmácia calculou \\($a \\div $b = $q\\) e conferiu com \\($b \\times $q\\). Qual deve ser o resultado?"),
	new ProblemaProvaReal("$nomeF encontrou \\($a \\div $b = $q\\) e verificou com \\($b \\times $q\\). Qual deve ser o resultado?"),
	new ProblemaProvaReal("Um aluno do sexto ano fez \\($a \\div $b = $q\\) e conferiu com \\($b \\times $q\\). Que resultado deve obter?"),
	new ProblemaProvaReal("$nomeM calculou \\($a \\div $b = $q\\) e multiplicou \\($b \\times $q\\) na prova real. Qual deve ser o resultado?"),
	new ProblemaProvaReal("A tesoureira do clube obteve \\($a \\div $b = $q\\) e conferiu com \\($b \\times $q\\). Qual deve ser o resultado?"),
	new ProblemaProvaReal("$nomeF registrou \\($a \\div $b = $q\\). Para verificar, fez \\($b \\times $q\\). Qual deve ser o resultado?"),
	new ProblemaProvaReal("Um comerciante calculou \\($a \\div $b = $q\\) e conferiu multiplicando \\($b \\times $q\\). Que valor deve aparecer?"),
	new ProblemaProvaReal("$nomeM obteve \\($a \\div $b = $q\\). Conferindo pela prova real \\($b \\times $q\\), qual deve ser o resultado?"),
	new ProblemaProvaReal("A aluna anotou \\($a \\div $b = $q\\) e multiplicou \\($b \\times $q\\) para conferir. Qual deve ser o resultado?"),

	};

	public static ProblemaProvaReal getProblema()
	{
		Random rand = new Random();
		return problemas[rand.nextInt(problemas.length)].clone();
	}
}
