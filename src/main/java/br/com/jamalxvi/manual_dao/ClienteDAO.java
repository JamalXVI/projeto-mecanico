package br.com.jamalxvi.manual_dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import br.com.jamalxvi.infra.Conexao;
import br.com.jamalxvi.modelo.Cliente;
import br.com.jamalxvi.modelo.FotoPerfil;
import br.com.jamalxvi.modelo.Identificacao;
public class ClienteDAO{
    private PreparedStatement cmd;
	private Connection con;
    public ClienteDAO()
    {
    }
public Collection<Cliente> clientes()
{
	try{
	 String sql = "SELECT * FROM Cliente c  WHERE c.ativo = true ORDER BY id LIMIT 10000";
     con = Conexao.Conectar();
     cmd = con.prepareStatement(sql);
     ResultSet rs = cmd.executeQuery();
     Collection<Cliente> clientes = new ArrayList<>();
     while(rs.next()){
         Cliente c = new Cliente();
         c.setId(rs.getInt("id"));
         c.setAtivo(rs.getBoolean("ativo"));
         c.setBai(rs.getString("bai"));
         c.setCep(rs.getString("cep"));
         c.setCidade(rs.getString("cidade"));
         c.setComp(rs.getString("comp"));
         c.setEmail(rs.getString("email"));
         c.setEstado(rs.getString("estado"));
         c.setInformacao(rs.getString("informacao"));
         c.setNome(rs.getString("nome"));
         c.setNum(rs.getString("num"));
         c.setRua(rs.getString("rua"));
         c.setIdentificacao(identificacao_id_cliente(c.getId()));
         clientes.add(c);
     }
     Conexao.Desconectar(con);
     return clientes;
 } catch (SQLException e) {
     System.out.println("ERRO: " + e.getMessage());
     return null;
 } finally {
     Conexao.Desconectar(con);
 }
}
public Cliente cliente(int id)
{
	try{
		 String sql = "SELECT * FROM Cliente c WHERE c.id = ? ORDER BY id";
	     con = Conexao.Conectar();
	     cmd = con.prepareStatement(sql);
	     cmd.setInt(1, id);
	     ResultSet rs = cmd.executeQuery();
	     List<Cliente> clientes = new ArrayList<>();
	     while(rs.next()){
	         Cliente c = new Cliente();
	         c.setId(rs.getInt("id"));
	         c.setAtivo(rs.getBoolean("ativo"));
	         c.setBai(rs.getString("bai"));
	         c.setCep(rs.getString("cep"));
	         c.setCidade(rs.getString("cidade"));
	         c.setComp(rs.getString("comp"));
	         c.setEmail(rs.getString("email"));
	         c.setEstado(rs.getString("estado"));
	         System.out.println(rs.getString("informacao"));
	         c.setInformacao(rs.getString("informacao"));
	         c.setNome(rs.getString("nome"));
	         c.setNum(rs.getString("num"));
	         c.setRua(rs.getString("rua"));
	         c.setIdentificacao(identificacao_id_cliente(c.getId()));
	         clientes.add(c);
	     }
	     Conexao.Desconectar(con);
	     return clientes.get(0);
	 } catch (SQLException e) {
	     System.out.println("ERRO: " + e.getMessage());
	     return null;
	 } finally {
	     Conexao.Desconectar(con);
	 }
}
public Cliente cliente_completo(int id)
{
	try{
		 String sql = "SELECT * FROM Cliente c WHERE c.id = ? ORDER BY id";
		 con = Conexao.Conectar();
	     cmd = con.prepareStatement(sql);
	     cmd.setInt(1, id);
	     ResultSet rs = cmd.executeQuery();
	     List<Cliente> clientes = new ArrayList<>();
	     while(rs.next()){
	         Cliente c = new Cliente();
	         c.setId(rs.getInt("id"));
	         c.setAtivo(rs.getBoolean("ativo"));
	         c.setBai(rs.getString("bai"));
	         c.setCep(rs.getString("cep"));
	         c.setCidade(rs.getString("cidade"));
	         c.setComp(rs.getString("comp"));
	         c.setEmail(rs.getString("email"));
	         c.setEstado(rs.getString("estado"));
	         c.setInformacao(rs.getString("informacao"));
	         c.setNome(rs.getString("nome"));
	         c.setNum(rs.getString("num"));
	         c.setRua(rs.getString("rua"));
	         c.setIdentificacao(identificacao_id_cliente(c.getId()));
	         
	         c.setTelefones(new TelefonesDAO().telefone_cliente(id));
	         clientes.add(c);
	     }
	     Conexao.Desconectar(con);
	     return clientes.get(0);
	 } catch (SQLException e) {
	     System.out.println("ERRO: " + e.getMessage());
	     return null;
	 } finally {
	     Conexao.Desconectar(con);
	 }
}
public Cliente ultimo()
{
	try{
		 String sql = "SELECT * FROM Cliente c ORDER BY id DESC LIMIT 1";
		 con = Conexao.Conectar();
	     cmd = con.prepareStatement(sql);
	     ResultSet rs = cmd.executeQuery();
	     List<Cliente> clientes = new ArrayList<>();
	     while(rs.next()){
	         Cliente c = new Cliente();
	         c.setId(rs.getInt("id"));
	         c.setAtivo(rs.getBoolean("ativo"));
	         c.setBai(rs.getString("bai"));
	         c.setCep(rs.getString("cep"));
	         c.setCidade(rs.getString("cidade"));
	         c.setComp(rs.getString("comp"));
	         c.setEmail(rs.getString("email"));
	         c.setEstado(rs.getString("estado"));
	         c.setInformacao(rs.getString("informacao"));
	         c.setNome(rs.getString("nome"));
	         c.setNum(rs.getString("num"));
	         c.setRua(rs.getString("rua"));
	         clientes.add(c);
	     }
	     Conexao.Desconectar(con);
	     return clientes.get(0);
	 } catch (SQLException e) {
	     System.out.println("ERRO: " + e.getMessage());
	     return null;
	 } finally {
	     Conexao.Desconectar(con);
	 }
}
public Collection<Cliente> cliente_nome(String nome)
{
	try{
		 String sql = "SELECT * FROM Cliente c WHERE c.nome LIKE ? AND c.ativo = true ORDER BY id  LIMIT 25";
		 con = Conexao.Conectar();
	     cmd = con.prepareStatement(sql);
	     cmd.setString(1, "%"+nome+"%");
	     ResultSet rs = cmd.executeQuery();
	     Collection<Cliente> clientes = new ArrayList<>();
	     while(rs.next()){
	         Cliente c = new Cliente();
	         c.setId(rs.getInt("id"));
	         c.setAtivo(rs.getBoolean("ativo"));
	         c.setBai(rs.getString("bai"));
	         c.setCep(rs.getString("cep"));
	         c.setCidade(rs.getString("cidade"));
	         c.setComp(rs.getString("comp"));
	         c.setEmail(rs.getString("email"));
	         c.setEstado(rs.getString("estado"));
	         c.setInformacao(rs.getString("informacao"));
	         c.setNome(rs.getString("nome"));
	         c.setNum(rs.getString("num"));
	         c.setRua(rs.getString("rua"));
	         c.setIdentificacao(identificacao_id_cliente(c.getId()));
	         clientes.add(c);
	     }
	     Conexao.Desconectar(con);
	     return clientes;
	 } catch (SQLException e) {
	     System.out.println("ERRO: " + e.getMessage());
	     return null;
	 } finally {
	     Conexao.Desconectar(con);
	 }
}
public Collection<Cliente> cliente_informacao(String nome)
{
	try{
		 String sql = "SELECT * FROM Cliente c WHERE c.informacao LIKE ? AND c.ativo = true ORDER BY id  LIMIT 25";
		 con = Conexao.Conectar();
	     cmd = con.prepareStatement(sql);
	     cmd.setString(1, "%"+nome+"%");
	     ResultSet rs = cmd.executeQuery();
	     Collection<Cliente> clientes = new ArrayList<>();
	     while(rs.next()){
	         Cliente c = new Cliente();
	         c.setId(rs.getInt("id"));
	         c.setAtivo(rs.getBoolean("ativo"));
	         c.setBai(rs.getString("bai"));
	         c.setCep(rs.getString("cep"));
	         c.setCidade(rs.getString("cidade"));
	         c.setComp(rs.getString("comp"));
	         c.setEmail(rs.getString("email"));
	         c.setEstado(rs.getString("estado"));
	         c.setInformacao(rs.getString("informacao"));
	         c.setNome(rs.getString("nome"));
	         c.setNum(rs.getString("num"));
	         c.setRua(rs.getString("rua"));
	         c.setIdentificacao(identificacao_id_cliente(c.getId()));
	         clientes.add(c);
	     }
	     Conexao.Desconectar(con);
	     return clientes;
	 } catch (SQLException e) {
	     System.out.println("ERRO: " + e.getMessage());
	     return null;
	 } finally {
	     Conexao.Desconectar(con);
	 }
}
public Collection<Cliente> cliente_identidade(String nome)
{
	try{
		 String sql = "SELECT * FROM Cliente c WHERE c.ativo = true ORDER BY id  LIMIT 25";
		 con = Conexao.Conectar();
	     cmd = con.prepareStatement(sql);
	     ResultSet rs = cmd.executeQuery();
	     Collection<Cliente> clientes = new ArrayList<>();
	     while(rs.next()){
	         Cliente c = new Cliente();
	         c.setId(rs.getInt("id"));
	         c.setAtivo(rs.getBoolean("ativo"));
	         c.setBai(rs.getString("bai"));
	         c.setCep(rs.getString("cep"));
	         c.setCidade(rs.getString("cidade"));
	         c.setComp(rs.getString("comp"));
	         c.setEmail(rs.getString("email"));
	         c.setEstado(rs.getString("estado"));
	         c.setInformacao(rs.getString("informacao"));
	         c.setNome(rs.getString("nome"));
	         c.setNum(rs.getString("num"));
	         c.setRua(rs.getString("rua"));
	         c.setIdentificacao(identificacao_id_cliente(c.getId()));
	         if ((c.getIdentificacao().getTipo().equals("CPF") && c.getIdentificacao().getCpf().contains(nome)) ||
	        		 (c.getIdentificacao().getTipo().equals("CNPJ") && c.getIdentificacao().getCnpj().contains(nome))) {
	        	 clientes.add(c);
			}
	         
	     }
	     Conexao.Desconectar(con);
	     return clientes;
	 } catch (SQLException e) {
	     System.out.println("ERRO: " + e.getMessage());
	     return null;
	 } finally {
	     Conexao.Desconectar(con);
	 }
}
public Identificacao identificacao_id_cliente(int id)
{
	try{
		 String sql = "SELECT * FROM Identificacao i WHERE i.cliente_id = ? ORDER BY id";
		 con = Conexao.Conectar();
	     cmd = con.prepareStatement(sql);
	     cmd.setInt(1, id);
	     ResultSet rs = cmd.executeQuery();
	     List<Identificacao> identificacoes = new ArrayList<>();
	     while(rs.next()){
	         Identificacao identificacao = new Identificacao();
	         identificacao.setCnpj(rs.getString("cnpj"));
	         identificacao.setCpf(rs.getString("cpf"));
	         identificacao.setId(rs.getShort("cliente_id"));
	         identificacao.setInscricao_estadual(rs.getString("inscricao_estadual"));
	         identificacao.setRg(rs.getString("rg"));
	         identificacao.setInscricao_municipal(rs.getString("inscricao_municipal"));
	         identificacao.setTipo(rs.getString("tipo"));
	         identificacoes.add(identificacao);
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
public List<FotoPerfil> foto_perfil_cliente_id(int id)
{
	try{
		 String sql = "SELECT * FROM FotoPerfil f WHERE f.cliente_id = ? AND f.ativo = true ORDER BY id";
		 con = Conexao.Conectar();
	     cmd = con.prepareStatement(sql);
	     cmd.setInt(1, id);
	     ResultSet rs = cmd.executeQuery();
	     List<FotoPerfil> fotos = new ArrayList<>();
	     while(rs.next()){
	         FotoPerfil foto = new FotoPerfil();
	         foto.setImg(rs.getBytes("img"));
	         foto.setAtivo(rs.getBoolean("ativo"));
	         foto.setId(rs.getInt("id"));
	         fotos.add(foto);
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
public Cliente preparar_cliente(Cliente cliente)
{
	cliente.setFotos(foto_perfil_cliente_id(cliente.getId()));
	cliente.setTelefones(new TelefonesDAO().telefone_cliente(cliente.getId()));
	cliente.setVeiculos(new VeiculoDAO().cliente_id(cliente.getId()));
	cliente.setAutorizados(new ExecutadoDAO().autorizador(cliente.getId())); 
	return cliente;
}
}
