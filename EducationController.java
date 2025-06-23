package com.example.portfolio.controller;

import com.example.portfolio.model.Education;
import com.example.portfolio.service.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/education")
// @CrossOrigin(origins = "http://127.0.0.1:5500") // Global CORS config in WebConfig is preferred
public class EducationController {

    @Autowired
    private EducationService educationService;

    @GetMapping
    public List<Education> getAllEducation() {
        return educationService.getAllEducation();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Education> getEducationById(@PathVariable Long id) {
        return educationService.getEducationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping // For adding new education entries (e.g., via an admin panel)
    public ResponseEntity<Education> createEducation(@RequestBody Education education) {
        Education createdEducation = educationService.createEducation(education);
        return new ResponseEntity<>(createdEducation, HttpStatus.CREATED);
    }

    @PutMapping("/{id}") // For updating existing education entries
    public ResponseEntity<Education> updateEducation(@PathVariable Long id, @RequestBody Education education) {
        try {
            Education updatedEducation = educationService.updateEducation(id, education);
            return ResponseEntity.ok(updatedEducation);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}") // For deleting education entries
    public ResponseEntity<Void> deleteEducation(@PathVariable Long id) {
        educationService.deleteEducation(id);
        return ResponseEntity.noContent().build();
    }
}