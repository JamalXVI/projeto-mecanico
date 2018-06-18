package br.com.jamalxvi.seguranca;

import javax.inject.Inject;

import br.com.caelum.vraptor.Accepts;
import br.com.caelum.vraptor.AroundCall;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.interceptor.SimpleInterceptorStack;
import br.com.jamalxvi.addon.Endereco_Mec;


@Intercepts
public class NomeInterceptor {
    private Result result;
    public NomeInterceptor(){}
    @Inject
    public NomeInterceptor(Result result){
        this.result = result;
    }
    @Accepts
    public boolean accepts(){
        return true;
    }
    @AroundCall
    public void intercept(SimpleInterceptorStack stack){
    	result.include("nome_mec", Endereco_Mec.nome);
    	stack.next();
    }
}
