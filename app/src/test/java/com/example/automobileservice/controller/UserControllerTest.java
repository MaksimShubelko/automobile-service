package com.example.automobileservice.controller;

import com.example.automobileservice.services.dto.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.validation.BindingResult;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest
class UserControllerTest extends BaseIT {

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void initSingIn() throws Exception {
        mockMvc.perform(get("/users/signIn"))
                .andExpect(view().name("users/signIn"))
                .andExpect(status().isOk());
    }

}