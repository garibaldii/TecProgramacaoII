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
public class CalculoDiaria implements CalculaValor{

    private double valorDiaria;
    
    public CalculoDiaria(double valorDiaria) {
     
        this.valorDiaria = valorDiaria;
        
    }
    
    

    @Override
    public double calcular(long periodo) {
           return Math.ceil((valorDiaria * periodo) / 1000 / 60 / 60 / 24);
    }
    
}
