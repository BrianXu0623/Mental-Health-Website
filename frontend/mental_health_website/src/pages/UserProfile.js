import React, {useState, useEffect} from 'react';
import './UserProfile.css';

const UserProfile = () => {

    const [user, setUser] = useState([]);

    useEffect(() => {

    fetch('http://localhost:8080/api/users/profile', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'token': localStorage.getItem('token')
        }
        })
        .then(response => response.json())
        .then((data) => {
        setUser(data);
        })
        .catch(error => console.error('There was an error!', error));
    });

    const userInfo = {
        name: user.username,
        email: user.email,
        icon: user.avatar
    };

    const [isEditingName, setIsEditingName] = useState(false);
    const [nameForm, setNameForm] = useState({
        name: 'Example User',
    });


    const handleNameInputChange = (e) => {
        const { name, value } = e.target;
        setNameForm((prev) => ({ ...prev, [name]: value }));
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
                <img src={userInfo.icon} alt="User Avatar" className="icon"/>
                <div className='profile-header-middle'>
                    <div className='name'>
                        {isEditingName ? (
                            <div>
                                <input
                                    type="text"
                                    name="name"
                                    value={nameForm.name}
                                    onChange={handleNameInputChange}
                                />
                                <button type="submit" onClick={handleNameSubmit}>Save</button>
                            </div>
                            
                            
                        ) : (
                            <div>
                                <h1 className="name">{nameForm.name}</h1>
                                <button onClick={() => setIsEditingName(true)}>edit</button>
                            </div>
                        )}
                    </div>
                    <p className="email">{userInfo.email}</p>
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
