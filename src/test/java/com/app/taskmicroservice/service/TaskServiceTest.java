package com.app.taskmicroservice.service;

import com.app.taskmicroservice.dto.TaskDto;
import com.app.taskmicroservice.entity.Task;
import com.app.taskmicroservice.exceptions.ToDoExceptions;
import com.app.taskmicroservice.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TaskServiceTest {

    @Mock
    TaskRepository  taskRepository;

    @InjectMocks
    private TaskService taskService;
    @Mock
    private ModelMapper modelMapper;
    private Task task;
    private TaskDto taskDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        task=taskMock();
        taskDto=taskDto();
    }

    @Test
    void addTask() {
        when(taskRepository.save(any(Task.class))).thenReturn(task);
        when(modelMapper.map(taskDto,Task.class))
                .thenReturn(task);
        taskService.addTask(taskDto);
        verify(taskRepository, times(1)).save(any());

    }

    @Test
    void getTaskAll() {
        when(taskRepository.findAll()).thenReturn(getTasks());
        taskService.getTaskAll();
        verify(taskRepository,times(1)).findAll();
    }

    @Test
    void deleteTask() {
        Long idTask=15L;
        doNothing().when(taskRepository).deleteById(idTask);
        taskService.deleteTask(idTask);
        verify(taskRepository,times(1)).deleteById(idTask);
    }

    @Test
    void getTaskById() {
        Long taskId=16L;
        when(taskRepository.findById(taskId)).thenReturn(Optional.ofNullable(task));
        taskService.getTaskById(taskId);
        verify(taskRepository,times(1)).findById(taskId);
    }

    @Test
    void updateTask() {
        Long taskId=16L;
        when(taskRepository.findById(taskId)).thenReturn(Optional.ofNullable(task));
        when(modelMapper.map(taskDto,Task.class))
                .thenReturn(task);
        taskService.updateTask(taskId,taskDto);
        verify(taskRepository, times(1)).save(any());
    }

    @Test
    void updateTaskWithEmpty() {
        Long taskId=16L;
        var validate = assertThrows(ToDoExceptions.class,() ->{
            taskService.updateTask(taskId,taskDto);
        });
        assertEquals("Task no encontrada", validate.getMessage());
        assertEquals(HttpStatus.NOT_FOUND ,validate.getHttpStatus());
    }

    private Task taskMock(){
        Task task= new Task();
        task.setId(15L);
        task.setTitle("Mock 01");
        task.setCodeTask("mock001");
        task.setActive(true);
        task.setDescription("mock001 description");
        task.setCreateDate(LocalDateTime.now());
        return task;
    }
    private Task taskMockEmpty(){
        return null;
    }

    private TaskDto taskDto(){
        TaskDto taskDto = new TaskDto();
        taskDto.setCodeTask("Code 001");
        taskDto.setTitle("Title 001");
        taskDto.setDescription("Description 001");
       return taskDto;
    }

    private List<Task> getTasks(){
        List<Task> lstTask= new ArrayList<>();
        Task task01= new Task();
        task01.setId(15L);
        task01.setTitle("Mock 01");
        task01.setCodeTask("mock001");
        task01.setActive(true);
        task01.setDescription("mock001 description");
        task01.setCreateDate(LocalDateTime.now());

        Task task02= new Task();
        task02.setId(15L);
        task02.setTitle("Mock 01");
        task02.setCodeTask("mock001");
        task02.setActive(true);
        task02.setDescription("mock001 description");
        task02.setCreateDate(LocalDateTime.now());
        lstTask.add(task01);
        lstTask.add(task02);
        return lstTask;

    }
}