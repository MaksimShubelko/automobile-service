package com.example.automobileservice.controller;

import com.example.automobileservice.entity.UserEntity;
import com.example.automobileservice.entity.UserRole;
import com.example.automobileservice.repository.AmenityRepository;
import com.example.automobileservice.repository.LoginSuccessRepository;
import com.example.automobileservice.repository.RequestRepository;
import com.example.automobileservice.repository.UserRepository;
import com.example.automobileservice.security.JpaUserDetailsService;
import com.example.automobileservice.services.AmenityService;
import com.example.automobileservice.services.RequestService;
import com.example.automobileservice.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.persistence.EntityManagerFactory;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;


public class BaseIT {

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    protected MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private RequestService requestService;

    @MockBean
    AmenityService amenityService;

    @MockBean
    private PersistentTokenRepository persistentTokenRepository;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private AmenityRepository amenityRepository;

    @MockBean
    private RequestRepository requestRepository;

    @MockBean
    private LoginSuccessRepository loginSuccessRepository;

    @MockBean
    private JpaUserDetailsService jpaUserDetailsService;

    @Qualifier("entityManagerFactory")
    private EntityManagerFactory entityManagerFactory;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(springSecurity()).build();

        UserEntity admin = UserEntity.builder().login("admin").password("{noop}1111")
                .role(UserRole.ADMINISTRATOR).build();

        UserEntity client = UserEntity.builder().login("client").password("{noop}1111")
                .role(UserRole.CLIENT).build();

        User userClient = new User(client.getLogin(),
                client.getPassword(),
                client.isEnabled(),
                client.isAccountNonExpired(),
                client.isCredentialsNonExpired(),
                client.isAccountNonLocked(),
                Collections.singleton(new SimpleGrantedAuthority(client.getRole().name())));



        User userAdmin = new User(admin.getLogin(),
                admin.getPassword(),
                admin.isEnabled(),
                admin.isAccountNonExpired(),
                admin.isCredentialsNonExpired(),
                admin.isAccountNonLocked(),
                Collections.singleton(new SimpleGrantedAuthority(admin.getRole().name())));

        when(userRepository.findUserEntityByLogin("admin")).thenReturn(Optional.of(admin));
        when(userRepository.findUserEntityByLogin("client")).thenReturn(Optional.of(client));
        when(jpaUserDetailsService.loadUserByUsername("admin")).thenReturn(userAdmin);
        when(jpaUserDetailsService.loadUserByUsername("client")).thenReturn(userClient);

    }
}
