import React, { useState, useEffect } from 'react';
import axios from 'axios';

function Beneficiaires() {
  const [beneficiaires, setBeneficiaires] = useState([]);
  const [formData, setFormData] = useState({
    nom: '',
    prenom: '',
    rib: '',
    type: 'PHYSIQUE'
  });

  useEffect(() => {
    loadBeneficiaires();
  }, []);

  const loadBeneficiaires = async () => {
    const result = await axios.get('http://localhost:8080/api/beneficiaires');
    setBeneficiaires(result.data);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    await axios.post('http://localhost:8080/api/beneficiaires', formData);
    loadBeneficiaires();
    setFormData({ nom: '', prenom: '', rib: '', type: 'PHYSIQUE' });
  };

  return (
    <div>
      <h2>Bénéficiaires</h2>
      
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          placeholder="Nom"
          value={formData.nom}
          onChange={(e) => setFormData({...formData, nom: e.target.value})}
        />
        <input
          type="text"
          placeholder="Prénom"
          value={formData.prenom}
          onChange={(e) => setFormData({...formData, prenom: e.target.value})}
        />
        <input
          type="text"
          placeholder="RIB"
          value={formData.rib}
          onChange={(e) => setFormData({...formData, rib: e.target.value})}
        />
        <select
          value={formData.type}
          onChange={(e) => setFormData({...formData, type: e.target.value})}
        >
          <option value="PHYSIQUE">Physique</option>
          <option value="MORALE">Morale</option>
        </select>
        <button type="submit">Ajouter</button>
      </form>

      <table>
        <thead>
          <tr>
            <th>Nom</th>
            <th>Prénom</th>
            <th>RIB</th>
            <th>Type</th>
          </tr>
        </thead>
        <tbody>
          {beneficiaires.map((b) => (
            <tr key={b.id}>
              <td>{b.nom}</td>
              <td>{b.prenom}</td>
              <td>{b.rib}</td>
              <td>{b.type}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default Beneficiaires; 