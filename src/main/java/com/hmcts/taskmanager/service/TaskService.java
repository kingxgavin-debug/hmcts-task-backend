package com.hmcts.taskmanager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hmcts.taskmanager.repository.TaskRepository;
import com.hmcts.taskmanager.task.Task;

@Service
public class TaskService {

	@Autowired
    private TaskRepository taskRepository;

    public Task createTask(Task task) {
    	return taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
    	return taskRepository.findAll();
    }

    public Task getTaskById(Long id) {
    	Optional<Task> task = taskRepository.findById(id);
        return task.orElse(null);
    }

    public Task updateTaskStatus(Long id, Task task) {
    	Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Task origTask = optionalTask.get();
            task.setStatus(task.getStatus());
            return taskRepository.save(task);
        }
        return null;

    }

    public void deleteTask(Long id) {
    	taskRepository.deleteById(id);
    }
}