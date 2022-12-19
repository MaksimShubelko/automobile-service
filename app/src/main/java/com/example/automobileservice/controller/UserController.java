package com.example.automobileservice.controller;

import com.example.automobileservice.entity.UserRole;
import com.example.automobileservice.security.CustomPasswordEncoderFactories;
import com.example.automobileservice.services.UserService;
import com.example.automobileservice.services.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.Collection;

@RequiredArgsConstructor
@RequestMapping("/users")
@Controller
public class UserController {

    private final UserService userService;


    @GetMapping("/signIn")
    public String initSingIn(Model model) {
        model.addAttribute("userDto", UserDto.builder().role(UserRole.CLIENT).build());
        return "users/signIn";
    }

    @PostMapping("/signIn")
    public String processSingIn(@Valid UserDto userDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "users/signIn";
        }
        if (!userDto.getPassword().equals(userDto.getConfirmPassword())) {
            bindingResult.rejectValue("confirmPassword", "err", "Don't matches");
            return "users/signIn";
        }
        if (userService.existsByLogin(userDto.getLogin())) {
            bindingResult.rejectValue("login", "err", "Login is exists");
            return "users/signIn";
        }
        userDto.setPassword(CustomPasswordEncoderFactories.createDelegatingPasswordEncoder().encode(userDto.getPassword()));
        userService.save(userDto);
        return "/index";
    }


    @ModelAttribute("roles")
    public Collection<UserRole> populateUserRoles() {
        return Arrays.asList(UserRole.values());
    }
}
