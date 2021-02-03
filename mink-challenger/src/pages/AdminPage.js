import React from 'react';
import ListChallenges from '../components/ListChallenges';
import HeaderComponent from '../components/HeaderComponent';
import FooterComponent from '../components/FooterComponent';
import CreateChallenge from '../components/CreateChallenge'

function AdminPage() {
    return (
        <div>
            <HeaderComponent />
            <div className="container">
                <ListChallenges />
                <CreateChallenge/>
            </div>
            <FooterComponent />
        </div >
    );
}

export default AdminPage;