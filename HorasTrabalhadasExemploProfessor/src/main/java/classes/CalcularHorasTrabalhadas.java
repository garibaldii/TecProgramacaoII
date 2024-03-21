package classes;

import java.util.Calendar;
import java.util.Date;

public class CalcularHorasTrabalhadas {

    Date diaInicial;
    Date diaFinal;

    public float calcularHT(float inicio, float fim, Date diaInicial, Date diaFinal) {

        float calculoDiaTrabalhado = diaInicial.getTime() - diaFinal.getTime() / 1000 / 60 / 60;

        float horasTrabalhadas;
        
        if (calculoDiaTrabalhado == 0) {
            
            
            horasTrabalhadas = 0;
            
            //*****Adicional da Hora do almoço ********
            // Se o periodo trabalhado for maior que 6 horas
            // adicione 1.5 horas a jornada.
            if (fim - inicio > 6) {
                horasTrabalhadas += 1.5;
            }
        }
        else {
            
        }
        

        //*********************************************
        //Para o intervalo entre 08:00 e 18:00
        horasTrabalhadas
                += calculaIntervalo(inicio, fim, 0.00f, 6.00f) * 2.0f;

        horasTrabalhadas
                += calculaIntervalo(inicio, fim, 6.00f, 8.00f) * 1.5f;

        horasTrabalhadas
                += calculaIntervalo(inicio, fim, 8.00f, 18.00f) * 1.0f;
        horasTrabalhadas
                += calculaIntervalo(inicio, fim, 18.00f, 22.00f) * 1.5f;
        horasTrabalhadas
                += calculaIntervalo(inicio, fim, 22.00f, 24.00f) * 2.0f;

        return horasTrabalhadas;

    }

    private float calculaIntervalo(float inicioPeriodo, float fimPeriodo, float inicioIntervalo, float fimIntervalo) {
        //O método deve calcular a quantidade de horas entre inicio e fim dentro do intervalo definido.

        if (inicioPeriodo < fimIntervalo && fimPeriodo > inicioIntervalo) {
            // Periodo está contido dentro do Intervalo
            if (inicioPeriodo < inicioIntervalo) {
                inicioPeriodo = inicioIntervalo;
            }

            if (fimPeriodo > fimIntervalo) {
                fimPeriodo = fimIntervalo;
            }

            return (fimPeriodo - inicioPeriodo > 0) ? fimPeriodo - inicioPeriodo : 0;
        } else {//Não está dentro do intervalo
            return 0;
        }

    }

}
