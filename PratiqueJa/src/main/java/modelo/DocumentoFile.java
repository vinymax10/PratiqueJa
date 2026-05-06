package modelo;

import java.io.Serializable;
import java.sql.Blob;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.Size;


@Entity
public class DocumentoFile implements Serializable, Entidade
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@Lob
	private Blob file;

	@Column(length = 255)
	@Size(max = 255)
	private String endDocumentacao;

	public DocumentoFile clone()
	{
		DocumentoFile documento=new DocumentoFile();
		documento.endDocumentacao=this.endDocumentacao;
		documento.file=this.file;
		return documento;
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

	public String getEndDocumentacao()
	{
		return endDocumentacao;
	}

	public void setEndDocumentacao(String endDocumentacao)
	{
		this.endDocumentacao = endDocumentacao;
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
		DocumentoFile other = (DocumentoFile) obj;
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
		return "DocumentoFile [id=" + id + ", endDocumentacao=" + endDocumentacao + "]";
	}

}
