package com.example.portfolio.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTechnologies() {
		return technologies;
	}
	public void setTechnologies(String technologies) {
		this.technologies = technologies;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getLiveDemoUrl() {
		return liveDemoUrl;
	}
	public void setLiveDemoUrl(String liveDemoUrl) {
		this.liveDemoUrl = liveDemoUrl;
	}
	public String getSourceCodeUrl() {
		return sourceCodeUrl;
	}
	public void setSourceCodeUrl(String sourceCodeUrl) {
		this.sourceCodeUrl = sourceCodeUrl;
	}
	private String title;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String technologies; // e.g., "Spring Boot, React, MySQL"
    private String imageUrl; // Path or URL to project screenshot/image (for frontend)
    private String liveDemoUrl;
    private String sourceCodeUrl;
}
