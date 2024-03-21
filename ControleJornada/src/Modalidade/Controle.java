package Modalidade;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author marke
 */
public class Controle {
    
    Periodo periodo = new Periodo();
    
    
    
    public boolean verificaLimiteDiario(Periodo periodo){
        
        if (periodo.getInicio() - periodo.getFim() > 24){
            return false;
            
        }
        return true;
    }
    
    public float calculaHoraTrabalhada(Periodo periodo){
        return (periodo.getInicio() - periodo.getFim()) * periodo.getPeso();
        
    }
    
    
            
    
    
}
