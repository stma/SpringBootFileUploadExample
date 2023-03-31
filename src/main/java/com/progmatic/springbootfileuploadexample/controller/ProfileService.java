package com.progmatic.springbootfileuploadexample.controller;


import com.progmatic.springbootfileuploadexample.model.Profile;
import com.progmatic.springbootfileuploadexample.model.ProfilePic;
import com.progmatic.springbootfileuploadexample.model.ProfileRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ProfileService {

    private ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public Profile getProfile(Long id) {
        return profileRepository.findById(id).orElse(null);
    }

    public ProfilePic getProfilePic(Long id) {
        Optional<Profile> p = profileRepository.findById(id);
        if (p.isPresent()) {
            return p.get().getPic();
        } else {
            throw new EntityNotFoundException();
        }
    }

    public Profile addNewProfile(
            String name,
            String fileName,
            String contentType,
            byte[] data
    ) {
        Profile np = new Profile();
        np.setName(name);

        ProfilePic pp = new ProfilePic();
        pp.setContentType(contentType);
        pp.setFileName(fileName);
        pp.setData(data);
        np.setPic(pp);

        profileRepository.save(np);

        return np;
    }

    public Iterable<Profile> getAllProfiles() {
        return profileRepository.findAll();
    }
}
