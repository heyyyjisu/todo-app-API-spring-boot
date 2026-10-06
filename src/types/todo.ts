import type { CategoryType } from "./category";

export interface TodoType {
  id: number;
  title: string;
  done: boolean;
  category: CategoryType;
}