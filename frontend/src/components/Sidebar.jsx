import React from 'react';
import { NavLink } from 'react-router-dom';
import { LayoutDashboard, Briefcase, Settings, Users, Database } from 'lucide-react';

const Sidebar = () => {
  return (
    <aside className="sidebar">
      <div className="sidebar-brand">
        <Database size={28} className="text-accent-primary" />
        TenderPro
      </div>
      
      <nav className="sidebar-nav">
        <NavLink 
          to="/dashboard" 
          className={({ isActive }) => `nav-item ${isActive ? 'active' : ''}`}
        >
          <LayoutDashboard size={20} />
          Dashboard
        </NavLink>
        
        <NavLink 
          to="/tenders" 
          className={({ isActive }) => `nav-item ${isActive ? 'active' : ''}`}
        >
          <Briefcase size={20} />
          Tenders & Prospects
        </NavLink>
        
        {/* Decorative non-functional links for aesthetic purposes */}
        <div style={{ marginTop: '2rem', marginBottom: '0.5rem', paddingLeft: '1rem', fontSize: '0.75rem', color: 'var(--text-muted)', textTransform: 'uppercase', letterSpacing: '1px' }}>
          System
        </div>
        
        <a href="#" className="nav-item" onClick={(e) => e.preventDefault()}>
          <Users size={20} />
          Team Members
        </a>
        
        <a href="#" className="nav-item" onClick={(e) => e.preventDefault()}>
          <Settings size={20} />
          Settings
        </a>
      </nav>
      
      <div style={{ marginTop: 'auto', padding: '1.5rem' }}>
        <div className="glass-panel" style={{ padding: '1rem', borderRadius: '12px', fontSize: '0.875rem' }}>
          <div style={{ display: 'flex', alignItems: 'center', gap: '0.5rem', marginBottom: '0.5rem' }}>
            <div style={{ width: '8px', height: '8px', borderRadius: '50%', backgroundColor: 'var(--success)', boxShadow: '0 0 10px var(--success)' }}></div>
            <span style={{ color: 'var(--text-secondary)' }}>System Status</span>
          </div>
          <div style={{ fontWeight: '600' }}>All Services Online</div>
        </div>
      </div>
    </aside>
  );
};

export default Sidebar;
