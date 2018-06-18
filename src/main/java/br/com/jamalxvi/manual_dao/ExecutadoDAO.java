package br.com.jamalxvi.manual_dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;


import br.com.jamalxvi.infra.Conexao;
import br.com.jamalxvi.infra.Soma_Dados_Contas_Mes;
import br.com.jamalxvi.modelo.ServicosExecutados;
public class ExecutadoDAO {
    private PreparedStatement cmd;
	private Connection con;
public Collection<ServicosExecutados> lista	()
{
	try{
	 String sql = "SELECT * FROM SERVICOSEXECUTADOS s WHERE s.ativo = true  LIMIT 10000";
     con = Conexao.Conectar();
     cmd = con.prepareStatement(sql);
     ResultSet rs = cmd.executeQuery();
     Collection<ServicosExecutados> solicitacoes = new ArrayList<>();
     while(rs.next()){
 	    Calendar c = Calendar.getInstance();
         ServicosExecutados s = new ServicosExecutados();
         s.setAtivo(rs.getBoolean("ativo"));
         s.setResponsavel(rs.getString("responsavel"));
         c.setTimeInMillis(rs.getTimestamp("entrega").getTime());
         s.setEntrega(c);
         s.setForma_pagamento(rs.getString("forma_pagamento"));
         s.setId(rs.getShort("id"));
         solicitacoes.add(s);
     }
     Conexao.Desconectar(con);
     return solicitacoes;
 } catch (SQLException e) {
     System.out.println("ERRO: " + e.getMessage());
     return null;
} finally {
     Conexao.Desconectar(con);
 }
}
public ServicosExecutados ultimo()
{
	try{
	 String sql = "SELECT * FROM SERVICOSEXECUTADOS s ORDER BY ID DESC LIMIT 1";
     con = Conexao.Conectar();
     cmd = con.prepareStatement(sql);
     ResultSet rs = cmd.executeQuery();
     List<ServicosExecutados> solicitacoes = new ArrayList<>();
     while(rs.next()){
 	    Calendar c = Calendar.getInstance();
         ServicosExecutados s = new ServicosExecutados();
         s.setAtivo(rs.getBoolean("ativo"));
         s.setResponsavel(rs.getString("responsavel"));
         c.setTimeInMillis(rs.getTimestamp("entrega").getTime());
         s.setEntrega(c);
         s.setForma_pagamento(rs.getString("forma_pagamento"));
         s.setId(rs.getShort("id"));
         s.setOrcamento(new OrcamentoDAO().orcamento_completo(rs.getInt("orcamento_id")));
         solicitacoes.add(s);
     }
     Conexao.Desconectar(con);
     return solicitacoes.get(0);
 } catch (SQLException e) {
     System.out.println("ERRO: " + e.getMessage());
     return null;
} finally {
     Conexao.Desconectar(con);
 }
}
public ServicosExecutados executado(int id)
{
	try{
	 String sql = "SELECT * FROM SERVICOSEXECUTADOS s WHERE s.id = ?";
     con = Conexao.Conectar();
     cmd = con.prepareStatement(sql);
     cmd.setInt(1, id);
     ResultSet rs = cmd.executeQuery();
     List<ServicosExecutados> solicitacoes = new ArrayList<>();
     while(rs.next()){
 	    Calendar c = Calendar.getInstance();
         ServicosExecutados s = new ServicosExecutados();
         s.setAtivo(rs.getBoolean("ativo"));
         s.setResponsavel(rs.getString("responsavel"));
         c.setTimeInMillis(rs.getTimestamp("entrega").getTime());
         s.setEntrega(c);
         s.setForma_pagamento(rs.getString("forma_pagamento"));
         s.setId(rs.getShort("id"));
         s.setPago(rs.getBoolean("pago"));
         s.setAutorizador(new ClienteDAO().cliente_completo(rs.getInt("autorizador_id")));
         s.setMecanico(new FuncionarioDAO().funcionario_cliente_id_executado(rs.getInt("mecanico_id")));
         s.setOrcamento(new OrcamentoDAO().orcamento_completo(rs.getInt("orcamento_id")));
         solicitacoes.add(s);
     }
     Conexao.Desconectar(con);
     return solicitacoes.get(0);
 } catch (SQLException e) {
     System.out.println("ERRO: " + e.getMessage());
     return null;
} finally {
     Conexao.Desconectar(con);
 }
}
public void pagar(int id)
{
	try{
		 String sql = "UPDATE SERVICOSEXECUTADOS s SET s.pago = true WHERE s.id = ?";
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
public List<ServicosExecutados> autorizador(int id)
{
	try{
	 String sql = "SELECT * FROM SERVICOSEXECUTADOS s WHERE s.autorizador_id = ?";
     con = Conexao.Conectar();
     cmd = con.prepareStatement(sql);
     cmd.setInt(1, id);
     ResultSet rs = cmd.executeQuery();
     List<ServicosExecutados> solicitacoes = new ArrayList<>();
     while(rs.next()){
 	    Calendar c = Calendar.getInstance();
         ServicosExecutados s = new ServicosExecutados();
         s.setAtivo(rs.getBoolean("ativo"));
         s.setResponsavel(rs.getString("responsavel"));
         s.setPago(rs.getBoolean("pago"));
         c.setTimeInMillis(rs.getTimestamp("entrega").getTime());
         s.setEntrega(c);
         s.setForma_pagamento(rs.getString("forma_pagamento"));
         s.setId(rs.getShort("id"));
         solicitacoes.add(s);
     }
     Conexao.Desconectar(con);
     return solicitacoes;
 } catch (SQLException e) {
     System.out.println("ERRO: " + e.getMessage());
     return null;
} finally {
     Conexao.Desconectar(con);
 }
}
public ServicosExecutados mecanico_id(int id)
{
	try{
	 String sql = "SELECT * FROM SERVICOSEXECUTADOS s WHERE s.mecanico_id = ?";
     con = Conexao.Conectar();
     cmd = con.prepareStatement(sql);
     cmd.setInt(1, id);
     ResultSet rs = cmd.executeQuery();
     List<ServicosExecutados> solicitacoes = new ArrayList<>();
     while(rs.next()){
 	    Calendar c = Calendar.getInstance();
         ServicosExecutados s = new ServicosExecutados();
         s.setAtivo(rs.getBoolean("ativo"));
         s.setResponsavel(rs.getString("responsavel"));
         s.setPago(rs.getBoolean("pago"));
         c.setTimeInMillis(rs.getTimestamp("entrega").getTime());
         s.setEntrega(c);
         s.setForma_pagamento(rs.getString("forma_pagamento"));
         s.setId(rs.getShort("id"));
         solicitacoes.add(s);
     }
     Conexao.Desconectar(con);
     return solicitacoes.get(0);
 } catch (SQLException e) {
     System.out.println("ERRO: " + e.getMessage());
     return null;
} finally {
     Conexao.Desconectar(con);
 }
}
public ServicosExecutados orcamento_id(int id)
{
	try{
	 String sql = "SELECT * FROM SERVICOSEXECUTADOS s WHERE s.orcamento_id = ?";
     con = Conexao.Conectar();
     cmd = con.prepareStatement(sql);
     cmd.setInt(1, id);
     ResultSet rs = cmd.executeQuery();
     List<ServicosExecutados> executados = new ArrayList<>();
     while(rs.next()){
 	    Calendar c = Calendar.getInstance();
         ServicosExecutados s = new ServicosExecutados();
         s.setAtivo(rs.getBoolean("ativo"));
         s.setResponsavel(rs.getString("responsavel"));
         s.setPago(rs.getBoolean("pago"));
         c.setTimeInMillis(rs.getTimestamp("entrega").getTime());
         s.setEntrega(c);
         s.setForma_pagamento(rs.getString("forma_pagamento"));
         s.setId(rs.getShort("id"));
         executados.add(s);
     }
     Conexao.Desconectar(con);
     return executados.get(0);
 } catch (SQLException e) {
     System.out.println("ERRO: " + e.getMessage());
     return null;
} finally {
     Conexao.Desconectar(con);
 }
}
public Soma_Dados_Contas_Mes soma_mes(int mes, int ano)
 {
		Soma_Dados_Contas_Mes contas = new Soma_Dados_Contas_Mes();
		try {
			String sql = "SELECT orcamento_id FROM SERVICOSEXECUTADOS s WHERE MONTH(s.entrega) = ? AND YEAR(s.entrega) = ? AND s.ativo = true";
			con = Conexao.Conectar();
			cmd = con.prepareStatement(sql);
			cmd.setInt(1, mes);
			cmd.setInt(2, ano);
			ResultSet rs = cmd.executeQuery();
			while (rs.next()) {
				Soma_Dados_Contas_Mes orcamentos = new OrcamentoDAO().soma_orcamentos(rs.getInt("orcamento_id"));
				contas.setVenda_mes(contas.getVenda_mes().add(orcamentos.getVenda_mes()));
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
