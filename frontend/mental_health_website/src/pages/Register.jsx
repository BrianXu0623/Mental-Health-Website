import React, {useState} from 'react'
import ReactDOM from 'react-dom'
export const Register = (props) => {
    const {email, setEmail} = useState('');
    const {pass, setPass} = useState('');
    const {conpass, setConpass} = useState('');
    const [passwordsMatch, setPasswordsMatch] = useState(true);
    const handleSubmit = (e) =>{
        e.preventDefault();
        if (pass === conpass) {
            // Passwords match, you can proceed with registration
            console.log('Registration successful');
        } else {
            // Passwords do not match, display an error message
            console.log('Passwords do not match');
            setPasswordsMatch(false);
        }
    }
    return(
        <>
            <h1 className="pageTitle">USYD Mental Health Support</h1>
            <form onSubmit={handleSubmit}>
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

                <label htmlFor="conpass">Confirm Password</label>
                <input
                    value={conpass}
                    onChange={(e) => setConpass(e.target.value)}
                    type="password"
                    placeholder="**********"
                    id="conpass"
                    name="conpass"
                />

                {!passwordsMatch && <p>Passwords do not match.</p>} {/* Display error message */}

                <button type="submit">Register</button>
            </form>
            <button onClick={() => props.onFormSwitch('login')}>Back to Login Page</button>
        </>
    )
}