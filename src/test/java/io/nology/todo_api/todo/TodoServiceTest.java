package io.nology.todo_api.todo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import io.nology.todo_api.category.CategoryRepository;

@ExtendWith(MockitoExtension.class)
public class TodoServiceTest {
  
  @Mock 
  private TodoRepository todoRepo;

  @Mock private CategoryRepository categoryRepo;

  private TodoService todoService;

  @BeforeEach
  public void setup() {
    todoService = new TodoService(todoRepo, categoryRepo);
  }

}
