
import horastrabalhadas.CalcularHorasTrabalhadasProfessor;
import java.util.Calendar;
import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;


public class TesteCalculaHTProfessor {
    
    /* A classe deverá possuir um método que receba duas datas, 
    início e fim da jornada e retorna o total de horas trabalhadas
    
    Para o cálculo deverão ser respeitados algumas regras:
    Dentro do período das 08:00 as 18:00 de segunda a sexta-feira é considerado horário normal.
    Fora desse período será acrescido 50% ao total de horas.
    No período das 22:00 as 06:00 será acrescido 100% ao total de horas
    Em qualquer situação se a jornada de trabalho ultrapassar 6 horas deverá ser acrescido 1,5 horas as horas totais.
    */
    static CalcularHorasTrabalhadasProfessor HT;
    @Before
    public static void inicializa(){
       HT= new CalcularHorasTrabalhadasProfessor();
    }
  
    
    //Calcula HT dentro do período Normal.
    @Test
    public void calculaHTPeriodoNormal(){        
        Calendar c1= Calendar.getInstance();        
        Calendar c2= Calendar.getInstance();
        
        
        //Entra as 08:00 sai as 12:00 = 4 horas     
        c1.set(2024, 3, 7, 8, 0);
        c2.set(2024, 3, 7, 12, 0);
        assertEquals(4, HT.calcularHT(c1,c2));
        //Entra as 13:00 sai as 18:00 = 5 horas        
        c1.set(2024, 3, 7, 13, 0);
        c2.set(2024, 3, 7, 18, 0);
        assertEquals(5, HT.calcularHT(c1,c2));
        //Entra as 08:00 sai as 18:00 = 10 horas + 1,5 almoço = 11,5  
        c1.set(2024, 3, 7, 8, 0);
        c2.set(2024, 3, 7, 18, 0);
        assertEquals(11.5f, HT.calcularHT(c1,c2));
    }
    @Test
    public void calculaHTPeridoExtra50(){
        Calendar c1= Calendar.getInstance();        
        Calendar c2= Calendar.getInstance();
        
        //Entra as 14:00 sai as 19:00 = 4 horas + 1.5 HE = 5.5h
        c1.set(2024, 3, 7, 14, 0);
        c2.set(2024, 3, 7, 19, 0);
        assertEquals(5.5f, HT.calcularHT(c1,c2));
        
        //Entra as 06:00 sai as 12:00 = 7 horas        
        c1.set(2024, 3, 7,6, 0);
        c2.set(2024, 3, 7, 12, 0);
        assertEquals(7.0f, HT.calcularHT(c1,c2));
        
        //Entra as 06:00 sai as 13:00 = 9,5   
        c1.set(2024, 3, 7,6, 0);
        c2.set(2024, 3, 7, 13, 0);
        assertEquals(9.5f, HT.calcularHT(c1,c2));    
    }
    @Test
    public void calculaHTPeridoExtra100(){
        
        Calendar c1= Calendar.getInstance();        
        Calendar c2= Calendar.getInstance();
        
        //Entra as 17:00 sai as 23:00 =  9h
        c1.set(2024, 3, 7, 17, 0);
        c2.set(2024, 3, 7, 23, 0);
        assertEquals(9.0f, HT.calcularHT(c1,c2));
        
        //Entra as 00:00 sai as 09:00 =  17,5h
        c1.set(2024, 3, 7, 00, 0);
        c2.set(2024, 3, 7, 9, 0);
        assertEquals(17.5f, HT.calcularHT(c1,c2));
        
        //Entra as 22:00 sai as 02:00 =  8h
        c1.set(2024, 3, 7, 22, 0);
        c2.set(2024, 3, 8, 2, 0);
        assertEquals(8.0f, HT.calcularHT(c1,c2));  
        
        //Entra as 14:00 sai as 2:00 =  19,5h
        c1.set(2024, 3, 7, 14, 0);
        c2.set(2024, 3, 8, 2, 0);
        assertEquals(19.5f, HT.calcularHT(c1,c2));
        
       //Entra as 07/03 as 00:00 sai 09/03 as 00:00 =  35*2 + 1.5h = 36.5
        c1.set(2024, 3, 7, 00, 0);
        c2.set(2024, 3, 9, 00, 0);
        assertEquals(71.5f, HT.calcularHT(c1,c2)); 
    }        
  
}