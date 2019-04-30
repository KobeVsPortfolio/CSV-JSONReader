package com.realdolmen.hbo5.wasdapp.wasdappcore.repo;

import com.realdolmen.hbo5.wasdapp.wasdappcore.domain.UserWassdapp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserWassdapp, Long> {
    UserWassdapp findByEmail(String email);
}
