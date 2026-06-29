package modelo.pdf;

public enum TipoPdf
{
	ListaExercicios("Lista de Exercícios",2),
	ListaQuestoes("Lista de Questões",3),
	Teoria("Teoria",1),
	MaterialSuplementar("Material Suplementar",4);

	private String nome;
	private int ordem;
	
	TipoPdf(String nome, int ordem)
	{
		this.nome = nome;
		this.ordem = ordem;
	}

	public String getNome()
	{
		return nome;
	}
	
	public int getOrdem()
	{
		return ordem;
	}
}
