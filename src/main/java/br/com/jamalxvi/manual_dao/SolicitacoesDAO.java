package br.com.jamalxvi.manual_dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;


import br.com.jamalxvi.infra.Conexao;
import br.com.jamalxvi.modelo.Fotos_Solicitacoes;
import br.com.jamalxvi.modelo.Solicitacoes;

public class SolicitacoesDAO {
    private PreparedStatement cmd;
	private Connection con;
public Collection<Solicitacoes> ativas()
{
	try{
	 String sql = "SELECT * FROM SOLICITACOES s WHERE s.ativo = true AND s.atendida = false ORDER BY ID DESC LIMIT 10000";
     con = Conexao.Conectar();
     cmd = con.prepareStatement(sql);
     ResultSet rs = cmd.executeQuery();
     Collection<Solicitacoes> solicitacoes = new ArrayList<>();
     while(rs.next()){
 	    Calendar c = Calendar.getInstance();
         Solicitacoes s = new Solicitacoes();
         s.setAtendida(rs.getBoolean("atendida"));
         s.setAtivo(rs.getBoolean("ativo"));
         c.setTimeInMillis(rs.getTimestamp("data_entrada").getTime());
         System.out.println(new SimpleDateFormat("dd/MM/YYYY HH:mm").format(c.getTimeInMillis()));
         s.setData_entrada(c);
         s.setSolicitacoes(rs.getString("solicitacoes"));
         s.setFotos(fotos_solicitacoes_id_solicitacao(s.getId()));
         s.setVeiculo(new VeiculoDAO().veiculo_solicitacao(rs.getInt("veiculo_id")));
         s.setId(rs.getInt("id"));
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
public Collection<Solicitacoes> inativas()
{
	try{
	 String sql = "SELECT * FROM SOLICITACOES s WHERE s.ativo = true AND s.atendida = true ORDER BY ID DESC LIMIT 10000";
     con = Conexao.Conectar();
     cmd = con.prepareStatement(sql);
     ResultSet rs = cmd.executeQuery();
     Collection<Solicitacoes> solicitacoes = new ArrayList<>();
     while(rs.next()){
 	    Calendar c = Calendar.getInstance();
         Solicitacoes s = new Solicitacoes();
         s.setAtendida(rs.getBoolean("atendida"));
         s.setAtivo(rs.getBoolean("ativo"));
         c.setTimeInMillis(rs.getTimestamp("data_entrada").getTime());
         s.setData_entrada(c);
         s.setSolicitacoes(rs.getString("solicitacoes"));
         s.setFotos(fotos_solicitacoes_id_solicitacao(s.getId()));
         if (s.isAtendida()) {
         	 s.setOrcamento(new OrcamentoDAO().orcamento_solicitacao(rs.getInt("orcamento_id")));;
         }
         s.setId(rs.getInt("id"));
         s.setVeiculo(new VeiculoDAO().veiculo_solicitacao(rs.getInt("veiculo_id")));
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
public Solicitacoes ultimo()
{
	try{
	 String sql = "SELECT * FROM SOLICITACOES s ORDER BY ID DESC LIMIT 1";
     con = Conexao.Conectar();
     cmd = con.prepareStatement(sql);
     ResultSet rs = cmd.executeQuery();
     List<Solicitacoes> solicitacoes = new ArrayList<>();
     while(rs.next()){
 	    Calendar c = Calendar.getInstance();
         Solicitacoes s = new Solicitacoes();
         s.setAtendida(rs.getBoolean("atendida"));
         s.setAtivo(rs.getBoolean("ativo"));
         c.setTimeInMillis(rs.getTimestamp("data_entrada").getTime());
         s.setData_entrada(c);
         s.setSolicitacoes(rs.getString("solicitacoes"));
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
public Solicitacoes solicitacao(int id)
{
	try{
	 String sql = "SELECT * FROM SOLICITACOES s WHERE s.id = ?";
     con = Conexao.Conectar();
     cmd = con.prepareStatement(sql);
     cmd.setInt(1, id);
     ResultSet rs = cmd.executeQuery();
     List<Solicitacoes> solicitacoes = new ArrayList<>();
     while(rs.next()){
 	    Calendar c = Calendar.getInstance();
         Solicitacoes s = new Solicitacoes();
         s.setAtendida(rs.getBoolean("atendida"));
         s.setAtivo(rs.getBoolean("ativo"));
         c.setTimeInMillis(rs.getTimestamp("data_entrada").getTime());
         s.setData_entrada(c);
         s.setSolicitacoes(rs.getString("solicitacoes"));
         s.setFotos(fotos_solicitacoes_id_solicitacao(s.getId()));
         s.setId(rs.getShort("id"));
         if (s.isAtendida()) {
         	 s.setOrcamento(new OrcamentoDAO().orcamento_solicitacao(rs.getInt("orcamento_id")));;
         }
         s.setId(rs.getInt("id"));
         s.setVeiculo(new VeiculoDAO().veiculo_solicitacao(rs.getInt("veiculo_id")));
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
public List<Solicitacoes> solicitacao_nome(String nome)
{
	try{
	 String sql = "SELECT * FROM SOLICITACOES s WHERE s.solicitacoes LIKE ? AND  s.ativo = true ORDER BY ID DESC LIMIT 25";
     con = Conexao.Conectar();
     cmd = con.prepareStatement(sql);
     cmd.setString(1, "%"+nome+"%");
     ResultSet rs = cmd.executeQuery();
     List<Solicitacoes> solicitacoes = new ArrayList<>();
     while(rs.next()){
 	    Calendar c = Calendar.getInstance();
         Solicitacoes s = new Solicitacoes();
         s.setAtendida(rs.getBoolean("atendida"));
         s.setAtivo(rs.getBoolean("ativo"));
         c.setTimeInMillis(rs.getTimestamp("data_entrada").getTime());
         s.setData_entrada(c);
         s.setSolicitacoes(rs.getString("solicitacoes"));
         s.setFotos(fotos_solicitacoes_id_solicitacao(s.getId()));
         if (s.isAtendida()) {
         	 s.setOrcamento(new OrcamentoDAO().orcamento_solicitacao(rs.getInt("orcamento_id")));;
         }
         s.setId(rs.getInt("id"));
         s.setVeiculo(new VeiculoDAO().veiculo_solicitacao(rs.getInt("veiculo_id")));
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
public List<Solicitacoes> solicitacao_id(String id)
{
	try{
	 String sql = "SELECT * FROM SOLICITACOES s WHERE s.id LIKE ? AND  s.ativo = true ORDER BY ID DESC LIMIT 25";
     con = Conexao.Conectar();
     cmd = con.prepareStatement(sql);
     cmd.setString(1, "%"+id+"%");
     ResultSet rs = cmd.executeQuery();
     List<Solicitacoes> solicitacoes = new ArrayList<>();
     while(rs.next()){
 	    Calendar c = Calendar.getInstance();
         Solicitacoes s = new Solicitacoes();
         s.setAtendida(rs.getBoolean("atendida"));
         s.setAtivo(rs.getBoolean("ativo"));
         c.setTimeInMillis(rs.getTimestamp("data_entrada").getTime());
         s.setData_entrada(c);
         s.setSolicitacoes(rs.getString("solicitacoes"));
         s.setFotos(fotos_solicitacoes_id_solicitacao(s.getId()));
         if (s.isAtendida()) {
         	 s.setOrcamento(new OrcamentoDAO().orcamento_solicitacao(rs.getInt("orcamento_id")));;
         }
         s.setId(rs.getInt("id"));
         s.setVeiculo(new VeiculoDAO().veiculo_solicitacao(rs.getInt("veiculo_id")));
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
public List<Solicitacoes> veiculos(int id)
{
	try{
	 String sql = "SELECT * FROM SOLICITACOES s WHERE s.ativo = true AND s.veiculo_id = ? ORDER BY ID DESC";
     con = Conexao.Conectar();
     cmd = con.prepareStatement(sql);
     cmd.setInt(1, id);
     ResultSet rs = cmd.executeQuery();
     List<Solicitacoes> solicitacoes = new ArrayList<>();
     while(rs.next()){
 	    Calendar c = Calendar.getInstance();
         Solicitacoes s = new Solicitacoes();
         s.setAtendida(rs.getBoolean("atendida"));
         s.setAtivo(rs.getBoolean("ativo"));
         c.setTimeInMillis(rs.getTimestamp("data_entrada").getTime());
         s.setData_entrada(c);
         s.setSolicitacoes(rs.getString("solicitacoes"));
         s.setId(rs.getInt("id"));
         s.setFotos(fotos_solicitacoes_id_solicitacao(s.getId()));
         if (s.isAtendida()) {
        	 s.setOrcamento(new OrcamentoDAO().orcamento_solicitacao_veiculo(rs.getInt("orcamento_id")));;
         }
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
public Solicitacoes orcamento(int id)
{
	try{
	 String sql = "SELECT * FROM SOLICITACOES s WHERE s.orcamento_id = ? ORDER BY ID";
     con = Conexao.Conectar();
     cmd = con.prepareStatement(sql);
     cmd.setInt(1, id);
     ResultSet rs = cmd.executeQuery();
     List<Solicitacoes> solicitacoes = new ArrayList<>();
     while(rs.next()){
 	    Calendar c = Calendar.getInstance();
         Solicitacoes s = new Solicitacoes();
         s.setAtendida(rs.getBoolean("atendida"));
         s.setAtivo(rs.getBoolean("ativo"));
         c.setTimeInMillis(rs.getTimestamp("data_entrada").getTime());
         s.setData_entrada(c);
         s.setSolicitacoes(rs.getString("solicitacoes"));
         s.setVeiculo(new VeiculoDAO().veiculo_solicitacao(rs.getInt("veiculo_id")));
         s.setId(rs.getInt("id"));
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

public List<Fotos_Solicitacoes> fotos_solicitacoes_id_solicitacao(int id)
{
	try{
	 String sql = "SELECT * FROM Fotos_Solicitacoes s WHERE s.ativo = true AND s.solicitacoes_id = ? ORDER BY ID";
     con = Conexao.Conectar();
     cmd = con.prepareStatement(sql);
     cmd.setInt(1, id);
     ResultSet rs = cmd.executeQuery();
     List<Fotos_Solicitacoes> fotos = new ArrayList<>();
     while(rs.next()){
         Fotos_Solicitacoes f = new Fotos_Solicitacoes();
         f.setAtivo(rs.getBoolean("ativo"));
         f.setId(rs.getShort("id"));
         fotos.add(f);
     }
     Conexao.Desconectar(con);
     return fotos;
 } catch (SQLException e) {
     System.out.println("ERRO: " + e.getMessage());
     return null;
} finally {
     Conexao.Desconectar(con);
 }
}
public Fotos_Solicitacoes fotos_solicitacoes(int id)
{
	try{
	 String sql = "SELECT * FROM Fotos_Solicitacoes s WHERE s.id = ? ORDER BY ID";
     con = Conexao.Conectar();
     cmd = con.prepareStatement(sql);
     cmd.setInt(1, id);
     ResultSet rs = cmd.executeQuery();
     List<Fotos_Solicitacoes> fotos = new ArrayList<>();
     while(rs.next()){
         Fotos_Solicitacoes f = new Fotos_Solicitacoes();
         f.setAtivo(rs.getBoolean("ativo"));
         f.setId(rs.getShort("id"));
         f.setImg(rs.getBytes("img"));
         fotos.add(f);
     }
     Conexao.Desconectar(con);
     return fotos.get(0);
 } catch (SQLException e) {
     System.out.println("ERRO: " + e.getMessage());
     return null;
} finally {
     Conexao.Desconectar(con);
 }
}
public int solicitacao_id_orcamento(int id)
{
	try{
	 String sql = "SELECT * FROM SOLICITACOES s WHERE s.id = ?";
     con = Conexao.Conectar();
     cmd = con.prepareStatement(sql);
     cmd.setInt(1, id);
     ResultSet rs = cmd.executeQuery();
     while(rs.next()){
         return rs.getInt("orcamento_id");
     }
     return 0;
 } catch (SQLException e) {
     System.out.println("ERRO: " + e.getMessage());
     return 0;
} finally {
     Conexao.Desconectar(con);
 }
}
public int salvar_update(int id, int orc_id)
{
	try{
	 String sql = "UPDATE SOLICITACOES s SET s.orcamento_id = ? WHERE s.id = ?";
     con = Conexao.Conectar();
     cmd = con.prepareStatement(sql);
     cmd.setInt(1, orc_id);
     cmd.setInt(2, id);
     cmd.executeUpdate();
     return 0;
 } catch (SQLException e) {
     System.out.println("ERRO: " + e.getMessage());
     return 0;
} finally {
     Conexao.Desconectar(con);
 }
}

}
