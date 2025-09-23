package net.abderrahimself.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class GatewayserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayserverApplication.class, args);
	}

	@Bean
	public RouteLocator abderrahimselfRouteLocator(RouteLocatorBuilder routeLocatorBuilder) {
		return routeLocatorBuilder.routes()
				.route(p -> p.path("/abderrahimself/accounts/**")
						.filters(f -> f.rewritePath("/abderrahimself/accounts/(?<segment>.*)", "/${segment}")
								.addRequestHeader("X-Response-Time", LocalDateTime.now().toString()))
						.uri("lb://ACCOUNTS"))
				.route(p -> p.path("/abderrahimself/loans/**")
						.filters(f -> f.rewritePath("/abderrahimself/loans/(?<segment>.*)", "/${segment}")
								.addRequestHeader("X-Response-Time", LocalDateTime.now().toString()))
						.uri("lb://LOANS"))
				.route(p -> p.path("/abderrahimself/cards/**")
						.filters(f -> f.rewritePath("/abderrahimself/cards/(?<segment>.*)", "/${segment}")
								.addRequestHeader("X-Response-Time", LocalDateTime.now().toString()))
						.uri("lb://CARDS"))
				.build();
	}
}