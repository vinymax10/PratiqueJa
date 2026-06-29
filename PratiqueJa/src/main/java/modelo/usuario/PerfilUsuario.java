package modelo.usuario;

public enum PerfilUsuario
{
	// nome, exerciciosIlimitados, questoesIlimitadas, resolucoesCompletas, downloadIlimitado
	Basico("Básico", false, false, false, false),
	Premium("Premium", true, true, true, true),
	Admin("Administrador", true, true, true, true);

	private String nome;
	private boolean exerciciosIlimitados;
	private boolean questoesIlimitadas;
	private boolean resolucoesCompletas;
	private boolean downloadIlimitado;

	PerfilUsuario(String nome, boolean exerciciosIlimitados, boolean questoesIlimitadas, boolean resolucoesCompletas, boolean downloadIlimitado)
	{
		this.nome = nome;
		this.exerciciosIlimitados = exerciciosIlimitados;
		this.questoesIlimitadas = questoesIlimitadas;
		this.resolucoesCompletas = resolucoesCompletas;
		this.downloadIlimitado = downloadIlimitado;
	}

	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	/** Acesso ilimitado aos exercícios (false = limitado ao plano Básico). */
	public boolean isExerciciosIlimitados()
	{
		return exerciciosIlimitados;
	}

	/** Acesso ilimitado às questões (false = limitado ao plano Básico). */
	public boolean isQuestoesIlimitadas()
	{
		return questoesIlimitadas;
	}

	/** Resoluções comentadas completas de exercícios e questões. */
	public boolean isResolucoesCompletas()
	{
		return resolucoesCompletas;
	}

	/** Download ilimitado de exercícios e questões em PDF. */
	public boolean isDownloadIlimitado()
	{
		return downloadIlimitado;
	}

	public boolean equals(PerfilUsuario x)
	{
		PerfilUsuario modulo = (PerfilUsuario) x;
		return this.nome.equals(modulo.nome);
	}

}
