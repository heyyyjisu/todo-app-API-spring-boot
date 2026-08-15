package io.nology.todo_api.category.dtos;

import jakarta.validation.constraints.NotBlank;

public class CreateCategoryRequest {


  @NotBlank
  private String name;

  public CreateCategoryRequest(@NotBlank String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
