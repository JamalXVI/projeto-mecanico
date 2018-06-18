

package br.com.jamalxvi.infra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexao{
    /**
	 * 
	 */
	private static final String DATABASE="mecanica";
    private static final String HOST="localhost";
    private static final String DRIVER="com.mysql.jdbc.Driver";
    private static final String URL="jdbc:mysql://"+HOST+"/"+DATABASE;
    private static final String USR="adm";
    private static final String PWD="2530";
    public Conexao()
    {
    	
    }
    public static Connection Conectar(){
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USR,PWD);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("ERRO: " + e.getMessage());
            return null;
        }
    }
    public static void Fechar_Result(ResultSet rs)
    {
    	try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
            System.out.println("ERRO: " + e.getMessage());
		}
    }
    public static void Desconectar(Connection con){
        try {
            if ( con != null){
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("ERRO: " + e.getMessage());
        }
    }
    
}
