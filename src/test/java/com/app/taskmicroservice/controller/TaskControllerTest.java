package com.app.taskmicroservice.controller;

import com.app.taskmicroservice.dto.TaskDto;
import com.app.taskmicroservice.entity.Task;
import com.app.taskmicroservice.service.TaskService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class TaskControllerTest {

    private MockMvc mockMvc;
    @InjectMocks
    private TaskController taskController;
    @Mock
    TaskService taskService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(taskController).build();
    }

    @Test
    void addTask() throws Exception {
        when(taskService.addTask(taskDto())).thenReturn(taskMock());
        mockMvc.perform( MockMvcRequestBuilders
                        .post("/task/v1")
                        .content(asJsonString(taskDto()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void getAllTask() throws Exception {
        when(taskService.getTaskAll()).thenReturn(getTasks());
        ResultActions response = this.mockMvc.perform(get("/task/v1"));
        response.andExpect(status().isOk());
    }
    @Test
    void getAllTaskEmpty() throws Exception {
        ResultActions response = this.mockMvc.perform(get("/task/v1"));
        response.andExpect(status().isNoContent());
    }

    @Test
    void deleteTask() throws Exception {
        long taskId= 15L;
        doNothing().when(taskService).deleteTask(taskId);
        ResultActions response = mockMvc.perform(delete("/task/v1/{id}", taskId));
        response.andExpect(status().isNoContent());
    }

    @Test
    void getTaskById() throws Exception {
        Long idTask=15L;
        when(taskService.getTaskById(idTask)).thenReturn(Optional.of(taskMock()));
        ResultActions response = mockMvc.perform(get("/task/v1/{id}", idTask));
        response.andExpect(status().isOk());
    }

    @Test
    void getTaskByIdEmpty() throws Exception {
        Long idTask=15L;
        ResultActions response = mockMvc.perform(get("/task/v1/{id}", idTask));
        response.andExpect(status().isNoContent());
    }

    @Test
    void updateTask() throws Exception {
        Long idTask=15L;
        when(taskService.updateTask(idTask,taskDto())).thenReturn(taskMock());
        mockMvc.perform( MockMvcRequestBuilders
                        .put("/task/v1/{id}",idTask)
                        .content(asJsonString(taskDto()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }


    private static String asJsonString(final Object obj) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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

    private TaskDto taskDto(){
        TaskDto taskDto = new TaskDto();
        taskDto.setCodeTask("Code 001");
        taskDto.setTitle("Title 001");
        taskDto.setDescription("Description 001");
        return taskDto;
    }
}