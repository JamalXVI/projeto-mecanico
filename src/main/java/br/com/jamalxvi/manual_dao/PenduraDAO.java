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
import br.com.jamalxvi.modelo.Pendura;
public class PenduraDAO{
    private PreparedStatement cmd;
	private Connection con;
    public PenduraDAO()
    {
    	
    }
    public Collection<Pendura> lista()
    {
    	try{
    		 String sql = "SELECT * FROM PENDURA p WHERE p.ativo = true LIMIT 10000";
    	     con = Conexao.Conectar();
    	     cmd = con.prepareStatement(sql);
    	     ResultSet rs = cmd.executeQuery();
    	     Collection<Pendura> penduras = new ArrayList<>();
    	     while(rs.next()){
        	    Calendar c = Calendar.getInstance();
				Pendura pendura = new Pendura();
				c.setTimeInMillis(rs.getTimestamp("dia_para_pagar").getTime());
				pendura.setAtivo(true);
				pendura.setCliente(new ClienteDAO().cliente_completo(rs.getInt("cliente_id")));
				pendura.setDescricao(rs.getString("descricao"));
				pendura.setValor(rs.getBigDecimal("valor"));
				pendura.setDia_para_pagar(c);
				pendura.setId(rs.getShort("id"));
				pendura.setPago(rs.getBoolean("pago"));
				pendura.setExecucao(rs.getBoolean("execucao"));
				try {
					Calendar c2 = Calendar.getInstance();
					c2.setTimeInMillis(rs.getTimestamp("dia_pago").getTime());
					pendura.setDia_pago(c2);
				} catch (Exception e) {
				}
				pendura.setDuplicata(rs.getString("duplicata"));
				pendura.setNota_fiscal(rs.getBoolean("nota_fiscal"));
				pendura.setNumero_nota(rs.getString("numero_nota"));
				pendura.setObservacao(rs.getString("observacao"));
				if (pendura.isExecucao()) {
					pendura.setServicosExecutados(new ExecutadoDAO().executado(rs.getInt("servicosExecutados_id")));
				}
				penduras.add(pendura);
    	     }

    	     Conexao.Desconectar(con);
    	     return penduras;
    	 } catch (SQLException e) {
    	     System.out.println("ERRO: " + e.getMessage());
    	     return null;
    	 } finally {
    	     Conexao.Desconectar(con);
    	 }
    }
    public Collection<Pendura> pesquisa_mes(int mes, int ano)
    {
    	try{
    		 String sql = "SELECT * FROM PENDURA p WHERE p.ativo = true and MONTH(p.dia_para_pagar) = ?"
    		 		+ " AND YEAR(p.dia_para_pagar) = ?  AND p.nota_fiscal = false ORDER BY ID DESC";
    	     con = Conexao.Conectar();
    	     cmd = con.prepareStatement(sql);
    	     cmd.setInt(1, mes);
    	     cmd.setInt(2, ano);
    	     ResultSet rs = cmd.executeQuery();
    	     Collection<Pendura> penduras = new ArrayList<>();
    	     while(rs.next()){
         	    Calendar c = Calendar.getInstance();
				Pendura pendura = new Pendura();
				c.setTimeInMillis(rs.getTimestamp("dia_para_pagar").getTime());
				pendura.setAtivo(true);
				pendura.setCliente(new ClienteDAO().cliente_completo(rs.getInt("cliente_id")));
				pendura.setDescricao(rs.getString("descricao"));
				pendura.setValor(rs.getBigDecimal("valor"));
				pendura.setDia_para_pagar(c);
				pendura.setId(rs.getShort("id"));
				pendura.setPago(rs.getBoolean("pago"));
				pendura.setExecucao(rs.getBoolean("execucao"));
				try {
					Calendar c2 = Calendar.getInstance();
					c2.setTimeInMillis(rs.getTimestamp("dia_pago").getTime());
					pendura.setDia_pago(c2);
				} catch (Exception e) {
				}
				pendura.setDuplicata(rs.getString("duplicata"));
				pendura.setNota_fiscal(rs.getBoolean("nota_fiscal"));
				pendura.setNumero_nota(rs.getString("numero_nota"));
				pendura.setObservacao(rs.getString("observacao"));
				if (pendura.isExecucao()) {
					pendura.setServicosExecutados(new ExecutadoDAO().executado(rs.getInt("servicosExecutados_id")));
				}
				penduras.add(pendura);
    	     }
    	     Conexao.Desconectar(con);
    	     return penduras;
    	 } catch (SQLException e) {
    	     System.out.println("ERRO: " + e.getMessage());
    	     return null;
    	 } finally {
    	     Conexao.Desconectar(con);
    	 }
    }
    public Collection<Pendura> pesquisa_dia(String inicio, String fim)
    {
    	try{
    		 String sql = "SELECT * FROM PENDURA p WHERE p.ativo = true AND p.dia_para_pagar >= STR_TO_DATE(?,'%d/%m/%Y') AND"
					+ " p.dia_para_pagar <= STR_TO_DATE(?,'%d/%m/%Y')  AND p.nota_fiscal = false ORDER BY ID DESC";
    		 
			con = Conexao.Conectar();
			cmd = con.prepareStatement(sql);
			cmd.setString(1,  inicio);
			cmd.setString(2, fim);
			ResultSet rs = cmd.executeQuery();
			Collection<Pendura> penduras = new ArrayList<>();
			while (rs.next()) {
				Calendar c = Calendar.getInstance();
				Pendura pendura = new Pendura();
				c.setTimeInMillis(rs.getTimestamp("dia_para_pagar").getTime());
				pendura.setAtivo(true);
				pendura.setCliente(new ClienteDAO().cliente_completo(rs.getInt("cliente_id")));
				pendura.setDescricao(rs.getString("descricao"));
				pendura.setValor(rs.getBigDecimal("valor"));
				pendura.setDia_para_pagar(c);
				pendura.setId(rs.getShort("id"));
				pendura.setPago(rs.getBoolean("pago"));
				pendura.setExecucao(rs.getBoolean("execucao"));
				try {
					Calendar c2 = Calendar.getInstance();
					c2.setTimeInMillis(rs.getTimestamp("dia_pago").getTime());
					pendura.setDia_pago(c2);
				} catch (Exception e) {
				}
				pendura.setDuplicata(rs.getString("duplicata"));
				pendura.setNota_fiscal(rs.getBoolean("nota_fiscal"));
				pendura.setNumero_nota(rs.getString("numero_nota"));
				pendura.setObservacao(rs.getString("observacao"));
				if (pendura.isExecucao()) {
					pendura.setServicosExecutados(new ExecutadoDAO().executado(rs.getInt("servicosExecutados_id")));
				}
				penduras.add(pendura);
    	     }
    	     Conexao.Desconectar(con);
    	     return penduras;
    	 } catch (SQLException e) {
    	     System.out.println("ERRO: " + e.getMessage());
    	     return null;
    	 } finally {
    	     Conexao.Desconectar(con);
    	 }
    }
    public Collection<Pendura> pesquisa_dia_nf(String inicio, String fim)
    {
    	try{
    		 String sql = "SELECT * FROM PENDURA p WHERE p.ativo = true AND p.dia_para_pagar >= STR_TO_DATE(?,'%d/%m/%Y') AND"
					+ " p.dia_para_pagar <= STR_TO_DATE(?,'%d/%m/%Y')  AND p.nota_fiscal = true ORDER BY ID DESC";
    		 
			con = Conexao.Conectar();
			cmd = con.prepareStatement(sql);
			cmd.setString(1,  inicio);
			cmd.setString(2, fim);
			ResultSet rs = cmd.executeQuery();
			Collection<Pendura> penduras = new ArrayList<>();
			while (rs.next()) {
				Calendar c = Calendar.getInstance();
				Pendura pendura = new Pendura();
				c.setTimeInMillis(rs.getTimestamp("dia_para_pagar").getTime());
				pendura.setAtivo(true);
				pendura.setCliente(new ClienteDAO().cliente_completo(rs.getInt("cliente_id")));
				pendura.setDescricao(rs.getString("descricao"));
				pendura.setValor(rs.getBigDecimal("valor"));
				pendura.setDia_para_pagar(c);
				pendura.setId(rs.getShort("id"));
				pendura.setPago(rs.getBoolean("pago"));
				pendura.setExecucao(rs.getBoolean("execucao"));
				try {
					Calendar c2 = Calendar.getInstance();
					c2.setTimeInMillis(rs.getTimestamp("dia_pago").getTime());
					pendura.setDia_pago(c2);
				} catch (Exception e) {
				}
				pendura.setDuplicata(rs.getString("duplicata"));
				pendura.setNota_fiscal(rs.getBoolean("nota_fiscal"));
				pendura.setNumero_nota(rs.getString("numero_nota"));
				pendura.setObservacao(rs.getString("observacao"));
				if (pendura.isExecucao()) {
					pendura.setServicosExecutados(new ExecutadoDAO().executado(rs.getInt("servicosExecutados_id")));
				}
				penduras.add(pendura);
    	     }
    	     Conexao.Desconectar(con);
    	     return penduras;
    	 } catch (SQLException e) {
    	     System.out.println("ERRO: " + e.getMessage());
    	     return null;
    	 } finally {
    	     Conexao.Desconectar(con);
    	 }
    }
    public Collection<Pendura> pesquisa_mes_nf(int mes, int ano)
    {
    	try{
    		 String sql = "SELECT * FROM PENDURA p WHERE p.ativo = true and MONTH(p.dia_para_pagar) = ?"
    		 		+ " AND YEAR(p.dia_para_pagar) = ?  AND p.nota_fiscal = true ORDER BY ID DESC";
    	     con = Conexao.Conectar();
    	     cmd = con.prepareStatement(sql);
    	     cmd.setInt(1, mes);
    	     cmd.setInt(2, ano);
    	     ResultSet rs = cmd.executeQuery();
    	     Collection<Pendura> penduras = new ArrayList<>();
    	     while(rs.next()){
         	    Calendar c = Calendar.getInstance();
				Pendura pendura = new Pendura();
				c.setTimeInMillis(rs.getTimestamp("dia_para_pagar").getTime());
				pendura.setAtivo(true);
				pendura.setCliente(new ClienteDAO().cliente_completo(rs.getInt("cliente_id")));
				pendura.setDescricao(rs.getString("descricao"));
				pendura.setValor(rs.getBigDecimal("valor"));
				pendura.setDia_para_pagar(c);
				pendura.setId(rs.getShort("id"));
				pendura.setPago(rs.getBoolean("pago"));
				pendura.setExecucao(rs.getBoolean("execucao"));
				try {
					Calendar c2 = Calendar.getInstance();
					c2.setTimeInMillis(rs.getTimestamp("dia_pago").getTime());
					pendura.setDia_pago(c2);
				} catch (Exception e) {
				}
				pendura.setDuplicata(rs.getString("duplicata"));
				pendura.setNota_fiscal(rs.getBoolean("nota_fiscal"));
				pendura.setNumero_nota(rs.getString("numero_nota"));
				pendura.setObservacao(rs.getString("observacao"));
				if (pendura.isExecucao()) {
					pendura.setServicosExecutados(new ExecutadoDAO().executado(rs.getInt("servicosExecutados_id")));
				}
				penduras.add(pendura);
    	     }
    	     Conexao.Desconectar(con);
    	     return penduras;
    	 } catch (SQLException e) {
    	     System.out.println("ERRO: " + e.getMessage());
    	     return null;
    	 } finally {
    	     Conexao.Desconectar(con);
    	 }
    }
    public void pagar_executado(int id)
    {
    	try{
    		 String sql = "UPDATE PENDURA p SET p.pago = true WHERE p.servicosExecutados_id = ?";
    	     con = Conexao.Conectar();
    	     cmd = con.prepareStatement(sql);
    	     cmd.setInt(1, id);
    	     cmd.executeUpdate();
    	 } catch (SQLException e) {
    	     System.out.println("ERRO: " + e.getMessage());
    	     return;
    	 } finally {
    	     Conexao.Desconectar(con);
    	 }
    }
    public Collection<Pendura> pesquisa_cliente(int id)
    {
    	try{
    		 String sql = "SELECT * FROM mecanica.pendura p WHERE"
    		 		+ " p.ativo = TRUE AND p.cliente_id = ? ORDER BY ID DESC";
    	     con = Conexao.Conectar();
    	     cmd = con.prepareStatement(sql);
    	     cmd.setInt(1, id);
    	     ResultSet rs = cmd.executeQuery();
    	     Collection<Pendura> penduras = new ArrayList<>();
    	     while(rs.next()){
         	    Calendar c = Calendar.getInstance();
				Pendura pendura = new Pendura();
				c.setTimeInMillis(rs.getTimestamp("dia_para_pagar").getTime());
				pendura.setAtivo(true);
				pendura.setCliente(new ClienteDAO().cliente_completo(rs.getInt("cliente_id")));
				pendura.setDescricao(rs.getString("descricao"));
				pendura.setValor(rs.getBigDecimal("valor"));
				pendura.setDia_para_pagar(c);
				pendura.setId(rs.getShort("id"));
				pendura.setPago(rs.getBoolean("pago"));
				pendura.setExecucao(rs.getBoolean("execucao"));
				try {
					Calendar c2 = Calendar.getInstance();
					c2.setTimeInMillis(rs.getTimestamp("dia_pago").getTime());
					pendura.setDia_pago(c2);
				} catch (Exception e) {
				}
				pendura.setDuplicata(rs.getString("duplicata"));
				pendura.setNota_fiscal(rs.getBoolean("nota_fiscal"));
				pendura.setNumero_nota(rs.getString("numero_nota"));
				pendura.setObservacao(rs.getString("observacao"));
				if (pendura.isExecucao()) {
					pendura.setServicosExecutados(new ExecutadoDAO().executado(rs.getInt("servicosExecutados_id")));
				}
				penduras.add(pendura);
    	     }
    	     Conexao.Desconectar(con);
    	     return penduras;
    	 } catch (SQLException e) {
    	     System.out.println("ERRO: " + e.getMessage());
    	     return null;
    	 } finally {
    	     Conexao.Desconectar(con);
    	 }
    }
    public Collection<Pendura> pesquisa_execucao(int id)
    {
    	try{
    		 String sql = "SELECT * FROM mecanica.pendura p WHERE p.ativo = TRUE"
    		 		+ " AND p.servicosExecutados_id = ? ORDER BY ID DESC";
    	     con = Conexao.Conectar();
    	     cmd = con.prepareStatement(sql);
    	     cmd.setInt(1, id);
    	     ResultSet rs = cmd.executeQuery();
    	     Collection<Pendura> penduras = new ArrayList<>();
    	     while(rs.next()){
         	    Calendar c = Calendar.getInstance();
				Pendura pendura = new Pendura();
				c.setTimeInMillis(rs.getTimestamp("dia_para_pagar").getTime());
				pendura.setAtivo(true);
				pendura.setCliente(new ClienteDAO().cliente_completo(rs.getInt("cliente_id")));
				pendura.setDescricao(rs.getString("descricao"));
				pendura.setValor(rs.getBigDecimal("valor"));
				pendura.setDia_para_pagar(c);
				pendura.setId(rs.getShort("id"));
				pendura.setPago(rs.getBoolean("pago"));
				pendura.setExecucao(rs.getBoolean("execucao"));
				try {
					Calendar c2 = Calendar.getInstance();
					c2.setTimeInMillis(rs.getTimestamp("dia_pago").getTime());
					pendura.setDia_pago(c2);
				} catch (Exception e) {
				}
				pendura.setDuplicata(rs.getString("duplicata"));
				pendura.setNota_fiscal(rs.getBoolean("nota_fiscal"));
				pendura.setNumero_nota(rs.getString("numero_nota"));
				pendura.setObservacao(rs.getString("observacao"));
				if (pendura.isExecucao()) {
					pendura.setServicosExecutados(new ExecutadoDAO().executado(rs.getInt("servicosExecutados_id")));
				}
				penduras.add(pendura);
    	     }
    	     Conexao.Desconectar(con);
    	     return penduras;
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
    		 String sql = "SELECT SUM(valor) as soma FROM PENDURA p WHERE p.ativo = true AND p.pago = true and MONTH(p.dia_para_pagar) = ? AND YEAR(p.dia_para_pagar) = ?";
    	     con = Conexao.Conectar();
    	     cmd = con.prepareStatement(sql);
    	     cmd.setInt(1, mes);
    	     cmd.setInt(2, ano);
    	     ResultSet rs = cmd.executeQuery();
    	     while(rs.next()){
    	    	 return rs.getBigDecimal("soma");
    	     }
    	     return new BigDecimal(0);
    	 } catch (SQLException e) {
    	     System.out.println("ERRO: " + e.getMessage());
    	     return null;
    	 } finally {
    	     Conexao.Desconectar(con);
    	 }
    }
    public BigDecimal soma_mes_nao_pago(int mes, int ano)
    {
    	try{
    		 String sql = "SELECT SUM(valor) as soma FROM PENDURA p WHERE p.ativo = true AND p.pago = false and MONTH(p.dia_para_pagar) = ? AND YEAR(p.dia_para_pagar) = ?";
    	     con = Conexao.Conectar();
    	     cmd = con.prepareStatement(sql);
    	     cmd.setInt(1, mes);
    	     cmd.setInt(2, ano);
    	     ResultSet rs = cmd.executeQuery();
    	     while(rs.next()){
    	    	 return rs.getBigDecimal("soma");
    	     }
    	     return new BigDecimal(0);
    	 } catch (SQLException e) {
    	     System.out.println("ERRO: " + e.getMessage());
    	     return null;
    	 } finally {
    	     Conexao.Desconectar(con);
    	 }
    }
    public int ultimo()
    {
    	try{
    		 String sql = "SELECT * FROM PENDURA p ORDER BY ID DESC";
    	     con = Conexao.Conectar();
    	     cmd = con.prepareStatement(sql);
    	     ResultSet rs = cmd.executeQuery();
    	     List<Integer> penduras = new ArrayList<>();
    	     while(rs.next()){
        	   penduras.add(rs.getInt("id"));
    	     }
    	     Conexao.Desconectar(con);
    	     return penduras.get(0);
    	 } catch (SQLException e) {
    	     System.out.println("ERRO: " + e.getMessage());
    	     return 0;
    	 } finally {
    	     Conexao.Desconectar(con);
    	 }
    }
    
