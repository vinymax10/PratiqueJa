package Modelo.Usuario;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import Modelo.Entidade;

@Entity
public class ControleAcesso implements Serializable, Entidade
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	private Usuario usuario;

	private LocalDate data;

	private int numDownloadExercicio;

	private int numDownloadQuestao;
	
	private int numDownloadQuestaoMassa;
	
	private int numDownloadMassa;
	
	private int numResolucaoExercicio;

	private int numResolucaoQuestao;
	
	private int numQuestaoFeita;
	
	public void limpar()
	{
		data=LocalDate.now();
		numDownloadExercicio=0;
		numDownloadQuestao=0;
		numResolucaoExercicio=0;
		numResolucaoQuestao=0;
		numQuestaoFeita=0;
		numDownloadQuestaoMassa=0;
		numDownloadMassa=0;
	}
	
	public void incrementaDownloadExercicio()
	{
		numDownloadExercicio++;
	}
	
	public void incrementaDownloadQuestao()
	{
		numDownloadQuestao++;
	}
	
	public void incrementaDownloadQuestaoMassa()
	{
		numDownloadQuestaoMassa++;
	}
	
	public void incrementaDownloadMassa()
	{
		numDownloadMassa++;
	}
	
	public void incrementaResolucaoExercicio()
	{
		numResolucaoExercicio++;
	}
	
	public void incrementaResolucaoQuestao()
	{
		numResolucaoQuestao++;
	}
	
	public void incrementaQuestaoFeita()
	{
		numQuestaoFeita++;
	}
	
	public Long getId()
	{
		return id;
	}

	public Usuario getUsuario()
	{
		return usuario;
	}

	public void setUsuario(Usuario usuario)
	{
		this.usuario = usuario;
	}

	public LocalDate getData()
	{
		return data;
	}

	public void setData(LocalDate data)
	{
		this.data = data;
	}

	public int getNumDownloadExercicio()
	{
		return numDownloadExercicio;
	}

	public void setNumDownloadExercicio(int numDownloadExercicio)
	{
		this.numDownloadExercicio = numDownloadExercicio;
	}

	public int getNumDownloadQuestao()
	{
		return numDownloadQuestao;
	}

	public void setNumDownloadQuestao(int numDownloadQuestao)
	{
		this.numDownloadQuestao = numDownloadQuestao;
	}

	public int getNumResolucaoExercicio()
	{
		return numResolucaoExercicio;
	}

	public void setNumResolucaoExercicio(int numResolucaoExercicio)
	{
		this.numResolucaoExercicio = numResolucaoExercicio;
	}

	public void setId(Long id)
	{
		this.id = id;
	}
	
	public int getNumResolucaoQuestao()
	{
		return numResolucaoQuestao;
	}

	public void setNumResolucaoQuestao(int numResolucaoQuestao)
	{
		this.numResolucaoQuestao = numResolucaoQuestao;
	}
	
	public int getNumQuestaoFeita()
	{
		return numQuestaoFeita;
	}

	public void setNumQuestaoFeita(int numQuestaoFeita)
	{
		this.numQuestaoFeita = numQuestaoFeita;
	}

	public int getNumDownloadQuestaoMassa()
	{
		return numDownloadQuestaoMassa;
	}

	public void setNumDownloadQuestaoMassa(int numDownloadQuestaoMassa)
	{
		this.numDownloadQuestaoMassa = numDownloadQuestaoMassa;
	}

	public int getNumDownloadMassa()
	{
		return numDownloadMassa;
	}

	public void setNumDownloadMassa(int numDownloadMassa)
	{
		this.numDownloadMassa = numDownloadMassa;
	}

	@Override
	public String toString()
	{
		return (id != null ? "id=" + id + ", " : "") + (usuario != null ? "usuario=" + usuario + ", " : "")
		+ (data != null ? "data=" + data + ", " : "") + "numDownloadExercicio=" + numDownloadExercicio
		+ ", numDownloadQuestao=" + numDownloadQuestao + ", numResolucaoExercicio=" + numResolucaoExercicio;
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj)
	{
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		ControleAcesso other = (ControleAcesso) obj;
		return Objects.equals(id, other.id);
	}

}
