import React, { useState } from "react";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import { Link } from "react-router-dom";
import "./Login.css";

export default function Login() {

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  function validateForm() {
    return email.length > 5 && password.length > 6;
  }
  function ButtonLink({ to, children }) {
    return <Link to={to}><button>{children}</button></Link>;
  }
  const handleSubmit = async (e) => {
    e.preventDefault();
    console.log("login!");

    try {
      const response = await fetch('http://localhost:8080/api/user/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    username: email,
                    password: password
                }) 
            });
      console.log('Login successful', response.data);
      
    } catch (error) {
        console.error('There was an error logging in!', error);
        
    }
  };
  return (
    <div className='loginPage'> 
      <div className="loginPageTitle">USYD Mental Health Support</div>
      <div className="loginForm">
        <Form onSubmit={handleSubmit}>
          <Form.Group size="lg" controlId="email">
            <Form.Label>Email</Form.Label>
            <Form.Control
              autoFocus
              type="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
            />
          </Form.Group>
          <Form.Group size="lg" controlId="password">
            <Form.Label>Password</Form.Label>
            <Form.Control
              type="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
            />
          </Form.Group>
          <Button block size="lg" type="submit" disabled={!validateForm()}>
            Login
          </Button>
        </Form>
        <div className="forgot-password-button">
          <Button>Forgot Password</Button>
        </div>

        <div className="register-button">
          <ButtonLink to="/register" className="btn btn-outline-secondary">
            Don't have an account? Register
          </ButtonLink>
        </div>
      </div>
    </div>
  );
}
