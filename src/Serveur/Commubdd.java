package Serveur;

import java.sql.*;

public class Commubdd {

    /**
     * @param args the command line arguments
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        
        String url = "jdbc:mysql://localhost/tp";
        String user = "root";
        String passwd = "";
        
        Connection conn = DriverManager.getConnection(url, user, passwd);
        
        
        Statement state = conn.createStatement();
        ResultSet result = state.executeQuery("SELECT * FROM eleve");
        ResultSetMetaData resultMeta = result.getMetaData();
        
        System.out.println("\n******************");
        
        for(int i=1; i<=resultMeta.getColumnCount();i++){
            System.out.println("\t"+resultMeta.getColumnName(i).toUpperCase()+"\t *");
        }
        
        System.out.println("\n********************");
        while(result.next()){
            for(int i=1; i<= resultMeta.getColumnCount(); i++)
                System.out.println("\t"+result.getObject(i).toString()+"\t |");
            System.out.println("\n******************");
        }
        

        result.close();
        state.close();
    }
    
}
