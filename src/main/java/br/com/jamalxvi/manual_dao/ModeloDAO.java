package br.com.jamalxvi.manual_dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.jamalxvi.infra.Conexao;
import br.com.jamalxvi.modelo.Modelo;
public class ModeloDAO {
    private PreparedStatement cmd;
	private Connection con;
public Collection<Modelo> modelos()
{
	try{
	 String sql = "SELECT * FROM MODELO m WHERE m.ativo = true ORDER BY ID LIMIT 10000";
     con = Conexao.Conectar();
     cmd = con.prepareStatement(sql);
     ResultSet rs = cmd.executeQuery();
     Collection<Modelo> modelos = new ArrayList<>();
     while(rs.next()){
         Modelo m = new Modelo();
         m.setAtivo(rs.getBoolean("ativo"));
         m.setId(rs.getShort("id"));
         m.setNome(rs.getString("nome"));
         m.setMarca(new MarcaDAO().marca(rs.getInt("marca_id")));
         m.setVeiculos(new VeiculoDAO().modelo_id(m.getId()));
         modelos.add(m);
     }
     Conexao.Desconectar(con);
     return modelos;
 } catch (SQLException e) {
     System.out.println("ERRO: " + e.getMessage());
     return null;
 } finally {
     Conexao.Desconectar(con);
 }
}
public Modelo ultimo()
{
	try{
	 String sql = "SELECT * FROM MODELO m ORDER BY ID DESC LIMIT 1";
     con = Conexao.Conectar();
     cmd = con.prepareStatement(sql);
     ResultSet rs = cmd.executeQuery();
     List<Modelo> modelos = new ArrayList<>();
     while(rs.next()){
         Modelo m = new Modelo();
         m.setAtivo(rs.getBoolean("ativo"));
         m.setId(rs.getShort("id"));
         m.setNome(rs.getString("nome"));
         modelos.add(m);
     }
     Conexao.Desconectar(con);
     return modelos.get(0);
 } catch (SQLException e) {
     System.out.println("ERRO: " + e.getMessage());
     return null;
 } finally {
     Conexao.Desconectar(con);
 }
}
public Modelo modelo(int id)
{
	try{
	 String sql = "SELECT * FROM MODELO m WHERE m.id = ? ORDER BY ID";
     con = Conexao.Conectar();
     cmd = con.prepareStatement(sql);
     cmd.setInt(1, id);
     ResultSet rs = cmd.executeQuery();
     List<Modelo> modelos = new ArrayList<>();
     while(rs.next()){
         Modelo m = new Modelo();
         m.setAtivo(rs.getBoolean("ativo"));
         m.setId(rs.getShort("id"));
         m.setNome(rs.getString("nome"));
         m.setMarca(new MarcaDAO().marca(rs.getInt("marca_id")));
         m.setVeiculos(new VeiculoDAO().modelo_id(m.getId()));
         modelos.add(m);
     }
     Conexao.Desconectar(con);
     return modelos.get(0);
 } catch (SQLException e) {
     System.out.println("ERRO: " + e.getMessage());
     return null;
 } finally {
     Conexao.Desconectar(con);
 }
}
public Collection<Modelo> modelo_nome(String nome)
{
	try{
	 String sql = "SELECT * FROM MODELO m WHERE m.nome LIKE ? AND m.ativo = true LIMIT 25";
     con = Conexao.Conectar();
     cmd = con.prepareStatement(sql);
     cmd.setString(1, "%"+nome+"%");
     ResultSet rs = cmd.executeQuery();
     Collection<Modelo> modelos = new ArrayList<>();
     while(rs.next()){
         Modelo m = new Modelo();
         m.setAtivo(rs.getBoolean("ativo"));
         m.setId(rs.getShort("id"));
         m.setNome(rs.getString("nome"));
         m.setMarca(new MarcaDAO().marca(rs.getInt("marca_id")));
         m.setVeiculos(new VeiculoDAO().modelo_id(m.getId()));
         modelos.add(m);
     }
     Conexao.Desconectar(con);
     return modelos;
 } catch (SQLException e) {
     System.out.println("ERRO: " + e.getMessage());
     return null;
 } finally {
     Conexao.Desconectar(con);
 }
}
public Modelo modelo_completo(int id)
{
	try{
	 String sql = "SELECT * FROM MODELO m WHERE m.id = ? ORDER BY ID";
     con = Conexao.Conectar();
     cmd = con.prepareStatement(sql);
     cmd.setInt(1, id);
     ResultSet rs = cmd.executeQuery();
     List<Modelo> modelos = new ArrayList<>();
     while(rs.next()){
         Modelo m = new Modelo();
         m.setAtivo(rs.getBoolean("ativo"));
         m.setId(rs.getShort("id"));
         m.setNome(rs.getString("nome"));
         m.setMarca(new MarcaDAO().marca(rs.getInt("marca_id")));
         modelos.add(m);
     }
     Conexao.Desconectar(con);
     return modelos.get(0);
 } catch (SQLException e) {
     System.out.println("ERRO: " + e.getMessage());
     return null;
 } finally {
     Conexao.Desconectar(con);
 }
}
public List<Modelo> marca_id(int id)
{
	try{
	 String sql = "SELECT * FROM MODELO m WHERE m.marca_id = ? AND m.ativo = true ORDER BY ID";
     con = Conexao.Conectar();
     cmd = con.prepareStatement(sql);
     cmd.setInt(1, id);
     ResultSet rs = cmd.executeQuery();
     List<Modelo> modelos = new ArrayList<>();
     while(rs.next()){
         Modelo m = new Modelo();
         m.setAtivo(rs.getBoolean("ativo"));
         m.setId(rs.getShort("id"));
         m.setNome(rs.getString("nome"));
         modelos.add(m);
     }
     Conexao.Desconectar(con);
     return modelos;
 } catch (SQLException e) {
     System.out.println("ERRO: " + e.getMessage());
     return null;
 } finally {
     Conexao.Desconectar(con);
 }
}
}
