package Modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Size;

@Entity
public class Email implements Serializable, Entidade {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(length = 255)
	@Size(max = 255)
	private String destinatario;
	
	@Column(length = 255)
	@Size(max = 255)
	private String assunto;
	
	@Column(length = 4097)
	@Size(max = 4097)
	private String mensagem;
	
	private int tentativaEnvio;
	
	private int tempoEspera=1;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL }, orphanRemoval = true)
	private List<DocumentoFile> documentosFile = new ArrayList<DocumentoFile>();
	
	public String getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Long getId() {
		return id;
	}

	public int getTentativaEnvio()
	{
		return tentativaEnvio;
	}

	public void setTentativaEnvio(int tentativaEnvio)
	{
		this.tentativaEnvio = tentativaEnvio;
	}

	public List<DocumentoFile> getDocumentosFile()
	{
		return documentosFile;
	}

	public void setDocumentosFile(List<DocumentoFile> documentosFile)
	{
		this.documentosFile = documentosFile;
	}

	public int getTempoEspera()
	{
		return tempoEspera;
	}

	public void setTempoEspera(int tempoEspera)
	{
		this.tempoEspera = tempoEspera;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Email other = (Email) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString()
	{
		return "Email [" + (id != null ? "id=" + id + ", " : "")
		+ (destinatario != null ? "destinatario=" + destinatario + ", " : "")
		+ (assunto != null ? "assunto=" + assunto + ", " : "") + (mensagem != null ? "mensagem=" + mensagem + ", " : "")
		+ "tentativaEnvio=" + tentativaEnvio + "]";
	}
	
}
