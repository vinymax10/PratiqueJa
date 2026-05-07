package modelo.seguranca;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.javers.core.metamodel.annotation.DiffIgnore;
import org.javers.core.metamodel.annotation.ValueObject;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import modelo.Entidade;
import modelo.usuario.Usuario;

@Data
@Entity
@ValueObject
public class PasswordResetToken implements Entidade, Serializable
{
	@DiffIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	@ManyToOne(optional = true)
	@JoinColumn(nullable = true)
    private Usuario usuario;
	
    private String token;
    private LocalDateTime criadoEm;
    private LocalDateTime expiraEm;
    private LocalDateTime usadoEm;
    private boolean usado;
    private String ipSolicitacao;
    private String userAgent;

}
