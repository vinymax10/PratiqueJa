package modelo.usuario;

public enum PerfilUsuario
{
	Basico("Básico"),
	Premium("Premium"),
	Admin("Administrador");

	private String nome;

	PerfilUsuario(String nome)
	{
		this.nome = nome;
	}

	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public boolean equals(PerfilUsuario x)
	{
		PerfilUsuario modulo = (PerfilUsuario) x;
		return this.nome.equals(modulo.nome);
	}

}
