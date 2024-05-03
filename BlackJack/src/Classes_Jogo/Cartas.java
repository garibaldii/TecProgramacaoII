package Classes_Jogo;




enum Naipes{PAUS, OUROS, COPAS, ESPADAS }

enum NumerosCartas{UM(1),DOIS(2),TRES(3),QUATRO(4),CINCO(5),SEIS(6),SETE(7),OITO(8),NOVE(9),DEZ(10),VALETE(11),DAMA(12),REI(13);

    private final int valor;

    NumerosCartas(int i){this.valor=i;}

    public int getValor(){return valor;}
}

public class Cartas {
    public final Naipes naipe;
    public final NumerosCartas numero;
    public final int valor;
    
    public Cartas(Naipes naipe, NumerosCartas numero, int valor){
        this.naipe=naipe;
        this.numero=numero;
        this.valor=valor;
    }
   
    public String getNome(){
        return "Carta "+ numero+" de "+naipe;
    }
}
