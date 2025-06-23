package com.example.portfolio.service;

import com.example.portfolio.model.Profile;
import com.example.portfolio.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    public List<Profile> getAllProfiles() {
        return profileRepository.findAll();
    }

    // Since there's likely only one profile, you might fetch it without an ID
    public Optional<Profile> getMyProfile() {
        // For a single-user portfolio, you might just fetch the first one or a known ID
        return profileRepository.findById(1L); // Assuming ID 1 is your profile
        // Alternatively, if you only ever have one, you could do:
        // return profileRepository.findAll().stream().findFirst();
    }

    public Profile createProfile(Profile profile) {
        return profileRepository.save(profile);
    }

    public Profile updateProfile(Long id, Profile profileDetails) {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profile not found with id: " + id));

        profile.setName(profileDetails.getName());
        profile.setTitle(profileDetails.getTitle());
        profile.setAboutMe(profileDetails.getAboutMe());
        profile.setEmail(profileDetails.getEmail());
        profile.setLinkedinUrl(profileDetails.getLinkedinUrl());
        profile.setGithubUrl(profileDetails.getGithubUrl());
        // Update other fields as necessary

        return profileRepository.save(profile);
    }

    public void deleteProfile(Long id) {
        profileRepository.deleteById(id);
    }
}
