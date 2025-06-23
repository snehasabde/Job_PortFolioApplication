package com.example.portfolio.controller;

import com.example.portfolio.model.Profile;
import com.example.portfolio.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profile")
// @CrossOrigin(origins = "http://127.0.0.1:5500")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping
    public ResponseEntity<Profile> getMyProfile() {
        // Assuming a single profile for the portfolio owner
        return profileService.getMyProfile()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // You might not expose these publicly for a static portfolio, but useful for admin panel
    @GetMapping("/all") // If you want to list all profiles (unlikely for a personal portfolio)
    public List<Profile> getAllProfiles() {
        return profileService.getAllProfiles();
    }

    @PostMapping // For creating the initial profile (or updating if none exists)
    public ResponseEntity<Profile> createProfile(@RequestBody Profile profile) {
        Profile createdProfile = profileService.createProfile(profile);
        return new ResponseEntity<>(createdProfile, HttpStatus.CREATED);
    }

    @PutMapping("/{id}") // For updating your profile data
    public ResponseEntity<Profile> updateProfile(@PathVariable Long id, @RequestBody Profile profile) {
        try {
            Profile updatedProfile = profileService.updateProfile(id, profile);
            return ResponseEntity.ok(updatedProfile);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}") // Unlikely to delete your own portfolio profile
    public ResponseEntity<Void> deleteProfile(@PathVariable Long id) {
        profileService.deleteProfile(id);
        return ResponseEntity.noContent().build();
    }
}