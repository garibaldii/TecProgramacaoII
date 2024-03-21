

import classes.CalcularHorasTrabalhadas;
import java.util.Calendar;
import java.util.Date;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;

public class TesteCalculaHT {
    
    /* A classe deverá possuir um método que receba duas datas, 
    início e fim da jornada e retorna o total de horas trabalhadas
    
    Para o cálculo deverão ser respeitados algumas regras:
    Dentro do período das 08:00 as 18:00 de segunda a sexta-feira é considerado horário normal.
    Fora desse período será acrescido 50% ao total de horas.
    No período das 22:00 as 06:00 será acrescido 100% ao total de horas
    Em qualquer situação se a jornada de trabalho ultrapassar 6 horas deverá ser acrescido 1,5 horas as horas totais.
    */
    
    
    
    static CalcularHorasTrabalhadas HT;
    @BeforeAll
    public static void inicializa(){
       HT= new CalcularHorasTrabalhadas();
    }
  
    
    //Calcula HT dentro do período Normal.
    @Test
    public void calculaHTPeriodoNormal(){        
        //Entra as 08:00 sai as 12:00 = 4 horas        
        assertEquals(4, HT.calcularHT(8,12));
        //Entra as 13:00 sai as 18:00 = 5 horas        
        assertEquals(5, HT.calcularHT(13,18));
        //Entra as 08:00 sai as 18:00 = 10 horas + 1,5 almoço = 11,5      
        assertEquals(11.5, HT.calcularHT(8,18));
    }
    @Test
    public void calculaHTPeridoExtra50(){
        assertEquals(5.5, HT.calcularHT(14,19));
        //Entra as 13:00 sai as 18:00 = 5 horas        
        assertEquals(7.0, HT.calcularHT(06,12));
        //Entra as 08:00 sai as 18:00 = 10 horas + 1,5 almoço = 11,5      
        assertEquals(9.5, HT.calcularHT(6,13));        
    }
    @Test
    public void calculaHTPeridoExtra100(){
        assertEquals(9.0f, HT.calcularHT(17.0f,23.0f));
        assertEquals(17.5f, HT.calcularHT(0.0f,9.0f));
        assertEquals(8.0f, HT.calcularHT(22.00f,02.00f));  
        assertEquals(19.5f, HT.calcularHT(14.00f,02.00f)); 
    }        
    
    
    
}
