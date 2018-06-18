package br.com.jamalxvi.manual_dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.jamalxvi.infra.Conexao;
import br.com.jamalxvi.modelo.IdentificacaoProdutor;
import br.com.jamalxvi.modelo.Produtor;
public class ProdutorDAO {
    private PreparedStatement cmd;
	private Connection con;
public Collection<Produtor> fornecedores()
{
	try{
	 String sql = "SELECT * FROM Produtor f  WHERE f.ativo = true ORDER BY id  LIMIT 10000";
     con = Conexao.Conectar();
     cmd = con.prepareStatement(sql);
     ResultSet rs = cmd.executeQuery();
     Collection<Produtor> fornecedores = new ArrayList<>();
     while(rs.next()){
         Produtor produtor = new Produtor();
         produtor.setId(rs.getInt("id"));
         produtor.setAtivo(rs.getBoolean("ativo"));
         produtor.setBairro(rs.getString("bairro"));
         produtor.setCep(rs.getString("cep"));
         produtor.setCidade(rs.getString("cidade"));
         produtor.setComp(rs.getString("comp"));
         produtor.setEmail(rs.getString("email"));
         produtor.setEstado(rs.getString("estado"));
         produtor.setNome(rs.getString("nome"));
         produtor.setNum(rs.getString("num"));
         produtor.setRua(rs.getString("rua"));
         produtor.setCondicao_pagamento(rs.getShort("condicao_pagamento"));
         produtor.setIdentificacao(identificacao_fornecedor_id_fornecedor(produtor.getId()));
         produtor.setTelefone(new TelefonesDAO().telefone_produtor(produtor.getId()));
         fornecedores.add(produtor);
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
public Produtor fornecedor(int id)
{
	try{
	 String sql = "SELECT * FROM Produtor f  WHERE f.id = ? ORDER BY id";
     con = Conexao.Conectar();
     cmd = con.prepareStatement(sql);
     cmd.setInt(1, id);
     ResultSet rs = cmd.executeQuery();
     List<Produtor> produtores = new ArrayList<>();
     while(rs.next()){
         Produtor produtor = new Produtor();
         produtor.setId(rs.getInt("id"));
         produtor.setAtivo(rs.getBoolean("ativo"));
         produtor.setBairro(rs.getString("bairro"));
         produtor.setCep(rs.getString("cep"));
         produtor.setCidade(rs.getString("cidade"));
         produtor.setComp(rs.getString("comp"));
         produtor.setEmail(rs.getString("email"));
         produtor.setEstado(rs.getString("estado"));
         produtor.setNome(rs.getString("nome"));
         produtor.setNum(rs.getString("num"));
         produtor.setRua(rs.getString("rua"));
         produtor.setCondicao_pagamento(rs.getShort("condicao_pagamento"));
         produtor.setIdentificacao(identificacao_fornecedor_id_fornecedor(produtor.getId()));
         produtor.setTelefone(new TelefonesDAO().telefone_produtor(produtor.getId()));
         produtores.add(produtor);
     }
     Conexao.Desconectar(con);
     return produtores.get(0);
 } catch (SQLException e) {
     System.out.println("ERRO: " + e.getMessage());
     return null;
 } finally {
     Conexao.Desconectar(con);
 }
}
public Produtor produtor_conta(int id)
{
	try{
	 String sql = "SELECT * FROM Produtor f  WHERE f.id = ? ORDER BY id";
     con = Conexao.Conectar();
     cmd = con.prepareStatement(sql);
     cmd.setInt(1, id);
     ResultSet rs = cmd.executeQuery();
     List<Produtor> produtores = new ArrayList<>();
     while(rs.next()){
         Produtor produtor = new Produtor();
         produtor.setId(rs.getInt("id"));
         produtor.setAtivo(rs.getBoolean("ativo"));
         produtor.setBairro(rs.getString("bairro"));
         produtor.setCep(rs.getString("cep"));
         produtor.setCidade(rs.getString("cidade"));
         produtor.setComp(rs.getString("comp"));
         produtor.setEmail(rs.getString("email"));
         produtor.setEstado(rs.getString("estado"));
         produtor.setNome(rs.getString("nome"));
         produtor.setNum(rs.getString("num"));
         produtor.setRua(rs.getString("rua"));
         produtor.setCondicao_pagamento(rs.getShort("condicao_pagamento"));
         produtor.setIdentificacao(identificacao_fornecedor_id_fornecedor(produtor.getId()));
         produtor.setTelefone(new TelefonesDAO().telefone_produtor(produtor.getId()));
         produtores.add(produtor);
     }
     Conexao.Desconectar(con);
     return produtores.get(0);
 } catch (SQLException e) {
     System.out.println("ERRO: " + e.getMessage());
     return null;
 } finally {
     Conexao.Desconectar(con);
 }
}
public Collection<Produtor> fornecedor_nome(String nome)
{
	try{
	 String sql = "SELECT * FROM Produtor f  WHERE f.nome LIKE ? AND f.ativo = true ORDER BY id  LIMIT 25";
     con = Conexao.Conectar();
     cmd = con.prepareStatement(sql);
     cmd.setString(1, "%"+nome+"%");
     ResultSet rs = cmd.executeQuery();
     Collection<Produtor> produtores = new ArrayList<>();
     while(rs.next()){
         Produtor produtor = new Produtor();
         produtor.setId(rs.getInt("id"));
         produtor.setAtivo(rs.getBoolean("ativo"));
         produtor.setBairro(rs.getString("bairro"));
         produtor.setCep(rs.getString("cep"));
         produtor.setCidade(rs.getString("cidade"));
         produtor.setComp(rs.getString("comp"));
         produtor.setEmail(rs.getString("email"));
         produtor.setEstado(rs.getString("estado"));
         produtor.setNome(rs.getString("nome"));
         produtor.setNum(rs.getString("num"));
         produtor.setRua(rs.getString("rua"));
         produtor.setCondicao_pagamento(rs.getShort("condicao_pagamento"));
         produtor.setIdentificacao(identificacao_fornecedor_id_fornecedor(produtor.getId()));
         produtores.add(produtor);
     }
     Conexao.Desconectar(con);
     return produtores;
 } catch (SQLException e) {
     System.out.println("ERRO: " + e.getMessage());
     return null;
 } finally {
     Conexao.Desconectar(con);
 }
}
public IdentificacaoProdutor identificacao_fornecedor_id_fornecedor(int id)
{
	try{
	 String sql = "SELECT * FROM IdentificacaoProdutor f  WHERE f.produtor_id = ? ORDER BY id";
     con = Conexao.Conectar();
     cmd = con.prepareStatement(sql);
     cmd.setInt(1, id);
     ResultSet rs = cmd.executeQuery();
     List<IdentificacaoProdutor> identificacoes = new ArrayList<>();
     while(rs.next()){
         IdentificacaoProdutor f = new IdentificacaoProdutor();
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
public Produtor ultimo()
{
	try{
	 String sql = "SELECT * FROM Produtor f ORDER BY id DESC LIMIT 1";
     con = Conexao.Conectar();
     cmd = con.prepareStatement(sql);
     ResultSet rs = cmd.executeQuery();
     List<Produtor> produtores = new ArrayList<>();
     while(rs.next()){
    	 Produtor produtor = new Produtor();
         produtor.setId(rs.getInt("id"));
         produtor.setAtivo(rs.getBoolean("ativo"));
         produtor.setBairro(rs.getString("bairro"));
         produtor.setCep(rs.getString("cep"));
         produtor.setCidade(rs.getString("cidade"));
         produtor.setComp(rs.getString("comp"));
         produtor.setEmail(rs.getString("email"));
         produtor.setEstado(rs.getString("estado"));
         produtor.setNome(rs.getString("nome"));
         produtor.setNum(rs.getString("num"));
         produtor.setRua(rs.getString("rua"));
         produtor.setCondicao_pagamento(rs.getShort("condicao_pagamento"));
         produtores.add(produtor);
     }
     Conexao.Desconectar(con);
     return produtores.get(0);
 } catch (SQLException e) {
     System.out.println("ERRO: " + e.getMessage());
     return null;
 } finally {
     Conexao.Desconectar(con);
 }
}
}
