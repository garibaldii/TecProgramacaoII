
package classes.estacionamento;



public class CalculoHorista implements CalculaValor{
    
    private double valorHora;
    

    public CalculoHorista(double valorHora) {
        this.valorHora = valorHora;
    }

    @Override
    public double calcular(long periodo) {
            
        return Math.ceil((periodo * valorHora) / 1000 / 60 / 60);

    }
    
}
