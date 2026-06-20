import React, { useState, useEffect } from 'react';
import { motion, AnimatePresence } from 'framer-motion';
import { Plus, Search, Edit2, Trash2, Filter, X } from 'lucide-react';
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
      email: '',
      brokerInvolvement: '',
      remarks: '',
      afterCallRemarks: ''
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
              <label className="form-label">Email</label>
              <input type="email" name="email" value={formData.email} onChange={handleChange} placeholder="Email address" />
            </div>

            <div className="form-group">
              <label className="form-label">Broker Involvement</label>
              <select name="brokerInvolvement" value={formData.brokerInvolvement} onChange={handleChange}>
                <option value="">Select option</option>
                <option value="Yes">Yes</option>
                <option value="No">No</option>
                <option value="Can Involve">Can Involve</option>
              </select>
            </div>

            <div className="form-group full-width">
              <label className="form-label">Head Office Address</label>
              <textarea name="headOfficeAddress" value={formData.headOfficeAddress} onChange={handleChange} rows="2" placeholder="Full address" />
            </div>

            <div className="form-group full-width">
              <label className="form-label">NCR Office Location</label>
              <textarea name="ncrOfficeLocation" value={formData.ncrOfficeLocation} onChange={handleChange} rows="2" placeholder="NCR address" />
            </div>

            <div className="form-group full-width">
              <label className="form-label">Remarks</label>
              <textarea name="remarks" value={formData.remarks} onChange={handleChange} rows="2" placeholder="General remarks or notes" />
            </div>

            <div className="form-group full-width">
              <label className="form-label">Meeting outcome / After Call Remarks</label>
              <textarea name="afterCallRemarks" value={formData.afterCallRemarks} onChange={handleChange} rows="2" placeholder="Outcome of the meeting/call" />
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

const Tenders = () => {
  const [tenders, setTenders] = useState([]);
  const [loading, setLoading] = useState(true);
  const [searchQuery, setSearchQuery] = useState('');
  const [showForm, setShowForm] = useState(false);
  const [editingTender, setEditingTender] = useState(null);

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
    if (lower.includes('can involve')) return 'can';
    return '';
  };

  return (
    <motion.div
      initial={{ opacity: 0, y: 20 }}
      animate={{ opacity: 1, y: 0 }}
      transition={{ duration: 0.5 }}
    >
      <div className="flex-between page-header">
        <div>
          <h1 className="page-title">Tenders & Prospects</h1>
          <p className="page-subtitle">Manage your outreach, contacts and status</p>
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
          <button className="btn btn-secondary" style={{ flexShrink: 0 }}>
            <Filter size={18} /> Filters
          </button>
        </div>

        {loading ? (
          <div className="flex-center" style={{ padding: '3rem' }}>
            Loading data...
          </div>
        ) : (
          <div className="table-container">
            <table>
              <thead>
                <tr>
                  <th>Company Name</th>
                  <th>Industry</th>
                  <th>Contact Person</th>
                  <th>Broker Required</th>
                  <th>Status / Outcome</th>
                  <th style={{ textAlign: 'right' }}>Actions</th>
                </tr>
              </thead>
              <tbody>
                <AnimatePresence>
                  {tenders.map((tender, i) => (
                    <motion.tr 
                      key={tender.id}
                      initial={{ opacity: 0, y: 10 }}
                      animate={{ opacity: 1, y: 0 }}
                      exit={{ opacity: 0, scale: 0.95 }}
                      transition={{ delay: i * 0.03 }}
                    >
                      <td>
                        <div style={{ fontWeight: 600 }}>{tender.companyName}</div>
                        {tender.phoneNo && <div style={{ fontSize: '0.8rem', color: 'var(--text-muted)', marginTop: '4px' }}>{tender.phoneNo}</div>}
                      </td>
                      <td>{tender.industryType}</td>
                      <td>
                        <div>{tender.contactPerson || '-'}</div>
                        <div style={{ fontSize: '0.8rem', color: 'var(--text-muted)' }}>{tender.designation}</div>
                      </td>
                      <td>
                        {tender.brokerInvolvement ? (
                          <span className={`badge ${getBadgeClass(tender.brokerInvolvement)}`}>
                            {tender.brokerInvolvement}
                          </span>
                        ) : '-'}
                      </td>
                      <td>
                        <div style={{ maxWidth: '250px', whiteSpace: 'nowrap', overflow: 'hidden', textOverflow: 'ellipsis', color: tender.afterCallRemarks?.toLowerCase().includes('done') ? 'var(--success)' : 'inherit' }}>
                          {tender.afterCallRemarks || tender.remarks || '-'}
                        </div>
                      </td>
                      <td style={{ textAlign: 'right' }}>
                        <div style={{ display: 'flex', gap: '0.5rem', justifyContent: 'flex-end' }}>
                          <button className="btn btn-secondary" style={{ padding: '0.5rem' }} onClick={() => openEditForm(tender)}>
                            <Edit2 size={16} />
                          </button>
                          <button className="btn btn-danger" style={{ padding: '0.5rem' }} onClick={() => handleDelete(tender.id)}>
                            <Trash2 size={16} />
                          </button>
                        </div>
                      </td>
                    </motion.tr>
                  ))}
                  {tenders.length === 0 && (
                    <tr>
                      <td colSpan="6" style={{ textAlign: 'center', padding: '3rem' }}>
                        No prospects found matching your search.
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
    </motion.div>
  );
};

export default Tenders;
