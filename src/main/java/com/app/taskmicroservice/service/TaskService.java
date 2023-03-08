package com.app.taskmicroservice.service;

import com.app.taskmicroservice.dto.TaskDto;
import com.app.taskmicroservice.entity.Task;
import com.app.taskmicroservice.exceptions.ToDoExceptions;
import com.app.taskmicroservice.repository.TaskRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;
    @Autowired
    private ModelMapper modelMapper;

    public Task addTask(TaskDto taskDto) {
        taskDto.setCreateDate(LocalDateTime.now());
        taskDto.setActive(true);
        var task = modelMapper.map(taskDto, Task.class);
        return taskRepository.save(task);
    }

    public List<Task> getTaskAll() {
        return taskRepository.findAll();
    }

    public void deleteTask(Long idTask) {
        taskRepository.deleteById(idTask);
    }

    public Optional<Task> getTaskById(Long idTask) {
        return taskRepository.findById(idTask);
    }

    public Task updateTask(Long idTask, TaskDto taskDto) {
        Optional<Task> task = taskRepository.findById(idTask);
        if (task.isEmpty()) {
            throw new ToDoExceptions("Task no encontrada", HttpStatus.NOT_FOUND);
        }
        taskDto.setId(idTask);
        taskDto.setCreateDate(task.get().getCreateDate());
        taskDto.setActive(task.get().isActive());
        var taskUpdate = modelMapper.map(taskDto, Task.class);
        return taskRepository.save(taskUpdate);
    }

}
