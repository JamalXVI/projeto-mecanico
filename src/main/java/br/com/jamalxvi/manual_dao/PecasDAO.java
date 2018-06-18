package br.com.jamalxvi.manual_dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import br.com.jamalxvi.infra.Conexao;
import br.com.jamalxvi.modelo.Pecas;
public class PecasDAO {
    private PreparedStatement cmd;
	private Connection con;
public Collection<Pecas> lista()
{
	try{
	 String sql = "SELECT * FROM PECAS p WHERE p.ativo = true ORDER BY ID DESC  LIMIT 20000";
     con = Conexao.Conectar();
     cmd = con.prepareStatement(sql);
     ResultSet rs = cmd.executeQuery();
     Collection<Pecas> pecas = new ArrayList<>();
     while(rs.next()){
    	 Pecas peca = new Pecas();
    	 peca.setAtivo(rs.getBoolean("ativo"));
    	 peca.setEstoque(rs.getShort("estoque"));
    	 peca.setId(rs.getShort("id"));
    	 peca.setNome(rs.getString("nome"));
    	 peca.setCod_fiscal(rs.getString("cod_fiscal"));
    	 peca.setFornecedor(new FornecedorDAO().fornecedor_peca(rs.getInt("fornecedor_id")));
         pecas.add(peca);
     }
     Conexao.Desconectar(con);
     return pecas;
 } catch (SQLException e) {
     System.out.println("ERRO: " + e.getMessage());
     return null;
} finally {
     Conexao.Desconectar(con);
 }
}
public Pecas ultimo()
{
	try{
	 String sql = "SELECT * FROM PECAS p ORDER BY ID DESC LIMIT 1";
     con = Conexao.Conectar();
     cmd = con.prepareStatement(sql);
     ResultSet rs = cmd.executeQuery();
     List<Pecas> pecas = new ArrayList<>();
     while(rs.next()){
    	 Pecas peca = new Pecas();
    	 peca.setAtivo(rs.getBoolean("ativo"));
    	 peca.setEstoque(rs.getShort("estoque"));
    	 peca.setCod_fiscal(rs.getString("cod_fiscal"));
    	 peca.setId(rs.getShort("id"));
    	 peca.setNome(rs.getString("nome"));
         pecas.add(peca);
     }
     Conexao.Desconectar(con);
     return pecas.get(0);
 } catch (SQLException e) {
     System.out.println("ERRO: " + e.getMessage());
     return null;
} finally {
     Conexao.Desconectar(con);
 }
}
public Pecas peca(int id)
{
	try{
	 String sql = "SELECT * FROM PECAS p WHERE p.id = ?";
     con = Conexao.Conectar();
     cmd = con.prepareStatement(sql);
     cmd.setInt(1, id);
     ResultSet rs = cmd.executeQuery();
     List<Pecas> pecas = new ArrayList<>();
     while(rs.next()){
    	 Pecas peca = new Pecas();
    	 peca.setAtivo(rs.getBoolean("ativo"));
    	 peca.setEstoque(rs.getShort("estoque"));
    	 peca.setCod_fiscal(rs.getString("cod_fiscal"));
    	 peca.setId(rs.getShort("id"));
    	 peca.setNome(rs.getString("nome"));
    	 peca.setFornecedor(new FornecedorDAO().fornecedor_peca(rs.getInt("fornecedor_id")));
         pecas.add(peca);
     }
     Conexao.Desconectar(con);
     return pecas.get(0);
 } catch (SQLException e) {
     System.out.println("ERRO: " + e.getMessage());
     return null;
} finally {
     Conexao.Desconectar(con);
 }
}
public List<Pecas> peca_nome(String nome)
{
	try{
	 String sql = "SELECT * FROM PECAS p WHERE p.nome LIKE ? AND p.ativo = true ORDER BY ID DESC LIMIT 25";
     con = Conexao.Conectar();
     cmd = con.prepareStatement(sql);
     cmd.setString(1, "%"+nome+"%");
     ResultSet rs = cmd.executeQuery();
     List<Pecas> pecas = new ArrayList<>();
     while(rs.next()){
    	 Pecas peca = new Pecas();
    	 peca.setAtivo(rs.getBoolean("ativo"));
    	 peca.setEstoque(rs.getShort("estoque"));
    	 peca.setCod_fiscal(rs.getString("cod_fiscal"));
    	 peca.setId(rs.getShort("id"));
    	 peca.setNome(rs.getString("nome"));
    	 peca.setFornecedor(new FornecedorDAO().fornecedor_peca(rs.getInt("fornecedor_id")));
         pecas.add(peca);
     }
     Conexao.Desconectar(con);
     return pecas;
 } catch (SQLException e) {
     System.out.println("ERRO: " + e.getMessage());
     return null;
} finally {
     Conexao.Desconectar(con);
 }
}
public List<Pecas> peca_fornecedor(int id)
{
	try{
	 String sql = "SELECT * FROM PECAS p WHERE p.fornecedor_id = ? AND p.ativo = true";
     con = Conexao.Conectar();
     cmd = con.prepareStatement(sql);
     cmd.setInt(1, id);
     ResultSet rs = cmd.executeQuery();
     List<Pecas> pecas = new ArrayList<>();
     while(rs.next()){
    	 Pecas peca = new Pecas();
    	 peca.setAtivo(rs.getBoolean("ativo"));
    	 peca.setEstoque(rs.getShort("estoque"));
    	 peca.setCod_fiscal(rs.getString("cod_fiscal"));
    	 peca.setId(rs.getShort("id"));
    	 peca.setNome(rs.getString("nome"));
         pecas.add(peca);
     }
     Conexao.Desconectar(con);
     return pecas;
 } catch (SQLException e) {
     System.out.println("ERRO: " + e.getMessage());
     return null;
} finally {
     Conexao.Desconectar(con);
 }
}

}
