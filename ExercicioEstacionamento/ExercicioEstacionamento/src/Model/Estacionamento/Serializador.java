/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Estacionamento;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author marke
 */
public class Serializador {
    
    public static void gravar(String caminho, Object objeto) throws FileNotFoundException, IOException{
        FileOutputStream outFile = new FileOutputStream(caminho);
        ObjectOutputStream s = new ObjectOutputStream(outFile);
        s.writeObject(objeto);
        s.close();
    }
    
    
    public static Object ler(String caminho)throws FileNotFoundException, IOException, ClassNotFoundException{
         FileInputStream inFile = new FileInputStream(caminho);
        ObjectInputStream s = new ObjectInputStream(inFile);
        Object objeto = s.readObject();
        s.close();
        return objeto;
}

}