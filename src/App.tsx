import './App.scss';
import CategoryList from './components/CategoryList/CategoryList';

function App() {
  return (
    <>
      <h1>Todo App</h1>
      <CategoryList />
      <button>Add new task</button> 
      <div>list</div>
    </>
  );
}

export default App;