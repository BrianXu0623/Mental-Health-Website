import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useLocation } from 'react-router-dom';
import "./Navbar.css"


function Navbar() {

  const search = () => {

  }

  const [username, setUsername] = useState(null);
  const navigate = useNavigate();
  const location = useLocation();

  const checkUsername = () => {
    const storedUsername = localStorage.getItem('username');
    setUsername(storedUsername);
  }

  useEffect(() => {
    checkUsername();

    const unlisten = () => {
      checkUsername();
    };

    return () => {
      unlisten();
    };
  }, [location]);

  return (
    <>
      <nav className="nav">
        <div className='navbar-container'>
          <Link to="/" className="website-title">USYD Mental<br/>Health Support</Link>
          <ul className='nav-menu'>
            <li className='nav-item'>
              <Link to='/information' className='nav-links'>
                Information
              </Link>
            </li>
            <li className='nav-item'>
              <Link to='/forum' className='nav-links'>
                Forum
              </Link>
            </li>
            <li className='nav-item'>
              <Link to='/appointment' className='nav-links'>
                Appointment
              </Link>
            </li>
            
          </ul>
          <ul className='nav-menu'>
            <li className='search-bar-container'>
              <form className='search-bar' onSubmit={search}>
                <input type='text' id='search-content' placeholder='search'>
                </input>
                
              </form>
              
            </li>
            <li className='nav-item'>
              {username !== null ? (
                <Link to='/account' className='welcome-user'>
                  <div>Welcome, </div>
                  <div>{username}!</div>
                </Link>
                
              ) : (
                <Link to='/login' className='nav-links'>
                  Login
                </Link>
              )}
              
            </li>
          </ul>
        </div>
      </nav>
    </>
  )
}

export default Navbar;
