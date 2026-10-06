import styles from "./CategoryForm.module.scss";
import type { Dispatch, SetStateAction } from "react";
import type { CategoryType } from "../../types/category";
import type { TodoType } from "../../types/todo";
import Button from "../Button/Button";

interface CategoryFormProps {
  categories: CategoryType[];
  todos: TodoType[];
  inputCategory: string;
  setInputCategory: Dispatch<SetStateAction<string>>;
  onAddCategory: (name: string) => void;
}

export default function CategoryForm({
  inputCategory,
  setInputCategory,
  onAddCategory,
}: CategoryFormProps) {
  
  return (
    <div className={styles.categoryForm}>
      {/* category */}
      <div className={styles.addNewCategory}>
        <header>Category</header>
        <input
        className={styles.categoryInput}
          type="text"
          placeholder="type here..."
          value={inputCategory}
          onChange={(e) => setInputCategory(e.target.value)}
        />
        <Button
          variant="submitBtn"
          onClick={() => onAddCategory(inputCategory)}
        >
          Add
        </Button>
      </div>

      {/* category list */}
      {/* //how do you choose the right category to the handle edit? 
                //{isEdit && <input />} every row checks the SAME boolean
                //isEdit is like the light switch, and the id is which one?
                // meaning, editId  doesn't answer if it is happening, but answers which category with the id is being edited */}
      
    </div>
  );
}
