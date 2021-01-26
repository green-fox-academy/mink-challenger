import React from 'react';
import { Link } from 'react-router-dom';
import HeaderComponent from '../components/HeaderComponent';
import FooterComponent from '../components/FooterComponent';

function LandingPage() {
  return (
    <div>
      <HeaderComponent />
      <div className="landing-page">
        <Link to="/">Home</Link>
      </div>
      <FooterComponent />
    </div >
  );
}

export default LandingPage;