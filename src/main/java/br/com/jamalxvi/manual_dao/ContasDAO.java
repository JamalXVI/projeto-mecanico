package br.com.jamalxvi.manual_dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import br.com.jamalxvi.infra.Conexao;
import br.com.jamalxvi.modelo.Contas;
public class ContasDAO{
    private PreparedStatement cmd;
	private Connection con;
    public ContasDAO()
    {
    	
    }
    public Collection<Contas> lista()
    {
    	try{
    		 String sql = "SELECT * FROM CONTAS C WHERE c.ativo = true LIMIT 10000";
    	     con = Conexao.Conectar();
    	     cmd = con.prepareStatement(sql);
    	     ResultSet rs = cmd.executeQuery();
    	     Collection<Contas> contas = new ArrayList<>();
    	     while(rs.next()){
         	    Calendar c = Calendar.getInstance();
        	    	Contas conta = new Contas();
        	    	conta.setAtivo(rs.getBoolean("ativo"));
        	    	c.setTimeInMillis(rs.getTimestamp("dia").getTime());
					conta.setDia(c);
					conta.setId(rs.getInt("id"));
					conta.setPaga(rs.getBoolean("paga"));
					conta.setMotivo(rs.getString("motivo"));
					conta.setProduto(rs.getBoolean("produto"));
					if (conta.isProduto()) {
						conta.setPeca(new PecasDAO().peca(rs.getInt("peca_id")));
						conta.setQuantidade(rs.getInt("quantidade"));
					}
					try {
						Calendar c2 = Calendar.getInstance();
						c2.setTimeInMillis(rs.getTimestamp("dia_pago").getTime());
						conta.setDia_pago(c2);
					} catch (Exception e) {
					}
					conta.setDuplicata(rs.getString("duplicata"));
					conta.setNota_fiscal(rs.getBoolean("nota_fiscal"));
					conta.setNumero_nota(rs.getString("numero_nota"));
					conta.setObservacao(rs.getString("observacao"));
					conta.setNome(rs.getString("nome"));
					conta.setValor(rs.getBigDecimal("valor"));
					conta.setTem_produtor(rs.getBoolean("tem_produtor"));
					if (conta.isTem_produtor()) {
						conta.setProdutor(new ProdutorDAO().produtor_conta(rs.getInt("produtor_id")));
					}
					contas.add(conta);
    	     }
    	     Conexao.Desconectar(con);
    	     return contas;
    	 } catch (SQLException e) {
    	     System.out.println("ERRO: " + e.getMessage());
    	     return null;
    	 } finally {
    	     Conexao.Desconectar(con);
    	 }
    }
    public Collection<Contas> pesquisa_mes(int mes, int ano)
    {
    	try{
    		 String sql = "SELECT * FROM CONTAS C WHERE c.ativo = true and MONTH(c.dia) = ? AND YEAR(c.dia) = ?";
    	     con = Conexao.Conectar();
    	     cmd = con.prepareStatement(sql);
    	     cmd.setInt(1, mes);
    	     cmd.setInt(2, ano);
    	     ResultSet rs = cmd.executeQuery();
    	     Collection<Contas> contas = new ArrayList<>();
    	     while(rs.next()){
         	    Calendar c = Calendar.getInstance();
        	    	Contas conta = new Contas();
        	    	conta.setAtivo(rs.getBoolean("ativo"));
					conta.setPaga(rs.getBoolean("paga"));
        	    	c.setTimeInMillis(rs.getTimestamp("dia").getTime());
					conta.setDia(c);
					conta.setId(rs.getInt("id"));
					conta.setMotivo(rs.getString("motivo"));
					conta.setProduto(rs.getBoolean("produto"));
					if (conta.isProduto()) {
						conta.setPeca(new PecasDAO().peca(rs.getInt("peca_id")));
						conta.setQuantidade(rs.getInt("quantidade"));
					}
					try {
						Calendar c2 = Calendar.getInstance();
						c2.setTimeInMillis(rs.getTimestamp("dia_pago").getTime());
						conta.setDia_pago(c2);
					} catch (Exception e) {
					}
					conta.setDuplicata(rs.getString("duplicata"));
					conta.setNota_fiscal(rs.getBoolean("nota_fiscal"));
					conta.setNumero_nota(rs.getString("numero_nota"));
					conta.setObservacao(rs.getString("observacao"));
					conta.setNome(rs.getString("nome"));
					conta.setValor(rs.getBigDecimal("valor"));
					conta.setTem_produtor(rs.getBoolean("tem_produtor"));
					if (conta.isTem_produtor()) {
						conta.setProdutor(new ProdutorDAO().produtor_conta(rs.getInt("produtor_id")));
					}
					contas.add(conta);
    	     }
    	     Conexao.Desconectar(con);
    	     return contas;
    	 } catch (SQLException e) {
    	     System.out.println("ERRO: " + e.getMessage());
    	     return null;
    	 } finally {
    	     Conexao.Desconectar(con);
    	 }
    }
    public BigDecimal soma_mes(int mes, int ano)
    {
    	try{
    		 String sql = "SELECT SUM(valor) as soma FROM CONTAS C WHERE c.ativo = true and MONTH(c.dia) = ? AND YEAR(c.dia) = ?";
    	     con = Conexao.Conectar();
    	     cmd = con.prepareStatement(sql);
    	     cmd.setInt(1, mes);
    	     cmd.setInt(2, ano);
    	     ResultSet rs = cmd.executeQuery();
    	     while(rs.next()){
    	    	 BigDecimal tot = rs.getBigDecimal("soma");
    	    	 return tot;
    	     }
    	     return new BigDecimal(0);
    	 } catch (SQLException e) {
    	     System.out.println("ERRO: " + e.getMessage());
    	     return null;
    	 } finally {
    	     Conexao.Desconectar(con);
    	 }
    }
    public Contas ultimo()
    {
    	try{
    		 String sql = "SELECT * FROM CONTAS C ORDER BY ID DESC";
    	     con = Conexao.Conectar();
    	     cmd = con.prepareStatement(sql);
    	     ResultSet rs = cmd.executeQuery();
    	     List<Contas> contas = new ArrayList<>();
    	     while(rs.next()){
         	    Calendar c = Calendar.getInstance();
        	    	Contas conta = new Contas();
        	    	conta.setAtivo(rs.getBoolean("ativo"));
					conta.setPaga(rs.getBoolean("paga"));
        	    	c.setTimeInMillis(rs.getTimestamp("dia").getTime());
					conta.setDia(c);
					conta.setId(rs.getInt("id"));
					conta.setMotivo(rs.getString("motivo"));
					conta.setProduto(rs.getBoolean("produto"));
					if (conta.isProduto()) {
						conta.setPeca(new PecasDAO().peca(rs.getInt("peca_id")));
						conta.setQuantidade(rs.getInt("quantidade"));
					}
					try {
						Calendar c2 = Calendar.getInstance();
						c2.setTimeInMillis(rs.getTimestamp("dia_pago").getTime());
						conta.setDia_pago(c2);
					} catch (Exception e) {
					}
					conta.setDuplicata(rs.getString("duplicata"));
					conta.setNota_fiscal(rs.getBoolean("nota_fiscal"));
					conta.setNumero_nota(rs.getString("numero_nota"));
					conta.setObservacao(rs.getString("observacao"));
					conta.setNome(rs.getString("nome"));
					conta.setValor(rs.getBigDecimal("valor"));
					conta.setTem_produtor(rs.getBoolean("tem_produtor"));
					if (conta.isTem_produtor()) {
						conta.setProdutor(new ProdutorDAO().produtor_conta(rs.getInt("produtor_id")));
					}
					contas.add(conta);
    	     }
    	     Conexao.Desconectar(con);
    	     return contas.get(0);
    	 } catch (SQLException e) {
    	     System.out.println("ERRO: " + e.getMessage());
    	     return null;
    	 } finally {
    	     Conexao.Desconectar(con);
    	 }
    }
    public Contas conta(int id)
    {
    	try{
    		 String sql = "SELECT * FROM CONTAS c WHERE c.id = ?";
    	     con = Conexao.Conectar();
    	     cmd = con.prepareStatement(sql);
    	     cmd.setInt(1, id);
    	     ResultSet rs = cmd.executeQuery();
    	     List<Contas> contas = new ArrayList<>();
    	     while(rs.next()){
         	    Calendar c = Calendar.getInstance();
        	    	Contas conta = new Contas();
        	    	conta.setAtivo(rs.getBoolean("ativo"));
					conta.setPaga(rs.getBoolean("paga"));
        	    	c.setTimeInMillis(rs.getTimestamp("dia").getTime());
					conta.setDia(c);
					conta.setId(rs.getInt("id"));
					conta.setMotivo(rs.getString("motivo"));
					conta.setProduto(rs.getBoolean("produto"));
					if (conta.isProduto()) {
						conta.setPeca(new PecasDAO().peca(rs.getInt("peca_id")));
						conta.setQuantidade(rs.getInt("quantidade"));
					}
					try {
						Calendar c2 = Calendar.getInstance();
						c2.setTimeInMillis(rs.getTimestamp("dia_pago").getTime());
						conta.setDia_pago(c2);
					} catch (Exception e) {
					}
					conta.setDuplicata(rs.getString("duplicata"));
					conta.setNota_fiscal(rs.getBoolean("nota_fiscal"));
					conta.setNumero_nota(rs.getString("numero_nota"));
					conta.setObservacao(rs.getString("observacao"));
					conta.setNome(rs.getString("nome"));
					conta.setValor(rs.getBigDecimal("valor"));
					conta.setTem_produtor(rs.getBoolean("tem_produtor"));
					if (conta.isTem_produtor()) {
						conta.setProdutor(new ProdutorDAO().produtor_conta(rs.getInt("produtor_id")));
					}
					contas.add(conta);
    	     }
    	     Conexao.Desconectar(con);
    	     return contas.get(0);
    	 } catch (SQLException e) {
    	     System.out.println("ERRO: " + e.getMessage());
    	     return null;
    	 } finally {
    	     Conexao.Desconectar(con);
    	 }
    }
	public Collection<Contas> pesquisa_dia(String inicio, String fim) {
		
		try{
			String sql = "SELECT * FROM CONTAS c WHERE c.ativo = true AND c.dia >= STR_TO_DATE(?,'%d/%m/%Y') AND"
					+ " c.dia <= STR_TO_DATE(?,'%d/%m/%Y')  ORDER BY ID DESC";
   	     con = Conexao.Conectar();
   	     cmd = con.prepareStatement(sql);
			cmd.setString(1,  inicio);
			cmd.setString(2, fim);
   	     ResultSet rs = cmd.executeQuery();
   	     List<Contas> contas = new ArrayList<>();
   	     while(rs.next()){
        	    Calendar c = Calendar.getInstance();
       	    	Contas conta = new Contas();
       	    	conta.setAtivo(rs.getBoolean("ativo"));
					conta.setPaga(rs.getBoolean("paga"));
       	    	c.setTimeInMillis(rs.getTimestamp("dia").getTime());
					conta.setDia(c);
					conta.setId(rs.getInt("id"));
					conta.setMotivo(rs.getString("motivo"));
					conta.setProduto(rs.getBoolean("produto"));
					if (conta.isProduto()) {
						conta.setPeca(new PecasDAO().peca(rs.getInt("peca_id")));
						conta.setQuantidade(rs.getInt("quantidade"));
					}
					try {
						Calendar c2 = Calendar.getInstance();
						c2.setTimeInMillis(rs.getTimestamp("dia_pago").getTime());
						conta.setDia_pago(c2);
					} catch (Exception e) {
					}
					conta.setDuplicata(rs.getString("duplicata"));
					conta.setNota_fiscal(rs.getBoolean("nota_fiscal"));
					conta.setNumero_nota(rs.getString("numero_nota"));
					conta.setObservacao(rs.getString("observacao"));
					conta.setNome(rs.getString("nome"));
					conta.setValor(rs.getBigDecimal("valor"));
					conta.setTem_produtor(rs.getBoolean("tem_produtor"));
					if (conta.isTem_produtor()) {
						conta.setProdutor(new ProdutorDAO().produtor_conta(rs.getInt("produtor_id")));
					}
					contas.add(conta);
   	     }
   	     Conexao.Desconectar(con);
   	     return contas;
   	 } catch (SQLException e) {
   	     System.out.println("ERRO: " + e.getMessage());
   	     return null;
   	 } finally {
   	     Conexao.Desconectar(con);
   	 }
	}
}
