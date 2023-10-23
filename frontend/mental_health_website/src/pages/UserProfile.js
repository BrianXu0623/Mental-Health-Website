import React, {useState, useEffect} from 'react';
import './UserProfile.css';
import { useNavigate } from 'react-router-dom';

const UserProfile = () => {

    const [user, setUser] = useState([]);
    const [myposts, setMyPosts] = useState([]);
    const [myAppointments, setMyAppointments] = useState([]);
    const [nameForm, setNameForm] = useState('');
    const [username, setUsername] = useState("");
    const navigate = useNavigate();

    const handleLogout = () =>{
        localStorage.clear();
        navigate('/information');
    };

    const updateUsername = (newUsername) => {
        fetch('http://localhost:8080/api/users/updateProfile', {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify({ 'newUserName': newUsername})
        })
        .then(response => response.json())
        .then(data => {
          if(data.success) {
            setUsername(newUsername);
          } else {
            console.error('Failed to update username');
          }
        })
        .catch((error) => console.error('Error:', error));
      }

    useEffect(() => {

    fetch('http://localhost:8080/api/users/profile', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'token': localStorage.getItem('token')
        }
        })
        .then(response => response.json())
        .then((data) => {setUser(data);
                        localStorage.setItem('username', data.username)})
        .catch(error => console.error('There was an error!', error));

    setUsername (user.username);

    fetch('http://localhost:8080/api/appointments/get/byUser', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'token': localStorage.getItem('token')
        }
        })
        .then(response => response.json())
        .then((data) => {setMyAppointments(data);})
        .catch(error => console.error('There was an error!', error));

    setUsername (user.username);

    fetch('http://localhost:8080/api/threads/get/byUserToken', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'token': localStorage.getItem('token')
        }
        })
        .then(response => response.json())
        .then((data) => {setMyPosts(data);})
        .catch(error => console.error('There was an error!', error));

    setUsername (user.username);

    }
    
    , []);

    const [isEditingName, setIsEditingName] = useState(false);
    const [isEditingEmail, setIsEditingEmail] = useState(false);
    const [isEditingIcon, setIsEditingIcon] = useState(false);

    const handleNameSubmit = async (e) => {
        e.preventDefault();
        updateUsername(username);
        setIsEditingName(false); 
    };

    return (
        <div className="profile-container">
            <div className="header">
                <img src={user.avatar} alt="User Avatar" className="icon"/>
                <div className='profile-header-middle'>
                    <div className='name'>
                        {isEditingName ? (
                            <>
                                <input
                                    type="text"
                                    name="name"
                                    defaultValue={user.username}
                                />
                                <button type="submit" onClick={handleNameSubmit}>Save</button>
                            </>
                                
                            
                            
                            
                        ) : (
                            <div>
                                <h1 className="name">{username}</h1>
                                <button onClick={() => setIsEditingName(true)}>edit</button>
                            </div>
                        )}
                    </div>
                    <p className="email">{user.email}</p>
                </div>
                
            </div>

            {/* <div className="lists-container">
                <ul className="post-list">
                    <li>My Posts:</li>
                    {myposts.map((item, index) => (
                        <li key={index}>
                            {item.name}
                        </li>))}
                </ul>
                <ul className="appointment-list">
                    <li>My Appointments:</li>
                    {myAppointments.map((item, index) => (
                        <li key={index}>
                            {item.name}
                        </li>))}
                </ul>
            </div> */}

            <button className='logout-button' onClick={handleLogout}> log out </button>
        </div>
    );
}

export default UserProfile;
