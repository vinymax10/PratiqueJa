package filtro.venda;

import java.time.LocalDate;
import java.util.List;

import filtro.Filtro;
import filtro.FiltroNumero;
import filtro.PeriodoPreset;
import lombok.Data;
import lombok.EqualsAndHashCode;
import modelo.configuracao.Funcionario;
import modelo.venda.CanalAtendimento;
import modelo.venda.FormaPagamento;
import modelo.venda.venda.StatusVenda;

@Data
@EqualsAndHashCode(callSuper = false)
public class FiltroVenda extends FiltroNumero implements Filtro
{
    private String termo;
    private Funcionario vendedor;

    private StatusVenda status;

    private List<LocalDate> periodo;
    private PeriodoPreset preset;

    private CanalAtendimento canalAtendimento;
    private FormaPagamento formaPagamento;

    public void limpar()
    {
        termo = null;
        vendedor = null;
        status = null;

        canalAtendimento = null;
        formaPagamento = null;

        periodo = null;
        preset = null;
    }

    public void resetPreset()
    {
        preset = PeriodoPreset.PERSONALIZADO;
    }

    public void aplicarPreset() 
    {
        if (this.preset == null) {
            this.periodo = null;
        } 
        else if (preset != PeriodoPreset.PERSONALIZADO) {
            periodo = preset.calcularIntervalo();
        }
    }
}