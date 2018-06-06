package br.com.instagramlike.config.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableResourceServer
@EnableGlobalMethodSecurity(securedEnabled = true)
@ComponentScan(basePackages = "br.com.instagramlike")
public class ResourceServer extends ResourceServerConfigurerAdapter {

    @Autowired
    @Qualifier("authSecurityService")
    private UserDetailsService userDetailsService;

    private static final String[] WHITE_LIST = {
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**"
    };


    public ResourceServer() {
        super();
    }


    @Bean
    public AuthenticationProvider authProvider(){

        final DaoAuthenticationProvider authenticationProvider;
        authenticationProvider = new DaoAuthenticationProvider();

        authenticationProvider.setUserDetailsService(userDetailsService);

        return authenticationProvider;
    }


    @Autowired
    public void configureGlobal(final AuthenticationManagerBuilder managerBuilder){
        managerBuilder.authenticationProvider(authProvider());
    }




    @Override
    public void configure(HttpSecurity http) throws Exception {

        http
          .cors().and()
         .authorizeRequests()
             .antMatchers(WHITE_LIST).permitAll()
         .anyRequest()
         .authenticated()
           .and()
         .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
           .and()
         .csrf().disable();
    }


}
