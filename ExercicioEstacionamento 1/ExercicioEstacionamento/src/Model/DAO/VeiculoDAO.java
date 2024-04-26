package Model.DAO;

import Model.Estacionamento.ContaVeiculo;
import Model.Estacionamento.TipoVeiculoEnum;
import Model.Estacionamento.Veiculo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author marke
 */
public class VeiculoDAO {

    private Connection connection;

    String caminhoPadrao = "C:\\Backup_Estacionamento";

    int countBackup = 0;

    public VeiculoDAO() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            String DATABASE_URL = "jdbc:derby://localhost:1527/bdveiculos";
            String usuario = "APP";
            String senha = "123";
            this.connection = DriverManager.getConnection(DATABASE_URL, usuario, senha);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ContaVeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public boolean taConectado() {
        return this.connection != null;

    }

    public boolean criarNovoRegistroVeiculo(Veiculo elemento) {

        if (!ehVeiculoCadastrado(elemento.getPlaca())) {
            String sql = "INSERT INTO veiculos(placa, tipo, nome) VALUES(?,?,?)";
            try {
                PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setString(1, elemento.getPlaca());
                stmt.setString(2, elemento.getTipo().toString());
                stmt.setString(3, elemento.getNome());
                stmt.execute();
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(VeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
        return false;
    }

    public List<Veiculo> listarVeiculos() {

        String sql = "SELECT * FROM clientes";
        List<Veiculo> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Veiculo veiculo = new Veiculo(resultado.getString("nome"), resultado.getString("placa"), TipoVeiculoEnum.valueOf(resultado.getString("tipo")));
                retorno.add(veiculo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;

    }

    public boolean ehVeiculoCadastrado(String placa) {
        String sql = "SELECT * FROM veiculos WHERE placa = ?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, placa);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Veiculo veiculo = new Veiculo(resultado.getString("nome"), resultado.getString("placa"), TipoVeiculoEnum.valueOf(resultado.getString("tipo")));

                if (veiculo.getPlaca().equals(placa)) {

                    return true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(VeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public Veiculo retornaVeiculoCadastrado(String placa) {
        String sql = "SELECT * FROM veiculos WHERE placa = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, placa);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Veiculo veiculo = new Veiculo(resultado.getString("nome"), resultado.getString("placa"), TipoVeiculoEnum.valueOf(resultado.getString("tipo")));

                if (veiculo.getPlaca().equals(placa)) {

                    return veiculo;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(VeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void sincronizarTabelaVeiculos(List<ContaVeiculo> contaVeiculoLocal) {
        for (ContaVeiculo conta : contaVeiculoLocal) {
            String placaDoVeiculoLista = conta.getVeiculo().getPlaca();

            if (!placaDoVeiculoLista.equals(ehVeiculoCadastrado(placaDoVeiculoLista))) {
                criarNovoRegistroVeiculo(conta.getVeiculo());
            }
        }
    }

}
