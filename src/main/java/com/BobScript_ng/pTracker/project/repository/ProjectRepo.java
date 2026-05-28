package com.BobScript_ng.pTracker.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BobScript_ng.pTracker.project.entity.Projects;

public interface ProjectRepo extends JpaRepository<Projects, Long> {
    List<Projects> findByOwnerId(Long ownerId);

}
