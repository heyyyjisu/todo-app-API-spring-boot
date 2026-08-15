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

  private final TodoRepository repo;
  private final CategoryRepository categoryRepo;

  public TodoService(TodoRepository repo, CategoryRepository categoryRepo) {
    this.repo = repo;
    this.categoryRepo = categoryRepo;
  }

  public Todo create(CreateTodoRequest request) {
    Todo createdTodo = new Todo();

    Category category = this.categoryRepo.findById(request.getCategoryId()).orElseThrow(() -> new RuntimeException("Category not found"));

    createdTodo.setTitle(request.getTitle().trim());
    createdTodo.setCategory(category);

    this.repo.save(createdTodo);
    return createdTodo;
  }

  public List<Todo> findAll() {
    return this.repo.findAll();
  }

  public Optional<Todo> findById(Long id) {
    return this.repo.findById(id);
  }

  public boolean deleteById(Long id) {
    Optional<Todo> result = this.findById(id);
    if(result.isEmpty()) {
      return false;
    }
    this.repo.delete(result.get());
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
    
    this.repo.saveAndFlush(foundTodo);
    return Optional.of(foundTodo);
  }

}
