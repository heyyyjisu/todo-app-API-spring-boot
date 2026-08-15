package io.nology.todo_api.todo.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateTodoRequest {

  @NotBlank
  private String title;

  @NotNull
  private Long categoryId;

  public void setCategoryId(Long categoryId) {
    this.categoryId = categoryId;
  }

  public Long getCategoryId() {
    return categoryId;
  }

  public CreateTodoRequest() {
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }



}
