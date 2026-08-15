package io.nology.todo_api.category;

import org.springframework.data.jpa.repository.JpaRepository;

import io.nology.todo_api.category.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
