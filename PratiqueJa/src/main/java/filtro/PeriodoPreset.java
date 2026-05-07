package filtro;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

public enum PeriodoPreset 
{
    HOJE("Hoje"),
    ONTEM("Ontem"),
    ESTA_SEMANA("Esta Semana"),
    ULTIMOS_7_DIAS("Últimos 7 dias"),
    MES_ATUAL("Mês Atual"),
    MES_ANTERIOR("Mês Anterior"),
    PERSONALIZADO("Personalizado");

    private final String nome;

    PeriodoPreset(String label) {
        this.nome = label;
    }

    public String getNome() {
        return nome;
    }

    public List<LocalDate> calcularIntervalo() 
    {
        LocalDate hoje = LocalDate.now();
        
        return switch (this) 
        {
            case HOJE -> List.of(hoje, hoje);
            
            case ONTEM -> {
                LocalDate ontem = hoje.minusDays(1);
                yield List.of(ontem, ontem);
            }
            
            case ESTA_SEMANA -> List.of(
                hoje.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)), 
                hoje.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY)));
            
            case ULTIMOS_7_DIAS -> List.of(hoje.minusDays(7), hoje);
            
            case MES_ATUAL -> List.of(
                hoje.with(TemporalAdjusters.firstDayOfMonth()), 
                hoje.with(TemporalAdjusters.lastDayOfMonth()));
            
            case MES_ANTERIOR -> {
                LocalDate mesPassado = hoje.minusMonths(1);
                yield List.of(
                    mesPassado.with(TemporalAdjusters.firstDayOfMonth()), 
                    mesPassado.with(TemporalAdjusters.lastDayOfMonth()));
            }
            
            default -> null; // Para o caso Personalizado, mantemos o que o usuário escolheu
        };
    }
}
