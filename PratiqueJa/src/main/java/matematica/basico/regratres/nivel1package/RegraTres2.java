package matematica.basico.regratres.nivel1package;

import matematica.GeradorExercicio;

public class RegraTres2 extends GeradorExercicio
{
	private static final String[] TEXTOS = {
		"Em uma turma de $V alunos, $P% tiraram nota máxima. Quantos alunos tiraram nota máxima?",
		"Uma loja tem $V produtos. $P% deles estão em promoção. Quantos produtos estão em promoção?",
		"Um fazendeiro possui $V hectares. $P% da área é plantada com milho. Quantos hectares são plantados?",
		"Em uma cidade com $V habitantes, $P% possuem carro. Quantos habitantes possuem carro?",
		"Um auditório tem $V lugares. $P% das cadeiras estão ocupadas. Quantas cadeiras estão ocupadas?",
		"Uma escola tem $V livros na biblioteca. $P% são de ciências. Quantos livros são de ciências?",
		"Em uma prova com $V questões, $P% foram anuladas. Quantas questões foram anuladas?",
		"Um time tem $V jogadores inscritos. $P% foram convocados para o jogo. Quantos foram convocados?",
	};

	@Override
	protected void construir()
	{
		int[] percentuais = {4, 5, 10, 20, 25, 50};
		int p = percentuais[rand.nextInt(percentuais.length)];
		int fator = 100 / p;
		int k = 2 + rand.nextInt(19);
		int V = k * fator;

		String texto = TEXTOS[rand.nextInt(TEXTOS.length)];
		texto = texto.replace("$P", "" + p).replace("$V", "" + V);

		addParagrafo(texto);
		gerarAlternativas("" + k);

		addResolucao("Calcular \\(" + p + "\\%\\) de \\(" + V + "\\):");
		addResolucao("\\(\\dfrac{" + p + "}{100} \\times " + V + " =\\)");
		addResolucao("\\(\\dfrac{" + (p * V) + "}{100} = \\mathbf{" + k + "}\\)");
	}
}
