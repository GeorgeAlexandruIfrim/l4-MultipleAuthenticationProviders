package com.georgeifrim.l4MultipleAuthenticationProviders.config.managers;

import com.georgeifrim.l4MultipleAuthenticationProviders.config.authentications.ApiKeyAuthentication;
import com.georgeifrim.l4MultipleAuthenticationProviders.config.provider.CustomAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
public class CustomAuthenticationManager implements AuthenticationManager {

    private final String key;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        var provider = new CustomAuthenticationProvider(key);

        if(provider.supports(authentication.getClass())){
            return provider.authenticate(authentication);
        }
        throw new BadCredentialsException("Ceva");
    }
}
