package com.example.automobileservice.config;

import com.example.automobileservice.security.CustomPasswordEncoderFactories;
import com.example.automobileservice.security.JpaUserDetailsService;
import com.example.automobileservice.security.RestHeaderAuthFilter;
import com.example.automobileservice.security.RestPathAuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    JpaUserDetailsService jpaUserDetailsService;

    @Autowired
    PersistentTokenRepository persistentTokenRepository;

    @Bean
    PasswordEncoder passwordEncoder() {
        return CustomPasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(restHeaderAuthFilter(authenticationManager()),
                        UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(restPathAuthFilter(authenticationManager()),
                        UsernamePasswordAuthenticationFilter.class)
                .csrf().ignoringAntMatchers("/api/**");

        http.csrf().disable()
                .authorizeRequests(req -> req
                        .antMatchers("/", "/index", "/login", "/resources/**", "/webjars/**").permitAll()
                        .antMatchers(HttpMethod.GET, "/users/new").permitAll()
                        .antMatchers("/users/index").permitAll()
                .mvcMatchers(HttpMethod.GET,"/users/signIn").permitAll()
                .mvcMatchers(HttpMethod.POST,"/users/signIn").permitAll())
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .formLogin(loginConf -> {
                    loginConf.loginProcessingUrl("/login")
                            .loginPage("/").permitAll()
                            .successForwardUrl("/")
                            .passwordParameter("password")
                            .usernameParameter("username")
                            .defaultSuccessUrl("/")
                            .failureUrl("/?error");
                })
                .logout(logoutConf ->{
                    logoutConf.logoutSuccessUrl("/?logout").permitAll()
                            .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"));
                }).rememberMe().tokenRepository(persistentTokenRepository)
                .userDetailsService(jpaUserDetailsService);
    }

    public RestHeaderAuthFilter restHeaderAuthFilter(AuthenticationManager authenticationManager) {
        RestHeaderAuthFilter filter = new RestHeaderAuthFilter(new AntPathRequestMatcher("/api/**"));
        filter.setAuthenticationManager(authenticationManager);
        return filter;
    }


    public RestPathAuthFilter restPathAuthFilter(AuthenticationManager authenticationManager) {
        RestPathAuthFilter filter = new RestPathAuthFilter(new AntPathRequestMatcher("/api/**"));
        filter.setAuthenticationManager(authenticationManager);
        return filter;
    }


}
