package id.ac.ui.cs.advprog.afk3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;

@SpringBootApplication
@EnableConfigurationProperties
public class Afk3Application {

	String url_gcp_sell = "http://34.126.165.220";
	String url_local = "http://localhost:8081/";
	public static void main(String[] args) {
		SpringApplication.run(Afk3Application.class, args);
	}
	@Bean
	public RouterFunction<ServerResponse> toUserSellerList() {
		return route("userSeller/list").GET("/userSeller/list", http(url_gcp_sell)).build();
	}
	@Bean
	public RouterFunction<ServerResponse> toListingList() {
		return route("listing/list").GET("/listing/list", http(url_gcp_sell)).build();
	}
	@Bean
	public RouterFunction<ServerResponse> toUserSellerCreate() {
		return route("userSeller/create").GET("/userSeller/create", http(url_gcp_sell)).build();
	}
	@Bean
	public RouterFunction<ServerResponse> toPostUserSellerCreate() {
		return route("userSeller/create").POST("/userSeller/create", http(url_gcp_sell)).build();
	}
	@Bean
	public RouterFunction<ServerResponse> toPostUserSellerEdit() {
		return route("userSeller/create").POST("/userSeller/edit", http(url_gcp_sell)).build();
	}
	@Bean
	public RouterFunction<ServerResponse> toPostListingCreate() {
		return route("listing/create").POST("/listing/create", http(url_gcp_sell)).build();
	}
	@Bean
	public RouterFunction<ServerResponse> toPostListingEdit() {
		return route("listing/edit").POST("/listing/edit", http(url_gcp_sell)).build();
	}
	@Bean
	public RouterFunction<ServerResponse> toPostListingDelete() {
		return route("listing/delete").POST("/listing/delete", http(url_gcp_sell)).build();
	}
	@Bean
	public RouterFunction<ServerResponse> toPostOrderCreate() {
		return route("order/create").POST("/order/create", http(url_gcp_sell)).build();
	}
	@Bean
	public RouterFunction<ServerResponse> toPostOrderToSeller() {
		return route("order/to-seller").POST("/order/to-seller", http(url_gcp_sell)).build();
	}
	@Bean
	public RouterFunction<ServerResponse> toPostOrderSetStatus() {
		return route("order/set-status/**").POST("/order/set-status/**", http(url_gcp_sell)).build();
	}
}
