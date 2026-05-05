package Bean.Usuario.Filtro;

import java.io.Serializable;
import java.time.LocalDate;

import Modelo.Usuario.Enum.PerfilUsuario;
import Modelo.Usuario.Enum.TipoPagamento;

public class FiltroPagamento implements Serializable
{
	private static final long serialVersionUID = 1L;

	private Long id;

	private String nomeUsuario;
	
	private PerfilUsuario plano;

	private double valor;

	private LocalDate dataInicio;
	private LocalDate dataFim;

	private TipoPagamento tipoPagamento;

	private Boolean pago;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getNomeUsuario()
	{
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario)
	{
		this.nomeUsuario = nomeUsuario;
	}

	public PerfilUsuario getPlano()
	{
		return plano;
	}

	public void setPlano(PerfilUsuario plano)
	{
		this.plano = plano;
	}

	public double getValor()
	{
		return valor;
	}

	public void setValor(double valor)
	{
		this.valor = valor;
	}

	public LocalDate getDataInicio()
	{
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio)
	{
		this.dataInicio = dataInicio;
	}

	public LocalDate getDataFim()
	{
		return dataFim;
	}

	public void setDataFim(LocalDate dataFim)
	{
		this.dataFim = dataFim;
	}

	public TipoPagamento getTipoPagamento()
	{
		return tipoPagamento;
	}

	public void setTipoPagamento(TipoPagamento tipoPagamento)
	{
		this.tipoPagamento = tipoPagamento;
	}

	public Boolean getPago()
	{
		return pago;
	}

	public void setPago(Boolean pago)
	{
		this.pago = pago;
	}

}
