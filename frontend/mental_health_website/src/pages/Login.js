import React, { useState } from "react";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import { Link, useNavigate } from "react-router-dom";
import "./Login.css";

export default function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  function validateForm() {
    return email.length > 5 && password.length > 6;
  }

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await fetch("http://localhost:8080/api/users/login", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          email: email,
          password: password,
        }),
      });

      if (response.status === 200) {
        const data = await response.json();
        if (data.token) {
          localStorage.setItem("token", data.token);
          localStorage.setItem("username", data.username);
          navigate("/information");
        } else {
          setError("No token received");
        }
      } else {
        setError("Login failed. Please check your username and password.");
      }
    } catch (error) {
      console.error("There was an error!", error);
      setError("Login failed. Please check your username and password.");
    }
  };

  return (
      <div className="loginPage">
        <div className="loginPageTitle">USYD Mental Health Support</div>
        <div className="loginForm">
          <Form onSubmit={handleSubmit}>
            <table className="form-table">
              <tbody>
              <tr>
                <td>
                  <Form.Label>Email</Form.Label>
                </td>
                <td>
                  <Form.Control
                      type="email"
                      value={email}
                      onChange={(e) => setEmail(e.target.value)}
                      placeholder="EMAIL@GMAIL.COM"
                  />
                </td>
              </tr>
              <tr>
                <td>
                  <Form.Label>Password</Form.Label>
                </td>
                <td>
                  <Form.Control
                      type="password"
                      value={password}
                      onChange={(e) => setPassword(e.target.value)}
                      placeholder="**********"
                  />
                </td>
              </tr>
              </tbody>
            </table>

            <Button block size="lg" type="submit" disabled={!validateForm()}>
              Login
            </Button>
          </Form>

          <div className="forgot-password-button">
            <Button>Forgot Password</Button>
          </div>

          {error && <p className="login-error">{error}</p>}

          <div className="login-button">
            <Button
                className="btn btn-secondary btn-lg"
                onClick={() => navigate('/register')}
            >
              Don't have an account? Register
            </Button>
          </div>
        </div>
      </div>
  );
}
