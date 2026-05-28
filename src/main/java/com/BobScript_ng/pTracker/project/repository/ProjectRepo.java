package com.BobScript_ng.pTracker.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BobScript_ng.pTracker.project.entity.Projects;

public interface ProjectRepo extends JpaRepository<Projects, Long> {

}
