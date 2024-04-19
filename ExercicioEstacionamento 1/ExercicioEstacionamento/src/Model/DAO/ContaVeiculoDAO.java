/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.DAO;

import Model.Estacionamento.ContaVeiculo;
import Model.Estacionamento.StatusConta;
import Model.Estacionamento.TipoVeiculoEnum;
import Model.Estacionamento.Veiculo;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author marke
 */
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
            Logger.getLogger(PersistenciaDados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean criarNovoRegistroConta(ContaVeiculo elemento) {
        String sql = "INSERT INTO contas(placa, inicio, fim, status) VALUES(?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, elemento.getVeiculo().getPlaca());
            stmt.setLong(2, elemento.getInicio());
            stmt.setLong(3, elemento.getFim());
            stmt.setString(4, elemento.getStatus().toString());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PersistenciaDados.class.getName()).log(Level.SEVERE, null, ex);
            return false;
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
               
                if (conta.getStatus().equals(StatusConta.FECHADO)){
                    conta.setFim(Calendar.getInstance().getTimeInMillis());
                }

                retorno.add(conta); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(VeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;

    }

    public void atualizarContas(String placa) {
        String sql = "UPDATE contas SET status = ?, FIM = ?  WHERE placa = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, StatusConta.FECHADO.toString());
            stmt.setLong(2,Calendar.getInstance().getTimeInMillis()); // seta o fim no BD com o tempo atual em milisegundos.
            stmt.setString(3, placa); //precisa atualizar o status do fim na lista também, o caraio viu
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(PersistenciaDados.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
