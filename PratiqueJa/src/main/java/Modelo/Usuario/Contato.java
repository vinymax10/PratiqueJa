package Modelo.Usuario;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import Modelo.Entidade;

@Entity
public class Contato implements Serializable, Entidade
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	private Usuario usuario;
	
	@Column(length = 255)
	@Size(max = 255)
	private String nomeUsuario;
	
	@Size(max = 255)
	@Pattern(regexp = "[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}", message = "Email inválido.")
	@Column(length = 255)
	private String email;

	@Column(length = 1023)
	@Size(max = 1023)
	private String mensagem;

	@Column(length = 255)
	@Size(max = 255)
	private String assunto;

	private LocalDate data;

	private boolean respondido = false;

	public Long getId()
	{
		return id;
	}

	public Usuario getUsuario()
	{
		return usuario;
	}

	public void setUsuario(Usuario usuario)
	{
		this.usuario = usuario;
	}

	public LocalDate getData()
	{
		return data;
	}
	
	public void setData(LocalDate data)
	{
		this.data = data;
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

	public boolean isRespondido()
	{
		return respondido;
	}

	public void setRespondido(boolean respondido)
	{
		this.respondido = respondido;
	}

	public String getNomeUsuario()
	{
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario)
	{
		this.nomeUsuario = nomeUsuario;
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
	public int hashCode()
	{
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj)
	{
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		Contato other = (Contato) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString()
	{
		return (id != null ? "id=" + id + ", " : "") + (usuario != null ? "usuario=" + usuario.getFirstNome() + ", " : "")
		+ (nomeUsuario != null ? "nomeUsuario=" + nomeUsuario + ", " : "")
		+ (mensagem != null ? "mensagem=" + mensagem + ", " : "") + (assunto != null ? "assunto=" + assunto + ", " : "")
		+ (data != null ? "data=" + data + ", " : "") + "respondido=" + respondido;
	}

	

}
