package matematica;

import java.util.Random;

/**
 * Listas de nomes próprios brasileiros para uso em exercícios contextualizados.
 *
 * Separadas por gênero para preservar a regência dos textos (ela/ele, seu/sua etc.):
 * ao substituir um nome fixo, troque por um nome do MESMO gênero.
 *
 * Uso típico em um GeradorExercicio (que possui o campo {@code rand}):
 * <pre>
 *   String nome = Nomes.feminino(rand);
 *   addParagrafo(nome + " leu " + a + " livros. Quantos ela leu?");
 * </pre>
 */
public final class Nomes
{
	private Nomes() {}

	public static final String[] MASCULINOS = {
		"Miguel", "Arthur", "Heitor", "Bernardo", "Davi", "Théo", "Gabriel", "Pedro", "Samuel", "Benjamin",
		"Lucas", "Matheus", "Rafael", "Henrique", "Gustavo", "Felipe", "João", "Bruno", "Carlos", "Eduardo",
		"Daniel", "Vitor", "Leonardo", "Murilo", "Nicolas", "Caio", "Enzo", "Otávio", "Rodrigo", "Diego",
		"André", "Marcelo", "Ricardo", "Roberto", "Fábio", "Sérgio", "Paulo", "Marcos", "Antônio", "Fernando",
		"Alexandre", "Renato", "Thiago", "Vinícius", "Guilherme", "Igor", "Leandro", "Wesley", "Anderson", "Júlio",
		"César", "Cauã", "Bryan", "Yuri", "Danilo", "Emanuel", "Joaquim", "Lorenzo", "Pietro", "Ryan",
		"Ian", "Erick", "Kaique", "Luan", "Breno", "Augusto", "Hugo", "Tomás", "Raul", "Saulo",
		"Edson", "Geraldo", "Hélio", "Ivan", "Jorge", "Kléber", "Mário", "Nelson", "Osvaldo", "Rui",
		"Silvio", "Tadeu", "Valter", "Wilson", "Adriano", "Cristiano", "Douglas", "Elias", "Francisco", "Gilberto",
		"Lauro", "Maurício", "Norberto", "Reinaldo", "Sebastião", "Vicente", "Wagner", "Fabrício", "Otto", "Renan"
	};

	public static final String[] FEMININOS = {
		"Helena", "Alice", "Laura", "Maria", "Sofia", "Manuela", "Valentina", "Heloísa", "Luiza", "Júlia",
		"Isabela", "Beatriz", "Mariana", "Ana", "Lara", "Marina", "Clara", "Cecília", "Lívia", "Yasmin",
		"Letícia", "Fernanda", "Camila", "Larissa", "Patrícia", "Carla", "Juliana", "Aline", "Bianca", "Débora",
		"Renata", "Vanessa", "Amanda", "Bruna", "Carolina", "Daniela", "Eduarda", "Gabriela", "Isadora", "Joana",
		"Kelly", "Luana", "Mirela", "Natália", "Olívia", "Priscila", "Raquel", "Sabrina", "Tatiana", "Vitória",
		"Yara", "Adriana", "Bárbara", "Cristina", "Denise", "Elaine", "Flávia", "Giovana", "Heloá", "Íris",
		"Janaína", "Karina", "Lorena", "Márcia", "Nicole", "Otília", "Paula", "Quitéria", "Rosa", "Simone",
		"Talita", "Úrsula", "Valéria", "Wanda", "Alana", "Antônia", "Cláudia", "Diana", "Elisa", "Fabiana",
		"Gisele", "Hortência", "Ingrid", "Jaqueline", "Késia", "Liliane", "Mônica", "Nádia", "Regina", "Sônia",
		"Teresa", "Vera", "Zélia", "Ester", "Rute", "Noemi", "Selma", "Tânia", "Viviane", "Sara"
	};

	public static String masculino(Random rand)
	{
		return MASCULINOS[rand.nextInt(MASCULINOS.length)];
	}

	public static String feminino(Random rand)
	{
		return FEMININOS[rand.nextInt(FEMININOS.length)];
	}

	/** Nome de gênero aleatório. */
	public static String qualquer(Random rand)
	{
		return rand.nextBoolean() ? masculino(rand) : feminino(rand);
	}

	/** Nome masculino garantidamente diferente de {@code jaUsado} (para 2 pessoas distintas). */
	public static String masculinoDiferente(Random rand, String jaUsado)
	{
		String nome;
		do
			nome = masculino(rand);
		while(nome.equals(jaUsado));
		return nome;
	}

	/** Nome feminino garantidamente diferente de {@code jaUsado} (para 2 pessoas distintas). */
	public static String femininoDiferente(Random rand, String jaUsado)
	{
		String nome;
		do
			nome = feminino(rand);
		while(nome.equals(jaUsado));
		return nome;
	}
}
