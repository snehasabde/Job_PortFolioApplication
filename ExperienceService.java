package com.example.portfolio.service;

import com.example.portfolio.model.Experience;
import com.example.portfolio.repository.ExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExperienceService {

    @Autowired
    private ExperienceRepository experienceRepository;

    public List<Experience> getAllExperiences() {
        return experienceRepository.findAll();
    }

    public Optional<Experience> getExperienceById(Long id) {
        return experienceRepository.findById(id);
    }

    public Experience createExperience(Experience experience) {
        return experienceRepository.save(experience);
    }

    public Experience updateExperience(Long id, Experience experienceDetails) {
        Experience experience = experienceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Experience not found with id: " + id));

        experience.setJobTitle(experienceDetails.getJobTitle());
        experience.setCompany(experienceDetails.getCompany());
        experience.setStartDate(experienceDetails.getStartDate());
        experience.setEndDate(experienceDetails.getEndDate());
        experience.setDescription(experienceDetails.getDescription());

        return experienceRepository.save(experience);
    }

    public void deleteExperience(Long id) {
        experienceRepository.deleteById(id);
    }
}
