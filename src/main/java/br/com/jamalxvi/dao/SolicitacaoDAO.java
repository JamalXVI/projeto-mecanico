package br.com.jamalxvi.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.hibernate.CacheMode;
import org.hibernate.annotations.QueryHints;

import br.com.caelum.vraptor.observer.upload.UploadedFile;
import br.com.jamalxvi.manual_dao.SolicitacoesDAO;
import br.com.jamalxvi.modelo.Fotos_Solicitacoes;
import br.com.jamalxvi.modelo.Solicitacoes;

@RequestScoped
public class SolicitacaoDAO {
	private EntityManager manager;
	private SolicitacoesDAO solicitacoesDAO;
	public SolicitacaoDAO(){
		this(null, null);
	}
	@Inject
	public SolicitacaoDAO(EntityManager manager, SolicitacoesDAO solicitacoesDAO){
		this.manager = manager;
		this.solicitacoesDAO = solicitacoesDAO;
	}
	public void salvar(Solicitacoes solicitacao, UploadedFile[] imagem)
	{
		manager.getTransaction().begin();
		solicitacao.setAtivo(true);
		manager.persist(solicitacao);
		if (imagem != null) {
			if (imagem.length > 0) {
				for (UploadedFile img : imagem) {
					byte [] arquivo = converter_imagem(img);
					if (arquivo != null) {
						Fotos_Solicitacoes foto = new Fotos_Solicitacoes();
						foto.setAtivo(true);
						foto.setSolicitacoes(solicitacao);
						foto.setImg(arquivo);
						manager.persist(foto);
					}
					
				}
			}
		}
		
		manager.getTransaction().commit();
	}
	public Collection<Solicitacoes> listar_ativas()
	{
		Collection<Solicitacoes> ativas = solicitacoesDAO.ativas();
		return ativas;
	}
	public Collection<Solicitacoes> listar_inativas()
	{
		return solicitacoesDAO.inativas();
	}
	public Solicitacoes pesquisar(int id)
	{
		return solicitacoesDAO.solicitacao(id);
	}
	private byte[] converter_imagem(UploadedFile imagem) {
		if (imagem == null) {
			return null;
		}
		//--- getters and setters
		long size = imagem.getSize();
	    System.out.println("File size: " + size);  

	    InputStream stream = imagem.getFile();
	    byte[] buffer = new byte[(int) size];  
	    try {
			stream.read(buffer, 0, (int) size);
		    stream.close();
		    return buffer;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}  
		
	}
	public Solicitacoes deletar(int id)
	{
		manager.getTransaction().begin();
		TypedQuery<Solicitacoes> query = manager.createQuery("select s from Solicitacoes s where s.id = :pId",
				Solicitacoes.class).setHint(QueryHints.CACHE_MODE, CacheMode.IGNORE);
		query.setParameter("pId", id);
		Solicitacoes solicitacoes = query.getResultList().get(0);
		solicitacoes.setAtivo(false);
		manager.getTransaction().commit();manager.clear();
		return solicitacoes;
	}
	
	public int decrescente()
	{
		
		return solicitacoesDAO.ultimo().getId();
	}
	public Collection<Solicitacoes> pesquisar_nome(String pesquisa)
	{
		if (pesquisa == null) {
			pesquisa = "";
		}
		return solicitacoesDAO.solicitacao_nome(pesquisa);
	}
	public Collection<Solicitacoes> pesquisar_id(String pesquisa)
	{
		if (pesquisa == null) {
			pesquisa = "";
		}
		return solicitacoesDAO.solicitacao_id(pesquisa);
	}
}
