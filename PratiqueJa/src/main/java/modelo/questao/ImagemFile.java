package modelo.questao;

import java.io.InputStream;
import java.io.Serializable;
import java.sql.Blob;
import java.sql.SQLException;

import org.javers.core.metamodel.annotation.DiffIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import modelo.Entidade;
import modelo.auditoria.AuditLabel;
import modelo.auditoria.GeneroGramatical;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = { "file", "paragrafo" })
@Data
@Entity
public class ImagemFile implements Serializable, Entidade
{
	private static final long serialVersionUID = 1L;

	@DiffIgnore
	@Id
	@GeneratedValue
	@EqualsAndHashCode.Include
	private Long id;

	@DiffIgnore
	@Lob
	private Blob file;

	@Column(length = 1023)
	@Size(max = 1023)
	@AuditLabel(value = "endereço da imagem", genero = GeneroGramatical.FEMININO)
	private String endImagem;

	@DiffIgnore
	@OneToOne(mappedBy = "imagemFile")
	private Paragrafo paragrafo;

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
