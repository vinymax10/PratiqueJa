package modelo.questao;

import java.io.InputStream;
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
public class ImagemFile implements Serializable, Entidade
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@Lob
	private Blob file;

	@Column(length = 1023)
	@Size(max = 1023)
	private String endImagem;

	@OneToOne(mappedBy = "imagemFile")
	private Paragrafo paragrafo;

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

	public String getEndImagem()
	{
		return endImagem;
	}

	public void setEndImagem(String endImagem)
	{
		this.endImagem = endImagem;
	}

	public Paragrafo getParagrafo()
	{
		return paragrafo;
	}

	public void setParagrafo(Paragrafo paragrafo)
	{
		this.paragrafo = paragrafo;
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
		ImagemFile other = (ImagemFile) obj;
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
		return "DocumentoFile [id=" + id + ", endDocumentacao=" + endImagem + "]";
	}

	public StreamedContent imagem()
	{
		if(file != null)
		{
			InputStream inStream;
			try
			{
				inStream = file.getBinaryStream();

				StreamedContent file = DefaultStreamedContent.builder().name(endImagem).contentType("image/jpeg").stream(() -> inStream).build();
				return file;

			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}

		return null;
	}
}
