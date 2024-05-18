package id.ac.ui.cs.advprog.afk3;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;
import org.springframework.web.bind.annotation.CrossOrigin;

import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;

@CrossOrigin(origins = "*")
@SpringBootApplication
public class Afk3Application {

	@Value("${app.sell-domain}")
	String url_gcp_sell;
	String URL_GCP_PAYMENT = "http://34.124.178.74";
	public static void main(String[] args) {
		SpringApplication.run(Afk3Application.class, args);
	}
	@Bean
	public RouterFunction<ServerResponse> toListingList() {
		return route("listing/list").GET("/listing/list", http(url_gcp_sell)).build();
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
	public RouterFunction<ServerResponse> toGetListingGetById(){
		return route("listing/get-by-id/**").GET("/listing/get-by-id/**", http(url_gcp_sell)).build();
	}
	@Bean
	public RouterFunction<ServerResponse> toGetListingGetBySeller(){
		return route("listing/get-by-seller").GET("/listing/get-by-seller", http(url_gcp_sell)).build();
	}

	@Bean
	public RouterFunction<ServerResponse> toGetListingGetAll(){
		return route("listing/get-all").GET("/listing/get-all", http(url_gcp_sell)).build();
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

	@Bean
	public RouterFunction<ServerResponse> toPostPaymentRequest() {
		return route("payment-request/create").POST("/payment-request/create", http(URL_GCP_PAYMENT)).build();
	}

	@Bean
	public RouterFunction<ServerResponse> toGetAllPaymentRequest() {
		return route("payment-request/get-all").GET("/payment-request/get-all", http(URL_GCP_PAYMENT)).build();
	}

	@Bean
	public RouterFunction<ServerResponse> toGetAllPaymentRequestByUsername() {
		return route("payment-request/get-all-by-buyer-username/**").GET("/payment-request/get-all-by-buyer-username/**", http(URL_GCP_PAYMENT)).build();
	}

	@Bean
	public RouterFunction<ServerResponse> toGetOnePaymentRequestById() {
		return route("payment-request/get-one-by-id/**").GET("/payment-request/get-one-by-id/**", http(URL_GCP_PAYMENT)).build();
	}

	@Bean
	public RouterFunction<ServerResponse> toDeletePaymentRequestById() {
		return route("payment-request/delete-by-id/**").DELETE("/payment-request/delete-by-id/**", http(URL_GCP_PAYMENT)).build();
	}

	@Bean
	public RouterFunction<ServerResponse> toCancelPaymentRequestById() {
		return route("payment-request/cancel/**").PATCH("/payment-request/cancel/**", http(URL_GCP_PAYMENT)).build();
	}

	@Bean
	public RouterFunction<ServerResponse> toRespondPaymentRequestById() {
		return route("payment-request/respond/**").PATCH("/payment-request/respond/**", http(URL_GCP_PAYMENT)).build();
	}
}
