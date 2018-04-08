package br.com.instagramlike.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ComponentScan(basePackages = "br.com.instagramlike")
public class Bootstrap extends WebMvcConfigurerAdapter {

    @Bean
    public StandardServletMultipartResolver multipartResolver(){

        StandardServletMultipartResolver multipartResolver;
        multipartResolver = new  StandardServletMultipartResolver();

        return multipartResolver;
    }

}
