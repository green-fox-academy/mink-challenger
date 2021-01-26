import React from 'react';
import LoginForm from '../components/LoginForm';
import HeaderComponent from '../components/HeaderComponent';
import FooterComponent from '../components/FooterComponent';

function LoginPage() {
    return (
        <div>
            <HeaderComponent />
            <div className="login-page">
                <LoginForm />
            </div>
            <FooterComponent />
        </div >
    )
}

export default LoginPage;