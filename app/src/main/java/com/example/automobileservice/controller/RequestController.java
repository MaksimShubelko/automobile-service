package com.example.automobileservice.controller;

import com.example.automobileservice.services.AmenityService;
import com.example.automobileservice.services.RequestService;
import com.example.automobileservice.services.UserService;
import com.example.automobileservice.services.dto.AmenityDto;
import com.example.automobileservice.services.dto.RequestDto;
import com.example.automobileservice.services.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/amenities/{amenityId}/requests")
@Controller
public class RequestController {

    private final UserService userService;
    private final AmenityService amenityService;
    private final RequestService requestService;

    @GetMapping("/create")
    public String createRequest(Principal principal, AmenityDto amenityDto) {
        UserDto userDto = userService.findByLogin(principal.getName());
        RequestDto requestDto = requestService.findRequestByUser(userDto);
        requestDto.setAmenityDto(amenityDto);
        requestDto.setUserDto(userDto);
        requestService.save(requestDto);
        return "redirect:/amenities/find";
    }

    @GetMapping("/{requestId}/delete")
    public String delete(@PathVariable(name = "requestId") Long requestId, AmenityDto amenityDto) {
        requestService.delete(requestId);
        return "redirect:/amenities/" + amenityDto.getId() + "/requests/find";
    }

    @GetMapping("/find")
    public String findRequests(AmenityDto amenityDto, Model model) {
        List<RequestDto> requestDtos = requestService.findRequestsByAmenity(amenityDto);
        model.addAttribute("selections", requestDtos);
        return "requests/index";
    }

    @ModelAttribute("amenityDto")
    public AmenityDto findAmenity(@PathVariable("amenityId") Long amenityId) {
        return amenityService.findById(amenityId);
    }

}
