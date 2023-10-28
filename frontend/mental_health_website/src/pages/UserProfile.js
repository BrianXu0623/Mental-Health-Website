import React, {useState, useEffect} from 'react';
import './UserProfile.css';
import { useNavigate } from 'react-router-dom';

const UserProfile = () => {

    const [user, setUser] = useState([]);
    const [myposts, setMyPosts] = useState([]);
    const [myAppointments, setMyAppointments] = useState([]);
    const [username, setUsername] = useState("");
    const [newEmail, setNewEmail] = useState("");
    const [isEditingEmail, setIsEditingEmail] = useState(false);
    const [avatarString, setAvatarString] = useState('');
    const navigate = useNavigate();

    const handleLogout = () =>{
        localStorage.clear();
        navigate('/information');
    };

    const handleImageUpload = (event) => {

        const file = event.target.files[0];
        if (file) {
            const reader = new FileReader();
            reader.onloadend = () => {
                const base64String = reader.result.replace('data:', '').replace(/^.+,/, '');
                setAvatarString(base64String);
                sendImageToBackend(base64String);
            };
            reader.readAsDataURL(file);
        }
    };

    const sendImageToBackend = async (base64String) => {
        try {
            const response = await fetch('http://localhost:8080/api/users/updateProfile', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'token': localStorage.getItem('token'),
                },
                body: JSON.stringify({ avatar: base64String }),
            });

            const data = await response.json();
        } catch (error) {
            console.error("There was an error uploading the image:", error);
        }
    };

    const handleUpdateEmail = () => {

        console.log(JSON.stringify(newEmail));
        setIsEditingEmail(false)

        fetch('http://localhost:8080/api/users/updateProfile', {
          method: 'POST',
          headers: { 'Content-Type': 'application/json',
                    'token': localStorage.getItem('token')
                        },
          body: JSON.stringify({
                newEmail
            })
        })
        .then(response => response.json())
        .then(data => {
          if(data.success) {
            console.log(data.email)

          } else {
            console.error('Failed to update email');
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
                setNewEmail(data.email);
                setAvatarString(data.avatar);
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



    return (
        <div className="profile-container">
            <div className="header">
                <div className='profile-header-left'>
                    <img className="icon" src={`data:image/jpeg;base64,${avatarString}`} alt="User Avatar" />
                    <input type="file" onChange={handleImageUpload} />
                </div>


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
                                        value={newEmail}
                                        onChange={e => setNewEmail(e.target.value)}
                                    />
                                    <button type="submit" onClick={handleUpdateEmail}>Save</button>
                                </>

                            ) : (
                                <div>
                                    <h1 className="email">{newEmail}</h1>
                                    <button className="default-button" onClick={() => setIsEditingEmail(true)}>update email</button>
                                </div>
                            )}
                        </div>
                    </div>
                </div>

            </div>

            <div className="lists-container">
                <div className='post-container'>
                    <h3>My Posts:</h3>
                    <div className="thread-container">
                    <ul className="post-list">
                        {myposts.map((item, index) => (
                            <li className="thread-item" key={index}>
                                <h2>{item.thread.title}</h2>
                            </li>
                            )
                        )
                    }
                    </ul>
                    </div>
                </div>

                <div className='post-container'>
                    <h3>My Appointment:</h3>
                    <ul className="appointment-list-">

                    {myAppointments.map((item, index) => (
                        <li key={index}>
                            <div className="appointment-small-container">
                                <div className='item-name'>
                                    <h2>{item.professionalName}</h2>
                                </div>
                                <div className='item-clinic'>
                                    {item.clinic}
                                </div>
                                <div className='item-date'>
                                    {item.appointment.date}
                                    {"    "}
                                    {item.appointment.time}
                                </div>

                            </div>
                        </li>))}
                    </ul>
                </div>

            </div>

            <button className='logout-button' onClick={handleLogout}> log out </button>
        </div>
    );
}

export default UserProfile;
