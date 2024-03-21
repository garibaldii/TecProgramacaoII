package horastrabalhadas;

public class HorasTrabalhadas {

    private float horaInicial;
    private float horaFinal;

    public float calculaHora(float inicio, float fim) {

        float jornada = 0;
        float contadorHoras = 0;
        
        if (inicio > fim){
            fim += 24;
        }

        for (float i = inicio; i < fim; i++) {
            
            //Horario Comercial - 08 - 18h
            if (i >= 8 && i < 18) {
                jornada += 1;
                contadorHoras ++;
                

            } //Hora Extra(1.5x) - 18 - 22h
            else if ( (i >= 18)  && (i < 22) || (i >= 6) && (i < 8)) {
                jornada += 1.5;
                contadorHoras ++;

                
            } //Terceiro Turno(2.0x) - 22 - 08h
            else if (i >= 22 || i < 6 ) {
                
                jornada += 2;
                contadorHoras ++;
            }

        }
        //Verifica se tem horário de almoço
        if (contadorHoras > 6.0f) {
            jornada += 1.5;
        }

        return jornada;

    }
}
