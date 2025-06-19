package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DbServiceTest {
    private Task task1;
    private Task task2;
    @Mock
    private TaskRepository taskRepository;
    @InjectMocks
    private DbService dbService;

    @BeforeEach
    void setUp() {
        task1 = new Task(1L, "Task 1", "Content 1");
        task2 = new Task(2L, "Task 2", "Content 2");
    }

    @Test
    void shouldGetAllTasks() {
        // Given
        when(taskRepository.findAll()).thenReturn(List.of(task1, task2));

        // when
        List<Task> expectedTasks = dbService.getAllTasks();

        // Then
        assertTrue(expectedTasks.size() == 2);
        assertTrue(expectedTasks.contains(task1));
        assertTrue(expectedTasks.contains(task2));
    }

    @Test
    void shouldGetTaskById() {
        // Given
        Task retrievedTask1 = new Task();

        // When
        when(taskRepository.findById(task1.getId())).thenReturn(Optional.of(task1));
        try {
            retrievedTask1  = dbService.getTask(task1.getId());
        } catch (Exception e) {
            e.getMessage();
        }

        // Then
        assertEquals("Task 1", retrievedTask1.getTitle());
    }

    @Test
    void shouldSaveTask() {
        // When
        when(taskRepository.save(task1)).thenReturn(task1);
        Task savedTask = dbService.saveTask(task1);

        // Then
        assertEquals("Content 1", savedTask.getContent());
    }

    @Test
    void shouldDeleteTask() {
        // When
        doNothing().when(taskRepository).deleteById(task2.getId());

        dbService.deleleTask(task2.getId());

        // Then
        verify(taskRepository, times(1)).deleteById(task2.getId());
    }
}