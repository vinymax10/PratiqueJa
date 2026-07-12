package modelo;

import java.io.Serializable;

import org.javers.core.metamodel.annotation.DiffIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import modelo.auditoria.AuditLabel;

/** Anexo de e-mail: os bytes ficam só em disco ({@link #caminhoArquivo}), nunca no banco. */
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
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

	/** Caminho absoluto do arquivo em disco (diretório {@code Config#getEnderecoAnexoEmail()}). */
	@Column(length = 511)
	@Size(max = 511)
	@DiffIgnore
	private String caminhoArquivo;

	@Column(length = 255)
	@Size(max = 255)
	@AuditLabel(value = "endereço do documento")
	private String endDocumentacao;

	public DocumentoFile clone()
	{
		DocumentoFile documento = new DocumentoFile();
		documento.endDocumentacao = this.endDocumentacao;
		documento.caminhoArquivo = this.caminhoArquivo;
		return documento;
	}
}
