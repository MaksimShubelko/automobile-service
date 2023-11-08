package com.example.automobileservice.controller;

import com.example.automobileservice.repository.AmenityRepository;
import com.example.automobileservice.repository.LoginSuccessRepository;
import com.example.automobileservice.repository.RequestRepository;
import com.example.automobileservice.repository.UserRepository;
import com.example.automobileservice.security.JpaUserDetailsService;
import com.example.automobileservice.services.AmenityService;
import com.example.automobileservice.services.RequestService;
import com.example.automobileservice.services.UserService;
import com.example.automobileservice.services.dto.AmenityDto;
import com.example.automobileservice.services.dto.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.provider.Arguments;
import org.mockito.MockedStatic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.assertj.AssertableApplicationContext;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.WebApplicationContext;

import javax.persistence.EntityManagerFactory;

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.anonymous;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@WebMvcTest
class  AmenityControllerIT extends BaseIT {


    @WithMockUser(username = "admin", roles={"ADMINISTRATOR"})
    @Test
    void getAmenitiesAuthAdmin_200() throws Exception {
        mockMvc.perform(get("/amenities/find"))
                .andDo(print())
                .andExpect(view().name("amenities/index"))
                .andExpect(status().isOk());

    }

    @WithMockUser(username = "client", roles={"CLIENT"})
    @Test
    void getAmenitiesAuthClient_200() throws Exception {
        mockMvc.perform(get("/amenities/find"))
                .andDo(print())
                .andExpect(view().name("amenities/index"))
                .andExpect(status().isOk());

    }

    @WithMockUser(username = "admin", roles={"ADMINISTRATOR"})
    @Test
    void getAmenitiesAnonymous_401() throws Exception {
        mockMvc.perform(get("/amenities/find"))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    void initUpdating() throws Exception {
        when(amenityService.findById(1L)).thenReturn(AmenityDto.builder().build());
        mockMvc.perform(get("/amenities/1/edit"))
                .andExpect(status().isUnauthorized());

    }

    @Test
    void processUpdating_Ok() throws Exception {
        mockMvc.perform(post("/amenities/1/edit"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void processUpdating_Error() throws Exception {
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true);

        mockMvc.perform(post("/amenities/1/edit"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void initCreating() throws Exception {
        when(amenityService.findById(1L)).thenReturn(AmenityDto.builder().build());
        mockMvc.perform(get("/amenities/create"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void processCreating() throws Exception {
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true);

        mockMvc.perform(post("/amenities/create"))
                .andExpect(status().isUnauthorized());
    }


}