import React, { useState } from 'react';
import { Form, Button } from 'react-bootstrap';
import { Link } from 'react-router-dom';
export default function Register() {
    const [email, setEmail] = useState('');
    const [pass, setPass] = useState('');
    const [conPass, setConPass] = useState('');
    const [passwordsMatch, setPasswordsMatch] = useState(true);

    const handleSubmit = (e) => {
        e.preventDefault();
        if (pass === conPass) {
            // Passwords match, you can proceed with registration
            console.log('Registration successful');
        } else {
            // Passwords do not match, display an error message
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

                <Form.Group controlId="password">
                    <Form.Label>Password</Form.Label>
                    <Form.Control
                        type="password"
                        value={pass}
                        onChange={(e) => setPass(e.target.value)}
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

                {!passwordsMatch && <p>Passwords do not match.</p>} {/* Display error message */}

                <Button type="submit" variant="primary">
                    REGISTER
                </Button>
            </Form>

            <ButtonLink to="/login" className="btn btn-secondary">
                <div> Back to Login Page </div>
            </ButtonLink>
        </>
    );
};
