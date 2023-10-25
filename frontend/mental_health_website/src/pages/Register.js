import React, { useState} from 'react';
import { Form, Button } from 'react-bootstrap';
import { useNavigate } from 'react-router-dom';
import { Link } from 'react-router-dom';

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
                .then(response => response.json())
                .then(data => {
                    console.log('Registration response:', data);

                    navigate('/login');
                })
                .catch(error => {
                    console.error('Registration error:', error);
                });
        } else {
            console.log('Passwords do not match');
            setPasswordsMatch(false);
        }
    };

    function ButtonLink({ to, children }) {
        return <Link to={to}><button>{children}</button></Link>;
    }



    return (
        <>
            <h1 className="pageTitle">USYD Mental Health Support</h1>
            <Form onSubmit={handleSubmit}>
                <Form.Group controlId="email">
                    <Form.Label>Email</Form.Label>
                    <Form.Control
                        type="email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        placeholder="YOUREMAIL@GMAIL.COM"
                    />
                </Form.Group>

                <Form.Group controlId="username">
                    <Form.Label>Username</Form.Label>
                    <Form.Control
                        type="text"
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                        placeholder="YourUsername"
                    />
                </Form.Group>

                <Form.Group controlId="password">
                    <Form.Label>Password</Form.Label>
                    <Form.Control
                        type="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        placeholder="**********"
                    />
                </Form.Group>

                <Form.Group controlId="conpass">
                    <Form.Label>Confirm Password</Form.Label>
                    <Form.Control
                        type="password"
                        value={conPass}
                        onChange={(e) => setConPass(e.target.value)}
                        placeholder="**********"
                    />
                </Form.Group>

                {!passwordsMatch && <p>Passwords do not match.</p>}

                <Button type="submit" variant="primary">
                    REGISTER
                </Button>
            </Form>

            <ButtonLink to="/login" className="btn btn-secondary">
                <div> Back to Login Page </div>
            </ButtonLink>
        </>
    );
}
