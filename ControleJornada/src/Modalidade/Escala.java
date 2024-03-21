package Modalidade;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author marke
 */
public class Escala {

    private String nome;
    private String descricao;
    private Periodo[] DiasDaSemana = new Periodo[7];

    public Escala(String nome, Periodo[] DiasDaSemana) {
        this.nome = nome;
        this.DiasDaSemana = DiasDaSemana;
    }

    
    
    
    
    public String getNome() {
        return nome;
    }

    public Periodo[] getDiasDaSemana() {
        return DiasDaSemana;
    }

    
    
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDiasDaSemana(Periodo[] DiasDaSemana) {
        this.DiasDaSemana = DiasDaSemana;
    }
    
    
    

}
