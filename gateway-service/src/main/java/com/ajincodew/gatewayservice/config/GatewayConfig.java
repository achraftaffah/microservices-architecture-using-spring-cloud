package com.ajincodew.gatewayservice.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    
    // JwtAuthenticationFilter handles gateway-level JWT validation
    // and token propagation to downstream services.
    // It's registered as a @Component and extends AbstractGatewayFilterFactory
    // to participate in Spring Cloud Gateway's filter chain.
    
}