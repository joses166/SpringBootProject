package br.com.erudio.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

public class JwtTokenFilter extends GenericFilterBean {

	@Autowired
	private JwtTokenProvider tokenProvider;
	
	public JwtTokenFilter(JwtTokenProvider tokenProvider) {
		this.tokenProvider = tokenProvider;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// Puxa o token da requisição
		String token = tokenProvider.resolveToken((HttpServletRequest) request);
		// Verifica se existe um token e se é válido
		if ( token != null && tokenProvider.validateToken(token) ) {
			// Puxa a autenticação de acordo com o token informado
			Authentication auth = tokenProvider.getAuthentication(token);
			// Verifica se existe a autenticação
			if (auth != null) {
				// Seta a informação de autenticação do contexto de segurança
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		}
		// Faz a filtragem com os dados da requisição e da resposta
		chain.doFilter(request, response);
	}

}