package modelo.avaliacao;

import java.io.Serializable;

import org.javers.core.metamodel.annotation.DiffIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import modelo.Entidade;
import modelo.auditoria.AuditLabel;
import modelo.auditoria.GeneroGramatical;
import modelo.usuario.Imagem;
import modelo.usuario.Usuario;

/**
 * Valores-padrão de avaliação de um usuário (cabeçalho e formato). Pré-carregados
 * a cada nova solicitação para o professor não digitar tudo de novo. Não inclui os
 * assuntos/itens — esses são montados por avaliação.
 */
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = { "logoEscola" })
@Data
@Entity
public class ConfigAvaliacao implements Serializable, Entidade
{
	private static final long serialVersionUID = 1L;

	@DiffIgnore
	@Id
	@GeneratedValue
	@EqualsAndHashCode.Include
	private Long id;

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

	/** Logo da escola estampada no cabeçalho do PDF (recurso dos planos Profissional e Master). */
	@DiffIgnore
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "logoEscola_id")
	private Imagem logoEscola;
	
	@DiffIgnore
	@OneToOne
	private Usuario usuario;
}
