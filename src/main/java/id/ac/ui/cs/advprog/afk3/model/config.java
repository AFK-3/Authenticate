package id.ac.ui.cs.advprog.afk3.model;
import org.apache.catalina.filters.RequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.gateway.route.RouteLocator;
//import org.springframework.cloud.gateway.route.builder.BooleanSpec;
//import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
//import org.springframework.cloud.gateway.route.builder.UriSpec;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.function.RouterFunction;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;

//@Configuration
//public class config {
//
//    @Bean
//    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
//        // adding 2 rotes to first microservice as we need to log request body if method is POST
//        return builder.routes()
//                .route("sell",r -> r.path("/userSeller/list")
//                        .and().method("GET")
//                        .uri("http://34.126.165.220/userSeller/list"))
//                .build();
//    }
//
//    private boolean f(){
//        return true;
//    }
//
//
//}
