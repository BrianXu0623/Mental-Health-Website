import React, { useState } from 'react';
import ReactDOM from 'react-dom';

export const Login = (props) => {
    const [email, setEmail] = useState('');
    const [pass, setPass] = useState('');

    const handleSubmit = (e) => {
        e.preventDefault();
        console.log(email);
    };

    return (
        <>
            <h1 className="pageTitle">USYD Mental Health Support</h1>
            <form>
                <label htmlFor="email">Email</label>
                <input
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                    type="email"
                    placeholder="youremail@gmail.com"
                    id="email"
                    name="email"
                />
                <label htmlFor="password">Password</label>
                <input
                    value={pass}
                    onChange={(e) => setPass(e.target.value)}
                    type="password"
                    placeholder="**********"
                    id="password"
                    name="password"
                />
                <button type="submit">Log In</button>
            </form>
            <button>Forgot Password</button>
            <button onClick={() => props.onFormSwitch('register')}>Don't have an account? Register</button>
        </>
    );
};
