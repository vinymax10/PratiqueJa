package modelo.usuario;

import java.io.Serializable;

import org.javers.core.metamodel.annotation.DiffIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;
import modelo.Entidade;
import modelo.auditoria.AuditLabel;
import modelo.avaliacao.PerfilAvaliacao;
import modelo.publicacao.PerfilCriador;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Entity
public class Produto implements Serializable, Entidade
{
	private static final long serialVersionUID = 1L;

	@DiffIgnore
	@Id
	@GeneratedValue
	@EqualsAndHashCode.Include
	private Long id;

	@AuditLabel(value = "nome")
	private String nome;

	@AuditLabel(value = "valor")
	private double valor;

	@AuditLabel(value = "tipo de pagamento")
	private TipoPagamento tipoPagamento;

	@AuditLabel(value = "ID Hotmart")
	private String idHotmart;

	@AuditLabel(value = "link de checkout Hotmart")
	private String linkHotmart;

	@AuditLabel(value = "perfil usuário")
	private PerfilUsuario perfilUsuario;

	@AuditLabel(value = "perfil criador")
	private PerfilCriador perfilCriador;

	@AuditLabel(value = "perfil avaliação")
	private PerfilAvaliacao perfilAvaliacao;

	@AuditLabel(value = "dias de validade")
	private int diasValididade;

	@AuditLabel(value = "ativo")
	private boolean ativo = true;
}
