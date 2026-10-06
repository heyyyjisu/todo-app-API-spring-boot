import styles from "./CategoryCard.module.scss";
import type { Dispatch, SetStateAction } from "react";
import type { CategoryType } from "../../types/category";
import type { TodoType } from "../../types/todo";
import Button from "../Button/Button";
import Todos from "../Todos/Todos";

interface CategoryCardProps {
  categories: CategoryType[];
  category: CategoryType;
  todos: TodoType[];
  onDeleteTodo: (id: number) => void;
  isEditing: boolean;
  editingCategoryId: number | null;
  newCategoryName: string;
  setNewCategoryName: Dispatch<SetStateAction<string>>;
  onDeleteCategory: (id: number) => void;
  onEditClick: (id: number, oldName: string) => void;
  onClickOkay: (id: number, newName: string) => void;
  onClickCancel: () => void;
}

export default function CategoryCard({
  category,
  todos,
  isEditing,
  editingCategoryId,
  newCategoryName,
  setNewCategoryName,
  onDeleteCategory,
  onEditClick,
  onClickOkay,
  onClickCancel,
  onDeleteTodo,
}: CategoryCardProps) {
  return (
    <div>
      <li className={styles.categoryCard}>
        <div className={styles.categoryTag}>
          {isEditing && editingCategoryId === category.id ? (
            <div>
              <input
                value={newCategoryName}
                onChange={(e) => setNewCategoryName(e.target.value)}
              />
              <div>
                <Button
                  variant="categoryBtn"
                  onClick={() => onClickOkay(category.id, newCategoryName)}
                >
                  Okay
                </Button>
                <Button onClick={onClickCancel} variant="categoryBtn">
                  Cancel
                </Button>
              </div>
            </div>
          ) : (
            <div className={styles.categoryName}>
              {category.name}</div>
          )}

          <div className={styles.tagBtns}>
            <Button
              onClick={() => onEditClick(category.id, category.name)}
              variant="categoryBtn"
              disabled={isEditing}
            >
              Edit
            </Button>
            <Button
              onClick={() => onDeleteCategory(category.id)}
              variant="categoryBtn"
              disabled={
                todos.some((t) => t.category.id === category.id) || isEditing
              }
            >
              Delete
            </Button>
          </div>
        </div>

        <div className={styles.todoList}>
          <Todos todos={todos} onDeleteTodo={onDeleteTodo} />
        </div>
      </li>
    </div>
  );
}
