import React, { useState } from 'react';
import { Form, Button } from 'react-bootstrap';
import { useNavigate } from 'react-router-dom';
import { Link } from 'react-router-dom';
import './Register.css';

export default function Register() {
    const navigate = useNavigate();
    const [email, setEmail] = useState('');
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [conPass, setConPass] = useState('');
    const [passwordsMatch, setPasswordsMatch] = useState(true);

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

                    navigate('/login');
                })
                .catch((error) => {
                    console.error('Registration error:', error);
                });
        } else {
            console.log('Passwords do not match');
            setPasswordsMatch(false);
        }
    };

    function ButtonLink({ to, children }) {
        return <Link to={to}>{children}</Link>;
    }

    return (
        <div className="loginPage">
            <div className="loginPageTitle">USYD Mental Health Support</div> {}
            <div className="loginForm"> {}
                <Form onSubmit={handleSubmit}>
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

                    <Button type="submit" variant="primary">
                        REGISTER
                    </Button>
                </Form>
                <div className="register-button">
                    <Button onClick={() => navigate('/login')}>Back To Login</Button>
                </div>
            </div>
        </div>
    );
}