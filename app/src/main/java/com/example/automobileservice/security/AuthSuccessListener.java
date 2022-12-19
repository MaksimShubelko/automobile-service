package com.example.automobileservice.security;

import com.example.automobileservice.entity.LoginSuccess;
import com.example.automobileservice.entity.UserEntity;
import com.example.automobileservice.repository.LoginSuccessRepository;
import com.example.automobileservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Slf4j
@Component
public class AuthSuccessListener {

    private final UserRepository userRepository;
    private final LoginSuccessRepository loginSuccessRepository;

    @EventListener
    public void listen(AuthenticationSuccessEvent event) {
        log.debug("User logged in OK");

        if (event.getAuthentication() instanceof UsernamePasswordAuthenticationToken) {
            UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) event.getSource();
            LoginSuccess loginSuccess = new LoginSuccess();

            if (token.getPrincipal() instanceof User user) {
                log.debug("User name logged in:" + user.getUsername());
                UserEntity userEntity = userRepository.findUserEntityByLogin(user.getUsername()).orElseThrow();
                loginSuccess.setUser(userEntity);

            }

            if (token.getDetails() instanceof WebAuthenticationDetails details) {
                log.debug("User IP:" + details.getRemoteAddress());
                loginSuccess.setSourceIp(details.getRemoteAddress());
            }
            loginSuccessRepository.save(loginSuccess);
        }
    }
}
