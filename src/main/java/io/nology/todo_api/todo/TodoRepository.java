package io.nology.todo_api.todo;

import org.springframework.data.jpa.repository.JpaRepository;

import io.nology.todo_api.todo.entities.Todo;
import java.util.List;


public interface TodoRepository extends JpaRepository<Todo, Long> {
  List<Todo> findByArchivedFalse();
  List<Todo> findByArchivedTrue();
}
