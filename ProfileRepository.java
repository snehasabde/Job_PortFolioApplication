package com.example.portfolio.repository;

import com.example.portfolio.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    // A portfolio usually has only one profile, so you might add a custom method
    // Optional<Profile> findTopByOrderByIdAsc();
}