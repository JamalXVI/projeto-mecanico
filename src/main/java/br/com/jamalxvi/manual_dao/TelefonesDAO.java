package br.com.jamalxvi.manual_dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import br.com.jamalxvi.infra.Conexao;
import br.com.jamalxvi.modelo.TelefoneCliente;
import br.com.jamalxvi.modelo.TelefoneFornecedor;
import br.com.jamalxvi.modelo.TelefoneProdutor;
public class TelefonesDAO {
    private PreparedStatement cmd;
	private Connection con;
    public List<TelefoneCliente> telefone_cliente(int id)
    {
    	try{
    	 String sql = "SELECT * FROM TELEFONECLIENTE t WHERE t.cliente_id = ? AND t.ativo = true";
         con = Conexao.Conectar();
         cmd = con.prepareStatement(sql);
         cmd.setInt(1, id);
         ResultSet rs = cmd.executeQuery();
         List<TelefoneCliente> telefones = new ArrayList<>();
         while(rs.next()){
        	 TelefoneCliente telefoneCliente = new TelefoneCliente();
        	 telefoneCliente.setAtivo(rs.getBoolean("ativo"));
        	 telefoneCliente.setDdd(rs.getString("ddd"));
        	 telefoneCliente.setId(rs.getShort("id"));
        	 telefoneCliente.setNumero(rs.getString("numero"));
        	 telefoneCliente.setOperadora(rs.getString("operadora"));
        	 telefoneCliente.setTipo(rs.getString("tipo"));
             telefones.add(telefoneCliente);
         }
	     Conexao.Desconectar(con);
         return telefones;
         
     } catch (SQLException e) {
         System.out.println("ERRO: " + e.getMessage());
         return null;
    } finally {
         Conexao.Desconectar(con);
     }
    }
    public List<TelefoneFornecedor> telefone_fornecedor(int id)
    {
    	try{
    	 String sql = "SELECT * FROM TELEFONEFORNECEDOR t WHERE t.fornecedor_id = ?";
         con = Conexao.Conectar();
         cmd = con.prepareStatement(sql);
         cmd.setInt(1, id);
         ResultSet rs = cmd.executeQuery();
         List<TelefoneFornecedor> telefones = new ArrayList<>();
         while(rs.next()){
        	 TelefoneFornecedor telefoneFornecedor = new TelefoneFornecedor();
        	 telefoneFornecedor.setAtivo(rs.getBoolean("ativo"));
        	 telefoneFornecedor.setDdd(rs.getString("ddd"));
        	 telefoneFornecedor.setId(rs.getShort("id"));
        	 telefoneFornecedor.setNumero(rs.getString("numero"));
        	 telefoneFornecedor.setOperadora(rs.getString("operadora"));
        	 telefoneFornecedor.setTipo(rs.getString("tipo"));
             telefones.add(telefoneFornecedor);
         }
	     Conexao.Desconectar(con);
         return telefones;
         
     } catch (SQLException e) {
         System.out.println("ERRO: " + e.getMessage());
         return null;
    } finally {
         Conexao.Desconectar(con);
     }
    }
    public List<TelefoneProdutor> telefone_produtor(int id)
    {
    	try{
    	 String sql = "SELECT * FROM TELEFONEPRODUTOR t WHERE t.produtor_id = ?";
         con = Conexao.Conectar();
         cmd = con.prepareStatement(sql);
         cmd.setInt(1, id);
         ResultSet rs = cmd.executeQuery();
         List<TelefoneProdutor> telefones = new ArrayList<>();
         while(rs.next()){
        	 TelefoneProdutor telefone = new TelefoneProdutor();
        	 telefone.setAtivo(rs.getBoolean("ativo"));
        	 telefone.setDdd(rs.getString("ddd"));
        	 telefone.setId(rs.getShort("id"));
        	 telefone.setNumero(rs.getString("numero"));
        	 telefone.setOperadora(rs.getString("operadora"));
        	 telefone.setTipo(rs.getString("tipo"));
             telefones.add(telefone);
         }
	     Conexao.Desconectar(con);
         return telefones;
         
     } catch (SQLException e) {
         System.out.println("ERRO: " + e.getMessage());
         return null;
    } finally {
         Conexao.Desconectar(con);
     }
    }
}
