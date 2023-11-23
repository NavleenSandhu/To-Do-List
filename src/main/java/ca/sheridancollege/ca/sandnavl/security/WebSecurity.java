package ca.sheridancollege.ca.sandnavl.security;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component("webSecurity")
public class WebSecurity {

    public boolean checkUsername(Authentication authentication, String username) {
        String authenticatedUsername = authentication.getName();
        return authenticatedUsername.equals(username);
    }
}
