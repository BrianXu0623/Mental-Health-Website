import React, { useState } from 'react';
import DatePicker from 'react-datepicker';
import Select from 'react-select';
import 'react-datepicker/dist/react-datepicker.css';
import './NewAppointment.css';
import { useParams } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';

export default function NewAppointment() {
    const [date, setDate] = useState(null);
    const [time, setTime] = useState(null);
    const [concern, setConcern] = useState('');
    const { doctorId } = useParams();
    const navigate = useNavigate();
    const availableTimes = [
        { value: '12:00:00', label: '12:00 PM' },
        { value: '13:00:00', label: '01:00 PM' },
        { value: '14:00:00', label: '02:00 PM' },
        { value: '15:00:00', label: '03:00 PM' },
        { value: '16:00:00', label: '04:00 PM' },
        { value: '17:00:00', label: '05:00 PM' },
    ];

    const handleDateChange = (selectedDate) => {
        setDate(selectedDate);
    };

    const handleTimeChange = (selectedTime) => {
        setTime(selectedTime.value);
    };

    const handleSubmit = (e) => {
        e.preventDefault();

        const userToken = localStorage.getItem('token');

        if (!userToken) {
            console.error('User token is missing. Please log in.');
            return;
        }
        console.log(userToken);


        console.log('Doctor ID:', doctorId);

        const appointmentData = {
            appointmentTopic: concern,
            date: date,
            time: time,
            professionalUserId: doctorId,
            userId: userToken,
        };




        console.log('Appointment created:', appointmentData);
        navigate('/appointment');
    };

    return (
        <div className="new-appointment-container">
            <h1 className="new-appointment-title">New Appointment</h1>
            <form className="new-appointment-form" onSubmit={handleSubmit}>
                <div className="form-group">
                    <label>Select Date:</label>
                    <DatePicker selected={date} onChange={handleDateChange} />
                </div>
                <div className="form-group">
                    <label>Select Time:</label>
                    <Select options={availableTimes} value={availableTimes.find((t) => t.value === time)} onChange={handleTimeChange} />
                </div>
                <div className="form-group">
                    <label>Describe Your Concern:</label>
                    <textarea
                        value={concern}
                        onChange={(e) => setConcern(e.target.value)}
                        rows={4}
                        placeholder="Please describe your concern here..."
                    />
                </div>
                <div className="button-container">
                    <button type="submit" className="btn btn-primary">
                        Submit
                    </button>
                </div>
            </form>
        </div>
    );
}
