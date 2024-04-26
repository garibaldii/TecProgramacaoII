/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.DAO;

import Model.Estacionamento.ContaVeiculo;
import Model.Estacionamento.StatusConta;
import java.sql.Connection;
import java.sql.DriverManager;
import static java.sql.JDBCType.NULL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ContaVeiculoDAO {

    VeiculoDAO veiculoDao = new VeiculoDAO();
    private Connection connection;

    public ContaVeiculoDAO() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            String DATABASE_URL = "jdbc:derby://localhost:1527/bdveiculos";
            String usuario = "APP";
            String senha = "123";
            this.connection = DriverManager.getConnection(DATABASE_URL, usuario, senha);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(VeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean taConectado() {
        return this.connection != null;
    }

    public ResultSet criarNovoRegistroConta(ContaVeiculo elemento) {
        String sql = "INSERT INTO contas(placa, inicio, fim, status) VALUES(?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, elemento.getVeiculo().getPlaca());
            stmt.setLong(2, elemento.getInicio());
            stmt.setLong(3, elemento.getFim());
            stmt.setString(4, elemento.getStatus().toString());
            stmt.execute();
            return stmt.getGeneratedKeys();
        } catch (SQLException ex) {
            Logger.getLogger(ContaVeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<ContaVeiculo> listarContas() {

        String sqlContas = "SELECT * FROM contas";

        List<ContaVeiculo> retorno = new ArrayList<>();
        try {
            PreparedStatement stmtContas = connection.prepareStatement(sqlContas);
            ResultSet resultadoContas = stmtContas.executeQuery();

            while (resultadoContas.next()) {
                ContaVeiculo conta = new ContaVeiculo(resultadoContas.getLong("inicio"), veiculoDao.retornaVeiculoCadastrado(resultadoContas.getString("placa")), StatusConta.valueOf(resultadoContas.getString("status")));

                if (conta.getStatus().equals(StatusConta.FECHADO)) {
                    conta.setFim(Calendar.getInstance().getTimeInMillis());
                }

                retorno.add(conta);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;

    }

    public void atualizarConta(String placa) {
        String sql = "UPDATE contas SET status = ?, FIM = ?  WHERE placa = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, StatusConta.FECHADO.toString());
            stmt.setLong(2, Calendar.getInstance().getTimeInMillis()); // seta o fim no BD com o tempo atual em milisegundos.
            stmt.setString(3, placa); //precisa atualizar o status do fim na lista também, o caraio viu
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ContaVeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sincronizarTabelaContas(List<ContaVeiculo> contaVeiculoLocal) {
        for (ContaVeiculo conta : contaVeiculoLocal) {

            //percorrer pela lista de veiculos locais
            //se o veículo tiver id nulo, quer dizer que ele não foi cadastrado no banco de dados
            //então, é necessário que ele seja adicionado na próxima sequência do banco.
            
            //por exemplo: banco parou no id 3, ele deve ser o id 4 e assim com os demais.
            
            if (conta.getId() == 0 && conta.getStatus().equals(StatusConta.ABERTO)) {// trata se o id da conta for 0 (caso de salvamento local)
                conta.setId(retornaUltimoIdBancoDados()); //retorna o último id do banco de dados.

                //aqui nao pode dar problema? Uma vez que estarei reescrevendo a sequência da chave primária. Por exemplo:
                //o banco parou no registro 5, mas o local fez mais 3 registros neste meio tempo.
                //na atualizacao, será passado os ids para os três registros, de acordo com este método: 6,7,8.
                //Quando o banco voltar e efetuar um registro, ele vai seguir a sequência do que ele escreveu, no caso, retomar ao 6
                //ou ele reconhece que já não estamos mais no id 5 e sim no id 8 e seguirá por diante?
                criarNovoRegistroConta(conta);

            }
        }
    }

    public int retornaUltimoIdBancoDados() {
        int ultimoId = 0;
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT MAX(id) FROM contas");

            if (rs.next()) {
                ultimoId = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ultimoId;
    }

}
