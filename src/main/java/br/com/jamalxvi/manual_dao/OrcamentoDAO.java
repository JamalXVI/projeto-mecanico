	package br.com.jamalxvi.manual_dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.jamalxvi.infra.Conexao;
import br.com.jamalxvi.infra.Soma_Dados_Contas_Mes;
import br.com.jamalxvi.modelo.Orcamento;
public class OrcamentoDAO {
    private PreparedStatement cmd;
	private Connection con;
    public Collection<Orcamento> lista()
    {
    	try{
    	 String sql = "SELECT * FROM ORCAMENTO o WHERE o.ativo = true AND o.atendido = false ORDER BY ID DESC LIMIT 10000";
         con = Conexao.Conectar();
         cmd = con.prepareStatement(sql);
         ResultSet rs = cmd.executeQuery();
         Collection<Orcamento> orcamentos = new ArrayList<>();
         while(rs.next()){
        	 Orcamento o = new Orcamento();
        	 o.setAtendido(rs.getBoolean("atendido"));
        	 o.setAtivo(rs.getBoolean("ativo"));
        	 o.setDesconto(rs.getBigDecimal("desconto"));
        	 o.setId(rs.getShort("id"));
        	 o.setTotal(rs.getBigDecimal("total"));
        	 o.setSolicitacao(new SolicitacoesDAO().orcamento(o.getId()));
        	 o.setPecas(new PecasUsadasDAO().peca_orcamento(o.getId()));
        	 o.setServicos(new ServicosUsadosDAO().servico_orcamento(o.getId()));
        	 if (o.isAtendido()) {
    			o.setExecutados(new ExecutadoDAO().orcamento_id(o.getId()));
        	 }
             orcamentos.add(o);
         }
	     Conexao.Desconectar(con);
         return orcamentos;
     } catch (SQLException e) {
         System.out.println("ERRO: " + e.getMessage());
         return null;
    } finally {
         Conexao.Desconectar(con);
     }
    }
public Collection<Orcamento> ativos()
{
	try{
	 String sql = "SELECT * FROM ORCAMENTO o WHERE o.ativo = true AND o.atendido = false ORDER BY ID DESC LIMIT 10000";
     con = Conexao.Conectar();
     cmd = con.prepareStatement(sql);
     ResultSet rs = cmd.executeQuery();
     Collection<Orcamento> orcamentos = new ArrayList<>();
     while(rs.next()){
    	 Orcamento o = new Orcamento();
    	 o.setAtendido(rs.getBoolean("atendido"));
    	 o.setAtivo(rs.getBoolean("ativo"));
    	 o.setDesconto(rs.getBigDecimal("desconto"));
    	 o.setId(rs.getShort("id"));
    	 o.setTotal(rs.getBigDecimal("total"));
    	 o.setSolicitacao(new SolicitacoesDAO().orcamento(o.getId()));
    	 o.setPecas(new PecasUsadasDAO().peca_orcamento(o.getId()));
    	 o.setServicos(new ServicosUsadosDAO().servico_orcamento(o.getId()));
    	 if (o.isAtendido()) {
			o.setExecutados(new ExecutadoDAO().orcamento_id(o.getId()));
    	 }
         orcamentos.add(o);
     }
     Conexao.Desconectar(con);
     return orcamentos;
 } catch (SQLException e) {
     System.out.println("ERRO: " + e.getMessage());
     return null;
} finally {
     Conexao.Desconectar(con);
 }
}
public Collection<Orcamento> inativos()
{
	try{
	 String sql = "SELECT * FROM ORCAMENTO o WHERE o.ativo = true AND o.atendido = true ORDER BY ID DESC LIMIT 10000";
     con = Conexao.Conectar();
     cmd = con.prepareStatement(sql);
     ResultSet rs = cmd.executeQuery();
     Collection<Orcamento> orcamentos = new ArrayList<>();
     while(rs.next()){
    	 Orcamento o = new Orcamento();
    	 o.setAtendido(rs.getBoolean("atendido"));
    	 o.setAtivo(rs.getBoolean("ativo"));
    	 o.setDesconto(rs.getBigDecimal("desconto"));
    	 o.setId(rs.getShort("id"));
    	 o.setTotal(rs.getBigDecimal("total"));
    	 o.setSolicitacao(new SolicitacoesDAO().orcamento(o.getId()));
    	 o.setPecas(new PecasUsadasDAO().peca_orcamento(o.getId()));
    	 o.setServicos(new ServicosUsadosDAO().servico_orcamento(o.getId()));
    	 if (o.isAtendido()) {
			o.setExecutados(new ExecutadoDAO().orcamento_id(o.getId()));
    	 }
         orcamentos.add(o);
     }
     Conexao.Desconectar(con);
     return orcamentos;
 } catch (SQLException e) {
     System.out.println("ERRO: " + e.getMessage());
     return null;
} finally {
     Conexao.Desconectar(con);
 }
}
public Collection<Orcamento> pesquisa_id(String id)
{
	try{
	 String sql = "SELECT * FROM ORCAMENTO o WHERE o.ativo = true AND O.ID LIKE ? ORDER BY ID DESC";
     con = Conexao.Conectar();
     cmd = con.prepareStatement(sql);
     cmd.setString(1, "%"+id+"%");
     ResultSet rs = cmd.executeQuery();
     Collection<Orcamento> orcamentos = new ArrayList<>();
     while(rs.next()){
    	 Orcamento o = new Orcamento();
    	 o.setAtendido(rs.getBoolean("atendido"));
    	 o.setAtivo(rs.getBoolean("ativo"));
    	 o.setDesconto(rs.getBigDecimal("desconto"));
    	 o.setId(rs.getShort("id"));
    	 o.setTotal(rs.getBigDecimal("total"));
    	 o.setSolicitacao(new SolicitacoesDAO().orcamento(o.getId()));
    	 o.setPecas(new PecasUsadasDAO().peca_orcamento(o.getId()));
    	 o.setServicos(new ServicosUsadosDAO().servico_orcamento(o.getId()));
    	 if (o.isAtendido()) {
			o.setExecutados(new ExecutadoDAO().orcamento_id(o.getId()));
    	 }
         orcamentos.add(o);
     }
     Conexao.Desconectar(con);
     return orcamentos;
 } catch (SQLException e) {
     System.out.println("ERRO: " + e.getMessage());
     return null;
} finally {
     Conexao.Desconectar(con);
 }
}
public Orcamento ultimo()
{
	try{
	 String sql = "SELECT * FROM ORCAMENTO o ORDER BY ID DESC LIMIT 1";
     con = Conexao.Conectar();
     cmd = con.prepareStatement(sql);
     ResultSet rs = cmd.executeQuery();
     List<Orcamento> orcamentos = new ArrayList<>();
     while(rs.next()){
    	 Orcamento o = new Orcamento();
    	 o.setAtendido(rs.getBoolean("atendido"));
    	 o.setAtivo(rs.getBoolean("ativo"));
    	 o.setDesconto(rs.getBigDecimal("desconto"));
    	 o.setId(rs.getShort("id"));
    	 o.setTotal(rs.getBigDecimal("total"));
    	 
         orcamentos.add(o);
     }
     Conexao.Desconectar(con);
     return orcamentos.get(0);
 } catch (SQLException e) {
     System.out.println("ERRO: " + e.getMessage());
     return null;
} finally {
     Conexao.Desconectar(con);
 }
}
public Orcamento orcamento(int id)
{
	try{
	 String sql = "SELECT * FROM ORCAMENTO o WHERE o.id = ?";
     con = Conexao.Conectar();
     cmd = con.prepareStatement(sql);
     cmd.setInt(1, id);
     ResultSet rs = cmd.executeQuery();
     List<Orcamento> orcamentos = new ArrayList<>();
     while(rs.next()){
    	 Orcamento o = new Orcamento();
    	 o.setAtendido(rs.getBoolean("atendido"));
    	 o.setAtivo(rs.getBoolean("ativo"));
    	 o.setDesconto(rs.getBigDecimal("desconto"));
    	 o.setId(rs.getInt("id"));
    	 o.setTotal(rs.getBigDecimal("total"));
    	 o.setSolicitacao(new SolicitacoesDAO().orcamento(o.getId()));
    	 o.setPecas(new PecasUsadasDAO().peca_orcamento(o.getId()));
    	 o.setServicos(new ServicosUsadosDAO().servico_orcamento(o.getId()));
    	 if (o.isAtendido()) {
			o.setExecutados(new ExecutadoDAO().orcamento_id(id));
    	 }
         orcamentos.add(o);
     }
     Conexao.Desconectar(con);
     return orcamentos.get(0);
 } catch (SQLException e) {
     System.out.println("ERRO: " + e.getMessage());
     return null;
} finally {
     Conexao.Desconectar(con);
 }
}
public Orcamento orcamento_solicitacao(int id)
{
	try{
	 String sql = "SELECT * FROM ORCAMENTO o WHERE o.ativo = true AND o.atendido = false";
     con = Conexao.Conectar();
     cmd = con.prepareStatement(sql);
     ResultSet rs = cmd.executeQuery();
     List<Orcamento> orcamentos = new ArrayList<>();
     while(rs.next()){
    	 Orcamento o = new Orcamento();
    	 o.setAtendido(rs.getBoolean("atendido"));
    	 o.setAtivo(rs.getBoolean("ativo"));
    	 o.setDesconto(rs.getBigDecimal("desconto"));
    	 o.setId(rs.getShort("id"));
    	 o.setTotal(rs.getBigDecimal("total"));
    	 
         orcamentos.add(o);
     }
     if (orcamentos.size() > 0) {
	     Conexao.Desconectar(con);
         return orcamentos.get(0);
		
	}
     return null;
 } catch (SQLException e) {
     System.out.println("ERRO: " + e.getMessage());
     return null;
} finally {
     Conexao.Desconectar(con);
 }
}
public Orcamento orcamento_solicitacao_veiculo(int id)
{
	try{
	 String sql = "SELECT * FROM ORCAMENTO o WHERE o.id = ?";
     con = Conexao.Conectar();
     cmd = con.prepareStatement(sql);
     cmd.setInt(1, id);
     ResultSet rs = cmd.executeQuery();
     List<Orcamento> orcamentos = new ArrayList<>();
     while(rs.next()){
    	 Orcamento o = new Orcamento();
    	 o.setAtendido(rs.getBoolean("atendido"));
    	 o.setAtivo(rs.getBoolean("ativo"));
    	 o.setDesconto(rs.getBigDecimal("desconto"));
    	 o.setId(rs.getShort("id"));
    	 o.setTotal(rs.getBigDecimal("total"));
    	 
         orcamentos.add(o);
     }
     return orcamentos.get(0);
 } catch (SQLException e) {
     System.out.println("ERRO: " + e.getMessage());
     return null;
} finally {
     Conexao.Desconectar(con);
 }
}
public List<Orcamento> orcamento_nome(String nome)
{
	try{
	 String sql = "SELECT * FROM SOLICITACOES s WHERE s.solicitacoes LIKE ?  AND s.ativo = true ORDER BY ID DESC LIMIT 50";
     con = Conexao.Conectar();
     cmd = con.prepareStatement(sql);
     cmd.setString(1, "%"+nome+"%");
     ResultSet rs = cmd.executeQuery();
     List<Orcamento> orcamentos = new ArrayList<>();
     while(rs.next()){
    	 int id = rs.getInt("orcamento_id");
    	 if (id != 0) {
    		 Orcamento orcamento = orcamento(id);
    		 if (orcamento.isAtivo()) {
    			 orcamentos.add(orcamento);
    		 }
             
    	 }
         
     }
     Conexao.Desconectar(con);
     return orcamentos;
 } catch (SQLException e) {
     System.out.println("ERRO: " + e.getMessage());
     return null;
} finally {
     Conexao.Desconectar(con);
 }
}
public Orcamento orcamento_completo(int id)
{
	try{
	 String sql = "SELECT * FROM ORCAMENTO o WHERE o.id = ?";
     con = Conexao.Conectar();
     cmd = con.prepareStatement(sql);
     cmd.setInt(1, id);
     ResultSet rs = cmd.executeQuery();
     List<Orcamento> orcamentos = new ArrayList<>();
     while(rs.next()){
    	 Orcamento o = new Orcamento();
    	 o.setAtendido(rs.getBoolean("atendido"));
    	 o.setAtivo(rs.getBoolean("ativo"));
    	 o.setDesconto(rs.getBigDecimal("desconto"));
    	 o.setId(rs.getShort("id"));
    	 o.setTotal(rs.getBigDecimal("total"));
    	 o.setSolicitacao(new SolicitacoesDAO().orcamento(o.getId()));
    	 o.setPecas(new PecasUsadasDAO().peca_orcamento(o.getId()));
    	 o.setServicos(new ServicosUsadosDAO().servico_orcamento(o.getId()));
         orcamentos.add(o);
     }
     Conexao.Desconectar(con);
     return orcamentos.get(0);
 } catch (SQLException e) {
     System.out.println("ERRO: " + e.getMessage());
     return null;
} finally {
     Conexao.Desconectar(con);
 }
}

	public int solicitacar_orcamento(int id) {
		return new SolicitacoesDAO().solicitacao_id_orcamento(id);
	}

	public Soma_Dados_Contas_Mes soma_orcamentos(int id)
	{
		Soma_Dados_Contas_Mes mes = new Soma_Dados_Contas_Mes();
		try {
			String sql = "SELECT total FROM orcamento o WHERE o.id = ?";
			con = Conexao.Conectar();
			cmd = con.prepareStatement(sql);
			cmd.setInt(1, id);
			ResultSet rs = cmd.executeQuery();
			
			while (rs.next()) {
			    mes.setVenda_mes(rs.getBigDecimal("total"));
			}
			return mes;
		} catch (SQLException e) {
			System.out.println("ERRO: " + e.getMessage());
			return null;
		} finally {
			Conexao.Desconectar(con);
		}
	}
}
