import './App.css';
import './tailwind.css';
import BestPractices from "./components/BestPractices";
import {ToastContainer} from "react-toastify";
import 'react-toastify/dist/ReactToastify.css';

function App() {
  return (
      <div>
        <BestPractices/>
        <ToastContainer />
      </div>

  );
}

export default App;
