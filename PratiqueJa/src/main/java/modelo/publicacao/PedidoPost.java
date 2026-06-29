package modelo.publicacao;

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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import modelo.Entidade;
import modelo.auditoria.AuditLabel;
import modelo.auditoria.GeneroGramatical;
import modelo.usuario.Usuario;

/**
 * Pedido de geração de posts sob demanda. Consome créditos do plano do usuário; os posts são
 * gerados de forma assíncrona, empacotados num ZIP e disponibilizados para download (e-mail opcional).
 */
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = { "usuario", "configPost", "itens" })
@Data
@Entity
public class PedidoPost implements Serializable, Entidade
{
	private static final long serialVersionUID = 1L;

	@DiffIgnore
	@Id
	@GeneratedValue
	@EqualsAndHashCode.Include
	private Long id;

	@DiffIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	private Usuario usuario;

	/** Config de posts (branding: cores, logo, perfil) usada na geração. */
	@DiffIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	private ConfigPost configPost;

	@Column(length = 10)
	@Size(max = 10)
	@AuditLabel(value = "código do lote")
	private String codigoBatch;

	@Column(length = 255)
	@Size(max = 255)
	@AuditLabel(value = "nome")
	private String nome;

	/** Total de posts (créditos) deste pedido — soma dos itens. Base da cota mensal. */
	@AuditLabel(value = "quantidade")
	private int quantidade;

	/** Registro de consumo gerado pela programação diária (não é um lote sob demanda baixável). */
	@AuditLabel(value = "programado")
	private boolean programado = false;

	// varchar (não ENUM nativo do MySQL) para não quebrar ao acrescentar valores ao enum.
	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "varchar(30)")
	@AuditLabel(value = "status")
	private StatusPedidoPost status = StatusPedidoPost.AGUARDANDO;

	@DiffIgnore
	private double progresso;

	@AuditLabel(value = "data de solicitação", genero = GeneroGramatical.FEMININO)
	private LocalDateTime dataSolicitacao;

	@AuditLabel(value = "data de expiração", genero = GeneroGramatical.FEMININO)
	private LocalDateTime dataExpiracao;

	@Column(length = 511)
	@Size(max = 511)
	@DiffIgnore
	private String caminhoArquivo;

	@Column(length = 255)
	@Size(max = 255)
	@DiffIgnore
	private String nomeDownload;

	@DiffIgnore
	@OneToMany(mappedBy = "pedidoPost", cascade = CascadeType.ALL, orphanRemoval = true)
	@OrderBy("ordem ASC")
	private List<ItemPedidoPost> itens = new ArrayList<>();
}
