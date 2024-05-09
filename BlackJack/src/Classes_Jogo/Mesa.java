package Classes_Jogo;

//Implemente um jogo de 21 (black jack), utilizando o padrão de projeto Singleton. Onde cada jogador (controlado pelo computador) 
import java.util.concurrent.CyclicBarrier;

//será executado em uma thread diferente, com o baralho e a mesa sendo uma instância única para todos os jogadores. 
//• Regras do jogo: 
//• Todos os jogadores iniciam com 100 reais de saldo para apostar. - OK
//• Os jogadores iniciam a rodada com 2 cartas, devendo nesse momento apostar um valor (R$ 1,00 à R$ 10,00),  - OK
//podendo ou não pegar mais cartas após apostar.  - OK
//• Se o jogador ultrapassar 21 pontos, ele deverá informar que está fora e irá perder o valor apostado. - OK 
//• A cada rodada o jogador que tiver o valor das cartas mais próximas de 21 pontos ganha a rodada,recebendo o valor apostado. - OK
//Os demais jogadores perdem a metade do valor apostado.  - OK
//• O valor do saldo é incrementado ou decrementado, dependendo se o jogador ganhou ou perdeu. - OK
//• O jogador deverá sair do jogo quanto o dinheiro acabar.
//• O jogo acaba quando sobrar um jogador ou após um número arbitrário de rodadas.
//
public class Mesa {

    public static void main(String[] args) {

        Jogador matheus = new Jogador("Matheus");
        Jogador erick = new Jogador("Erick");

        Jogo jogo = new Jogo(matheus, erick);


        Thread threadJogador1 = new Thread(() -> { //a classe Thread implementa Runnable que possui apenas um método abstrato run(). então neste caso. 
            //estamos sobreescrevendo o método run() através de uma lambda para adaptarmos a nossa necessidade.

            jogo.iniciarJogo(matheus);

        });

        Thread threadJogador2 = new Thread(() -> {

            jogo.iniciarJogo(erick);

        });

        threadJogador2.start();
        threadJogador1.start();

        try {
            threadJogador1.join();
            threadJogador2.join();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