    public Pendura pendura(int id)
    {
    	try{
    		 String sql = "SELECT * FROM PENDURA p WHERE p.id = ?";
    	     con = Conexao.Conectar();
    	     cmd = con.prepareStatement(sql);
    	     cmd.setInt(1, id);
    	     ResultSet rs = cmd.executeQuery();
    	     List<Pendura> penduras = new ArrayList<>();
    	     while(rs.next()){
         	    Calendar c = Calendar.getInstance();
				Pendura pendura = new Pendura();
				c.setTimeInMillis(rs.getTimestamp("dia_para_pagar").getTime());

				pendura.setAtivo(true);
				pendura.setCliente(new ClienteDAO().cliente_completo(rs.getInt("cliente_id")));
				pendura.setDescricao(rs.getString("descricao"));
				pendura.setValor(rs.getBigDecimal("valor"));
				pendura.setDia_para_pagar(c);
				pendura.setId(rs.getShort("id"));
				pendura.setPago(rs.getBoolean("pago"));
				pendura.setExecucao(rs.getBoolean("execucao"));
				try {
					Calendar c2 = Calendar.getInstance();
					c2.setTimeInMillis(rs.getTimestamp("dia_pago").getTime());
					pendura.setDia_pago(c2);
				} catch (Exception e) {
				}
				pendura.setDuplicata(rs.getString("duplicata"));
				pendura.setNota_fiscal(rs.getBoolean("nota_fiscal"));
				pendura.setNumero_nota(rs.getString("numero_nota"));
				pendura.setObservacao(rs.getString("observacao"));
				if (pendura.isExecucao()) {
					pendura.setServicosExecutados(new ExecutadoDAO().executado(rs.getInt("servicosExecutados_id")));
				}
				penduras.add(pendura);
    	     }
    	     Conexao.Desconectar(con);
    	     return penduras.get(0);
    	 } catch (SQLException e) {
    	     System.out.println("ERRO: " + e.getMessage());
    	     return null;
    	 } finally {
    	     Conexao.Desconectar(con);
    	 }
    }
}
