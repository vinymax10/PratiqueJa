package modelo.configuracao;

import java.io.Serializable;

import org.javers.core.metamodel.annotation.DiffIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import modelo.Entidade;
import modelo.auditoria.AuditLabel;

/**
 * Configuração do sistema. Persiste apenas o <b>endereço-raiz</b> (e dois
 * comportamentos: sistema operacional e limpeza de diretórios). Todos os demais
 * caminhos são derivados da raiz: {@code latex/}, {@code images/},
 * {@code pdf/}, {@code ebook/}, {@code avaliacoes/}.
 */
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Data
@Entity
public class Config implements Serializable, Entidade
{
	private static final long serialVersionUID = 1L;

	@DiffIgnore
	@Id
	@GeneratedValue
	@EqualsAndHashCode.Include
	private Long id;

	/** Endereço-raiz; todos os outros caminhos derivam dele. */
	@AuditLabel(value = "endereço")
	private String endereco;

	@AuditLabel(value = "sistema operacional")
	private SistemaOperacional sistemaOperacional;

	@AuditLabel(value = "remover diretórios")
	private boolean removeDiretorios;

	// ── Caminhos derivados da raiz (não persistidos) ──────────────────

	public String getEnderecoLatex()
	{
		return endereco + "/latex";
	}

	public String getEndBackground()
	{
		return endereco + "/images";
	}

	public String getEnderecoPdf()
	{
		return endereco + "/pdf";
	}

	public String getEnderecoEBook()
	{
		return endereco + "/ebook";
	}

	public String getEnderecoAvaliacao()
	{
		return endereco + "/avaliacoes";
	}

	// ── Nomes fixos (antes configuráveis) ─────────────────────────────

	public String getNome()
	{
		return "exercicio";
	}

	public String getImagens()
	{
		return "imagens";
	}

	public String getImagensResolucao()
	{
		return "imagensResolucao";
	}
}
