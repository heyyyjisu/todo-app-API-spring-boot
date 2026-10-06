import styles from "./Todos.module.scss";
import Button from "../Button/Button";
import type { TodoType } from "../../types/todo";

interface TodoProps {
  todos: TodoType[];
  onDeleteTodo: (id: number) => void;
}

export default function Todos({ todos, onDeleteTodo }: TodoProps) {

  //todo list has to be fetched from mysql
  //meaning using GET
  //meaning use useEffect? OR promises?
  //delete todo

  return (
        <ul className={styles.todos}>
          {todos.map((t) => (
            <li key={t.id} className={styles.todo}>{t.title}<Button variant="todoBtn" onClick={() => onDeleteTodo(t.id)}>✖️</Button></li>
          ))}
        </ul>

  );
}
