package bma;

import GestionDeInstalaciones.Instalacion;
import java.sql.Time;

/**
 *
 * @author Diego
 */
public class Horario {
    private DiasSemana Dia1;
    private Time Hora1;
    private DiasSemana Dia2;
    private Time Hora2;
    
    private Instalacion instalacion;

    public String getDia1() {
        return Dia1.toString();
    }

    public String getDia2() {
        return Dia2.toString();
    }

    public Time getHora1() {
        return Hora1;
    }

    public Time getHora2() {
        return Hora2;
    }
    
    
}
