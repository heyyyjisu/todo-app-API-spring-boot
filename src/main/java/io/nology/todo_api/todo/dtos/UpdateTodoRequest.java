package io.nology.todo_api.todo.dtos;

import jakarta.validation.constraints.Pattern;

public class UpdateTodoRequest {

  @Pattern(regexp = ".*\\S.*", message = "Title cannot be empty")
  private String title;

  private Long categoryId;

  public void setCategoryId(Long categoryId) {
    this.categoryId = categoryId;
  }

  public Long getCategoryId() {
    return categoryId;
  }

  public UpdateTodoRequest() {
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

}
