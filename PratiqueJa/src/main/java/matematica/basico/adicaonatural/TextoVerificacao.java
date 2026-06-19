package matematica.basico.adicaonatural;

import java.util.Random;

// Biblioteca de enunciados de VERIFICAÇÃO da adição: alguém calculou a + b = c e confere
// subtraindo c - b (deve recuperar a parcela a). Placeholders: $a, $b, $c, $nomeM/$nomeF.
public class TextoVerificacao
{
	static ProblemaVerificacao problemas[] = {

	new ProblemaVerificacao("$nomeF calculou que \\($a + $b = $c\\). Para verificar, ela subtraiu \\($c - $b\\). Qual deve ser o resultado?"),
	new ProblemaVerificacao("$nomeM obteve \\($a + $b = $c\\) e, para conferir, subtraiu \\($c - $b\\). Qual deve ser o resultado?"),
	new ProblemaVerificacao("Um aluno somou \\($a + $b = $c\\). Verificando com \\($c - $b\\), qual deve ser o resultado?"),
	new ProblemaVerificacao("A professora corrigiu \\($a + $b = $c\\) usando a subtração inversa \\($c - $b\\). Que valor deve obter?"),
	new ProblemaVerificacao("$nomeF anotou \\($a + $b = $c\\). Conferindo com \\($c - $b\\), qual deve ser o resultado?"),
	new ProblemaVerificacao("Um caixa registrou \\($a + $b = $c\\). Para checar, calculou \\($c - $b\\). Qual deve ser o resultado?"),
	new ProblemaVerificacao("$nomeM encontrou \\($a + $b = $c\\) e verificou com \\($c - $b\\). Que valor deve dar?"),
	new ProblemaVerificacao("Uma estudante achou \\($a + $b = $c\\). Subtraindo \\($c - $b\\) para conferir, qual deve ser o resultado?"),
	new ProblemaVerificacao("$nomeF resolveu \\($a + $b = $c\\) e conferiu pela operação inversa \\($c - $b\\). Qual deve ser o resultado?"),
	new ProblemaVerificacao("Um contador somou \\($a + $b = $c\\) e verificou subtraindo \\($c - $b\\). Que valor deve obter?"),
	new ProblemaVerificacao("$nomeM calculou \\($a + $b = $c\\). Para ter certeza, fez \\($c - $b\\). Qual deve ser o resultado?"),
	new ProblemaVerificacao("A monitora obteve \\($a + $b = $c\\) e conferiu com \\($c - $b\\). Qual deve ser o resultado?"),
	new ProblemaVerificacao("$nomeF achou \\($a + $b = $c\\). Conferindo pela inversa \\($c - $b\\), que valor deve obter?"),
	new ProblemaVerificacao("Um aluno do quarto ano fez \\($a + $b = $c\\) e verificou com \\($c - $b\\). Qual deve ser o resultado?"),
	new ProblemaVerificacao("$nomeM somou \\($a + $b = $c\\) e subtraiu \\($c - $b\\) para verificar. Qual deve ser o resultado?"),
	new ProblemaVerificacao("A tesoureira obteve \\($a + $b = $c\\) e conferiu com \\($c - $b\\). Qual deve ser o resultado?"),
	new ProblemaVerificacao("$nomeF registrou \\($a + $b = $c\\). Para verificar, fez \\($c - $b\\). Qual deve ser o resultado?"),
	new ProblemaVerificacao("Um comerciante calculou \\($a + $b = $c\\) e conferiu pela subtração inversa. Que valor deve aparecer ao fazer \\($c - $b\\)?"),
	new ProblemaVerificacao("$nomeM obteve \\($a + $b = $c\\). Conferindo com \\($c - $b\\), qual deve ser o resultado?"),
	new ProblemaVerificacao("A aluna anotou \\($a + $b = $c\\) e subtraiu \\($c - $b\\) para conferir. Qual deve ser o resultado?"),
	new ProblemaVerificacao("$nomeF fez \\($a + $b = $c\\) e verificou com \\($c - $b\\). Que valor deve dar?"),
	new ProblemaVerificacao("Um professor obteve \\($a + $b = $c\\) e conferiu com \\($c - $b\\). Qual deve ser o resultado?"),
	new ProblemaVerificacao("$nomeM resolveu \\($a + $b = $c\\) e conferiu o valor com \\($c - $b\\). Qual deve ser o resultado?"),
	new ProblemaVerificacao("O monitor da turma obteve \\($a + $b = $c\\) e fez a verificação \\($c - $b\\). Qual deve ser o resultado?"),
	new ProblemaVerificacao("$nomeF calculou \\($a + $b = $c\\). Para ter certeza, subtraiu \\($c - $b\\). Que resultado deve obter?"),
	new ProblemaVerificacao("Um estudante encontrou \\($a + $b = $c\\) e conferiu com \\($c - $b\\). Qual deve ser o resultado?"),
	new ProblemaVerificacao("$nomeM anotou \\($a + $b = $c\\) e verificou pela inversa \\($c - $b\\). Qual deve ser o resultado?"),
	new ProblemaVerificacao("A funcionária do banco obteve \\($a + $b = $c\\) e conferiu com \\($c - $b\\). Que valor deve dar?"),
	new ProblemaVerificacao("$nomeF achou \\($a + $b = $c\\). Conferindo com \\($c - $b\\), qual deve ser o resultado?"),
	new ProblemaVerificacao("Um aluno calculou \\($a + $b = $c\\) e, para checar, subtraiu \\($c - $b\\). Qual deve ser o resultado?"),
	new ProblemaVerificacao("$nomeM obteve \\($a + $b = $c\\) e usou \\($c - $b\\) para verificar. Qual deve ser o resultado?"),
	new ProblemaVerificacao("A professora de matemática fez \\($a + $b = $c\\) e conferiu com \\($c - $b\\). Qual deve ser o resultado?"),
	new ProblemaVerificacao("$nomeF registrou \\($a + $b = $c\\) e subtraiu \\($c - $b\\) para verificar. Que valor deve obter?"),
	new ProblemaVerificacao("Um caixa de supermercado calculou \\($a + $b = $c\\) e conferiu com \\($c - $b\\). Qual deve ser o resultado?"),
	new ProblemaVerificacao("$nomeM encontrou \\($a + $b = $c\\). Para conferir, subtraiu \\($c - $b\\). Qual deve ser o resultado?"),
	new ProblemaVerificacao("A aluna obteve \\($a + $b = $c\\) e verificou pela inversa \\($c - $b\\). Qual deve ser o resultado?"),
	new ProblemaVerificacao("$nomeF calculou \\($a + $b = $c\\) e conferiu subtraindo \\($c - $b\\). Qual deve ser o resultado?"),
	new ProblemaVerificacao("Um contador fez \\($a + $b = $c\\). Para checar, subtraiu \\($c - $b\\). Que valor deve dar?"),
	new ProblemaVerificacao("$nomeM obteve \\($a + $b = $c\\) e conferiu com \\($c - $b\\). Qual deve ser o resultado?"),
	new ProblemaVerificacao("A monitora obteve \\($a + $b = $c\\) e fez a verificação \\($c - $b\\). Qual deve ser o resultado?"),
	new ProblemaVerificacao("$nomeF anotou \\($a + $b = $c\\). Verificando com \\($c - $b\\), qual deve ser o resultado?"),
	new ProblemaVerificacao("Um aluno registrou \\($a + $b = $c\\) e subtraiu \\($c - $b\\) para conferir. Qual deve ser o resultado?"),
	new ProblemaVerificacao("$nomeM resolveu \\($a + $b = $c\\) e conferiu pela inversa \\($c - $b\\). Que valor deve obter?"),
	new ProblemaVerificacao("A vendedora encontrou \\($a + $b = $c\\) e conferiu com \\($c - $b\\). Qual deve ser o resultado?"),
	new ProblemaVerificacao("$nomeF fez \\($a + $b = $c\\) e, para confirmar, subtraiu \\($c - $b\\). Qual deve ser o resultado?"),
	new ProblemaVerificacao("Um caixa registrou \\($a + $b = $c\\) e conferiu com a diferença \\($c - $b\\). Qual deve ser o resultado?"),
	new ProblemaVerificacao("$nomeM calculou \\($a + $b = $c\\). Conferindo com \\($c - $b\\), que valor deve obter?"),
	new ProblemaVerificacao("A estudante obteve \\($a + $b = $c\\) e verificou com \\($c - $b\\). Qual deve ser o resultado?"),
	new ProblemaVerificacao("$nomeF achou \\($a + $b = $c\\) e fez a verificação subtraindo \\($c - $b\\). Qual deve ser o resultado?"),
	new ProblemaVerificacao("Um professor registrou \\($a + $b = $c\\) e conferiu com \\($c - $b\\). Que resultado deve dar?"),
	new ProblemaVerificacao("$nomeM obteve \\($a + $b = $c\\) e subtraiu \\($c - $b\\) para verificar. Qual deve ser o resultado?"),
	new ProblemaVerificacao("A caixa do cinema calculou \\($a + $b = $c\\) e conferiu com \\($c - $b\\). Qual deve ser o resultado?"),
	new ProblemaVerificacao("$nomeF encontrou \\($a + $b = $c\\). Para conferir, subtraiu \\($c - $b\\). Que valor deve obter?"),
	new ProblemaVerificacao("Um aluno obteve \\($a + $b = $c\\) e usou \\($c - $b\\) como verificação. Qual deve ser o resultado?"),
	new ProblemaVerificacao("$nomeM anotou \\($a + $b = $c\\) e conferiu com \\($c - $b\\). Qual deve ser o resultado?"),
	new ProblemaVerificacao("A tesoureira calculou \\($a + $b = $c\\) e verificou subtraindo \\($c - $b\\). Qual deve ser o resultado?"),
	new ProblemaVerificacao("$nomeF registrou \\($a + $b = $c\\) e conferiu pela operação inversa \\($c - $b\\). Que valor deve dar?"),
	new ProblemaVerificacao("Um comerciante obteve \\($a + $b = $c\\) e conferiu com \\($c - $b\\). Qual deve ser o resultado?"),
	new ProblemaVerificacao("$nomeM fez a soma \\($a + $b = $c\\) e conferiu com \\($c - $b\\). Que valor deve obter?"),
	new ProblemaVerificacao("Uma caixa de loja calculou \\($a + $b = $c\\) e conferiu subtraindo \\($c - $b\\). Qual deve ser o resultado?"),
	new ProblemaVerificacao("$nomeF obteve \\($a + $b = $c\\) e usou a subtração \\($c - $b\\) como verificação. Qual deve ser o resultado?"),
	new ProblemaVerificacao("Um bancário calculou \\($a + $b = $c\\) e conferiu com \\($c - $b\\). Qual deve ser o resultado?"),
	new ProblemaVerificacao("$nomeM achou \\($a + $b = $c\\). Para checar a conta, subtraiu \\($c - $b\\). Que valor deve dar?"),
	new ProblemaVerificacao("A contadora encontrou \\($a + $b = $c\\) e verificou pela inversa \\($c - $b\\). Que valor deve dar?"),
	new ProblemaVerificacao("$nomeF anotou \\($a + $b = $c\\). Conferindo com \\($c - $b\\), qual deve ser o resultado?"),
	new ProblemaVerificacao("Um aluno calculou \\($a + $b = $c\\) e subtraiu \\($c - $b\\) para verificar. Qual deve ser o resultado?"),
	new ProblemaVerificacao("$nomeM obteve \\($a + $b = $c\\) e conferiu o resultado com \\($c - $b\\). Que valor deve encontrar?"),
	new ProblemaVerificacao("A vendedora registrou \\($a + $b = $c\\). Para conferir, subtraiu \\($c - $b\\). Qual deve ser o resultado?"),
	new ProblemaVerificacao("$nomeF resolveu \\($a + $b = $c\\) e fez a verificação subtraindo \\($c - $b\\). Qual deve ser o resultado?"),
	new ProblemaVerificacao("Um professor obteve \\($a + $b = $c\\) e conferiu com \\($c - $b\\). Qual deve ser o resultado?"),
	new ProblemaVerificacao("$nomeM achou \\($a + $b = $c\\). Para checar, subtraiu \\($c - $b\\). Que valor deve dar?"),
	new ProblemaVerificacao("Uma caixa de farmácia calculou \\($a + $b = $c\\) e conferiu com \\($c - $b\\). Qual deve ser o resultado?"),
	new ProblemaVerificacao("$nomeF encontrou \\($a + $b = $c\\) e verificou com \\($c - $b\\). Qual deve ser o resultado?"),
	new ProblemaVerificacao("Um aluno do quinto ano fez \\($a + $b = $c\\) e conferiu com \\($c - $b\\). Que resultado deve obter?"),
	new ProblemaVerificacao("$nomeM calculou \\($a + $b = $c\\) e subtraiu \\($c - $b\\) na verificação. Qual deve ser o resultado?"),
	new ProblemaVerificacao("A tesoureira do clube obteve \\($a + $b = $c\\) e conferiu com \\($c - $b\\). Qual deve ser o resultado?"),
	new ProblemaVerificacao("$nomeF registrou \\($a + $b = $c\\). Para verificar, fez \\($c - $b\\). Qual deve ser o resultado?"),
	new ProblemaVerificacao("Um comerciante calculou \\($a + $b = $c\\) e conferiu subtraindo \\($c - $b\\). Que valor deve aparecer?"),
	new ProblemaVerificacao("$nomeM obteve \\($a + $b = $c\\). Conferindo pela inversa \\($c - $b\\), qual deve ser o resultado?"),
	new ProblemaVerificacao("A aluna anotou \\($a + $b = $c\\) e subtraiu \\($c - $b\\) para conferir. Qual deve ser o resultado?"),

	};

	public static ProblemaVerificacao getProblema()
	{
		Random rand = new Random();
		return problemas[rand.nextInt(problemas.length)].clone();
	}
}
