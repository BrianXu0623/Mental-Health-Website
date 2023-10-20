import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import DatePicker from 'react-datepicker';
import Select from 'react-select';
import 'react-datepicker/dist/react-datepicker.css';
import './NewAppointment.css';

export default function NewAppointment() {
    const [date, setDate] = useState(null);
    const [time, setTime] = useState(null);
    const [concern, setConcern] = useState('');
    const [doctorInfo, setDoctorInfo] = useState({});

    const { doctorName } = useParams();

    const availableTimes = [
        { value: 'morning', label: 'Morning' },
        { value: 'afternoon', label: 'Afternoon' },
        { value: 'evening', label: 'Evening' },
    ];

    const handleDateChange = (selectedDate) => {
        setDate(selectedDate);
    };

    const handleTimeChange = (selectedTime) => {
        setTime(selectedTime);
    };

    const handleSubmit = (e) => {
        e.preventDefault();
    };

    useEffect(() => {

        fetch(`/api/getDoctorInfo?name=${doctorName}`)
            .then((response) => response.json())
            .then((data) => {
                setDoctorInfo(data);
            })
            .catch((error) => {
                console.error('Error fetching doctor info:', error);
            });
    }, [doctorName]);

    return (
        <div className="new-appointment-container">
            <h1 className="new-appointment-title">New Appointment</h1>
            <div className="doctor-info">
                <div className="doctor-avatar">
                    <img src={doctorInfo.avatar} alt={`${doctorName} avatar`} />
                </div>
                <div className="doctor-rating">
                    <p>Doctor: {doctorName}</p>
                    <p>Rating: {doctorInfo.rating}</p>
                </div>
            </div>
            <form className="new-appointment-form" onSubmit={handleSubmit}>
                <div className="form-group">
                    <label>Select Date:</label>
                    <DatePicker selected={date} onChange={handleDateChange} />
                </div>
                <div className="form-group">
                    <label>Select Time:</label>
                    <Select options={availableTimes} value={time} onChange={handleTimeChange} />
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
