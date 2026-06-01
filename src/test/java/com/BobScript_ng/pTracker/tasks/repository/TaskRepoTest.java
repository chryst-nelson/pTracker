package com.BobScript_ng.pTracker.tasks.repository;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import com.BobScript_ng.pTracker.project.entity.Projects;
import com.BobScript_ng.pTracker.project.repository.ProjectRepo;
import com.BobScript_ng.pTracker.task.entity.TaskStatus;
import com.BobScript_ng.pTracker.task.entity.Tasks;
import com.BobScript_ng.pTracker.task.repository.TaskRepo;
import com.BobScript_ng.pTracker.user.entity.Role;
import com.BobScript_ng.pTracker.user.entity.User;
import com.BobScript_ng.pTracker.user.repository.UserRepo;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class TaskRepoTest {

    @Autowired
    private TaskRepo taskRepo;

    @Autowired
    private ProjectRepo projectRepo;

    @Autowired
    private UserRepo userRepo;

    private User savedUser;
    private Projects savedProject;

    @BeforeEach
    void setUp() {
        User user = new User();
        user.setFullname("Bob");
        user.setEmail("bob@test.com");
        user.setPassword("hashed");
        user.setRole(Role.USER);
        savedUser = userRepo.save(user);

        Projects project = new Projects();
        project.setName("Test Project");
        project.setOwner(savedUser);
        savedProject = projectRepo.save(project);
    }

    @Test
    void findByProjectId_shouldReturnTasksForProject() {
        Tasks task = new Tasks();
        task.setTitle("Test Task");
        task.setStatus(TaskStatus.TODO);
        task.setProject(savedProject);
        task.setAssigne(savedUser);
        taskRepo.save(task);

        List<Tasks> result = taskRepo.findByProjectId(savedProject.getId());

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getTitle()).isEqualTo("Test Task");
    }

    @Test
    void findByStatus_shouldReturnOnlyMatchingTasks() {
        Tasks todo = new Tasks();
        todo.setTitle("Todo Task");
        todo.setStatus(TaskStatus.TODO);
        todo.setProject(savedProject);
        todo.setAssigne(savedUser);
        taskRepo.save(todo);

        Tasks done = new Tasks();
        done.setTitle("Done Task");
        done.setStatus(TaskStatus.DONE);
        done.setProject(savedProject);
        done.setAssigne(savedUser);
        taskRepo.save(done);

        List<Tasks> result = taskRepo.findByStatus(TaskStatus.TODO);

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getTitle()).isEqualTo("Todo Task");
    }
}
