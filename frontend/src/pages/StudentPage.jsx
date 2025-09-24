import React, { useEffect, useState } from 'react';
import axios from 'axios';
import AddStudentModal from '../components/AddStudentModal';

export default function StudentPage({ back }) {
  const [students, setStudents] = useState([]);
  const [showAdd, setShowAdd] = useState(false);

  useEffect(() => {
    fetchList();
  }, []);

  const fetchList = () => {
    axios.get('/api/students').then(r => setStudents(r.data)).catch(()=>setStudents([]));
  };

  const onAdded = () => {
    setShowAdd(false);
    fetchList();
  };

  return (
    <div className="page-root">
      <button onClick={back}>← Back</button>
      <h2>Students</h2>
      <button onClick={() => setShowAdd(true)}>Add Student</button>
      <ul>
        {students.map(s => <li key={s.id}>{s.name} — {s.course} — {s.email} — {s.phone} — {s.status}</li>)}
      </ul>

      {showAdd && <AddStudentModal onClose={() => setShowAdd(false)} onAdded={onAdded} />}
    </div>
  );
}
