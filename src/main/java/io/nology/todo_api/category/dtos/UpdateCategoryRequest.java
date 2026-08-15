package io.nology.todo_api.category.dtos;

import jakarta.validation.constraints.Pattern;

public class UpdateCategoryRequest {

  @Pattern(regexp = ".*\\S.*", message = "Name cannot be empty")
  private String name;

  public UpdateCategoryRequest() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
