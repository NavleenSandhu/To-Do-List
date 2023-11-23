package ca.sheridancollege.ca.sandnavl.security;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	 @Override
	    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
	            throws IOException, ServletException {
	        String targetUrl = determineTargetUrl(authentication);
	        if (response.isCommitted()) {
	            return;
	        }
	        getRedirectStrategy().sendRedirect(request, response, targetUrl);
	    }

	    protected String determineTargetUrl(Authentication authentication) {
	        String username = authentication.getName();
	        return "/" + username;
	    }
}
