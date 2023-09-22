import React, { useState } from "react";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import { Link } from "react-router-dom";
export default function Login() {

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  function validateForm() {
    return email.length > 5 && password.length > 6;
  }
  function ButtonLink({ to, children }) {
    return <Link to={to}><button>{children}</button></Link>;
  }
  const handleSubmit = (e) => {
    e.preventDefault();
    console.log(email);
  };
  return (
      <> <h1 className="pageTitle">USYD Mental Health Support</h1>
    <div className="Login">
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
        </>
  );
}
