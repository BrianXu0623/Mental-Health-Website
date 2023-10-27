import React, { useEffect } from 'react';
import ReactDOM from "react-dom";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import NoPage from "./pages/NoPage";
import Login from "./pages/Login";
import Navbar from "./pages/Navbar";
import Information from "./pages/Information";
import Forum from "./pages/Forum";
import Appointment from "./pages/Appointment";
import Register from "./pages/Register";
import DetailedInformation from "./pages/DetailedInformation";
import DetailedThread from "./pages/DetailedThread";
import ProfilePage from "./pages/ProfilePage";
import UserProfile from "./pages/UserProfile";
import NewPost from "./pages/NewPost";
import NewAppointment from "./pages/NewAppointment";
import AddComment from "./pages/AddComment";

export default function App() {
    // useEffect(() => {
    //     window.addEventListener('beforeunload', clearLocalStorage);
    // }, []);

    // const clearLocalStorage = () => {
    //     localStorage.clear();
    // };

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
          <Route path="/appointment/make/:doctorId" element={<NewAppointment />} />
          <Route path="/forum/newthread/" element={<NewPost />} />
          <Route path="/" element={<Information />} />
          <Route path="*" element={<NoPage />} />
          <Route path="/addcomment/:id" element={<AddComment />} />
      </Routes>
        
      </div>
    </BrowserRouter>
  );
}

