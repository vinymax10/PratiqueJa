package Bean.Usuario.Filtro;

import java.io.Serializable;
import java.time.LocalDate;

public class FiltroContato implements Serializable
{
	private static final long serialVersionUID = 1L;

	private Long id;

	private String nomeUsuario;
	private String mensagem;
	private String assunto;

	private LocalDate dataInicio;
	private LocalDate dataFim;

	private Boolean respondido;

	private String email;
	
	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getNomeUsuario()
	{
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario)
	{
		this.nomeUsuario = nomeUsuario;
	}

	public String getMensagem()
	{
		return mensagem;
	}

	public void setMensagem(String mensagem)
	{
		this.mensagem = mensagem;
	}

	public String getAssunto()
	{
		return assunto;
	}

	public void setAssunto(String assunto)
	{
		this.assunto = assunto;
	}

	public LocalDate getDataInicio()
	{
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio)
	{
		this.dataInicio = dataInicio;
	}

	public LocalDate getDataFim()
	{
		return dataFim;
	}

	public void setDataFim(LocalDate dataFim)
	{
		this.dataFim = dataFim;
	}

	public Boolean getRespondido()
	{
		return respondido;
	}

	public void setRespondido(Boolean respondido)
	{
		this.respondido = respondido;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	@Override
	public String toString()
	{
		return (id != null ? "id=" + id + ", " : "") + (nomeUsuario != null ? "nomeUsuario=" + nomeUsuario + ", " : "")
		+ (mensagem != null ? "mensagem=" + mensagem + ", " : "") + (assunto != null ? "assunto=" + assunto + ", " : "")
		+ (dataInicio != null ? "dataInicio=" + dataInicio + ", " : "")
		+ (dataFim != null ? "dataFim=" + dataFim + ", " : "") + (respondido != null ? "respondido=" + respondido : "");
	}

}
