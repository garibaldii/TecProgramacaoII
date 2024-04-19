package Model.DAO;

import Model.Estacionamento.ContaVeiculo;
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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PersistenciaDados {

    private Connection connection;

    String caminhoPadrao = "C:\\Backup_Estacionamento";

    int countBackup = 0;

    public PersistenciaDados() {
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

    public boolean salvarBackupLocal(List<ContaVeiculo> listaRegistros) throws Exception {
        //Cria um novo arquivo de backup da lista de registros de ContaEstacionamento       
        gravarBackup(caminhoPadrao + "backup" + countBackup + ".dat", listaRegistros);
        return false;
    }

    private void gravarBackup(String caminho, Object objeto) throws FileNotFoundException, IOException {
        FileOutputStream outFile = new FileOutputStream(caminho);
        ObjectOutputStream s = new ObjectOutputStream(outFile);
        s.writeObject(objeto);
        s.close();
    }

    private Object lerBackup(String caminho) throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream inFile = new FileInputStream(caminho);
        ObjectInputStream s = new ObjectInputStream(inFile);
        Object objeto = s.readObject();
        s.close();
        return objeto;
    }
}
