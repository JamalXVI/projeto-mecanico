package br.com.jamalxvi.seguranca;


import javax.inject.Inject;

import br.com.caelum.vraptor.Accepts;
import br.com.caelum.vraptor.AroundCall;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.controller.ControllerMethod;
import br.com.caelum.vraptor.interceptor.SimpleInterceptorStack;
import br.com.jamalxvi.controller.ErrosController;


@Intercepts(after=AutorizacaoInterceptor.class)
public class AutorizacaoNivelInterceptor {
    private ControllerMethod controllerMethod;
    private UsuarioLogado usuarioLogado;
    private Result result;
    public AutorizacaoNivelInterceptor(){}
    @Inject
    public AutorizacaoNivelInterceptor(ControllerMethod controllerMethod, UsuarioLogado usuarioLogado, Result result){
        this.controllerMethod = controllerMethod;
        this.usuarioLogado = usuarioLogado;
        this.result = result;
    }
    @Accepts
    public boolean accepts(){
        return controllerMethod.containsAnnotation(NivelFuncionario.class);
    }
    @AroundCall
    public void intercept(SimpleInterceptorStack stack){
    	int nivel = 900;
    	NivelFuncionario[] annotations = controllerMethod.getMethod().getAnnotationsByType(NivelFuncionario.class);
    	for (NivelFuncionario nivelFuncionario : annotations) {
			nivel = nivelFuncionario.nivel();
		}
        if(usuarioLogado.isLogado()){
        	if (nivel >= usuarioLogado.getUsuario().getFuncao()) {
                stack.next();
			}else{
				result.redirectTo(ErrosController.class).erro_acesso();
			}
        	
        }else{
            result.redirectTo(ErrosController.class).erro_acesso();
        }
    }
}
