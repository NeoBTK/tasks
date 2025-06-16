package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskMapperTests {
    private TaskMapper taskMapper;

    @Test
    void testAllMappers() {
        // Given
        Task task = new Task(1L, "task1", "task1content");
        TaskDto taskDto = new TaskDto(1L, "taskDto1", "taskDto1content");
        List<Task> tasks = List.of(task);

        taskMapper = new TaskMapper();

        // When
        TaskDto mappedTask = taskMapper.mapToTaskDto(task);
        Task mappedTaskDto = taskMapper.mapToTask(taskDto);
        List<TaskDto> mappedTasks = taskMapper.mapToTaskDtoList(tasks);

        // Then
        assertEquals("task1", mappedTask.getTitle());
        assertEquals("taskDto1content", mappedTaskDto.getContent());
        assertEquals(1, mappedTasks.size());
    }

}