package com.BobScript_ng.pTracker.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BobScript_ng.pTracker.user.entity.User;
import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);

}
