import ReactDOM from "react-dom/client";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import NoPage from "./pages/NoPage";
import Login from "./pages/Login";
import Navbar from "./pages/Navbar";
import Information from "./pages/Information";
import Forum from "./pages/Forum";
import Appointment from "./pages/Appointment";
import "./App.css";
import Register from "./pages/Register";


export default function App() {
  return (
    
    <BrowserRouter>
    <Navbar />
      <div>
      
      <Routes>
          <Route path="/information" element={<Information />}>
          </Route>
          <Route path="/forum" element={<Forum />}>
          </Route>
          <Route path="/appointment" element={<Appointment />}>
          </Route>
          <Route path="/login" element={<Login />}>
          </Route>
          <Route path="/register" element={<Register />}>
          </Route>
          <Route path="/" element={<Information />}>
          </Route>
          <Route path="*" element={<NoPage />}>
          </Route>
        </Routes>
        
      </div>
    </BrowserRouter>
  );
}

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(<App />);

// import './App.css';
// function App(){
//     const [currentForm, setCurrentForm] = useState('login')
//
//     const toggleForm = (formName) =>{
//         setCurrentForm(formName);
//     }
//     return (
//         <div className= "App">
//             {
//                 currentForm ==="login" ? <Login onFormSwitch={toggleForm}/> : <Register onFormSwitch={toggleForm}/>
//             }
//         </div>
//     );
// }
//
// export default App;


