package io.nology.todo_api.todo;

import org.springframework.data.jpa.repository.JpaRepository;

import io.nology.todo_api.todo.entities.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {

}
