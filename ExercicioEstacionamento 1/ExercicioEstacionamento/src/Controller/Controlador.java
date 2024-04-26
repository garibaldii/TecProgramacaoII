package Controller;

import Model.DAO.*;
import Model.Estacionamento.*;
import View.TelaPrincipal;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class Controlador {

    private List<ContaVeiculo> listaVeiculos;
    private Thread t0, t1;
    private PersistenciaDados DAO;
    private VeiculoDAO veiculoDAO = new VeiculoDAO();
    private ContaVeiculoDAO contaVeiculoDAO = new ContaVeiculoDAO();

    public Controlador() {
        listaVeiculos = new ArrayList<ContaVeiculo>();
        DAO = new PersistenciaDados();

    }

    public boolean taConectadoNoBanco() {
        //se o veiculoDAO e contaveiculoDAO estiverem com uma conexao nula, é necessário 
        if (contaVeiculoDAO.taConectado() && veiculoDAO.taConectado()) {
            veiculoDAO.sincronizarTabelaVeiculos(listaVeiculos);
            contaVeiculoDAO.sincronizarTabelaContas(listaVeiculos);
            return true;
        }
        return false;
    }

    public void addContaVeiculo(String nome, String placa, TipoVeiculoEnum tipo) throws Exception {

        if (taConectadoNoBanco()) {
            veiculoDAO.criarNovoRegistroVeiculo(new Veiculo(nome, placa, tipo));
            contaVeiculoDAO.criarNovoRegistroConta(new ContaVeiculo(Calendar.getInstance().getTimeInMillis() - (1000 * 60 * 60 * 2), new Veiculo(nome, placa, tipo), StatusConta.ABERTO));
        }

        listaVeiculos.add(new ContaVeiculo(Calendar.getInstance().getTimeInMillis() - (1000 * 60 * 60 * 2), new Veiculo(nome, placa, tipo), StatusConta.ABERTO));

    }

    public void salvar(String caminho) throws IOException {
        Serializador.gravar(caminho, listaVeiculos);

    }

    public void ler(String caminho) throws IOException, FileNotFoundException, ClassNotFoundException {
        listaVeiculos = (List<ContaVeiculo>) Serializador.ler(caminho);
    }

    public List<ContaVeiculo> pegaContasCadastradasBD() {

        if (taConectadoNoBanco()) {

            List<ContaVeiculo> listaContaBD = contaVeiculoDAO.listarContas();

            for (ContaVeiculo conta : listaContaBD) {
                listaVeiculos.add(conta);
            }
            return listaVeiculos;
        }

        return listaVeiculos;
    }

    public String[][] listaVeiculosCadastrados() throws Exception {
        //está renderizando a lista do bd - ok. Porém toda vez que o form é preenchido, TODA a lista do bd é renderizada novamente,
        //o que acontece? pegaContasCadastradasBD atualiza o tamanho de listaVeiculos, ele soma os registros do bd com a lista local. 
        //listaVeiculosCadastrados percorre a listaVeiculos. Se o método pegaContasCadastradasBD estiver presente no listaVeiculosCadastrados, a lista do bd será renderizada toda vez que o método for executado.
        //o que precisamos: que os itens do bd sejam renderizados apenas uma vez(ao iniciar o app) e exiba somente o item que acabou de ser adicionado e não TODA LISTA. 

        String[][] aux = new String[listaVeiculos.size()][6];
        ContaVeiculo conta;
        Date dataAux;
        for (int i = 0; i < listaVeiculos.size(); i++) {
            conta = (ContaVeiculo) listaVeiculos.get(i);
            aux[i][0] = conta.getVeiculo().getNome();
            aux[i][1] = conta.getVeiculo().getPlaca();
            aux[i][2] = conta.getVeiculo().getTipo().toString();
//            dataAux = new Date(conta.getInicio());
            aux[i][3] = Long.toString(conta.getInicio());
            aux[i][4] = Long.toString(conta.getFim());
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
        for (ContaVeiculo conta : listaVeiculos) {
            placa = conta.getVeiculo().getPlaca();

            if (placa.equals(placaVeiculo.toString())) {
                conta.setStatus(StatusConta.FECHADO);
                conta.setFim(Calendar.getInstance().getTimeInMillis());
            }

            if (taConectadoNoBanco()) {
                contaVeiculoDAO.atualizarConta((String) placaVeiculo);
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
            return automatico(placaVeiculo);

        }

        return "deu ruim";
    }

    private String automatico(String placaVeiculo) throws Exception {
        //fazer a verificação do automatico, comparando o periodo da conta inicial - conta.getInicio()
        //ele vai fazer a verificação e vai passar como parâmetro a métrica correspondente ao menor valor

        ContaVeiculo conta = retornaConta(placaVeiculo);

        float periodoInicial = conta.getInicio() - Calendar.getInstance().getTimeInMillis() / 1000 / 60 / 60;
        System.out.println(periodoInicial);

        if (periodoInicial < 1) {
            return calculoTipo(placaVeiculo, MetricaCalculoEnum.UM_QUARTO_HORA);
        } else if (periodoInicial >= 1 && periodoInicial < 12) {
            return calculoTipo(placaVeiculo, MetricaCalculoEnum.HORA);
        } else {
            return calculoTipo(placaVeiculo, MetricaCalculoEnum.DIARIA);
        }
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

    public ArrayList<String> carregaPlaca() {
        ArrayList<String> aux = new ArrayList<>();
        for (ContaVeiculo conta : listaVeiculos) {
            aux.add(conta.getVeiculo().getPlaca());
        }

        return aux;

    }

    public ContaVeiculo retornaConta(String placaVeiculo) {
        String placa;
        for (ContaVeiculo conta : listaVeiculos) {
            placa = conta.getVeiculo().getPlaca();

            if (placa.equals(placaVeiculo)) {
                return conta;
            }
        }
        return null;

    }

}
