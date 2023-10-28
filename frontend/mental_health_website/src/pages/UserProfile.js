import React, {useState, useEffect} from 'react';
import './UserProfile.css';
import { useNavigate } from 'react-router-dom';

const UserProfile = () => {

    const [user, setUser] = useState([]);
    const [myposts, setMyPosts] = useState([]);
    const [myAppointments, setMyAppointments] = useState([]);
    const [username, setUsername] = useState("");
    const navigate = useNavigate();

    const handleLogout = () =>{
        localStorage.clear();
        navigate('/information');
    };

    const handleUpdateEmail = (newEmail) => {

        console.log(JSON.stringify(newEmail));

        fetch('http://localhost:8080/api/users/updateProfile', {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify({
                newEmail
            })
        })
        .then(response => response.json())
        .then(data => {
          if(data.success) {
            console.log(data.email)
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
                setUsername(data.username);
            })
            .catch(error => console.error('There was an error!', error));

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

    return (
        <div className="profile-container">
            <div className="header">
                <img src={user.avatar} alt="User Avatar" className="icon"/>
                <div className='profile-header-middle'>
                    <div className='name'>
                        
                        <div>
                            <h1 className="name">{username}</h1>
                        </div>
                    </div>
                    <div className="email">
                        <div className='email'>
                            {isEditingEmail ? (
                                <>
                                    <input
                                        type="text"
                                        name="newEmail"
                                        defaultValue={user.email}
                                    />
                                    <button type="submit" onClick={handleUpdateEmail}>Save</button>
                                </>
                                    
                            ) : (
                                <div>
                                    <h1 className="email">{user.email}</h1>
                                    <button className="default-button" onClick={() => setIsEditingEmail(true)}>edit</button>
                                </div>
                            )}
                        </div>
                    </div>
                </div>
                
            </div>

            {/* { <div className="lists-container">
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
