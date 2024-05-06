package Classes_Jogo;

//Implemente um jogo de 21 (black jack), utilizando o padrão de projeto Singleton. Onde cada jogador (controlado pelo computador) 
//será executado em uma thread diferente, com o baralho e a mesa sendo uma instância única para todos os jogadores. 
//• Regras do jogo: 
//• Todos os jogadores iniciam com 100 reais de saldo para apostar. - OK
//• Os jogadores iniciam a rodada com 2 cartas, devendo nesse momento apostar um valor (R$ 1,00 à R$ 10,00), 
//podendo ou não pegar mais cartas após apostar. 
//• Se o jogador ultrapassar 21 pontos, ele deverá informar que está fora e irá perder o valor apostado.
//• A cada rodada o jogador que tiver o valor das cartas mais próximas de 21 pontos ganha a rodada,recebendo o valor apostado.
//Os demais jogadores perdem a metade do valor apostado. 
//• O valor do saldo é incrementado ou decrementado, dependendo se o jogador ganhou ou perdeu.
//• O jogar deverá sair do jogo quanto o dinheiro acabar.
//• O jogo acaba quando sobrar um jogador ou após um número arbitrário de rodadas.
public class Mesa {

    public static void main(String[] args) {

        Jogador matheus = new Jogador("Matheus");
        Jogador erick = new Jogador("Erick");

        Jogo jogo = new Jogo(matheus, erick);

        Thread threadJogador1 = new Thread(() -> { //a classe Thread implementa Runnable que possui apenas um método abstrato run(). então neste caso. 
            //estamos sobreescrevendo o método run() através de uma lambda para adaptarmos a nossa necessidade.

            jogo.iniciar(matheus);

        });

        Thread threadJogador2 = new Thread(() -> { //a classe Thread implementa Runnable que possui apenas um método abstrato run(). então neste caso. 
            //estamos sobreescrevendo o método run() através de uma lambda para adaptarmos a nossa necessidade.

            jogo.iniciar(erick);

        });

        threadJogador1.start();
        threadJogador2.start();
        
        
        try {
            threadJogador1.join(); // Espera a thread do Matheus terminar
            threadJogador2.join(); // Espera a thread do Erick terminar
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        Jogador vencedor = jogo.vencedorRodada();
        System.out.println("E o vencedor da rodada eh o " + vencedor.getNome());

    }

}
