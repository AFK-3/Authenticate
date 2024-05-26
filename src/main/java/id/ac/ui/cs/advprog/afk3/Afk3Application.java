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

	final String URL_GCP_REVIEW = "http://34.124.202.219/";

	@Value("${app.buy-domain}")
	String URL_GCP_BUY;

	String URL_GCP_FEATURED_LISTING = "http://35.197.133.115/";

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
		return route("payment-request/get-all-by-buyer-username").GET("/payment-request/get-all-by-buyer-username", http(URL_GCP_PAYMENT)).build();
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
	public RouterFunction<ServerResponse> toDeleteAll() {
		return route("payment-request/delete-all").DELETE("/payment-request/delete-all", http(URL_GCP_PAYMENT)).build();
	}

	@Bean
	public RouterFunction<ServerResponse> toCancelPaymentRequestById() {
		return route("payment-request/cancel/**").PUT("/payment-request/cancel/**", http(URL_GCP_PAYMENT)).build();
	}

	@Bean
	public RouterFunction<ServerResponse> toEditPaymentRequestById() {
		return route("payment-request/edit/**").PUT("/payment-request/edit/**", http(URL_GCP_PAYMENT)).build();
	}

	@Bean
	public RouterFunction<ServerResponse> toRespondPaymentRequestById() {
		return route("payment-request/respond/**").PUT("/payment-request/respond/**", http(URL_GCP_PAYMENT)).build();
	}

	@Bean
	public RouterFunction<ServerResponse> toAddToCart(){
		return route("cart").POST("/cart",http(URL_GCP_BUY)).build();
	}

	@Bean
	public RouterFunction<ServerResponse> toReduceListings(){
		return route("cart/reduce").PUT("/cart/reduce",http(URL_GCP_BUY)).build();
	}

	@Bean
	public RouterFunction<ServerResponse> toAddListings(){
		return route("cart/add").PUT("/cart/add",http(URL_GCP_BUY)).build();
	}

	@Bean
	public RouterFunction<ServerResponse> toDeleteListing(){
		return route("cart").DELETE("/cart",http(URL_GCP_BUY)).build();
	}

	@Bean
	public RouterFunction<ServerResponse> toGetCartByUsername(){
		return route("cart/**").GET("/cart/**",http(URL_GCP_BUY)).build();
	}

	@Bean
	public RouterFunction<ServerResponse> toGetAllCart(){
		return route("cart").GET("/cart",http(URL_GCP_BUY)).build();
	}

	@Bean
	public RouterFunction<ServerResponse> toCheckout(){
		return route("transaction").POST("/transaction",http(URL_GCP_BUY)).build();
	}

	@Bean
	public RouterFunction<ServerResponse> toUpdateTransactionStatus(){
		return route("transaction/**").PUT("/transaction/**",http(URL_GCP_BUY)).build();
	}

	@Bean
	public RouterFunction<ServerResponse> toGetAllTransaction(){
		return route("transaction").GET("/transaction",http(URL_GCP_BUY)).build();
	}

	@Bean
	public RouterFunction<ServerResponse> toGetTransactionByUser(){
		return route("transaction/username").GET("/transaction/username",http(URL_GCP_BUY)).build();
	}

	@Bean
	public RouterFunction<ServerResponse> toGetTransactionById(){
		return route("transaction/id").GET("/transaction/id",http(URL_GCP_BUY)).build();
	}

	@Bean
	public RouterFunction<ServerResponse> toGetReviews() {
		return route("review/getReview/**").GET("review/getReview/**", http(URL_GCP_REVIEW)).build();
	}

	@Bean
	public RouterFunction<ServerResponse> toCreateReview() {
		return route("review/createReview/**").POST("review/createReview/**", http(URL_GCP_REVIEW)).build();
	}

	@Bean
	public RouterFunction<ServerResponse> toUpdateReview() {
		return route("review/updateReview/**").PUT("review/updateReview/**", http(URL_GCP_REVIEW)).build();
	}

	@Bean
	public RouterFunction<ServerResponse> toDeleteReview() {
		return route("review/deleteReview/**").DELETE("review/deleteReview/**", http(URL_GCP_REVIEW)).build();
	}

	@Bean
	public RouterFunction<ServerResponse> toAllowUserToReview() {
		return route("review/allowUserToReview").POST("review/allowUserToReview", http(URL_GCP_REVIEW)).build();
	}

	@Bean
	public RouterFunction<ServerResponse> toCreateFeaturedListing() {
		return route("featured-listing/createListing").POST("featured-listing/createListing", http(URL_GCP_FEATURED_LISTING)).build();
	}

	@Bean
	public RouterFunction<ServerResponse> toGetFeaturedListingById() {
		return route("featured-listing/**").GET("featured-listing/**", http(URL_GCP_FEATURED_LISTING)).build();
	}

	@Bean
	public RouterFunction<ServerResponse> toGetAllFeaturedListing() {
		return route("featured-listing/get-all").GET("featured-listing/get-all", http(URL_GCP_FEATURED_LISTING)).build();
	}

	@Bean
	public RouterFunction<ServerResponse> toGetAllListing() {
		return route("featured-listing/get-all-listing").GET("featured-listing/get-all-listing", http(URL_GCP_FEATURED_LISTING)).build();
	}

	@Bean
	public RouterFunction<ServerResponse> toEditFeaturedListing() {
		return route("featured-listing/edit").PUT("featured-listing/edit", http(URL_GCP_FEATURED_LISTING)).build();
	}

	@Bean
	public RouterFunction<ServerResponse> toDeleteFeaturedListing() {
		return route("featured-listing/delete/**").DELETE("featured-listing/delete/**", http(URL_GCP_FEATURED_LISTING)).build();
	}

	@Bean
	public RouterFunction<ServerResponse> toDeleteAllFeaturedListing() {
		return route("featured-listing/delete-all").DELETE("featured-listing/delete-all", http(URL_GCP_FEATURED_LISTING)).build();
	}
}
