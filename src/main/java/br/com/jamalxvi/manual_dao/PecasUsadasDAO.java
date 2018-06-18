package br.com.jamalxvi.manual_dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;

import br.com.jamalxvi.infra.Conexao;
import br.com.jamalxvi.modelo.PecasUsadas;
@RequestScoped
public class PecasUsadasDAO {
    private PreparedStatement cmd;
	private Connection con;
    public PecasUsadas peca_usada(int id)
    {
    	try{
    	 String sql = "SELECT * FROM PECASUSADAS p WHERE p.id = ?";
         con = Conexao.Conectar();
         cmd = con.prepareStatement(sql);
         cmd.setInt(1, id);
         ResultSet rs = cmd.executeQuery();
         List<PecasUsadas> pecas = new ArrayList<>();
         while(rs.next()){
        	 PecasUsadas peca = new PecasUsadas();
        	 peca.setId(rs.getShort("id"));
        	 peca.setPreco_compra(rs.getBigDecimal("preco_compra"));
        	 peca.setPreco_venda(rs.getBigDecimal("preco_venda"));
        	 peca.setQuantidade_usada(rs.getInt("quantidade_usada"));
        	 peca.setPeca(new PecasDAO().peca(rs.getInt("peca_id")));
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
    public List<PecasUsadas> peca_orcamento(int id)
    {
    	try{
    	 String sql = "SELECT * FROM ORCAMENTO_PECASUSADAS p WHERE p.orcamento_id = ?";
         con = Conexao.Conectar();
         cmd = con.prepareStatement(sql);
         cmd.setInt(1, id);
         ResultSet rs = cmd.executeQuery();
         List<PecasUsadas> pecas = new ArrayList<>();
         while(rs.next()){
        	 PecasUsadas usada = peca_usada(rs.getInt("pecas_id"));
        	 pecas.add(usada);
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
