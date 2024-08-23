package com.example.demo_data_API.security;

import java.security.interfaces.RSAPublicKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * This class configures JWT-based authentication and security settings.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

        /**
         * Injects the RSA public key used for verifying JWTs.
         * The key is loaded from the application properties.
         */
        @Value("${rsa.public-key}")
        RSAPublicKey publicKey;

        /**
         * Creates a JwtDecoder bean using the injected RSA public key.
         * This decoder is used to validate and decode JWT tokens.
         * 
         * @return a JwtDecoder instance.
         */
        private JwtDecoder jwtDecoder() {
                return NimbusJwtDecoder.withPublicKey(publicKey).build();
        }

        /**
         * Configures the security filter chain for the application.
         * 
         * @param http the HttpSecurity object to configure.
         * @return a SecurityFilterChain instance.
         * @throws Exception if an error occurs during configuration.
         */
        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                return http
                                // Configures session management to be stateless (no session stored on the
                                // server)
                                .sessionManagement(
                                                session -> session
                                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                // Disables Cross-Site Request Forgery (CSRF) protection
                                .csrf(csrf -> csrf.disable())
                                // Configures authorization rules for HTTP requests
                                .authorizeHttpRequests(auth -> auth
                                                .requestMatchers("/").permitAll()
                                                .anyRequest().authenticated())
                                // Configures the application to use JWT tokens for OAuth2 resource server
                                // security
                                .oauth2ResourceServer(
                                                (resourceServer) -> resourceServer
                                                                .jwt((customizer) -> customizer.decoder(jwtDecoder())))

                                .build();
        }

}
