package com.example.automobileservice.security;

import com.example.automobileservice.entity.UserEntity;
import com.example.automobileservice.entity.UserRole;
import com.example.automobileservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;


@RequiredArgsConstructor
@Service
public class JpaUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findUserEntityByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username " + username + " not found"));
        User user = new User(userEntity.getLogin(),
                userEntity.getPassword(),
                userEntity.isEnabled(),
                userEntity.isAccountNonExpired(),
                userEntity.isCredentialsNonExpired(),
                userEntity.isAccountNonLocked(),
                convertToAuthorities(userEntity.getRole()));


        return user;
    }

    private Collection<? extends GrantedAuthority> convertToAuthorities(UserRole userRole) {
        if (userRole != null) {
            return Collections.singleton(new SimpleGrantedAuthority(userRole.name()));
        } else {
            return Collections.emptySet();
        }
    }
}
