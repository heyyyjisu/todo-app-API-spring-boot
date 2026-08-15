package io.nology.todo_api.category;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.nology.todo_api.category.dtos.CategoryResponse;
import io.nology.todo_api.category.dtos.CreateCategoryRequest;
import io.nology.todo_api.category.dtos.UpdateCategoryRequest;
import io.nology.todo_api.category.entities.Category;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/categories")
public class CategoryController {

  private final CategoryService service;

  public CategoryController(CategoryService service) {
    this.service = service;
  }

  @PostMapping()
  public ResponseEntity<CategoryResponse> createCategory (@RequestBody @Valid CreateCategoryRequest data) {
      System.out.println(data);
      Category createdCategory = this.service.create(data);

      return new ResponseEntity<CategoryResponse>(CategoryResponse.of(createdCategory), HttpStatus.CREATED);
  }

  @GetMapping()
  public ResponseEntity<List<CategoryResponse>> getAllCategories() {
    List<Category> allCategories = this.service.findAll();
      return ResponseEntity.ok(CategoryResponse.of(allCategories));
  }

  @GetMapping("/{id}")
  public ResponseEntity<CategoryResponse> getById(@PathVariable Long id) {
    Category result = this.service.findById(id).orElseThrow(()-> new RuntimeException("Not found"));

     return ResponseEntity.ok(CategoryResponse.of(result));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCategoryById(@PathVariable Long id) {
    boolean isDeleted = this.service.deleteById(id);
    if(isDeleted) {
      return ResponseEntity.noContent().build();
    }
    throw new RuntimeException("Could not find category with id " + id);
  }
  
  @PatchMapping("/{id}")
  public ResponseEntity<CategoryResponse> updateCategoryById(@PathVariable Long id, @Valid @RequestBody UpdateCategoryRequest data) {
    Category result = this.service.updateById(id, data).orElseThrow(() -> new RuntimeException("Could not find category with id "+ id));
    return ResponseEntity.ok(CategoryResponse.of(result));
  }
  
}
