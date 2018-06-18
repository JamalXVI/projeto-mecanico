package br.com.jamalxvi.manual_dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.jamalxvi.infra.Conexao;
import br.com.jamalxvi.modelo.Funcionario;
public class FuncionarioDAO {
    private PreparedStatement cmd;
	private Connection con;
    public FuncionarioDAO()
    {
    }
public Collection<Funcionario> funcionarios()
{
	try{
	 String sql = "SELECT * FROM Funcionario f WHERE f.ativo = true ORDER BY id LIMIT 10000";
     con = Conexao.Conectar();
     cmd = con.prepareStatement(sql);
     ResultSet rs = cmd.executeQuery();
     Collection<Funcionario> funcionarios = new ArrayList<>();
     while(rs.next()){
         Funcionario f = new Funcionario();
         f.setAtivo(rs.getBoolean("ativo"));
         f.setFuncao(rs.getShort("funcao"));
         f.setHora_entrada(rs.getString("hora_entrada"));
         f.setHora_saida(rs.getString("hora_saida"));
         f.setSenha(rs.getString("senha"));
         f.setUsuario(rs.getString("usuario"));
         f.setCliente(new ClienteDAO().cliente_completo(rs.getInt("cliente_id")));
         f.setId(rs.getInt("id"));
         funcionarios.add(f);
     }
     Conexao.Desconectar(con);
     return funcionarios;
 } catch (SQLException e) {
     System.out.println("ERRO: " + e.getMessage());
     return null;
 } finally {
     Conexao.Desconectar(con);
 }
}
public Funcionario ultimo()
{
	try{
	 String sql = "SELECT * FROM Funcionario f ORDER BY id DESC LIMIT 1";
     con = Conexao.Conectar();
     cmd = con.prepareStatement(sql);
     ResultSet rs = cmd.executeQuery();
     List<Funcionario> funcionarios = new ArrayList<>();
     while(rs.next()){
         Funcionario f = new Funcionario();
         f.setAtivo(rs.getBoolean("ativo"));
         f.setFuncao(rs.getShort("funcao"));
         f.setHora_entrada(rs.getString("hora_entrada"));
         f.setHora_saida(rs.getString("hora_saida"));
         f.setSenha(rs.getString("senha"));
         f.setUsuario(rs.getString("usuario"));
         f.setId(rs.getInt("id"));
         funcionarios.add(f);
     }
     Conexao.Desconectar(con);
     return funcionarios.get(0);
 } catch (SQLException e) {
     System.out.println("ERRO: " + e.getMessage());
     return null;
 } finally {
     Conexao.Desconectar(con);
 }
}
public Funcionario funcionario(int id)
{
	try{
	 String sql = "SELECT * FROM Funcionario f WHERE f.id = ? ORDER BY id";
     con = Conexao.Conectar();
     cmd = con.prepareStatement(sql);
     cmd.setInt(1, id);
     ResultSet rs = cmd.executeQuery();
     List<Funcionario> funcionarios = new ArrayList<>();
     while(rs.next()){
         Funcionario f = new Funcionario();
         f.setAtivo(rs.getBoolean("ativo"));
         f.setFuncao(rs.getShort("funcao"));
         f.setHora_entrada(rs.getString("hora_entrada"));
         f.setHora_saida(rs.getString("hora_saida"));
         f.setSenha(rs.getString("senha"));
         f.setUsuario(rs.getString("usuario"));
         f.setCliente(new ClienteDAO().cliente_completo(rs.getInt("cliente_id")));
         f.setId(rs.getInt("id"));
         funcionarios.add(f);
     }
     Conexao.Desconectar(con);
     return funcionarios.get(0);
 } catch (SQLException e) {
     System.out.println("ERRO: " + e.getMessage());
     return null;
 } finally {
     Conexao.Desconectar(con);
 }
}
public Funcionario funcionario_cliente_id(int id)
{
	try{
	 String sql = "SELECT * FROM Funcionario f WHERE f.cliente_id = ? ORDER BY id";
     con = Conexao.Conectar();
     cmd = con.prepareStatement(sql);
     ResultSet rs = cmd.executeQuery();
     List<Funcionario> funcionarios = new ArrayList<>();
     while(rs.next()){
         Funcionario f = new Funcionario();
         f.setAtivo(rs.getBoolean("ativo"));
         f.setFuncao(rs.getShort("funcao"));
         f.setHora_entrada(rs.getString("hora_entrada"));
         f.setHora_saida(rs.getString("hora_saida"));
         f.setSenha(rs.getString("senha"));
         f.setUsuario(rs.getString("usuario"));
         f.setId(rs.getInt("id"));
         funcionarios.add(f);
     }
     Conexao.Desconectar(con);
     return funcionarios.get(0);
 } catch (SQLException e) {
     System.out.println("ERRO: " + e.getMessage());
     return null;
 } finally {
     Conexao.Desconectar(con);
 }
}
public Boolean funcionario_eh_cliente_id(int id)
{
	try{
	 String sql = "SELECT * FROM Funcionario f WHERE f.cliente_id = ? ORDER BY id";
     con = Conexao.Conectar();
     cmd = con.prepareStatement(sql);
     ResultSet rs = cmd.executeQuery();
     while(rs.next()){
         return true;
     }
     return false;
 } catch (SQLException e) {
     System.out.println("ERRO: " + e.getMessage());
     return false;
 } finally {
     Conexao.Desconectar(con);
 }
}
public Funcionario funcionario_cliente_id_executado(int id)
{
	try{
	 String sql = "SELECT * FROM Funcionario f WHERE f.ativo = true ORDER BY id";
     con = Conexao.Conectar();
     cmd = con.prepareStatement(sql);
     ResultSet rs = cmd.executeQuery();
     List<Funcionario> funcionarios = new ArrayList<>();
     while(rs.next()){
         Funcionario f = new Funcionario();
         f.setAtivo(rs.getBoolean("ativo"));
         f.setFuncao(rs.getShort("funcao"));
         f.setHora_entrada(rs.getString("hora_entrada"));
         f.setHora_saida(rs.getString("hora_saida"));
         f.setSenha(rs.getString("senha"));
         f.setUsuario(rs.getString("usuario"));
         f.setCliente(new ClienteDAO().cliente_completo(rs.getInt("cliente_id")));
         f.setId(rs.getInt("id"));
         funcionarios.add(f);
     }
     Conexao.Desconectar(con);
     return funcionarios.get(0);
 } catch (SQLException e) {
     System.out.println("ERRO: " + e.getMessage());
     return null;
 } finally {
     Conexao.Desconectar(con);
 }
}
}
