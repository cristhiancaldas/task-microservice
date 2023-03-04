package com.app.taskmicroservice.service;

import com.app.taskmicroservice.entity.Task;
import com.app.taskmicroservice.exceptions.ToDoExceptions;
import com.app.taskmicroservice.repository.TaskRepository;
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

    public Task addTask(Task taskRequest) {
        taskRequest.setCreateDate(LocalDateTime.now());
        taskRequest.setActive(true);
        Task task = taskRepository.save(taskRequest);
       return task;
    }

    public List<Task> getTaskAll() {
      return taskRepository.findAll();
    }

    public void deleteTask(Long idTask){
        taskRepository.deleteById(idTask);
    }

    public Optional<Task> getTaskById(Long idTask){
        return taskRepository.findById(idTask);
    }

    public Task updateTask(Long idTask ,Task taskUpdate ){
      Optional<Task> task =  taskRepository.findById(idTask);
      if(task.isEmpty()){
          throw new ToDoExceptions("Task no encontrada", HttpStatus.NOT_FOUND);
      }
        taskUpdate.setId(idTask);
        taskUpdate.setCreateDate(task.get().getCreateDate());
        taskUpdate.setActive(task.get().isActive());
         return taskRepository.save(taskUpdate);
    }

}
