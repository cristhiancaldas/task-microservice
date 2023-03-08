package com.app.taskmicroservice.controller;

import com.app.taskmicroservice.dto.ResponseDTO;
import com.app.taskmicroservice.dto.TaskDto;
import com.app.taskmicroservice.entity.Task;
import com.app.taskmicroservice.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/task/v1")
public class TaskController {

    @Autowired
    TaskService taskService;

    @PostMapping
    public ResponseEntity<ResponseDTO> addTask(@RequestBody TaskDto taskDto) {
        var taskResponse = taskService.addTask(taskDto);
        var responseDTO = new ResponseDTO("User Data Created", taskResponse);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ResponseDTO> getAllTask() {
        List<Task> lstTask = taskService.getTaskAll();
        var responseDTO = new ResponseDTO("Get Call Success", lstTask);
        if (lstTask.isEmpty()) {
            return new ResponseEntity<>(responseDTO, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteTask(@PathVariable("id") long idTask) {
        taskService.deleteTask(idTask);
        var responseDTO = new ResponseDTO("Deleted Successfully", null);
        return new ResponseEntity<>(responseDTO, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getTaskById(@PathVariable("id") Long id) {
        Optional<Task> task = taskService.getTaskById(id);
        var responseDTO = new ResponseDTO("Get Call ById Success", task);
        if (task.isEmpty()) {
            return new ResponseEntity<>(responseDTO, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> updateTask(@PathVariable("id") Long id, @RequestBody TaskDto taskDto) {
        var taskResponse = taskService.updateTask(id, taskDto);
        var responseDTO = new ResponseDTO("Update Success", taskResponse);
        return new ResponseEntity<>(responseDTO, HttpStatus.NO_CONTENT);
    }

}
