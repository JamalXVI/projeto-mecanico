package br.com.jamalxvi.manual_dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import br.com.jamalxvi.infra.Conexao;
import br.com.jamalxvi.modelo.Fotos_Solicitacoes;
import br.com.jamalxvi.modelo.IdentificacaoVeiculo;
public class VeiculoDAO {
    private PreparedStatement cmd;
	private Connection con;
public Collection<IdentificacaoVeiculo> veiculos()
{
	try{
	 String sql = "SELECT * FROM IdentificacaoVeiculo v  WHERE v.ativo = true ORDER BY ID DESC LIMIT 10000";
     con = Conexao.Conectar();
     cmd = con.prepareStatement(sql);
     ResultSet rs = cmd.executeQuery();
     Collection<IdentificacaoVeiculo> veiculos = new ArrayList<>();
     while(rs.next()){
         IdentificacaoVeiculo v = new IdentificacaoVeiculo();
         v.setAno(rs.getInt("ano"));
         v.setAtivo(rs.getBoolean("ativo"));
         v.setCor(rs.getString("cor"));
         v.setId(rs.getShort("id"));
         v.setPlaca(rs.getString("placa"));
         v.setTipo(rs.getString("tipo"));
         v.setModelo(new ModeloDAO().modelo_completo(rs.getInt("modelo_id")));
         v.setCliente(new ClienteDAO().cliente_completo(rs.getInt("cliente_id")));
         v.setSolicitacoes(new SolicitacoesDAO().veiculos(v.getId()));
         veiculos.add(v);
     }
     Conexao.Desconectar(con);
     return veiculos;
 } catch (SQLException e) {
     System.out.println("ERRO: " + e.getMessage());
     return null;
 } finally {
     Conexao.Desconectar(con);
 }
}
public IdentificacaoVeiculo ultimo()
{
	try{
	 String sql = "SELECT * FROM IdentificacaoVeiculo v ORDER BY id DESC LIMIT 1";
     con = Conexao.Conectar();
     cmd = con.prepareStatement(sql);
     ResultSet rs = cmd.executeQuery();
     List<IdentificacaoVeiculo> veiculos = new ArrayList<>();
     while(rs.next()){
         IdentificacaoVeiculo v = new IdentificacaoVeiculo();
         v.setAno(rs.getInt("ano"));
         v.setAtivo(rs.getBoolean("ativo"));
         v.setCor(rs.getString("cor"));
         v.setId(rs.getShort("id"));
         v.setPlaca(rs.getString("placa"));
         v.setTipo(rs.getString("tipo"));
         veiculos.add(v);
     }
     Conexao.Desconectar(con);
     return veiculos.get(0);
 } catch (SQLException e) {
     System.out.println("ERRO: " + e.getMessage());
     return null;
 } finally {
     Conexao.Desconectar(con);
 }
}

public IdentificacaoVeiculo veiculo(int id) {
	try {
		String sql = "SELECT * FROM IdentificacaoVeiculo v WHERE v.id = ? AND v.ativo = true ORDER BY ID";
		con = Conexao.Conectar();
		cmd = con.prepareStatement(sql);
		cmd.setInt(1, id);
		ResultSet rs = cmd.executeQuery();
		List<IdentificacaoVeiculo> veiculos = new ArrayList<>();
		while (rs.next()) {
			IdentificacaoVeiculo v = new IdentificacaoVeiculo();
			v.setAno(rs.getInt("ano"));
			v.setAtivo(rs.getBoolean("ativo"));
			v.setCor(rs.getString("cor"));
			v.setId(rs.getShort("id"));
			v.setPlaca(rs.getString("placa"));
			v.setTipo(rs.getString("tipo"));
			v.setModelo(new ModeloDAO().modelo_completo(rs.getInt("modelo_id")));
	         v.setCliente(new ClienteDAO().cliente_completo(rs.getInt("cliente_id")));
	         v.setSolicitacoes(new SolicitacoesDAO().veiculos(v.getId()));
			veiculos.add(v);
		}
	     Conexao.Desconectar(con);
		return veiculos.get(0);
	} catch (SQLException e) {
		System.out.println("ERRO: " + e.getMessage());
		return null;
	} finally {
		Conexao.Desconectar(con);
	}
}
public Collection<IdentificacaoVeiculo> pesquisa(String placa) {
	try {
		String sql = "SELECT * FROM IdentificacaoVeiculo v WHERE v.placa LIKE ? AND v.ativo = true ORDER BY ID";
		con = Conexao.Conectar();
		cmd = con.prepareStatement(sql);
		cmd.setString(1, "%"+placa+"%");
		ResultSet rs = cmd.executeQuery();
		Collection<IdentificacaoVeiculo> veiculos = new ArrayList<>();
		while (rs.next()) {
			IdentificacaoVeiculo v = new IdentificacaoVeiculo();
			v.setAno(rs.getInt("ano"));
			v.setAtivo(rs.getBoolean("ativo"));
			v.setCor(rs.getString("cor"));
			v.setId(rs.getShort("id"));
			v.setPlaca(rs.getString("placa"));
			v.setTipo(rs.getString("tipo"));
			v.setModelo(new ModeloDAO().modelo_completo(rs.getInt("modelo_id")));
	         v.setCliente(new ClienteDAO().cliente_completo(rs.getInt("cliente_id")));
	         v.setSolicitacoes(new SolicitacoesDAO().veiculos(v.getId()));
			veiculos.add(v);
		}
	     Conexao.Desconectar(con);
		return veiculos;
	} catch (SQLException e) {
		System.out.println("ERRO: " + e.getMessage());
		return null;
	} finally {
		Conexao.Desconectar(con);
	}
}
	public List<IdentificacaoVeiculo> cliente_id(int id) {
		try {
			String sql = "SELECT * FROM IdentificacaoVeiculo v WHERE v.cliente_id = ? AND v.ativo = true ORDER BY ID";
			con = Conexao.Conectar();
			cmd = con.prepareStatement(sql);
			cmd.setInt(1, id);
			ResultSet rs = cmd.executeQuery();
			List<IdentificacaoVeiculo> veiculos = new ArrayList<>();
			while (rs.next()) {
				IdentificacaoVeiculo v = new IdentificacaoVeiculo();
				v.setAno(rs.getInt("ano"));
				v.setAtivo(rs.getBoolean("ativo"));
				v.setCor(rs.getString("cor"));
				v.setId(rs.getShort("id"));
				v.setPlaca(rs.getString("placa"));
				v.setTipo(rs.getString("tipo"));
				v.setSolicitacoes(new SolicitacoesDAO().veiculos(v.getId()));
				v.setModelo(new ModeloDAO().modelo_completo(rs.getInt("modelo_id")));
				veiculos.add(v);
			}
   	     Conexao.Desconectar(con);
			return veiculos;
		} catch (SQLException e) {
			System.out.println("ERRO: " + e.getMessage());
			return null;
		} finally {
			Conexao.Desconectar(con);
		}
	}
	public List<IdentificacaoVeiculo> modelo_id(int id) {
		try {
			String sql = "SELECT * FROM IdentificacaoVeiculo v WHERE v.modelo_id = ? AND v.ativo = true ORDER BY ID";
			con = Conexao.Conectar();
			cmd = con.prepareStatement(sql);
			cmd.setInt(1, id);
			ResultSet rs = cmd.executeQuery();
			List<IdentificacaoVeiculo> veiculos = new ArrayList<>();
			while (rs.next()) {
				IdentificacaoVeiculo v = new IdentificacaoVeiculo();
				v.setAno(rs.getInt("ano"));
				v.setAtivo(rs.getBoolean("ativo"));
				v.setCor(rs.getString("cor"));
				v.setId(rs.getShort("id"));
				v.setPlaca(rs.getString("placa"));
				v.setTipo(rs.getString("tipo"));
				v.setSolicitacoes(new SolicitacoesDAO().veiculos(v.getId()));
				veiculos.add(v);
			}
   	     Conexao.Desconectar(con);
			return veiculos;
		} catch (SQLException e) {
			System.out.println("ERRO: " + e.getMessage());
			return null;
		} finally {
			Conexao.Desconectar(con);
		}
	}
	public IdentificacaoVeiculo veiculo_solicitacao(int id) {
		try {
			String sql = "SELECT * FROM IdentificacaoVeiculo v WHERE v.id = ? ORDER BY ID";
			con = Conexao.Conectar();
			cmd = con.prepareStatement(sql);
			cmd.setInt(1, id);
			ResultSet rs = cmd.executeQuery();
			List<IdentificacaoVeiculo> veiculos = new ArrayList<>();
			while (rs.next()) {
				IdentificacaoVeiculo v = new IdentificacaoVeiculo();
				v.setAno(rs.getInt("ano"));
				v.setAtivo(rs.getBoolean("ativo"));
				v.setCor(rs.getString("cor"));
				v.setId(rs.getShort("id"));
				v.setPlaca(rs.getString("placa"));
				v.setTipo(rs.getString("tipo"));
				
				v.setCliente(new ClienteDAO().cliente_completo(rs.getInt("cliente_id")));
				v.setModelo(new ModeloDAO().modelo_completo(rs.getInt("modelo_id")));
				veiculos.add(v);
			}
   	     Conexao.Desconectar(con);
			return veiculos.get(0);
		} catch (SQLException e) {
			System.out.println("ERRO: " + e.getMessage());
			return null;
		} finally {
			Conexao.Desconectar(con);
		}
	}
	public Fotos_Solicitacoes foto_solicitacao(int id)
	{
		return new SolicitacoesDAO().fotos_solicitacoes(id);
	}
}
