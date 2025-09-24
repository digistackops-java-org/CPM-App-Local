import React, { useState } from 'react';
import axios from 'axios';

export default function AddManagerModal({ onClose, onAdded }) {
  const [name, setName] = useState('');
  const [course, setCourse] = useState('');
  const [email, setEmail] = useState('');

  const submit = async (e) => {
    e.preventDefault();
    try {
      await axios.post('/api/managers', { name, course, email });
      onAdded();
    } catch (err) {
      alert('Failed to add manager');
    }
  };

  return (
    <div className="modal">
      <form onSubmit={submit} className="modal-form">
        <h3>Add Manager</h3>
        <input required placeholder="Name" value={name} onChange={e=>setName(e.target.value)} />
        <input placeholder="Course" value={course} onChange={e=>setCourse(e.target.value)} />
        <input type="email" placeholder="Email" value={email} onChange={e=>setEmail(e.target.value)} />
        <div className="modal-actions">
          <button type="submit">Add</button>
          <button type="button" onClick={onClose}>Cancel</button>
        </div>
      </form>
    </div>
  );
}
