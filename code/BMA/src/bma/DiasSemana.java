package bma;

/**
 *
 * @author Diego
 */
public enum DiasSemana {
    Lunes, Martes, Miercoles, Jueves, Viernes, Sabado, Domingo;

    public static int getNumeroDia(String dia) {
        
        int res = 0;
        
        if("Lunes".equals(dia))
            res = 0;
        else if("Martes".equals(dia))
            res = 1;
        else if("Miercoles".equals(dia))
            res = 2;
        else if("Jueves".equals(dia))
            res = 3;
        else if("Viernes".equals(dia))
            res = 4;
        else if("Sabado".equals(dia))
            res = 5;
        else if("Domingo".equals(dia))
            res = 6;
        
        
        return res;
    
    }
   
}
