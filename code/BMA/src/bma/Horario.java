package bma;

import GestionDeInstalaciones.Instalacion;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/**
 *
 * @author Diego
 */
public class Horario {
    private String Dia1;
    private Time Hora1;
    private String Dia2;
    private Time Hora2;
    
    private Instalacion instalacion;

    public Horario(String dia1, String dia2, String hora, String min) throws ParseException {
        Dia1 = dia1;
        Dia2 = dia2;
        
        String aux1 = hora.concat(":").concat(min);
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
        Hora1 = new Time(sdf.parse(aux1).getTime());
        Hora2 = new Time(sdf.parse(aux1).getTime());
    }

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
