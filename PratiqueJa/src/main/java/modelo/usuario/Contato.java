package modelo.usuario;

import java.io.Serializable;
import java.time.LocalDate;

import org.javers.core.metamodel.annotation.DiffIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import modelo.Entidade;
import modelo.auditoria.AuditLabel;
import modelo.auditoria.GeneroGramatical;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = { "usuario" })
@Data
@Entity
public class Contato implements Serializable, Entidade
{
	private static final long serialVersionUID = 1L;

	@DiffIgnore
	@Id
	@GeneratedValue
	@EqualsAndHashCode.Include
	private Long id;

	@DiffIgnore
	@ManyToOne
	private Usuario usuario;

	@Column(length = 255)
	@Size(max = 255)
	@AuditLabel(value = "nome do usuário")
	private String nomeUsuario;

	@Size(max = 255)
	@Pattern(regexp = "[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}", message = "Email inválido.")
	@Column(length = 255)
	@AuditLabel(value = "email")
	private String email;

	@Column(length = 1023)
	@Size(max = 1023)
	@AuditLabel(value = "mensagem", genero = GeneroGramatical.FEMININO)
	private String mensagem;

	@Column(length = 255)
	@Size(max = 255)
	@AuditLabel(value = "assunto")
	private String assunto;

	@Column(length = 20)
	@Size(max = 20)
	@AuditLabel(value = "telefone")
	private String telefone;

	@AuditLabel(value = "data", genero = GeneroGramatical.FEMININO)
	private LocalDate data;

	@AuditLabel(value = "respondido")
	private boolean respondido = false;

	@AuditLabel(value = "data de resposta", genero = GeneroGramatical.FEMININO)
	private LocalDate dataResposta;
}
