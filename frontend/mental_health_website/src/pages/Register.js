import React, { useState } from 'react';
import { Form, Button } from 'react-bootstrap';
import './Register.css';
import { useNavigate } from 'react-router-dom';

export default function Register() {
    const navigate = useNavigate();
    const [email, setEmail] = useState('');
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [conPass, setConPass] = useState('');
    const [passwordsMatch, setPasswordsMatch] = useState(true);
    const [registrationError, setRegistrationError] = useState(null);

    const handleSubmit = (e) => {
        e.preventDefault();
        if (password === conPass) {
            console.log('Registration successful');

            fetch('http://localhost:8080/api/users/register', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ email, username, password }),
            })
                .then((response) => response.json())
                .then((data) => {
                    console.log('Registration response:', data);

                    if (data.success) {
                        console.log('Registration successful');
                        navigate('/login');
                    } else {
                        setRegistrationError('Registration failed. Please check your information.');
                    }
                })
                .catch((error) => {
                    console.error('Registration error:', error);
                    setRegistrationError('Registration failed. Please try again later.');
                });
        } else {
            console.log('Passwords do not match');
            setPasswordsMatch(false);
        }
    };

    return (
        <div className="loginPage">
            <div className="loginPageTitle">USYD Mental Health Support</div>
            <div className="loginForm">
                <Form onSubmit={handleSubmit}>
                    <p className="password-hint">Password must be at least 10 characters and include at least one</p>
                    <p className="password-hint">uppercase letter, one lowercase letter, one digit, and one special character.</p>
                    <Form.Group controlId="formTable" className="form-container">
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
                                    <Form.Label>Username</Form.Label>
                                </td>
                                <td>
                                    <Form.Control
                                        type="text"
                                        value={username}
                                        onChange={(e) => setUsername(e.target.value)}
                                        placeholder="YourUsername"
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
                            <tr>
                                <td>
                                    <Form.Label>Confirm Password</Form.Label>
                                </td>
                                <td>
                                    <Form.Control
                                        type="password"
                                        value={conPass}
                                        onChange={(e) => setConPass(e.target.value)}
                                        placeholder="**********"
                                    />
                                </td>
                            </tr>
                            </tbody>
                        </table>

                    </Form.Group>

                    {!passwordsMatch && <p className="password-mismatch-error">Passwords do not match.</p>}
                    {registrationError && <p className="registration-error">{registrationError}</p>}

                    <div className="register-button">
                        <div>
                            <Button type="submit" variant="primary" className="btn btn-lg">
                                REGISTER
                            </Button>
                        </div>
                    </div>
                </Form>

                <div className="register-button">
                    <Button
                        className="btn btn-secondary btn-lg"
                        onClick={() => navigate('/login')}
                    >
                        Back to Login
                    </Button>
                </div>
            </div>
        </div>
    );
}
