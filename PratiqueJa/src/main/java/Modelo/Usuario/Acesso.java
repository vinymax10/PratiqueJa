package Modelo.Usuario;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import Modelo.Entidade;

@Entity
public class Acesso implements Serializable, Entidade
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	private Usuario usuario;

	private LocalDate data;

	private Long inicioAcesso;

	private int minutos;

	private String idSessao;

	private boolean finalizado;

	public void finalizadoToggle()
	{
		finalizado =!finalizado;
	}
	
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

	public int getMinutos()
	{
		return minutos;
	}

	public void setMinutos(int minutos)
	{
		this.minutos = minutos;
	}

	public String getIdSessao()
	{
		return idSessao;
	}

	public void setIdSessao(String idSessao)
	{
		this.idSessao = idSessao;
	}

	public boolean isFinalizado()
	{
		return finalizado;
	}

	public void setFinalizado(boolean finalizado)
	{
		this.finalizado = finalizado;
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
		Acesso other = (Acesso) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString()
	{
		return "Acesso:\n" + (id != null ? "id: " + id + "\n" : "") + (usuario != null ? "usuario: " + usuario + "\n" : "")
		+ (data != null ? "data: " + data + "\n" : "") + (inicioAcesso != null ? "inicioAcesso: " + inicioAcesso + "\n" : "") + "minutos: " + minutos + "\n"
		+ (idSessao != null ? "idSessao: " + idSessao + "\n" : "") + "finalizado: " + finalizado;
	}

	public Long getInicioAcesso()
	{
		return inicioAcesso;
	}

	public void setInicioAcesso(Long inicioAcesso)
	{
		this.inicioAcesso = inicioAcesso;
	}

}
