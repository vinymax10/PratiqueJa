package Bean.Exercicio;

import java.io.Serializable;

import Modelo.Usuario.Usuario;

public class ConfigDownload implements Serializable
{
	private static final long serialVersionUID = 1L;

	private boolean identificacao = true;

	private boolean respostas = true;

	private boolean resolucao = true;

	private Usuario usuario;

	public boolean isIdentificacao()
	{
		return identificacao;
	}

	public void setIdentificacao(boolean identificacao)
	{
		this.identificacao = identificacao;
	}

	public boolean isRespostas()
	{
		return respostas;
	}

	public void setRespostas(boolean respostas)
	{
		this.respostas = respostas;
	}

	public boolean isResolucao()
	{
		return resolucao;
	}

	public void setResolucao(boolean resolucao)
	{
		this.resolucao = resolucao;
	}

	public Usuario getUsuario()
	{
		return usuario;
	}

	public void setUsuario(Usuario usuario)
	{
		this.usuario = usuario;
	}

	@Override
	public String toString()
	{
		return "ConfigDownload:\nidentificacao: " + identificacao + "\nrespostas: " + respostas + "\nresolucao: " + resolucao + "\n"
		+ (usuario != null ? "usuario: " + usuario : "");
	}

}
