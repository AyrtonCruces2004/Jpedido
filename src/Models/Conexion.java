/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author EnzoV
 */
public class Conexion {
     private static final String Driver = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/pedido?useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static Connection con = null;

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        try{
            Class.forName(Driver);
            con= DriverManager.getConnection(URL, USER, PASSWORD);
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error de conexion");
        }
        return con;
    }
}
