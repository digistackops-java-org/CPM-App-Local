import React, { useState } from 'react';
import axios from 'axios';

export default function AddStudentModal({ onClose, onAdded }) {
  const [name, setName] = useState('');
  const [course, setCourse] = useState('');
  const [email, setEmail] = useState('');
  const [phone, setPhone] = useState('');
  const [fee, setFee] = useState('');
  const [status, setStatus] = useState('UNPAID');

  const submit = async (e) => {
    e.preventDefault();
    try {
      await axios.post('/api/students', { name, course, email, phone, fee, status });
      onAdded();
    } catch (err) {
      alert('Failed to add student');
    }
  };

  return (
    <div className="modal">
      <form onSubmit={submit} className="modal-form">
        <h3>Add Student</h3>
        <input required placeholder="Name" value={name} onChange={e=>setName(e.target.value)} />
        <input placeholder="Course" value={course} onChange={e=>setCourse(e.target.value)} />
        <input type="email" placeholder="Email" value={email} onChange={e=>setEmail(e.target.value)} />
        <input placeholder="Phone" value={phone} onChange={e=>setPhone(e.target.value)} />
        <input placeholder="Fee" value={fee} onChange={e=>setFee(e.target.value)} />
        <select value={status} onChange={e => setStatus(e.target.value)}>
          <option value="PAID">PAID</option>
          <option value="UNPAID">UNPAID</option>
        </select>
        <div className="modal-actions">
          <button type="submit">Add</button>
          <button type="button" onClick={onClose}>Cancel</button>
        </div>
      </form>
    </div>
  );
}
