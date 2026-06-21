import React, { useState, useEffect } from 'react';
import { motion, AnimatePresence } from 'framer-motion';
import { Plus, Search, Edit2, Trash2, Filter, X, ChevronDown, ChevronUp, Eye } from 'lucide-react';
import api from '../api';

const TenderForm = ({ tender, onClose, onSuccess }) => {
  const [formData, setFormData] = useState(
    tender || {
      companyName: '',
      industryType: '',
      headOfficeAddress: '',
      ncrOfficeLocation: '',
      contactPerson: '',
      designation: '',
      phoneNo: '',
      emailId: '',
      tenderReferenceNo: '',
      tenderStatus: '',
      typeOfInsurance: '',
      brokerInvolvement: '',
      existingBroker: '',
      opportunityStage: '',
      lastContactDate: '',
      remarksIntel: '',
      afterCallNotes: ''
    }
  );
  const [loading, setLoading] = useState(false);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData(prev => ({ ...prev, [name]: value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    try {
      if (tender?.id) {
        await api.put(`/tenders/${tender.id}`, formData);
      } else {
        await api.post('/tenders', formData);
      }
      onSuccess();
    } catch (error) {
      console.error('Error saving tender:', error);
      alert('Failed to save tender data');
    } finally {
      setLoading(false);
    }
  };

  return (
    <motion.div 
      initial={{ opacity: 0 }}
      animate={{ opacity: 1 }}
      exit={{ opacity: 0 }}
      className="modal-overlay"
    >
      <motion.div 
        initial={{ y: 50, opacity: 0, scale: 0.95 }}
        animate={{ y: 0, opacity: 1, scale: 1 }}
        exit={{ y: 20, opacity: 0, scale: 0.95 }}
        className="modal-content"
      >
        <div className="modal-header">
          <h2 style={{ fontSize: '1.25rem', fontWeight: 600 }}>
            {tender ? 'Edit Prospect / Tender' : 'Add New Prospect'}
          </h2>
          <button onClick={onClose} className="btn btn-secondary" style={{ padding: '0.5rem' }}>
            <X size={20} />
          </button>
        </div>
        
        <form onSubmit={handleSubmit}>
          <div className="modal-body form-grid">
            <div className="form-group full-width">
              <label className="form-label">Company Name *</label>
              <input required name="companyName" value={formData.companyName} onChange={handleChange} placeholder="e.g. Airports Authority Of India" />
            </div>
            
            <div className="form-group">
              <label className="form-label">Industry Type *</label>
              <input required name="industryType" value={formData.industryType} onChange={handleChange} placeholder="e.g. Airport/Aviation" />
            </div>

            <div className="form-group">
              <label className="form-label">Contact Person</label>
              <input name="contactPerson" value={formData.contactPerson} onChange={handleChange} placeholder="Name of contact" />
            </div>

            <div className="form-group">
              <label className="form-label">Designation</label>
              <input name="designation" value={formData.designation} onChange={handleChange} placeholder="e.g. DGM (E-C)" />
            </div>

            <div className="form-group">
              <label className="form-label">Phone No.</label>
              <input name="phoneNo" value={formData.phoneNo} onChange={handleChange} placeholder="Contact number" />
            </div>

            <div className="form-group">
              <label className="form-label">Email ID</label>
              <input name="emailId" value={formData.emailId} onChange={handleChange} placeholder="Email address" />
            </div>

            <div className="form-group">
              <label className="form-label">Broker Involvement</label>
              <select name="brokerInvolvement" value={formData.brokerInvolvement} onChange={handleChange}>
                <option value="">Select option</option>
                <option value="Yes">Yes</option>
                <option value="No">No</option>
                <option value="Unknown">Unknown</option>
              </select>
            </div>

            <div className="form-group">
              <label className="form-label">Existing Broker</label>
              <input name="existingBroker" value={formData.existingBroker} onChange={handleChange} placeholder="e.g. AON, Marsh" />
            </div>

            <div className="form-group">
              <label className="form-label">Tender Reference No.</label>
              <input name="tenderReferenceNo" value={formData.tenderReferenceNo} onChange={handleChange} placeholder="e.g. 2026/AAI/276578/1" />
            </div>

            <div className="form-group">
              <label className="form-label">Tender Status</label>
              <select name="tenderStatus" value={formData.tenderStatus} onChange={handleChange}>
                <option value="">Select status</option>
                <option value="Open / Active">Open / Active</option>
                <option value="Upcoming">Upcoming</option>
                <option value="Applied">Applied</option>
                <option value="Closed">Closed</option>
                <option value="To Be Tracked">To Be Tracked</option>
                <option value="N/A">N/A</option>
              </select>
            </div>

            <div className="form-group">
              <label className="form-label">Type of Insurance</label>
              <input name="typeOfInsurance" value={formData.typeOfInsurance} onChange={handleChange} placeholder="e.g. GMC; GPA" />
            </div>

            <div className="form-group">
              <label className="form-label">Opportunity Stage</label>
              <select name="opportunityStage" value={formData.opportunityStage} onChange={handleChange}>
                <option value="">Select stage</option>
                <option value="Active Opportunity">Active Opportunity</option>
                <option value="Meeting Done">Meeting Done</option>
                <option value="Meeting scheduled">Meeting Scheduled</option>
                <option value="Follow-Up Pending">Follow-Up Pending</option>
                <option value="Applied">Applied</option>
                <option value="Monitor">Monitor</option>
                <option value="Not Reachable">Not Reachable</option>
                <option value="Not Interested">Not Interested</option>
              </select>
            </div>

            <div className="form-group">
              <label className="form-label">Last Contact Date</label>
              <input type="date" name="lastContactDate" value={formData.lastContactDate} onChange={handleChange} />
            </div>

            <div className="form-group full-width">
              <label className="form-label">Head Office Address</label>
              <textarea name="headOfficeAddress" value={formData.headOfficeAddress} onChange={handleChange} rows="2" placeholder="Full address" />
            </div>

            <div className="form-group full-width">
              <label className="form-label">NCR / Local Office</label>
              <textarea name="ncrOfficeLocation" value={formData.ncrOfficeLocation} onChange={handleChange} rows="2" placeholder="NCR address" />
            </div>

            <div className="form-group full-width">
              <label className="form-label">Remarks / Intel</label>
              <textarea name="remarksIntel" value={formData.remarksIntel} onChange={handleChange} rows="2" placeholder="General remarks or intelligence" />
            </div>

            <div className="form-group full-width">
              <label className="form-label">After Call Notes</label>
              <textarea name="afterCallNotes" value={formData.afterCallNotes} onChange={handleChange} rows="2" placeholder="Outcome of the meeting/call" />
            </div>
          </div>
          
          <div className="modal-footer">
            <button type="button" onClick={onClose} className="btn btn-secondary">Cancel</button>
            <button type="submit" className="btn btn-primary" disabled={loading}>
              {loading ? 'Saving...' : 'Save Tender'}
            </button>
          </div>
        </form>
      </motion.div>
    </motion.div>
  );
};

/* Detail side-panel for viewing full tender info */
const TenderDetail = ({ tender, onClose }) => {
  if (!tender) return null;

  const fields = [
    { label: 'Company Name', value: tender.companyName },
    { label: 'Industry Type', value: tender.industryType },
    { label: 'Contact Person', value: tender.contactPerson },
    { label: 'Designation', value: tender.designation },
    { label: 'Phone No.', value: tender.phoneNo },
    { label: 'Email ID', value: tender.emailId },
    { label: 'Head Office Address', value: tender.headOfficeAddress },
    { label: 'NCR / Local Office', value: tender.ncrOfficeLocation },
    { label: 'Tender Reference No.', value: tender.tenderReferenceNo },
    { label: 'Tender Status', value: tender.tenderStatus },
    { label: 'Type of Insurance', value: tender.typeOfInsurance },
    { label: 'Broker Involvement', value: tender.brokerInvolvement },
    { label: 'Existing Broker', value: tender.existingBroker },
    { label: 'Opportunity Stage', value: tender.opportunityStage },
    { label: 'Last Contact Date', value: tender.lastContactDate },
    { label: 'Remarks / Intel', value: tender.remarksIntel },
    { label: 'After Call Notes', value: tender.afterCallNotes },
  ];

  return (
    <motion.div 
      initial={{ opacity: 0 }}
      animate={{ opacity: 1 }}
      exit={{ opacity: 0 }}
      className="modal-overlay"
    >
      <motion.div 
        initial={{ x: 100, opacity: 0 }}
        animate={{ x: 0, opacity: 1 }}
        exit={{ x: 100, opacity: 0 }}
        className="modal-content detail-panel"
      >
        <div className="modal-header">
          <h2 style={{ fontSize: '1.25rem', fontWeight: 600 }}>Prospect Details</h2>
          <button onClick={onClose} className="btn btn-secondary" style={{ padding: '0.5rem' }}>
            <X size={20} />
          </button>
        </div>
        <div className="modal-body" style={{ padding: '1.5rem' }}>
          {fields.map((f, i) => (
            <div key={i} className="detail-field">
              <div className="detail-label">{f.label}</div>
              <div className="detail-value">{f.value || '—'}</div>
            </div>
          ))}
        </div>
      </motion.div>
    </motion.div>
  );
};

const Tenders = () => {
  const [tenders, setTenders] = useState([]);
  const [loading, setLoading] = useState(true);
  const [searchQuery, setSearchQuery] = useState('');
  const [showForm, setShowForm] = useState(false);
  const [editingTender, setEditingTender] = useState(null);
  const [viewingTender, setViewingTender] = useState(null);
  const [filterStage, setFilterStage] = useState('');
  const [filterStatus, setFilterStatus] = useState('');
  const [showFilters, setShowFilters] = useState(false);

  const fetchTenders = async () => {
    setLoading(true);
    try {
      const endpoint = searchQuery ? `/tenders/search?query=${searchQuery}` : '/tenders';
      const response = await api.get(endpoint);
      setTenders(response.data);
    } catch (error) {
      console.error('Error fetching tenders:', error);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    const delayDebounceFn = setTimeout(() => {
      fetchTenders();
    }, 400);
    return () => clearTimeout(delayDebounceFn);
  }, [searchQuery]);

  const handleDelete = async (id) => {
    if (window.confirm('Are you sure you want to delete this record?')) {
      try {
        await api.delete(`/tenders/${id}`);
        fetchTenders();
      } catch (error) {
        console.error('Error deleting tender:', error);
        alert('Failed to delete tender');
      }
    }
  };

  const openAddForm = () => {
    setEditingTender(null);
    setShowForm(true);
  };

  const openEditForm = (tender) => {
    setEditingTender(tender);
    setShowForm(true);
  };

  const getBadgeClass = (val) => {
    if (!val) return '';
    const lower = val.toLowerCase().trim();
    if (lower === 'yes') return 'yes';
    if (lower === 'no') return 'no';
    if (lower === 'unknown') return 'can';
    return '';
  };

  const getStageBadgeClass = (val) => {
    if (!val) return 'stage-default';
    const lower = val.toLowerCase().trim();
    if (lower.includes('meeting done') || lower.includes('meeting scheduled')) return 'stage-success';
    if (lower.includes('active opportunity')) return 'stage-active';
    if (lower.includes('applied')) return 'stage-applied';
    if (lower.includes('follow-up')) return 'stage-followup';
    if (lower.includes('monitor')) return 'stage-monitor';
    if (lower.includes('not interested') || lower.includes('not intrested')) return 'stage-lost';
    if (lower.includes('not reachable')) return 'stage-unreachable';
    return 'stage-default';
  };

  const getStatusBadgeClass = (val) => {
    if (!val) return '';
    const lower = val.toLowerCase().trim();
    if (lower.includes('open') || lower.includes('active')) return 'status-open';
    if (lower === 'upcoming') return 'status-upcoming';
    if (lower === 'applied') return 'status-applied';
    if (lower === 'closed') return 'status-closed';
    if (lower === 'to be tracked') return 'status-track';
    return '';
  };

  // Filter logic
  const filteredTenders = tenders.filter(t => {
    if (filterStage && t.opportunityStage) {
      const norm = t.opportunityStage.toLowerCase().replace('intrested', 'interested');
      if (!norm.includes(filterStage.toLowerCase())) return false;
    } else if (filterStage && !t.opportunityStage) {
      return false;
    }
    if (filterStatus && t.tenderStatus) {
      const norm = t.tenderStatus.toLowerCase().replace('open/active', 'open / active');
      if (!norm.includes(filterStatus.toLowerCase())) return false;
    } else if (filterStatus && !t.tenderStatus) {
      return false;
    }
    return true;
  });

  return (
    <motion.div
      initial={{ opacity: 0, y: 20 }}
      animate={{ opacity: 1, y: 0 }}
      transition={{ duration: 0.5 }}
    >
      <div className="flex-between page-header">
        <div>
          <h1 className="page-title">Tenders & Prospects</h1>
          <p className="page-subtitle">Manage your outreach, contacts and tender status — {filteredTenders.length} records</p>
        </div>
        <button className="btn btn-primary" onClick={openAddForm}>
          <Plus size={20} /> Add Prospect
        </button>
      </div>

      <div className="glass-panel" style={{ padding: '1.5rem' }}>
        <div style={{ display: 'flex', flexWrap: 'wrap', alignItems: 'center', gap: '1rem', marginBottom: '1rem' }}>
          <div style={{ position: 'relative', flex: '1 1 250px', minWidth: 0 }}>
            <Search size={18} style={{ position: 'absolute', left: '1rem', top: '50%', transform: 'translateY(-50%)', color: 'var(--text-muted)' }} />
            <input 
              type="text" 
              placeholder="Search by company name..." 
              value={searchQuery}
              onChange={(e) => setSearchQuery(e.target.value)}
              style={{ paddingLeft: '2.5rem' }}
            />
          </div>
          <button className="btn btn-secondary" style={{ flexShrink: 0 }} onClick={() => setShowFilters(!showFilters)}>
            <Filter size={18} /> Filters {showFilters ? <ChevronUp size={14}/> : <ChevronDown size={14}/>}
          </button>
        </div>

        <AnimatePresence>
          {showFilters && (
            <motion.div 
              initial={{ height: 0, opacity: 0 }}
              animate={{ height: 'auto', opacity: 1 }}
              exit={{ height: 0, opacity: 0 }}
              style={{ overflow: 'hidden', marginBottom: '1rem' }}
            >
              <div style={{ display: 'flex', gap: '1rem', flexWrap: 'wrap', padding: '1rem', background: 'rgba(0,0,0,0.15)', borderRadius: '8px' }}>
                <div style={{ flex: '1 1 200px' }}>
                  <label className="form-label" style={{ marginBottom: '0.25rem', display: 'block' }}>Opportunity Stage</label>
                  <select value={filterStage} onChange={e => setFilterStage(e.target.value)}>
                    <option value="">All Stages</option>
                    <option value="Active Opportunity">Active Opportunity</option>
                    <option value="Meeting Done">Meeting Done</option>
                    <option value="Follow-Up Pending">Follow-Up Pending</option>
                    <option value="Applied">Applied</option>
                    <option value="Monitor">Monitor</option>
                    <option value="Not Reachable">Not Reachable</option>
                    <option value="Not Interested">Not Interested</option>
                  </select>
                </div>
                <div style={{ flex: '1 1 200px' }}>
                  <label className="form-label" style={{ marginBottom: '0.25rem', display: 'block' }}>Tender Status</label>
                  <select value={filterStatus} onChange={e => setFilterStatus(e.target.value)}>
                    <option value="">All Statuses</option>
                    <option value="Open / Active">Open / Active</option>
                    <option value="Upcoming">Upcoming</option>
                    <option value="Applied">Applied</option>
                    <option value="Closed">Closed</option>
                    <option value="To Be Tracked">To Be Tracked</option>
                    <option value="N/A">N/A</option>
                  </select>
                </div>
                <button className="btn btn-secondary" onClick={() => { setFilterStage(''); setFilterStatus(''); }} style={{ alignSelf: 'flex-end' }}>
                  Clear
                </button>
              </div>
            </motion.div>
          )}
        </AnimatePresence>

        {loading ? (
          <div className="flex-center" style={{ padding: '3rem' }}>
            <motion.div 
              animate={{ rotate: 360 }}
              transition={{ repeat: Infinity, duration: 1, ease: "linear" }}
              style={{ width: '32px', height: '32px', border: '3px solid rgba(99, 102, 241, 0.3)', borderTopColor: '#6366f1', borderRadius: '50%' }}
            />
          </div>
        ) : (
          <div className="table-container">
            <table>
              <thead>
                <tr>
                  <th>Company Name</th>
                  <th>Industry</th>
                  <th>Contact</th>
                  <th>Insurance Type</th>
                  <th>Tender Status</th>
                  <th>Broker</th>
                  <th>Opportunity Stage</th>
                  <th>Last Contact</th>
                  <th style={{ textAlign: 'right' }}>Actions</th>
                </tr>
              </thead>
              <tbody>
                <AnimatePresence>
                  {filteredTenders.map((tender, i) => (
                    <motion.tr 
                      key={tender.id}
                      initial={{ opacity: 0, y: 10 }}
                      animate={{ opacity: 1, y: 0 }}
                      exit={{ opacity: 0, scale: 0.95 }}
                      transition={{ delay: i * 0.02 }}
                    >
                      <td>
                        <div style={{ fontWeight: 600, maxWidth: '200px' }}>{tender.companyName}</div>
                        {tender.tenderReferenceNo && <div style={{ fontSize: '0.75rem', color: 'var(--accent-primary)', marginTop: '2px', fontFamily: 'monospace' }}>{tender.tenderReferenceNo}</div>}
                      </td>
                      <td>
                        <span style={{ fontSize: '0.85rem' }}>{tender.industryType}</span>
                      </td>
                      <td>
                        <div style={{ fontWeight: 500 }}>{tender.contactPerson || '—'}</div>
                        <div style={{ fontSize: '0.75rem', color: 'var(--text-muted)' }}>{tender.designation}</div>
                        {tender.phoneNo && <div style={{ fontSize: '0.75rem', color: 'var(--text-muted)' }}>{tender.phoneNo}</div>}
                      </td>
                      <td>
                        <span style={{ fontSize: '0.85rem' }}>{tender.typeOfInsurance || '—'}</span>
                      </td>
                      <td>
                        {tender.tenderStatus ? (
                          <span className={`badge ${getStatusBadgeClass(tender.tenderStatus)}`}>
                            {tender.tenderStatus}
                          </span>
                        ) : '—'}
                      </td>
                      <td>
                        <div>
                          {tender.brokerInvolvement ? (
                            <span className={`badge ${getBadgeClass(tender.brokerInvolvement)}`}>
                              {tender.brokerInvolvement}
                            </span>
                          ) : '—'}
                        </div>
                        {tender.existingBroker && tender.existingBroker !== 'Not Known' && tender.existingBroker !== 'No broker involvement' && (
                          <div style={{ fontSize: '0.7rem', color: 'var(--text-muted)', marginTop: '4px' }}>{tender.existingBroker}</div>
                        )}
                      </td>
                      <td>
                        {tender.opportunityStage ? (
                          <span className={`badge ${getStageBadgeClass(tender.opportunityStage)}`}>
                            {tender.opportunityStage}
                          </span>
                        ) : '—'}
                      </td>
                      <td>
                        <span style={{ fontSize: '0.85rem', color: 'var(--text-secondary)' }}>
                          {tender.lastContactDate || '—'}
                        </span>
                      </td>
                      <td style={{ textAlign: 'right' }}>
                        <div style={{ display: 'flex', gap: '0.35rem', justifyContent: 'flex-end' }}>
                          <button className="btn btn-secondary" style={{ padding: '0.4rem' }} onClick={() => setViewingTender(tender)} title="View Details">
                            <Eye size={15} />
                          </button>
                          <button className="btn btn-secondary" style={{ padding: '0.4rem' }} onClick={() => openEditForm(tender)} title="Edit">
                            <Edit2 size={15} />
                          </button>
                          <button className="btn btn-danger" style={{ padding: '0.4rem' }} onClick={() => handleDelete(tender.id)} title="Delete">
                            <Trash2 size={15} />
                          </button>
                        </div>
                      </td>
                    </motion.tr>
                  ))}
                  {filteredTenders.length === 0 && (
                    <tr>
                      <td colSpan="9" style={{ textAlign: 'center', padding: '3rem' }}>
                        No prospects found matching your search or filters.
                      </td>
                    </tr>
                  )}
                </AnimatePresence>
              </tbody>
            </table>
          </div>
        )}
      </div>

      <AnimatePresence>
        {showForm && (
          <TenderForm 
            tender={editingTender} 
            onClose={() => setShowForm(false)} 
            onSuccess={() => {
              setShowForm(false);
              fetchTenders();
            }}
          />
        )}
      </AnimatePresence>

      <AnimatePresence>
        {viewingTender && (
          <TenderDetail
            tender={viewingTender}
            onClose={() => setViewingTender(null)}
          />
        )}
      </AnimatePresence>
    </motion.div>
  );
};

export default Tenders;
