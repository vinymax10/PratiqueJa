package modelo.pdf;

public enum TipoPdf
{
	ListaExercicios("Lista de Exercícios"),
	ListaQuestoes("Lista de Questões"),
	Teoria("Teoria"),
	MaterialSuplementar("Material Suplementar");

	private String nome;

	TipoPdf(String nome)
	{
		this.nome = nome;
	}

	public String getNome()
	{
		return nome;
	}
}
