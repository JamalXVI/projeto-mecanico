package br.com.jamalxvi.manual_dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.enterprise.context.RequestScoped;

import br.com.jamalxvi.infra.Conexao;
import br.com.jamalxvi.modelo.Marca;
@RequestScoped
public class MarcaDAO {
    private PreparedStatement cmd;
	private Connection con;
public Collection<Marca> marcas()
{
	try{
	 String sql = "SELECT * FROM MARCA m WHERE m.ativo = true ORDER BY ID LIMIT 10000";
     con = Conexao.Conectar();
     cmd = con.prepareStatement(sql);
     ResultSet rs = cmd.executeQuery();
     Collection<Marca> marcas = new ArrayList<>();
     while(rs.next()){
         Marca m = new Marca();
         m.setAtivo(rs.getBoolean("ativo"));
         m.setNome(rs.getString("nome"));
         m.setId(rs.getShort("id"));
         marcas.add(m);
     }
     Conexao.Desconectar(con);
     return marcas;
 } catch (SQLException e) {
     System.out.println("ERRO: " + e.getMessage());
     return null;
 } finally {
     Conexao.Desconectar(con);
 }
}
public Marca ultimo()
{
	try{
	 String sql = "SELECT * FROM MARCA m ORDER BY ID DESC LIMIT 1";
     con = Conexao.Conectar();
     cmd = con.prepareStatement(sql);
     ResultSet rs = cmd.executeQuery();
     List<Marca> marcas = new ArrayList<>();
     while(rs.next()){
         Marca m = new Marca();
         m.setAtivo(rs.getBoolean("ativo"));
         m.setNome(rs.getString("nome"));
         m.setId(rs.getShort("id"));
         marcas.add(m);
     }
     Conexao.Desconectar(con);
     return marcas.get(0);
 } catch (SQLException e) {
     System.out.println("ERRO: " + e.getMessage());
     return null;
 } finally {
     Conexao.Desconectar(con);
 }
}
public Marca marca(int id)
{
	try{
	 String sql = "SELECT * FROM MARCA m WHERE m.id = ? ORDER BY ID";
     con = Conexao.Conectar();
     cmd = con.prepareStatement(sql);
     cmd.setInt(1, id);
     ResultSet rs = cmd.executeQuery();
     List<Marca> marcas = new ArrayList<>();
     while(rs.next()){
         Marca m = new Marca();
         m.setAtivo(rs.getBoolean("ativo"));
         m.setNome(rs.getString("nome"));
         m.setId(rs.getShort("id"));
         marcas.add(m);
     }
     Conexao.Desconectar(con);
     return marcas.get(0);
 } catch (SQLException e) {
     System.out.println("ERRO: " + e.getMessage());
     return null;
 } finally {
     Conexao.Desconectar(con);
 }
}
public Collection<Marca> marca_nome(String nome)
{
	try{
	 String sql = "SELECT * FROM MARCA m WHERE m.nome LIKE ? AND m.ativo = true ORDER BY ID LIMIT 25";
     con = Conexao.Conectar();
     cmd = con.prepareStatement(sql);
     cmd.setString(1, "%"+nome+"%");
     ResultSet rs = cmd.executeQuery();
     Collection<Marca> marcas = new ArrayList<>();
     while(rs.next()){
         Marca m = new Marca();
         m.setAtivo(rs.getBoolean("ativo"));
         m.setNome(rs.getString("nome"));
         m.setId(rs.getShort("id"));
         marcas.add(m);
     }
     Conexao.Desconectar(con);
     return marcas;
 } catch (SQLException e) {
     System.out.println("ERRO: " + e.getMessage());
     return null;
 } finally {
     Conexao.Desconectar(con);
 }
}
public Marca preparar_marca(Marca marca)
{
	marca.setModelo(new ModeloDAO().marca_id(marca.getId()));
	return marca;
}
}
