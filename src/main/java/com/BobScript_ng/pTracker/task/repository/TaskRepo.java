package com.BobScript_ng.pTracker.task.repository;

import java.util.List;

import org.springframework.boot.data.autoconfigure.web.DataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import com.BobScript_ng.pTracker.task.entity.TaskStatus;
import com.BobScript_ng.pTracker.task.entity.Tasks;

public interface TaskRepo extends JpaRepository<Tasks, Long> {
    List<Tasks> findByProjectId(Long projectId);

    List<Tasks> findByStatus(TaskStatus status);

    Page<Tasks> findByProjectId(Long projectId, Pageable pageable);

}
