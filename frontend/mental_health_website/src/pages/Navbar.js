import React, {useState} from 'react';
import {Link} from 'react-router-dom';
import "./Navbar.css"


function Navbar() {

  const [click, setClick] = useState(false);

  const menuIconClick = () => setClick(!click);

  const closeMenu = () => setClick(false);

  return (
    <>
      <nav className="nav">
        <div className='navbar-container'>
          <Link to="/" className="website_title">USYD Mental<br />Health Support</Link>
          <div className='menu-icon' onClick={menuIconClick}>
            <i className={click ? 'fas fa-times' : 'fas fa-bars'} />
          </div>
          <ul className={click ? 'nav-menu active' : 'nav-menu'}>
            <li className='nav-item'>
              <Link to='/' className='nav-links' onClick={closeMenu}>
                Home
              </Link>
            </li>
            <li className='nav-item'>
              <Link to='/information' className='nav-links' onClick={closeMenu}>
                Information
              </Link>
            </li>
            <li className='nav-item'>
              <Link to='/forum' className='nav-links' onClick={closeMenu}>
                Forum
              </Link>
            </li>
            <li className='nav-item'>
              <Link to='/appointment' className='nav-links' onClick={closeMenu}>
                Appointment
              </Link>
            </li>
            <li className='login-button'>
              <Link to='/login' className='nav-links-mobile' onClick={closeMenu}>
                Login
              </Link>
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