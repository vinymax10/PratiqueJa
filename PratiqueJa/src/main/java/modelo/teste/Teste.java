package modelo.teste;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.javers.core.metamodel.annotation.DiffIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import modelo.Entidade;
import modelo.auditoria.AuditLabel;
import modelo.auditoria.GeneroGramatical;
import modelo.usuario.Usuario;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = { "testePadrao", "etapasTeste", "usuario" })
@Data
@Entity
public class Teste implements Serializable, Entidade
{
	private static final long serialVersionUID = 1L;

	@DiffIgnore
	@Id
	@GeneratedValue
	@EqualsAndHashCode.Include
	private Long id;

	@DiffIgnore
	@ManyToOne(optional = true)
	@JoinColumn(nullable = true)
	private TestePadrao testePadrao;

	@DiffIgnore
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "teste")
	private List<EtapaTeste> etapasTeste = new ArrayList<EtapaTeste>();

	@AuditLabel(value = "realizado")
	private boolean realizado = false;

	@AuditLabel(value = "repetir até passar")
	private boolean repetirAtePassar = false;

	@AuditLabel(value = "realização", genero = GeneroGramatical.FEMININO)
	private LocalDate realizacao;

	@AuditLabel(value = "nota", genero = GeneroGramatical.FEMININO)
	private double nota;

	@AuditLabel(value = "tempo")
	private double tempo;

	@AuditLabel(value = "duração", genero = GeneroGramatical.FEMININO)
	private double duracao;

	@AuditLabel(value = "nota mínima", genero = GeneroGramatical.FEMININO)
	private double notaMinima;

	@DiffIgnore
	@ManyToOne
	private Usuario usuario;

	public boolean aprovado()
	{
		return realizado && nota >= notaMinima;
	}

	public void reduzTempo()
	{
		tempo -= 1;
	}

	public String getTempoString()
	{
		int min = (int) (tempo / 60);
		int sec = (int) (tempo % 60);
		return String.format("%02d:%02d", min, sec);
	}

	public void setDuracao(double duracao)
	{
		this.duracao = duracao;
		this.tempo = duracao;
	}
}
