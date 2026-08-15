package io.nology.todo_api.todo.dtos;

import java.util.List;

import io.nology.todo_api.category.dtos.CategoryResponse;
import io.nology.todo_api.todo.entities.Todo;

public record TodoResponse(Long id, String title, boolean done, boolean archived, CategoryResponse category) {

  public static TodoResponse of(Todo todo) {
    return new TodoResponse(todo.getId(), todo.getTitle(), todo.isDone(), todo.isArchived(), todo.getCategory() == null ? null : CategoryResponse.of(todo.getCategory()));
  }

  public static List<TodoResponse> of(List<Todo> todos) {
    return todos.stream().map(t -> TodoResponse.of(t)).toList();
  }

}
