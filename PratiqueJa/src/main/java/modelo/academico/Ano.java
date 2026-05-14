package modelo.academico;

import java.util.ArrayList;
import java.util.List;

import org.javers.core.metamodel.annotation.DiffIgnore;
import org.javers.core.metamodel.annotation.ValueObject;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import modelo.Config;
import modelo.Entidade;
import modelo.auditoria.AuditLabel;
import modelo.questao.Questao;

@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@ToString(exclude = { "questoes" })
@Data
@Entity
@ValueObject
public class Ano extends Config implements Entidade
{
	private static final long serialVersionUID = 1L;

	@DiffIgnore
	@Id
	@GeneratedValue
	@EqualsAndHashCode.Include
	private Long id;

	@Column(length = 255)
	@Size(max = 255)
	@AuditLabel(value = "nome")
	private String nome;

	@DiffIgnore
	@OneToMany(mappedBy = "ano")
	private List<Questao> questoes = new ArrayList<Questao>();

}
