import React, { useState, useEffect } from 'react';
import { motion } from 'framer-motion';
import {
  PieChart, Pie, Cell, ResponsiveContainer, Tooltip as RechartsTooltip,
  BarChart, Bar, XAxis, YAxis, CartesianGrid,
  RadialBarChart, RadialBar, Legend
} from 'recharts';
import { Briefcase, Building2, CheckCircle, Clock, Target, FileText, TrendingUp, Shield } from 'lucide-react';
import api from '../api';

const COLORS = ['#6366f1', '#10b981', '#f59e0b', '#ef4444', '#8b5cf6', '#ec4899', '#14b8a6', '#f97316', '#06b6d4', '#84cc16'];

const STAGE_COLORS = {
  'Active Opportunity': '#10b981',
  'Meeting Done': '#6366f1',
  'Meeting scheduled': '#8b5cf6',
  'Follow-Up Pending': '#f59e0b',
  'Applied': '#06b6d4',
  'Monitor': '#14b8a6',
  'Not Reachable': '#f97316',
  'Not Interested': '#ef4444',
};

const STATUS_COLORS = {
  'Open / Active': '#10b981',
  'Upcoming': '#6366f1',
  'Applied': '#06b6d4',
  'Closed': '#ef4444',
  'To Be Tracked': '#f59e0b',
  'N/A': '#71717a',
};

