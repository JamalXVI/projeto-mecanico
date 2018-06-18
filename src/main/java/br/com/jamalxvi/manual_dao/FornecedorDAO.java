package br.com.jamalxvi.manual_dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.jamalxvi.infra.Conexao;
import br.com.jamalxvi.modelo.Fornecedor;
import br.com.jamalxvi.modelo.IdentificacaoFornecedor;
public class FornecedorDAO {
    private PreparedStatement cmd;
	private Connection con;
public Collection<Fornecedor> fornecedores()
{
	try{
	 String sql = "SELECT * FROM Fornecedor f  WHERE f.ativo = true ORDER BY id  LIMIT 10000";
     con = Conexao.Conectar();
     cmd = con.prepareStatement(sql);
     ResultSet rs = cmd.executeQuery();
     Collection<Fornecedor> fornecedores = new ArrayList<>();
     while(rs.next()){
         Fornecedor fornecedor = new Fornecedor();
         fornecedor.setId(rs.getInt("id"));
         fornecedor.setAtivo(rs.getBoolean("ativo"));
         fornecedor.setBairro(rs.getString("bairro"));
         fornecedor.setCep(rs.getString("cep"));
         fornecedor.setCidade(rs.getString("cidade"));
         fornecedor.setComp(rs.getString("comp"));
         fornecedor.setEmail(rs.getString("email"));
         fornecedor.setEstado(rs.getString("estado"));
         fornecedor.setNome(rs.getString("nome"));
         fornecedor.setNum(rs.getString("num"));
         fornecedor.setRua(rs.getString("rua"));
         fornecedor.setPorcentagem(rs.getBigDecimal("porcentagem"));
         fornecedor.setCondicao_pagamento(rs.getShort("condicao_pagamento"));
         fornecedor.setIdentificacao(identificacao_fornecedor_id_fornecedor(fornecedor.getId()));
         fornecedor.setPecas(new PecasDAO().peca_fornecedor(fornecedor.getId()));
         fornecedores.add(fornecedor);
     }
     Conexao.Desconectar(con);
     return fornecedores;
 } catch (SQLException e) {
     System.out.println("ERRO: " + e.getMessage());
     return null;
 } finally {
     Conexao.Desconectar(con);
 }
}
public Fornecedor fornecedor(int id)
{
	try{
	 String sql = "SELECT * FROM Fornecedor f  WHERE f.id = ? ORDER BY id";
     con = Conexao.Conectar();
     cmd = con.prepareStatement(sql);
     cmd.setInt(1, id);
     ResultSet rs = cmd.executeQuery();
     List<Fornecedor> fornecedores = new ArrayList<>();
     while(rs.next()){
         Fornecedor fornecedor = new Fornecedor();
         fornecedor.setId(rs.getInt("id"));
         fornecedor.setAtivo(rs.getBoolean("ativo"));
         fornecedor.setBairro(rs.getString("bairro"));
         fornecedor.setCep(rs.getString("cep"));
         fornecedor.setCidade(rs.getString("cidade"));
         fornecedor.setComp(rs.getString("comp"));
         fornecedor.setEmail(rs.getString("email"));
         fornecedor.setEstado(rs.getString("estado"));
         fornecedor.setNome(rs.getString("nome"));
         fornecedor.setNum(rs.getString("num"));
         fornecedor.setRua(rs.getString("rua"));
         fornecedor.setPorcentagem(rs.getBigDecimal("porcentagem"));
         fornecedor.setCondicao_pagamento(rs.getShort("condicao_pagamento"));
         fornecedor.setIdentificacao(identificacao_fornecedor_id_fornecedor(fornecedor.getId()));
         fornecedor.setTelefone(new TelefonesDAO().telefone_fornecedor(fornecedor.getId()));
         fornecedor.setPecas(new PecasDAO().peca_fornecedor(fornecedor.getId()));
         fornecedores.add(fornecedor);
     }
     Conexao.Desconectar(con);
     return fornecedores.get(0);
 } catch (SQLException e) {
     System.out.println("ERRO: " + e.getMessage());
     return null;
 } finally {
     Conexao.Desconectar(con);
 }
}
public Fornecedor fornecedor_peca(int id)
{
	try{
	 String sql = "SELECT * FROM Fornecedor f  WHERE f.id = ? ORDER BY id";
     con = Conexao.Conectar();
     cmd = con.prepareStatement(sql);
     cmd.setInt(1, id);
     ResultSet rs = cmd.executeQuery();
     List<Fornecedor> fornecedores = new ArrayList<>();
     while(rs.next()){
         Fornecedor fornecedor = new Fornecedor();
         fornecedor.setId(rs.getInt("id"));
         fornecedor.setAtivo(rs.getBoolean("ativo"));
         fornecedor.setBairro(rs.getString("bairro"));
         fornecedor.setCep(rs.getString("cep"));
         fornecedor.setCidade(rs.getString("cidade"));
         fornecedor.setComp(rs.getString("comp"));
         fornecedor.setEmail(rs.getString("email"));
         fornecedor.setEstado(rs.getString("estado"));
         fornecedor.setPorcentagem(rs.getBigDecimal("porcentagem"));
      fornecedor.setNome(rs.getString("nome"));
      fornecedor.setNum(rs.getString("num"));
      fornecedor.setRua(rs.getString("rua"));
      fornecedor.setCondicao_pagamento(rs.getShort("condicao_pagamento"));
      fornecedores.add(fornecedor);
     }
     Conexao.Desconectar(con);
     return fornecedores.get(0);
 } catch (SQLException e) {
     System.out.println("ERRO: " + e.getMessage());
     return null;
 } finally {
     Conexao.Desconectar(con);
 }
}
public Collection<Fornecedor> fornecedor_nome(String nome)
{
	try{
	 String sql = "SELECT * FROM Fornecedor f  WHERE f.nome LIKE ? AND f.ativo = true ORDER BY id  LIMIT 25";
     con = Conexao.Conectar();
     cmd = con.prepareStatement(sql);
     cmd.setString(1, "%"+nome+"%");
     ResultSet rs = cmd.executeQuery();
     Collection<Fornecedor> fornecedores = new ArrayList<>();
     while(rs.next()){
         Fornecedor fornecedor = new Fornecedor();
         fornecedor.setId(rs.getInt("id"));
         fornecedor.setAtivo(rs.getBoolean("ativo"));
         fornecedor.setBairro(rs.getString("bairro"));
         fornecedor.setCep(rs.getString("cep"));
         fornecedor.setCidade(rs.getString("cidade"));
         fornecedor.setComp(rs.getString("comp"));
         fornecedor.setEmail(rs.getString("email"));
         fornecedor.setEstado(rs.getString("estado"));
         fornecedor.setPorcentagem(rs.getBigDecimal("porcentagem"));
         fornecedor.setNome(rs.getString("nome"));
         fornecedor.setNum(rs.getString("num"));
         fornecedor.setRua(rs.getString("rua"));
         fornecedor.setCondicao_pagamento(rs.getShort("condicao_pagamento"));
         fornecedor.setIdentificacao(identificacao_fornecedor_id_fornecedor(fornecedor.getId()));
         fornecedor.setPecas(new PecasDAO().peca_fornecedor(fornecedor.getId()));
         fornecedores.add(fornecedor);
     }
     Conexao.Desconectar(con);
     return fornecedores;
 } catch (SQLException e) {
     System.out.println("ERRO: " + e.getMessage());
     return null;
 } finally {
     Conexao.Desconectar(con);
 }
}
public IdentificacaoFornecedor identificacao_fornecedor_id_fornecedor(int id)
{
	try{
	 String sql = "SELECT * FROM IdentificacaoFornecedor f  WHERE f.fornecedor_id = ? ORDER BY id";
     con = Conexao.Conectar();
     cmd = con.prepareStatement(sql);
     cmd.setInt(1, id);
     ResultSet rs = cmd.executeQuery();
     List<IdentificacaoFornecedor> identificacoes = new ArrayList<>();
     while(rs.next()){
         IdentificacaoFornecedor f = new IdentificacaoFornecedor();
         f.setCnpj(rs.getString("cnpj"));
         f.setId(rs.getShort("id"));
         f.setInscricao_estadual(rs.getString("inscricao_estadual"));
         identificacoes.add(f);
     }
     Conexao.Desconectar(con);
     return identificacoes.get(0);
 } catch (SQLException e) {
     System.out.println("ERRO: " + e.getMessage());
     return null;
 } finally {
     Conexao.Desconectar(con);
 }
}
public Fornecedor ultimo()
{
	try{
	 String sql = "SELECT * FROM Fornecedor f ORDER BY id DESC LIMIT 1";
     con = Conexao.Conectar();
     cmd = con.prepareStatement(sql);
     ResultSet rs = cmd.executeQuery();
     List<Fornecedor> fornecedores = new ArrayList<>();
     while(rs.next()){
         Fornecedor fornecedor = new Fornecedor();
         fornecedor.setId(rs.getInt("id"));
         fornecedor.setAtivo(rs.getBoolean("ativo"));
         fornecedor.setBairro(rs.getString("bairro"));
         fornecedor.setCep(rs.getString("cep"));
         fornecedor.setCidade(rs.getString("cidade"));
         fornecedor.setComp(rs.getString("comp"));
         fornecedor.setEmail(rs.getString("email"));
         fornecedor.setEstado(rs.getString("estado"));
         fornecedor.setNome(rs.getString("nome"));
         fornecedor.setNum(rs.getString("num"));
         fornecedor.setRua(rs.getString("rua"));
         fornecedor.setCondicao_pagamento(rs.getShort("condicao_pagamento"));
         fornecedor.setPorcentagem(rs.getBigDecimal("porcentagem"));
         fornecedores.add(fornecedor);
     }
     Conexao.Desconectar(con);
     return fornecedores.get(0);
 } catch (SQLException e) {
     System.out.println("ERRO: " + e.getMessage());
     return null;
 } finally {
     Conexao.Desconectar(con);
 }
}
}
