package modelo.usuario;

public enum StatusAssinatura
{
	Ativa("Ativa", "bgVerde", "fa-regular fa-circle-check"),
	Cancelada("Cancelada", "bgVermelho", "fa-regular fa-circle-xmark"),
	Expirada("Expirada", "bgAmarelo", "fa-regular fa-clock");

	private final String nome;
	private final String cor;
	private final String icon;

	StatusAssinatura(String nome, String cor, String icon)
	{
		this.nome = nome;
		this.cor = cor;
		this.icon = icon;
	}

	public String getNome()
	{
		return nome;
	}

	public String getCor()
	{
		return cor;
	}

	public String getIcon()
	{
		return icon;
	}
}
