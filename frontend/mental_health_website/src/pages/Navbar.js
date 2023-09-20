import React from 'react';

function Navbar() {
  return (
    <nav className="nav">
      <a href="/" className="website_title">USYD Mental<br />Health Support</a>
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
}

export default Navbar;
