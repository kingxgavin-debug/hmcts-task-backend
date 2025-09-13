package com.hmcts.taskmanager.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hmcts.taskmanager.task.Task;

@Service
public class TaskService {

    public Task createTask(Task task) {
        return null;
    }

    public List<Task> getAllTasks() {
        return null;
    }

    public Task getTaskById(Long id) {
        return null;
    }

    public Task updateTaskStatus(Long id, String status) {
        return null;
    }

    public void deleteTask(Long id) {
    }
}