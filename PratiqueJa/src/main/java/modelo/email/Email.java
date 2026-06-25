package modelo.email;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.javers.core.metamodel.annotation.DiffIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import modelo.DocumentoFile;
import modelo.Entidade;
import modelo.auditoria.AuditLabel;
import modelo.auditoria.GeneroGramatical;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = { "documentosFile" })
@Data
@Entity
public class Email implements Serializable, Entidade
{
	private static final long serialVersionUID = 1L;

	@DiffIgnore
	@Id
	@GeneratedValue
	@EqualsAndHashCode.Include
	private Long id;

	@Column(length = 255)
	@Size(max = 255)
	@AuditLabel(value = "destinatário")
	private String destinatario;

	@Column(length = 255)
	@Size(max = 255)
	@AuditLabel(value = "assunto")
	private String assunto;

	@Column(columnDefinition = "TEXT")
	@Size(max = 16383)
	@AuditLabel(value = "mensagem", genero = GeneroGramatical.FEMININO)
	private String mensagem;

	@AuditLabel(value = "tentativa de envio")
	private int tentativaEnvio;

	@AuditLabel(value = "data de envio", genero = GeneroGramatical.FEMININO)
	private LocalDateTime dataEnvio;

	@AuditLabel(value = "erro")
	private String erro;

	@Enumerated(EnumType.STRING)
	@AuditLabel(value = "status")
	private StatusEmail status = StatusEmail.PENDENTE;

	@DiffIgnore
	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL }, orphanRemoval = true)
	private List<DocumentoFile> documentosFile = new ArrayList<DocumentoFile>();

	public void incrementaTetativa()
	{
		tentativaEnvio++;
	}
}
