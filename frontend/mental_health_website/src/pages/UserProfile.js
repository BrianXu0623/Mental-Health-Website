import React, {useState, useEffect} from 'react';
import './UserProfile.css';

const UserProfile = () => {

    const [user, setUser] = useState([]);
    const [nameForm, setNameForm] = useState('');

    const updateUsername = (newUsername) => {
        fetch('/api/users/updateProfile', {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify({ newUserName: newUsername,
                                 newEmail: })
        })
        .then(response => response.json())
        .then(data => {
          if(data.success) {
            // Update username in the state if update is successful
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
        .then((data) => {setUser(data);})
        .catch(error => console.error('There was an error!', error));

    setNameForm(user.username);
    });

    const [isEditingName, setIsEditingName] = useState(false);
    const [isEditingEmail, setIsEditingEmail] = useState(false);
    const [isEditingIcon, setIsEditingIcon] = useState(false);

    


    const handleNameInputChange = (e) => {
        const { name, value } = e.target;
        setNameForm((prev) => ({ ...prev, [name]: value }));
        localStorage.setItem('username', e.target);
    };

    const handleNameSubmit = async (e) => {
        e.preventDefault();

        setIsEditingName(false); 
    };

    const list1 = ['Item 1', 'Item 2', 'Item 3'];
    const list2 = ['Item 1', 'Item 2', 'Item 3'];
    const list3 = ['Item 1', 'Item 2', 'Item 3'];

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
                                    value={nameForm}
                                    onChange={handleNameInputChange}
                                />
                                <button type="submit" onClick={handleNameSubmit}>Save</button>
                            </>
                                
                            
                            
                            
                        ) : (
                            <div>
                                <h1 className="name">{nameForm}</h1>
                                <button onClick={() => setIsEditingName(true)}>edit</button>
                            </div>
                        )}
                    </div>
                    <p className="email">{user.email}</p>
                </div>
                
            </div>

            <div className="lists-container">
                <ul className="list">
                    {list1.map((item, index) => <li key={index}>{item}</li>)}
                </ul>
                <ul className="list">
                    {list2.map((item, index) => <li key={index}>{item}</li>)}
                </ul>
                <ul className="list">
                    {list3.map((item, index) => <li key={index}>{item}</li>)}
                </ul>
            </div>
        </div>
    );
}

export default UserProfile;
