package Modelo.Usuario;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import Modelo.Entidade;
import Modelo.Usuario.Enum.PerfilUsuario;
import Modelo.Usuario.Enum.TipoPagamento;

@Entity
public class Pagamento implements Serializable, Entidade
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	private Usuario usuario;

	private PerfilUsuario plano;

	private double valor;

	private LocalDate data;

	private TipoPagamento tipoPagamento;

	private boolean pago = false;

	public void pagoToggle()
	{
		pago = !pago;
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

	public TipoPagamento getTipoPagamento()
	{
		return tipoPagamento;
	}

	public void setTipoPagamento(TipoPagamento tipoPagamento)
	{
		this.tipoPagamento = tipoPagamento;
	}

	public boolean isPago()
	{
		return pago;
	}

	public void setPago(boolean pago)
	{
		this.pago = pago;
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
		Pagamento other = (Pagamento) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString()
	{
		return "Pagamento [id=" + id + ", usuario=" + usuario.getNome() + ", plano=" + plano + ", valor=" + valor + ", data=" + data + ", tipoPagamento="
		+ tipoPagamento + "]";
	}

	public void calcularValor()
	{
		switch(plano) {
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
