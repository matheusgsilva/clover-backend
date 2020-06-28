package br.com.clover;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class JwtFilter implements Filter {

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		if (request instanceof HttpServletRequest) {
			String url = ((HttpServletRequest) request).getRequestURL().toString();

			HttpServletRequest httpRequest = (HttpServletRequest) request;

			if (httpRequest.getMethod().equals("OPTIONS")) {
				chain.doFilter(request, response);
				return;
			}

			if (!(url != null && url.contains("/api/"))) {
				chain.doFilter(request, response);
				return;
			}

			String jwt = httpRequest.getHeader("Authorization");

			if (jwtTokenUtil.validateToken(jwt)) {
				chain.doFilter(request, response);
				return;
			}
		}

		HttpServletResponse httpServletResponse = ((HttpServletResponse) response);

		httpServletResponse.getWriter()
				.append("{\n" + "\"status\": 401,\n" + "\"message\": \"UNAUTHORIZED\",\n" + "\"body\": null\n" + "}");
		httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		httpServletResponse.setContentType("application/json");
		httpServletResponse.addHeader("Access-Control-Allow-Origin", "*");
	}
}
