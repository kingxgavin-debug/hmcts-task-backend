package com.hmcts.taskmanager.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hmcts.taskmanager.service.TaskService;
import com.hmcts.taskmanager.task.Task;

@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TaskService taskService;

    @Test
    void shouldCreateTaskSuccessfully() throws Exception {
        Task input = new Task("New Task", "desc", "pending", LocalDateTime.now().plusDays(1));
        Task saved = new Task("New Task", "desc", "pending", input.getDueDate());

        when(taskService.createTask(any(Task.class))).thenReturn(saved);

        mockMvc.perform(post("/api/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(input)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.title").value("New Task"))
            .andExpect(jsonPath("$.status").value("pending"));

        verify(taskService).createTask(any(Task.class));
    }

    @Test
    void shouldReturnAllTasks() throws Exception {
        List<Task> tasks = List.of(
            new Task("Task 1", "desc1", "pending", LocalDateTime.now().plusDays(1)),
            new Task("Task 2", "desc2", "done", LocalDateTime.now().plusDays(2))
        );

        when(taskService.getAllTasks()).thenReturn(tasks);

        mockMvc.perform(get("/api/tasks"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.length()").value(2))
            .andExpect(jsonPath("$[0].title").value("Task 1"))
            .andExpect(jsonPath("$[1].status").value("done"));
    }

    @Test
    void shouldReturnTaskById() throws Exception {
        Task task = new Task("Task A", "descA", "active", LocalDateTime.now().plusDays(3));
        when(taskService.getTaskById(1L)).thenReturn(task);

        mockMvc.perform(get("/api/tasks/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.title").value("Task A"))
            .andExpect(jsonPath("$.status").value("active"));
    }

    @Test
    void shouldUpdateTaskStatus() throws Exception {
        Task updated = new Task("Updated Task", "desc", "done", LocalDateTime.now().plusDays(5));
        when(taskService.updateTaskStatus(eq(1L), any(Task.class))).thenReturn(updated);

        mockMvc.perform(put("/api/tasks/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updated)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.status").value("done"));
    }

    @Test
    void shouldDeleteTask() throws Exception {
        doNothing().when(taskService).deleteTask(1L);

        mockMvc.perform(delete("/api/tasks/1"))
            .andExpect(status().isNoContent());
    }
}