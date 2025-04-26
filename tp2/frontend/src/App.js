import React from 'react';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import Beneficiaires from './components/Beneficiaires';
import Virements from './components/Virements';

function App() {
  return (
    <Router>
      <div>
        <nav>
          <ul>
            <li><Link to="/beneficiaires">Bénéficiaires</Link></li>
            <li><Link to="/virements">Virements</Link></li>
          </ul>
        </nav>

        <Routes>
          <Route path="/beneficiaires" element={<Beneficiaires />} />
          <Route path="/virements" element={<Virements />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App; 