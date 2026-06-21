package matematica.avancado.funcaoquadratica;

import java.util.Random;

import static matematica.avancado.funcaoquadratica.TipoFuncaoQuadratica.ABSCISSA;
import static matematica.avancado.funcaoquadratica.TipoFuncaoQuadratica.VALOR;

// Biblioteca de enunciados de máximo de função quadrática. Cada template traz o tipo
// de pergunta (valor/abscissa), a letra da variável, a unidade da resposta e as bandeiras
// do modelo (aUm: a=1; cZero: c=0). O placeholder $f é substituído pela função gerada.
// getProblema() sorteia e clona; os valores (e a função) são gerados depois pelo Problema.
public class TextoFuncaoQuadratica
{
	// atalhos para os perfis de modelo
	private static ProblemaFuncaoQuadratica projetil(String t, TipoFuncaoQuadratica tipo, String u)
	{ return new ProblemaFuncaoQuadratica(t, tipo, "t", u, false, true); }
	private static ProblemaFuncaoQuadratica area(String t, TipoFuncaoQuadratica tipo, String u)
	{ return new ProblemaFuncaoQuadratica(t, tipo, "x", u, true, true); }
	private static ProblemaFuncaoQuadratica lucro(String t, TipoFuncaoQuadratica tipo, String u)
	{ return new ProblemaFuncaoQuadratica(t, tipo, "x", u, false, false); }

