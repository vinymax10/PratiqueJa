package modelo.seguranca;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.javers.core.metamodel.annotation.DiffIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import modelo.Entidade;
import modelo.usuario.Usuario;

/**
 * Token de uso único do "Esqueci minha senha". O token que vai no link do e-mail nunca é
 * gravado: guardamos só o SHA-256 dele, para que um vazamento do banco não permita redefinir
 * senha de ninguém. A senha atual do usuário continua valendo até o token ser usado.
 */
@Data
@Entity
public class TokenSenha implements Serializable, Entidade
{
	private static final long serialVersionUID = 1L;

	@DiffIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;

	@DiffIgnore
	@ManyToOne(optional = false)
	private Usuario usuario;

	/** SHA-256 (hex) do token enviado no link — nunca o token em si. */
	@DiffIgnore
	@Column(length = 64, unique = true, nullable = false)
	private String hash;

	@DiffIgnore
	private LocalDateTime criacao;

	@DiffIgnore
	private LocalDateTime expiracao;

	/** Preenchido quando a senha é efetivamente trocada — garante o uso único. */
	@DiffIgnore
	private LocalDateTime uso;

	public boolean isValido()
	{
		return uso == null && expiracao != null && LocalDateTime.now().isBefore(expiracao);
	}
}
