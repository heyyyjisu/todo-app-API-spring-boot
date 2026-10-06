import styles from "./CategoryList.module.scss";
import type { Dispatch, SetStateAction } from "react";
import type { CategoryType } from "../../types/category";
import type { TodoType } from "../../types/todo";
import CategoryCard from "../CategoryCard/CategoryCard";

interface CategoryListProps {
  categories: CategoryType[];
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

export default function CategoryList(
  {
    categories,
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
  }: CategoryListProps
) {
  return (
    <div className={styles.categoryList}>
      <ul className={styles.myCategories}>
        {categories.map((c) => (
          <CategoryCard
            key={c.id}
            categories={categories}
            category={c}
            todos={todos}
            isEditing={isEditing}
            editingCategoryId={editingCategoryId}
            newCategoryName={newCategoryName}
            setNewCategoryName={setNewCategoryName}
            onDeleteCategory={onDeleteCategory}
            onEditClick={onEditClick}
            onClickOkay={onClickOkay}
            onClickCancel={onClickCancel}
            onDeleteTodo={onDeleteTodo}
          />
        ))}
      </ul>
    </div>
  );
}
