import React, { useState, useEffect } from 'react';
import axios from 'axios';

function Virements() {
  const [virements, setVirements] = useState([]);
  const [beneficiaires, setBeneficiaires] = useState([]);
  const [formData, setFormData] = useState({
    beneficiaireId: '',
    ribSource: '',
    montant: '',
    description: '',
    type: 'NORMAL'
  });

  useEffect(() => {
    loadVirements();
    loadBeneficiaires();
  }, []);

  const loadVirements = async () => {
    const result = await axios.get('http://localhost:8080/api/virements');
    setVirements(result.data);
  };

  const loadBeneficiaires = async () => {
    const result = await axios.get('http://localhost:8080/api/beneficiaires');
    setBeneficiaires(result.data);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    await axios.post('http://localhost:8080/api/virements', {
      ...formData,
      dateVirement: new Date(),
      montant: parseFloat(formData.montant)
    });
    loadVirements();
    setFormData({
      beneficiaireId: '',
      ribSource: '',
      montant: '',
      description: '',
      type: 'NORMAL'
    });
  };

  return (
    <div>
      <h2>Virements</h2>
      
      <form onSubmit={handleSubmit}>
        <select
          value={formData.beneficiaireId}
          onChange={(e) => setFormData({...formData, beneficiaireId: e.target.value})}
        >
          <option value="">Sélectionnez un bénéficiaire</option>
          {beneficiaires.map(b => (
            <option key={b.id} value={b.id}>{b.nom} {b.prenom}</option>
          ))}
        </select>
        <input
          type="text"
          placeholder="RIB Source"
          value={formData.ribSource}
          onChange={(e) => setFormData({...formData, ribSource: e.target.value})}
        />
        <input
          type="number"
          placeholder="Montant"
          value={formData.montant}
          onChange={(e) => setFormData({...formData, montant: e.target.value})}
        />
        <input
          type="text"
          placeholder="Description"
          value={formData.description}
          onChange={(e) => setFormData({...formData, description: e.target.value})}
        />
        <select
          value={formData.type}
          onChange={(e) => setFormData({...formData, type: e.target.value})}
        >
          <option value="NORMAL">Normal</option>
          <option value="INSTANTANE">Instantané</option>
        </select>
        <button type="submit">Effectuer le virement</button>
      </form>

      <table>
        <thead>
          <tr>
            <th>Bénéficiaire ID</th>
            <th>RIB Source</th>
            <th>Montant</th>
            <th>Description</th>
            <th>Type</th>
            <th>Date</th>
          </tr>
        </thead>
        <tbody>
          {virements.map((v) => (
            <tr key={v.id}>
              <td>{v.beneficiaireId}</td>
              <td>{v.ribSource}</td>
              <td>{v.montant}</td>
              <td>{v.description}</td>
              <td>{v.type}</td>
              <td>{new Date(v.dateVirement).toLocaleDateString()}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default Virements; 