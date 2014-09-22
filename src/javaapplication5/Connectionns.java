/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication5;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
/**
 *
 * @author inv.Desarrollo2
 */
public class Connectionns {
   boolean res;    
   String error;
    public boolean connectDB(JFrame jframe,String host,String db,String user,String pass){
try{
Statement stmt = null;ResultSet rs = null;

Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
String connectionUrl = "jdbc:sqlserver://"+host+";"+"databaseName="+db+";"+"user="+user+";"+"password="+pass+";";

Connection con =DriverManager.getConnection(connectionUrl);
System.out.println(con.getMetaData().getDatabaseProductName().toString());
System.out.println(con.getMetaData().getURL());
System.out.println("Success");


//JOptionPane.showMessageDialog(jframe,"Success", null, -1);
}
catch(SQLException e){
     //System.out.println(""+e.getLocalizedMessage().toString());
    res=true;
    error= e.getLocalizedMessage();
     //JOptionPane.showMessageDialog(jframe,e.getLocalizedMessage().toString(), null, 0);
     //System.exit(0);
     
}
catch (ClassNotFoundException cE) {

            System.out.println("Class Not Found Exception: "+ cE.toString());
      }
        return res;
        
} 
    public String getError(){

       return error;
    }


}
