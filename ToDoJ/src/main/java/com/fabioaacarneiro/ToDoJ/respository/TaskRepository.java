
package com.fabioaacarneiro.ToDoJ.respository;

import com.fabioaacarneiro.ToDoJ.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * TaskRepository
 */
public interface TaskRepository extends JpaRepository<Task, Long> {
}
