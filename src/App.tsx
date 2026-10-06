import { useEffect, useState } from "react";
import "./App.scss";
import type { CategoryType } from "./types/category";
import type { TodoType } from "./types/todo";
import CategoryList from "./components/CategoryList/CategoryList";
import CategoryForm from "./components/CategoryForm/CategoryForm";
import TodoForm from "./components/TodoForm/TodoForm";

function App() {
  const [categories, setCategories] = useState<CategoryType[]>([]);
  const [todos, setTodos] = useState<TodoType[]>([]);

  const [inputCategory, setInputCategory] = useState<string>("");
  const [isEditing, setIsEditing] = useState<boolean>(false);
  const [editingCategoryId, setEditingCategoryId] = useState<number | null>(
    null,
  );
  const [newCategoryName, setNewCategoryName] = useState<string>("");

  const [todoInput, setTodoInput] = useState("");
  const [categoryId, setCategoryId] = useState<number | null>(null);

  //do we only fetch once at mount? or do we update this or only update when added or removed?

  //fetch categories
  useEffect(() => {
    const getCategories = async () => {
      try {
        const res = await fetch("http://localhost:8080/categories");
        if (!res.ok) {
          throw new Error(`no data found ${res.status}`);
        }
        const data = await res.json();
        setCategories(data);
      } catch (err) {
        if (err instanceof Error) {
          console.error(err.message);
        } else {
          console.error(err);
        }
      }
    };
    getCategories();
  }, []);

  //fetch todos
  useEffect(() => {
    const getTodos = async () => {
      try {
        const res = await fetch("http://localhost:8080/todos");
        if (!res.ok) {
          throw new Error(`no data found ${res.status}`);
        }
        const data = await res.json();
        setTodos(data);
      } catch (err) {
        if (err instanceof Error) {
          console.error(err.message);
        } else {
          console.error(err);
        }
      }
    };
    getTodos();
  }, []);

  //category function
  const handleAddCategory = async (name: string) => {
    try {
      const res = await fetch("http://localhost:8080/categories", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          name: name,
        }),
      });
      setInputCategory("");
      if (!res.ok) {
        throw new Error(`HTTP error! Status: ${res.status}`);
      }
      const data = await res.json();
      console.log(data);
      setCategories((prev) => [...prev, data]);
    } catch (err) {
      if (err instanceof Error) {
        console.log(err.message);
      } else {
        console.log("Something went wrong: ", err);
      }
    }
  };
  //click to delete category
  const handleDeleteCategory = async (id: number) => {
    try {
      const res = await fetch(`http://localhost:8080/categories/${id}`, {
        method: "DELETE",
      });
      if (!res.ok) {
        throw new Error(`HTTP error! Status: ${res.status}`);
      }
      setCategories((prev) => prev.filter((c) => c.id !== id));
      console.log("category successfully deleted");
    } catch (err) {
      if (err instanceof Error) {
        console.log(err.message);
      } else {
        console.log("Something went wrong: ", err);
      }
    }
  };
  // to patch category
  const handlePatchCategory = async (id: number, newName: string) => {
    try {
      const res = await fetch(`http://localhost:8080/categories/${id}`, {
        method: "PATCH",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          id: id,
          name: newName,
        }),
      });
      if (!res.ok) {
        const body = await res.text();
        throw new Error(`HTTP error! Status: ${res.status} and body: ${body}`);
      }
      const data = await res.json();
      console.log(data);
      setCategories((prev) => prev.map((c) => (c.id === data.id ? data : c)));
      console.log("successfully updated");
      setIsEditing(false);
    } catch (err) {
      if (err instanceof Error) {
        console.log(err.message);
      } else {
        console.log("Something went wrong: ", err);
      }
    }
  };
  //when you click edit
  const handleEditClick = (id: number, oldName: string) => {
    setIsEditing(true);
    setNewCategoryName(oldName);
    setEditingCategoryId(id);
  };
  //when you click okay on editing
  const handleOkayClick = (id: number, newName: string) => {
    setIsEditing(true);
    handlePatchCategory(id, newName);
  };
  //when you click cancel on editing
  const handleCancelClick = () => {
    setIsEditing(false);
  };

  //todo function
  const handleAddTodo = async () => {
    if (categoryId === null) return;
    try {
      const res = await fetch("http://localhost:8080/todos", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          categoryId: Number(categoryId),
          title: todoInput,
        }),
      });
      if (!res.ok) {
        throw new Error(`HTTP error! Status: ${res.status}`);
      }
      const data = await res.json();
      console.log(data);
      setTodos((prev) => [...prev, data]);
      setTodoInput("");
    } catch (err) {
      if (err instanceof Error) {
        console.log(err.message);
      } else {
        console.log("Something went wrong: ", err);
      }
    }
  };
  //delete todo
  const handleDeleteTodo = async (id: number) => {
    try {
      const res = await fetch(`http://localhost:8080/todos/${id}`, {
        method: "DELETE",
      });
      if (!res.ok) {
        throw new Error(`HTTP error! Status: ${res.status}`);
      }
      setTodos((prev) => prev.filter((t) => t.id !== id));
      console.log("todo successfuly deleted");
    } catch (err) {
      if (err instanceof Error) {
        console.log(err.message);
      } else {
        console.log("Something went wrong: ", err);
      }
    }
  };

  console.log("todos: ", todos);
  console.log("categories: ", categories);

  return (
    <>
      <div className="pageWrapper">
        <div className="contentWrapper">
          <div className="formWrapper">

          <CategoryForm
            categories={categories}
            todos={todos}
            inputCategory={inputCategory}
            setInputCategory={setInputCategory}
            onAddCategory={handleAddCategory}
            />

          <TodoForm
            categories={categories}
            todoInput={todoInput}
            setTodoInput={setTodoInput}
            categoryId={categoryId}
            setCategoryId={setCategoryId}
            onAddTodo={handleAddTodo}
            />
            </div>

          {/* <Todos
            categories={categories}
            todos={todos}
            todoInput={todoInput}
            setTodoInput={setTodoInput}
            categoryId={categoryId}
            setCategoryId={setCategoryId}
            onAddTodo={handleAddTodo}
            onDeleteTodo={handleDeleteTodo}
          /> */}

          <CategoryList
            categories={categories}
            todos={todos}
            isEditing={isEditing}
            editingCategoryId={editingCategoryId}
            newCategoryName={newCategoryName}
            setNewCategoryName={setNewCategoryName}
            onDeleteCategory={handleDeleteCategory}
            onEditClick={handleEditClick}
            onClickOkay={handleOkayClick}
            onClickCancel={handleCancelClick}
            onDeleteTodo={handleDeleteTodo}
          />
        </div>
      </div>
    </>
  );
}

export default App;
