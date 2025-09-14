package com.hmcts.taskmanager.repository;

import com.hmcts.taskmanager.task.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}