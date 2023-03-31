package com.progmatic.springbootfileuploadexample.controller;

import com.progmatic.springbootfileuploadexample.model.ProfilePic;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequestMapping("/profiles")
public class ProfileController {

    private ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping
    public String showProfiles(Model m) {
        m.addAttribute("profiles", profileService.getAllProfiles());
        return "profiles";
    }

    @GetMapping(path = "/new")
    public String newProfile(Model m) {
        m.addAttribute("profileData", new ProfileForm());
        return "new-profile";
    }

    @PostMapping
    public String addProfiles(
            @ModelAttribute("profileData") ProfileForm form
    ) throws IOException {
        profileService.addNewProfile(
                form.getName(),
                form.getProfilePicture().getName(),
                form.getProfilePicture().getContentType(),
                form.getProfilePicture().getBytes()
        );
        return "redirect:/profiles";
    }

    @GetMapping("/img/{id}")
    public ResponseEntity<byte[]> getProfilePic(
            @PathVariable("id") Long id
    ) {
        ProfilePic pp;
        try {
            pp = profileService.getProfilePic(id);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(pp.getContentType()));

        return new ResponseEntity<>(pp.getData(), headers, HttpStatus.OK);
    }
}
