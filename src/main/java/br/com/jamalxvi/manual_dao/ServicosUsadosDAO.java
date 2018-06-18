package br.com.jamalxvi.manual_dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.jamalxvi.infra.Conexao;
import br.com.jamalxvi.modelo.ServicosFeitos;
public class ServicosUsadosDAO {
    private PreparedStatement cmd;
	private Connection con;
    public ServicosFeitos servico_usado(int id)
    {
    	try{
    	 String sql = "SELECT * FROM SERVICOSFEITOS s WHERE s.id = ?";
         con = Conexao.Conectar();
         cmd = con.prepareStatement(sql);
         cmd.setInt(1, id);
         ResultSet rs = cmd.executeQuery();
         List<ServicosFeitos> servicos = new ArrayList<>();
         while(rs.next()){
        	 ServicosFeitos servico = new ServicosFeitos();
        	 servico.setId(rs.getInt("id"));
        	 servico.setNumero_horas(rs.getInt("numero_horas"));
        	 servico.setPreco(rs.getBigDecimal("preco"));
        	 servico.setServico(new ServicosDAO().servico(rs.getInt("servico_id")));
             servicos.add(servico);
         }
	     Conexao.Desconectar(con);
         return servicos.get(0);
         
     } catch (SQLException e) {
         System.out.println("ERRO: " + e.getMessage());
         return null;
    } finally {
         Conexao.Desconectar(con);
     }
    }
    public List<ServicosFeitos> servico_orcamento(int id)
    {
    	try{
    	 String sql = "SELECT * FROM ORCAMENTO_SERVICOSFEITOS s WHERE s.orcamento_id = ?";
         con = Conexao.Conectar();
         cmd = con.prepareStatement(sql);
         cmd.setInt(1, id);
         ResultSet rs = cmd.executeQuery();
         List<ServicosFeitos> servicos = new ArrayList<>();
         while(rs.next()){
        	 ServicosFeitos usado = servico_usado(rs.getInt("servicos_id"));
        	 servicos.add(usado);
         }
	     Conexao.Desconectar(con);
         return servicos;
         
     } catch (SQLException e) {
         System.out.println("ERRO: " + e.getMessage());
         return null;
    } finally {
         Conexao.Desconectar(con);
     }
    }
}
