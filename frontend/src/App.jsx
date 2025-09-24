import React, { useState } from 'react';
import { motion } from 'framer-motion';
import ManagerPage from './pages/ManagerPage';
import StudentPage from './pages/StudentPage';
import './styles/animations.css';

export default function App() {
  const [view, setView] = useState(null);
  if (view === 'manager') return <ManagerPage back={() => setView(null)} />;
  if (view === 'student') return <StudentPage back={() => setView(null)} />;

  return (
    <div className="app-root">
      <h1 className="title">CPM App</h1>
      <div className="card-row">
        <motion.button
          className="big-button"
          whileHover={{ scale: 1.05 }}
          whileTap={{ scale: 0.98 }}
          onClick={() => setView('manager')}
        >
          Manager
        </motion.button>

        <motion.button
          className="big-button"
          whileHover={{ scale: 1.05 }}
          whileTap={{ scale: 0.98 }}
          onClick={() => setView('student')}
        >
          Student
        </motion.button>
      </div>
    </div>
  );
}
