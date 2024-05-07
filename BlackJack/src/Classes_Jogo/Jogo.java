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

    public void iniciarJogo(Jogador jogador) {
        int saldo = jogador.getSaldo();

        while (saldo > 0) {
            jogarRodada(jogador);

            Jogador vencedor = vencedorRodada();

            // Verifica se o saldo de um jogador é menor ou igual a zero
            if (jogadorA.getSaldo() <= 0 || jogadorB.getSaldo() <= 0) {
                break;
            }

        }

        //achar uma forma de atualizar o saldo conforme as rodadas são jogadas.
    }

    
    
    
    public void jogarRodada(Jogador jogador) {

        apostar(jogador);

        while (jogador.getValorMao() < 21) {

            if (!continuarComprando(jogador)) {

                break;
            }

        }
        atualizaSaldo(jogador);

    }

    //1º Etapa - aposta
    private int apostar(Jogador jogador) {
        Random valorAleatorio = new Random();
        jogador.setAposta(valorAleatorio.nextInt(1, 10));
        int aposta = jogador.getAposta();

        if (jogador.getSaldo() > 0) {

            if (jogador.getSaldo() - aposta < 0) {
                aposta = valorAleatorio.nextInt(1, jogador.getSaldo()); //ou teto = aposta;

                if (jogador.getSaldo() == aposta) {
                    return aposta;
                }
                return aposta;
            }
            return aposta;
        }

        return 0;

    }

    //2º Etapa - #comprar carta, #verificar se o limite estourou # calcular a porcentagem de acerto na proxima compra
    private boolean continuarComprando(Jogador jogador) {

        if (EstourouLimite(jogador)) {
            System.out.println(jogador.getNome() + " - Estourou! Pontuacao: " + jogador.getValorMao() + " perdeu aposta R$ " + jogador.getAposta());
            return false; //perdeu a vez
        }

        if (deveComprar(jogador)) {
            jogador.getListaMao().add(Baralho.getInstancia().pegarCarta());
            return true;

        }

        System.out.println(jogador.getNome() + " - Nao vou mais comprar, Pontuacao:  " + jogador.getValorMao() + " aposta  R$" + jogador.getAposta());
        return false;

    }

    private boolean EstourouLimite(Jogador jogador) {
        jogador.setValorMao(0);

        int aux = 0;
        for (Cartas carta : jogador.getListaMao()) {
            aux += carta.numero.getValor();

        }
        jogador.setValorMao(aux);

        return jogador.getValorMao() > 21 ? true : false;

    }

    private boolean deveComprar(Jogador jogador) {
        int cartasAceitas = 0;

        if (jogador.getValorMao() == 21) {
            System.out.println(jogador.getNome() + " - 21!!");
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

        return probabilidade >= 0.3;

    }

    //3º Etapa #define o vencedor da rodada , #faz a distribuicao das apostas
    public Jogador vencedorRodada() {
        int pontuacaoJogadorA = jogadorA.getValorMao();
        int pontuacaoJogadorB = jogadorB.getValorMao();

        if (pontuacaoJogadorA > 21 && pontuacaoJogadorB > 21) {
            return null;
        } else if (pontuacaoJogadorA > 21) {
            distribuirApostas(jogadorB, jogadorA);
            return jogadorB;
        } else if (pontuacaoJogadorB > 21) {
            distribuirApostas(jogadorA, jogadorB);
            return jogadorA;
        } else {
            // Nenhum jogador estourou, então comparamos as pontuações
            if (pontuacaoJogadorA > pontuacaoJogadorB) {
                distribuirApostas(jogadorA, jogadorB);
                return jogadorA;
            } else if (pontuacaoJogadorB > pontuacaoJogadorA) {
                distribuirApostas(jogadorB, jogadorA);
                return jogadorB;
            } else {
                return null; // Empate
            }
        }
    }

    public void distribuirApostas(Jogador vencedor, Jogador perdedor) {
        //]jogador perdedor tem o saldo - aposta e o vencedor tem a aposta acrescida ao saldo.
        vencedor.setSaldo(vencedor.getSaldo() + vencedor.getAposta());
        perdedor.setSaldo(perdedor.getSaldo() - perdedor.getAposta());
        System.out.println(vencedor.getNome() + " Saldo jogador Vencedor: " + vencedor.getSaldo());
        System.out.println(perdedor.getNome() + " Saldo jogador Perdedor: " + perdedor.getSaldo());

    }

    public void atualizaSaldo(Jogador jogador) {
        Jogador vencedor = vencedorRodada();
        if (vencedor != null) {
            Jogador perdedor = (vencedor == jogadorA) ? jogadorB : jogadorA;

            distribuirApostas(vencedor, perdedor);
        }
    }
}
