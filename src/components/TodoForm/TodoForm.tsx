import styles from "./TodoForm.module.scss";
import { type Dispatch, type SetStateAction } from "react";
import type { CategoryType } from "../../types/category";
import Button from "../Button/Button";

interface TodoProps {
  categories: CategoryType[];
  todoInput: string;
  setTodoInput: Dispatch<SetStateAction<string>>;
  categoryId: number | null;
  setCategoryId: Dispatch<SetStateAction<number | null>>;
  onAddTodo: () => void;
}

export default function TodoForm({
  categories,
  todoInput,
  setTodoInput,
  categoryId,
  setCategoryId,
  onAddTodo,
}: TodoProps) {
  return (
    <div className={styles.todoForm}>
      <div className={styles.addNewTodo}>
        <header>Todo</header>
     
          <select
          className={styles.todoSelect}
            value={categoryId ?? ""}
            onChange={(e) =>
              setCategoryId(
                e.target.value === "" ? null : Number(e.target.value),
              )
            }
          >
            <option value=""> Categories...</option>
            {categories.map((c) => (
              <option key={c.id} value={c.id}>
                {c.name}
              </option>
            ))}
          </select>
          <input
          className={styles.todoInput}
            placeholder="Type here..."
            value={todoInput}
            onChange={(e) => setTodoInput(e.target.value)}
          />

        <Button
          variant="submitBtn"
          onClick={onAddTodo}
          disabled={categoryId === null}
        >
          Add
        </Button>
      </div>
    </div>
  );
}
