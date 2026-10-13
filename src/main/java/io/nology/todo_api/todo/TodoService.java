package io.nology.todo_api.todo;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import io.nology.todo_api.category.CategoryRepository;
import io.nology.todo_api.category.entities.Category;
import io.nology.todo_api.todo.dtos.CreateTodoRequest;
import io.nology.todo_api.todo.dtos.UpdateTodoRequest;
import io.nology.todo_api.todo.entities.Todo;

@Service
public class TodoService {

  private final TodoRepository todoRepo;
  private final CategoryRepository categoryRepo;

  public TodoService(TodoRepository todoRepo, CategoryRepository categoryRepo) {
    this.todoRepo = todoRepo;
    this.categoryRepo = categoryRepo;
  }

  public Todo create(CreateTodoRequest request) {
    Todo createdTodo = new Todo();

    Category category = this.categoryRepo.findById(request.getCategoryId()).orElseThrow(() -> new RuntimeException("Category not found"));

    createdTodo.setTitle(request.getTitle().trim());
    createdTodo.setCategory(category);

    this.todoRepo.save(createdTodo);
    return createdTodo;
  }
 
  public List<Todo> findAll(boolean archived) {
    if(archived) {
      return this.todoRepo.findByArchivedTrue();
    }
    return this.todoRepo.findByArchivedFalse();
  }

  public Optional<Todo> findById(Long id) {
    Optional<Todo> result = this.todoRepo.findById(id).filter(todo -> !todo.isArchived());
    return result;
  }
  

  public boolean deleteById(Long id) {
    Optional<Todo> result = this.findById(id);
    if(result.isEmpty()) {
      return false;
    }
    Todo foundTodo = result.get();
    foundTodo.setArchived(true);
    this.todoRepo.save(foundTodo);
    return true;
  }

  public Optional<Todo> updateById(Long id, UpdateTodoRequest data) {
    Optional<Todo> result = this.findById(id);
    if(result.isEmpty()) {
      return result;
    }
    Todo foundTodo = result.get();

    if(data.getTitle() != null) {
      foundTodo.setTitle(data.getTitle().trim());
    }
    if(data.getCategoryId() != null) {
      Category category = this.categoryRepo.findById(data.getCategoryId()).orElseThrow(() -> new RuntimeException("category id not found"));

      foundTodo.setCategory(category);
    }
    
    this.todoRepo.saveAndFlush(foundTodo);
    return Optional.of(foundTodo);
  }

  
  public Optional<Todo> restoreById(Long id) {
    Optional<Todo> result = this.todoRepo.findById(id);
    if(result.isEmpty()) {
      return result;
    }
    Todo foundTodo = result.get();
    foundTodo.setArchived(false);
    this.todoRepo.save(foundTodo);
    return result;
  }
}
