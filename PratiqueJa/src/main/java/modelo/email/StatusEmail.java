package modelo.email;

public enum StatusEmail 
{
    PENDENTE("Pendente de envio"),
    ENVIADO("Enviado com sucesso"),
    ERRO("Erro temporário no envio"),
    FALHA_DEFINITIVA("Falha definitiva após múltiplas tentativas"),
    CANCELADO("Cancelado manualmente"),
    DESCARTADO("Descartado automaticamente");

    private final String nome;

    public String getNome()
	{
		return nome;
	}

	StatusEmail(String descricao) 
    {
        this.nome = descricao;
    }

    public String getDescricao() 
    {
        return nome;
    }

	public boolean equals(StatusEmail x)
	{
		StatusEmail statusEmail = (StatusEmail) x;
		return this.nome.equals(statusEmail.nome);
	}
}
