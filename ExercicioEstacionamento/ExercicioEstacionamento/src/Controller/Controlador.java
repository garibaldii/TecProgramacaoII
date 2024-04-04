package Controller;

import Model.DAO.*;
import Model.Estacionamento.*;
import View.TelaPrincipal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class Controlador {

    private List<ContaVeiculo> listaVeiculos;
    private Thread t0, t1;
    private PersistenciaDados DAO;

    public Controlador() {
        listaVeiculos = new ArrayList<ContaVeiculo>();
        DAO = new PersistenciaDados();
    }

    public void addContaVeiculo(String nome, String placa, TipoVeiculoEnum tipo) throws Exception {
        //listaVeiculos.add(new ContaVeiculo(Calendar.getInstance().getTimeInMillis(), new Veiculo(nome, placa, tipo)));
        listaVeiculos.add(new ContaVeiculo(Calendar.getInstance().getTimeInMillis() - (1000 * 60 * 60 * 2), new Veiculo(nome, placa, tipo)));

    }

    public String[][] listaVeiculosCadastrados() throws Exception {

        String[][] aux = new String[listaVeiculos.size()][6];
        ContaVeiculo conta;
        Date dataAux;
        for (int i = 0; i < listaVeiculos.size(); i++) {
            conta = (ContaVeiculo) listaVeiculos.get(i);
            aux[i][0] = conta.getVeiculo().getNome();
            aux[i][1] = conta.getVeiculo().getPlaca();
            aux[i][2] = conta.getVeiculo().getTipo().toString();
            dataAux = new Date(conta.getInicio());
            aux[i][3] = dataAux.toString();
            dataAux = new Date(conta.getFim());
            aux[i][4] = dataAux.toString();
            aux[i][5] = conta.getStatus().toString();
        }
        return aux;

    }

    public void finalizarConta(Object placaVeiculo) throws Exception {
        // Altera o status para fechado e salva o registro.
        //Se valor da conta for zero retorna um erro.
        //Necessário puxar a conta e trocar o status para FECHADO
        
        //ideia 1 - percorrer a lista de veículos cadastrados
            //verificar a igualdade das placas do índice com o parâmetro
            //se igual, conta.setStatus(StatusConta.FECHADO);
            //se não, passa.
        
            String placa;
            for (ContaVeiculo conta: listaVeiculos){
                placa = conta.getVeiculo().getPlaca();
                
               // if(conta.valorConta().equals(0)){
                   // System.out.println("Erro");
                //}
                
                if (placa.equals(placaVeiculo)){
                    conta.setStatus(StatusConta.FECHADO);
                }
                
            }
            
        
        //Se não for possivel registra no BD, salve um backup local da listaVeiculos;
        //Utilize o objeto DAO
    }

    public String calculoTipo(String placaVeiculo, MetricaCalculoEnum metrica) throws Exception {

        if (metrica.equals(metrica.DIARIA)) {
            CalculoDiaria calculoDiario = new CalculoDiaria();
            return Double.toString(calculoDiario.calcular(Long.parseLong(calculaPermanencia(placaVeiculo)), retornaVeiculo(placaVeiculo)));
        } else if (metrica.equals(metrica.HORA)) {
            CalculoHorista calculoHora = new CalculoHorista();
            return Double.toString(calculoHora.calcular(Long.parseLong(calculaPermanencia(placaVeiculo)), retornaVeiculo(placaVeiculo)));
        } else if (metrica.equals(metrica.UM_QUARTO_HORA)) {
            Calculo15Minutos calculo15min = new Calculo15Minutos();
            return Double.toString(calculo15min.calcular(Long.parseLong(calculaPermanencia(placaVeiculo)), retornaVeiculo(placaVeiculo)));
        } else if (metrica.equals(metrica.AUTOMATICO)) {
            //retorna o menor valor entre os demais, pensei em adicionar os valores a uma lista temporária e retornar o menor entre eles.
        }

        return "este eh um teste";
    }

    public String calculaPermanencia(String placaVeiculo) throws Exception {
        //PESQUISA NA LISTA DE VEÍCULOS A PLACA CORRESPONTENTE.
        //PEGA A DATA DE INÍCIO MENOS A ATUAL
        //PEGA ESSA DIFERENÇA E RETORNA.
        String placa;
        for (ContaVeiculo conta : listaVeiculos) {
            placa = conta.getVeiculo().getPlaca();

            if (placa.equals(placaVeiculo)) {

                return Long.toString(Calendar.getInstance().getTimeInMillis() - conta.getInicio());
            }
        }
        return "123";
    }

    public Veiculo retornaVeiculo(String placaVeiculo) {

        String placa;
        for (ContaVeiculo conta : listaVeiculos) {
            placa = conta.getVeiculo().getPlaca();

            if (placa.equals(placaVeiculo)) {
                return conta.getVeiculo();
            }
        }
        return null;
    }

}
