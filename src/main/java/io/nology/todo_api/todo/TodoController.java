package io.nology.todo_api.todo;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
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

  @GetMapping()
  public ResponseEntity<List<TodoResponse>> getAllTodos() {
    List<Todo> allTodos = this.service.findAll();
      return ResponseEntity.ok(TodoResponse.of(allTodos));
  }

  @GetMapping("/{id}")
  public ResponseEntity<TodoResponse> getById(@PathVariable Long id) {
    Todo result = this.service
            .findById(id).orElseThrow(()-> new RuntimeException("Not found"));
    return ResponseEntity.ok(TodoResponse.of(result));
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
    Todo result = this.service.updateById(id, data).orElseThrow(() -> new RuntimeException("could not find todo with id " + id));
    return ResponseEntity.ok(TodoResponse.of(result));
  }

}
  
  


