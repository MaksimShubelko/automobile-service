package com.example.automobileservice.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.*;

import java.util.HashMap;
import java.util.Map;


public class CustomPasswordEncoderFactories {

    public static PasswordEncoder createDelegatingPasswordEncoder() {
        String encodingId = "bcrypt";
        Map<String, PasswordEncoder> encoders = new HashMap();
        encoders.put("noop", NoOpPasswordEncoder.getInstance());
        encoders.put(encodingId, new BCryptPasswordEncoder(5));
        encoders.put("ldap", new LdapShaPasswordEncoder());
        encoders.put("sha256", new StandardPasswordEncoder());
        return new DelegatingPasswordEncoder(encodingId, encoders);
    }
}