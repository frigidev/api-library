package br.com.frigidev.library.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.http.HttpServletResponse;

//Define que essa classe e uma configuracao para o Spring
@Configuration
//Habilita a seguranca para a api
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired 
	private JWTFilter filter;
	@Autowired 
	private UserDetailsServiceImpl uds;

	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests((requests) -> requests // Autorizando requisicoes de entrada
				.requestMatchers("/auth/**").permitAll() // Autorizando requisicoes sem autenticacao para esse endpoint (nesse caso, path /auth/ e uri´s subsequentes
				.requestMatchers("/user/**").hasRole("USER") // Autorizando apenas usuarios com o perfil "USER" a utilizar esse endpoint (nesse caso, path /user/ e uri´s subsequentes
				.anyRequest().authenticated() // Todas as requisicoes, exceto para as definidas acima, precisarao ser autenticadas
			)
			.logout((logout) -> logout.permitAll())
			.userDetailsService(uds) // Setando o servico "user details" (do modulo Security do Spring) para essa implementacao customizada
			.exceptionHandling((exception) -> exception
					.authenticationEntryPoint(
		                    // Rejeitando requisicoes nao autorizadas que chegam ate esse ponto.
		                    // Logo, isso significa que essa requisicao requer autenticacao e um JWT valido
		                    (request, response, authException) ->
		                            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized")
		            )); 

		// Adicionando o filtro JWT
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}
	
	// Bean (tipo de Service) que sera responsavel por encriptar a senha
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    // Expondo o bean responsavel pelo gerenciamento do processo de autenticacao
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
           return config.getAuthenticationManager();
    }
}