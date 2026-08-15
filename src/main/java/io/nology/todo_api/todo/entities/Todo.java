package io.nology.todo_api.todo.entities;

import io.nology.todo_api.category.entities.Category;
import io.nology.todo_api.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "todos")
public class Todo extends BaseEntity{

  @Column
  private String title;

  @Column
  private boolean done;

  @Column
  private boolean archived;

  @ManyToOne
  @JoinColumn(name = "category_id")
  private Category category;

  public void setCategory(Category category) {
    this.category = category;
  }

  public Category getCategory() {
    return category;
  }

  public Todo() {
  }

  public String getTitle() {
    return title;
  }

  public boolean isDone() {
    return done;
  }

  public boolean isArchived() {
    return archived;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setDone(boolean done) {
    this.done = done;
  }

  public void setArchived(boolean archived) {
    this.archived = archived;
  }

  

}
