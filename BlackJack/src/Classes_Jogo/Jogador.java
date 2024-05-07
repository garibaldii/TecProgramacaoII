package Classes_Jogo;

import java.util.ArrayList;
import java.util.List;

public class Jogador {

    private String nome;
    private List<Cartas> listaMao;
    private int valorMao;
    private int saldo;
    private int aposta;

    
    
//Construtor
    public Jogador(String nome) {
        this.nome = nome;
        this.saldo = 100; //Todos os jogadores iniciam com 100 reais de saldo para apostar.
        this.valorMao = 0;
        this.listaMao = new ArrayList<>();

        //começar com duas cartas
        this.listaMao.add(Baralho.getInstancia().pegarCarta());
        this.listaMao.add(Baralho.getInstancia().pegarCarta());
    }

//Getters
    public String getNome() {
        return this.nome;
    }

    public List<Cartas> getListaMao() {
        return this.listaMao;
    }

    public int getValorMao() {
        return this.valorMao;
    }

    public int getSaldo() {
        return this.saldo;
    }

    public int getAposta() {
        return this.aposta;
    }

    //Setters  
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setListaMao(List<Cartas> listaMao) {
        this.listaMao = listaMao;
    }

    public void setValorMao(int valorMao) {
        this.valorMao = valorMao;
    }

    public void setSaldo(int novoSaldo) {
        this.saldo = novoSaldo;
    }

    public void setAposta(int novaAposta) {
        this.aposta = novaAposta;
    }

}
