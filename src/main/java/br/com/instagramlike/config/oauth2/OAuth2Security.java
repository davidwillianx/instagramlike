package br.com.instagramlike.config.oauth2;

import com.google.common.collect.ImmutableList;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class OAuth2Security extends WebSecurityConfigurerAdapter {

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors();
    }





    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration cors;
        UrlBasedCorsConfigurationSource configurationSource;

        cors = new CorsConfiguration();
        configurationSource = new UrlBasedCorsConfigurationSource();

        cors.setAllowedOrigins(Arrays.asList("*"));
        cors.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "HEAD"));
        cors.setAllowCredentials(true);
        cors.setAllowedHeaders(ImmutableList.of("Authorization", "Cache-Control", "Content-Type"));


        configurationSource.registerCorsConfiguration("/**", cors);


        return configurationSource;
    }


}
