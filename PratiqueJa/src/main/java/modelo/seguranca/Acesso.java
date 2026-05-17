package modelo.seguranca;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.javers.core.metamodel.annotation.DiffIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import modelo.Entidade;
import modelo.usuario.Usuario;

@Data
@Entity
public class Acesso implements Serializable, Entidade
{
	@DiffIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;

	@DiffIgnore
	@ManyToOne
	private Usuario usuario;

	@DiffIgnore
	private LocalDateTime inicio;

	@DiffIgnore
	private LocalDateTime termino;

	@DiffIgnore
	private long duracao;

	@DiffIgnore
	private String idSessao;

	@DiffIgnore
	@Enumerated(EnumType.STRING)
	private StatusAcesso status;
}
