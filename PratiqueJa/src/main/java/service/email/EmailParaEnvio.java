package service.email;

import java.util.ArrayList;
import java.util.List;

/**
 * Representação "pronta para envio" de um e-mail: todos os dados (inclusive os
 * bytes dos anexos) já foram lidos dentro de uma transação/persistence context,
 * de modo que o envio via SMTP pode ocorrer fora de qualquer transação, sem
 * risco de {@code LazyInitializationException} ou de acesso a {@code Blob}
 * desanexado.
 */
public class EmailParaEnvio
{
	private final Long id;
	private final String destinatario;
	private final String assunto;
	private final String mensagem;
	private final List<Anexo> anexos = new ArrayList<>();

	public EmailParaEnvio(Long id, String destinatario, String assunto, String mensagem)
	{
		this.id = id;
		this.destinatario = destinatario;
		this.assunto = assunto;
		this.mensagem = mensagem;
	}

	public void adicionarAnexo(String nome, byte[] dados)
	{
		anexos.add(new Anexo(nome, dados));
	}

	public Long getId()
	{
		return id;
	}

	public String getDestinatario()
	{
		return destinatario;
	}

	public String getAssunto()
	{
		return assunto;
	}

	public String getMensagem()
	{
		return mensagem;
	}

	public List<Anexo> getAnexos()
	{
		return anexos;
	}

	public static class Anexo
	{
		private final String nome;
		private final byte[] dados;

		public Anexo(String nome, byte[] dados)
		{
			this.nome = nome;
			this.dados = dados;
		}

		public String getNome()
		{
			return nome;
		}

		public byte[] getDados()
		{
			return dados;
		}
	}
}
