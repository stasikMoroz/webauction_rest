package com.vironit.businessauction.security.provider;

import com.vironit.businessauction.dao.TokenDao;
import com.vironit.businessauction.entity.Token;
import com.vironit.businessauction.security.token.TokenAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class TokenAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private TokenDao tokenDao;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        TokenAuthentication tokenAuthentication = (TokenAuthentication) authentication;

        Optional<Token> tokenOptional = tokenDao.findTokenByValue(tokenAuthentication.getName());

        if (tokenOptional.isPresent()) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(tokenOptional.get().getUser().getLogin());
            if (!userDetails.isAccountNonLocked()) {
                throw new LockedException("User with login " + userDetails.getUsername() + " has BAN status!");
            }
            tokenAuthentication.setUserDetails(userDetails);
            tokenAuthentication.setAuthenticated(true);
            return tokenAuthentication;
        }
        throw new IllegalArgumentException("Bad token!");
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return TokenAuthentication.class.equals(aClass);
    }
}
