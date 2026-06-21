package modelo.avaliacao;

import java.io.Serializable;
import java.time.LocalDate;
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

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = { "usuario", "itens" })
@Data
@Entity
public class PedidoAvaliacao implements Serializable, Entidade
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

	@Column(length = 10)
	@Size(max = 10)
	@AuditLabel(value = "código do lote")
	private String codigoBatch;

	@Column(length = 255)
	@Size(max = 255)
	@AuditLabel(value = "título")
	private String titulo;

	@Column(length = 255)
	@Size(max = 255)
	@AuditLabel(value = "escola")
	private String escola;

	@Column(length = 255)
	@Size(max = 255)
	@AuditLabel(value = "professor")
	private String nomeProfessor;

	@AuditLabel(value = "data da avaliação", genero = GeneroGramatical.FEMININO)
	private LocalDate dataAvaliacao;

	@Enumerated(EnumType.STRING)
	@AuditLabel(value = "tipo de gabarito")
	private TipoGabarito tipoGabarito = TipoGabarito.SOMENTE_GABARITO;

	@Enumerated(EnumType.STRING)
	@AuditLabel(value = "posição do gabarito", genero = GeneroGramatical.FEMININO)
	private PosicaoGabarito posicaoGabarito = PosicaoGabarito.AGRUPADO_NO_FINAL;

	@Enumerated(EnumType.STRING)
	@AuditLabel(value = "formato de saída")
	private FormatoSaida formatoSaida = FormatoSaida.PDF_UNICO;

	@AuditLabel(value = "quantidade")
	private int quantidade = 1;

	@Enumerated(EnumType.STRING)
	@AuditLabel(value = "status")
	private StatusPedidoAvaliacao status = StatusPedidoAvaliacao.AGUARDANDO;

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
	@OneToMany(mappedBy = "pedidoAvaliacao", cascade = CascadeType.ALL, orphanRemoval = true)
	@OrderBy("ordem ASC")
	private List<ItemPedidoAvaliacao> itens = new ArrayList<>();
}
