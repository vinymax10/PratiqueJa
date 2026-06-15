package pdfAntigo.ebook;

public class ItemSumario 
{
	public String nome;
	public String index;
	public String pagina;
	
	public ItemSumario(String nome, String index, String pagina) {
		super();
		this.nome = nome;
		this.index = index;
		this.pagina = pagina;
	}

	@Override
	public String toString() {
		return "ItemSumario [nome=" + nome + ", index=" + index + ", pagina=" + pagina + "]";
	}
	
	
}