const tooltipStyle = {
  contentStyle: { background: 'rgba(26, 29, 36, 0.95)', border: '1px solid rgba(255,255,255,0.1)', borderRadius: '10px', color: '#fff', fontSize: '0.85rem' },
  itemStyle: { color: '#fff' }
};

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
      transition: { staggerChildren: 0.08 }
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

  const activeCount = stats?.activeTendersCount || 0;
  const meetingCount = stats?.meetingDoneCount || 0;
  const tenderRefCount = stats?.tenderRefCount || 0;

  // Prepare opportunity stage data with colors
  const stageData = (stats?.opportunityStageBreakdown || []).map(item => ({
    ...item,
    fill: STAGE_COLORS[item.name] || '#71717a'
  }));

  // Prepare tender status data with colors
  const statusData = (stats?.tenderStatusBreakdown || []).map(item => ({
    ...item,
    fill: STATUS_COLORS[item.name] || '#71717a'
  }));

  // Contact completeness for radial chart
  const contactData = (stats?.contactCompleteness || []).map((item, i) => ({
    ...item,
    fill: COLORS[i]
  }));

  return (
    <motion.div 
      initial="hidden"
      animate="visible"
      variants={containerVariants}
    >
      <div className="page-header">
        <motion.h1 variants={itemVariants} className="page-title">Executive Dashboard</motion.h1>
        <motion.p variants={itemVariants} className="page-subtitle">
          MIB Tender Tracking — FY 2026-27 | {stats?.totalTenders || 0} Companies Tracked
        </motion.p>
      </div>

      {/* ===== KPI STAT CARDS (6 cards) ===== */}
      <div style={{ display: 'grid', gridTemplateColumns: 'repeat(auto-fit, minmax(170px, 1fr))', gap: '1.25rem' }}>
        <motion.div variants={itemVariants} className="glass-panel stat-card">
          <div className="stat-icon primary"><Briefcase size={22} /></div>
          <div className="stat-content">
            <div className="stat-title">Total Prospects</div>
            <div className="stat-value">{stats?.totalTenders || 0}</div>
          </div>
        </motion.div>

        <motion.div variants={itemVariants} className="glass-panel stat-card">
          <div className="stat-icon warning"><Building2 size={22} /></div>
          <div className="stat-content">
            <div className="stat-title">Industries</div>
            <div className="stat-value">{stats?.totalIndustries || 0}</div>
          </div>
        </motion.div>

        <motion.div variants={itemVariants} className="glass-panel stat-card">
          <div className="stat-icon success"><CheckCircle size={22} /></div>
          <div className="stat-content">
            <div className="stat-title">Broker Involved</div>
            <div className="stat-value">{stats?.brokerYesCount || 0}</div>
          </div>
        </motion.div>

        <motion.div variants={itemVariants} className="glass-panel stat-card">
          <div className="stat-icon" style={{ background: 'rgba(59, 130, 246, 0.15)', color: '#3b82f6' }}><Clock size={22} /></div>
          <div className="stat-content">
            <div className="stat-title">Meetings Done</div>
            <div className="stat-value">{meetingCount}</div>
          </div>
        </motion.div>

        <motion.div variants={itemVariants} className="glass-panel stat-card">
          <div className="stat-icon" style={{ background: 'rgba(20, 184, 166, 0.15)', color: '#14b8a6' }}><Target size={22} /></div>
          <div className="stat-content">
            <div className="stat-title">Active Tenders</div>
            <div className="stat-value">{activeCount}</div>
          </div>
        </motion.div>

        <motion.div variants={itemVariants} className="glass-panel stat-card">
          <div className="stat-icon" style={{ background: 'rgba(139, 92, 246, 0.15)', color: '#8b5cf6' }}><FileText size={22} /></div>
          <div className="stat-content">
            <div className="stat-title">Tender Refs</div>
            <div className="stat-value">{tenderRefCount}</div>
          </div>
        </motion.div>
      </div>

      {/* ===== ROW 1: Opportunity Stage + Tender Status ===== */}
      <div className="charts-grid" style={{ marginTop: '1.5rem' }}>
        <motion.div variants={itemVariants} className="glass-panel chart-card">
          <h3 className="chart-title">Opportunity Stage Pipeline</h3>
          <ResponsiveContainer width="100%" height={300}>
            <PieChart>
              <Pie data={stageData} cx="50%" cy="50%" innerRadius={55} outerRadius={105} paddingAngle={3} dataKey="count">
                {stageData.map((entry, i) => (
                  <Cell key={`stage-${i}`} fill={entry.fill} />
                ))}
              </Pie>
              <RechartsTooltip {...tooltipStyle} />
            </PieChart>
          </ResponsiveContainer>
          <div className="chart-legend">
            {stageData.map((entry, i) => (
              <div key={i} className="legend-item">
                <div className="legend-dot" style={{ backgroundColor: entry.fill }}></div>
                <span>{entry.name} ({entry.count})</span>
              </div>
            ))}
          </div>
        </motion.div>

        <motion.div variants={itemVariants} className="glass-panel chart-card">
          <h3 className="chart-title">Tender Status Distribution</h3>
          <ResponsiveContainer width="100%" height={300}>
            <PieChart>
              <Pie data={statusData} cx="50%" cy="50%" innerRadius={55} outerRadius={105} paddingAngle={3} dataKey="count">
                {statusData.map((entry, i) => (
                  <Cell key={`status-${i}`} fill={entry.fill} />
                ))}
              </Pie>
              <RechartsTooltip {...tooltipStyle} />
            </PieChart>
          </ResponsiveContainer>
          <div className="chart-legend">
            {statusData.map((entry, i) => (
              <div key={i} className="legend-item">
                <div className="legend-dot" style={{ backgroundColor: entry.fill }}></div>
                <span>{entry.name} ({entry.count})</span>
              </div>
            ))}
          </div>
        </motion.div>
      </div>

      {/* ===== ROW 2: Insurance Type + Industry Breakdown ===== */}
      <div className="charts-grid" style={{ marginTop: '1.5rem' }}>
        <motion.div variants={itemVariants} className="glass-panel chart-card">
          <h3 className="chart-title">Insurance Type Demand</h3>
          <ResponsiveContainer width="100%" height={320}>
            <BarChart data={(stats?.insuranceBreakdown || []).slice(0, 10)} layout="vertical" margin={{ top: 5, right: 30, left: 80, bottom: 5 }}>
              <CartesianGrid strokeDasharray="3 3" stroke="rgba(255,255,255,0.06)" />
              <XAxis type="number" stroke="#71717a" tick={{ fill: '#a1a1aa', fontSize: 12 }} />
              <YAxis type="category" dataKey="name" stroke="#71717a" tick={{ fill: '#a1a1aa', fontSize: 11 }} width={75} />
              <RechartsTooltip {...tooltipStyle} />
              <Bar dataKey="count" radius={[0, 6, 6, 0]}>
                {(stats?.insuranceBreakdown || []).slice(0, 10).map((entry, i) => (
                  <Cell key={i} fill={COLORS[i % COLORS.length]} />
                ))}
              </Bar>
            </BarChart>
          </ResponsiveContainer>
        </motion.div>

        <motion.div variants={itemVariants} className="glass-panel chart-card">
          <h3 className="chart-title">Top Industries (Top 8)</h3>
          <ResponsiveContainer width="100%" height={320}>
            <PieChart>
              <Pie
                data={stats?.industryBreakdown?.slice(0, 8) || []}
                cx="50%"
                cy="50%"
                innerRadius={60}
                outerRadius={110}
                paddingAngle={4}
                dataKey="count"
              >
                {(stats?.industryBreakdown?.slice(0, 8) || []).map((entry, index) => (
                  <Cell key={`cell-${index}`} fill={COLORS[index % COLORS.length]} />
                ))}
              </Pie>
              <RechartsTooltip {...tooltipStyle} />
            </PieChart>
          </ResponsiveContainer>
          <div className="chart-legend">
            {(stats?.industryBreakdown?.slice(0, 8) || []).map((entry, index) => (
              <div key={index} className="legend-item">
                <div className="legend-dot" style={{ backgroundColor: COLORS[index % COLORS.length] }}></div>
                <span>{entry.name} ({entry.count})</span>
              </div>
            ))}
          </div>
        </motion.div>
      </div>

      {/* ===== ROW 3: Geographic + Broker Involvement ===== */}
      <div className="charts-grid" style={{ marginTop: '1.5rem' }}>
        <motion.div variants={itemVariants} className="glass-panel chart-card">
          <h3 className="chart-title">Geographic Distribution (NCR)</h3>
          <ResponsiveContainer width="100%" height={300}>
            <BarChart data={stats?.geoBreakdown || []} margin={{ top: 10, right: 30, left: 10, bottom: 5 }}>
              <CartesianGrid strokeDasharray="3 3" stroke="rgba(255,255,255,0.06)" />
              <XAxis dataKey="name" stroke="#71717a" tick={{ fill: '#a1a1aa', fontSize: 11 }} angle={-15} />
              <YAxis stroke="#71717a" tick={{ fill: '#a1a1aa', fontSize: 12 }} />
              <RechartsTooltip {...tooltipStyle} />
              <Bar dataKey="count" radius={[6, 6, 0, 0]}>
                {(stats?.geoBreakdown || []).map((entry, i) => (
                  <Cell key={i} fill={COLORS[i % COLORS.length]} />
                ))}
              </Bar>
            </BarChart>
          </ResponsiveContainer>
        </motion.div>

        <motion.div variants={itemVariants} className="glass-panel chart-card">
          <h3 className="chart-title">Broker Involvement</h3>
          <ResponsiveContainer width="100%" height={300}>
            <PieChart>
              <Pie data={stats?.brokerBreakdown || []} cx="50%" cy="50%" innerRadius={55} outerRadius={105} paddingAngle={5} dataKey="count">
                {(stats?.brokerBreakdown || []).map((entry, i) => (
                  <Cell key={i} fill={['#10b981', '#ef4444', '#f59e0b'][i] || COLORS[i]} />
                ))}
              </Pie>
              <RechartsTooltip {...tooltipStyle} />
            </PieChart>
          </ResponsiveContainer>
          <div className="chart-legend">
            {(stats?.brokerBreakdown || []).map((entry, i) => (
              <div key={i} className="legend-item">
                <div className="legend-dot" style={{ backgroundColor: ['#10b981', '#ef4444', '#f59e0b'][i] || COLORS[i] }}></div>
                <span>{entry.name} ({entry.count})</span>
              </div>
            ))}
          </div>
        </motion.div>
      </div>

      {/* ===== ROW 4: Existing Brokers + Designation Breakdown ===== */}
      <div className="charts-grid" style={{ marginTop: '1.5rem' }}>
        <motion.div variants={itemVariants} className="glass-panel chart-card">
          <h3 className="chart-title">Existing Broker Landscape</h3>
          <ResponsiveContainer width="100%" height={320}>
            <BarChart data={(stats?.existingBrokerBreakdown || []).filter(b => b.name !== 'Not Known' && b.name !== 'No broker involvement').slice(0, 10)} layout="vertical" margin={{ top: 5, right: 30, left: 100, bottom: 5 }}>
              <CartesianGrid strokeDasharray="3 3" stroke="rgba(255,255,255,0.06)" />
              <XAxis type="number" stroke="#71717a" tick={{ fill: '#a1a1aa', fontSize: 12 }} />
              <YAxis type="category" dataKey="name" stroke="#71717a" tick={{ fill: '#a1a1aa', fontSize: 11 }} width={95} />
              <RechartsTooltip {...tooltipStyle} />
              <Bar dataKey="count" radius={[0, 6, 6, 0]}>
                {(stats?.existingBrokerBreakdown || []).filter(b => b.name !== 'Not Known' && b.name !== 'No broker involvement').slice(0, 10).map((_, i) => (
                  <Cell key={i} fill={COLORS[i % COLORS.length]} />
                ))}
              </Bar>
            </BarChart>
          </ResponsiveContainer>
        </motion.div>

        <motion.div variants={itemVariants} className="glass-panel chart-card">
          <h3 className="chart-title">Contact Designation Levels</h3>
          <ResponsiveContainer width="100%" height={320}>
            <BarChart data={stats?.designationBreakdown || []} layout="vertical" margin={{ top: 5, right: 30, left: 110, bottom: 5 }}>
              <CartesianGrid strokeDasharray="3 3" stroke="rgba(255,255,255,0.06)" />
              <XAxis type="number" stroke="#71717a" tick={{ fill: '#a1a1aa', fontSize: 12 }} />
              <YAxis type="category" dataKey="name" stroke="#71717a" tick={{ fill: '#a1a1aa', fontSize: 11 }} width={105} />
              <RechartsTooltip {...tooltipStyle} />
              <Bar dataKey="count" radius={[0, 6, 6, 0]}>
                {(stats?.designationBreakdown || []).map((_, i) => (
                  <Cell key={i} fill={COLORS[i % COLORS.length]} />
                ))}
              </Bar>
            </BarChart>
          </ResponsiveContainer>
        </motion.div>
      </div>

      {/* ===== ROW 5: Contact Completeness + Broker-Industry Cross ===== */}
      <div className="charts-grid" style={{ marginTop: '1.5rem', marginBottom: '2rem' }}>
        <motion.div variants={itemVariants} className="glass-panel chart-card">
          <h3 className="chart-title">Contact Data Completeness</h3>
          <ResponsiveContainer width="100%" height={300}>
            <PieChart>
              <Pie data={contactData} cx="50%" cy="50%" innerRadius={55} outerRadius={105} paddingAngle={4} dataKey="count">
                {contactData.map((entry, i) => (
                  <Cell key={i} fill={entry.fill} />
                ))}
              </Pie>
              <RechartsTooltip {...tooltipStyle} />
            </PieChart>
          </ResponsiveContainer>
          <div className="chart-legend">
            {contactData.map((entry, i) => (
              <div key={i} className="legend-item">
                <div className="legend-dot" style={{ backgroundColor: entry.fill }}></div>
                <span>{entry.name} ({entry.count})</span>
              </div>
            ))}
          </div>
        </motion.div>

        <motion.div variants={itemVariants} className="glass-panel chart-card">
          <h3 className="chart-title">Top Broker-Involved Industries</h3>
          <ResponsiveContainer width="100%" height={300}>
            <BarChart data={(stats?.brokerIndustryBreakdown || []).slice(0, 8)} margin={{ top: 10, right: 30, left: 10, bottom: 60 }}>
              <CartesianGrid strokeDasharray="3 3" stroke="rgba(255,255,255,0.06)" />
              <XAxis dataKey="name" stroke="#71717a" tick={{ fill: '#a1a1aa', fontSize: 10 }} angle={-35} textAnchor="end" interval={0} />
              <YAxis stroke="#71717a" tick={{ fill: '#a1a1aa', fontSize: 12 }} />
              <RechartsTooltip {...tooltipStyle} />
              <Bar dataKey="count" radius={[6, 6, 0, 0]}>
                {(stats?.brokerIndustryBreakdown || []).slice(0, 8).map((_, i) => (
                  <Cell key={i} fill={COLORS[i % COLORS.length]} />
                ))}
              </Bar>
            </BarChart>
          </ResponsiveContainer>
        </motion.div>
      </div>
    </motion.div>
  );
};

export default Dashboard;
