package com.georgeifrim.l4MultipleAuthenticationProviders.config.provider;

import com.georgeifrim.l4MultipleAuthenticationProviders.config.authentications.ApiKeyAuthentication;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;


@AllArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {


    private final String key;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        ApiKeyAuthentication a = (ApiKeyAuthentication) authentication;

        String headerKey = a.getKey();

        if(key.equals(headerKey)){
            a.setAuthenticated(true);
            return a;
        }
            throw new BadCredentialsException("Oh my god");

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(ApiKeyAuthentication.class);
    }
}
