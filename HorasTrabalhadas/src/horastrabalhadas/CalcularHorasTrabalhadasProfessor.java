package horastrabalhadas;

import java.util.Calendar;



public class CalcularHorasTrabalhadasProfessor {

    public float calcularHT(Calendar inicio, Calendar fim) {
        float horasTrabalhadas=0;
        
        int anoCorrente;
        int mesCorrente;
        int diaCorrente;
        int diaAnoInicio=inicio.get(Calendar.DAY_OF_YEAR);
        int diaAnoFim=fim.get(Calendar.DAY_OF_YEAR);
        
        Calendar aux=Calendar.getInstance();
        aux.set(inicio.get(Calendar.YEAR), inicio.get(Calendar.MONTH), inicio.get(Calendar.DAY_OF_MONTH));
        
         //*****Adicional da Hora do almoço ********
           // Se o periodo trabalhado for maior que 6 horas
           // adicione 1.5 horas a jornada.
           if(calculaHora(inicio, fim) >6)horasTrabalhadas+=1.5;
           //*********************************************
           
        //Realiza o calculo de todos os periodos para cada novo dia
        for(int i=diaAnoInicio;i<=diaAnoFim;i++){
            
            aux.set(Calendar.DAY_OF_YEAR, i);
            
            anoCorrente=aux.get(Calendar.YEAR);
            mesCorrente=aux.get(Calendar.MONTH);
            diaCorrente=aux.get(Calendar.DAY_OF_MONTH);


            Calendar c1= Calendar.getInstance();
            c1.set(anoCorrente, mesCorrente, diaCorrente, 0, 0);
            Calendar c2= Calendar.getInstance();
            c2.set(anoCorrente, mesCorrente, diaCorrente, 6, 0);
           horasTrabalhadas += 
                   calculaIntervalo(inicio,fim,c1,c2)*2.0f;
           
           c1.set(anoCorrente, mesCorrente, diaCorrente, 6, 0);
           c2.set(anoCorrente, mesCorrente, diaCorrente, 8, 0);
           horasTrabalhadas += 
                   calculaIntervalo(inicio,fim,c1,c2)*1.5f;

           c1.set(anoCorrente, mesCorrente, diaCorrente, 8, 0);
           c2.set(anoCorrente, mesCorrente, diaCorrente, 18, 0);
           horasTrabalhadas+=
                   calculaIntervalo(inicio,fim,c1,c2)*1.0f;

           c1.set(anoCorrente, mesCorrente, diaCorrente, 18, 0);
           c2.set(anoCorrente, mesCorrente, diaCorrente, 22, 0);
           horasTrabalhadas += 
                   calculaIntervalo(inicio,fim,c1,c2)*1.5f;

           c1.set(anoCorrente, mesCorrente, diaCorrente, 22, 0);
           c2.set(anoCorrente, mesCorrente, diaCorrente, 24, 0);
           horasTrabalhadas += 
                   calculaIntervalo(inicio,fim,c1,c2)*2.0f;
        }
             
        return horasTrabalhadas;
    }
    
    private float calculaIntervalo
        (Calendar inicioPeriodo, Calendar fimPeriodo, Calendar inicioIntervalo, Calendar fimIntervalo){
        //O método deve calcular a quantidade de horas entre inicio e fim dentro do intervalo definido.
        
        if(inicioPeriodo.getTimeInMillis() < fimIntervalo.getTimeInMillis() && fimPeriodo.getTimeInMillis()> inicioIntervalo.getTimeInMillis()){
            // Periodo está contido dentro do Intervalo
            if(inicioPeriodo.getTimeInMillis()<inicioIntervalo.getTimeInMillis()) 
                inicioPeriodo=inicioIntervalo;     
            
            if(fimPeriodo.getTimeInMillis()>fimIntervalo.getTimeInMillis()) 
                fimPeriodo=fimIntervalo;
            
            return (calculaHora(inicioPeriodo,fimPeriodo)  > 0)? calculaHora(inicioPeriodo,fimPeriodo):0;
        }
        else{//Não está dentro do intervalo
             return 0;
        }
    }
    
    private float calculaHora(Calendar inicio,Calendar fim){
        float periodoMillis=fim.getTimeInMillis()-inicio.getTimeInMillis();
        return Math.round((periodoMillis)/1000/60/60);
    }
        
    
}
