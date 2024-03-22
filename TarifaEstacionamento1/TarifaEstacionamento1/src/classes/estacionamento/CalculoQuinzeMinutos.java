
package classes.estacionamento;


public class CalculoQuinzeMinutos implements CalculaValor{

    private double valorQuinzeMinutos;
    
    public CalculoQuinzeMinutos(double valorQuinzeMinutos){
        this.valorQuinzeMinutos = valorQuinzeMinutos;
    }
    
    
    @Override
    public double calcular(long periodo) {
        return Math.ceil(periodo  / 1000 / 60/ 15) * valorQuinzeMinutos;
        
    }
    
}
