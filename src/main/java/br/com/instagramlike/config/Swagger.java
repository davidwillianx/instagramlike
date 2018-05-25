package br.com.instagramlike.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.AuthorizationCodeGrantBuilder;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

@Configuration
@EnableSwagger2
public class Swagger {

    private static final String AUTH_URL = "http://localhost:8080/";
    private static final String CLIENT_ID  = "instagram-like-app";
    private static final String CLIENT_SECRET = "b2b2d2d23";

    @Bean
    public Docket api(){

        return new Docket(DocumentationType.SWAGGER_2)
                    .select()
                    .apis(RequestHandlerSelectors.any())
                    .paths(PathSelectors.any())
                    .build()
                    .securitySchemes(Arrays.asList(securityScheme()))
                    .securityContexts(Arrays.asList(securityContext()));
    }


    @Bean
    public SecurityConfiguration security(){

        return SecurityConfigurationBuilder.builder()
                    .clientId(CLIENT_ID)
                    .clientSecret(CLIENT_SECRET)
                    .scopeSeparator(" ")
                    .useBasicAuthenticationWithAccessCodeGrant(true)
                    .build();
    }

    private SecurityScheme securityScheme(){

        GrantType grantType;
        SecurityScheme oauth;
        TokenEndpoint endpoint = new TokenEndpoint(AUTH_URL + "/token", "oauthtoken");
        TokenRequestEndpoint requestEndpoint = new TokenRequestEndpoint(AUTH_URL + "/authorize", CLIENT_ID, CLIENT_ID);


        grantType = new AuthorizationCodeGrantBuilder()
                            .tokenEndpoint(endpoint)
                            .tokenRequestEndpoint(requestEndpoint)
                            .build();


        oauth = new OAuthBuilder().name("instagramlike-oauth")
                            .grantTypes(Arrays.asList(grantType))
                            .scopes(Arrays.asList(scopes()))
                            .build();

        return oauth;
    }

    private SecurityContext securityContext(){
        return SecurityContext.builder()
                    .securityReferences(Arrays.asList(
                       new SecurityReference("instagramlike-oauth", scopes())
                    ))
                    .forPaths(PathSelectors.any())
                    .build();

    }


    private AuthorizationScope[] scopes(){

        AuthorizationScope[] scopes = {
                new AuthorizationScope("read", "read ops"),
                new AuthorizationScope("write", "read ops")
        };

         return scopes;
    }

}
