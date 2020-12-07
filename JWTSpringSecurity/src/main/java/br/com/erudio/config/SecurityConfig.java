package br.com.erudio.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.erudio.security.jwt.JwtConfigurer;
import br.com.erudio.security.jwt.JwtTokenProvider;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtTokenProvider tokenProvider;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	protected void configure(HttpSecurity http) throws Exception {
		http
			.httpBasic().disable() // Desabilita o acesso pela autenticação Basic
			.csrf().disable() // Desabilita a falsificação de solicitação entre sites
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Sessão não guarda estados
			.and()
				.authorizeRequests() // Autoriza requisições
				.antMatchers("/auth/signin", "/api-docs/**", "swagger-ui.html").permitAll() // Liberada para acesso ser estar autenticado
				.antMatchers("/api/**").authenticated() // Só libera para acesso se estiver autenticado
				.antMatchers("/users").denyAll() // Não tem acesso
			.and()
				.apply(new JwtConfigurer(tokenProvider));
	}
	
}
