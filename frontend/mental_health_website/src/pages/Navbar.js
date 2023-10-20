import React, {useState} from 'react';
import {Link} from 'react-router-dom';
import "./Navbar.css"


function Navbar() {

  const search = () => {

  }

  return (
    <>
      <nav className="nav">
        <div className='navbar-container'>
          <Link to="/" className="website-title">USYD Mental<br />Health Support</Link>
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
                <input type='text' id='search-content'>
                  
                </input>
                
              </form>
              
            </li>
            <li className='nav-item'>
<<<<<<< HEAD
              <Link to='/login' className='nav-links'>
                Login
              </Link>
=======
              {localStorage.getItem('username') !== null ? (
                <div className='welcome-user'>
                  <div>Welcome, </div>
                  <div>{localStorage.getItem('username')}!</div>
                </div>
                
              ) : (
                <Link to='/login' className='nav-links'>
                  Login
                </Link>
              )}
              
>>>>>>> c6e22db9da70d7d1645bd1e6084dde7d63847fac
            </li>
          </ul>
        </div>
      </nav>
    </>
  )
}

export default Navbar;


/*
return (
    <nav className="nav">
      
      <ul>
        <li>
          <a href="/information">Information</a>
        </li>
        <li>
          <a href="/forum">Forum</a>
        </li>
        <li>
          <a href="/appointment">Appointment</a>
        </li>
      </ul>
      <ul>
        <li
      >
        <a href="/login">Login</a>
      </li>
      </ul>
      
    </nav>
  );
  */