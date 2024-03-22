/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.estacionamento;

/**
 *
 * @author Alunos
 */
public class ContaEstacionamento {

    private CalculaValor calculo;


    private long inicio;
    private long fim;

    public ContaEstacionamento(long inicio, long fim, String tipo) {
        this.inicio = inicio;
        this.fim = fim;


    }

    public double valorConta() {
        return calculo.calcular(fim - inicio);
    }

    public void setCalculo(CalculoQuinzeMinutos tipo){
        this.calculo = tipo;
    }
    
    public void setCalculo(CalculoDiaria tipo) {
        this.calculo = tipo;
    }

    public void setCalculo(CalculoHorista tipo) {
        this.calculo = tipo;
    }

}
