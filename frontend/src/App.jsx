import React from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import Sidebar from './components/Sidebar';
import Dashboard from './components/Dashboard';
import Tenders from './components/Tenders';

function App() {
  return (
    <Router>
      <div className="app-container">
        <Sidebar />
        <main className="main-content">
          {/* Background animated elements for premium dark aesthetic */}
          <div className="bg-shape bg-shape-1"></div>
          <div className="bg-shape bg-shape-2"></div>
          
          <Routes>
            <Route path="/" element={<Navigate to="/dashboard" replace />} />
            <Route path="/dashboard" element={<Dashboard />} />
            <Route path="/tenders" element={<Tenders />} />
          </Routes>
        </main>
      </div>
    </Router>
  );
}

export default App;
