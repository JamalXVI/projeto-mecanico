package br.com.jamalxvi.manual_dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.jamalxvi.infra.Conexao;
import br.com.jamalxvi.modelo.Servicos;
public class ServicosDAO {
    private PreparedStatement cmd;
	private Connection con;  
public Collection<Servicos> lista()
{
	try{
	 String sql = "SELECT * FROM SERVICOS S WHERE s.ativo = true ORDER BY ID DESC  LIMIT 10000";
     con = Conexao.Conectar();
     cmd = con.prepareStatement(sql);
     ResultSet rs = cmd.executeQuery();
     Collection<Servicos> servicos = new ArrayList<>();
     while(rs.next()){
    	 Servicos servico = new Servicos();
    	 servico.setAtivo(rs.getBoolean("ativo"));
    	 servico.setId(rs.getShort("id"));
    	 servico.setNome(rs.getString("nome"));
         servicos.add(servico);
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
public Servicos ultimo()
{
	try{
	 String sql = "SELECT * FROM SERVICOS S ORDER BY ID DESC LIMIT 1";
     con = Conexao.Conectar();
     cmd = con.prepareStatement(sql);
     ResultSet rs = cmd.executeQuery();
     List<Servicos> servicos = new ArrayList<>();
     while(rs.next()){
    	 Servicos servico = new Servicos();
    	 servico.setAtivo(rs.getBoolean("ativo"));
    	 servico.setId(rs.getShort("id"));
    	 servico.setNome(rs.getString("nome"));
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
public Servicos servico(int id)
{
	try{
	 String sql = "SELECT * FROM SERVICOS S WHERE s.id = ?";
     con = Conexao.Conectar();
     cmd = con.prepareStatement(sql);
     cmd.setInt(1, id);
     ResultSet rs = cmd.executeQuery();
     List<Servicos> servicos = new ArrayList<>();
     while(rs.next()){
    	 Servicos servico = new Servicos();
    	 servico.setAtivo(rs.getBoolean("ativo"));
    	 servico.setId(rs.getShort("id"));
    	 servico.setNome(rs.getString("nome"));
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
public List<Servicos> servico_nome(String nome)
{
	try{
	 String sql = "SELECT * FROM SERVICOS S WHERE s.nome LIKE ? AND s.ativo = true  LIMIT 25";
     con = Conexao.Conectar();
     cmd = con.prepareStatement(sql);
     cmd.setString(1, "%"+nome+"%");
     ResultSet rs = cmd.executeQuery();
     List<Servicos> servicos = new ArrayList<>();
     while(rs.next()){
    	 Servicos servico = new Servicos();
    	 servico.setAtivo(rs.getBoolean("ativo"));
    	 servico.setId(rs.getShort("id"));
    	 servico.setNome(rs.getString("nome"));
         servicos.add(servico);
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
