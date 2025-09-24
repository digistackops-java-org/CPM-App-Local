import React, { useEffect, useState } from 'react';
import axios from 'axios';
import AddManagerModal from '../components/AddManagerModal';

export default function ManagerPage({ back }) {
  const [managers, setManagers] = useState([]);
  const [showAdd, setShowAdd] = useState(false);

  useEffect(() => {
    fetchList();
  }, []);

  const fetchList = () => {
    axios.get('/api/managers').then(r => setManagers(r.data)).catch(()=>setManagers([]));
  };

  const onAdded = () => {
    setShowAdd(false);
    fetchList();
  };

  return (
    <div className="page-root">
      <button onClick={back}>← Back</button>
      <h2>Managers</h2>
      <button onClick={() => setShowAdd(true)}>Add Manager</button>
      <ul>
        {managers.map(m => <li key={m.id}>{m.name} — {m.course} — {m.email}</li>)}
      </ul>

      {showAdd && <AddManagerModal onClose={() => setShowAdd(false)} onAdded={onAdded} />}
    </div>
  );
}
