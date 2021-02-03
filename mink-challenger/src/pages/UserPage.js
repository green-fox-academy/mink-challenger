import React from 'react';
import ListCommitments from '../components/ListCommitments';
import HeaderComponent from '../components/HeaderComponent';
import FooterComponent from '../components/FooterComponent';
import CreateCommitment from '../components/CreateCommitment';

function UserPage() {
    return (
        <div>
            <HeaderComponent />
            <div className="container">
                <ListCommitments />
                <CreateCommitment />
            </div>
            <FooterComponent />
        </div >
    );
}

export default UserPage;