	static ProblemaFuncaoQuadratica problemas[] = {

	// ===================== PROJÉTIL — altura máxima (VALOR) =====================
	projetil("Um projétil é lançado e sua altura é \\(h(t) = $f\\) (em metros), com \\(t\\) em segundos. Qual é a altura máxima atingida?", VALOR, "metros"),
	projetil("Uma bola é chutada e sua altura segue \\(h(t) = $f\\) (em metros). Qual é a altura máxima alcançada?", VALOR, "metros"),
	projetil("Um foguete de água tem altura \\(h(t) = $f\\) (em metros). Qual é a altura máxima?", VALOR, "metros"),
	projetil("A altura de uma bola de tênis é \\(h(t) = $f\\) (em metros). Qual é a altura máxima atingida?", VALOR, "metros"),
	projetil("Um jato d'água atinge altura \\(h(t) = $f\\) (em metros). Qual é a altura máxima?", VALOR, "metros"),
	projetil("Uma pedra é arremessada e sua altura é \\(h(t) = $f\\) (em metros). Qual é a altura máxima?", VALOR, "metros"),
	projetil("Um fogo de artifício sobe segundo \\(h(t) = $f\\) (em metros). Qual é a altura máxima?", VALOR, "metros"),
	projetil("A altura de um salto é \\(h(t) = $f\\) (em metros). Qual é a altura máxima atingida?", VALOR, "metros"),
	projetil("Uma flecha lançada para cima tem altura \\(h(t) = $f\\) (em metros). Qual é a altura máxima?", VALOR, "metros"),
	projetil("Um golfista lança a bola com altura \\(h(t) = $f\\) (em metros). Qual é a altura máxima?", VALOR, "metros"),
	projetil("A altura de um míssil de treino é \\(h(t) = $f\\) (em metros). Qual é a altura máxima?", VALOR, "metros"),
	projetil("Uma bola de vôlei sobe segundo \\(h(t) = $f\\) (em metros). Qual é a altura máxima?", VALOR, "metros"),
	projetil("Uma bola de futebol sobe segundo \\(h(t) = $f\\) (em metros). Qual é a altura máxima?", VALOR, "metros"),
	projetil("A altura de um drone em subida é \\(h(t) = $f\\) (em metros). Qual é a altura máxima?", VALOR, "metros"),
	projetil("Um esquilo salta e sua altura é \\(h(t) = $f\\) (em metros). Qual é a altura máxima?", VALOR, "metros"),

	// ===================== PROJÉTIL — instante do máximo (ABSCISSA) =====================
	projetil("Um projétil tem altura \\(h(t) = $f\\) (em metros). Em que instante, em segundos, atinge a altura máxima?", ABSCISSA, "segundos"),
	projetil("Uma bola chutada tem altura \\(h(t) = $f\\) (em metros). Após quantos segundos atinge a altura máxima?", ABSCISSA, "segundos"),
	projetil("A altura de um foguete é \\(h(t) = $f\\) (em metros). Em quantos segundos atinge a altura máxima?", ABSCISSA, "segundos"),
	projetil("Um jato d'água segue \\(h(t) = $f\\) (em metros). Após quantos segundos a altura é máxima?", ABSCISSA, "segundos"),
	projetil("A altura de uma pedra arremessada é \\(h(t) = $f\\) (em metros). Em que instante a altura é máxima?", ABSCISSA, "segundos"),
	projetil("Um fogo de artifício sobe com \\(h(t) = $f\\) (em metros). Após quantos segundos atinge o ponto mais alto?", ABSCISSA, "segundos"),
	projetil("A altura de uma bola de basquete é \\(h(t) = $f\\) (em metros). Em quantos segundos atinge a altura máxima?", ABSCISSA, "segundos"),
	projetil("Um saltador tem altura \\(h(t) = $f\\) (em metros). Após quantos segundos atinge a altura máxima?", ABSCISSA, "segundos"),
	projetil("A altura de uma flecha é \\(h(t) = $f\\) (em metros). Em que instante atinge o ponto mais alto?", ABSCISSA, "segundos"),
	projetil("Uma bola de golfe segue \\(h(t) = $f\\) (em metros). Após quantos segundos atinge a altura máxima?", ABSCISSA, "segundos"),
	projetil("A altura de uma bola de vôlei é \\(h(t) = $f\\) (em metros). Após quantos segundos atinge a altura máxima?", ABSCISSA, "segundos"),
	projetil("Um foguete de brinquedo tem altura \\(h(t) = $f\\) (em metros). Em quantos segundos atinge o ponto mais alto?", ABSCISSA, "segundos"),

	// ===================== ÁREA — área máxima (VALOR) =====================
	area("Um retângulo de perímetro fixo tem área \\(A(x) = $f\\) (em m²), onde \\(x\\) é a largura. Qual é a área máxima?", VALOR, "metros quadrados"),
	area("Um terreno retangular tem área \\(A(x) = $f\\) (em m²), com \\(x\\) sendo um dos lados. Qual é a área máxima?", VALOR, "metros quadrados"),
	area("Um curral retangular tem área \\(A(x) = $f\\) (em m²). Qual é a maior área possível?", VALOR, "metros quadrados"),
	area("Uma horta retangular tem área \\(A(x) = $f\\) (em m²). Qual é a área máxima?", VALOR, "metros quadrados"),
	area("Um jardim retangular tem área \\(A(x) = $f\\) (em m²). Qual é a área máxima?", VALOR, "metros quadrados"),
	area("Uma região cercada tem área \\(A(x) = $f\\) (em m²). Qual é a área máxima?", VALOR, "metros quadrados"),
	area("Um pátio retangular tem área \\(A(x) = $f\\) (em m²). Qual é a maior área?", VALOR, "metros quadrados"),
	area("Uma piscina retangular tem área \\(A(x) = $f\\) (em m²). Qual é a área máxima?", VALOR, "metros quadrados"),
	area("Um aviário retangular tem área \\(A(x) = $f\\) (em m²). Qual é a área máxima?", VALOR, "metros quadrados"),
	area("Um estacionamento retangular tem área \\(A(x) = $f\\) (em m²). Qual é a área máxima?", VALOR, "metros quadrados"),

	// ===================== ÁREA — dimensão ótima (ABSCISSA) =====================
	area("A área de um retângulo é \\(A(x) = $f\\) (em m²), onde \\(x\\) é a largura. Que largura, em metros, gera a área máxima?", ABSCISSA, "metros"),
	area("Um terreno retangular tem área \\(A(x) = $f\\) (em m²). Qual valor de \\(x\\), em metros, maximiza a área?", ABSCISSA, "metros"),
	area("Um curral tem área \\(A(x) = $f\\) (em m²). Que largura, em metros, dá a maior área?", ABSCISSA, "metros"),
	area("Uma horta tem área \\(A(x) = $f\\) (em m²). Que medida \\(x\\), em metros, maximiza a área?", ABSCISSA, "metros"),
	area("Um jardim tem área \\(A(x) = $f\\) (em m²). Que largura, em metros, gera a área máxima?", ABSCISSA, "metros"),
	area("Uma região cercada tem área \\(A(x) = $f\\) (em m²). Qual \\(x\\), em metros, dá a área máxima?", ABSCISSA, "metros"),
	area("Um pátio tem área \\(A(x) = $f\\) (em m²). Que largura, em metros, maximiza a área?", ABSCISSA, "metros"),
	area("Uma piscina retangular tem área \\(A(x) = $f\\) (em m²). Que valor de \\(x\\), em metros, maximiza a área?", ABSCISSA, "metros"),
	area("Um cercado retangular tem área \\(A(x) = $f\\) (em m²). Que largura, em metros, maximiza a área?", ABSCISSA, "metros"),
	area("Um canteiro retangular tem área \\(A(x) = $f\\) (em m²). Que largura, em metros, gera a área máxima?", ABSCISSA, "metros"),

	// ===================== LUCRO / RECEITA — valor máximo (VALOR) =====================
	lucro("O lucro de uma empresa é \\(L(x) = $f\\) (em reais), com \\(x\\) a quantidade vendida. Qual é o lucro máximo?", VALOR, "reais"),
	lucro("A receita de uma loja é \\(R(x) = $f\\) (em reais). Qual é a receita máxima?", VALOR, "reais"),
	lucro("O lucro de uma fábrica é \\(L(x) = $f\\) (em reais). Qual é o lucro máximo?", VALOR, "reais"),
	lucro("O lucro mensal de um negócio é \\(L(x) = $f\\) (em reais). Qual é o lucro máximo?", VALOR, "reais"),
	lucro("A receita de uma empresa é \\(R(x) = $f\\) (em reais). Qual é a maior receita?", VALOR, "reais"),
	lucro("O lucro de uma loja é \\(L(x) = $f\\) (em reais), onde \\(x\\) é o número de itens. Qual é o lucro máximo?", VALOR, "reais"),
	lucro("O lucro de uma cafeteria é \\(L(x) = $f\\) (em reais). Qual é o lucro máximo?", VALOR, "reais"),
	lucro("A receita de vendas é \\(R(x) = $f\\) (em reais). Qual é a receita máxima?", VALOR, "reais"),
	lucro("O lucro de uma startup é \\(L(x) = $f\\) (em reais). Qual é o lucro máximo?", VALOR, "reais"),
	lucro("O lucro diário de uma banca é \\(L(x) = $f\\) (em reais). Qual é o lucro máximo?", VALOR, "reais"),
	lucro("A receita de um evento é \\(R(x) = $f\\) (em reais). Qual é a receita máxima?", VALOR, "reais"),
	lucro("O lucro de uma confeitaria é \\(L(x) = $f\\) (em reais). Qual é o lucro máximo?", VALOR, "reais"),
	lucro("O lucro de uma padaria é \\(L(x) = $f\\) (em reais). Qual é o lucro máximo?", VALOR, "reais"),
	lucro("A receita de um food truck é \\(R(x) = $f\\) (em reais). Qual é a receita máxima?", VALOR, "reais"),

	// ===================== LUCRO / RECEITA — quantidade ótima (ABSCISSA) =====================
	lucro("O lucro de uma empresa é \\(L(x) = $f\\), onde \\(x\\) é a quantidade produzida. Que quantidade maximiza o lucro?", ABSCISSA, "unidades"),
	lucro("A receita de uma loja é \\(R(x) = $f\\). Que quantidade maximiza a receita?", ABSCISSA, "unidades"),
	lucro("O lucro de uma fábrica é \\(L(x) = $f\\). Quantas unidades maximizam o lucro?", ABSCISSA, "unidades"),
	lucro("O lucro de um negócio é \\(L(x) = $f\\). Qual quantidade produzida maximiza o lucro?", ABSCISSA, "unidades"),
	lucro("A receita de vendas é \\(R(x) = $f\\). Que quantidade gera a receita máxima?", ABSCISSA, "unidades"),
	lucro("O lucro de uma cafeteria é \\(L(x) = $f\\). Quantos itens maximizam o lucro?", ABSCISSA, "unidades"),
	lucro("O lucro de uma loja é \\(L(x) = $f\\). Que quantidade de produtos maximiza o lucro?", ABSCISSA, "unidades"),
	lucro("A receita de um evento é \\(R(x) = $f\\). Quantos ingressos maximizam a receita?", ABSCISSA, "ingressos"),
	lucro("O lucro de uma confeitaria é \\(L(x) = $f\\). Que quantidade maximiza o lucro?", ABSCISSA, "unidades"),
	lucro("O lucro de um produtor é \\(L(x) = $f\\). Quantas unidades maximizam o lucro?", ABSCISSA, "unidades"),
	lucro("A receita de uma plataforma é \\(R(x) = $f\\). Que número de assinantes maximiza a receita?", ABSCISSA, "assinantes"),
	lucro("O lucro de uma startup é \\(L(x) = $f\\). Que quantidade maximiza o lucro?", ABSCISSA, "unidades"),
	lucro("O lucro diário de uma banca é \\(L(x) = $f\\). Que quantidade vendida maximiza o lucro?", ABSCISSA, "unidades"),
	lucro("A receita de um food truck é \\(R(x) = $f\\). Quantos pratos maximizam a receita?", ABSCISSA, "pratos"),
	lucro("A receita de um cinema é \\(R(x) = $f\\). Quantos ingressos maximizam a receita?", ABSCISSA, "ingressos"),

	// --- complementos ---
	projetil("Uma bola de handebol sobe segundo \\(h(t) = $f\\) (em metros). Qual é a altura máxima?", VALOR, "metros"),
	projetil("A altura de um drone é \\(h(t) = $f\\) (em metros). Em quantos segundos atinge a altura máxima?", ABSCISSA, "segundos"),
	area("Um canteiro retangular tem área \\(A(x) = $f\\) (em m²). Qual é a área máxima?", VALOR, "metros quadrados"),
	lucro("O lucro de uma oficina é \\(L(x) = $f\\) (em reais). Qual é o lucro máximo?", VALOR, "reais"),

	};

	public static ProblemaFuncaoQuadratica getProblema()
	{
		Random rand = new Random();
		return problemas[rand.nextInt(problemas.length)].clone();
	}
}
