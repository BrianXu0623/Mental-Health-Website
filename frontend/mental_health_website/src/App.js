import ReactDOM from "react-dom/client";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Home from "./pages/Home";
import NoPage from "./pages/NoPage";
import Login from "./pages/Login";
import Navbar from "./pages/Navbar";
import Information from "./pages/Information";
import Forum from "./pages/Forum";
import Appointment from "./pages/Appointment";
import "./App.css";


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
          <Route path="/" element={<Home />}>
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




