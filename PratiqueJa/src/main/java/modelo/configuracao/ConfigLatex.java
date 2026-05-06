package modelo.configuracao;

import java.io.Serializable;

import org.javers.core.metamodel.annotation.DiffIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import modelo.Entidade;
import modelo.auditoria.AuditLabel;
import modelo.auditoria.GeneroGramatical;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Data
@Entity
public class ConfigLatex implements Serializable, Entidade
{
	private static final long serialVersionUID = 1L;

	@DiffIgnore
	@Id
	@GeneratedValue
	@EqualsAndHashCode.Include
	private Long id;

	@AuditLabel(value = "endereço")
	private String endereco;

	@AuditLabel(value = "endereço do background")
	private String endBackground;

	@AuditLabel(value = "endereço do e-book")
	private String enderecoEBook;

	@AuditLabel(value = "nome", atributo = "nome")
	private String nome;

	@AuditLabel(value = "remover diretórios")
	private boolean removeDiretorios;

	@AuditLabel(value = "sistema operacional")
	private SistemaOperacional sistemaOperacional;

	@AuditLabel(value = "imagens", genero = GeneroGramatical.FEMININO)
	String imagens = "imagens";

	@AuditLabel(value = "imagens de resolução", genero = GeneroGramatical.FEMININO)
	String imagensResolucao = "imagensResolucao";
}
