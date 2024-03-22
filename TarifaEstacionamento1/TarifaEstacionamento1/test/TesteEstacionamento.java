

import classes.estacionamento.CalculoHorista;
import classes.estacionamento.CalculoDiaria;
import classes.estacionamento.CalculoQuinzeMinutos;
import classes.estacionamento.ContaEstacionamento;
import java.util.Calendar;
import static junit.framework.Assert.assertEquals;
import org.junit.Test;


public class TesteEstacionamento {
    
    
    
@Test
    public void calculoDiaria(){        
        Calendar inicio = Calendar.getInstance();
        inicio.set(2024, 3, 17);
        Calendar fim = Calendar.getInstance();
        fim.set(2024, 3, 18);
        
        ContaEstacionamento conta=new ContaEstacionamento(inicio.getTimeInMillis(), fim.getTimeInMillis() ,"Corolla");
        CalculoDiaria tipo = new CalculoDiaria(50);
        
        conta.setCalculo(tipo);
        assertEquals(50.0,conta.valorConta());        
    }
    
    @Test
    public void calculoHorista(){        
        Calendar inicio = Calendar.getInstance();
        inicio.set(2024, 3, 17);
        Calendar fim = Calendar.getInstance();
        fim.set(2024, 3, 18);
        
        ContaEstacionamento conta=new ContaEstacionamento(inicio.getTimeInMillis(), fim.getTimeInMillis() ,"Corolla");
        
        CalculoHorista tipo = new CalculoHorista(4);        
        
        
        
        conta.setCalculo(tipo);
        assertEquals(4d*24d,conta.valorConta());        
        
    }
    
    
    @Test
    public void calculoQuinzeMinutos(){
        Calendar inicio = Calendar.getInstance();
        inicio.set(2024, 03, 17,14,0);
        Calendar fim = Calendar.getInstance();
        fim.set(2024, 03, 17, 14, 30);
        
        ContaEstacionamento conta = new ContaEstacionamento(inicio.getTimeInMillis(), fim.getTimeInMillis(), "Corolla");
        
        System.out.println((fim.getTimeInMillis() -  inicio.getTimeInMillis()) / 1000 / 60 / 15);
        
        CalculoQuinzeMinutos tipo =   new CalculoQuinzeMinutos(1);
        
        
        conta.setCalculo(tipo);
        assertEquals(2d, conta.valorConta());
}
    
}
