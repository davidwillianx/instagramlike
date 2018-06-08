package br.com.instagramlike.config;

import br.com.instagramlike.config.oauth2.AuthorizationServer;
import br.com.instagramlike.config.oauth2.OAuth2Security;
import br.com.instagramlike.config.oauth2.ResourceServer;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

public class App extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{Bootstrap.class, Persistence.class, Swagger.class, AuthorizationServer.class, OAuth2Security.class, ResourceServer.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[0];
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected Filter[] getServletFilters() {
        return new Filter[] { new Cors() };
    }

}
