package Principal;

import java.util.ArrayList;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author marke
 */
class Periodo {

    private String nome;
    private float inicio;
    private float fim;
    private float peso;

    public Periodo(String nome, float inicio, float fim, float peso) {
        this.nome = nome;
        this.inicio = inicio;
        this.fim = fim;
        this.peso = peso;

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getInicio() {
        return inicio;
    }

    public void setInicio(float inicio) {
        this.inicio = inicio;
    }

    public float getFim() {
        return fim;
    }

    public void setFim(float fim) {
        this.fim = fim;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float calculaDuracao() {
        if (inicio - fim < 0) {
            return (inicio - fim + 24);
        }

        return inicio - fim;
    }

    public List<Float> percorrePeriodo(float inicio, float fim) {
        List<Float> tempo = new ArrayList<>();//retorna uma lista com os horarios que estão entre o ínicio e o fim do periodo
        for (float i = inicio; i < fim; i++) {
            tempo.add(i);
        }
        return tempo;
    }
    
    public float getSalarioPadrao(){
        return ((fim - inicio) * peso);
    }
    


}
