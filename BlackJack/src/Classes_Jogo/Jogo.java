package Classes_Jogo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Jogo {

    private Jogador jogadorA;
    private Jogador jogadorB;

    public Jogo(Jogador jogadorA, Jogador jogadorB) {
        this.jogadorA = jogadorA;
        this.jogadorB = jogadorB;
    }

    public Jogador iniciar(Jogador jogador) {
        apostar(jogador);

        while (jogador.getValorMao() < 21) {
            if (!continuarComprando(jogador)) {
                break;
            }
        }

        return null;
    }

    private boolean continuarComprando(Jogador jogador) {

        if (EstourouLimite(jogador)) {
            System.out.println(jogador.getNome() + " - Estourei, tomar no cu: " + jogador.getValorMao());
            return false; //perdeu a vez
        }

        if (deveComprar(jogador)) {

            if (EstourouLimite(jogador)) {
                System.out.println(jogador.getNome() + " - Tomei no cu, estorei o limite dessa porra: " + jogador.getValorMao());
                return false;
            } else {
                continuarComprando(jogador);
            }
            return false;
        }
        //o  que fazer? o jogador chegou em um ponto em que o limite nao estourou e a probabilidade de vir uma carta que estoure 
        //é alta, ele não irá comprar. Ele teria que passar a vez. Como funciona?
        System.out.println(jogador.getNome() + " - Vou manter, nao quero comprar e nao estourei, mao: " + jogador.getValorMao());

        return false;

    }

    private void apostar(Jogador jogador) {
        Random valorAleatorio = new Random();
        int aposta = valorAleatorio.nextInt(1, 10);

        if (jogador.getSaldo() > 0) {

            if (jogador.getSaldo() - aposta < 0) {
                aposta = valorAleatorio.nextInt(1, aposta);

                if (jogador.getSaldo() == aposta) {
                    System.out.println("All win, apostei tudo que tenho! R$" + aposta);
                    return;
                }

                System.out.println("Aposto R$" + aposta);

            }

            jogador.setSaldo(jogador.getSaldo() - aposta);

            System.out.println(jogador.getNome() + " - Aposto R$" + aposta);

        } else {

            sair();
        }

    }

    private boolean EstourouLimite(Jogador jogador) {
        jogador.setValorMao(0);

        int aux = 0;
        for (Cartas carta : jogador.getListaMao()) {
            aux += carta.numero.getValor();

        }
        jogador.setValorMao(aux);

        if (jogador.getValorMao() > 21) {
            return true;
        }

        return false;
    }

    private boolean deveComprar(Jogador jogador) {
        int cartasAceitas = 0;

        if (jogador.getValorMao() == 21) {
            System.out.println(jogador.getNome() + " - 21 caralho!!");
            return false;
        }

        int cartasRestantes = Baralho.getInstancia().getQuantidadeCartas();
        List<Cartas> cartasCarteado = new ArrayList<>(Baralho.getInstancia().getCarteado());

        for (Cartas carta : cartasCarteado) {
            if (carta.numero.getValor() + jogador.getValorMao() <= 21) {
                cartasAceitas++;
            }
        }
        double probabilidade = (double) cartasAceitas / cartasRestantes;

        if (probabilidade >= 0.30) {
            jogador.getListaMao().add(Baralho.getInstancia().pegarCarta());
            return true;

        }

        return false;

    }

    public Jogador vencedorRodada() {
        int pontuacaoJogadorA = jogadorA.getValorMao();
        int pontuacaoJogadorB = jogadorB.getValorMao();

        return (pontuacaoJogadorA > 21 && pontuacaoJogadorB <= 21) ? jogadorB
                : (pontuacaoJogadorB > 21 && pontuacaoJogadorA <= 21) ? jogadorA
                        : (pontuacaoJogadorA > pontuacaoJogadorB) ? jogadorA
                                : (pontuacaoJogadorB > pontuacaoJogadorA) ? jogadorB
                                        : null; // Empate
    }

    private void sair() {
        System.out.println("Meu saldo acabou : /");
        //lógica para sair do jogo
    }

}
