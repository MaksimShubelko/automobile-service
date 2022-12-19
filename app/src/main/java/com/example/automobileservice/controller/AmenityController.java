package com.example.automobileservice.controller;

import com.example.automobileservice.services.AmenityService;
import com.example.automobileservice.services.dto.AmenityDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/amenities")
@Controller
public class AmenityController {

    private final AmenityService amenityService;

    @GetMapping("/find")
    public String getAmenities(Model model) {
        List<AmenityDto> amenityDtos = amenityService.findAll();
        model.addAttribute("selections", amenityDtos);
        return "amenities/index";
    }

    @GetMapping("/{amenityId}/edit")
    public String initUpdating(@PathVariable(name = "amenityId") Long amenityId,
                               Model model) {
        AmenityDto amenityDto = amenityService.findById(amenityId);
        model.addAttribute("amenityDto", amenityDto);
        return "amenities/edition";
    }

    @PostMapping("/{amenityId}/edit")
    public String processUpdating(@Valid AmenityDto amenityDto, BindingResult bindingResult,
                                  @PathVariable(name = "amenityId") Long amenityId) {
        if (bindingResult.hasErrors()) {
            return "amenities/edition";
        } else {
            amenityDto.setId(amenityId);
            amenityService.save(amenityDto);
            return "redirect:/amenities/find";
        }
    }

    @GetMapping("/create")
    public String initCreating(Model model) {
        model.addAttribute("amenityDto", AmenityDto.builder().build());
        return "amenities/creation";
    }

    @PostMapping("/create")
    public String processCreating(@Valid AmenityDto amenityDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "amenities/creation";
        } else {
            amenityService.save(amenityDto);
            return "redirect:/amenities/find";
        }
    }

    @GetMapping("/{amenityId}/delete")
    public String deleteAmenity(@PathVariable(name = "amenityId") Long amenityId) {
        amenityService.delete(amenityId);
        return "redirect:/amenities/find";
    }

}
