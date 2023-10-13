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
import DetailedInformation from "./pages/DetailedInformation";
import DetailedThread from "./pages/DetailedThread";
import ProfilePage from "./pages/ProfilePage";
import UserProfile from "./pages/UserProfile";
import NewPost from "./pages/NewPost";
import NewAppointment from "./pages/NewAppointment";

export default function App() {
  return (

    <BrowserRouter>
    <Navbar />
      <div>

      <Routes>
          <Route path="/information" element={<Information />} />
          <Route path="/information/:id" element={<DetailedInformation />} />
          <Route path="/forum" element={<Forum />} />
          <Route path="/forum/:id" element={< DetailedThread />} />
          <Route path="/appointment" element={<Appointment />} />
          <Route path="/profile/:id" element={<ProfilePage />} />
          <Route path="/account" element={<UserProfile />} />
          <Route path="/login" element={<Login />} />
          <Route path="/register" element={<Register />} />
          <Route path="/" element={<Information />} />
          <Route path="*" element={<NoPage />} />
          <Route path="/newPost" element={<NewPost />} />
          <Route path="/bookappointment/:doctorId" element={<NewAppointment />} />
      </Routes>
      </div>
    </BrowserRouter>
  );
}

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(<App />);




