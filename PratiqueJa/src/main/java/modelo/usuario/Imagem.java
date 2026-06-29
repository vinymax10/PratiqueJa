package modelo.usuario;

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

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = { "file", "usuario" })
@Data
@Entity
public class Imagem implements Serializable, Entidade
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

	@Column(length = 255)
	@Size(max = 255)
	@AuditLabel(value = "endereço")
	private String endereco;

	@DiffIgnore
	@OneToOne(mappedBy = "foto")
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
}
