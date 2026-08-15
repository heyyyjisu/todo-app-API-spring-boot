package io.nology.todo_api.category;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import io.nology.todo_api.category.dtos.CreateCategoryRequest;
import io.nology.todo_api.category.dtos.UpdateCategoryRequest;
import io.nology.todo_api.category.entities.Category;

@Service
public class CategoryService {

  private final CategoryRepository repo;

  public CategoryService(CategoryRepository repo) {
    this.repo = repo;
  }

  public Category create(CreateCategoryRequest request) {
    Category createdCategory = new Category();
    createdCategory.setName(request.getName().trim());
    this.repo.save(createdCategory);
    return createdCategory;
  }

  public List<Category> findAll() {
    return this.repo.findAll();
  }

  public Optional<Category> findById(Long id) {
    return this.repo.findById(id);
  }

  public boolean deleteById(Long id) {
    Optional<Category> result = this.findById(id);
    if(result.isEmpty()) {
      return false;
    }
    this.repo.delete(result.get());
    return true;
  }

  public Optional<Category> updateById(Long id, UpdateCategoryRequest data) {
    Optional<Category> result = this.findById(id);
    if(result.isEmpty()) {
      return result;
    }

    Category foundCategory = result.get();
    if(data.getName() != null) {
          foundCategory.setName(data.getName().trim());
    }


    this.repo.saveAndFlush(foundCategory);
    return Optional.of(foundCategory);
    
  }

}
