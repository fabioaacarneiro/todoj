package com.fabioaacarneiro.ToDoJ.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fabioaacarneiro.ToDoJ.entities.Task;
import com.fabioaacarneiro.ToDoJ.respository.TaskRepository;
import com.fabioaacarneiro.ToDoJ.service.exceptions.taskExceptions.TaskListIsEmptyException;
import com.fabioaacarneiro.ToDoJ.service.exceptions.taskExceptions.TaskNotFoundException;
import com.fabioaacarneiro.ToDoJ.service.exceptions.taskExceptions.TaskNotInsertedException;

/**
 * TaskService
 */
@Service
public class TaskService {

    @Autowired
    private TaskRepository repository;

    public List<Task> findAll() {
        List<Task> allTasks = repository.findAll();
        return allTasks;
    }

    public Task findById(Long id) {
        Optional<Task> task = repository.findById(id);
        return task.orElseThrow(() -> new TaskListIsEmptyException());
    }

    public Task insert(Task task) {
        try {
            return repository.save(task);
        } catch (RuntimeException e) {
            throw new TaskNotInsertedException();
        }
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (RuntimeException e) {
            throw new TaskNotFoundException();
        }
    }

	public void update(Long id, Task newTask) {
		try {
			Task task = repository.getReferenceById(id);
			updateTask(task, newTask);
			repository.save(task);
		} catch (RuntimeException e) {
            throw new TaskNotFoundException();
		}
	}

    private void updateTask(Task task, Task newTask) {
        task.setTitle(newTask.getTitle());
        task.setTaskInfo(newTask.getTaskInfo());
    }
}
