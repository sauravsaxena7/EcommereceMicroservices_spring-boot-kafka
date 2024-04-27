package config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.SecurityWebFilterChain;


//download image docker run -p 8181:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=a
//dmin quay.io/keycloak/keycloak:23.0.3 start-dev
//relm:-springbootecommerecekeycloackrealm
//clienid:-spring-cloud-client



@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
         httpSecurity
                .cors(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
                 .authorizeHttpRequests(authorize ->
                         authorize
                                 .requestMatchers("/eureka/**")
                         .authenticated()
                 )
                 .oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults()));

         return httpSecurity.build();

    }



}
