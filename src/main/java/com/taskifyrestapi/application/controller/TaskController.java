package com.taskifyrestapi.application.controller;

import com.taskifyrestapi.application.dto.TaskDTO;
import com.taskifyrestapi.application.model.Project;
import com.taskifyrestapi.application.model.Task;
import com.taskifyrestapi.application.service.ProjectService;
import com.taskifyrestapi.application.service.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/teamleader")
@CrossOrigin(origins = "http://localhost:3000")
public class TaskController {

    private TaskService taskService;
    private ProjectService projectService ;

    @Autowired
    public TaskController(ProjectService projectService , TaskService taskService){
        this.taskService = taskService ;
        this.projectService = projectService ;
    }


    @PostMapping("/tasks/project/{projectId}/member/{memberId}")
    @PreAuthorize("hasAuthority('TEAMLEADER')")
    public ResponseEntity<Task> createTask(@RequestBody TaskDTO taskDTO , @PathVariable int projectId, @PathVariable int memberId) {
        Task createdTask = taskService.createTask(taskDTO, projectId, memberId);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    @GetMapping("/tasks")
    @PreAuthorize("hasAuthority('TEAMLEADER')")
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("/tasks/{id}")
    @PreAuthorize("hasAuthority('TEAMLEADER')")
    public ResponseEntity<Task> getTaskById(@PathVariable int id) {
        Optional<Task> task = taskService.getTaskById(id);
        if (!task.isEmpty()) {
            return ResponseEntity.ok(task.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/tasks/{id}")
    @PreAuthorize("hasAuthority('TEAMLEADER')")
    public ResponseEntity<Task> updateTask(@PathVariable int id, @RequestBody TaskDTO taskDTO) {
        if (taskDTO.getId() < 0 ) taskDTO.setId(id);
        Task updatedTask = taskService.updateTask(taskDTO);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/tasks/{id}")
    @PreAuthorize("hasAuthority('TEAMLEADER')")
    public ResponseEntity<?> deleteTask(@PathVariable int id) {
        return taskService.getTaskById(id)
                .map(task -> {
                    taskService.deleteTask(id);
                    return ResponseEntity.ok().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }



}
