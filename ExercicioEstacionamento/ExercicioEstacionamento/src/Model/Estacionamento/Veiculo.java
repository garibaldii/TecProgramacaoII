
package Model.Estacionamento;

import java.io.Serializable;

public class Veiculo implements Serializable{
    
    private String nome, placa;
    private TipoVeiculoEnum tipo;

    public Veiculo(String nome, String placa, TipoVeiculoEnum tipo) {
        this.nome = nome;
        this.placa = placa;
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public String getPlaca() {
        return placa;
    }

    public TipoVeiculoEnum getTipo() {
        return tipo;
    }

    
    
}
