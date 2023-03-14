package com.fabioaacarneiro.ToDoJ.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fabioaacarneiro.ToDoJ.service.TaskService;

import jakarta.validation.Valid;

import com.fabioaacarneiro.ToDoJ.entities.Task;

/**
 * TaskResource
 */
@RestController
@RequestMapping(value = "/tasks")
public class TaskController {

    @Autowired
    private TaskService service;

    @GetMapping
    public ResponseEntity<List<Task>> findAll() {
        List<Task> taskList = service.findAll();
        return ResponseEntity.ok().body(taskList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Task> findById(@PathVariable Long id) {
        Task task = service.findById(id);
        return ResponseEntity.ok().body(task);
    }

    @PostMapping
    public ResponseEntity<Task> insert(@Valid @RequestBody Task task) {
        task = service.insert(task);
        URI uri = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(task.getId())
            .toUri();

        return ResponseEntity.created(uri).body(task);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Task> update(@PathVariable Long id, @RequestBody Task task){
        service.update(id, task);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Task> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
