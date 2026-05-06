package modelo.usuario;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.SQLException;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Size;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import modelo.Entidade;

@Entity
public class Imagem implements Serializable, Entidade
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@Lob
	private Blob file;

	@Column(length = 255)
	@Size(max = 255)
	private String endereco;

	@OneToOne(mappedBy = "imagem")
	private Usuario usuario;

	public StreamedContent getFotoStreamedContent()
	{
		return DefaultStreamedContent.builder().contentType("image/jpg").name(endereco).stream(() ->
		{
			try
			{
				return file.getBinaryStream();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			return null;
		}).build();
	}

	public Long getId()
	{
		return id;
	}

	public Blob getFile()
	{
		return file;
	}

	public void setFile(Blob file)
	{
		this.file = file;
	}

	public String getEndereco()
	{
		return endereco;
	}

	public void setEndereco(String endereco)
	{
		this.endereco = endereco;
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
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
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
		Imagem other = (Imagem) obj;
		if(id == null)
		{
			if(other.id != null)
				return false;
		}
		else if(!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return "DocumentoFile [id=" + id + ", endDocumentacao=" + endereco + "]";
	}

}
