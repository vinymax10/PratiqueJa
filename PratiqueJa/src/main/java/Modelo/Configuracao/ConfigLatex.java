package Modelo.Configuracao;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import Modelo.Entidade;
import Modelo.Configuracao.Enum.SistemaOperacional;

@Entity
public class ConfigLatex implements Serializable, Entidade
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	private String endereco;
	
	private String endBackground;
	
	private String enderecoEBook;
	
	private String nome;
	
	private boolean removeDiretorios;

	private SistemaOperacional sistemaOperacional;
	
	String imagens="imagens";
	String imagensResolucao="imagensResolucao";
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public boolean isRemoveDiretorios() {
		return removeDiretorios;
	}

	public void setRemoveDiretorios(boolean removeDiretorios) {
		this.removeDiretorios = removeDiretorios;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public SistemaOperacional getSistemaOperacional()
	{
		return sistemaOperacional;
	}

	public void setSistemaOperacional(SistemaOperacional sistemaOperacional)
	{
		this.sistemaOperacional = sistemaOperacional;
	}

	public String getImagens()
	{
		return imagens;
	}

	public void setImagens(String imagens)
	{
		this.imagens = imagens;
	}

	public String getImagensResolucao()
	{
		return imagensResolucao;
	}

	public void setImagensResolucao(String imagensResolucao)
	{
		this.imagensResolucao = imagensResolucao;
	}

	public String getEndBackground()
	{
		return endBackground;
	}

	public void setEndBackground(String endBackground)
	{
		this.endBackground = endBackground;
	}

	public String getEnderecoEBook()
	{
		return enderecoEBook;
	}

	public void setEnderecoEBook(String enderecoEBook)
	{
		this.enderecoEBook = enderecoEBook;
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
		ConfigLatex other = (ConfigLatex) obj;
		return Objects.equals(id, other.id);
	}
	
}