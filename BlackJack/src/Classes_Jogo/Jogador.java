package Classes_Jogo;

import java.util.ArrayList;
import java.util.Random;
import java.util.List;

public class Jogador {

    private String nome;
    private List<Cartas> listaMao;
    private int valorMao;
    private int saldo;

    public Jogador(String nome) {
        this.nome = nome;
        this.saldo = 100; //Todos os jogadores iniciam com 100 reais de saldo para apostar.
        this.valorMao = 0;
        this.listaMao = new ArrayList<>();

        //começar com duas cartas
        this.listaMao.add(Baralho.getInstancia().pegarCarta());
        this.listaMao.add(Baralho.getInstancia().pegarCarta());
    }

    
    
    
//Controller    
    
    //fazer outro while aqui para saber se um jogador foi eliminado (saldo < 100) ou não.
    public Jogador jogar() {
        apostar();

        while (valorMao < 21) {
            if (!continuarComprando()) {
                break;
            }
        }

        return this;
    }


    
    








    
//Regra de negócio    
    private boolean continuarComprando() {

        if (EstourouLimite()) {
            System.out.println("Estourei, tomar no cu: " + valorMao);
            return false; //perdeu a vez
        }

        if (deveComprar()) {
            

            if (EstourouLimite()) {
                System.out.println("Tomei no cu, estorei o limite dessa porra: " + valorMao);
                return false;
            } 
            else {
                continuarComprando();
            }
            return false;
        }
        //o  que fazer? o jogador chegou em um ponto em que o limite nao estourou e a probabilidade de vir uma carta que estoure 
        //é alta, ele não irá comprar. Ele teria que passar a vez. Como funciona?
        System.out.println("Vou manter, nao quero comprar e nao estourei, mao: " + valorMao);

        return false;

    }

    private boolean EstourouLimite() {
        valorMao = 0;
        for (Cartas carta : listaMao) {
            valorMao += carta.numero.getValor();

        }

        if (valorMao > 21) {
            return true;
        }

        return false;
    }

    private boolean deveComprar() {
        int cartasAceitas = 0;

        if (valorMao == 21) {
            System.out.println("21 caralho!!");
            return false;
        }

        int cartasRestantes = Baralho.getInstancia().getQuantidadeCartas();

        for (Cartas carta : Baralho.getInstancia().getCarteado()) {
            if (carta.numero.getValor() + valorMao <= 21) {
                cartasAceitas++;
            }
        }
        double probabilidade = (double) cartasAceitas / cartasRestantes;

        if (probabilidade >= 0.30) {
            listaMao.add(Baralho.getInstancia().pegarCarta());
            return true;

        }

        return false;

    }

    private void apostar() {
        Random valorAleatorio = new Random();
        int aposta = valorAleatorio.nextInt(1, 10);

        if (saldo > 0) {

            if (saldo - aposta < 0) {
                aposta = valorAleatorio.nextInt(1, aposta);

                if (saldo == aposta) {
                    System.out.println("All win, apostei tudo que tenho! R$" + aposta);
                    return;
                }

                System.out.println("Aposto R$" + aposta);

            }

            saldo -= aposta;

            System.out.println(nome + " - Aposto R$" + aposta);

        } else {

            sair();
        }

    }

    private void sair() {
        System.out.println("Meu saldo acabou : /");
        //lógica para sair do jogo
    }






//Model
public String getNome(){
    return this.nome ;
}

public int getValorMao(){
    return this.valorMao;
}

public int getSaldo(){
    return this.saldo;
}

//public int getAposta(){
//    return this.aposta;
//}




}
