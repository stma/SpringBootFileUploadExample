package com.progmatic.springbootfileuploadexample.controller;

import org.springframework.web.multipart.MultipartFile;

public class ProfileForm {

    private MultipartFile profilePicture;

    private String name;

    public MultipartFile getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(MultipartFile profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
