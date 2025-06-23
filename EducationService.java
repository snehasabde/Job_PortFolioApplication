package com.example.portfolio.service;

import com.example.portfolio.model.Education;
import com.example.portfolio.repository.EducationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EducationService {

    @Autowired
    private EducationRepository educationRepository;

    public List<Education> getAllEducation() {
        return educationRepository.findAll();
    }

    public Optional<Education> getEducationById(Long id) {
        return educationRepository.findById(id);
    }

    public Education createEducation(Education education) {
        return educationRepository.save(education);
    }

    public Education updateEducation(Long id, Education educationDetails) {
        Education education = educationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Education not found with id: " + id)); // Or custom exception

        education.setDegree(educationDetails.getDegree());
        education.setInstitution(educationDetails.getInstitution());
        education.setGraduationDate(educationDetails.getGraduationDate());
        education.setDescription(educationDetails.getDescription());

        return educationRepository.save(education);
    }

    public void deleteEducation(Long id) {
        educationRepository.deleteById(id);
    }
}