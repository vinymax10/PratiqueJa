package modelo;

import java.io.Serializable;
import java.sql.Blob;

import org.javers.core.metamodel.annotation.DiffIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import modelo.auditoria.AuditLabel;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = { "file" })
@Data
@Entity
public class DocumentoFile implements Serializable, Entidade
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
	@AuditLabel(value = "endereço do documento")
	private String endDocumentacao;

	public DocumentoFile clone()
	{
		DocumentoFile documento = new DocumentoFile();
		documento.endDocumentacao = this.endDocumentacao;
		documento.file = this.file;
		return documento;
	}
}
