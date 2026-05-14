package modelo.usuario;

import java.io.Serializable;
import java.time.LocalDate;

import org.javers.core.metamodel.annotation.DiffIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
public class Pagamento implements Serializable, Entidade
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

	@AuditLabel(value = "plano")
	private PerfilUsuario plano;

	@AuditLabel(value = "valor")
	private double valor;

	@AuditLabel(value = "data", genero = GeneroGramatical.FEMININO)
	private LocalDate data;

	@AuditLabel(value = "tipo de pagamento")
	private TipoPagamento tipoPagamento;

	@AuditLabel(value = "pago")
	private boolean pago = false;

	public void calcularValor()
	{
		switch(plano)
		{
			case Prata:
				if(tipoPagamento == TipoPagamento.Mensal)
					valor = 27.90;
				else
					valor = 176.40;
				break;

			case Ouro:
				valor = 427.90;
				break;

			default:
				break;
		}
	}
}
