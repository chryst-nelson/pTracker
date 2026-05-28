package com.BobScript_ng.pTracker.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BobScript_ng.pTracker.task.entity.Tasks;

public interface TaskRepo extends JpaRepository<Tasks, Long> {

}
