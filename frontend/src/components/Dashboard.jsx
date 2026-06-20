import React, { useState, useEffect } from 'react';
import { motion } from 'framer-motion';
import { AreaChart, Area, PieChart, Pie, Cell, ResponsiveContainer, Tooltip as RechartsTooltip, XAxis, YAxis } from 'recharts';
import { Briefcase, Building2, CheckCircle, Clock } from 'lucide-react';
import api from '../api';

const Dashboard = () => {
  const [stats, setStats] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchStats = async () => {
      try {
        const response = await api.get('/tenders/stats');
        setStats(response.data);
      } catch (error) {
        console.error('Error fetching stats:', error);
      } finally {
        setLoading(false);
      }
    };
    fetchStats();
  }, []);

  const containerVariants = {
    hidden: { opacity: 0 },
    visible: {
      opacity: 1,
      transition: { staggerChildren: 0.1 }
    }
  };

  const itemVariants = {
    hidden: { y: 20, opacity: 0 },
    visible: {
      y: 0,
      opacity: 1,
      transition: { type: 'spring', stiffness: 100 }
    }
  };

  const COLORS = ['#6366f1', '#10b981', '#f59e0b', '#ef4444', '#8b5cf6', '#ec4899'];

  if (loading) {
    return (
      <div className="flex-center" style={{ height: '100%' }}>
        <motion.div 
          animate={{ rotate: 360 }}
          transition={{ repeat: Infinity, duration: 1, ease: "linear" }}
          style={{ width: '40px', height: '40px', border: '3px solid rgba(99, 102, 241, 0.3)', borderTopColor: '#6366f1', borderRadius: '50%' }}
        />
      </div>
    );
  }

  return (
    <motion.div 
      initial="hidden"
      animate="visible"
      variants={containerVariants}
    >
      <div className="page-header">
        <motion.h1 variants={itemVariants} className="page-title">Executive Dashboard</motion.h1>
        <motion.p variants={itemVariants} className="page-subtitle">Overview of tender operations and prospects</motion.p>
      </div>

      <div style={{ display: 'grid', gridTemplateColumns: 'repeat(auto-fit, minmax(240px, 1fr))', gap: '1.5rem' }}>
        <motion.div variants={itemVariants} className="glass-panel stat-card">
          <div className="stat-icon primary">
            <Briefcase size={24} />
          </div>
          <div className="stat-content">
            <div className="stat-title">Total Tenders</div>
            <div className="stat-value">{stats?.totalTenders || 0}</div>
          </div>
        </motion.div>

        <motion.div variants={itemVariants} className="glass-panel stat-card">
          <div className="stat-icon warning">
            <Building2 size={24} />
          </div>
          <div className="stat-content">
            <div className="stat-title">Industries</div>
            <div className="stat-value">{stats?.totalIndustries || 0}</div>
          </div>
        </motion.div>

        <motion.div variants={itemVariants} className="glass-panel stat-card">
          <div className="stat-icon success">
            <CheckCircle size={24} />
          </div>
          <div className="stat-content">
            <div className="stat-title">Broker Involved</div>
            <div className="stat-value">{stats?.brokerYesCount || 0}</div>
          </div>
        </motion.div>

        <motion.div variants={itemVariants} className="glass-panel stat-card">
          <div className="stat-icon" style={{ background: 'rgba(59, 130, 246, 0.15)', color: '#3b82f6' }}>
            <Clock size={24} />
          </div>
          <div className="stat-content">
            <div className="stat-title">Meetings Done</div>
            <div className="stat-value">
              {stats?.meetingBreakdown?.find(m => m.name === 'Meeting Done')?.count || 0}
            </div>
          </div>
        </motion.div>
      </div>

      <div className="charts-grid">
        <motion.div variants={itemVariants} className="glass-panel chart-card">
          <h3 style={{ marginBottom: '1.5rem', fontWeight: 600 }}>Tenders by Industry (Top 6)</h3>
          <ResponsiveContainer width="100%" height={280}>
            <PieChart>
              <Pie
                data={stats?.industryBreakdown?.slice(0, 6) || []}
                cx="50%"
                cy="50%"
                innerRadius={60}
                outerRadius={100}
                paddingAngle={5}
                dataKey="count"
              >
                {(stats?.industryBreakdown?.slice(0, 6) || []).map((entry, index) => (
                  <Cell key={`cell-${index}`} fill={COLORS[index % COLORS.length]} />
                ))}
              </Pie>
              <RechartsTooltip 
                contentStyle={{ background: 'rgba(26, 29, 36, 0.9)', border: '1px solid rgba(255,255,255,0.1)', borderRadius: '8px', color: '#fff' }}
                itemStyle={{ color: '#fff' }}
              />
            </PieChart>
          </ResponsiveContainer>
          <div style={{ display: 'flex', flexWrap: 'wrap', gap: '1rem', justifyContent: 'center', marginTop: '1rem' }}>
            {(stats?.industryBreakdown?.slice(0, 6) || []).map((entry, index) => (
              <div key={index} style={{ display: 'flex', alignItems: 'center', gap: '0.4rem', fontSize: '0.8rem' }}>
                <div style={{ width: 12, height: 12, borderRadius: 3, backgroundColor: COLORS[index % COLORS.length] }}></div>
                <span>{entry.name}</span>
              </div>
            ))}
          </div>
        </motion.div>

        <motion.div variants={itemVariants} className="glass-panel chart-card">
          <h3 style={{ marginBottom: '1.5rem', fontWeight: 600 }}>Broker Involvement</h3>
          <ResponsiveContainer width="100%" height={280}>
            <AreaChart data={stats?.brokerBreakdown || []} margin={{ top: 10, right: 30, left: 0, bottom: 0 }}>
              <defs>
                <linearGradient id="colorCount" x1="0" y1="0" x2="0" y2="1">
                  <stop offset="5%" stopColor="#6366f1" stopOpacity={0.8}/>
                  <stop offset="95%" stopColor="#6366f1" stopOpacity={0}/>
                </linearGradient>
              </defs>
              <XAxis dataKey="name" stroke="#a1a1aa" tick={{fill: '#a1a1aa'}} />
              <YAxis stroke="#a1a1aa" tick={{fill: '#a1a1aa'}} />
              <RechartsTooltip 
                contentStyle={{ background: 'rgba(26, 29, 36, 0.9)', border: '1px solid rgba(255,255,255,0.1)', borderRadius: '8px', color: '#fff' }}
              />
              <Area type="monotone" dataKey="count" stroke="#6366f1" fillOpacity={1} fill="url(#colorCount)" />
            </AreaChart>
          </ResponsiveContainer>
        </motion.div>
      </div>
    </motion.div>
  );
};

export default Dashboard;
