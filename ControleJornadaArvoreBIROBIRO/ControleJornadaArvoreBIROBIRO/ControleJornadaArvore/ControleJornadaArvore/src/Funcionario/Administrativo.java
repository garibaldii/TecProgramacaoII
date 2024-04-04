/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Funcionario;

import Principal.PlanoDeTrabalho;

/**
 *
 * @author marke
 */
public class Administrativo implements Funcionario {

    String nome;
    int idade;
    String CPF;
    PlanoDeTrabalho planoADM;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public PlanoDeTrabalho getPlanoADM() {
        return planoADM;
    }

    public void setPlanoADM(PlanoDeTrabalho planoADM) {
        this.planoADM = planoADM;
    }
    


    @Override
    public float calculaSalario(PlanoDeTrabalho plano, float valorVariado) {
      return plano.getSalario(); 
    }

 
    
    

    
}
