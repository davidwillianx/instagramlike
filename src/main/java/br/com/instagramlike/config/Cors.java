package br.com.instagramlike.config;


import com.google.common.collect.ImmutableList;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;


public class Cors extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("Tracking data flow");
        System.out.println(request.getHeader("Origin"));

        Set<String> allowedOrigins  = new HashSet<>(ImmutableList.of("http://localhost:8100"));
        String origin = request.getHeader("Origin");

        if(allowedOrigins.contains(origin)){

            System.out.println("Request Handler allowed you to send message to this server .. lets processs");

            response.addHeader("Access-Control-Allow-Origin", origin);

            if(request.getHeader("Access-Control-Request-Metehod") != null
                    && "OPTIONS".equalsIgnoreCase(request.getMethod()) ){

                response.addHeader("Access-Control-Request-Method", "GET, POST, PUT, DELETE");
                response.addHeader("Access-Control-Allow-Headers", "Content-Type");
                response.addHeader("Access-Control-Max-Age", "1");
            }

        }

        return super.preHandle(request, response, handler);

    }

}
