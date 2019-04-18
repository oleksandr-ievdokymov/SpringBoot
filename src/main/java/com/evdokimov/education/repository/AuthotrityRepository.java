package com.evdokimov.education.repository;

import com.evdokimov.education.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthotrityRepository extends JpaRepository<Authority, String> {

}
