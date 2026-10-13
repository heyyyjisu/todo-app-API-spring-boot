package io.nology.todo_api.todo;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.nology.todo_api.todo.dtos.CreateTodoRequest;
import io.nology.todo_api.todo.dtos.TodoResponse;
import io.nology.todo_api.todo.dtos.UpdateTodoRequest;
import io.nology.todo_api.todo.entities.Todo;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/todos")
public class TodoController {

  private final TodoService service;

  // controller pass to service and vice versa?
  public TodoController(TodoService service) {
    this.service = service;
  }
  
  @PostMapping()
  public ResponseEntity<TodoResponse> createTodo(@RequestBody @Valid CreateTodoRequest data) {
      System.out.println(data);
      Todo createdTodo = this.service.create(data);      
      return new ResponseEntity<TodoResponse>(TodoResponse.of(createdTodo), HttpStatus.CREATED);
  }

  @PostMapping("/{id}/restore")
  public ResponseEntity<TodoResponse> restoreById(@PathVariable Long id) {
    Optional<Todo> result = this.service.restoreById(id);
    if(result.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(TodoResponse.of(result.get()));
  }

  @GetMapping()
  public ResponseEntity<List<TodoResponse>> getAllTodos( @RequestParam(defaultValue = "false") boolean archived) {
    List<Todo> allTodos = this.service.findAll(archived);
      return ResponseEntity.ok(TodoResponse.of(allTodos));
  }

  @GetMapping("/{id}")
  public ResponseEntity<TodoResponse> getById(@PathVariable Long id) {
    Optional<Todo> result = this.service
            .findById(id);
            if(result.isEmpty()) {
              return ResponseEntity.notFound().build();
            }
    return ResponseEntity.ok(TodoResponse.of(result.get()));
  }

  @DeleteMapping("/{id}") 
  public ResponseEntity<Void> deleteTodoById(@PathVariable Long id) {
    boolean isDeleted = this.service.deleteById(id);
    if(isDeleted) {
      return ResponseEntity.noContent().build();
    }
    throw new RuntimeException("Could not find todo with id " + id);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<TodoResponse> updateTodoById(@PathVariable Long id, @Valid @RequestBody UpdateTodoRequest data) {
    Optional<Todo> result = this.service.updateById(id, data);
    if (result.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(TodoResponse.of(result.get()));
  }

}
  
  


